/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntryPackage;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.lang.String.valueOf;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.marc4j.converter.CharConverter;
import org.marc4j.converter.impl.AnselToUnicode;
import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Leader;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.VariableField;
import org.marc4j.marc.impl.Verifier;
import org.marc4j.MarcException;
import org.marc4j.Constants;
import java.nio.charset.Charset;
import static java.nio.charset.Charset.forName;
import static org.marc4j.Constants.FT;
import static org.marc4j.Constants.RT;
import static org.marc4j.Constants.US;
import static org.marc4j.marc.MarcFactory.newInstance;
import static org.marc4j.marc.impl.Verifier.isControlField;

/**
 *
 * @author user
 */

public class MarcStringReader {

    private Record record;

    private MarcFactory factory;

    private String encoding = "ISO8859_1";

    private boolean override = false;
      
    private CharConverter converterAnsel = null;
    /**
     * Returns the next record in the iteration.
     *
     * @param marcString
     * @return Record - the record object
     */
    
    public MarcStringReader()
    {
        factory = newInstance();
    }
    
    public Record recordFromString(String marcString)
    {
        record = factory.newRecord();
        String parts[] = marcString.split("\n");
        
        if(parts.length>0)
        {
            if(parts[0].length()>20)
            {
                String ldrStr = parts[0].substring(7);
                System.out.println("LEADER String: "+ldrStr);
                Leader leader = factory.newLeader(parts[0].substring(7));
                record.setLeader(leader);
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
        }
        
        return record;
    }
    
    public Record parseString(String marcString)
    {
        record = factory.newRecord();

        try {

            byte[] utf8bytes = marcString.substring(7).getBytes();
            Charset utf8charset = forName("UTF-8");
            Charset iso88591charset = forName("ISO-8859-1");

            String string = new String ( utf8bytes, utf8charset );
            // "When I do a getbytes(encoding) and "
            byte[] iso88591bytes = string.getBytes(iso88591charset);

            byte[] byteArray = new byte[24];
            byteArray = string.getBytes(iso88591charset);
            int recordLength = parseRecordLength(byteArray);
            
            utf8bytes = marcString.substring(28).getBytes();
            string = new String ( utf8bytes, utf8charset );
            
            byte[] recordBuf = string.getBytes(iso88591charset);
            parseRecord(record, byteArray, recordBuf, recordLength);
            return(record);
        }
        catch (EOFException e) {
            throw new MarcException("Premature end of file encountered", e);
        }
        catch (IOException e) {
            throw new MarcException("an error occured reading input", e);
        }  
    }
   
    private void parseRecord(Record record, byte[] byteArray, byte[] recordBuf, int recordLength)
    {
        Leader ldr;
        ldr = factory.newLeader();
        ldr.setRecordLength(recordLength);
        int directoryLength=0;
       
        try {               
            parseLeader(ldr, byteArray);
            directoryLength = ldr.getBaseAddressOfData() - (24 + 1);
        }
        catch (IOException e) {
            throw new MarcException("error parsing leader with data: "
                    + new String(byteArray), e);
        }
        catch (MarcException e) {
            throw new MarcException("error parsing leader with data: "
                    + new String(byteArray), e);
        }

        // if MARC 21 then check encoding
        switch (ldr.getCharCodingScheme()) {
        case ' ':
            if (!override)
                encoding = "ISO-8859-1";
            break;
        case 'a':
            if (!override)
                encoding = "UTF8";
        }
        record.setLeader(ldr);
       
        if ((directoryLength % 12) != 0)
        {
            throw new MarcException("invalid directory");
        }
        DataInputStream inputrec = new DataInputStream(new ByteArrayInputStream(recordBuf));
        int size = directoryLength / 12;

        String[] tags = new String[size];
        int[] lengths = new int[size];

        byte[] tag = new byte[3];
        byte[] length = new byte[4];
        byte[] start = new byte[5];

        String tmp;

        try {
            for (int i = 0; i < size; i++)
            {
                inputrec.readFully(tag);               
                tmp = new String(tag);
                tags[i] = tmp;
   
                inputrec.readFully(length);
                tmp = new String(length);
                lengths[i] = parseInt(tmp);
   
                inputrec.readFully(start);
            }
   
            if (inputrec.read() != FT)
            {
                throw new MarcException("expected field terminator at end of directory");
            }
           
            for (int i = 0; i < size; i++)
            {
                int fieldLength = getFieldLength(inputrec);
                if (isControlField(tags[i]))
                {
                    byteArray = new byte[lengths[i] - 1];
                    inputrec.readFully(byteArray);
   
                    if (inputrec.read() != FT)
                    {
                        throw new MarcException("expected field terminator at end of field");
                    }
   
                    ControlField field = factory.newControlField();
                    field.setTag(tags[i]);
                    field.setData(getDataAsString(byteArray));
                    record.addVariableField(field);
                }
                else
                {
                    byteArray = new byte[lengths[i]];
                    inputrec.readFully(byteArray);
   
                    try {
                        record.addVariableField(parseDataField(tags[i], byteArray));
                    } catch (IOException e) {
                        throw new MarcException(
                                "error parsing data field for tag: " + tags[i]
                                        + " with data: "
                                        + new String(byteArray), e);
                    }
                }
            }
           
            if (inputrec.read() != RT)
            {
                throw new MarcException("expected record terminator");
            }
        }
        catch (IOException e)
        {
            throw new MarcException("an error occured reading input", e);           
        }
    }

    private DataField parseDataField(String tag, byte[] field)
            throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(field);
        char ind1 = (char) bais.read();
        char ind2 = (char) bais.read();

        DataField dataField = factory.newDataField();
        dataField.setTag(tag);
        dataField.setIndicator1(ind1);
        dataField.setIndicator2(ind2);

        int code;
        int size;
        int readByte;
        byte[] data;
        Subfield subfield;
        while (true) {
            readByte = bais.read();
            if (readByte < 0)
                break;
            switch (readByte) {
            case US:
                code = bais.read();
                if (code < 0)
                    throw new IOException("unexpected end of data field");
                if (code == FT)
                    break;
                size = getSubfieldLength(bais);
                data = new byte[size];
                bais.read(data);
                subfield = factory.newSubfield();
                subfield.setCode((char) code);
                subfield.setData(getDataAsString(data));
                dataField.addSubfield(subfield);
                break;
            case FT:
                break;
            }
        }
        return dataField;
    }
   
