/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntryPackage;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import java.io.StringWriter;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import org.apache.spark.sql.SparkSession;
import org.marc4j.marc.Record;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import java.util.Arrays;
import MLTools.RDFTriple;
import Mappings.Marc2RDFMapper;
/**
 *
 * @author userqq
 */
public class Marc2RDFConverter {

public Marc2RDFConverter(){};

    public static void main(String[] args) throws Exception {

      SparkSession sparkSession = SparkSession
			      .builder()
			      .appName("MarcRecordReader")
			      .master("local")
                              .config("spark.driver.cores", 1)
			      .getOrCreate();
            
            JavaSparkContext spark = new JavaSparkContext(sparkSession.sparkContext());
            Marc2RDFConverter mrc = new Marc2RDFConverter();
//            mrc.convertMarctoRDF(spark, sparkSession);
            mrc.ConvertCSVtoDirectRDFTriple(args, spark, sparkSession);
            
        spark.stop();
    }
   
    private void convertMarctoRDF(JavaSparkContext spark,SparkSession ss)
    {
        Configuration configuration = new Configuration();
        configuration.set("textinputformat.record.delimiter", "LEADER");
        configuration.set("mapreduce.input.fileinputformat.inputdir", "/Users/user/NetBeansProjects/SparkSample/marctxt.txt");
            
        JavaPairRDD<LongWritable,Text> javaPairRDD = spark.newAPIHadoopRDD(configuration, TextInputFormat.class, LongWritable.class, Text.class);
        JavaRDD<Text> textRDD = javaPairRDD.values();    
        //textRDD.saveAsTextFile("/Users/user/NetBeansProjects/SparkSample/marctxt3.txt");
            
        Broadcast<String> broadcastBasePropertyURI = spark.broadcast("http://klyuniv.ac.in/ontology/property#");
        Broadcast<String> broadcastBaseResourceURI = spark.broadcast("http://klyuniv.ac.in/ontology/resource#");
        
        JavaRDD<String> rdd_customers = textRDD.map((Text text) -> {
            
            String line = text.toString();
            String modStr = "";
            if(line.length()>50)
            {
                line = "LEADER"+line;
                StringToMarc reader = new StringToMarc();
                Record record = reader.recordFromString(line);
                String baseUri = broadcastBasePropertyURI.getValue();
                Model dataModel = ModelFactory.createDefaultModel();
                Model dataModel2 = ModelFactory.createDefaultModel();
                dataModel.setNsPrefix("property", baseUri);
                Marc2RDFMapper.createResourceFromRecordInModel(record, dataModel, dataModel2, false);
                String syntax = "N-TRIPLES";
                StringWriter out = new StringWriter();
                dataModel.write(out, syntax);
                modStr = out.toString();
            }
         
            return modStr;
        });
        
        JavaRDD<String> rdf_lines = rdd_customers.flatMap(s -> Arrays.asList(s.split("\n")).iterator());
        JavaRDD<RDFTriple> rdf_triples = rdf_lines.map((String line) -> {
            
            Pattern pattern = Pattern.compile("<(.*?)>");
            Matcher matcher = pattern.matcher(line);
            RDFTriple triple = new RDFTriple();
            int count = 0;
            int end = 0;
            int totalLength = line.length();
            while(matcher.find())
            {
                if(count==0)
                {
                    String s = matcher.group(1);
                    triple.setSubject(s);
                    count +=1;
                }
                else if(count==1)
                {
                    String p = matcher.group(1);
                    triple.setPredictae(p);
                    count +=1;
                    end = matcher.end();
                }
                else if(count==2)
                {
                    String o = matcher.group(1);
                    triple.equals(o);
                    count +=1;
                }
                
                if (count == 2){
                    String o = line.substring(end+1,totalLength-2);
                    triple.setObject(o);
                }
            }
            return triple;
        });
        
        Dataset<Row> dataset = ss.createDataFrame(rdf_triples, RDFTriple.class); 
//        dataset.write().parquet("/Users/user/Desktop/PhD/ResearchData/Marc21ToRDF.parquet");
        dataset.write().parquet("/usr/local/hadoop/input/Marc21ToRDFslave003.parquet");
        dataset.show();
    }
    
    private void ConvertCSVtoDirectRDFTriple(String args[], JavaSparkContext spark, SparkSession ss)
    {
        JavaRDD<String> lines = spark.textFile(args[0]);
        String header = lines.first();//take out header
        String headers[] = header.split(",");
        
        Broadcast<String[]> broadcastHeader = spark.broadcast(headers);
        Broadcast<String> broadcastBasePropertyURI = spark.broadcast("http://klyuniv.ac.in/ontology/property#");
        Broadcast<String> broadcastBaseResourceURI = spark.broadcast("http://klyuniv.ac.in/ontology/resource#");
        
        JavaRDD<String> filteredRows = lines.filter(row -> !row.equals(header));//filter header
        
        //convert each line of csv data into rdf data in n-triples format
        JavaRDD<String> rdd_ntriples = filteredRows.map((String line) -> {
            
            String fields[] = line.split(",");

            String p[] = broadcastHeader.value();
            String baseUri = broadcastBasePropertyURI.getValue();
            String baseResUri = broadcastBaseResourceURI.getValue();
            Model biboModel = ModelFactory.createDefaultModel();
            biboModel.setNsPrefix("property", baseUri);
            Resource aRes = biboModel.createResource(baseResUri+"_"+fields[0]);

            int i = 0;
            for(String h : p)
            {
                aRes.addProperty(new PropertyImpl(baseUri, h), fields[i]);
                i++;
            }

            String syntax = "N-TRIPLES"; // also try "N-TRIPLE" and "TURTLE"
            StringWriter out = new StringWriter();
            biboModel.write(out, syntax);
            String modStr = out.toString();
            return modStr;
        });
        
        //convert n-triples into individual lines
        JavaRDD<String> rdf_lines = rdd_ntriples.flatMap(s -> Arrays.asList(s.split("\n")).iterator());
        
        //convert each line RDFTriple object
        JavaRDD<RDFTriple> rdf_triples = rdf_lines.map((String line) -> {
            
//            Pattern pattern = Pattern.compile("^(?:<([^>]+)>\\s*){2}<?([^>]+)>?$");  
            Pattern pattern = Pattern.compile("<(.*?)>");
            Matcher matcher = pattern.matcher(line);
            RDFTriple triple = new RDFTriple();
            int count = 0;
            int end = 0;
            int totalLength = line.length();
            while(matcher.find())
            {
                if(count==0)
                {
                    String s = matcher.group(1);
                    triple.setSubject(s);
                    count +=1;
                }
                else if(count==1)
                {
                    String p = matcher.group(1);
                    triple.setPredictae(p);
                    count +=1;
                    end = matcher.end();
                }
                else if(count==2)
                {
                    String o = matcher.group(1);
                    triple.equals(o);
                    count +=1;
                }
                
                if (count == 2){
                    String o = line.substring(end+1,totalLength-2);
                    triple.setObject(o);
                }
            }
            return triple;
        });
        
        //create data-frmae
        Dataset<Row> dataset = ss.createDataFrame(rdf_triples, RDFTriple.class); 
        dataset.write().parquet("/Users/user/Desktop/PhD/ResearchData/rdf_triples.parquet");
    }
}

