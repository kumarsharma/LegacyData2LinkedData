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

import com.googlecode.fascinator.vocabulary.BIBO;
import com.googlecode.fascinator.vocabulary.SKOS;

import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.vocabulary.VCARD;
 
import Vocabularies.OL;
/**
 *
 * @author home
 */
public class ML3_PhysicalDescriptionInfo extends ML00_DataInfo {
    
    
      
    public Property getPropertyForDataField(DataField df)
    {
        int tag = Integer.parseInt(df.getTag());
        Property property;
        switch(tag)
        {
            case 300: //Physical description
                property = DC_11.description;
                break;
                
            case 306: //Playing time
            case 307: //Hours, etc.
            case 310: //Current publication frequency 321 Former publication frequency
            case 336: //Content type
                property = DC_11.type;
                break;
                
            case 337: //Media type
                property = DCTerms.mediator;
                break;
                
            case 338: //Carrier type
            case 340: //Physical medium
                property = DCTerms.medium;
                break;
                
            case 342: //Geospatial reference data
                property = DCTerms.spatial;
                break;
                
            case 343: //Planar coordinate data
            case 351: //Organization and arrangement of materials
            case 352: //Digital graphic representation
            case 355: //Security classification control
            case 357: //Originator dissemination control
                
            case 362: //Dates of publication and/or sequential designation
                property = DCTerms.dateAccepted;
                break;
                
            case 365: //T rade price
            case 366: //T rade availability information
            case 380: //Form of work
            case 381: //Other distinguishing characteristics of work
            case 382: //Medium of performance
            case 383: //Numeric designation of musical work
            case 384: //Key
                
            default:
                 property = DC_11.title;
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
            case 300:
            {
                switch(sTag)
                {
                    case 'a':
                        
                        property = DCTerms.extent;//Extent
                        break;

                    case 'b':
                        property = null; //Other physical details
                        break;

                    case 'c':
                        property = RDA1.propertyForName("dimensions"); //dimension
                        break;    

                    case 'e':
                        property = OL.propertyForName("accompanying_material");//accompanying material
                        break;

                    case 'f':
                        property = null;//type of unit
                        break;  
                        
                    case 'g':
                        property = null;//size of unit
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
                
            case 306:
              {
                switch(sTag)
                {
                    case 'a':
                        property = null;//playing time
                        break;

                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                }
                
              }
                
                break;
                
            case 307:
              {
                switch(sTag)
                {
                    case 'a':
                        property = null;//Hours
                        break;
                        
                    case 'b':
                        property = null;//Additional  information
                        break;    

                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                }
                
              }
                
                break; 
                
             case 310:
              {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("frequency");//Current publication frequency
                        break;
                        
                    case 'b':
                        property = null;//date of Current publication frequency
                        break;    

                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                }
                
              }
                
                break;    
                 
            case 321:
              {
                switch(sTag)
                {
                    case 'a':
                        property = null;//former publication frequency
                        break;
                        
                    case 'b':
                        property = null;//date of former publication frequency
                        break;    

                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                }
                
              }
                
                break; 
                
            case 336:
              {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("contentType");//content type term
                        break;
                        
                    case 'b':
                        property = RDA1.propertyForName("natureOfTheContent");//content type code
                        break;
                        
                    case '2':
                        property = marcont.hasSource;//source
                        break;
                        
                    case '3':
                        property = RDA1.propertyForName("appliedMaterial");//materials specified
                        break;    

                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                }
                
              }
                
                break;  
                
            case 337:
              {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("mediaType");//media type term
                        break;
                        
                    case 'b':
                        property = null;//media type code
                        break;
                        
                    case '2':
                        property = marcont.hasSource;//source
                        break;
                        
                    case '3':
                        property = RDA1.propertyForName("appliedMaterial");//materials specified
                        break;    

                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                }
                
              }
                
                break;
                
                
            case 338:
              {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("carrierType");//carrier type term
                        break;
                        
                    case 'b':
                        property = null;//carrier type code
                        break;
                        
                    case '2':
                        property = marcont.hasSource;//source
                        break;
                        
                    case '3':
                        property = RDA1.propertyForName("appliedMaterial");//materials specified
                        break;    

                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                }
                
              }
                
                break;    
                
                
            case 340:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("baseMaterial");//material base and configuration
                        break;
                            
                    case 'b':
                        property = RDA1.propertyForName("dimensions"); //dimension
                        break;

