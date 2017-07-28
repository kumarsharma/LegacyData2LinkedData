/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vocabularies;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;

/**
 *
 * @author home
 */
public class VoID {
 
    protected static final String uri = "http://rdfs.org/ns/void#";
    
    
    static final String   kDataset = "Dataset";
    public static       Resource Dataset = null;
    public static       Resource Linkset = null;
    public static       Resource TechnicalFeature = null;
    public static       Resource DatasetDescription = null;
   
    
    // Return URI for vocabulary elements
    public static String getURI(  )
    {
        return uri;
    } 
    
    public static Property propertyForName(String name)
    {
        return new PropertyImpl(uri, name);
    }
    
    static 
    { 
        try {     
                Dataset = new ResourceImpl(uri, kDataset);
                DatasetDescription = new ResourceImpl(uri, "TechnicalFeature");
                TechnicalFeature = new ResourceImpl(uri, "TechnicalFeature");
                Linkset = new ResourceImpl(uri, "Linkset"); 
            } 
            catch (Exception e) 
            {
                System.out.println(e);
            }
    }
}
