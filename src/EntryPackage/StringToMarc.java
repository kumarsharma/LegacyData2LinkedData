/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntryPackage;

import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Leader;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;
import static org.marc4j.marc.impl.Verifier.isControlField;

/**
 *
 * @author Kumar Sharma
 */
public class StringToMarc {
    
    private Record record;

    private final MarcFactory factory;

    public StringToMarc()
    {
        factory = MarcFactory.newInstance();
    }
     
    public Record recordFromString(String marcString)
    {
        record = factory.newRecord();
        Leader leader = null;
        String parts[] = marcString.split("\n");
        try{
            leader = factory.newLeader(parts[0].substring(7));
            record.setLeader(leader);
        }catch(Exception e)
        {
            System.err.println("Error at LEADER Parsing for data: "+marcString);
        }
        
        int i = 0;
        for(String field : parts)
        {
            if(i>0)
            {
                String tag = field.substring(0, 3);
                String data = field.substring(4, field.length());
                
                if(isControlField(tag))
                {
                    ControlField cField = factory.newControlField();
                    cField.setTag(tag);
                    cField.setData(data);
                    record.addVariableField(cField);
                }
                else
                {
                    char ind1 = data.charAt(0);
                    char ind2 = data.charAt(1);
                    
                    String realData = data.substring(2);
                    String[] subfieldsData = realData.split("\\$");
                    
                    DataField df = factory.newDataField(tag, ind2, ind1);
                    for(String sf : subfieldsData)
                    {
                        if(sf.length()>2)
                        {
                            char sfCode = sf.charAt(0);
                            String sfData = sf.substring(1);
                            df.addSubfield(factory.newSubfield(sfCode, sfData)); 
                        }
                    }                            
                    record.addVariableField(df);
                }
            }
            i++;
        }
        
        return record;
    }
}
