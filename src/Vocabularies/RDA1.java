/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vocabularies;

import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.Property;

/**
 *
 * @author home
 */
public class RDA1 {
 
    
    // URI for vocabulary elements
    protected static final String uri = "http://RDVocab.info/Elements/";
    
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
