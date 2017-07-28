/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MappingClasses;

import Vocabularies.RDA1;
import Vocabularies.marcont;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;
import com.hp.hpl.jena.vocabulary.VCARD;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;

/**
 *
 * @author home
 */
public class ML7_AddedEntryInfo extends ML00_DataInfo {
    
    
      
    public Property getPropertyForDataField(DataField df)
    {
        int tag = Integer.parseInt(df.getTag());
        Property property;
        
        switch(tag)
        {
            case 700: //Added entry - personal name
            case 710: // Added entry - corporate name
            case 711: // Added entry - meeting name
            case 720: // Added entry - uncontrolled name 730 Added entry - uniform title
            case 740: // Added entry - uncontrolled related/analytical title
            case 752: // Added entry - hierarchical place name
            case 753: // System details access to computer files
            case 754: // Added entry - taxonomic identification
            case 755: // Added entry - physical characteristics
                
//                76X-78X Linking entry fields
                
            case 760: // Main series entry
           case  762: // Subseries entry
            case 765: // Original language entry
            case 767: // T ranslation entry
            case 770: // Supplement/special issue entry
            case 772: // Parent record entry
            case 773: // Host item entry
            case 774: // Constituent unit entry
            case 776: // Additional physical form entry
            case 777: // Issued with entry
            case 780: // Preceding entry
             case  785: // Succeeding entry
            case  786: // Data source entry
             case  787: // Nonspecific relationship entry
                
            default:
                 property = DCTerms.accessRights;
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
            
            case 710:
            {
                switch(sTag)
                {
                    case 'a':
                        property = DCTerms.coverage;//- Corporate name or jurisdiction name as entry element (NR)
                        break;
                        
                    case 'b':
                        property = null;//- Subordinate unit (R)
                        break;
                        
                    case 'c':
                        property = null;//- Location of meeting (NR)
                        break;
                        
                    case 'd':
                        property = null;//- Date of meeting or treaty signing (R)
                        break;
                        
                    case 'e':
                        property = null;//- Relator term (R)
                        break;
                        
                    case 'f':
                        property = RDA1.propertyForName("dateOfWork");//- Date of a work (NR)
                        break;
                        
                    case 'g': 
                        property = null;//- Miscellaneous information (NR)
                        break;
                        
                    case 'h':
                        property = DCTerms.medium;//- Medium (NR)
                        break;
                        
                    case 'i':
                        property = null;//- Relationship information (R)
                        break;
                        
                    case 'k':
                        property = null;//- Form subheading (R)
                        break;
                        
                    case 'l':
                        property = RDA1.propertyForName("languageOfExpression");//- Language of a work (NR)
                        break;
                        
                    case 'm':
                        property = RDA1.propertyForName("mediumOfPerformanceOfMusicalContent");//- Medium of performance for music (R)
                        break;
                        
                    case 'n':
                        property = null;//- Number of part/section/meeting (R)
                        break;
                        
                    case 'o':
                        property = null;//- Arranged statement for music (NR)
                        break;
                        
                    case 'p':
                        property = null;//- Name of part/section of a work (R)
                        break;
                        
                    case 'r':
                        property = null;//- Key for music (NR)
                        break;
                        
                    case 's':
                        property = null;//- Version (NR)
                        break;
                        
                            
                    case 't':
                        property = RDA1.propertyForName("titleOfTheWork");//- Title of a work (NR)
                        break;
                        
                    case 'u':
                        property =  marcont.hasAffiliation;//- Affiliation (NR)
                        break;
                        
                    case 'x': 
                        property = RDA1.propertyForName("issnOfSeries");//- International Standard Serial Number (NR)
                        break;
                        
                    case '0':
                        property = null;//- Authority record control number (R)
                        break;
                        
                    case '3':
                        property = null;//- Materials specified (NR)
                        break;
                        
                    case '4':
                        property = null;//- Relator code (R)
                        break;
                        
                    case '5':
                        property = null;//- Institution to which field applies (NR)
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
            
            case 533: //series statement
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;//- Type of reproduction (NR) 
                        break;
                        
                    case 'b':
                        property = null;//- Place of reproduction (R) 
                        break;
                        
                    case 'c':
                        property = null;//- Agency responsible for reproduction (R) 
                        break;
                        
                    case 'd':
                        property = null;//- Date of reproduction (NR) 
                        break;
                        
                    case 'e':
                        property = null;//- Physical description of reproduction (NR) 
                        break;
                        
                    case 'f':
                        property = null;//- Series statement of reproduction (R) 
                        break;
                        
                    case 'm':
                        property = null;//- Dates and/or sequential designation of issues reproduced (R) 
                        break;
                        
                    case 'n':
                        property = null;//- Note about reproduction (R) 
                        break;
                        
                    case '3':
                        property = null;//- Materials specified (NR) 
                        
                    case '5':
                        property = null;//- Institution to which field applies (NR) 
                        break;
                        
                    case '7':
                        property = null;//- Fixed-length data elements of reproduction (NR) 
                        break;
                        
                        /*/0 - Type of date/Publication status 
                            /1-4 - Date 1 
                            /5-8 - Date 2 
                            /9-11 - Place of publication, production, or execution 
                            /12 - Frequency 
                            /13 - Regularity 
                            /14 - Form of item 
                            * */
                        
                           
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                
                
 
        }
        
        return property;
    }

}
