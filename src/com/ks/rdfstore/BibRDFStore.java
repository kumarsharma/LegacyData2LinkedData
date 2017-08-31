/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ks.rdfstore;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.hp.hpl.jena.query.Dataset ;
import com.hp.hpl.jena.rdf.model.Model;

import com.hp.hpl.jena.query.Query ;
import com.hp.hpl.jena.query.QueryExecution ;
import com.hp.hpl.jena.query.QueryExecutionFactory ;
import com.hp.hpl.jena.query.QueryFactory ;
import com.hp.hpl.jena.query.ResultSet ;
import com.hp.hpl.jena.query.ResultSetFormatter ;
import com.hp.hpl.jena.query.QuerySolution ;
//import com.hp.hpl.jena.tdb.TDBFactory;

import MLTools.Tools;
import com.hp.hpl.jena.query.ReadWrite;

import com.hp.hpl.jena.tdb.TDBFactory;

/**
 *
 * @author home
 */
public class BibRDFStore {

    private File tdbDir;
    Dataset bibDataset;
    Model defaultModel;
   
    String graphName() 
    {
        return Tools.getBaseURI()+"bib_graph/"; 
    }
    
     public BibRDFStore()
    {
        try {
		File tdb_Dir = new File("/Users/user/Documents/RDFStore/TDBStore/Experiment1");
		this.tdbDir = tdb_Dir;
                bibDataset = TDBFactory.createDataset(tdbDir.getAbsolutePath());
                defaultModel = bibDataset.getDefaultModel();
            } catch (Exception ex) { ex.printStackTrace(); }
    }
     
    public void addModel(Model m)
    {
        defaultModel.add(m);
    }
    
    public void commitAndClose()
    {
        defaultModel.commit();
        defaultModel.close();
    }
    
    public void storeModelIntoTDB(Model m)
    {
        tdbDir.mkdir();
        Model model = bibDataset.getDefaultModel();
        model.add(m);
        model.commit();
        model.close();
    }
    
    public void testModel()
    {
        bibDataset.begin(ReadWrite.WRITE);
        
        Model model = bibDataset.getDefaultModel();
  	System.out.println("Default model's size: " + model.size());
        System.out.println("Resource Count: " + model.listSubjects().toList().size());
        
  	/*Iterator<String> it = bibDataset.listNames();
  	for (;it.hasNext();) {
  		String name = it.next();
  		model = bibDataset.getNamedModel(name);
  		System.out.println("Named graph " + name + " size: " + model.size());
   	}  */	
    }
    
    public Model getModel()
    {
        Model m = bibDataset.getDefaultModel();
        return m;
    }
    
}
