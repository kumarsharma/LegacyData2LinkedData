/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MappingClasses;

import Vocabularies.RDA1;
import Vocabularies.marcont;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;

/**
 *  800-840 Series added entry fields
 *  841-88X Holdings, alternate graphics, etc. - General information
 * @author home
 */

public class ML8_HoldingsInfo extends ML00_DataInfo {
    
    
      
    public Property getPropertyForDataField(DataField df)
    {
        int tag = Integer.parseInt(df.getTag());
        Property property;
        switch(tag)
        {
            case 800: // Series added entry - personal name
            case 810: // Series added entry - corporate name
            case 811: // Series added entry - meeting name
            case 830: // Series added entry - uniform title
            case 841: //-88X Holdings, alternate graphics, etc. - General information
//            case 841: // Holdings coded data values
            case 842: // T extual physical form designator
            case 843: // Reproduction note
            case 844: // Name of unit
            case 845: // T erms governing use and reproduction note
            case  850: // Holding institution
            case  852: // Location
            case  853: // Captions and pattern -- basic bibli
               
            case  854: // Captions and pattern -- supplementary material
            case  855: // Captions and pattern -- indexes
            case  856: // Electronic location and access
            case  863: // Enumeration and chronology -- basic bibliographic unit
            case   864: // Enumeration and chronology -- supplementary material
            case  865: // Enumeration and chronology -- indexes
            case  866: // T extual holdings -- basic bibliographic unit
            case  868: // T extual holdings -- indexes
            case  876: // Item information -- basic bibliographic unit
            case  877: // Item information -- supplementary material
            case  878: // Item information -- indexes
            case   880: // Alternate graphic representation
            case   886: // Foreign MARC information field
            case   887: // Non-MARC information field
                
            default:
                 property = DC_11.relation;
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
            
            case 830:
            {
                switch(sTag)
                {
                    case 'a':
                        property = marcont.hasTitle;//Uniform title (NR)
                        break;
                        
                    case 'd':
                        property = null;//- Date of treaty signing (R)
                        break;
                        
                    case 'f':
                        property = marcont.hasDate;//- Date of a work (NR)
                        
                        break;
                        
                    case 'g':
                        property = null;//- Miscellaneous information (NR)
                        break;
                        
                    case 'h':
                        property = DCTerms.medium;//- Medium (NR)
                        
                        break;
                        
                    case 'k':
                        property = null;//- Form subheading (R)
                        break;
                        
                    case 'l':
                        property = null;//- Language of a work (NR)
                        break;
                        
                    case 'm':
                        property = RDA1.propertyForName("mediumOfPermonanceOfMusicalContentExpression");//- Medium of performance for music (R)
                        break;
                        
                    case 'n':
                        property = null;//- Number of part/section of a work (R)
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
                        property = null;//- Title of a work (NR) 
                        break;
                        
                    case 'v':
                        property = null;//- Volume/sequential designation (NR)
                        break;
                        
                    case 'w':
                        property = null;//- Bibliographic record control number (R)
                        break;
                        
                    case 'x':
                        property = null;//- International Standard Serial Number (NR)
                        break;
                        
                    case '0':
                        property = null;//- Authority record control number (R)
                        break;
                        
                    case '3':
                        property = null;//- Materials specified (NR)
                        break;
                        
                    case '5':
                        property = null;//- Institution to which field applies (R)
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
            
            case 856: //series statement
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;// - Host name (R) 
                        break;
                        
                    case 'b':
                        property = null;//- Access number (R) 
                        break;
                        
                    case 'c':
                        property = null;//- Compression information (R) 
                        break;
                        
                    case 'd':
                        property = null;//- Path (R) 
                        break;
                        
                    case 'f':
                        property = null;//- Electronic name (R) 
                        break;
                        
                    case 'h':
                        property = null;//- Processor of request (NR) 
                        break;
                        
                    case 'i':
                        property = null;//- Instruction (R) 
                        break;
                        
                    case 'j':
                        property = null;//- Bits per second (NR) 
                        break;
                        
                    case 'k':
                        property = null;//- Password (NR) 
                        break;
                        
                    case 'l':
                        property = null;//- Logon (NR) 
                        break;
                        
                    case 'm':
                        property = null;//- Contact for access assistance (R) 
                        break;
                        
                    case 'n':
                        property = null;//- Name of location of host (NR) 
                        break;
                        
                    case 'o':
                        property = null;//- Operating system (NR) 
                        break;
                        
                    case 'p':
                        property = null;//- Port (NR) 
                        break;
                        
                    case 'q':
                        property = null;//- Electronic format type (NR) 
                        break;
                        
                    case 'r':
                        property = null;//- Settings (NR) 
                        break;
                        
                    case 's':
                        property = null;//- File size (R) 
                        break;
                        
                    case 't':
                        property = null;//- Terminal emulation (R) 
                        break;
                        
                    case 'u':
                        property = marcont.hasURL;//- Uniform Resource Identifier (R) 
                        break;
                        
                    case 'v':
                        property = null;//- Hours access method available (R) 
                        break;
                        
                    case 'w':
                        property = null;//- Record control number (R) 
                        break;
                        
                    case 'x':
                        property = null;//- Nonpublic note (R) 
                        break;
                        
                    case 'y':
                        property = null;//- Link text (R) 
                        break;
                        
                    case 'z':
                        property = null;//- Public note (R) 
                        break;
                        
                    case '2':
                        property = null;//- Access method (NR) 
                        break;
                        
                    case '3':
                        property = null;//- Materials specified (NR) 
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
                
                
              
            case 800: // Series Added Entry - Personal Name (R)
            { 
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//Personal name (NR)
                        
                        
                    case 'b':
                        property = null;
                        break;//- Numeration (NR)
                        
                    case 'c':
                        property = null;
                        break;//- Titles and other words associated with a name (R)
                        
                    case 'd':
                        property = null;
                        break;//- Dates associated with a name (NR)
                        
                    case 'e':
                        property = null;
                        break;//- Relator term (R)
                        
                    case 'f':
                        property = RDA1.propertyForName("dateOfWork");
                        break;//- Date of a work (NR)
                        
                    case 'g':
                        property = null;
                        break;//- Miscellaneous information (NR)
                        
                    case 'h':
                        property = null;
                        break;//- Medium (NR)
                        
                    case 'j':
                        property = null;
                        break;//- Attribution qualifier (R)
                        
                    case 'k':
                        property = null;
                        break;//- Form subheading (R)
                        
                    case 'l':
                        property = null;
                        break;//- Language of a work (NR)
                        
                    case 'm':
                        property = null;
                        break;//- Medium of performance for music (R)
                        
                    case 'n':
                        property = null;
                        break;//- Number of part/section of a work (R)
                        
                    case 'o':
                        property = null;
                        break;//- Arranged statement for music (NR)
                        
                    case 'p':
                        property = null;
                        break;//- Name of part/section of a work (R)
                        
                    case 'q':
                        property = null;
                        break;//- Fuller form of name (NR)
                        
                    case 'r':
                        property = null;
                        break;//- Key for music (NR)
                        
                    case 's':
                        property = null;
                        break;//- Version (NR)
                        
                    case 't':
                        property = null;
                        break;//- Title of a work (NR)
                        
                    case 'u':
                        property = null;
                        break;//- Affiliation (NR)
                        
                    case 'v':
                        property = null;
                        break;//- Volume/sequential designation (NR)
                        
                    case 'w':
                        property = null;
                        break;//- Bibliographic record control number (R)
                        
                    case 'x':
                        property = null;
                        break;//- International Standard Serial Number (NR)
                        
                    case '0':
                        property = null;
                        break;//- Authority record control number (R)
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)
                        
                    case '4':
                        property = null;
                        break;//- Relator code (R)
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (R)
                                                  
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                
                
                case 810: // Series Added Entry - Personal Name (R)
            { 
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//Corporate name or jurisdiction name as entry element (NR)
                        
                        
                    case 'b':
                        property = null;
                        break;//- Subordinate unit (R)
                        
                    case 'c':
                        property = null;
                        break;//- Location of meeting (NR)
                        
                    case 'd':
                        property = null;
                        break;//- Date of meeting or treaty signing (R)
                        
                    case 'e':
                        property = null;
                        break;//- Relator term (R)

                    case 'f':
                        property = RDA1.propertyForName("dateOfWork");
                        break;//- Date of a work (NR)
                        
                    case 'g':
                        property = null;
                        break;//- Miscellaneous information (NR)
                        
                    case 'h':
                        property = null;
                        break;//- Medium (NR)
                        
                    case 'j':
                        property = null;
                        break;//- Attribution qualifier (R)
                        
                    case 'k':
                        property = null;
                        break;//- Form subheading (R)
                        
                    case 'l':
                        property = null;
                        break;//- Language of a work (NR)
                        
                    case 'm':
                        property = null;
                        break;//- Medium of performance for music (R)
                        
                    case 'n':
                        property = null;
                        break;//- Number of part/section of a work (R)
                        
                    case 'o':
                        property = null;
                        break;//- Arranged statement for music (NR)
                        
                    case 'p':
                        property = null;
                        break;//- Name of part/section of a work (R)
                        
                    case 'q':
                        property = null;
                        break;//- Fuller form of name (NR)
                        
                    case 'r':
                        property = null;
                        break;//- Key for music (NR)
                        
                    case 's':
                        property = null;
                        break;//- Version (NR)
                        
                    case 't':
                        property = null;
                        break;//- Title of a work (NR)
                        
                    case 'u':
                        property = null;
                        break;//- Affiliation (NR)
                        
                    case 'v':
                        property = null;
                        break;//- Volume/sequential designation (NR)
                        
                    case 'w':
                        property = null;
                        break;//- Bibliographic record control number (R)
                        
                    case 'x':
                        property = null;
                        break;//- International Standard Serial Number (NR)
                        
                    case '0':
                        property = null;
                        break;//- Authority record control number (R)
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)
                        
                    case '4':
                        property = null;
                        break;//- Relator code (R)
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (R)
                                                  
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                
                
             case 811: // Series Added Entry-Meeting Name (R)
            { 
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//Meeting name or jurisdiction name as entry element (NR)

                        
                    case 'c':
                        property = null;
                        break;//- Location of meeting (NR)
                        
                    case 'd':
                        property = null;
                        break;//- Date of meeting (R)
                        
                    case 'e':
                        property = null;
                        break;//- Subordinate unit (R)

                    case 'f':
                        property = RDA1.propertyForName("dateOfWork");
                        break;//- Date of a work (NR)
                        
   
                    case 'g':
                        property = null;
                        break;//- Miscellaneous information (NR)
                        
                    case 'h':
                        property = null;
                        break;//- Medium (NR)
                        
                    case 'j':
                        property = null;
                        break;//- Attribution qualifier (R)
                        
                    case 'k':
                        property = null;
                        break;//- Form subheading (R)
                        
                    case 'l':
                        property = null;
                        break;//- Language of a work (NR)
                        
                    case 'm':
                        property = null;
                        break;//- Medium of performance for music (R)
                        
                    case 'n':
                        property = null;
                        break;//- Number of part/section of a work (R)
                        
                    case 'o':
                        property = null;
                        break;//- Arranged statement for music (NR)
                        
                    case 'p':
                        property = null;
                        break;//- Name of part/section of a work (R)
                        
                    case 'q':
                        property = null;
                        break;//- Fuller form of name (NR)
                        
                    case 'r':
                        property = null;
                        break;//- Key for music (NR)
                        
                    case 's':
                        property = null;
                        break;//- Version (NR)
                        
                    case 't':
                        property = null;
                        break;//- Title of a work (NR)
                        
                    case 'u':
                        property = null;
                        break;//- Affiliation (NR)
                        
                    case 'v':
                        property = null;
                        break;//- Volume/sequential designation (NR)
                        
                    case 'w':
                        property = null;
                        break;//- Bibliographic record control number (R)
                        
                    case 'x':
                        property = null;
                        break;//- International Standard Serial Number (NR)
                        
                    case '0':
                        property = null;
                        break;//- Authority record control number (R)
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)
                        
                    case '4':
                        property = null;
                        break;//- Relator code (R)
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (R)
                                                  
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                 
                 
             case 850://850
             {        //Holding Institution (R)
                
                 switch(sTag)
                {
                    case 'a':
                            property = null; // Holding institution (R)
                             break;

                    default: 
                            property = null;
                    break;
                 }
                     
             }
                 break;
                 
                 
                    
             case 852://Location (R)
             {        
                 switch(sTag)
                {
                    case 'a':
                            property = null; 
                             break;
                        
                    case 'b':
                            property = null; 
                             break; // Sublocation or collection (R) $c Shelving location (R)
                        
                    case 'h':
                            property = null; 
                             break; //Classification part (NR)
                        
                    case 'i':
                            property = null; 
                             break; // Item part (R)
                    case 'j':
                            property = null; 
                             break; // Shelving control number (NR) $n Country code (NR)
                    case 'p':
                            property = null; 
                             break; // Piece Designation (NR)
                    case 't':
                            property = null; 
                             break; // Copy number (NR)

                    default: 
                            property = null;
                            break;
                 }
                     
             }
                 break;
                 
             case 857://850
             {        //Holding Institution (R)
                
                 switch(sTag)
                {
                    case 'a':
                            property = null; // Holding institution (R)
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
