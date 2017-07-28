/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MappingClasses;

//jena
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.VCARD;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.vocabulary.DC_11;

//marc
import org.marc4j.marc.ControlField;

import Vocabularies.marcont;


/**
 *
 * @author home
 */
public class ML0_ControlInfo {
    
    
    public Property getPropertyForControlField(ControlField cf)
    {
        int tag = Integer.parseInt(cf.getTag());
        Property property;
        switch(tag)
        {
            
            case 001:
                property = DC_11.identifier;
                break;
                
            case 003:
                property = DC_11.creator;
                break;
                
            case 005:
                property = marcont.hasDate;
                break;
                
            case 8:
                property = marcont.hasCoverage;
                break;
                
//            case 010:
//                property = null;//LOC Control number, LOC- Library of congress
//                break;
            
            case 013:
                property = null; //patent control info
                break;
                
            case 015:
            property = null; //National bib no.
            break;
                    
            case 017:
            property = null; //copy right
            break;
                        
            case 020:
            property = null; //patent control info
            break;
             
                /*
                           case 013:
                property = null; //patent control info
                break;
                               
                               case 013:
                property = null; //patent control info
                break;
                */
                case 007:
                property = DC_11.format;
                break;
                
            case 072:
                property = DC_11.subject;
                break;
                
//            case 010:
//                property = DC_11.identifier;
//                break;
                
            case 044:
                property = DC_11.language;
                break;
            
            case 006:
                property = RDF.type;
                break;
   
            default:
                property = DC_11.subject;
                break;
        }
        
        return property;
    }
    
    
    
}
