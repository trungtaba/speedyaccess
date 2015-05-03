package XML.ParseXML;

import Table.Application;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by trung on 4/26/2015.
 */
public class ApplicationParse extends MainParse
{
    public ApplicationParse()
    {
        super();
    }

    public ArrayList<String> Receive_Create_Update_Config(String string)
    {
        ArrayList<String> arrayList=new ArrayList<>();
        CreateDocument(string);

        NodeList nList = doc.getElementsByTagName("Config");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String PasswordFormat=eElement.getElementsByTagName("PasswordFormat").item(0).getTextContent().trim();
            String UserNameValidate=eElement.getElementsByTagName("UserNameValidate").item(0).getTextContent().trim();
            String PasswordValidate=eElement.getElementsByTagName("PasswordValidate").item(0).getTextContent().trim();
            String MaxFailedPassword=eElement.getElementsByTagName("MaxFailedPassword").item(0).getTextContent().trim();

            arrayList.add(PasswordFormat);
            arrayList.add(UserNameValidate);
            arrayList.add(PasswordValidate);
            arrayList.add(MaxFailedPassword);
        }
        return arrayList;
    }

    public ArrayList<String> Receive_Create_Update_App(String string)
    {
        ArrayList<String> arrayList=new ArrayList<>();
        CreateDocument(string);

        NodeList nList = doc.getElementsByTagName("application");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String AppName=eElement.getElementsByTagName("AppName").item(0).getTextContent();
            String AppDes=eElement.getElementsByTagName("AppDes").item(0).getTextContent();

            arrayList.add(AppName);
            arrayList.add(AppDes);
        }
        return arrayList;
    }

    public String Send_Result_App(int AppID,String KeyApp,int CodeFunction,int ID,int result)
    {
        String string="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key =\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\" >\n" +
                "    <application>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </application>   \n" +
                "</Application>";
        return string;
    }

    public String Send_Find_App(int AppID,String KeyApp,int CodeFunction,int ID,Application app)
    {
        String string="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key =\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\" >\n" +
                "    <application>\n" +
                "        <AppName>"+app.getAppName()+"</AppName>\n" +
                "        <AppDes>"+app.getAppDes()+"</AppDes>\n" +
                "    </application>   \n" +
                "</Application>\n";
        return string;
    }
    public String Send_Result_Config(int AppID,String KeyApp,int CodeFunction,int ID,int result)
    {
        String string="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key =\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\" >\n" +
                "    <Config>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </Config>   \n" +
                "</Application>";
        return string;
    }
}
