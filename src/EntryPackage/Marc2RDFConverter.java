/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntryPackage;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import java.io.Serializable;
import java.util.regex.Pattern;
import MLTools.RDFTriple;
import java.util.regex.Matcher;
import org.apache.spark.api.java.function.Function;
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
                              .master("local")
//			      .master("spark://192.168.1.106:7077")
                              .config("spark.driver.cores", 10)
			      .getOrCreate();
 
      JavaSparkContext spark = new JavaSparkContext(sparkSession.sparkContext());
      Marc2RDFConverter mrc = new Marc2RDFConverter();
        mrc.ShowataFromParquetFile(spark, sparkSession);
      mrc.convertMarctoRDF(spark, sparkSession);
            
        spark.stop();
    }
   
    private void convertMarctoRDF(JavaSparkContext spark,SparkSession ss)
    {
        /*
        Configuration configuration = new Configuration();
        configuration.set("textinputformat.record.delimiter", "LEADER");
        configuration.set("mapreduce.input.fileinputformat.inputdir", "/Users/user/Desktop/marctxt.txt");
        JavaPairRDD<LongWritable,Text> fileContent = spark.newAPIHadoopRDD(configuration, TextInputFormat.class, LongWritable.class, Text.class);
        JavaRDD<Text> marc_records = fileContent.values();   
        JavaRDD<String> n_triples = marc_records.map(new MarcToNTriplesFunction());
        JavaRDD<String> n_triples_lines = n_triples.flatMap(s -> Arrays.asList(s.split("\n")).iterator());
        JavaRDD<RDFTriple> rdf_triples = n_triples_lines.map(new LinesToRDFTripleFunction());
        Dataset<Row> dataset = ss.createDataFrame(rdf_triples, RDFTriple.class); 
        dataset.write().parquet("/usr/local/Cellar/hadoop/hdfs/input/Marc21ToRDFExperiment5.parquet");*/
    }
    
    private void ShowataFromParquetFile(JavaSparkContext spark, SparkSession ss)
     {
//        Dataset<Row> ds = ss.read().parquet("/usr/local/hadoop/input/csv2rdf_triples_set6.parquet");
        Dataset<Row> ds = ss.read().parquet("/usr/local/Cellar/hadoop/hdfs/input/Marc21ToRDFExperiment6.parquet");
        ds.printSchema();
//        Dataset<Row> dataset2 = ss.read().parquet("/Users/user/Desktop/PhD/ResearchData/rdf_triples.parquet");
//       ds.toJavaRDD().saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/FetchedRDF2");
//        ds.createOrReplaceTempView("rdf_triples");
//        Dataset<Row> namesDF = ss.sql("SELECT count(DISTINCT subject) from rdf_triples");
//        Dataset<Row> namesDF = ss.sql("SELECT subject,predicate,object from rdf_triples");
//        namesDF.toJavaRDD().saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/FetchedRDF2");
//        namesDF.show();
//        ds.createOrReplaceTempView("rdf_triples2");
//        ds.createOrReplaceTempView("rdf_triples3");
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
        
//        Dataset<Row> ds2 = ss.sql("SELECT *FROM rdf_triples s inner join rdf_triples p on (s.subject=p.subject) where s.predicate='http://www.marcont.org/ontology/2.1#hasAddress' AND s.object='\"University of South Carolina,\"'");
//        ds.show();
//        ds2.toJavaRDD().saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/FetchedRDF2");
//        subDS.toJavaRDD().saveAsTextFile("/Users/user/Desktop/PhD/ResearchData/FetchedRDF");
     }

    
    private static class LinesToRDFTripleFunction implements Function<String, RDFTriple> {

        public LinesToRDFTripleFunction() {
        }

        @Override
        public RDFTriple call(String line) {
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
        }
    }
  }

