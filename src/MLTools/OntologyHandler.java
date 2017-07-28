/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MLTools;

/**
 * Copyright (c) 2012 Arup Sarkar
 * @author Arup Sarkar
 */

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.sparql.function.library.e;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.RDFS;


/*
 * Please don't confuse this class OntologyHandler with tellarup.handler.HandleOntology.
 * Two are different classes. First one comes under <tt>com.tellarup.mapping.util</tt> package
 * while the second one comes under the <tt>tellarup.handler</tt> package in <i>ontomatch-1.1.jar</i> 
 */
public class OntologyHandler 
{
	private HashMap<Integer, String> allClass = null;
	private HashMap<Integer, OntClass> allOntClass = null;
	private HashMap<Integer, OntClass> superClasses = null;
	private HandleOntology ho  = null;
	
	public OntologyHandler()
	{
		ho = new HandleOntology();
	}
	
	
	public void prepareAllClass(String ontURI, String ontURL) throws FileNotFoundException, Exception
	{
		//HandleOntology ho = new HandleOntology();
		ho.setFileURI(ontURI);
		ho.setFileURI(ontURL);
		allClass = ho.getAllClass();
	}
	
	public void prepareAllOntClass(String ontURI, String ontURL) throws FileNotFoundException, Exception
	{
		//HandleOntology ho = new HandleOntology();
		ho.setFileURI(ontURI);
		ho.setFileLocation(ontURL);
		allOntClass = ho.getAllOntClass();
		
	}
	
	public HashMap<Integer, String> getAllClass()
	{
		return allClass;
	}
	
	public HashMap<Integer, OntClass> getAllOntClass()
	{
		return allOntClass;
	}
	
	public OntModel getOntologyModel() throws Exception
	{
		return ho.getOntModel();
	}
	public void getParents(OntClass c)
	{
		int counter = 1;
		//String cls = null;
		OntClass c1 = null;
		superClasses = new HashMap<Integer, OntClass>();
		HashSet<OntClass> cls = new HashSet<OntClass>();
		cls = this.getImmediateParent(c);
		Iterator<OntClass> it = cls.iterator();
		while(it.hasNext())
		{
			c1 = it.next();
			if(c1.getLocalName()!="Thing")
				superClasses.put(counter++, c1);
		}
		//System.out.println("Class:"+c);
		
		
	}
	
	public HashSet<OntClass> getImmediateParent(OntClass c)
	{
		OntClass c1 = null;
		//HashMap<Integer, OntClass> superClasses = new HashMap<Integer, OntClass>();
		HashSet<OntClass> cls = new HashSet<OntClass>();
		ExtendedIterator eit = c.listSuperClasses();
		while(eit.hasNext())
		{
			c1 = (OntClass)eit.next();
			cls.add(c1);
			//if(c1.getLocalName()!="Thing")
				//superClasses.put(counter++, c1);
			//System.out.println(eit.next().getLocalName());
		}
		
		return cls;
		
	}
	/**************************Test Unit******************************/
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
