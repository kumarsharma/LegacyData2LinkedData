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
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import Mappings.Marc2RDFMapper;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.spark.api.java.function.Function;
import java.util.List;
import scala.Tuple2;
import org.apache.spark.api.java.function.FlatMapFunction;
/**
 *
 * @author userqq
 */
public class Marc2RDFConverter implements Serializable  {

public Marc2RDFConverter(){};

    public static void main(String[] args) throws Exception {

      SparkSession sparkSession = SparkSession
			      .builder()
			      .appName("MarcRecordReader")
//                              .master("local")
			      .master("spark://192.168.1.101:7077")
                              .config("spark.driver.cores", 2)
			      .getOrCreate();
            
            JavaSparkContext spark = new JavaSparkContext(sparkSession.sparkContext());
            
            Marc2RDFConverter mrc = new Marc2RDFConverter();
            mrc.ConvertListMarctoRDF(spark, sparkSession);
//            mrc.ShowataFromParquetFile(spark, sparkSession);
//            mrc.convertMarctoRDF(spark, sparkSession);
//            mrc.ConvertCSVtoDirectRDFTriple(args, spark, sparkSession);
            
        spark.stop();
    }
    
    private void ConvertListMarctoRDF(JavaSparkContext spark,SparkSession ss)
    {
        Configuration configuration = new Configuration();
        configuration.set("textinputformat.record.delimiter", "LEADER");
        configuration.set("mapreduce.input.fileinputformat.inputdir", "/Users/user/Desktop/PhD/ResearchData/marctxt2.txt");
            
        JavaPairRDD<LongWritable,Text> javaPairRDD = spark.newAPIHadoopRDD(configuration, TextInputFormat.class, LongWritable.class, Text.class);
//        JavaRDD<Tuple2<LongWritable, Text>> rdds = JavaRDD.fromRDD(javaPairRDD.rdd(), null);
//        List<Text> textRDD = javaPairRDD.values().collect();   
        //textRDD.saveAsTextFile("/Users/user/NetBeansProjects/SparkSample/marctxt3.txt");
            
        JavaRDD<String> rddsc = javaPairRDD.flatMap(
            
            new FlatMapFunction<Tuple2<LongWritable, Text>, String>() {
                
                @Override
                public Iterator<String> call(Tuple2<LongWritable, Text> t) throws Exception {
                    
                    return Arrays.asList(t._2.toString()).iterator();
                }
            });
        List<String> rddList = rddsc.collect();
        JavaRDD<String> rddT = spark.parallelize(rddList);
        
        /*
        Broadcast<String> broadcastBasePropertyURI = spark.broadcast("http://klyuniv.ac.in/ontology/property#");
        Broadcast<String> broadcastBaseResourceURI = spark.broadcast("http://klyuniv.ac.in/ontology/resource#");
        
        JavaRDD<Text> rddt = spark.parallelize(textRDD);
        
        JavaRDD<String> rdd_customers = rddt.map(new Function<Text, String>() {

            @Override
            public String call(Text text) {
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
                    System.out.println("RDF from Marc21:"+modStr);
                }
                
                return modStr;
            }
        });*/
//        rdd_customers.collect();
        rddT.saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/Marc21ToRDFTextSSS");
        
        /*JavaRDD<String> rdf_lines = rdd_customers.flatMap(s -> Arrays.asList(s.split("\n")).iterator());
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
        
        rdf_triples.collect();*/
        
//        rdf_triples.saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/Marc21ToRDFText");
        
//        Dataset<Row> dataset = ss.createDataFrame(rdf_triples, RDFTriple.class); 
//        dataset.write().parquet("/Users/user/Desktop/PhD/ResearchData/Marc21ToRDF.parquet");
//        dataset.write().parquet("/usr/local/hadoop/input/Marc21ToRDFslave003.parquet");
//        dataset.show();
    }
   
    private void convertMarctoRDF(JavaSparkContext spark,SparkSession ss)
    {
        Configuration configuration = new Configuration();
        configuration.set("textinputformat.record.delimiter", "LEADER");
        configuration.set("mapreduce.input.fileinputformat.inputdir", "/Users/user/Desktop/PhD/ResearchData/marctxt2.txt");
            
        JavaPairRDD<LongWritable,Text> javaPairRDD = spark.newAPIHadoopRDD(configuration, TextInputFormat.class, LongWritable.class, Text.class);
        JavaRDD<Text> textRDD = javaPairRDD.values();   
        //textRDD.saveAsTextFile("/Users/user/NetBeansProjects/SparkSample/marctxt3.txt");
            
        
        Broadcast<String> broadcastBasePropertyURI = spark.broadcast("http://klyuniv.ac.in/ontology/property#");
        Broadcast<String> broadcastBaseResourceURI = spark.broadcast("http://klyuniv.ac.in/ontology/resource#");
        
        JavaRDD<String> rdd_customers = textRDD.map(new Function<Text, String>() {

            @Override
            public String call(Text text) {
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
                    System.out.println("RDF from Marc21:"+modStr);
                }
                
                return modStr;
            }
        });
//        rdd_customers.collect();
        rdd_customers.saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/Marc21ToRDFText");
        
