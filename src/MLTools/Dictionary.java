/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MLTools;

import java.util.HashMap;
import java.io.File;
import java.io.File;

/**
 *
 * @author KSharma
 */
public class Dictionary {
    
    private HashMap<String, Object> mapTable;
    
    public Dictionary()
    {
        this.mapTable = new HashMap<String, Object>();
    }
    
    public void setValueForKey(Object value, String key)
    {
        this.mapTable.put(key, value);
    }
    
    public void setValuesForKeys(Object values[], String keys[])
    {
        if(values.length == keys.length)
        {
            for(int i = 0; i < values.length; i++)
            {
                Object value = values[i];
                String key = keys[i];
                
                this.mapTable.put(key, value);
            }
        }
    }
    
    public Object valueForKey(String key)
    {
        Object value;
        value = this.mapTable.get(key);
        
        return value;
    }
    
    public void removeObjectForKey(String key)
    {
        Object value;
        value = this.mapTable.remove(key);
    }
    
}
