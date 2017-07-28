/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MLTools;

import MappingClasses.*;

/**
 *
 * @author home
 */
public class Tools {
    
    public static String getBaseURI()
    {
        return "http://localhost:8080/BibliographicLinkedData/BibResources/";
    }
    
    public static ML00_DataInfo dataInfoObjectFromTag(String tag)
    {
        char firstChar = tag.charAt(0);
        ML00_DataInfo dataInfo = null;
        switch(firstChar)
        {
            case '0':
                dataInfo = new ML0109_NumCodeInfo();
                break;
            
            case '1':
                dataInfo = new ML1_MainEntryInfo();
                break;
                
            case '2':
                dataInfo = new ML2_TitleInfo();
                break;
                
            case '3':
                dataInfo = new ML3_PhysicalDescriptionInfo();
                break;
                
            case '4':
                dataInfo = new ML4_SeriesStatementsInfo();
                break;
                
            case '5':
                dataInfo = new ML5_NotesInfo();
                break;
                
            case '6':
                dataInfo = new ML6_SubjectAccessInfo();
                break;
                
                case '7':
                dataInfo = new ML7_AddedEntryInfo();
                break;
                
            case '8':
                dataInfo = new ML8_HoldingsInfo();
                break;
                
            default:
                dataInfo = new ML4_SeriesStatementsInfo();
                break;
        }
        
        return dataInfo;
    }
}
