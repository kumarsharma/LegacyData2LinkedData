/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mappings;

import LinkDataset.URiLookup;
import MLTools.Tools;
import MappingClasses.ML000_LeaderInfo;
import MappingClasses.ML0_ControlInfo;
import MappingClasses.ML00_DataInfo;
import MappingClasses.ML2_TitleInfo;
import Vocabularies.marcont;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.*;
import java.util.ListIterator;
import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.converter.CharConverter;
import org.marc4j.marc.Leader;
import MLTools.Dictionary;
import java.util.Iterator;


/**
 *
 * @author home
 */

 class CharCon extends CharConverter{
    
    public CharCon()
    {
        
    }
    
    @Override
    public String convert(char[] chars)
    {
        return null;
    }

}

public class Marc2RDFMapper {
 
    
    static Dictionary uriCache;
    
    public static void createResourceFromRecordInModel(Record record, Model model, Model model2, boolean addLink)
    {
        String keyTitle = null;
        
        uriCache = new Dictionary();

        Iterator<DataField> itds = record.getDataFields().iterator();
        while(itds.hasNext())
        {
            DataField df = itds.next();
            if(df.getTag().equalsIgnoreCase("245"))
            {
                keyTitle = ML2_TitleInfo.getKeyTitle(df);
                break;
            }
            
            if(df.getTag().equalsIgnoreCase("246"))
            {
                keyTitle = ML2_TitleInfo.getKeyTitle(df);
                break;
            }
        }
       
        Resource aRes = null;
        String mappingTable = "";

        if(null != keyTitle)
        {
            if(keyTitle.length()>45)
                keyTitle = keyTitle.substring(0, 45);
            
            keyTitle = keyTitle.replaceAll(" ", "_");
            keyTitle = keyTitle.replaceAll("\\[", "_");
            keyTitle = keyTitle.replaceAll("\\]", "_");
            System.out.println("REPLACED: " + keyTitle);
            aRes = model.createResource(Tools.getBaseURI()+keyTitle);
                        
            if(addLink)
            {
                String otherURI = (String)uriCache.valueForKey(keyTitle);
                if(otherURI == null)
                {
                    otherURI = URiLookup.lookUpDBPediaURiForResourceTitle(keyTitle, "Book");

                    if(otherURI != null)
                        uriCache.setValueForKey(otherURI, keyTitle);
                }
                if(otherURI != null)
                {
                    aRes.addProperty(RDFS.seeAlso, model.createResource(otherURI));
                }
                otherURI = URiLookup.lookUpVIAFURiForSearchTerm(keyTitle);
                if(otherURI != null)
                {
                    aRes.addProperty(RDFS.seeAlso, model.createResource(otherURI));
                }
            }
        }
        else
            aRes = model.createResource(Tools.getBaseURI()+record.getLeader().toString());
        
        //record type
        String leader = record.getLeader().toString();
        int l = record.getLeader().getRecordLength();
        Leader le = record.getLeader();
        String rtype = ML000_LeaderInfo.getStringFromRecordType(record.getLeader().getTypeOfRecord());
        if(null != rtype)
        {
            aRes.addProperty(DCTerms.type, rtype);
            mappingTable += "LEADER,"+record.getLeader().getTypeOfRecord()+","+DCTerms.getURI()+"\n";
        }
        
        //record status
        String rstatus = ML000_LeaderInfo.getStringFromRecordStatus(record.getLeader().getRecordStatus());
        if(null != rstatus)
        {
            Property p = new PropertyImpl(marcont.getURI(), "hasRecordStatus");
            aRes.addProperty(p, rstatus);
            mappingTable += "LEADER,RecordStatusß"+","+DCTerms.getURI()+"\n";
        }
        
        //encoding scheme
        String rscheme = ML000_LeaderInfo.stringFromEncodingScheme(record.getLeader().getCharCodingScheme());
        if(null != rscheme)
        {
            Property p = new PropertyImpl(marcont.getURI(), "hasEncodingScheme");
            aRes.addProperty(p, rscheme);
            mappingTable += "LEADER,RecordStatusß"+","+DCTerms.getURI()+"\n";
        }
        
        //record lenght
        int rlen = record.getLeader().getRecordLength();
        if(0 != rlen)
        {
            Property p = new PropertyImpl(marcont.getURI(), "hasRecordLength");
            aRes.addProperty(p, ""+rlen);
        }
        
        //aRes.addProperty(DCTerms.type, DCTerms.BibliographicResource);
        ListIterator controlFields = record.getControlFields().listIterator();
        while(controlFields.hasNext())
        {
            ControlField cf = (ControlField)controlFields.next();
            
            ML0_ControlInfo mlcf = new ML0_ControlInfo();
            Property prop = mlcf.getPropertyForControlField(cf);
           
            if(prop != null)
                aRes.addProperty(prop, cf.getData());
        }
        
        ListIterator dataFields = record.getDataFields().listIterator();
        while(dataFields.hasNext())
        {
            
            DataField df = (DataField)dataFields.next();
            ML00_DataInfo dataInfo = Tools.dataInfoObjectFromTag(df.getTag());
            
//            if(dataInfo.getPropertyForDataField(df)!=null)
//                aRes.addProperty(dataInfo.getPropertyForDataField(df), df.toString());
            
            mappingTable += dataInfo.addPropertiesInResourceUsingDataField(aRes, df);
//            dataInfo.addPropertiesInResourceAndModelUsingDataField(aRes, model, model2, df, uriCache, addLink);
        }
        System.out.println("Mapping table:\n"+mappingTable);
//        BibRDFStore store = new BibRDFStore();
//        store.storeModelIntoTDB(model);
    }    
    