        /*JavaRDD<String> rdf_lines = rdd_customers.flatMap(s -> Arrays.asList(s.split("\n")).iterator());
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
        
        rdf_triples.collect();*/
        
//        rdf_triples.saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/Marc21ToRDFText");
        
//        Dataset<Row> dataset = ss.createDataFrame(rdf_triples, RDFTriple.class); 
//        dataset.write().parquet("/Users/user/Desktop/PhD/ResearchData/Marc21ToRDF.parquet");
//        dataset.write().parquet("/usr/local/hadoop/input/Marc21ToRDFslave003.parquet");
//        dataset.show();
    }
    
    private void ConvertCSVtoDirectRDFTriple(String args[], JavaSparkContext spark, SparkSession ss)
    {
        JavaRDD<String> lines = spark.textFile("/Users/user/NetBeansProjects/LegacyData2LinkedData/customers.csv");
        String header = lines.first();//take out header
        String headers[] = header.split(",");
        
        Broadcast<String[]> broadcastHeader = spark.broadcast(headers);
        Broadcast<String> broadcastBasePropertyURI = spark.broadcast("http://klyuniv.ac.in/ontology/property#");
        Broadcast<String> broadcastBaseResourceURI = spark.broadcast("http://klyuniv.ac.in/ontology/resource#");
        
        JavaRDD<String> filteredRows = lines.filter(new Function<String, Boolean>() {

            @Override
            public Boolean call(String row) {
                return !row.equals(header);
            }
        });//filter header
        
        
        //convert each line of csv data into rdf data in n-triples format
        /*JavaRDD<String> rdd_ntriples = filteredRows.map((String line) -> {
            
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
        });*/
        filteredRows.saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/csv_rdf_triples");
        /*
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
        });*/
        
        //create data-frmae
//        Dataset<Row> dataset = ss.createDataFrame(rdf_triples, RDFTriple.class); 
//        dataset.write().parquet("/Users/user/Desktop/PhD/ResearchData/csv_triples.parquet");
    }
    
    private void ShowataFromParquetFile(JavaSparkContext spark, SparkSession ss)
     {
//        Dataset<Row> ds = ss.read().parquet("/usr/local/hadoop/input/csv2rdf_triples_set6.parquet");
        Dataset<Row> ds = ss.read().parquet("/Users/user/Desktop/PhD/ResearchData/Marc21ToRDF.parquet");
//        Dataset<Row> dataset2 = ss.read().parquet("/Users/user/Desktop/PhD/ResearchData/rdf_triples.parquet");
        ds.createOrReplaceTempView("rdf_triples");
        ds.createOrReplaceTempView("rdf_triples2");
        ds.createOrReplaceTempView("rdf_triples3");
//        ds.select("subject").show();
//        Dataset<Row> dsResult = ds.filter("subject='http://klyuniv.ac.in/ontology/resource#_39'").filter("predicate='http://klyuniv.ac.in/ontology/property#Phone'");
//        dsResult.show();
//        Dataset<Row> namesDF = ss.sql("SELECT subject,predicate,object from rdf_triples").filter("subject='http://klyuniv.ac.in/ontology/resource#_39'").filter("predicate='http://klyuniv.ac.in/ontology/property#Phone'");
//        Dataset<Row> namesDF = ss.sql("SELECT subject,predicate,object from rdf_triples group by subject");
//        Dataset<Row> ds1 = ss.sql("SELECT object from rdf_triples").filter("predicate='http://klyuniv.ac.in/ontology/property#Type'").filter("object='\"VIP\"'");
//        Dataset<Row> ds2 = ss.sql("SELECT s.object from rdf_triples s inner join rdf_triples p on (s.subject=p.subject) where s.predicate='http://klyuniv.ac.in/ontology/property#FirstName' AND p.object='\"FREQUENT\"'");
//        Dataset<Row> ds2 = ss.sql("SELECT s.object as MBOX, s2.object as NAME from rdf_triples s inner join rdf_triples p on (s.subject=p.subject) inner join rdf_triples s2 on (s.subject=s2.subject) where s.predicate='http://klyuniv.ac.in/ontology/property#Email' AND s2.predicate='http://klyuniv.ac.in/ontology/property#FirstName' AND p.object='\"Ajay\"'");
//        ds2.show();
//        namesDF.show();
//        dataset.printSchema();
//        Dataset<Row> subDS = ss.sql("SELECT *FROM rdf_triples").filter("predicate='http://www.marcont.org/ontology/2.1#hasAddress'").filter("object='\"University of South Carolina,\"'");
//        subDS.show();
        
        Dataset<Row> ds2 = ss.sql("SELECT *FROM rdf_triples s inner join rdf_triples p on (s.subject=p.subject) where s.predicate='http://www.marcont.org/ontology/2.1#hasAddress' AND s.object='\"University of South Carolina,\"'");
        ds2.show();
        ds2.toJavaRDD().saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/FetchedRDF2");
//        subDS.toJavaRDD().saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/FetchedRDF");
     }
}

 class TrippleMapper implements Function, Serializable
{
    @Override
    public String call(Object t1) throws Exception {
        
        String line = t1.toString();
        String modStr = "";
        if(line.length()>50)
        {
            line = "LEADER"+line;
            StringToMarc reader = new StringToMarc();
            Record record = reader.recordFromString(line);
            String baseUri = "http://klyuniv.ac.in/ontology/property#";
            Model dataModel = ModelFactory.createDefaultModel();
            Model dataModel2 = ModelFactory.createDefaultModel();
            dataModel.setNsPrefix("property", baseUri);
            Marc2RDFMapper.createResourceFromRecordInModel(record, dataModel, dataModel2, false);
            String syntax = "N-TRIPLES";
            StringWriter out = new StringWriter();
            dataModel.write(out, syntax);
            modStr = out.toString();
            System.out.println("RDF from Marc21:"+modStr);
        }

        return modStr;
    }
    
}

