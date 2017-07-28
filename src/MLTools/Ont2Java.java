/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MLTools;

import com.ibm.adtech.jastor.JastorGenerator;
import com.ibm.adtech.jastor.JastorContext;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author home
 */
public class Ont2Java {
    
    
    public static boolean generateJavaFromOnt(String ontFilePath, String destFilePath) 
    {
        JastorGenerator jgen = null;
        
        ontFilePath = "/Users/home/OWLs/bibo.xml.owl";
        
        try{
        JastorContext jcontext = new JastorContext();
        jcontext.addOntologyToGenerate(new FileInputStream(ontFilePath), "http://purl.org/ontology/bibo/", "Vocabularies");
        
        jgen = new JastorGenerator(
                          new File("/Users/home/OWLs/bibo").getCanonicalFile(),
                          jcontext);
        }catch(FileNotFoundException fnf){}
        
        catch(IOException ie){}
        
        if(null != jgen)
            jgen.run();
        
        return false;
    }
}