                    case 'c':
                        property = RDA1.propertyForName("appliedMaterial"); //materials applied to surface
                        break;    

                    case 'd':
                        property = RDA1.propertyForName("typeOfRecordingAnalogDigital");//Information recording technique
                        break;

                    case 'e':
                        property = null;//Support
                        break; 
                        
                    case 'f':
                        property = null; //Production rate/ratio
                        break;    
                            
                    case 'h':
                        property = null;//location within medium
                        break;
                        
                    case 'i':
                        property = null;//technical specification of medium
                        break;         

                    case 'j':
                        property = RDA1.propertyForName("generation");//Generation
                        break;     

                    case 'k':
                        property = RDA1.propertyForName("layout");//Layout
                        break; 

                    case 'm':
                        property = RDA1.propertyForName("bookFormat");//Book format
                        break;    

                    case 'n':
                        property = RDA1.propertyForName("fontSize");//Font size
                        break;    

                    case 'o':
                        property = RDA1.propertyForName("polarity");//Polarity
                        break;    

                    case '0':
                        property = marcont.hasNumber;//authority record control no. or std no.
                        break;   
                        
                     case '2':
                        property = marcont.hasSource;//source
                        break;
                        
                    case '3':
                        property = RDA1.propertyForName("appliedMaterial");//materials specified
                        break;       

