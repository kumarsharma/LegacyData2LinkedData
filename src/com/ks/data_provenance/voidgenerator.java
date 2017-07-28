/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ks.data_provenance;


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.VCARD;
import Vocabularies.VoID;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.NsIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import java.util.Date;

import Vocabularies.PROV;

/**
 *
 * @author home
 */
public class voidgenerator {
    
    Model voidModel, provModel;
    public String dsURI, dsTitle, dsDescription, dsPublisher, dsSource, dsLicense, dsSparqlEndPoint;
    public Model generateVoidForModel(Model rdfModel)
    {
     
        voidModel = ModelFactory.createDefaultModel();
                
        voidModel.setNsPrefix("rdf", RDF.getURI());
        voidModel.setNsPrefix("rdfs", RDFS.getURI());
        voidModel.setNsPrefix("foaf", FOAF.NS);
        voidModel.setNsPrefix("DC", DCTerms.NS);
        voidModel.setNsPrefix("VCARD", VCARD.getURI()); 
        voidModel.setNsPrefix("void", VoID.getURI()); 
        

       
        Resource aRes = voidModel.createResource(dsURI);
        
        aRes.addProperty(RDF.type, VoID.Dataset);
        aRes.addProperty(DCTerms.title, dsTitle);
        aRes.addProperty(DCTerms.description, dsDescription);
        aRes.addProperty(DCTerms.publisher, dsPublisher);
        aRes.addProperty(DCTerms.title, dsTitle);
        aRes.addProperty(DCTerms.source, dsSource);
        aRes.addProperty(DCTerms.language, "English");
        aRes.addProperty(DCTerms.format, "RDF");
        
        aRes.addProperty(VoID.propertyForName("sparqlEndpoint"), dsSparqlEndPoint);
        aRes.addProperty(VoID.propertyForName("feature"), "application/rdf+xml");
        
        
        //vocabularies
        NsIterator it = rdfModel.listNameSpaces();
        while(it.hasNext())
        {
            aRes.addProperty(VoID.propertyForName("vocabulary"), it.nextNs());
        }
        
        int triples = rdfModel.listStatements().toList().toArray().length;
        int subjects = rdfModel.listSubjects().toList().toArray().length;
        int objects = rdfModel.listObjects().toList().toArray().length;
        
        
        aRes.addProperty(VoID.propertyForName("triples"), ""+triples, XSDDatatype.XSDinteger);

        aRes.addProperty(VoID.propertyForName("distinctSubjects"), ""+subjects, XSDDatatype.XSDinteger);
        aRes.addProperty(VoID.propertyForName("distinctObjects"), ""+objects, XSDDatatype.XSDinteger);
        
        
        //linksets
        Resource dbRes = voidModel.createResource("dataset1");
        dbRes.addProperty(FOAF.homepage, "http://dbpedia.org/");
        dbRes.addProperty(RDF.type, VoID.Dataset);
        dbRes.addProperty(DCTerms.title, "DBpedia");
        dbRes.addProperty(DCTerms.description, "DBpedia aims to provide structured information that already present in Wikipedia. The extracted information are modelled using RDF & URI and published into Web of Data.");
        dbRes.addProperty(DCTerms.publisher, "University of Leipzig, Freie Universität Berlin, OpenLink Software");
        dbRes.addProperty(DCTerms.license, "GNU General Public License");
        
        Resource links1 = voidModel.createResource("link-dataset1");
        links1.addProperty(RDF.type, VoID.Linkset);
        links1.addProperty(VoID.propertyForName("linkPredicate"), RDFS.seeAlso);
        links1.addProperty(VoID.propertyForName("target"), aRes);
        links1.addProperty(VoID.propertyForName("target"), dbRes);

        
        return voidModel;
    }   
    
