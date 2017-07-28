/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vocabularies;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;

/**
 *
 * @author home
 */
public class Bibo {
    
     // URI for vocabulary elements
    protected static String uri = "http://purl.org/ontology/bibo/";
    
    // Return URI for vocabulary elements
    public static String getURI(  )
    {
        return uri;
    } 
    
    public static Property propertyForName(String name)
    {
        return new PropertyImpl(uri, name);
    }
}
