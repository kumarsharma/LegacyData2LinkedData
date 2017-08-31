/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vocabularies;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;

/**
 *
 * The goal of MarcOnt bibliographic ontology (see http://www.marcont.org/ontology/2.1/ for more information) is to provide a uniform bibliographic description format. It should capture concepts from existing formats such as BibTEX, Dublin Core, MARC21. As the process of development of such an ontology is complicated it should involve a community of domain experts sharing their knowledge and experience, together building community ontology with tools such as MarcOnt Portal96.
The ontology It is to be used in JeromeDL (see Chapter 7) as a format capturing bibliographic descriptions of the resources. The ontology is also being used as a mediation format in MarcOnt Mediation Services97. With use of MarcOnt ontology one can transform bibliographic descriptions of the resources between supported formats (BibTEX, Dublin Core, MARC21) and MarcOnt.
When talking about ontologies, classes are often identified with objects in the real world. Their names often reflect this approach (e.g. Person class in the FOAF ontology represents a human being). Because of that creation of list of classes in the ontology and their hierarchy seems to be straightforward. The main problem occurs when one should build a model of particular domain of interest on multiple models existing in this domain. This is a case when building a bibliographic ontology. Such an ontology should be built on the base of existing metadata standards (e.g. BibTEX, DublinCore etc.). This implies the complicated process of achieving consensus on the ontology.

 */


public class marcont  extends Object {
    
    // URI for vocabulary elements
    protected static final String uri = "http://www.marcont.org/ontology/2.1#";
    
    // Return URI for vocabulary elements
    public static String getURI(  )
    {
        return uri;
    }    
    
    public static Property propertyForName(String name)
    {
        return new PropertyImpl(uri, name);
    }
    
    // Define the property labels and objects
    
           static final String   kMarcOnt = "MarcOnt";
    public static       Resource MarcOnt = null;
  

           static final String   khasAbstract = "hasAbstract";
    public static       Property hasAbstract = null;     
 
           static final String   khasAddress = "hasAddress";
    public static       Property hasAddress = null;
    
           static final String   khasAffiliation = "hasAffiliation";
    public static       Property hasAffiliation = null;
    
    static final String   khasAuthor = "hasAuthor";
    public static       Property hasAuthor = null;
 
  
           static final String   khasBeginDate = "hasBeginDate";
    public static       Property hasBeginDate = null;
  
            static final String   khasContents = "hasContents";
    public static       Property hasContents = null;
    
           
            static final String   khasContributor = "hasContributor";
    public static       Property hasContributor = null;

            static final String   khasCoverage = "hasCoverage";
    public static       Property hasCoverage = null;

            static final String   khasCreator = "hasCreator";
    public static       Property hasCreator = null;

            static final String   khasDCMIType = "hasDCMIType";
    public static       Property hasDCMIType = null;

            static final String   khasDOI = "hasDOI";
    public static       Property hasDOI = null;
    
            static final String   khasDate = "hasDate";
    public static       Property hasDate = null;

            static final String   khasDescription = "hasDescription";
    public static       Property hasDescription = null;

            static final String   khasDomain = "hasDomain";
    public static       Property hasDomain = null;

            static final String   khasEdition = "hasEdition";
    public static       Property hasEdition = null;
    
            static final String   khasEditor = "hasEditor";
    public static       Property hasEditor = null;
    
            static final String   khasEndDate = "hasEndDate";
    public static       Property hasEndDate = null;
    
            static final String   khasHumanCreator = "hasHumanCreator";
    public static       Property hasHumanCreator = null;

            static final String   khasISBN = "hasISBN";
    public static       Property hasISBN = null;

            static final String   khasISSN = "hasISSN";
    public static       Property hasISSN = null;
    

            static final String   khasIdentifier = "hasIdentifier";
    public static       Property hasIdentifier = null;
    

            static final String   khasJournal = "hasJournal";
    public static       Property hasJournal = null;

            static final String   khasKeyword = "hasKeyword";
    public static       Property hasKeyword = null;

            static final String   khasMonth = "hasMonth";
    public static       Property hasMonth = null;
    
            static final String   khasNote = "hasNote";
    public static       Property hasNote = null;

            static final String   khasNumber = "hasNumber";
    public static       Property hasNumber = null;

            static final String   khasOrder = "hasOrder";
    public static       Property hasOrder = null;

            static final String   khasOrganization = "hasOrganization";
    public static       Property hasOrganization = null;

            static final String   khasOriginalPublicationMedium = "hasOriginalPublicationMedium";
    public static       Property hasOriginalPublicationMedium = null;

            static final String   khasPages = "hasPages";
    public static       Property hasPages = null;
    
            static final String   khasPagesFrom = "hasPagesFrom";
    public static       Property hasPagesFrom = null;
    
    
            static final String   khasPagesTo = "hasPagesTo";
    public static       Property hasPagesTo = null;

            static final String   khasPublisher = "hasPublisher";
    public static       Property hasPublisher = null;

            static final String   khasRelatedEvent = "hasRelatedEvent";
    public static       Property hasRelatedEvent = null;
    
            static final String   khasReview = "hasReview";
    public static       Property hasReview = null;
            static final String   khasReviewDate = "hasReviewDate";
    public static       Property hasReviewDate = null;

            static final String   khasReviewer = "hasReviewer";
    public static       Property hasReviewer = null;
            static final String   khasReviewComment = "hasReviewComment";
    public static       Property hasReviewComment = null;
    
