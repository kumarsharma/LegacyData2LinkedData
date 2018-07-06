/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkDataset;

import Network.URLRequest;
import com.googlecode.fascinator.vocabulary.DC;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;
import org.w3c.dom.*;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

//import java.io.in;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.ArrayList;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.googlecode.fascinator.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.VCARD;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.OWL;

import info.debatty.java.stringsimilarity.NGram;
/**
 *
 * @author u
 */
public class URiLookup {
    
    public URiLookup()
    {
        
    }
    
    public static String lookUpDBPediaURiForResourceTitle(String resourceTitle, String queryClass)
    {
        System.out.println("Looking UP: " + resourceTitle);
        
        String resourceURI = null;
       //http://lookup.dbpedia.org/api/search.asmx/KeywordSearch?QueryClass=place&QueryString=West%20Bengal
        
        //first lookup using prefix search
        String urlFormat = "";
        String requestParams = "";
        resourceTitle = resourceTitle.replace(' ', '_');
        
        urlFormat = "http://lookup.dbpedia.org/api/search.asmx/PrefixSearch?";
        requestParams = "QueryClass=" + queryClass + "&MaxHits=1" + "&QueryString=" + resourceTitle;
        String searchResult = null;//URLRequest.fireGETRequest(urlFormat, requestParams);
        
       
        if(searchResult == null)
        {
            //lookup using keyword search         
             urlFormat = "http://lookup.dbpedia.org/api/search.asmx/KeywordSearch?";
             requestParams = "QueryClass=" + queryClass + "&QueryString=" + resourceTitle;
             searchResult = URLRequest.fireGETRequest(urlFormat, requestParams);
        }
        //parsing xml
        try
        {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            
            byte [] xmlDATA = searchResult.getBytes();
            ByteArrayInputStream in = new ByteArrayInputStream(xmlDATA);
            Document doc = docBuilder.parse(in);
            
            NodeList listOfURI = doc.getElementsByTagName("URI");
            System.out.println("List of URIs: " + listOfURI.getLength() + listOfURI.toString());
            for(int i = 0; i < listOfURI.getLength(); i++)
            {
                Node iNode = listOfURI.item(i);
                resourceURI = iNode.getTextContent();
                break;
            }
        }
        catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        
       // System.out.println("DBPedia Search result: "+searchResult);
        return resourceURI;
    }
    
    
     public List<String> LinkedURIsForResourceTitle(String resourceTitle, String queryClass, Resource res)
    {
        System.out.println("Looking UP: " + resourceTitle);
     
        List<String> resourceURIs = new ArrayList<String>();
        String resourceURI = null;
       //http://lookup.dbpedia.org/api/search.asmx/KeywordSearch?QueryClass=place&QueryString=West%20Bengal
        
        //first lookup using prefix search
        String urlFormat = "";
        String requestParams = "";
        resourceTitle = resourceTitle.replace(' ', '_');
        
        urlFormat = "http://lookup.dbpedia.org/api/search.asmx/PrefixSearch?";
        requestParams = "QueryClass=" + queryClass + "&MaxHits=1" + "&QueryString=" + resourceTitle;
        String searchResult = null;//URLRequest.fireGETRequest(urlFormat, requestParams);
       
        if(searchResult == null)
        {
            //lookup using keyword search         
             urlFormat = "http://lookup.dbpedia.org/api/search.asmx/KeywordSearch?";
             requestParams = "QueryClass=" + queryClass + "&QueryString=" + resourceTitle;
             searchResult = URLRequest.fireGETRequest(urlFormat, requestParams);
        }
        //parsing xml
        try
        {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            
            byte [] xmlDATA = searchResult.getBytes();
            ByteArrayInputStream in = new ByteArrayInputStream(xmlDATA);
            Document doc = docBuilder.parse(in);
            
            NodeList listOfURI = doc.getElementsByTagName("URI");
            System.out.println("List of URIs: " + listOfURI.getLength() + listOfURI.toString());
            for(int i = 0; i < listOfURI.getLength(); i++)
            {
                Node iNode = listOfURI.item(i);
                resourceURI = iNode.getTextContent();
                break;
            }
            
            if(resourceURI != null){
                
                String queryStr = "SELECT ?prop ?place WHERE { <"+resourceURI+"> ?prop ?place .}";
                Query query = QueryFactory.create(queryStr);
                // Remote execution.
                try {
                    
                    QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
                    // Set the DBpedia specific timeout.
                    ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
                    // Execute.
                    ResultSet rs = qexec.execSelect();
                    List<String> vars = rs.getResultVars();
                    Model model = ModelFactory.createDefaultModel();
                    
                    Resource qR = model.createResource(resourceURI);
                    while(rs.hasNext()){
                        
                        QuerySolution qs = rs.nextSolution();
                        String prop = qs.get(vars.get(0)).toString();
                        String obj = qs.get(vars.get(1)).toString();
                        qR.addProperty(new PropertyImpl(prop, ""), obj);
                    }
//                    ResultBinding bind = new ResultBinding(model, rs.nextBinding());
//                    URiLookup.writeModelToFile(model, "RDF/XML", "dbpedia");
                    //ResultSetFormatter.out(System.out, rs, query);
                    
                    
                    float measure = this.RDFResourceSimilarityMeasure(res, qR);
                    if(measure>0)
                    {
                         StmtIterator stmtItr1 = qR.listProperties(RDFS.seeAlso);
                         while(stmtItr1.hasNext()){
                             
                            resourceURIs.add(stmtItr1.nextStatement().getObject().toString());
                         }
                         
                         StmtIterator stmtItr2 = qR.listProperties(OWL.sameAs);
                         while(stmtItr2.hasNext()){
                             
                            resourceURIs.add(stmtItr2.nextStatement().getObject().toString());
                         }
                         resourceURIs.add(resourceURI);
                    }
                    
                } catch (Exception e) {
                }
        
            }
        }
        catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        
       // System.out.println("DBPedia Search result: "+searchResult);
        return resourceURIs;
    }
     
