/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntryPackage;

import Mappings.Marc2RDFMapper;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import java.io.StringWriter;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import java.util.regex.Pattern;

//marc headers
import org.marc4j.marc.Record;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;
import org.apache.hadoop.mapreduce.InputFormat;

import org.apache.spark.api.java.function.Function;

import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
/**
 *
 * @author user
 */
public class Marc2RDFConverter {
    
private static final Pattern SPACE = Pattern.compile(" ");


public Marc2RDFConverter(){};

    public static void main(String[] args) throws Exception {

        int state = 2;        
        if(state == 1)
        {
            //for csv
            SparkSession ss = SparkSession
              .builder()
              .appName("WordCount")
              .config("spark.master", "local")
              .getOrCreate();
            
            JavaSparkContext spark = new JavaSparkContext(ss.sparkContext());
            Marc2RDFConverter mrc = new Marc2RDFConverter();
            mrc.convertCSVtoRDF(args, spark);
            spark.stop();
        }
        else if(state == 2)
        {
            SparkSession sparkSession2 = SparkSession
			      .builder()
			      .appName("MarcRecordReader")
			      .master("local")
			      .getOrCreate();
            
            JavaSparkContext javaSparkContext = new JavaSparkContext(sparkSession2.sparkContext());
            Marc2RDFConverter mrc = new Marc2RDFConverter();
            mrc.convertMarctoRDF(javaSparkContext);  
            javaSparkContext.stop();
        }
    }
    
    private void convertCSVtoRDF(String args[], JavaSparkContext spark)
    {
        JavaRDD<String> lines = spark.textFile(args[0]);
        String header = lines.first();//take out header
        String headers[] = header.split(",");
        
        Broadcast<String[]> broadcastHeader = spark.broadcast(headers);
        Broadcast<String> broadcastBasePropertyURI = spark.broadcast("http://klyuniv.ac.in/ontology/property#");
        Broadcast<String> broadcastBaseResourceURI = spark.broadcast("http://klyuniv.ac.in/ontology/resource#");
        
        JavaRDD<String> filteredRows = lines.filter(row -> !row.equals(header));//filter header
        
        JavaRDD<String> rdd_customers = filteredRows.map((String line) -> {
            
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
        
        rdd_customers.saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/productsrdd");
    }
    
    private void convertMarctoRDF(JavaSparkContext spark)
    {
        Configuration configuration = new Configuration();
            configuration.set("textinputformat.record.delimiter", "LEADER");
            configuration.set("mapreduce.input.fileinputformat.inputdir", "/Users/user/NetBeansProjects/SparkSample/marctxt.txt");
            
        JavaPairRDD<LongWritable,Text> javaPairRDD = spark.newAPIHadoopRDD(configuration, TextInputFormat.class, LongWritable.class, Text.class);
        JavaRDD<Text> textRDD = javaPairRDD.values();    
        Broadcast<String> broadcastBasePropertyURI = spark.broadcast("http://klyuniv.ac.in/ontology/property#");
        Broadcast<String> broadcastBaseResourceURI = spark.broadcast("http://klyuniv.ac.in/ontology/resource#");
        
        JavaRDD<String> rdd_customers = textRDD.map((Text text) -> {
            
            String line = text.toString();
            
            if(line.length()>50)
            {
                line = "LEADER"+line;
                MarcStringReader reader = new MarcStringReader();
                Record record = reader.recordFromString(line);

                String baseUri = broadcastBasePropertyURI.getValue();
                String baseResUri = broadcastBaseResourceURI.getValue();
                Model biboModel = ModelFactory.createDefaultModel();
                Model foafModel = ModelFactory.createDefaultModel();
                biboModel.setNsPrefix("property", baseUri);
                Marc2RDFMapper.createResourceFromRecordInModel(record, biboModel, foafModel, true);

                String syntax = "N-TRIPLES"; // also try "N-TRIPLE" and "TURTLE"
                StringWriter out = new StringWriter();
                biboModel.write(out, syntax);
                String modStr = out.toString();
                return modStr;
            }
            
            return line;
        });
        
        rdd_customers.saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/marcrecordsparsed");
    }
}