                    case '6':
                        property = null;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
                    break;    
                        
                }
            }
                break;
            
            
             case 342: //Geospatial Reference Data
            {
                switch(sTag)
                {
                    case 'a':
                        property = FOAF.firstName;//Name
                        break;
                            
                    case 'b':
                        property = FOAF.firstName; //Coordinate units or distance units
                        break;

                    case 'c':
                        property = DC_11.title; //Latitude resolution
                        break;    

                    case 'd':
                        property = DC_11.date;//Longitude resolution
                        break;

                    case 'e':
                        property = FOAF.firstName;//Std parallel or oblique line attitude
                        break; 
                        
                    case 'f':
                        property = DC_11.subject; //Oblique line longitude
                        break;    
                        
                    case 'g':
                        property = DC_11.subject; //Longitude of central meridian or projection center
                        break;        
                            
                    case 'h':
                        property = DC_11.language;//Latitude or projection center or projection origin
                        break;
                        
                    case 'i':
                        property = FOAF.firstName;//False easting
                        break;         

                    case 'j':
                        property = FOAF.firstName;//False northing
                        break;     

                    case 'k':
                        property = DC_11.date;//Scale factor
                        break; 
                        
                    case 'l':
                        property = DC_11.subject; //Height of perspective point above surface
                        break;        

                    case 'm':
                        property = FOAF.firstName;//Azimuthal angle
                        break;    

                    case 'n':
                        property = DC_11.date;//Azimuth measure point longitude or st vertical 
                        break;    

                    case 'o':
                        property = DC_11.date;//Lanset number and path number
                        break;    
                        
                        
                    case 'p':
                        property = DC_11.date;//Zone identifier
                        break;    
                        
                    case 'q':
                        property = DC_11.date;//Ellipsoid name
                        break;    
                        
                    case 'r':
                        property = DC_11.subject; //Semi major-axis
                        break;        

                    case 's':
                        property = FOAF.firstName;//Denominator of flattering ration
                        break;    

                    case 't':
                        property = DC_11.date;//Vertical resolution
                        break;    

                    case 'u':
                        property = DC_11.date;//Vertical encoding method
                        break;    
                        
                        
                    case 'v':
                        property = DC_11.date;//Local planar, local, or other projection  or grid decription
                        break;    
                        
                    case 'w':
                        property = DC_11.date;//Local planar or local georeference info
                        break;        
                        
                     case '2':
                        property = FOAF.firstName;//Reference method used
                        break;

                    case '6':
                        property = DC_11.date;//linkage 
                        break;    

                    case '8':
                        property = FOAF.topic_interest;//field link and sequence no.
                    break;    
                        
                }
                
                property = null;
                
            }
                break;
                
                
             case 344://Sound Characteristics (R)
              {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("typeOfRecordingAnalogDigital");//- Type of recording (R)
                        break;
                        
                    case 'b':
                        property = RDA1.propertyForName("recordingMedium");//- Recording medium (R)
                        break;
                        
                    case 'c':
                        property = RDA1.propertyForName("playingSpeed");
                        break;//- Playing speed (R)
                        
                    case 'd':
                        property = RDA1.propertyForName("grooveCharacteristic");
                        break;//- Groove characteristic (R)
                        
                    case 'e':
                        property = RDA1.propertyForName("trackConfiguration");
                        break;//- Track configuration (R)
                        
                    case 'f':
                        property = RDA1.propertyForName("tapConfiguration");
                        break;//- Tape configuration (R)
                        
                    case 'g':
                        property = RDA1.propertyForName("configurationOfPlaybackChannels");
                        break;//- Configuration of playback channels (R)
                        
                    case 'h':
                        property = RDA1.propertyForName("specialPlaybackCharacteristic");
                        break;//- Special playback characteristics (R)
                        
                    case '0':
                        property = marcont.hasNumber;
                        break;//- Authority record control number or standard number (R)
                        
                    case '2':
                        property = marcont.hasSource;
                        break;//- Source (NR)
                        
                    case '3':
                        property = RDA1.propertyForName("baseMaterial");
                        break;//- Materials specified (NR)
                        
                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                }
                
              }
                
                break;
                 
                 
           case 345:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("presentationFormat");//Presentation format (R)
                        break;
                            
                    case 'b':
                        property = RDA1.propertyForName("projectionSpeed");
                        break;//- Projection speed
                            
                    case '0':
                        property = null;
                        break;//- Authority record control number or standard number (R)
                        
                    case '2':
                        property = null;
                        break;//- Source (NR)
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)
                        
                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                        
                }
            }
                break;   
               
               
           case 346:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("videoFormat");//Video format (R)
                        break;
                            
                    case 'b':
                        property = RDA1.propertyForName("broadcastStandard");
                        break;//- Broadcast standard (R)
                            
                    case '0':
                        property = null;
                        break;//- Authority record control number or standard number (R)
                        
                    case '2':
                        property = null;
                        break;//- Source (NR)
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)
                        
                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                        
                }
            }
                break;
               
               
            case 347://Digital File Characteristics (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("fileType");;//File type(R)
                        break;
                            
                    case 'b':
                        property = RDA1.propertyForName("encodingFormat");;
                        break;//- Encoding format (R)
                        
                    case 'c':
                        property = RDA1.propertyForName("fileSize");;
                        break;//- File size (R)
                        
                    case 'd':
                        property = RDA1.propertyForName("resolution");;
                        break;//- Resolution (R)
                        
                    case 'e':
                        property = RDA1.propertyForName("regionalEncoding");;
                        break;//- Regional encoding (R)
                        
                    case 'f':
                        property = RDA1.propertyForName("transmissionSpeed");;
                        break;//- Transmission speed (R)

                    case '0':
                        property = null;
                        break;//- Authority record control number or standard number (R)
                        
                    case '2':
                        property = null;
                        break;//- Source (NR)
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR)
                        
                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                        
                }
            }
                break;
                
                
                
                case 351://Organization and Arrangement of Materials (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = marcont.hasOrganization;//a - Organization (R) 
                        break;
                            
                    case 'b':
                        property = null;
                        break;//- Arrangement (R)
                            
                    case 'c':
                        property = null;
                        break;//- Hierarchical level 
                        
                    case '3':
                        property = RDA1.propertyForName("baseMaterial");
                        break;//- Materials specified (NR)
                        
                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                        
                }
            }
                break;
                    
                    
                    
                    
            case 352: //Digital Graphic Representation (R
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("productionMethod");//$a - Direct reference method (NR) 

                        break;
                            
                    case 'b':
                        property = null;
                        break;//- Object type(R)
                            
                    case 'c':
                        property = null;
                        break;//- Object count(R)
                        
                    case 'd':
                        property = null;
                        break;//- Row count (NR)
                        
                    case 'e':
                        property = null;
                        break;//- Column count (NR)
                        
                        
                    case 'f':
                        property = null;
                        break;//- Vertical count(R)
                            
                    case 'g':
                        property = null;
                        break;//- VPF topology level (R)
                        
                    case 'i':
                        property = null;
                        break;//- Indirect reference description(NR)
                        
                    case 'q':
                        property = null;
                        break;//- Format of the digital image(NR)
                        
                        
                    case '6':
                        property = null;//linkage
                        break;

                    case '8':
                        property = null;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                        
                }
            }
                break;
                        
                        
                        
                
            default:
                property = null;
        }
        
        
        return property;
    }
    
    
}
