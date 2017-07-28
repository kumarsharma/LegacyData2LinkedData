/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MappingClasses;

//util

import LinkDataset.URiLookup;
import MLTools.Tools;
import java.util.List;
import java.util.Iterator;

//jena
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.*;

//marc
import org.marc4j.marc.Subfield;
import org.marc4j.marc.DataField;

//import Vocabularies.*;
import com.googlecode.fascinator.vocabulary.BIBO;

import Vocabularies.marcont;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.VCARD;

import MLTools.Dictionary;



//tools

/**
 *http://www.loc.gov/marc/bibliographic/bd00x.html
 * @author home
 */
public class ML00_DataInfo {
    
    
    public List<RDFNode> getRDFNodesForSubFileds(List<Subfield> sFields)
    {
        RDFNode node;
        Resource res;
        
        return null;
    }
    
    protected RDFNode getRDFNodeForSubField(Subfield sField)
    {
        return null;
    }
    
    public Property getPropertyForDataField(DataField df)
    {
        return null;
    }
    
    public Property getPropertyForSubFieldUsingDataField(Subfield sf, DataField df)
    {
        
        return null;
    }
    
    public void addPropertiesInResourceUsingDataField(Resource res, DataField df)
    {
        List<Subfield> sfs = df.getSubfields();
        
        Iterator<Subfield> it = sfs.iterator();
            
        
        
        while(it.hasNext())
        {
            Subfield sf = it.next();
            Property prop = this.getPropertyForSubFieldUsingDataField(sf, df);
            
            if(null != prop)
                res.addProperty(prop, sf.getData());
        }
    }
    
    
   public void addPropertiesInResourceAndModelUsingDataField(Resource res, Model model, Model model2, DataField df, Dictionary uriCache, boolean addLink)
    {
        
        int dTag = Integer.parseInt(df.getTag());
        
        if(dTag == 100)
        {
            String pName = ML1_MainEntryInfo.getPersonName(df);
            
            if(null != pName)
            {
                String personURI = Tools.getBaseURI()+pName;
                Resource pres = model2.createResource(personURI);
                res.addProperty(marcont.hasAuthor, pres);

                List<Subfield> sfs = df.getSubfields();

                Iterator<Subfield> it = sfs.iterator();
                while(it.hasNext())
                {
                    Subfield sf = it.next();
                    Property prop = this.getPropertyForSubFieldUsingDataField(sf, df);

                    if(null != prop)
                        pres.addProperty(prop, sf.getData());
                }
                
                pres.addProperty(RDF.type, FOAF.Person);
                
                
                String otherURI = (String)uriCache.valueForKey(pName);
                
                if(otherURI == null && addLink)
                {
                    otherURI = URiLookup.lookUpDBPediaURiForResourceTitle(pName, "Person");
                    
                    if(otherURI != null)
                        uriCache.setValueForKey(otherURI, pName);
                }
                else
                {
                    System.out.println("READ FROM CACHE");
                }
                
                if(otherURI != null)
                {
                    pres.addProperty(RDFS.seeAlso, model2.createResource(otherURI));
                }
//                else
//                {
//                    res.addProperty(marcont.hasAuthor, model2.getResource(personURI));
//                }
                
                if(addLink){
                    
                    otherURI = URiLookup.lookUpVIAFURiForSearchTerm(pName);
                    if(otherURI != null)
                    {
                        pres.addProperty(RDFS.seeAlso, model2.createResource(otherURI));
                    }
                }
                
            }
            
        }
        else if(dTag == 650 || dTag == 651)
        {
            String pName = ML6_SubjectAccessInfo.getLocalityName(df);
            
            if(null != pName)
            {
                String otherURI = (String)uriCache.valueForKey(pName);
                
                
                if(otherURI == null && addLink)
                {
                    otherURI = URiLookup.lookUpDBPediaURiForResourceTitle(pName, "Place");
                    
                    if(otherURI != null)
                        uriCache.setValueForKey(otherURI, pName);
                }
                else
                {
                    System.out.println("READ FROM CACHE");
                }
                
                
                if(otherURI != null)
                {
                    res.addProperty(VCARD.Locality, model2.createResource(otherURI));
                }
                else
                {
                    res.addProperty(VCARD.Locality, pName);
                }
            }
            
        }
        else
        {
            this.addPropertiesInResourceUsingDataField(res, df);
        }
    }
}
