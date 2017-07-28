/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MappingClasses;

/**
 *
 * @author home
 */
public class ML000_LeaderInfo {
    
    
    public static String getStringFromRecordType(char type)
    {
       String string = null;
       
       switch(type)
       {
           case 'a':
               string = "Language material";
               break;
               
           case 'c':
               string = "Notated music";
               break;
               
           case 'd':
               string = "Manuscript notated music";
               break;
               
           case 'e':
               string = "Cartographic material";
               break;
               
           case 'f':
               string = "Manuscript cartographic material";
               break;
               
           case 'g':
               string = "Projected medium";
               break;
               
           case 'i':
               string = "Nonmusical sound recording";
               break;
               
           case 'j':
               string = "Musical sound recording";
               break;
               
           case 'k':
               string = "Two-dimensional nonprojectable graphic";
               break;
               
           case 'm':
               string = "Computer file";
               break;
               
           case 'o':
               string = "Kit";
               break;
               
           case 'p':
               string = "Mixed materials";
               break;
               
           case 'r':
               string = "Three-dimensional artifact or naturally occurring object";
               break;
               
           case 't':
               string = "Manuscript language material";
               break;

       }
       
       return string;
    }
    
    public static String getStringFromRecordStatus(char status)
    {
       String string = null;
       
       switch(status)
       {
           case 'a':
               string = "Increase in encoding level";
               break;
               
           case 'c':
               string = "Corrected or revised";
               
           case 'd':
               string = "Deleted";
               break;
               
           case 'n':
               string = "New";
               break;
               
           case 'p':
               string = "Increase in encoding level from prepublication";
               break;
       }
       
       return string;
    }
    
    public static String stringFromEncodingScheme(char scheme)
    {
        String string = null;
       
       switch(scheme)
       {
           case 'a':
               string = "UCS/Unicode";
               break;
               
           case '#':
               string = "MARC-8";
               break;
       }
       
       return string;
    }
    
}
