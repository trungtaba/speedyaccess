package XML.ParseXML;

import Table.Action;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class ActionParse extends MainParse
{
    public ActionParse()
    {
        super();
    }

    public ArrayList<String> Receive_Create_Action(String string)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);

        //Element root=doc.getDocumentElement();
        NodeList nList = doc.getElementsByTagName("action");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String ActName=eElement.getElementsByTagName("ActName").item(0).getTextContent().trim();
            String ActDes=eElement.getElementsByTagName("ActDes").item(0).getTextContent().trim();

            array.add(ActName);
            array.add(ActDes);
        }
        return array;
    }

    public String Receive_Delete_Find_Action(String string)
    {
        CreateDocument(string);

        String ActName=null;
        NodeList nList = doc.getElementsByTagName("action");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            ActName=eElement.getElementsByTagName("ActName").item(0).getTextContent().trim();
        }
        return ActName;
    }

    public ArrayList<String> Receive_Update_Action(String string)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);

        Element root=doc.getDocumentElement();
        NodeList nList = doc.getElementsByTagName("action");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String OldActName=eElement.getElementsByTagName("OldActName").item(0).getTextContent().trim();
            String NewActName=eElement.getElementsByTagName("NewActName").item(0).getTextContent().trim();
            String ActDes=eElement.getElementsByTagName("ActDes").item(0).getTextContent().trim();

            array.add(OldActName);
            array.add(NewActName);
            array.add(ActDes);
        }
        return array;
    }

    public ArrayList<String> Receive_Role_Action(String string)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);

        Element root=doc.getDocumentElement();
        NodeList nList = doc.getElementsByTagName("RoleAction");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String RoleName=eElement.getElementsByTagName("RoleName").item(0).getTextContent().trim();
            String ActName=eElement.getElementsByTagName("ActName").item(0).getTextContent().trim();

            array.add(RoleName);
            array.add(ActName);
        }
        return array;
    }

    public ArrayList<String> Receive_User_Action(String string)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);

        NodeList nList = doc.getElementsByTagName("UserAction");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent().trim();
            String ActName=eElement.getElementsByTagName("ActName").item(0).getTextContent().trim();

            array.add(UserName);
            array.add(ActName);
        }
        return array;
    }

    public String Receive_ListAction(String string)
    {
        CreateDocument(string);
        String name=null;
        NodeList nList = doc.getElementsByTagName("Application");
        Node nNode = nList.item(0);

        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            name=eElement.getElementsByTagName("Name").item(0).getTextContent().trim();
        }
        return name;
    }

    public String Send_Result_Action(int AppID,String KeyApp,int CodeFunction,int ID,int result)
    {
        String query= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application id=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "    <action>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </action>   \n" +
                "</Application>\n";
        return query;
    }

    public String Send_Find_Action(int AppID,String KeyApp,int CodeFunction,int ID,Action act)
    {
        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "    <action>        \n" ;
        if(act!=null)
        {
            query+= "        <ID>"+act.getID()+"</ID>\n" +
                    "        <ActName>"+act.getActName().trim()+"</ActName>\n" +
                    "        <ActDes>"+act.getActDes().trim()+"</ActDes>\n" ;
        }
        else
        {
            query+="        <ID></ID>\n" +
                    "        <ActName></ActName>\n" +
                    "        <ActDes></ActDes>\n" ;
        }
        query+= "    </action>   \n" +
                "</Application>\n";
        return query;
    }

    public  String Send_ListAction(int AppID,String KeyApp,int CodeFunction,int ID,String Name,ArrayList<Action> arr)
    {
        String query="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        query+="<Application id=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" ;
        query+="    <name>\n" +
                "        <Name>"+Name+"</Name>\n" +
                "    </name>\n";
        if(arr!=null)
        {
            for(Action temp:arr)
            {
                query+="        <action>  \n";
                query+="            <ID>"+temp.getID()+"</ID>\n";
                query+="            <ActName>"+temp.getActName()+"</ActName>\n";
                query+="            <ActDes>"+temp.getActDes()+"</ActDes>\n";
                query+="        </action> \n";
            }
        }

        query+="</Application>\n";
        return query;
    }

    public String Send_RoleAction(int AppID,String KeyApp,int CodeFunction,int ID,int result)
    {
        String string=  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application id=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" ;

        string+="     <RoleAction>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </RoleAction>\n ";

        string+=       "</Application>";
        return string;
    }

    public String Send_UserAction(int AppID,String KeyApp,int CodeFunction,int ID,int result)
    {
        String string=  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application id=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" ;
        string+="<UserAction>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </UserAction>\n";

        string+=       "</Application>";
        System.out.println(string);
        return string;
    }


}
