/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntryPackage;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.io.FileNotFoundException;
import javax.swing.JTextPane;
//import java.util.*;

//marc headers
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.Record;

//mapping
import Mappings.Marc2RDFMapper;
import Vocabularies.RDA1;
import Vocabularies.OL;

//jena
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC_11;
import com.hp.hpl.jena.vocabulary.VCARD;

import Vocabularies.marcont;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.marc4j.MarcTxtWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import MLTools.RDFTriple;
import com.ks.rdfstore.BibRDFStore;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author home
 */
public class MarcConverter {
    
    File marcFile;
    Model biboModel, foafModel;
    JTextPane pane, pane2;
    int recordLimit;
    boolean addRDFLinks;
    
    public MarcConverter()
    {
    }
    
    public MarcConverter(File mFile, JTextPane pane, JTextPane pane2, int recordLimit, boolean addRDFLinks)
    {
        biboModel = ModelFactory.createDefaultModel();
        foafModel = ModelFactory.createDefaultModel();
                
        biboModel.setNsPrefix("rdf", RDF.getURI());
        biboModel.setNsPrefix("rdfs", RDFS.getURI());
        biboModel.setNsPrefix("foaf", FOAF.NS);
        biboModel.setNsPrefix("DC_11", DC_11.NS);
        biboModel.setNsPrefix("DC", DCTerms.NS);
        biboModel.setNsPrefix("marcont", marcont.getURI());
        biboModel.setNsPrefix("VCARD", VCARD.getURI());
        biboModel.setNsPrefix("rdagroup1elements", RDA1.getURI());
        biboModel.setNsPrefix("ol", OL.getURI());
        
        foafModel.setNsPrefix("rdfs", RDFS.getURI());
        foafModel.setNsPrefix("rdf", RDF.getURI());
        foafModel.setNsPrefix("foaf", FOAF.NS);
        foafModel.setNsPrefix("DC_11", DC_11.NS);
        foafModel.setNsPrefix("DC", DCTerms.NS);
        foafModel.setNsPrefix("marcont", marcont.getURI());
        foafModel.setNsPrefix("VCARD", VCARD.getURI());
        foafModel.setNsPrefix("rdagroup1elements", RDA1.getURI());
        
        this.marcFile = mFile;
        this.pane = pane;
        this.pane2 = pane2;
        this.recordLimit = recordLimit;
    }
    
    private Model getABibModel()
    {
        Model m = ModelFactory.createDefaultModel();                
        m.setNsPrefix("rdf", RDF.getURI());
        m.setNsPrefix("rdfs", RDFS.getURI());
        m.setNsPrefix("foaf", FOAF.NS);
        m.setNsPrefix("DC_11", DC_11.NS);
        m.setNsPrefix("DC", DCTerms.NS);
        m.setNsPrefix("marcont", marcont.getURI());
        m.setNsPrefix("VCARD", VCARD.getURI());
        m.setNsPrefix("rdagroup1elements", RDA1.getURI());
        m.setNsPrefix("ol", OL.getURI());
        return m;
    }
    
    private Model getAFoafModel()
    {
        Model m = ModelFactory.createDefaultModel();                
        m.setNsPrefix("rdfs", RDFS.getURI());
        m.setNsPrefix("rdf", RDF.getURI());
        m.setNsPrefix("foaf", FOAF.NS);
        m.setNsPrefix("DC_11", DC_11.NS);
        m.setNsPrefix("DC", DCTerms.NS);
        m.setNsPrefix("marcont", marcont.getURI());
        m.setNsPrefix("VCARD", VCARD.getURI());
        m.setNsPrefix("rdagroup1elements", RDA1.getURI());
        return m;
    }
    
    public Model ConverMarcWithFile() 
    {
        InputStream in = null;
        try{
            
            in = new FileInputStream(this.marcFile.getAbsolutePath());
        }
        catch(java.io.FileNotFoundException e)
        {
            System.out.println("################### ERROR: Supplied File not found ###################"); 
           return null;
        }
                
        pane.setText("################### Marc21 Records ###################\n");
        pane2.setText("################### RDF Triples ###################\n");
        
        MarcReader reader = new MarcStreamReader(in);
        
        String paneText = "";
        String pane2Text = pane2.getText();
        
        int recordCount = 0;
        while(reader.hasNext())
        {
            try{
                recordCount++;
                Record record = reader.next(); 
                paneText = paneText.concat(record.toString() + "ENDRECORD");
                Marc2RDFMapper.createResourceFromRecordInModel(record, biboModel, foafModel, addRDFLinks);
                System.out.println("Record Count: " + recordCount); 

                if(recordCount > this.recordLimit)
                    break;
                }catch(org.marc4j.MarcException me){}
        }
        pane.setText(paneText);
        System.out.println("Writing to text area");
        this.writeModelToTextArea(biboModel, "RDF/XML", pane2);
        this.writeModelToFile(biboModel, "RDF/XML", "mark21rdfdata");
        return biboModel;
    }
    
