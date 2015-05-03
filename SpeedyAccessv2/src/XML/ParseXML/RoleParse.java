package XML.ParseXML;

import Table.Role;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class RoleParse extends  MainParse
{
    public RoleParse()
    {
        super();
    }

    public ArrayList<String> Receive_Create_Role(String string)
    {
        ArrayList<String>  array=new ArrayList<>();
        CreateDocument(string);
        NodeList nList = doc.getElementsByTagName("Role");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String RoleName=eElement.getElementsByTagName("RoleName").item(0).getTextContent().trim();
            String RoleDes=eElement.getElementsByTagName("RoleDes").item(0).getTextContent().trim();

            array.add(RoleName);
            array.add(RoleDes);
        }
        return array;
    }

    public String Receive_Delete_Find_Role(String string)
    {
        CreateDocument(string);

        String RoleName=null;
        NodeList nList = doc.getElementsByTagName("Role");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            RoleName=eElement.getElementsByTagName("RoleName").item(0).getTextContent().trim();

        }
        return RoleName;
    }

    public ArrayList<String> Receive_Update_Role(String string)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);

        NodeList nList = doc.getElementsByTagName("Role");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String OldRoleName=eElement.getElementsByTagName("OldRoleName").item(0).getTextContent().trim();
            String NewRoleName=eElement.getElementsByTagName("NewRoleName").item(0).getTextContent().trim();
            String RoleDes=eElement.getElementsByTagName("RoleDes").item(0).getTextContent().trim();

            array.add(OldRoleName);
            array.add(NewRoleName);
            array.add(RoleDes);
        }
        return array;
    }

    public String Receive_ListRole(String string)
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

    public String Send_Result_Role(int AppID,String KeyApp,int CodeFunction,int ID, int result)
    {
        String query= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "    <Role>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </Role>\n" +
                "</Application>";
        return query;
    }

    public String Send_Find_Role(int AppID,String KeyApp,int CodeFunction,int ID, Role role )
    {
        String string="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "    <Role>\n" ;
        if(role!=null)
        {
            string+="        <RoleName>"+role.getRoleName()+"</RoleName>\n" +
                    "        <RoleDes>"+role.getRoleDes()+"</RoleDes>\n" ;
        }
        else
        string+="        <RoleName></RoleName>\n" +
                "        <RoleDes></RoleDes>\n" ;

         string+=       "    </Role>\n" +
                "</Application>";
        return string;
    }

    public  String Send_ListRole(int AppID,String KeyApp,int CodeFunction,int ID,String name,ArrayList<Role> arr)
    {
        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "    <Name>"+name+"</Name>" ;
        if(arr!=null)
        {
            for(Role role:arr)
            {
                query+= "    <Role>\n" +
                        "        <ID>"+role.getID()+"</ID>\n" +
                        "        <RoleName>"+role.getRoleName()+"</RoleName>\n" +
                        "        <RoleDes>"+role.getRoleDes()+"</RoleDes>\n" +
                        "    </Role>\n";
            }
        }

        query+=        "</Application>";
        return query;
    }
}
