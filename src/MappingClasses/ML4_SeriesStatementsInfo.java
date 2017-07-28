/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MappingClasses;

import Vocabularies.Bibo;
import Vocabularies.RDA1;
import Vocabularies.marcont;
import com.googlecode.fascinator.vocabulary.BIBO;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;
import com.hp.hpl.jena.vocabulary.VCARD;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;

/**
 *
 * @author home
 */
public class ML4_SeriesStatementsInfo extends ML00_DataInfo {
    
      
    public Property getPropertyForDataField(DataField df)
    {
        int tag = Integer.parseInt(df.getTag());
        Property property;
        switch(tag)
        {
            
            case 400: //[Series statement/Added entry - personal name/title]
                property = DC_11.creator;
                break;
                
            case 410: //[Series statement/Added entry - corporate name/title]
                property = VCARD.Orgunit;
                break;
                
            case 411: //[Series statement/Added entry - conference or meeting/title]
                property = VCARD.GROUP;
                break;
                
            case 440: //[Series statement/Added entry - title] 490 Series statement
            
            case 450: //See From Tracing – Topical Term (R)
                property = DC_11.creator;
                break;
                
            case 451: //See From Tracing – Geographic Name (R)
                property = DC_11.source;
                
                break;
                
            case 480: //See From Tracing –General Subdivision(R)
                property = null;
                break;
                
            case 482: //Heading – Chronological Subdivision (NR)
                property = DC_11.title;
                break;
                
            default:
                 property = VCARD.GROUP;
                break;
        }
        
        return property;
    }
    
     public Property getPropertyForSubFieldUsingDataField(Subfield sf, DataField df)
    {
        int dTag = Integer.parseInt(df.getTag());
        char sTag = sf.getCode();
        
        Property property = null;
        switch(dTag)
        {
            case 490: //series statement
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("seriesStatement");//Series statement
                        break;

                    case 'l':
                        property = Bibo.propertyForName("lccn"); //Library of congress call number
                        break;

                    case 'v':
                        property = marcont.hasVolume; //Volume/seq designation
                        break;    

                    case 'x':
                        property = marcont.hasISSN;//International std serial no.
                        break;

                   
                    case '3':
                        property = RDA1.propertyForName("baseMaterial");//materials specified
                        break;      
                        
                    case '6':
                        property = null;//linkage
                        break;      
                        
                    case '8':
                        property = null;//field link and seq no.
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
