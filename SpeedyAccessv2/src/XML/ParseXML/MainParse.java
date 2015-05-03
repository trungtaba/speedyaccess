package XML.ParseXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainParse 
{
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    protected Document doc;
    
    public  MainParse()
    {
        try {
            //Create a DocumentBuilder
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MainParse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CreateDocument(String string)
    {
        try
        {
            doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(string.getBytes("utf-8"))));
            doc.getDocumentElement().normalize();
        }
        catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(MainParse.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SAXException | IOException ex)
        {
            Logger.getLogger(MainParse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> FindParameter(String string)
    {
        CreateDocument(string);
        ArrayList<String> array=new ArrayList<>();
        Element root=doc.getDocumentElement();

        String AppID=root.getAttribute("AppID").trim();
        String key=root.getAttribute("key").trim();
        String code=root.getAttribute("code").trim();
        String ID=root.getAttribute("ID").trim();
        array.add(AppID);
        array.add(key);
        array.add(code);
        array.add(ID);
        return array;
    }

    public String Send_KeyError(int AppID,String KeyApp,int ID,String KeyError)
    {
        String string="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application id=\""+AppID+"\" key=\""+KeyApp+"\" code=\"0\" ID=\""+ID+"\">\n"+
                "    <application>\n" +
                "        <KeyError>"+KeyError+"</KeyError>\n" +
                "    </application>\n" +
                "</Application>";
        return string;
    }
}
