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
import org.marc4j.MarcTxtWriter;

/**
 *
 * @author home
 */
public class MarcConverter {
    
    File marcFileName;
    Model biboModel, foafModel;
    
    public MarcConverter()
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
    }
    
    public Model converMarcWithFile(File mFile, JTextPane pane, JTextPane pane2) 
    {
        this.marcFileName = mFile;
        InputStream in = null;
        OutputStream out = null;

        try{
            
            in = new FileInputStream(this.marcFileName.getAbsolutePath());
            out = new FileOutputStream("/Users/user/NetBeansProjects/SparkSample/marctxt.txt");
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
        
        String data = "";
        
        MarcTxtWriter writer = new MarcTxtWriter(out); 
        while(reader.hasNext())
        {
            try{
                
                    recordCount++;
                    Record record = reader.next(); 
                    writer.write(record);
                    
                    paneText = paneText.concat(record.toString() + "ENDRECORD");
                    Marc2RDFMapper.createResourceFromRecordInModel(record, biboModel, foafModel, true);
                    System.out.println("Record Count: " + recordCount);                            
                    if(recordCount > 100)
                        break;
                }catch(org.marc4j.MarcException me){}
        }
        
        try{
            writer.close();
        out.close();
        }catch(Exception e){}
        System.out.println("MARC 21 data as string: "+paneText);
        
        pane.setText(paneText);
        
        System.out.println("Writing to text area");
        
        this.writeModelToTextArea(biboModel, "RDF/XML", pane2);
        this.writeModelToFile(biboModel, "RDF/XML", "mark21rdfdata");
        
        NsIterator it = biboModel.listNameSpaces();
        System.out.println("Listing NS");
        while(it.hasNext())
        {
            System.out.println("NS: " + it.nextNs());
        }
        
        return biboModel;
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
            String outputPath = "/Users/user/Desktop/";
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
