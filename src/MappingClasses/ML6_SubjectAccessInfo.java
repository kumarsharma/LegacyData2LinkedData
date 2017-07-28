/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MappingClasses;

import Vocabularies.marcont;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.DC_11;
import com.hp.hpl.jena.vocabulary.VCARD;
import java.util.Iterator;
import java.util.List;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Subfield;

/**
 *
 * @author home
 */
public class ML6_SubjectAccessInfo extends ML00_DataInfo {
    
      
    public static String getLocalityName(DataField df)
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
        switch(dTag)
        {
            
            case 650: //series statement
            case 651:
            {
                switch(sTag)
                {
                    case 'a':
                        property = VCARD.Locality;//- Topical term or geographic name entry element (NR) 
                        break;
                        
                    case 'b':
                        property = null;//- Topical term following geographic name entry element (NR) 
                        break;
                        
                    case 'c':
                        property = null;//- Location of event (NR) 
                        break;
                        
                    case 'd':
                        property = null;//- Active dates (NR) 
                        break;
                        
                    case 'e':
                        property = null;//- Relator term (R) 
                        break;
                        
                    case '4':
                        property = null;//- Relator code (R) 
                        break;
                        
                    case 'v':
                        property = null;//- Form subdivision (R) 
                        break;
                        
                    case 'x':
                        property = marcont.hasCoverage;//- General subdivision (R) 
                        break;
                        
                    case 'y':
                        property = null;//- Chronological subdivision (R) 
                        break;
                        
                    case 'z':
                        property = VCARD.Region;//- Geographic subdivision (R) 
                        break;
                }
            }
                break;
            
            case 600: //Subject Added Entry-Personal Name (R)
            case 610:
            case 611:
            case 630:    
            {
                switch(sTag)
                {
                    case 'a':
                    {
                        if(dTag == 600 || dTag == 610 || dTag == 611 || dTag == 630)
                            property = DCTerms.subject;//- Personal name (NR) 
                        else if(dTag == 610)
                            property = null;//-  Corporate name or jurisdiction name as entry element (NR) 
                        else if(dTag == 611)
                            property = null;//- Meeting name or jurisdiction name as entry element (NR) 
                        else if(dTag == 630)
                            property = null;//- Uniform title (NR) 
                    }   
                     break;   
                        
                    case 'b':
                    {
                        if(dTag == 600)
                            property = null;//- Numeration (NR) 
                        else if(dTag == 610)
                            property = null;//- Subordinate unit (R) 

                    }
                        break;
                        
                    case 'c':
                    {
                        if(dTag == 600)
                            property = null;//- Titles and other words associated with a name (R) 
                        else if(dTag == 610)
                            property = null;//- Location of meeting (NR) 
                        else if(dTag == 611)
                            property = null;//- Location of meeting (NR) 
                    }
                        break;
                        
                    case 'd':
                    {
                        if(dTag == 600)
                            property = null;//- Dates associated with a name (NR) 
                        else if(dTag == 610)
                            property = null;//- Date of meeting or treaty signing (R) 
                        else if(dTag == 611)
                            property = null;//- Date of meeting (NR)
                        else if(dTag == 630)
                            property = null;//- Date of treaty signing (R) 
                    }
                        break;
                        
                    case 'e':
                    {
                        if(dTag == 600 || dTag == 610 || dTag == 630)
                            property = null;//- Relator term (R) 
                        else if(dTag == 611)
                            property = null;//- Subordinate unit (R) 
                    }
                        break;
                        
                        
                        
                    case 'f':
                        property = null;
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
                        break;//- Form subdivision (R) 
                        
                    case 'x':
                        property = null;
                        break;//- General subdivision (R) 
                        
                    case 'y':
                        property = null;
                        break;//- Chronological subdivision (R) 
                        
                    case 'z':
                        property = null;
                        break;//- Geographic subdivision (R) 
                        
                    case '0':
                        property = null;
                        break;//- Authority record control number (R)
                        
                    case '2':
                        property = null;
                        break;//- Source of heading or term (NR) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '4':
                        property = null;
                        break;//- Relator code (R) 

        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                
            case 648: //  Subject Added Entry-Chronological Term (R)

            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Chronological term (NR) 
                    
//                        Subject subdivision portion
                        
                    case 'v':
                        property = null;
                        break;//- Form subdivision (R) 
                        
                    case 'x':
                        property = null;
                        break;//- General subdivision (R) 
                        
                    case 'y':
                        property = null;
                        break;//- Chronological subdivision (R) 
                        
                    case 'z':
                        property = null;
                        break;//- Geographic subdivision (R) 

                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;
                
            case 653: //Index Term-Uncontrolled (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//-  Uncontrolled term (R) 
        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;    
                
 
            case 654: //Subject Added Entry-Faceted Topical Terms (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Focus term (R) 
                        
                       
                    case 'b':
                        property = null;
                        break;//- Non-focus term (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Facet/hierarchy designation (R) 
                        
                    case 'e':
                        property = null;
                        break;//- Relator term (R) 
                            
                    case 'v':
                        property = null;
                        break;//- Form subdivision (R) 
                        
                    case 'y':
                        property = null;
                        break;//- Chronological subdivision (R) 
                        
                    case 'z':
                        property = null;
                        break;//- Geographic subdivision (R) 
                        
                    case '0':
                        property = null;
                        break;//- Authority record control number (R)
                        
                    case '2':
                        property = null;
                        break;//- Source of heading or term (NR) 
                        
                    case '3':
                        property = null;
                        break;//- Materials specified (NR) 
                        
                    case '4':
                        property = null;
                        break;//- Relator code (R) 
        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;    
                
                    
            case 655: // -  Index Term-Genre/Form (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Genre/form data or focus term (NR) 
                        
                    case 'b':
                        property = null;
                        break;//- Non-focus term (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Facet/hierarchy designation (R) 
                        
                    case 'v':
                        property = null;
                        break;//- Form subdivision (R) 
                        
                    case 'x':
                        property = null;
                        break;//- General subdivision (R) 
                        
                    case 'y':
                        property = null;
                        break;//- Chronological subdivision (R) 
                        
                    case 'z':
                        property = null;
                        break;//- Geographic subdivision (R) 
                        
                    case '0':
                        property = null;
                        break;//- Authority record control number (R)
                        
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
                        
 
                     
            case 656: //Index Term-Occupation (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Occupation (NR) 
        
                    case 'k':
                        property = null;
                        break;//- Form (NR) 
                        
                    case 'v':
                        property = null;
                        break;//- Form subdivision (R) 
                        
                    case 'x':
                        property = null;
                        break;//- General subdivision (R) 
                        
                    case 'y':
                        property = null;
                        break;//- Chronological subdivision (R) 
                        
                    case 'z':
                        property = null;
                        break;//- Geographic subdivision (R) 
                        
                    case '0':
                        property = null;
                        break;//- Authority record control number (R)
                        
                    case '2':
                        property = null;
                        break;//- Source of term (NR) 
                        
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
                
               
                 
            case 657: //Index Term-Function (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Function (NR) 
                        
                    case 'v':
                        property = null;
                        break;//- Form subdivision (R) 
                        
                    case 'x':
                        property = null;
                        break;//- General subdivision (R) 
                        
                    case 'y':
                        property = null;
                        break;//- Chronological subdivision (R) 
                        
                    case 'z':
                        property = null;
                        break;//- Geographic subdivision (R) 
                        
                    case '0':
                        property = null;
                        break;//- Authority record control number (R)
                        
                    case '2':
                        property = null;
                        break;//- Source of term (NR) 
                        
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
                
                
                
            case 658:// - Index Term-Curriculum Objective (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Main curriculum objective (NR) 
                        
                    case 'b':
                        property = null;
                        break;//- Subordinate curriculum objective (R) 
                        
                    case 'c':
                        property = null;
                        break;//- Curriculum code (NR) 
                        
                    case 'd':
                        property = null;
                        break;//- Correlation factor (NR) 
                        
                    case '2':
                        property = null;
                        break;//- Source of term or code (NR)
        
                    case '6':
                        property = null;//- Linkage (NR) 
                        break;
                        
                    case '8':
                        property = null;//- Field link and sequence number (R) 
                        break;
     
                }
            }
                break;    
                
                
             case 662:// - Subject Added Entry-Hierarchical Place Name (R)
            {
                switch(sTag)
                {
                    case 'a':
                        property = null;
                        break;//- Country or larger entity (R) 
                        
                        
                    case 'b':
                        property = null;
                        break;//- First-order political jurisdiction (NR) 
                        
                    case 'c':
                        property = null;
                        break;//- Intermediate political jurisdiction (R) 
                        
                    case 'd':
                        property = null;
                        break;//- City (NR) 
                        
                    case 'e':
                        property = null;
                        break;//- Relator term (R) 
                        
                    case 'f':
                        property = null;
                        break;//- City subsection (R) 
                        
                    case 'g':
                        property = null;
                        break;//- Other nonjurisdictional geographic region and feature (R) 
                        
                    case 'h':
                        property = null;
                        break;//- Extraterrestrial area (R) 
                        
                    case '0':
                        property = null;
                        break;//- Authority record control number (R)
                        
                    case '2':
                        property = null;
                        break;//- Source of heading or term (NR) 
                        
                    case '4':
                        property = null;
                        break;//- Relator code (R) 
        
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