    public Model generateProvenanceUsingPROVForModel(Model rdfModel)
    {
     
        provModel = ModelFactory.createDefaultModel();
                
        provModel.setNsPrefix("rdf", RDF.getURI());
        provModel.setNsPrefix("rdfs", RDFS.getURI());
        provModel.setNsPrefix("foaf", FOAF.NS);
        provModel.setNsPrefix("DC", DCTerms.NS);
        provModel.setNsPrefix("VCARD", VCARD.getURI()); 
        provModel.setNsPrefix("prov", PROV.getURI()); 
        provModel.setNsPrefix("void", VoID.getURI()); 
        

        //activity
        Resource conversion = provModel.createResource("dataConversion");
        conversion.addProperty(RDF.type, PROV.resourceForName("Activity"));
        
       
        Resource aRes = provModel.createResource(dsURI);
        
        aRes.addProperty(RDF.type, PROV.resourceForName("Entity"));
        
        aRes.addProperty(PROV.propertyForName("wasGeneratedBy"), conversion);
        aRes.addProperty(PROV.propertyForName("hadPrimarySource"), dsSource);

        
        aRes.addProperty(DCTerms.title, dsTitle);
        aRes.addProperty(DCTerms.description, dsDescription);
        aRes.addProperty(DCTerms.publisher, dsPublisher);
        aRes.addProperty(DCTerms.language, "English");
        aRes.addProperty(DCTerms.format, "RDF");
        
        aRes.addProperty(VoID.propertyForName("sparqlEndpoint"), dsSparqlEndPoint);
        aRes.addProperty(VoID.propertyForName("feature"), "application/rdf+xml");
        

        //vocabularies
        NsIterator it = rdfModel.listNameSpaces();
        while(it.hasNext())
        {
            aRes.addProperty(VoID.propertyForName("vocabulary"), it.nextNs());
        }
        
        int triples = rdfModel.listStatements().toList().toArray().length;
        int subjects = rdfModel.listSubjects().toList().toArray().length;
        int objects = rdfModel.listObjects().toList().toArray().length;
        
        
        aRes.addProperty(VoID.propertyForName("triples"), ""+triples, XSDDatatype.XSDinteger);

        aRes.addProperty(VoID.propertyForName("distinctSubjects"), ""+subjects, XSDDatatype.XSDinteger);
        aRes.addProperty(VoID.propertyForName("distinctObjects"), ""+objects, XSDDatatype.XSDinteger);
        
        
        //linksets
        Resource dbRes = provModel.createResource("dataset1");
        dbRes.addProperty(RDF.type, PROV.resourceForName("Entity"));
        
                
        dbRes.addProperty(FOAF.homepage, "http://dbpedia.org/");
        dbRes.addProperty(DCTerms.title, "DBpedia");
        dbRes.addProperty(DCTerms.description, "DBpedia aims to provide structured information that already present in Wikipedia. The extracted information are modelled using RDF & URI and published into Web of Data.");
        dbRes.addProperty(DCTerms.publisher, "University of Leipzig, Freie Universität Berlin, OpenLink Software");
        dbRes.addProperty(DCTerms.license, "GNU General Public License");
        
        
        Resource viafRes = provModel.createResource("dataset2");

        viafRes.addProperty(RDF.type, PROV.resourceForName("Entity"));

        viafRes.addProperty(FOAF.homepage, "http://viaf.org/ontology/1.1/#");
        viafRes.addProperty(DCTerms.title, "Virtual International Authority File (VIAF) Ontology");
        viafRes.addProperty(DCTerms.description, "VIAF, implemented and hosted by OCLC, is a joint project of several national libraries plus selected regional and trans-national library agencies.");
        viafRes.addProperty(DCTerms.publisher, "OCLC");
        viafRes.addProperty(DCTerms.license, "© 2010-2012 OCLC");
        
        
//        Resource links1 = provModel.createResource("link-dataset1");
//        links1.addProperty(RDF.type, VoID.Linkset);
//        links1.addProperty(VoID.propertyForName("linkPredicate"), RDFS.seeAlso);
//        links1.addProperty(VoID.propertyForName("target"), aRes);
//        links1.addProperty(VoID.propertyForName("target"), dbRes);
        
        
        conversion.addProperty(PROV.propertyForName("used"), aRes);
        conversion.addProperty(PROV.propertyForName("used"), dbRes);
        conversion.addProperty(PROV.propertyForName("used"), viafRes);

        conversion.addProperty(PROV.propertyForName("generatedAtTime"), new Date().toString());
        
        Resource univ = provModel.createResource("CIRM");
        univ.addProperty(RDF.type, PROV.propertyForName("Organization"));
        univ.addProperty(FOAF.name, "Centre for Information Resource Management");
        univ.addProperty(FOAF.mbox, "support@klyuniv.ac.in");
        
        conversion.addProperty(PROV.propertyForName("wasAssociatedWith"), univ);

        

        
        return provModel;
    }   
}
