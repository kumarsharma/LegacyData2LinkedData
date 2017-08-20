/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MappingClasses;

import Vocabularies.marcont;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;
import com.hp.hpl.jena.vocabulary.RDF;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;

//import org.dspace.adapters.rdf.vocabularies.DC;
//import org.dspace.adapters.rdf.vocabularies.DS;

import com.hp.hpl.jena.rdf.model.Resource;
import java.util.Iterator;

import java.util.List;

import Vocabularies.RDA1;

/**
 *
 * @author home
 */
public class ML1_MainEntryInfo extends ML00_DataInfo {
    
    
   
     public static String getPersonName(DataField df)
    {
        String keyTitle = null;
        String pName = "";
        List<Subfield> subfields = df.getSubfields();

        Iterator<Subfield> it = subfields.iterator();
        while(it.hasNext())
        {
            Subfield sf = it.next();
            
            if(sf.getCode() == 'a')
            {
                keyTitle = sf.getData();
                String[] s = keyTitle.split(",");
                if(s.length >= 2)
                {
                    pName = pName.concat(s[1]);
                    pName = pName.concat(" ");
                    pName = pName.concat(s[0]);
                }
                else 
                    pName = keyTitle;
                
                pName = pName.replaceFirst(" ", "");
                pName = pName.replaceAll(",", "");
                pName = pName.replaceAll(" ", "_");
            }
        }

        return pName;
    }
    
     public Property getPropertyForSubFieldUsingDataField(Subfield sf, DataField df)
    {
        int dTag = Integer.parseInt(df.getTag());
        char sTag = sf.getCode();
        
      
       
        
        Property property = null;
                
        if(dTag == 100 || dTag == 110 || dTag == 111)
        {
            switch(sTag)
            {          
                case 'f':
                    property = RDA1.propertyForName("dateOfWork");//date of a work
                    break;    

                case 'g':
                    property = marcont.hasAbstract;//miscellaneous information
                    break;    

                case 'j':
                    property = null; //attribution qualifier
                    break;

                case 'k':
                    property = null; //form subheading
                    break;    

                case 'l':
                    property = RDA1.propertyForName("languageOfTheContentExpression");//;//language of work
                    break;

                case 'n':
                    property = marcont.hasNumber;//number of part/secion of a work
                    break;     

                case 'p':
                    property = marcont.hasTopic;//name of part/secion of a work
                    break; 

                case 'q':
                    property = FOAF.name;//fuller form of name
                    break;    

                case 't':
                    property = RDA1.propertyForName("titleOfTheWork");//title of a work
                    break;    

                case 'u':
                    property = marcont.hasAffiliation;//affilliation
                    break;    

                case '0':
                    property = null;//authority record control no.
                    break;    

                case '4':
                    property = null;//related code
                    break;    

                case '6':
                    property = marcont.hasSource;//linkage 
                    break;    

                case '8':
                    property = null;//field link and sequence no.
                break;
            }  
        }
        
        if(property != null)
            return property;
        
        switch(dTag)
        {
            case 100:// Main Entry-Personal Name (NR)

            {
                 switch(sTag)
                    {                          
                        case 'a':
                            property = FOAF.name;//personal name
                            break;
                            
                        case 'b':
                            property = null; //numeration
                            break;
                            
                        case 'c':
                            property = FOAF.title; //title & words associated with a name
                            break;    
                            
                        case 'd':
                            property = FOAF.birthday;//date associated with a name
                            break;
                            
                        case 'e':
                            property = marcont.hasKeyword;//related term
                            break; 
                            
                        case 'q':
                            property = FOAF.name;//- Fuller form of name (NR) 
                            break;

                        default:
                            property = null;
                            break; 
                    }                
            }
                break;
                
            case 110://Main Entry - Corporate Name (NR)
              {
                 switch(sTag)
                    {
                        case 'a':
                            property = null;//corporate name or jurisdiction name as entry element
                            break;
                            
                        case 'b':
                            property = null;//subordinate unit
                            break;
                            
                        case 'c':
                            property = marcont.hasAddress;//location of meeting
                            break;    
                            
                        case 'd':
                            property = DC_11.date;//date of meeting or treaty signing
                            break;
                            
                        case 'e':
                            property = null;//subordinate unit
                            break; 
                            
                        default:
                            property = null;
                            break; 
                    } 
              }
                
                break;
                
            case 111:// Main Entry - Meeting Name (NR)
            {
                switch(sTag)
                {
                        case 'a':
                            property = marcont.hasOrganization;//meeting name or jurisdiction name as entry element
                            break;

                        case 'b':
                            property = null;//subordinate unit
                            break;

                        case 'c':
                            property = marcont.hasAddress;//location of meeting
                            break;    

                        case 'd':
                            property = DC_11.date;//date of meeting or treaty signing
                            break;

                        case 'e':
                            property = marcont.hasKeyword;//relation term
                            break; 

                        default:
                            property = null;
                            break;
                        
                }
            }
                break;
                
            case 130://Main Entry - Uniform Title (NR) 
            {
                switch(sTag)
                {
                        case 'a':
                            property = RDA1.propertyForName("otherTitleInformation");//uniform title
                            break;

                        case 'd':
                            property = marcont.hasDate;//date of treaty signing
                            break;

                        case 'f':
                            property = RDA1.propertyForName("dateOfWork");//date of a work
                            break;    

                        case 'g':
                            property = null;//misc. information
                            break;

                        case 'h':
                            property = RDA1.propertyForName("mediumOfPerformance");//medium
                            break; 

                        case 'k':
                            property = null;//form subheading
                            break;

                        case 'l':
                            property = RDA1.propertyForName("languageOfTheContent");//language of a work
                            break;

                        case 'm':
                            property = RDA1.propertyForName("mediumOfPerformanceOfMusicalConten");//medium of performance for music
                            break;    

                        case 'n':
                            property = marcont.hasNumber;//no. of part/section of a work
                            break;

                        case 'o':
                            property = null;//arranged statement for music
                            break; 


                        case 'p':
                            property = null;//name of part/secion of a work
                            break; 

                        case 'r':
                            property = null;//key for music
                            break;    

                        case 's':
                            property = marcont.hasVolume;//version
                            break;    

                        case 't':
                            property = RDA1.propertyForName("titleOfTheWork");;//title of a work
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
