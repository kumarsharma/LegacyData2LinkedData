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
public class PROV {
 
     protected static final String uri = "http://www.w3.org/ns/prov#";
    
    // Return URI for name-space
    public static String getURI(  )
    {
        return uri;
    } 
    
    public static Property propertyForName(String name)
    {
        return new PropertyImpl(uri, name);
    }
    
    public static Resource resourceForName(String name)
    {
        return new ResourceImpl(uri, name);
    }
}