    public Model ConverMarcAndStoreIntoTDB() 
    {
        InputStream in = null;
        try{
            
            in = new FileInputStream(this.marcFile.getAbsolutePath());
        }
        catch(java.io.FileNotFoundException e)
        {
            System.out.println("################### ERROR: Supplied File not found ###################"); 
           return null;
        }
                
        pane.setText("################### Marc21 Records ###################\n");
        pane2.setText("################### RDF Triples ###################\n");
        
        MarcReader reader = new MarcStreamReader(in);
        
        BibRDFStore store = new BibRDFStore();
        String paneText = "";
        
        int recordCount = 0;
        int modelRecordCountLimit = 10000, modelRecordCount=0;
        Model bibM = this.getABibModel();
        Model foafM = this.foafModel;
        while(reader.hasNext())
        {
            try{
                recordCount++;
                Record record = reader.next(); 
                paneText = paneText.concat(record.toString() + "ENDRECORD");
                
                Marc2RDFMapper.createResourceFromRecordInModel(record, bibM, foafM, addRDFLinks);
                System.out.println("Record Count: " + recordCount); 
                modelRecordCount++;
                if(modelRecordCount==modelRecordCountLimit)
                {
                    store.addModel(biboModel);
                    store.addModel(bibM);
                    store.commitAndClose();
                    modelRecordCount=0;
                    bibM = this.getABibModel();
                    foafM = this.foafModel;
                }

                if(this.recordLimit>0 && recordCount > this.recordLimit)
                    break;
                }catch(org.marc4j.MarcException me){}
        }
        store.commitAndClose();
        System.out.println("Legacy data converted into RDF and stored into TDB");
        pane2.setText("Legacy data converted into RDF and stored into TDB");
        return biboModel;
    }
    
     public Model ConverTextMarcAndStoreIntoTDB() 
    {
        InputStream in = null;
        BufferedReader br = null;
        try{
            
            in = new FileInputStream(this.marcFile.getAbsolutePath());
            br = new BufferedReader(new InputStreamReader(in));
        }
        catch(java.io.FileNotFoundException e)
        {
            System.out.println("################### ERROR: Supplied File not found ###################"); 
           return null;
        }
                        
        BibRDFStore store = new BibRDFStore();
        
        int recordCount = 0;
        int modelRecordCountLimit = 100000, modelRecordCount=0;
        Model bibM = this.getABibModel();
        Model foafM = this.foafModel;
        
        String strLine = "";
        String marcRecord = "";
        String lastLine = "";
        StringToMarc reader = new StringToMarc();
        String RDF = "";
        try{
            while ((strLine = br.readLine()) != null)   {

              if(strLine.length()>0)
                marcRecord += strLine+"\n";
              if(strLine.equalsIgnoreCase("") && lastLine.contains("906"))
              {
                  recordCount++;
                  Record record = reader.recordFromString(marcRecord);
//                  RDF += Marc2RDFMapper.getRDFInN_TriplesForRecord(record, addRDFLinks);
                  Marc2RDFMapper.createResourceFromRecordInModel(record, bibM, foafM, addRDFLinks);
                  System.out.println("Record Count: " + recordCount); 
                  modelRecordCount++;
                    
                    if(modelRecordCount==modelRecordCountLimit)
                    {
                        store.addModel(biboModel);
                        store.addModel(bibM);
                        store.commitAndClose();
                        modelRecordCount=0;
                        bibM.removeAll();// = this.getABibModel();
                        foafM.removeAll();// = this.foafModel;
                    }
                    
                   marcRecord = "";
                    if(this.recordLimit>0 && recordCount > this.recordLimit)
                        break;
              }
            lastLine = strLine;
        }
        }catch(java.io.IOException ioe){}
        
        store.addModel(biboModel);
        store.addModel(bibM);
        store.commitAndClose();
        System.out.println("Legacy data converted into RDF and stored into TDB");
        pane2.setText("Legacy data converted into RDF and stored into TDB");
        return biboModel;
    }
     