    private float RDFResourceSimilarityMeasure(Resource r1, Resource r2)
    {
        List<Statement> r1States = r1.listProperties(new PropertyImpl("http://purl.org/dc/terms/type")).toList();
        r1States.addAll(r1.listProperties(VCARD.Locality).toList());
        r1States.addAll(r1.listProperties(new PropertyImpl("http://RDVocab.info/Elements/otherTitleInformation")).toList());
        r1States.addAll(r1.listProperties(new PropertyImpl("http://RDVocab.info/Elements/keyTitle")).toList());
        r1States.addAll(r1.listProperties(new PropertyImpl("http://RDVocab.info/Elements/placeOfPublication")).toList());
        r1States.addAll(r1.listProperties(new PropertyImpl("http://www.marcont.org/ontology/2.1#statementOfResponsibility")).toList());
        
        List<Statement> r2States = r2.listProperties(RDF.type).toList();
        r2States.addAll(r2.listProperties(RDFS.comment).toList());
        r2States.addAll(r2.listProperties(RDFS.label).toList());

        List<Statement> unionR = new ArrayList<>();
        unionR.addAll(r1States);
        for(Statement s : r2States)
        {
            if(!this.containsStatementInStatements(s, r1States, true))
                unionR.add(s);
        }

        List<Statement> intersectionR = new ArrayList<>();
        for(Statement s:r1States)
        {
            if(this.containsStatementInStatements(s, r2States, false))
                intersectionR.add(s);
        }

        float measure = (float)intersectionR.size()/unionR.size();
        return measure;
    }


    private boolean containsStatementInStatements(Statement s, List<Statement>list, boolean keepFrequentCount)
    {
        boolean contains = false;
        for(Statement s2:list)
        {
            double measure = this.RDFStatementSimilarityMeasure(s, s2);
            if(measure>0.10)
            {
                contains = true;
                break;
            }
        }

        return contains;
    }