            static final String   khasSchool = "hasSchool";
    public static       Property hasSchool = null;
    
            static final String   khasSeries = "hasSeries";
    public static       Property hasSeries = null;
    
            static final String   khasSource = "hasSource";
    public static       Property hasSource = null;
    
    
            static final String   khasSponsor = "hasSponsor";
    public static       Property hasSponsor = null;

            static final String   khasTitle = "hasTitle";
    public static       Property hasTitle = null;

            static final String   khasTopic = "hasTopic";
    public static       Property hasTopic = null;
    
            static final String   khasURL = "hasURL";
    public static       Property hasURL = null;
            
            static final String   khasVolume = "hasVolume";
    public static       Property hasVolume = null;
            static final String   khasYear = "hasYear";
    public static       Property hasYear = null;
            static final String   khowPublished = "howPublished";
    public static       Property howPublished = null;
    

            static final String   kisPartOf = "isPartOf";
    public static       Property isPartOf = null;
    
    
            static final String   kisPeerOf = "isPeerOf";
    public static       Property isPeerOf = null;
    
            static final String   kpresentedAt = "presentedAt";
    public static       Property presentedAt = null;

    static final String   kpublishedIn = "publishedIn";
    public static       Property publishedIn = null;
    
    static final String   khasControlNumber = "hasControlNumber";
    public static       Property hasControlNumber = null;

    // Instantiate the properties and the Dataset
    static {
        try {
            
            // Instantiate the properties
            
            hasAbstract = new PropertyImpl(uri+khasAbstract);
            hasAddress = new PropertyImpl(uri, khasAddress);
            hasAffiliation = new PropertyImpl(uri, khasAffiliation);
            hasAuthor = new PropertyImpl(uri, khasAuthor);
            hasBeginDate = new PropertyImpl(uri, khasBeginDate);
            hasContents = new PropertyImpl(uri, khasContents);    
            hasContents = new PropertyImpl(uri, khasContents);
            hasCoverage = new PropertyImpl(uri, khasCoverage);
            hasCreator = new PropertyImpl(uri, khasCreator);
            hasDCMIType = new PropertyImpl(uri, khasDCMIType);
            hasDOI = new PropertyImpl(uri, khasDOI);
              
            MarcOnt = new ResourceImpl(uri, kMarcOnt);
            hasDescription = new PropertyImpl(uri, khasDescription);
            hasDate = new PropertyImpl(uri, khasDate);
            hasDescription = new PropertyImpl(uri, khasDescription);
            
            hasDomain = new PropertyImpl(uri, khasDomain);
            
            hasEdition = new PropertyImpl(uri, khasEdition);
            hasEditor = new PropertyImpl(uri, khasEditor);
            hasEndDate = new PropertyImpl(uri, khasEndDate);
            hasHumanCreator = new PropertyImpl(uri, khasHumanCreator);
            hasISBN = new PropertyImpl(uri, khasISBN);
            hasISSN = new PropertyImpl(uri, khasISSN);
            
            hasIdentifier = new PropertyImpl(uri, khasIdentifier);
            
            hasJournal = new PropertyImpl(uri, khasJournal);
            hasKeyword = new PropertyImpl(uri, khasKeyword);
            hasMonth = new PropertyImpl(uri, khasMonth);
            hasNote = new PropertyImpl(uri, khasNote);
            hasNumber = new PropertyImpl(uri, khasNumber);
            hasOrder = new PropertyImpl(uri, khasOrder);
              
            hasOrganization = new PropertyImpl(uri, khasOrganization);
            hasOriginalPublicationMedium = new PropertyImpl(uri, khasOriginalPublicationMedium);
            hasPages = new PropertyImpl(uri, khasPages);
            
            hasPagesFrom = new PropertyImpl(uri, khasPagesFrom);           
            
            hasPagesTo = new PropertyImpl(uri, khasPagesTo);
            hasPublisher = new PropertyImpl(uri, khasPublisher);
            hasRelatedEvent = new PropertyImpl(uri, khasRelatedEvent);
            hasReview = new PropertyImpl(uri, khasReview);
            hasReviewDate = new PropertyImpl(uri, khasReviewDate);
             
            hasReviewer = new PropertyImpl(uri, khasReviewer);
            hasReviewDate = new PropertyImpl(uri, khasReviewDate);
            
            hasReviewer = new PropertyImpl(uri, khasReviewer);
            hasReviewComment = new PropertyImpl(uri, khasReviewComment);
            hasSchool = new PropertyImpl(uri, khasSchool);
            hasSeries = new PropertyImpl(uri, khasSeries);
            hasSource = new PropertyImpl(uri, khasSource);
            
            hasSponsor = new PropertyImpl(uri, khasSponsor);
            hasTitle = new PropertyImpl(uri, khasTitle);
            
            hasTopic = new PropertyImpl(uri, khasTopic);
            
            hasURL = new PropertyImpl(uri, khasURL);
            hasVolume = new PropertyImpl(uri, khasVolume);
            hasYear = new PropertyImpl(uri, khasYear);
            howPublished = new PropertyImpl(uri, khowPublished);
            
            isPartOf = new PropertyImpl(uri, kisPartOf);
            isPeerOf = new PropertyImpl(uri, kisPeerOf);
            presentedAt = new PropertyImpl(uri, kpresentedAt);
            publishedIn = new PropertyImpl(uri, kpublishedIn);
            hasControlNumber = new PropertyImpl(uri, khasControlNumber);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}