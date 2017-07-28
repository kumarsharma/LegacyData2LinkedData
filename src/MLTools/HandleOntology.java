/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MLTools;

/**
 * 
 */
import java.io.FileNotFoundException;
import java.util.HashMap;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;


import org.openrdf.elmo.codegen.OntologyConverter;
/**
 * (c) 2011 Arup Sarkar
 * @author Arup Sarkar
 *
 */
public class HandleOntology 
{
	//FileInputStream fis1 = null, fis2 = null;
	
	private String file_uri = "";
	private String file_url = "";
	
	public void setFileURI(String uri)
	{
            
		this.file_uri = uri;
	}
	public void setFileLocation(String url)
	{
		this.file_url = url;
	}
	
	public boolean clear()
	{
		this.setFileURI("");
		this.setFileLocation("");
		if(this.file_uri.equals("")&&this.file_url.equals(""))
			return true;
		return false;
	}
	
	public OntModel  getOntModel() throws Exception
	{
		OntModel om = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,null);
		 String fileuri = this.file_uri;
		 String fileurl = this.file_url;
		 
		 if(fileuri.equals("")||fileurl.equals(""))
		 {
			 throw new Exception("Set file uri and url with valid parameter.");
		 }
		 
		 om.getDocumentManager().addAltEntry(fileuri, fileurl);
		 om.read(this.file_uri);
		 
		 return om;
	}
	
	 public HashMap<Integer, String> getAllClass() throws FileNotFoundException, Exception
	 {
		 //fis1 = new FileInputStream(filenm);
		/* OntModel om = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,null);
		 String fileuri = this.file_uri;
		 String fileurl = this.file_url;
		 
		 if(fileuri.equals("")||fileurl.equals(""))
		 {
			 throw new InvalidURIAndURLException("Set file uri and url with valid parameter.");
		 }
		 
		 om.getDocumentManager().addAltEntry(fileuri, fileurl);
		 om.read(this.file_uri);
		 ExtendedIterator<OntClass> i = om.listNamedClasses();
		 HashMap<Integer, String> h1 = new HashMap<Integer, String>(); 
		 int counter = 1;
		 while(i.hasNext())
		 {
			 OntClass c = i.next();
			 h1.put(counter, c.getLocalName());
			 
			 counter++;
			// System.out.println("class: "+c.getLocalName());
		 }*/
		 HashMap<Integer, OntClass> h1 = new HashMap<Integer, OntClass>();
		 HashMap<Integer, String> h2 = new HashMap<Integer, String>();
		 h1 = this.getAllOntClass();
		 for(int i = 1;i <= h1.size(); i++)
		 {
			 h2.put(i, h1.get(i).getLocalName());
		 }
		 return h2;
	 }
	
	 public HashMap<Integer, OntClass> getAllOntClass() throws FileNotFoundException, Exception
	 {
		 //fis1 = new FileInputStream(filenm);
		 /*OntModel om = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,null);
		 String fileuri = this.file_uri;
		 String fileurl = this.file_url;
		 
		 if(fileuri.equals("")||fileurl.equals(""))
		 {
			 throw new InvalidURIAndURLException("Set file uri and url with valid parameter.");
		 }
		 
		 om.getDocumentManager().addAltEntry(fileuri, fileurl);
		 om.read(this.file_uri);*/
		 OntModel om = this.getOntModel();
		 ExtendedIterator i = om.listNamedClasses();
		 HashMap<Integer, OntClass> h1 = new HashMap<Integer, OntClass>(); 
		 int counter = 1;
		 while(i.hasNext())
		 {
			 //OntClass c = i.next();
//			 h1.put(counter, i.next());
			 
			 counter++;
			// System.out.println("class: "+c.getLocalName());
		 }
		 return h1;
	 }
	 
	/* public void getOntProp() throws InvalidURIAndURLException //for testing change when req.
	 {
		 OntModel om = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,null);
		 String fileuri = this.file_uri;
		 String fileurl = this.file_url;
		 
		 if(fileuri.equals("")||fileurl.equals(""))
		 {
			 throw new InvalidURIAndURLException("Set file uri and url with valid parameter.");
		 }
		 
		 om.getDocumentManager().addAltEntry(fileuri, fileurl);
		 om.read(this.file_uri);
		 ExtendedIterator<OntClass> i = om.listNamedClasses();
		 HashMap<Integer, OntClass> h1 = new HashMap<Integer, OntClass>(); 
		 int counter = 1;
		 while(i.hasNext())
		 {
			 //OntClass c = i.next();
			 h1.put(counter, i.next());
			 
			 counter++;
			// System.out.println("class: "+c.getLocalName());
		 }
		 System.out.println(h1.get(1).toString());
		Property p = om.getProperty(h1.get(1).toString());
		System.err.println(p.hasDomain(h1.get(1)));
	 }*/
	/******************Test Unit*****************/
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws InvalidURIAndURLException 
	 */
	public static void main(String[] args) throws FileNotFoundException, Exception 
	{
		// TODO Auto-generated method stub
		
		HandleOntology h = new HandleOntology();
		//h.setFileURI("http://www.klyuniv.ac.in/ontologies/kalyani.owl");
		//h.setFileLocation("file:ontology/kalyani/kalyani.owl");
		h.setFileURI("http://purl.org/ontology/bibo/");
		h.setFileLocation("http://localhost:8080/ontology/dbpedia_3.7.owl");
		//HashMap<Integer, String> h2 = h.getAllClass();
		HashMap<Integer, OntClass> h2 = h.getAllOntClass();
		int size = h2.size();
		System.out.println("HashMap size = "+size);
                
                OntologyConverter oc = new OntologyConverter();
                
                
		
		String s;
		OntClass c = null;
		for(int i=1;i<=size;i++)
		{
			System.out.println("Class "+i+": "+h2.get(i));
			
			
			if((s = (c = h2.get(i)).getLocalName()).equals("Racer"))
			{
				System.out.println(s);
				System.out.println(c.getLabel("en"));
				System.out.println(c.getComment("en"));
			}
		}
		//h.getOntProp();

	}

}
