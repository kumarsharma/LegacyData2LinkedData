/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MLTools;

import java.io.Serializable;
/**
 *
 * @author user
 */
public class RDFTriple implements Serializable {
    
    private String subject, predicate, object;
    
    public RDFTriple(String s, String p, String o)
    {
        this.subject = s;
        this.predicate = p;
        this.object = o;
    }
    
    public String getSubject(){return this.subject;};
    public String getPredicate(){return this.predicate;};
    public String getObject(){return this.object;};
    
    public void setSubject(String s){this.subject=s;};
    public void setPredictae(String p){this.predicate=p;};
    public void setObject(String o){this.object=o;};
    
    public RDFTriple(){}
}
