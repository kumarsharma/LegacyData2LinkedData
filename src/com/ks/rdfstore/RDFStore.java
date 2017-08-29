/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ks.rdfstore;

import com.hp.hpl.jena.query.Dataset ;
import com.hp.hpl.jena.rdf.model.Model;

import com.hp.hpl.jena.tdb.TDBFactory;
import java.io.File;

/**
 *
 * @author apple
 */
public class RDFStore {
    
    Dataset ds;
    
    public void initializeStore()
    {
        File file = new File("/Users/apple/Datasets2");
        ds = TDBFactory.createDataset(file.getAbsolutePath());
        Model model = ds.getDefaultModel();
        
        ds.close();
    }
    
}