     public void testTDBStore()
     {
         BibRDFStore store = new BibRDFStore();
         store.testModel();
     }
    
    public Model ConverMarcAndStoreIntoFiles() 
    {
        InputStream in = null;
        try{
            
            in = new FileInputStream(this.marcFile.getAbsolutePath());
        }
        catch(java.io.FileNotFoundException e)
        {
            System.out.println("################### ERROR: Supplied File not found ###################"); 
           return null;
        }
                
        pane.setText("################### Marc21 Records ###################\n");
        pane2.setText("################### RDF Triples ###################\n");
        
        MarcReader reader = new MarcStreamReader(in);
        String paneText = "";
        
        int recordCount = 0;
        int modelRecordCountLimit = 1000, modelRecordCount=0;
        int partCount = 1;
        while(reader.hasNext())
        {
            try{
                recordCount++;
                Record record = reader.next(); 
                paneText = paneText.concat(record.toString() + "ENDRECORD");
                
                Marc2RDFMapper.createResourceFromRecordInModel(record, biboModel, foafModel, addRDFLinks);
                System.out.println("Record Count: " + recordCount); 
                modelRecordCount++;
                if(modelRecordCount==modelRecordCountLimit)
                {
                    this.writeModelToFile(biboModel, "RDF/XML", "mark21rdf_part_"+partCount);
                    biboModel.removeAll();
                    foafModel.removeAll();
                    partCount++;
                    modelRecordCount=0;
                }

                if(recordCount > this.recordLimit)
                    break;
                }catch(org.marc4j.MarcException me){}
        }
       
        System.out.println("Legacy data converted into RDF and stored into Files");
        pane2.setText("Legacy data converted into RDF and stored into Files");
        return biboModel;
    }
    
    public void converMarcToText() 
    {
        InputStream in = null;
        OutputStream out = null;

        try{
            
            in = new FileInputStream(this.marcFile.getAbsolutePath());
            out = new FileOutputStream("/Users/user/Desktop/marctxt.txt");
        }
        catch(java.io.FileNotFoundException e)
        {
           System.out.println("################### ERROR: Supplied File not found ###################"); 
        }
                
        pane.setText("################### Marc21 Records ###################\n");
        pane2.setText("################### RDF Triples ###################\n");
        MarcReader reader = new MarcStreamReader(in);        
        String paneText = "";
        int recordCount = 0;
        MarcTxtWriter writer = new MarcTxtWriter(out); 

        while(reader.hasNext())
        {
            try{
                    recordCount++;
                    Record record = reader.next(); 
                    writer.write(record);
//                    paneText = paneText.concat(record.toString() + "ENDRECORD");
                    System.out.println("Record Count: " + recordCount);                            
                    if(this.recordLimit>0 && recordCount > this.recordLimit)
                        break;
                }catch(org.marc4j.MarcException me){}
        }
        
        try{
            writer.close();
        out.close();
        }catch(Exception e){}
        System.out.println("MARC 21 data as string: "+paneText);
        pane.setText(paneText);        
    }
    
    public void converMarcToTextAndAppendToOneFile() 
    {
        InputStream in = null;
        PrintWriter out = null;
        try{
            
            in = new FileInputStream(this.marcFile.getAbsolutePath());
            out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/user/Desktop/marctxt.txt", true)));
        }
        catch(java.io.IOException e)
        {
           System.out.println("################### ERROR: Supplied File not found ###################"); 
        }
                
        MarcReader reader = new MarcStreamReader(in); 
        int recordCount = 0;
        
        while(reader.hasNext())
        {
            try{
                    recordCount++;
                    Record record = reader.next(); 
                    out.println(record.toString());
                    System.out.println("Record Count: " + recordCount);                            
                }catch(org.marc4j.MarcException me){}
        }
        
        try{
            
            out.close();
        }catch(Exception e){}
        pane.setText("Finished Appending");        
    }
    
