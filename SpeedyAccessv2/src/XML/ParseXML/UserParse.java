package XML.ParseXML;

import Table.User;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by trung on 4/26/2015.
 */
public class UserParse extends MainParse
{
    public UserParse()
    {
        super();
    }

    public ArrayList<String> Receive_Create_User(String string)
    {
        ArrayList<String> arrayList=new ArrayList<>();
        CreateDocument(string);

        NodeList nList = doc.getElementsByTagName("User");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;

            String UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent().trim();
            String PassWord=eElement.getElementsByTagName("PassWord").item(0).getTextContent().trim();
            String Email=eElement.getElementsByTagName("Email").item(0).getTextContent().trim();

            arrayList.add(UserName);
            arrayList.add(PassWord);
            arrayList.add(Email);
        }
        return arrayList;
    }

    public String Receive_Delete_Find_Close_User(String string)
    {
        CreateDocument(string);

        String UserName=null;
        NodeList nList = doc.getElementsByTagName("User");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent().trim();
        }
        return UserName;
    }

    public ArrayList<String> Receive_Update_UserName(String string)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);

        Element root=doc.getDocumentElement();
        NodeList nList = doc.getElementsByTagName("UpdateUserName");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String OldUserName=eElement.getElementsByTagName("OldUserName").item(0).getTextContent().trim();
            String NewUserName=eElement.getElementsByTagName("NewUserName").item(0).getTextContent().trim();

            array.add(OldUserName);
            array.add(NewUserName);
        }
        return array;
    }

    public String Receive_ListUser(String string)
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

    public ArrayList<String> Receive_Update_Email(String string)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);

        Element root=doc.getDocumentElement();
        NodeList nList = doc.getElementsByTagName("UpdateEmail");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent().trim();
            String NewEmail=eElement.getElementsByTagName("NewEmail").item(0).getTextContent().trim();

            array.add(UserName);
            array.add(NewEmail);
        }
        return array;
    }

    public ArrayList<String> Receive_UserRole(String string)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);

        NodeList nList = doc.getElementsByTagName("UserRole");

        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent();
            String RoleName=eElement.getElementsByTagName("RoleName").item(0).getTextContent();

            array.add(UserName);
            array.add(RoleName);
        }


        return array;
    }

    public ArrayList<String> Receive_ChangePasswordByUser(String string)
    {
        CreateDocument(string);

        ArrayList<String> result=new ArrayList<>();

        NodeList nList = doc.getElementsByTagName("ChangPassword");
        Node nNode = nList.item(0);

        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent();
            result.add(UserName);
            String OldPassword=eElement.getElementsByTagName("OldPassword").item(0).getTextContent();
            result.add(OldPassword);
            String NewPassword=eElement.getElementsByTagName("NewPassword").item(0).getTextContent();
            result.add(NewPassword);
        }

        return result;
    }

    public ArrayList<String> Receive_ChangePasswordByAdmin(String string)
    {
        CreateDocument(string);

        ArrayList<String> result=new ArrayList<>();

        NodeList nList = doc.getElementsByTagName("ChangPassword");
        Node nNode = nList.item(0);

        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent();
            result.add(UserName);
            String NewPassword=eElement.getElementsByTagName("NewPassword").item(0).getTextContent();
            result.add(NewPassword);
        }

        return result;
    }

    public ArrayList<String> Receive_CheckSignIn(String string)
    {

        CreateDocument(string);
        ArrayList<String> arr=new ArrayList<>();

        NodeList nList = doc.getElementsByTagName("CheckSignIn");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent();
            String Password=eElement.getElementsByTagName("Password").item(0).getTextContent();

            arr.add(UserName);
            arr.add(Password);
        }

        return arr;
    }

    public String Receive_Logout(String string)
    {
        CreateDocument(string);
        String UserName=null;
        NodeList nList = doc.getElementsByTagName("Logout");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent().trim();
        }
        return UserName;
    }

    public ArrayList<String> Receive_CheckUserAction(String string)
    {
        CreateDocument(string);
        ArrayList<String> arr=new ArrayList<>();

        NodeList nList = doc.getElementsByTagName("CheckUserAction");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent();
            String Action=eElement.getElementsByTagName("CActionBackup").item(0).getTextContent();

            arr.add(UserName);
            arr.add(Action);
        }

        return arr;
    }
    public String Send_Result_User(int AppID,String KeyApp,int CodeFunction,int ID,int result)
    {
        String string="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "    <User>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </User>\n" +
                "</Application>";
        return string;
    }

    public String Send_Find_User(int AppID,String KeyApp,int CodeFunction,int ID, User user)
    {
        String string="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "    <User>\n" +
                "        <UserName>"+user.getUserName()+"</UserName>\n" +
                "        <Email>"+user.getEmail()+"</Email>\n" +
                "        <CreateDate>"+user.getCreateDate()+"</CreateDate>\n" +
                "        <LastLoginDate>"+user.getLastLoginDate()+"</LastLoginDate>\n" +
                "        <LastLockoutDate>"+user.getLastLockoutDate()+"</LastLockoutDate>\n"+
                "        <LastPasswordChangedDate>"+user.getLastPasswordChangedDate()+"</LastPasswordChangedDate>\n" +
                "        <LastActiveDate>"+user.getLastActiveDate()+"</LastActiveDate>\n" +
                "    </User>\n" +
                "</Application>";
        return string;
    }

    public  String Send_ListUser(int AppID,String KeyApp,int CodeFunction,int ID,String name,ArrayList<User> arr)
    {
        String query="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "    <Name>"+name+"</Name>\n" ;
        if(arr!=null)
        {
            for(User user:arr)
            {
                query+= "    <User>\n" +
                        "        <UserName>"+user.getUserName() + "</UserName>\n" +
                        "        <Email>"+user.getEmail() + "</Email>\n" +
                        "        <CreateDate>"+user.getCreateDate() + "</CreateDate>\n" +
                        "        <LastLoginDate>"+user.getLastLoginDate() + "</LastLoginDate>\n" +
                        "        <LastLockoutDate>"+user.getLastLockoutDate() + "</LastLockoutDate>\n"+
                        "        <LastPasswordChangedDate>"+user.getLastPasswordChangedDate() + "</LastPasswordChangedDate>\n" +
                        "        <LastActiveDate>"+user.getLastActiveDate()+"</LastActiveDate>\n" +
                        "    </User>\n" ;
            }
        }

        query+=        "</Application>";
        return query;
    }

    public String Send_UserRole(int AppID,String KeyApp,int CodeFunction,int ID,int result)
    {
        String string=  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "   <UserRole>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </UserRole> \n"+
                 "</Application>";
        return string;
    }

    public String Send_ChangePassword(int AppID,String KeyApp,int CodeFunction,int ID,String UserName,int result)
    {
        String string= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "    <ChangPassword>\n" +
                "        <UserName>"+UserName+"</UserName>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </ChangPassword>\n" +
                "</Application>";
        System.out.println(string);
        return string;
    }

    public String Send_CheckSignIn(int AppID,String KeyApp,int CodeFunction,int ID,String UserName,String PassWord,int result)
    {
        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "    <CheckSignIn>\n" +
                "        <UserName>"+UserName+"</UserName>\n" +
                "        <Password>"+PassWord+"</Password>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </CheckSignIn>\n" +
                "</Application>\n";

        System.out.println(query);

        return query;
    }

    public String Send_CheckUserAction(int AppID,String KeyApp,int CodeFunction,int ID,String UserName,String Action,int result)
    {
        String string="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application AppID=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n" +
                "    <CheckUserAction>\n" +
                "        <UserName>"+UserName+"</UserName>\n" +
                "        <CActionBackup>"+Action+"</CActionBackup>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </CheckUserAction>\n" +
                "</Application>";
        return string;
    }

    public String Send_Logout(int AppID,String KeyApp,int CodeFunction,int ID,String UserName,int result)
    {
        String string=  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Application id=\""+AppID+"\" key=\""+KeyApp+"\" code=\""+CodeFunction+"\" ID=\""+ID+"\">\n"+
                "    <Logout>\n" +
                "        <UserName>"+UserName+"</UserName>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </Logout>\n" +
                "</Application>";
        return string;
    }
}