    private int getFieldLength(DataInputStream bais) throws IOException
    {
        bais.mark(9999);
        int bytesRead = 0;
        while (true) {
            switch (bais.read()) {
             case FT:
                bais.reset();
                return bytesRead;
            case -1:
                bais.reset();
                throw new IOException("Field not terminated");
            case US:
            default:
                bytesRead++;
            }
        }
    }

    private int getSubfieldLength(ByteArrayInputStream bais) throws IOException {
        bais.mark(9999);
        int bytesRead = 0;
        while (true) {
            switch (bais.read()) {
            case US:
            case FT:
                bais.reset();
                return bytesRead;
            case -1:
                bais.reset();
                throw new IOException("subfield not terminated");
            default:
                bytesRead++;
            }
        }
    }

    private int parseRecordLength(byte[] leaderData) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(leaderData), "ISO-8859-1");
        int length = -1;
        char[] tmp = new char[5];
        isr.read(tmp);
        String str = new String(tmp);
        try {
            length = parseInt(new String(tmp));
        } catch (NumberFormatException e) {
            throw new MarcException("unable to parse record length", e);
        }
        return(length);
    }
   
    private void parseLeader(Leader ldr, byte[] leaderData) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(leaderData),"ISO-8859-1");
        char[] tmp = new char[5];
        isr.read(tmp);
        //  Skip over bytes for record length, If we get here, its already been computed.
        ldr.setRecordStatus((char) isr.read());
        ldr.setTypeOfRecord((char) isr.read());
        tmp = new char[2];
        isr.read(tmp);
        ldr.setImplDefined1(tmp);
        ldr.setCharCodingScheme((char) isr.read());
        char indicatorCount = (char) isr.read();
        char subfieldCodeLength = (char) isr.read();
        char baseAddr[] = new char[5];
        isr.read(baseAddr);
        tmp = new char[3];
        isr.read(tmp);
        ldr.setImplDefined2(tmp);
        tmp = new char[4];
        isr.read(tmp);
        ldr.setEntryMap(tmp);
        isr.close();
        try {
            ldr.setIndicatorCount(parseInt(valueOf(indicatorCount)));
        } catch (NumberFormatException e) {
            throw new MarcException("unable to parse indicator count", e);
        }
        try {
            ldr.setSubfieldCodeLength(parseInt(valueOf(subfieldCodeLength)));
        } catch (NumberFormatException e) {
            throw new MarcException("unable to parse subfield code length", e);
        }
        try {
            ldr.setBaseAddressOfData(parseInt(new String(baseAddr)));
        } catch (NumberFormatException e) {
            throw new MarcException("unable to parse base address of data", e);
        }

    }

    private String getDataAsString(byte[] bytes)
    {
        String dataElement = null;
        if (encoding.equals("UTF-8") || encoding.equals("UTF8"))
        {
            try {
                dataElement = new String(bytes, "UTF8");
            }
            catch (UnsupportedEncodingException e) {
                throw new MarcException("unsupported encoding", e);
            }
        }
        else if (encoding.equals("MARC-8") || encoding.equals("MARC8"))
        {
            if (converterAnsel == null) converterAnsel = new AnselToUnicode();
            dataElement = converterAnsel.convert(bytes);
        }
        else if (encoding.equals("ISO-8859-1") || encoding.equals("ISO8859_1") || encoding.equals("ISO_8859_1"))
        {
            try {
                dataElement = new String(bytes, "ISO-8859-1");
            }
            catch (UnsupportedEncodingException e) {
                throw new MarcException("unsupported encoding", e);
            }
        }
        return dataElement;
    }
   
}