     public void ConvertMarc21ToRDFUsingSpark()
    {
        SparkSession sparkSession = SparkSession
			      .builder()
			      .appName("MarcRecordReader")
//                              .master("local")
			      .master("spark://192.168.1.106:7077")
                              .config("spark.driver.cores", 2)
                              .config("spark.executor.uri", "/Users/user/spark-2.2.0-bin-hadoop2.7")
			      .getOrCreate();
            
        JavaSparkContext spark = new JavaSparkContext(sparkSession.sparkContext());
        Configuration configuration = new Configuration();
        configuration.set("textinputformat.record.delimiter", "LEADER");
        
        String path = this.marcFile.getAbsolutePath();
        configuration.set("mapreduce.input.fileinputformat.inputdir", path);
            
        JavaPairRDD<LongWritable,Text> javaPairRDD = spark.newAPIHadoopRDD(configuration, TextInputFormat.class, LongWritable.class, Text.class);
        JavaRDD<Text> textRDD = javaPairRDD.values(); 
        
        Broadcast<String> broadcastBasePropertyURI = spark.broadcast("http://klyuniv.ac.in/ontology/property#");
        Broadcast<String> broadcastBaseResourceURI = spark.broadcast("http://klyuniv.ac.in/ontology/resource#");
        
        JavaRDD<String> marc_rdf = textRDD.map((Text text) -> {
            
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
            else
            {
                System.out.println("ERROR: " + line);
            }
            return modStr;
        });
        
        JavaRDD<String> rdf_lines = marc_rdf.flatMap(s -> Arrays.asList(s.split("\n")).iterator());
        
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
        rdf_triples.collect();
        Dataset<Row> dataset = sparkSession.createDataFrame(rdf_triples, RDFTriple.class); 
        dataset.write().parquet("/Users/user/Documents/RDFStore/ParquetStore/Experiment1/Marc21ToRDF.parquet");
        spark.stop();
    }
     
     private void showCSVDataFromParquetFile(JavaSparkContext spark, SparkSession ss)
     {
        Dataset<Row> ds = ss.read().parquet("/usr/local/hadoop/input/Marc21ToRDF.parquet");
        ds.createOrReplaceTempView("rdf_triples");
//        ds.select("subject").show();
        ds.filter("subject='http://klyuniv.ac.in/ontology/resource#_39'").filter("predicate='http://klyuniv.ac.in/ontology/property#Phone'").show();
//        Dataset<Row> namesDF = ss.sql("SELECT subject from rdf_triples where object>42");
//        namesDF.show();
//        dataset.printSchema();
//        Dataset<Row> subDS = ss.sql("SELECT * FROM rdf_triples where $\"predicate\" === \"<http://klyuniv.ac.in/ontology/property#Phone>\"");
//        subDS.show();
     }
    
    private void writeModelToTextArea(Model m, String format, JTextPane pane)
    {
        pane.setText("");
         //redirect output to textarea
        ByteArrayOutputStream pipeOut = new ByteArrayOutputStream();
         // Store the current System.out
        PrintStream old_out = System.out;
         // Replace redirect output to our stream
        System.setOut(new PrintStream(pipeOut));
        // Revert back to the old System.out
        System.setOut(old_out);
//         m.write(pipeOut, "N3");
        m.write(pipeOut, format);
         Long noOfStmts = m.size();
         String no_ofStmts = noOfStmts.toString();
         
         String stmts = new String(pipeOut.toByteArray());

        pane.setText("Number of statements:" + no_ofStmts + "\n" + stmts);        
    }
    
    //write any model to a given file
    public void writeModelToFile(Model m, String format, String fileName)
    {
        try{
            String outputPath = "/Users/user/Documents/RDFStore/FileStore/Experiment1/";
            java.io.FileOutputStream fout = null;
            if(format.equalsIgnoreCase("RDF/XML"))
                fout = new java.io.FileOutputStream(outputPath+fileName+".rdf");
            else if(format.equalsIgnoreCase("N3"))
                fout = new java.io.FileOutputStream(outputPath+fileName+".n3");
            else if(format.equalsIgnoreCase("TURTLE") || format.equalsIgnoreCase("TTL"))
                fout = new java.io.FileOutputStream(outputPath+fileName+".ttl");
            else if(format.equalsIgnoreCase("N-TRIPLES") || format.equalsIgnoreCase("NT") || format.equalsIgnoreCase("N-TRIPLE"))
                fout = new java.io.FileOutputStream(outputPath+fileName+".nt");
            
            m.write(fout);
        }catch(Exception ie){}
    }
}
