/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MappingClasses;

import Vocabularies.*;

import com.googlecode.fascinator.vocabulary.BIBO;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.VCARD;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;

/**
 *
 * @author home
 */
public class ML0109_NumCodeInfo  extends ML00_DataInfo {
    
    
    public Property getPropertyForSubFieldUsingDataField(Subfield sf, DataField df)
    {
        int dTag = Integer.parseInt(df.getTag());
        char sTag = sf.getCode();
        
        Property property = null;
        switch(dTag)
        {
            case 010: //series statement
            {
                switch(sTag)
                {
                    case 'a':
                        property = marcont.hasNumber;//LC Control num
                        break;

                    case 'b':
                        property = null; //NUCMC control number 
                        break;

                    case 'z':
                        property = marcont.hasVolume; //Canceled/invalid LC control number (R) 
                        break;         
                        
                    case '8':
                        property = marcont.hasPagesFrom;//field link and seq no.
                        break;      

                    default:
                        property = null;
                        break; 
                        
                }
            }
                break;
                
                
            case 020: //series statement
            {
                switch(sTag)
                {
                    case 'a':
                        property = marcont.hasISBN;//- International Standard Book Number (NR) 
                        break;
                        
                    case 'c':
                        property = RDA1.propertyForName("termsOfAvailability");//- Terms of availability (NR) 
                        break;
                        
                    case 'z':
                        property = null;//- Canceled/invalid ISBN (R)
                        break;
                        
                    case '6':
                        property = null;//- Linkage (NR)
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
                
             case 022: //series statement
            {
                switch(sTag)
                {
                    case 'a':
                        property = marcont.hasISSN;//- International Standard Serial Number (NR) 
                        break;
                        
                    case 'l':
                        property = null;//- ISSN-L (NR) 
                        break;
                        
                    case 'm':
                        property = null;//- Canceled ISSN-L (R) 
                        break;
                        
                    case 'y':
                        property = null;//- Incorrect ISSN (R) 
                        break;
                        
                    case 'z':
                        property = null;//- Canceled ISSN (R) 
                        break;
                        
                    case '2':
                        property = null;//- Source (NR) 
                        break;
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                }
            }
                break;    
                
            case 040: //series statement
            {
                switch(sTag)
                {
                    case 'a':
                        property = marcont.hasSource;// Original cataloging agency 
                        break;

                    case 'b':
                        property = DC_11.language; // Language of cataloging
                        break;

                    case 'c':
                        property = DCTerms.mediator; //Transcribing agency 
                        break;  
                        
                        
                    case 'd':
                        property = null; // Modifying agency (R)
                    break;

                    case 'e':
                        property = null; //Description conventions (R)
                        break;  
                        
                        
                    case '6':
                        property = null;// Linkage (NR)
                        break;      
                        
                    case '8':
                        property = marcont.hasPagesFrom;//field link and seq no.
                        break;      

                    default:
                        property = null;
                        break; 
                        
                }
            }
                break;  
                
                
             case 043: //series statement
            {
                switch(sTag)
                {
                    case 'a':
                        property = VCARD.GEO;// Geographic area code (R)
                        break;

                    case 'b':
                        property = null; //  Local GAC code (R)
                        break;

                    case 'c':
                        property = null; //ISO code (R)
                        break;  
                        
                    case '0': 
                            property = null;//Authority record control number or standard number (R)
                            break;
                    case '2': 
                            property = null;//- Source of local code (R)
                            break;
                            
                    case '6':
                            property = null;//- Linkage (NR)
                            break;
                            
                            
                    case '8':
                            property = null;//- Field link and sequence number (R)
                        break;
                        
                    default:
                        property = null;
                        break; 
                }
            }
                break;     
        }
        
        return property;
    }
    
}
