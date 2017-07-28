/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MappingClasses;

import Vocabularies.marcont;
import com.googlecode.fascinator.vocabulary.BIBO;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;
import com.hp.hpl.jena.vocabulary.VCARD;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.googlecode.fascinator.vocabulary.BIBO;
import org.marc4j.marc.VariableField;

import Vocabularies.RDA1;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author home
 */
public class ML2_TitleInfo extends ML00_DataInfo{
    
    
    public static String getKeyTitle(DataField df)
    {
        String keyTitle = null;
        List<Subfield> subfields = df.getSubfields();
        
        Iterator<Subfield> it = subfields.iterator();
        while(it.hasNext())
        {
            Subfield sf = it.next();
            if(sf.getCode() == 'a')
            {
                keyTitle = sf.getData();
            }
        }

        return keyTitle;
    }
    
    public Property getPropertyForDataField(DataField df)
    {
        int tag = Integer.parseInt(df.getTag());
        Property property;
        
        switch(tag)
        {
            
            case 247: //Former title
                property = RDA1.propertyForName("otherTitleInformation");
            break;
                
            case 258: //Philatelic issue data
                property = null;
            break;
                
            case 270: //Address
                property = VCARD.ADR;
              break;
                
            case 260: //Publication, distribution, etc. (Imprint) 263 Projected publication date
                property = DCTerms.dateSubmitted;
            break;
            
            case 210:// Abbreviated title 222 Key title
            case 240: // Uniform title

            case 241: // [Romanized title]
            case 242: // T ranslation of title by cataloguing agency
            case 243: //Collective uniform title
            case 245:  //Title statement
            case 246: //Varying form of title
           
                
            case 250: //Edition statement
            case 254: //Musical presentation statement
                
            case 255: //Cartographic mathematical data
            case 256: //Computer file characteristics
            case 257: //Country of producing entity

            
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
            case 210:
            {
                switch(sTag)
                {
                    case 'a':
                        
                        property = RDA1.propertyForName("abbreviatedTitle");//abbreviated title
                        break;

                    case 'b':
                        property =  null; //qualifying information
                        break;

                    case '2':
                        property = marcont.hasSource; //source
                        break;    

                    case '6':
                        property = marcont.hasSource;//linkage
                        break;

                    case '8':
                        property = marcont.hasSource;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                        
                }
            }
                break;
                
            case 222:
              {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("keyTitle");//key title
                        break;

                    case 'b':
                        property = null; //qualifying information
                        break;

                    case '6':
                        property = marcont.hasSource;//linkage
                        break;

                    case '8':
                        property = marcont.hasSource;//field link and sequence no.
                        break;  

                    default:
                        property = null;
                        break; 
                }
                
              }
                
                break;
                
            case 240:
            case 243:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("otherTitleInformation");//uniform title
                        break;
                            
                    case 'd':
                        property = null; //date of treaty signing
                        break;

                    case 'f':
                        property = RDA1.propertyForName("dateOfWork"); //date of a work
                        break;    

                    case 'g':
                        property = null;//misc information
                        break;

                    case 'h':
                        property = DCTerms.medium;//medium
                        break; 
                        
                    case 'k':
                        property = DC_11.subject; //form subheading
                        break;    
                            
                    case 'l':
                        property = DC_11.language;//language of a work
                        break;
                        
                    case 'm':
                        property = DCTerms.medium;//medium of perofrmance for music
                        break;         

                    case 'n':
                        property = null;//number of part/secion of a work
                        break;     

                    case 'o':
                        property = null;//arranged stmt for music
                        break; 

                    case 'p':
                        property = RDA1.propertyForName("titleOfTheWork");//new PropertyImpl(BIBO.DocumentPart);//name of part/section of a work
                        break;    

                    case 'r':
                        property = null;//key for music
                        break;    

                    case 's':
                        property = null;//Version
                        break;    

                    case '0':
                        property = marcont.hasNumber;//authority record control no.
                        break;        

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = marcont.hasSource;//field link and sequence no.
                    break;    
                        
                }
            }
                break;
                
             case 242:
              {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("title");//title
                        break;

                    case 'b':
                        property = RDA1.propertyForName("otherTitleInformation"); //remainder of title
                        break;
                        
                    case 'c':
                        property = RDA1.propertyForName("statementOfResponsibility"); //statement of responsebility
                        break;    

                    case 'h':
                        property = DCTerms.medium;//medium
                        break; 
                        
                    case 'n':
                        property = marcont.hasNumber; //number of part/section of a work
                        break;    
                            
                    case 'p':
                        property = RDA1.propertyForName("titleOfTheWork");//of part/section of a work
                        break;    
                        
                    case 'y':
                        property = DC_11.language;//language code of translated title
                        break;        

                    case '6':
                        property = marcont.hasSource;//linkage
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
                 
                 
           case 245:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("keyTitle");;//title
                        break;
                            
                    case 'b':
                        property = RDA1.propertyForName("otherTitleInformation"); //remainder of title
                        break;
                        
                    case 'c':
                        property = null; //stmt of responsability
                        break;    

                    case 'f':
                        property = DC_11.date; //inclusive dates
                        break;    

                    case 'g':
                        property = null;//bulk dates
                        break;

                    case 'h':
                        property = DCTerms.medium;//medium
                        break; 
                        
                    case 'k':
                        property = RDA1.propertyForName("formOfWork"); //form 
                        break;         

                    case 'n':
                        property = marcont.hasNumber;//number of part/secion of a work
                        break;     

                    case 'p':
                        property = RDA1.propertyForName("titleOfTheWork");//name of part/section of a work
                        break;       

                    case 's':
                        property = null;//Version
                        break;            

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = marcont.hasPagesFrom;//field link and sequence no.
                    break;    
                        
                }
            }
                break;      
               
               
            case 246:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("titleProper");//title proper/short title
                        break;
                            
                    case 'b':
                        property = RDA1.propertyForName("otherTitleInformation"); //remainder of title
                        break;

                    case 'f':
                        property = null; //date or seq designation
                        break;    

                    case 'g':
                        property = null;//misc info.
                        break;

                    case 'h':
                        property = DCTerms.medium;//medium
                        break; 
                        
                    case 'i':
                        property = marcont.hasNote; //display text
                        break;         

                    case 'n':
                        property = marcont.hasNumber;//number of part/secion of a work
                        break;     

                    case 'p':
                        property = RDA1.propertyForName("titleOfTheWork");//name of part/section of a work
                        break;       

                    case '5':
                        property = marcont.hasOrganization;//institution to which field apples
                        break;            

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
                    break;    
                        
                }
            }
                break;         
                
           case 247:
            {
                switch(sTag)
                {
                    case 'a':
                        property = DC_11.title;//title
                        break;
                            
                    case 'b':
                        property = RDA1.propertyForName("otherTitleInformation"); //remainder of title
                        break;

                    case 'f':
                        property = null; //date or seq designation
                        break;    

                    case 'g':
                        property = null;//misc info.
                        break;

                    case 'h':
                        property = DCTerms.medium;//medium
                        break; 
                        
                    case 'i':
                        property = marcont.hasNote; //display text
                        break;         

                    case 'n':
                        property = marcont.hasNumber;//number of part/secion of a work
                        break;     

                    case 'p':
                        property = RDA1.propertyForName("titleOfTheWork");//name of part/section of a work
                        break;       

                    case 'x':
                        property = marcont.hasISSN;//International Standard Serial Number
                        break;            

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = marcont.hasPagesFrom;//field link and sequence no.
                    break;    
                        
                }
            }
                break;     
              
           
           case 250:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("editionStatement");//edition stmt
                        break;
                        
                    case 'b':
                        property = RDA1.propertyForName("noteOnEditionStatement");//remainder of edition stmt
                        break;                 

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
                    break;      
                }
            }
               break;
               
           case 254:
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;//musical presentation stmt
                        break;

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
                    break;      
                }
            }  
               break;
               
             case 255:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("scale");//stmt of scale
                        break;
                        
                    case 'b':
                        property = RDA1.propertyForName("projectionOfCartographicConten"); //stmt of projection
                        break;

                    case 'c':
                        property = RDA1.propertyForName("coordinatesOfCartographicContentWork"); //stmt of coordinate
                        break;    

                    case 'd':
                        property = null;//stmt of zone
                        break;

                    case 'e':
                        property = RDA1.propertyForName("equinox");//stmt of equinox
                        break; 
                        
                    case 'f':
                        property = null; //outer G-ring coordinate pairs
                        break;         

                    case 'g':
                        property = null;// Exclusion G-ring coordinate pairs
                        break;      

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
                    break;      
                }
            }  
               break;  
               
           case 256:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("digitalFileCharacteristic");//computer file characteristics
                        break;

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
                    break;      
                }
            }  
               break;
               
             case 257:
            {
                switch(sTag)
                {
                    case 'a':
                        property = VCARD.Country;//country of producing entity
                        break;
                        
                    case '2':
                        property = marcont.hasSource;//source
                        break;    

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
                    break;      
                }
            }  
               break;
                 
            case 258:
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;//Issuing jurisdiction
                        break;
                        
                    case 'b':
                        property = marcont.hasDOI;//denomination
                        break;    

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
                    break;      
                }
            }  
               break;  
                
                
             case 260:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("placeOfPublication");//place of publication
                        break;
                        
                    case 'b':
                        property = RDA1.propertyForName("publishersName"); //name of publisher, distribution...
                        break;

                    case 'c':
                        property = RDA1.propertyForName("dateOfPublication"); //date of publication
                        break;    

                    case 'e':
                        property = RDA1.propertyForName("placeOfManufacture");//place of manufacturing
                        break; 
                        
                    case 'f':
                        property = RDA1.propertyForName("manufacturersName"); //manufacturer
                        break;         

                    case 'g':
                        property = RDA1.propertyForName("dateOfManufacture");//date of manufacturing
                        break;      

                    case '3':
                        property = null;//materials specified
                        break;    
                         
                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
                    break;      
                }
            }  
               break;    
            
            case 263:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("dateOfPublication");//projected publication date
                        break;

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
                    break;      
                }
            }  
               break;
                
            case 264:
            {
                switch(sTag)
                {
                    case 'a':
                        property = RDA1.propertyForName("placeOfPublication");//place of production, publication, distribution, manufature
                        break;
                        
                    case 'b':
                        property = RDA1.propertyForName("distributorsName");//name of production, publication, distribution, manufature
                        break;    
                        
                    case 'c':
                        property = RDA1.propertyForName("dateOfDistribution");//date of production, publication, distribution, manufature
                        break;
                        
                    case '3':
                        property = null;// new PropertyImpl(BIBO.producer);//materials specified
                        break;        

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
                    break;      
                }
            }  
               break;
                
            case 270:
            {
                switch(sTag)
                {
                    case 'a':
                        property = marcont.hasAddress;//address
                        break;
                            
                    case 'b':
                        property = VCARD.Extadd; //city
                        break;
                        
                    case 'c':
                        property = VCARD.Extadd; //state
                        break;    

                    case 'd':
                        property = VCARD.Country;//country
                        break;

                    case 'e':
                        property = VCARD.Pcode;//postal code
                        break;     

                    case 'f':
                        property = RDA1.propertyForName("termsOfAvailability"); //terms of preceding attention name
                        break;    

                    case 'g':
                        property = marcont.hasNote;//attention name
                        break;

                    case 'h':
                        property = null;//new PropertyImpl(BIBO.status);//attention position
                        break; 
                        
                    case 'i':
                        property = null; //type of address
                        break;         

                    case 'j':
                        property = VCARD.TEL;//specialized telephone number
                        break;     

                    case 'k':
                        property = FOAF.phone;//telephone number
                        break;       

                    case 'l':
                        property = FOAF.phone;//Fax number
                        break;  
                        
                    case 'm':
                        property = FOAF.mbox; //E-mail addres
                        break;    

                    case 'n':
                        property = null;//TDD or TTY number
                        break;

                    case 'p':
                        property = RDA1.propertyForName("contactInformation");//contact person
                        break; 
                        
                    case 'q':
                        property = VCARD.TITLE; //title of contact person
                        break;         

                    case 'r':
                        property = null;//Hourse
                        break;     

                    case 'z':
                        property = RDA1.propertyForName("noteOnTitle");//Public note
                        break;       

                    case '4':
                        property = null;//new PropertyImpl(BIBO.Code);//Relator code
                        break;      

                    case '6':
                        property = marcont.hasSource;//linkage 
                        break;    

                    case '8':
                        property = null;//field link and sequence no.
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