    public static String getRDFInN_TriplesForRecord(Record record, boolean addLink)
    {
        String RDF = "";
        
        String keyTitle = null;
        uriCache = new Dictionary();

        //assigning resource name
        Iterator<DataField> itds = record.getDataFields().iterator();
        while(itds.hasNext())
        {
            DataField df = itds.next();
            if(df.getTag().equalsIgnoreCase("245"))
            {
                keyTitle = ML2_TitleInfo.getKeyTitle(df);
                break;
            }
            
            if(df.getTag().equalsIgnoreCase("246"))
            {
                keyTitle = ML2_TitleInfo.getKeyTitle(df);
                break;
            }
        }
       
        String resource = null;
        
        if(null != keyTitle)
        {
            if(keyTitle.length()>45)
                keyTitle = keyTitle.substring(0, 45);
            
            keyTitle = keyTitle.replaceAll(" ", "_");
            keyTitle = keyTitle.replaceAll("\\[", "_");
            keyTitle = keyTitle.replaceAll("\\]", "_");
            resource = Tools.getBaseURI()+keyTitle;
            
            if(addLink)
            {
                String otherURI = (String)uriCache.valueForKey(keyTitle);
                if(otherURI == null)
                {
                    otherURI = URiLookup.lookUpDBPediaURiForResourceTitle(keyTitle, "Book");
                    if(otherURI != null)
                        uriCache.setValueForKey(otherURI, keyTitle);
                }
                if(otherURI != null)
                {
                    RDF += "<"+resource+">"+" "+"<"+RDFS.seeAlso.getURI()+">"+" "+"<"+otherURI+"> .\n";
                }
                otherURI = URiLookup.lookUpVIAFURiForSearchTerm(keyTitle);
                if(otherURI != null)
                {
                    RDF += "<"+resource+">"+" "+"<"+RDFS.seeAlso.getURI()+">"+" "+"<"+otherURI+"> .\n";
                }
            }
        }
        else
            resource = Tools.getBaseURI()+record.getLeader().toString();
        
        //record type
        String leader = record.getLeader().toString();
        int l = record.getLeader().getRecordLength();
        Leader le = record.getLeader();
        String rtype = ML000_LeaderInfo.getStringFromRecordType(record.getLeader().getTypeOfRecord());
        if(null != rtype)
        {
            RDF += "<"+resource+">"+" "+"<"+DCTerms.type.getURI()+">"+" "+"\""+rtype+"\" .\n";
        }
        
        //record status
        String rstatus = ML000_LeaderInfo.getStringFromRecordStatus(record.getLeader().getRecordStatus());
        if(null != rstatus)
        {
            Property p = new PropertyImpl(marcont.getURI(), "hasRecordStatus");
            RDF += "<"+resource+">"+" "+"<"+p.getURI()+">"+" "+"\""+rstatus+"\" .\n";
        }
        
        //encoding scheme
        String rscheme = ML000_LeaderInfo.stringFromEncodingScheme(record.getLeader().getCharCodingScheme());
        if(null != rscheme)
        {
            Property p = new PropertyImpl(marcont.getURI(), "hasEncodingScheme");
            RDF += "<"+resource+">"+" "+"<"+p.getURI()+">"+" "+"\""+rscheme+"\" .\n";
        }
        
        //record lenght
        int rlen = record.getLeader().getRecordLength();
        if(0 != rlen)
        {
            Property p = new PropertyImpl(marcont.getURI(), "hasRecordLength");
            RDF += "<"+resource+">"+" "+"<"+p.getURI()+">"+" "+"\""+rlen+"\" .\n";
        }
        
        RDF += "<"+resource+">"+" "+"<"+DCTerms.type.getURI()+">"+" "+"<"+DCTerms.BibliographicResource+"> .\n";
        ListIterator controlFields = record.getControlFields().listIterator();
        while(controlFields.hasNext())
        {
            ControlField cf = (ControlField)controlFields.next();
            
            ML0_ControlInfo mlcf = new ML0_ControlInfo();
            Property prop = mlcf.getPropertyForControlField(cf);
           
            if(prop != null)
            {
                String data = cf.getData();
                if(cf.getTag().equals("005"))
                {
                    java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("yyyymmddhhmmss.SSS");
                    java.util.Date date;
                    try {
                        date = dateFormat.parse(data);
                        System.out.println(date.toString()); // Wed Dec 04 00:00:00 CST 2013

                        data = date.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                RDF += "<"+resource+">"+" "+"<"+prop.getURI()+">"+" "+"\""+data+"\" .\n";
            }
        }
        
        ListIterator dataFields = record.getDataFields().listIterator();
        while(dataFields.hasNext())
        {
            DataField df = (DataField)dataFields.next();
            ML00_DataInfo dataInfo = Tools.dataInfoObjectFromTag(df.getTag());
//            if(dataInfo.getPropertyForDataField(df)!=null)
//                RDF += "<"+resource+">"+" "+"<"+dataInfo.getPropertyForDataField(df).getURI()+">"+" "+"\""+df.toString()+"\" .\n";
            RDF += dataInfo.propertiesForNTripleResourceUsingDataField(resource, df);
//            RDF += dataInfo.propertiesForNTripleResourceAndModelUsingDataField(resource, df, uriCache, addLink);
        }
        return RDF;
    }    
    
}