    private double RDFStatementSimilarityMeasure(Statement s1, Statement s2)
    {
        /*if(s1.getPredicate().getLocalName().equalsIgnoreCase("type") || s1.getPredicate().getLocalName().equalsIgnoreCase("rdf:type"))
        {
            return 0;
        }*/

        int propertyWeight = this.RDFProperySimilarity(s1.getPredicate(), s2.getPredicate()) ? 1 : 0;
        NGram ngram = new NGram(2);

        String value1;
        if(s1.getObject().isLiteral())
            value1 = s1.getObject().asLiteral().getString();
        else
            value1 = s1.getObject().asResource().toString();

        String value2;
        if(s2.getObject().isLiteral())
            value2 = s2.getObject().asLiteral().getString();
        else
            value2 = s2.getObject().asResource().toString();

        double valueSimilarity = ngram.similarity(value1, value2);  
        return valueSimilarity;// * propertyWeight; ignore property similarity for this time

    }

    private boolean RDFProperySimilarity(Property p1, Property p2)
    {
        return p1.getURI().equalsIgnoreCase(p2.getURI()) && p1.getLocalName().equalsIgnoreCase(p2.getLocalName());
    }
     
     public static void writeModelToFile(Model m, String format, String fileName)
    {
        try{
            String outputPath = "/Users/user/Documents/RDFStore/FileStore/RDFXML/";
            java.io.FileOutputStream fout = null;
            if(format.equalsIgnoreCase("RDF/XML"))
                fout = new java.io.FileOutputStream(outputPath+fileName+".rdf");
            else if(format.equalsIgnoreCase("N3"))
                fout = new java.io.FileOutputStream(outputPath+fileName+".n3");
            else if(format.equalsIgnoreCase("TURTLE") || format.equalsIgnoreCase("TTL"))
                fout = new java.io.FileOutputStream(outputPath+fileName+".ttl");
            else if(format.equalsIgnoreCase("N-TRIPLES") || format.equalsIgnoreCase("NT") || format.equalsIgnoreCase("N-TRIPLE"))
                fout = new java.io.FileOutputStream(outputPath+fileName+".nt");
            
            if(format.equalsIgnoreCase("RDF/XML"))
                m.write(fout, "RDF/XML");
            else if(format.equalsIgnoreCase("N3"))
                m.write(fout, "N3");
            else if(format.equalsIgnoreCase("N-TRIPLES") || format.equalsIgnoreCase("NT") || format.equalsIgnoreCase("N-TRIPLE"))
                m.write(fout, "N-TRIPLE");
            else if(format.equalsIgnoreCase("TURTLE") || format.equalsIgnoreCase("TTL"))
                m.write(fout, "TURTLE");
            else 
                m.write(fout, "RDF/XML");
        }catch(Exception ie){}
    }
    
     public static String lookUpVIAFURiForSearchTerm(String searchTerm)
    {
        System.out.println("Looking UP: " + searchTerm);
        
        String resourceURI = null;
       //http://lookup.dbpedia.org/api/search.asmx/KeywordSearch?QueryClass=place&QueryString=West%20Bengal
        
        //first lookup using prefix search
        String urlFormat = "";
        String requestParams = "";
        
        urlFormat = "http://viaf.org/viaf/search";
        
        requestParams = "query=cql.any+all+%22" + searchTerm + "%22+&maximumRecords=1&startRecord=1&sortKeys=holdingscount&httpAccept=application/rss%2bxml";
        
        String searchResult = null;//URLRequest.fireGETRequest(urlFormat, requestParams);
            
        //lookup using 
        searchResult = URLRequest.fireGETRequest(urlFormat, requestParams);
        //parsing xml
        try
        {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            
            byte [] xmlDATA = searchResult.getBytes();
            ByteArrayInputStream in = new ByteArrayInputStream(xmlDATA);
            Document doc = docBuilder.parse(in);
            
            NodeList listOfURI = doc.getElementsByTagName("link");
            
            System.out.println("List of VIAF URIs: " + listOfURI.getLength() + listOfURI.toString());
            for(int i = 1; i < listOfURI.getLength(); i++)
            {
                Node iNode = listOfURI.item(i);
                resourceURI = iNode.getTextContent();
                
                System.out.println("VIAF URI: " + resourceURI);
                break;
            }
        }
        catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        
       // System.out.println("DBPedia Search result: "+searchResult);
        return resourceURI;
    }
    
    
}
