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

import com.googlecode.fascinator.vocabulary.BIBO;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import org.marc4j.marc.Subfield;



/**
 *
 * @author home
 */
public class ML5_NotesInfo extends ML00_DataInfo {
    
      
    public Property getPropertyForDataField(DataField df) 
    {
        int tag = Integer.parseInt(df.getTag());
        Property property;
        
        
        switch(tag)
        {
            
            
            case 500: //General note
            case 501: //With note
                 property = VCARD.NOTE;
                break;
                
            case 502: //Dissertation note
                 property = DC_11.description;
                break;
                
            case 504: ///Bibliography , etc. note
                 property = DCTerms.bibliographicCitation;
                break;
                
            case 505: //Formatted contents note
            case 506: //Restrictions on access note
            case 507: //Scale note for graphic material
            case 508: //Creation/production credits note
            case 510: //Citation/references note
            case 511: //Participant or performer note
            case 513: // Type of report and period covered note
            case 514: // Data quality note
            case 515: // Numbering peculiarities note
            case 516: // Type of computer file or data note
            case 518: // Date/time and place of an event note
            case 520: // Summary , etc.
            case 521: // T arget audience note
            case 522: // Geographic coverage note
            case 524: // Preferred citation of described materials note
            case 525: // Supplement note
            case 526: // Study program information note
            case 530: // Additional physical form available note
            case 533: // Reproduction note
            case 534: // Original version note
            case 535: // Location of originals/duplicates note
            case 536: // Funding information note
            case 538: // System details note
            case 540: // T erms governing use and reproduction note
            case 541: // Immediate source of acquisition note
            case 542: // Information relating to copyright status
            case 544: // Location of other archival materials note
            case 545: // Biographical or historical note
            case 546: // Language note
            case 547: // Former title complexity note
            case 550: // Issuing body note
                
            case 552: // Entity and attribute information note
            case  555: // Cumulative index/finding aids note
            case  556: // Information about documentation note
            case  561: // Ownership and custodial history (Provenance)
            case  562: // Copy and version identification note
            case  563: // Binding information
            case  565: // Case file characteristics note 567 Methodology note
            case  580: // Linking entry complexity note
            case  581: // Publications about described materials note
            case  583: // Action note
            case  584: // Accumulation and frequency of use note
            case  585: // Exhibitions note
            case  586: // A wards note
            case  588: // Source of information 59X Local notes
                
            default:
                 property = DCTerms.bibliographicCitation;
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
            
            case 500: //General note
            case 501: //With note
            {
                switch(sTag)
                {
                    case 'a':
                        property = marcont.hasNote;//- General note (NR) 
                        break;
                        
                    case '3':
                        property = RDA1.propertyForName("baseMaterial");//- Materials specified (NR) 
                        break;
                        
                    case '5':
                        property = RDA1.propertyForName("grantingInstitutionOrFaculty");//- Institution to which field applies (NR) 
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
                
           case 502: //Dissertation Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- dissertation note (NR) 
                        
                    case 'b':
                        property = null;
                        break;//- Degree type (NR)
                        
                    case 'c':
                        property = null;
                        break;//- Name of granting institution (NR)
                        
                    case 'd':
                        property = null;
                        break;//- Year degree granted (NR)
                        
                    case 'g':
                        property = null;
                        break;//- Miscellaneous information (R)
                        
                    case 'o':
                        property = null;
                        break;//- Dissertation identifier (R)
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
               
               
               case 504: //Bibliography, Etc. Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Bibliography, etc. note (NR) 
                        
                    case 'b':
                        property = null;
                        break;//- Number of references (NR) 

                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                   
                   
                  
            case 505: //Formatted content
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Formatted contents note (NR) 
                        
                    case 'g':
                        property = null;
                        break;//- Formatted contents note (NR) - Miscellaneous information (R) 
                        
                    case 'r':
                        property = null;
                        break;//- Formatted contents note (NR) - Statement of responsibility (R) 
                        
                    case 't':
                        property = null;
                        break;//- Formatted contents note (NR) - Title (R) 
                        
                    case 'u':
                        property = null;
                        break;//- Formatted contents note (NR) - Uniform Resource Identifier (R) 
                        
                    case '3':
                        property = RDA1.propertyForName("baseMaterial");//- Materials specified (NR) 
                        break;
                        
                    case '5':
                        property = RDA1.propertyForName("grantingInstitutionOrFaculty");//- Institution to which field applies (NR) 
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
                       
                       
            case 506: // - Restrictions on Access Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Terms governing access (NR) 
                        
                    case 'b':
                        property = null;
                        break;//- Jurisdiction (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Physical access provisions (R) 
                        
                    case 'd':
                        property = null;
                        break;//- Authorized users (R) 
                        
                    case 'e':
                        property = null;
                        break;//- Authorization (R) 
                        
                    case 'f':
                        property = null;
                        break;//- Standardized terminology for access restriction (R) 
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R) 
                        
                    case '2':
                        property = null;
                        break;//- Source of term (NR) 

                    case '3':
                        property = RDA1.propertyForName("baseMaterial");//- Materials specified (NR) 
                        break;
                        
                    case '5':
                        property = RDA1.propertyForName("grantingInstitutionOrFaculty");//- Institution to which field applies (NR) 
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
                           
                           
                           
            case 507: //Scale Note for Graphic Material (NR)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;//- Representative fraction of scale note (NR) ) 
                        break;

                        
                    case 'b':
                        property = null;//- Remainder of scale note (NR) 
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
                                
            case 508: //Creation/Production Credits Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- $a - Creation/production credits note (NR) 

                        
                    case 'b':
                        property = null;
                        break;//- Remainder of scale note (NR) 
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
            case 510: //Citation/References Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Name of source (NR)
 
                    case 'b':
                        property = null;
                        break;//- Coverage of source (NR)
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R)
                        
                    case 'x':
                        property = null;
                        break;//- International Standard Serial Number (NR)
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
                
               
            case 511: //Participant or Performer Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Participant or performer note (NR) 

                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                    
                   
            case 513: //Type of Report and Period Covered Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Type of report (NR) 

                        
                    case 'b':
                        property = null;
                        break;//- Period covered (NR) 
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                        
                        
            case 514: //Data Quality Note (NR)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Attribute accuracy report (NR) 

                    case 'c':
                        property = null;
                        break;//- Attribute accuracy explanation (R) 
                        
                    case 'd':
                        property = null;
                        break;//- Logical consistency report (NR) 
                        
                    case 'e':
                        property = null;
                        break;//- Completeness report (NR) 
                        
                    case 'f':
                        property = null;
                        break;//- Horizontal position accuracy report (NR) 
                        
                    case 'g':
                        property = null;
                        break;//- Horizontal position accuracy value (R) 
                        
                    case 'h':
                        property = null;
                        break;//- Horizontal position accuracy explanation (R) 
                        
                    case 'i':
                        property = null;
                        break;//- Vertical positional accuracy report (NR) 
                        
                    case 'j':
                        property = null;
                        break;//- Vertical positional accuracy value (R) 
                        
                    case 'k':
                        property = null;
                        break;//- Vertical positional accuracy explanation (R) 
                        
                    case 'm':
                        property = null;
                        break;//- Cloud cover (NR) 
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R) 
                        
                    case 'z':
                        property = null;
                        break;//- Display note (R) 
    
                    case 'b':
                        property = null;
                        break;//- Attribute accuracy value (R) 
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                            
                     
            case 515: // Numbering peculiarities note (NR)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Numbering peculiarities note (NR)

                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
                
            case 516: // Type of computer file or data note (NR
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Type of computer file or data note (NR

                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
            case 518: // Date/Time and Place of an Event Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Date/time and place of an event note (NR)

                    case 'd':
                        property = null;
                        break;//- Date of event (R)
                        
                    case 'o':
                        property = null;
                        break;//- Other event information (R)
                        
                    case 'p':
                        property = null;
                        break;//- Place of event (R)
                        
                    case '0':
                        property = null;
                        break;//- Record control number (R)
                        
                    case '2':
                        property = null;
                        break;//- Source of term (R)
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)

                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
                
            case 520: // Summary, Etc. (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Summary, etc. (NR) 

                    case 'b':
                        property = null;
                        break;//-- Expansion of summary note (NR) 
                        
                    case 'c':
                        property = null;
                        break;//-- Assigning source (NR)
                        
                    case 'u':
                        property = null;
                        break;//-- Uniform Resource Identifier (R) 
                        
                    case '2':
                        property = null;
                        break;//-- Source (NR)
                        
                    case '3':
                        property = null;
                        break;//-- Materials specified (NR) 
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
                
            case 521: // Target Audience Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Target audience note (R) 

                    case 'b':
                        property = null;
                        break;//- Source (NR) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 

                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
            case 522: // Geographic Coverage Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Geographic Coverage Note (NR)

                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
            case 524: // Preferred Citation of Described Materials Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = DCTerms.description;
                        break;//-  Numbering peculiarities note (NR)

                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
            case 525: // Supplement Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Supplement Note (NR) 
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
            case 526: // Study Program Information Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Program name (NR) 

                    case 'b':
                        property = null;
                        break;//- Interest level (NR) 
                        
                    case 'c':
                        property = null;
                        break;//- Reading level (NR) 
                        
                    case 'd':
                        property = null;
                        break;//- Title point value (NR) 
                        
                    case 'i':
                        property = null;
                        break;//- Display text (NR) 
                        
                    case 'x':
                        property = null;
                        break;//- Nonpublic note (R) 
                        
                    case 'z':
                        property = null;
                        break;//- Public note (R) 
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (NR) 

                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
            case 530: // Additional Physical Form Available Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Additional physical form available note (NR) 

                    case 'b':
                        property = null;
                        break;//- Availability source (NR) 
                        
                    case 'c':
                        property = null;
                        break;//- Availability conditions (NR) 
                        
                    case 'd':
                        property = null;
                        break;//- Order number (NR) 
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
            case 533: // Reproduction Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("productionMethod");
                        break;//-  Type of reproduction (NR) 

                        
                    case 'b':
                        property = RDA1.propertyForName("placeOfProduction");
                        break;//- Place of reproduction (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Agency responsible for reproduction (R) 
                        
                    case 'd':
                        property = null;
                        break;//- Date of reproduction (NR) 
                        
                    case 'e':
                        property = null;
                        break;//- Physical description of reproduction (NR) 
                        
                    case 'f':
                        property = null;
                        break;//- Series statement of reproduction (R) 
                        
                    case 'm':
                        property = null;
                        break;//- Dates and/or sequential designation of issues reproduced (R) 
                        
                    case 'n':
                            property = null;
                        break;//- Note about reproduction (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (NR) 
                        
                    case '7':
                        property = null;
                        break;//- Fixed-length data elements of reproduction (NR) 
                              //  /0 - Type of date/Publication status 
                              //  /1-4 - Date 1 
                              //  /5-8 - Date 2 
                              //  /9-11 - Place of publication, production, or execution 
                              //  /12 - Frequency 
                              //  /13 - Regularity 
                              //    /14 - Form of item 
                       
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
                
            case 534: // Original Version Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Main entry of original (NR)

                        
                    case 'b':
                        property = null;
                        break;//- Edition statement of original (NR)
                        
                    case 'c':
                        property = null;
                        break;//- Publication, distribution, etc. of original (NR)
                        
                    case 'e':
                        property = null;
                        break;//- Physical description, etc. of original (NR)
                        
                    case 'f':
                        property = null;
                        break;//- Series statement of original (R)
                        
                    case 'k':
                        property = null;
                        break;//- Key title of original (R)
                        
                    case 'l':
                        property = null;
                        break;//- Location of original (NR)
                        
                    case 'm':
                        property = null;
                        break;//- Material specific details (NR)
                        
                    case 'n':
                        property = null;
                        break;//- Note about original (R)
                        
                    case 'o':
                        property = null;
                        break;//- Other resource identifier (R)
                        
                    case 'p':
                        property = null;
                        break;//-- Introductory phrase (NR)
                        
                    case 't':
                        property = null;
                        break;//-- Title statement of original (NR)
                        
                    case 'x':
                        property = null;
                        break;//-- International Standard Serial Number (R)
                        
                    case 'z':
                        property = null;
                        break;//-- International Standard Book Number (R)
                        
                    case '3':
                        property = null;
                        break;//-- Materials specified (NR)
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
            case 535: // Location of Originals/Duplicates Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Custodian (NR) 

                        
                    case 'b':
                        property = null;
                        break;//- Postal address (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Country (R) 
                        
                    case 'd':
                        property = null;
                        break;//- Telecommunications address (R) 
                        
                    case 'g':
                        property = null;
                        break;//- Repository location code (NR) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
            case 536: // Funding Information Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Text of note (NR) 
                            
                    case 'b':
                           property = null;
                        break;// - Contract number (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Grant number (R) 
                        
                    case 'd':
                        property = null;
                        break;//- Undifferentiated number (R) 
                        
                    case 'e':
                        property = null;
                        break;//- Program element number (R) 
                        
                    case 'f':
                        property = null;
                        break;//- Project number (R) 
                        
                    case 'g':
                        property = null;
                        break;//- Task number (R) 
                        
                    case 'h':
                        property = null;
                        break;//- Work unit number (R) 
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
                }
            }
                break;
                
           case 538: // System Details Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  System details note (NR) 
                        
                    case 'i':
                        property = null;
                        break;//- Display text (NR) 
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
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
            
            case 540: //Terms Governing Use and Reproduction Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Terms governing use and reproduction (NR) 
                        
                    case 'b':
                        property = null;
                        break;//- Jurisdiction (NR) 
                        
                    case 'c':
                        property = null;
                        break;//- Authorization (NR) 
                        
                    case 'd':
                        property = null;
                        break;//- Authorized users (NR) 
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (NR) 
                        
                           
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                
            case 541: //Immediate Source of Acquisition Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Source of acquisition (NR) 
                        
                    
                    case 'b':
                        property = null;
                        break;//- Address (NR) 
                        
                    case 'c':
                        property = null;
                        break;//- Method of acquisition (NR) 
                        
                    case 'd':
                        property = null;
                        break;//- Date of acquisition (NR) 
                        
                    case 'e':
                        property = null;
                        break;//- Accession number (NR) 
                        
                    case 'f':
                        property = null;
                        break;//- Owner (NR) 
                        
                    case 'h':
                        property = null;
                        break;//- Purchase price (NR) 
                        
                    case 'n':
                        property = null;
                        break;//- Extent (R) 
                        
                    case 'o':
                        property = null;
                        break;//- Type of unit (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (NR) 
                        
                       
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                
                case 542: //Information Relating to Copyright Status (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Personal creator (NR)
                      
                    case 'b':
                        property = null;
                        break;//- Personal creator death date (NR)
                        
                    case 'c':
                        property = null;
                        break;//- Corporate creator (NR)
                        
                    case 'd':
                        property = null;
                        break;//- Copyright holder (R)
                        
                    case 'e':
                        property = null;
                        break;//- Copyright holder contact information (R)
                        
                    case 'f':
                        property = null;
                        break;//- Copyright statement (R)
                        
                    case 'g':
                        property = null;
                        break;//- Copyright date (NR)
                        
                    case 'h':
                        property = null;
                        break;//- Copyright renewal date (R)
                        
                    case 'i':
                        property = null;
                        break;//- Publication date (NR)
                        
                    case 'j':
                        property = null;
                        break;//- Creation date (NR)
                        
                    case 'k':
                        property = null;
                        break;//- Publisher (R)	$l - Copyright status (NR)
                        
                    case 'm':
                        property = null;
                        break;//- Publication status (NR)
                        
                    case 'n':
                        property = null;
                        break;//- Note (R)
                        
                    case 'o':
                        property = null;
                        break;//- Research date (NR)
                        
                    case 'p':
                        property = null;
                        break;//- Country of publication or creation (R)
                        
                    case 'q':
                        property = null;
                        break;//- Supplying agency (NR)
                        
                    case 'r':
                        property = null;
                        break;//- Jurisdiction of copyright assessment (NR)
                        
                    case 's':
                        property = null;
                        break;//- Source of information (NR)
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R)
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)
                        
                           
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                    
                  
            case 544: //Location of Other Archival Materials Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Custodian (R) 
                        
                      
                    case 'b':
                        property = null;
                        break;//- Address (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Country (R) 
                        
                    case 'd':
                        property = null;
                        break;//- Title (R) 
                        
                    case 'e':
                        property = null;
                        break;//- Provenance (R) 
                        
                    case 'n':
                        property = null;
                        break;//- Note (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                        
             case 545: //Biographical or Historical Data (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Biographical or historical data (NR) 
                        
                    case 'b':
                        property = null;
                        break;//- Expansion (NR) 
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R) 
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                 
                 
            case 546: //Language Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Language note (NR)
                        
                    case 'b':
                        property = null;
                        break;//- Information code or alphabet (R)
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)   
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                
                
            case 547: //Former Title Complexity Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Former title complexity note (NR) 
                        
                           
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                
                
            case 550: // Issuing Body Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Issuing body note (NR)
                        
                           
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                
                
            case 552: //Entity and Attribute Information Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Entity type label (NR) 
                        
                    case 'b':
                        property = null;
                        break;//- Entity type definition and source (NR) 
                        
                    case 'c':
                        property = null;
                        break;//- Attribute label (NR) 
                        
                    case 'd':
                        property = null;
                        break;//- Attribute definition and source (NR) 
                        
                    case 'e':
                        property = null;
                        break;//- Enumerated domain value (R) 
                        
                    case 'f':
                        property = null;
                        break;//- Enumerated domain value definition and source (R) 
                        
                    case 'g':
                        property = null;
                        break;//- Range domain minimum and maximum (NR) 
                        
                    case 'h':
                        property = null;
                        break;//- Codeset name and source (NR) 
                        
                    case 'i':
                        property = null;
                        break;//- Unrepresentable domain (NR) 
                        
                    case 'j':
                        property = null;
                        break;//- Attribute units of measurement and resolution (NR) 
                        
                    case 'k':
                        property = null;
                        break;//- Beginning and ending date of attribute values (NR) 
                        
                    case 'l':
                        property = null;
                        break;//- Attribute value accuracy (NR) 
                        
                    case 'm':
                        property = null;
                        break;//- Attribute value accuracy explanation (NR) 
                        
                    case 'n':
                        property = null;
                        break;//- Attribute measurement frequency (NR) 
                        
                    case 'o':
                        property = null;
                        break;//- Entity and attribute overview (R) 
                        
                    case 'p':
                        property = null;
                        break;//- Entity and attribute detail citation (R) 
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R) 
                        
                    case 'z':
                        property = null;
                        break;//- Display note (R) 
 
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                
                
            case 555: //Cumulative Index/Finding Aids Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = DCTerms.description;
                        break;//- Cumulative index/finding aids note (NR) 
                        
                    case 'b':
                        property = null;
                        break;//- Availability source (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Degree of control (NR) 
                        
                    case 'd':
                        property = null;
                        break;//- Bibliographic reference (NR) 
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)    
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                
            case 556: // Information About Documentation Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Information about documentation note (NR) 
                        
                   
                    case 'z':
                        property = null;
                        break;//-- International Standard Book Number (R)        
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                
            case 561: //Ownership and Custodial History (R
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- History (NR)
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R)
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (NR)
                           
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                
             case 562: //Copy and Version Identification Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- dentifying markings (R) 
                        
                    case 'b':
                        property = null;
                        break;//- Copy identification (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Version identification (R) 
                        
                    case 'd':
                        property = null;
                        break;//- Presentation format (R) 
                        
                    case 'e':
                        property = null;
                        break;//- Number of copies (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (NR) 
                           
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                 
             case 563: //Binding Information (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Binding note (NR) 
                        
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (NR)    
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                 
             case 565: //Case File Characteristics Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Number of cases/variables (NR) 
                      
                        
                    case 'b':
                        property = null;
                        break;//- Name of variable (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Unit of analysis (R) 
                        
                    case 'd':
                        property = null;
                        break;//- Universe of data (R) 
                        
                    case 'e':
                        property = null;
                        break;//- Filing scheme or code (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 

                           
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                 
             case 567: //Methodology Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Methodology note (NR)
                        
                           
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;     
                 
             case 580: //Linking Entry Complexity Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Linking entry complexity note (NR)
                        
                           
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                 
             case 581: // Publications About Described Materials Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Publications about described materials note (NR) 
                        
                        
                    case 'z':
                        property = null;
                        break;//- International Standard Book Number (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)    
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                 
                 
             case 583: //Action Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Action (NR) 
                        

                    case 'b':
                        property = null;
                        break;//- Action identification (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Time/date of action (R) 
                        
                    case 'd':
                        property = null;
                        break;//- Action interval (R) 
                        
                    case 'e':
                        property = null;
                        break;//- Contingency for action (R) 
                        
                    case 'f':
                        property = null;
                        break;//- Authorization (R) 
                        
                    case 'h':
                        property = null;
                        break;//- Jurisdiction (R) 
                        
                    case 'i':
                        property = null;
                        break;//- Method of action (R) 
                        
                    case 'j':
                        property = null;
                        break;//- Site of action (R) 
                        
                    case 'k':
                        property = null;
                        break;//- Action agent (R) 
                        
                    case 'l':
                        property = null;
                        break;//- Status (R) 
                        
                    case 'n':
                        property = null;
                        break;//- Extent (R) 
                        
                    case 'o':
                        property = null;
                        break;//- Type of unit (R) 
                        
                    case 'u':
                        property = null;
                        break;//- Uniform Resource Identifier (R) 
                        
                    case 'x':
                        property = null;
                        break;//- Nonpublic note (R) 
                        
                    case 'z':
                        property = null;
                        break;//- Public note (R) 
                        
                    case '2':
                        property = null;
                        break;//- Source of term (NR) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (NR) 
                        

                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                 
              case 584: //Accumulation and Frequency of Use Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Accumulation (R) ) 
                        
                    case 'b':
                        property = null;
                        break;//- Frequency of use (R) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (NR)      
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                  
              case 585: //Exhibitions Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Exhibitions note (NR) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (NR)                            
                        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                  
                  
              case 586: //Awards Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Awards Note (NR) 
                    
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                           
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break; 
                  
                  
              case 588: //Source of Description Note (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Source of description note (NR)
                        
                     
                    case '5':
                        property = null;
                        break;//- Institution to which field applies (NR)     
                        
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
