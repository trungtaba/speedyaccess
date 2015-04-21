package XML.ParseXML;

import MyClass.MyUser;
import Table.Action;
import Table.Application;
import Table.Role;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class MainParse 
{
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    private Document doc;
    
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
        try {
            doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(string.getBytes("utf-8"))));
            doc.getDocumentElement().normalize();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainParse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(MainParse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    dau tien parse se goi ham nay de lay ve ma lenh can thuc thi
    dua vao ma lenh ta xac dinh tiep theo se goi ham nao
    */
    /*==============================================
                        TEST
    ==============================================*/
    public int FindFunction(String string)
    {
        CreateDocument(string);
        int function=0;
        Element root=doc.getDocumentElement();
        function=Integer.parseInt(root.getAttribute("code"));
        return function;
    }
    /*This function used by Socker server to parse XML string
    This function receive string as input
    parse string to create Action object
    then return this Action
    */
    /*=============================================
                        TEST
    =============================================*/
    public Action Receive_Action(String string)
    {
        Action act=null;
        CreateDocument(string);
        
        Element root=doc.getDocumentElement();
        int AppID=Integer.parseInt(root.getAttribute("id"));
        NodeList nList = doc.getElementsByTagName("action");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            int ID=Integer.parseInt(eElement.getElementsByTagName("ID").item(0).getTextContent());
            String ActName=eElement.getElementsByTagName("ActName").item(0).getTextContent();
            String ActDes=eElement.getElementsByTagName("ActDes").item(0).getTextContent();
            
            act=new Action(ID, AppID, ActName, ActDes);
        }
        return act;
    }
    
    /*
    Socker server use this function to create XML string
    to send to client
    */
    /*=============================================
                        TEST
    =============================================*/
    public String Send_Action(int AppID,int CodeFunction,Action act,int reesult)
    {
        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Application id=\""+AppID+"\" code=\""+CodeFunction+"\">\n" +
                        "    <action>        \n" +
                        "        <ID>"+act.getID()+"</ID>\n" +
                        "        <ActName>"+act.getActName()+"</ActName>\n" +
                        "        <ActDes>"+act.getActDes()+"</ActDes>\n" +
                        "        <Result>"+reesult+"</Result>\n" +
                        "    </action>   \n" +
                        "</Application>\n";
        System.out.println(query);
        System.out.println(query);
        return query;
    }
    
    /*
    receive information from client
    id:AppID
    code:code function
    ID:what is ID of object we need to find
    list of action this object have
    */
    /*=============================================
                        TEST
    =============================================*/
    public ArrayList<String> Receive_List(String string)
    {
        /*
        1:AppID
        2:Name
        */
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);
        
        Element root=doc.getDocumentElement();
        String AppID=root.getAttribute("id");
        array.add(AppID);
        
//        int CodeFunction=Integer.parseInt(root.getAttribute("code"));
//        array.add(CodeFunction);
        
        NodeList nList = doc.getElementsByTagName("Application");
        Node nNode = nList.item(0);
        
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String Name=eElement.getElementsByTagName("Name").item(0).getTextContent();
            array.add(Name);
        }
        return array;
    }
    
    /*
    send from server to client
    */
    public  String Send_ListAction(int AppID,String Name,int CodeFunction,ArrayList<Action> arr)
    {
        String query="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        query+="<Application id=\""+AppID+"\" code=\""+CodeFunction+"\">\n";
        query+="    <name>\n" +
                "        <Name>"+Name+"</Name>\n" +
                "    </name>";
        for(Action temp:arr)
        {
            query+="        <action>  \n";
            query+="            <ID>"+temp.getID()+"</ID>\n";
            query+="            <ActName>"+temp.getActName()+"</ActName>\n";
            query+="            <ActDes>"+temp.getActDes()+"</ActDes>\n";
            query+="        </action> \n";
        }
        query+="</Application>\n";
        return query;
    }

    public ArrayList<String> Receive_RoleAction(String string)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);
        
        Element root=doc.getDocumentElement();
        String AppID=root.getAttribute("id");
        
        array.add(AppID);
        
        NodeList nList = doc.getElementsByTagName("RoleAction");

        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String RoleName=eElement.getElementsByTagName("RoleName").item(0).getTextContent();
            String ActName=eElement.getElementsByTagName("ActName").item(0).getTextContent();

            array.add(RoleName);
            array.add(ActName);
        }
        
        
        return array;
    }

    public String Send_RoleAction(int AppID,int CodeFunction,ArrayList<String> arr,int result)
    {
        String string=  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<Application id=\""+AppID+"\" code=\""+CodeFunction+"\">\n" ;

                string+="     <RoleAction>\n" +
                        "        <RoleName>"+arr.get(1)+"</RoleName>\n" +
                        "        <ActName>"+arr.get(2)+"</ActName>\n" +
                        "        <Result>"+result+"</Result>\n" +
                        "    </RoleAction>\n ";
            
            string+=       "</Application>";
            System.out.println(string);
        return string;
    }

    public ArrayList<String> Receive_UserAction(String string)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);
        
        Element root=doc.getDocumentElement();
        String AppID=root.getAttribute("id");
        
        array.add(AppID);
        
        NodeList nList = doc.getElementsByTagName("UserAction");
        
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent();
            String ActName=eElement.getElementsByTagName("ActName").item(0).getTextContent();

            array.add(UserName);
            array.add(ActName);
        }
        
        
        return array;
    }

    public String Send_UserAction(int AppID,int CodeFunction,ArrayList<String> arr,int result)
    {
        String string=  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<Application id=\""+AppID+"\" code=\""+CodeFunction+"\">\n" ;
            string+="<UserAction>\n" +
                "        <UserName>"+arr.get(1)+"</UserName>\n" +
                "        <ActName>"+arr.get(2)+"</ActName>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </UserAction>\n";
        
        string+=       "</Application>";
        System.out.println(string);
        return string;
    }

    public Application Receive_App(String string)
    {
        Application app=null;
        CreateDocument(string);
        Element root=doc.getDocumentElement();
        int AppID=Integer.parseInt(root.getAttribute("id"));
        NodeList nList = doc.getElementsByTagName("application");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String ActName=eElement.getElementsByTagName("AppName").item(0).getTextContent();
            String ActDes=eElement.getElementsByTagName("AppDes").item(0).getTextContent();
            
            app=new Application(AppID, ActName, ActDes);
        }
        
        return app;
    }
    
    public String Send_App(Application app,int CodeFunction,int result)
    {
        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Application id=\""+app.getID()+"\" code=\""+CodeFunction+"\">\n" +
                        "    <application>\n" +
                        "        <ID>"+app.getID()+"</ID>\n" +
                        "        <AppName>"+app.getAppName()+"</AppName>\n" +
                        "        <AppDes>"+app.getAppDes()+"</AppDes>\n" +
                        "        <Result>"+result+"</Result>\n" +
                        "    </application>   \n" +
                        "</Application>";
        System.out.println(query);
        return query;
    }
    /*
    Nhan thong tin từ client
    dọc thong tin lay username, password
    */
    //test
    public ArrayList<String> Receive_CheckSignIn(String string)
    {
        /*
        1:AppID
        2:UserName
        3:Password
        */
        CreateDocument(string);
        ArrayList<String> arr=new ArrayList<>();
        Element root=doc.getDocumentElement();
        String AppID=root.getAttribute("id");
        arr.add(AppID);
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
    //test
    public String Send_CheckSignIn(int AppID,String UserName,String PassWord,int result)
    {
        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Application id=\""+AppID+"\" code=\"13\">\n" +
                        "    <CheckSignIn>\n" +
                        "        <UserName>"+UserName+"</UserName>\n" +
                        "        <Password>"+PassWord+"</Password>\n" +
                        "        <Result>"+result+"</Result>\n" +
                        "    </CheckSignIn>\n" +
                        "</Application>\n";
        
        System.out.println(query);
        
        return query;
    }

    public Role Receive_Role(String string)
    {
        Role role=null;
        CreateDocument(string);
        Element root=doc.getDocumentElement();
        int AppID=Integer.parseInt(root.getAttribute("id"));
        NodeList nList = doc.getElementsByTagName("Role");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            int ID=Integer.parseInt(eElement.getElementsByTagName("ID").item(0).getTextContent());
            String RoleName=eElement.getElementsByTagName("RoleName").item(0).getTextContent();
            String RoleDes=eElement.getElementsByTagName("RoleDes").item(0).getTextContent();
            
            role=new Role(ID, RoleName, RoleDes, AppID);
        }
        return role;
    }
    
    /*==============================================
                        TEST
    ==============================================*/
    public String Send_Role(int AppID,int CodeFunction,Role role,int result)
    {
        String query="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        query+="<Application id=\""+AppID+"\" code=\""+CodeFunction+"\">\n";
        query+="    <Role>\n";
        query+="        <ID>"+role.getID()+"</ID>\n";
        query+="        <RoleName>"+role.getRoleName()+"</RoleName>\n";
        query+="        <RoleDes>"+role.getRoleDes()+"</RoleDes>\n";
        query+="        <Result>"+result+"</Result>\n";
        query+="    </Role>\n";
        query+="</Application>\n";
        System.out.println(query);
        return query;
    }
    
    public  String Send_ListRole(int AppID,String name,int CodeFunction,ArrayList<Role> arr)
    {
        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Application id=\""+AppID+"\" code=\""+CodeFunction+"\">\n" +
                        "    <Name>"+name+"</Name>\n" ;
        for(Role role:arr)
        {
            query+= "    <Role>\n" +
                    "        <ID>"+role.getID()+"</ID>\n" +
                    "        <RoleName>"+role.getRoleName()+"</RoleName>\n" +
                    "        <RoleDes>"+role.getRoleDes()+"</RoleDes>\n" +
                    "    </Role>\n";
        }
                query+=        "</Application>";
        System.out.println(query);
        return query;
    }
    
    public MyUser Receive_User(String string)
    {
        MyUser myuser=null; 
        CreateDocument(string);
        Element root=doc.getDocumentElement();
        int AppID=Integer.parseInt(root.getAttribute("id"));
        NodeList nList = doc.getElementsByTagName("User");
        Node nNode = nList.item(0);
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            int ID=Integer.parseInt(eElement.getElementsByTagName("ID").item(0).getTextContent());
            int IsApproved=Integer.parseInt(eElement.getElementsByTagName("IsApproved").item(0).getTextContent());
            String UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent();
            String PassWordFormat=eElement.getElementsByTagName("PassWordFormat").item(0).getTextContent();
            String PassWord=eElement.getElementsByTagName("PassWord").item(0).getTextContent();
            String Email=eElement.getElementsByTagName("Email").item(0).getTextContent();
            String PassWordQuestion=eElement.getElementsByTagName("PassWordQuestion").item(0).getTextContent();
            String PassWordAnswer=eElement.getElementsByTagName("PassWordAnswer").item(0).getTextContent();
            int MaxFailedPassword=Integer.parseInt(eElement.getElementsByTagName("MaxFailedPassword").item(0).getTextContent());
            
            myuser=new MyUser(ID, AppID, UserName, PassWord, PassWordFormat,
                    Email, PassWordQuestion, PassWordAnswer, IsApproved,MaxFailedPassword);
        }
        return myuser;
    }
    
    public String Send_User(int AppID,MyUser myUser,int CodeFunction,int result)
    {
        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Application id=\""+AppID+"\" code=\""+CodeFunction+"\">\n" +
                        "    <User>\n" +
                        "        <ID>"+myUser.getID()+"</ID>\n" +
                        "        <UserName>"+myUser.getUserName()+"</UserName>\n" +
                        "        <PassWord>"+myUser.getPassWord()+"</PassWord>\n" +
                        "        <PassWordFormat>"+myUser.getPassWordFormat()+"</PassWordFormat>\n" +
                        "        <Email>"+myUser.getEmail()+"</Email>\n" +
                        "        <PassWordQuestion>"+myUser.getPassWordQuestion()+"</PassWordQuestion>\n" +
                        "        <PassWordAnswer>"+myUser.getPassWordAnswer()+"</PassWordAnswer>\n" +
                        "        <IsApproved>"+myUser.getIsApproved()+"</IsApproved>\n" +
                        "        <Result>"+result+"</Result>\n"+
                        "    </User>\n" +
                        "</Application>";
        return query;
    }
    
    public  String Send_ListUser(int AppID,String name,int CodeFunction,ArrayList<MyUser> arr)
    {
        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Application id=\""+AppID+"\" code=\""+CodeFunction+"\">\n" +
                        "    <Name>"+name+"</Name>\n" ;
        for(MyUser myUser:arr)
        {
            query+= "      <User>\n" +
            "            <ID>"+myUser.getID()+"</ID>\n" +
            "            <UserName>"+myUser.getUserName()+"</UserName>\n" +
            "            <PassWord>"+myUser.getPassWord()+"</PassWord>\n" +
            "            <PassWordFormat>"+myUser.getPassWordFormat()+"</PassWordFormat>\n" +
            "            <Email>"+myUser.getEmail()+"</Email>\n" +
            "            <PassWordQuestion>"+myUser.getPassWordQuestion()+"</PassWordQuestion>\n" +
            "            <PassWordAnswer>"+myUser.getPassWordAnswer()+"</PassWordAnswer>\n" +
            "            <IsApproved>"+myUser.getIsApproved()+"</IsApproved>\n" +
            "        </User>\n";
        }
                query+=        "</Application>";
        System.out.println(query);
        return query;
    }
    
    public ArrayList<String> Receive_UserRole(String string)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateDocument(string);
        
        Element root=doc.getDocumentElement();
        String AppID=root.getAttribute("id");
        array.add(AppID);
        
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
    /*==============================================
                        TEST
    ==============================================*/
    public String Send_UserRole(int AppID,int CodeFunction,ArrayList<String> arr,int result)
    {
        String string=  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<Application id=\""+AppID+"\" code=\""+CodeFunction+"\">\n" ;
        string+="<UserRole>\n" +
                "        <UserName>"+arr.get(1)+"</UserName>\n" +
                "        <RoleName>"+arr.get(2)+"</RoleName>\n" +
                "        <Result>"+result+"</Result>\n" +
                "    </UserRole> \n";
        
        string+=       "</Application>";
        System.out.println(string);
        return string;
    }
    
    public ArrayList<String> Receive_ChanglePasswordByUser(String string)
    {
        /*
        0:AppID
        1:UserName
        2:OldPassword
        3:NewPassword
        */
        CreateDocument(string);
        
        ArrayList<String> result=new ArrayList<>();
        
        Element root=doc.getDocumentElement();
        String AppID=root.getAttribute("id");
        result.add(AppID);
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
    
    public ArrayList<String> Receive_ChanglePasswordByAdmin(String string)
    {
        /*
        0:AppID
        1:UserName
        2:NewPassword
        */
        CreateDocument(string);
        
        ArrayList<String> result=new ArrayList<>();
        
        Element root=doc.getDocumentElement();
        String AppID=root.getAttribute("id");
        result.add(AppID);
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
    
    public String Send_ChangePassword(int AppID,int CodeFunction, String UserName,int result)
    {
        String string=  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Application id=\""+AppID+"\" code=\""+CodeFunction+"\">\n" +
                        "    <ChangPassword>\n" +
                        "        <UserName>"+UserName+"</UserName>\n" +
                        "        <Result>"+result+"</Result>\n" +
                        "    </ChangPassword>\n" +
                        "</Application>";
        return string;
    }
    
    public ArrayList<String> Receive_ChanglePasswordQuestion(String string)
    {
        /*
        0:AppID
        1:UserName
        2:Question
        3:Answer
        */
        CreateDocument(string);
        
        ArrayList<String> result=new ArrayList<>();
        
        Element root=doc.getDocumentElement();
        String AppID=root.getAttribute("id");
        result.add(AppID);
        NodeList nList = doc.getElementsByTagName("ChangePasswordQuestion");
        Node nNode = nList.item(0);
        
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) nNode;
            String UserName=eElement.getElementsByTagName("UserName").item(0).getTextContent();
            result.add(UserName);
            String Question=eElement.getElementsByTagName("Question").item(0).getTextContent();
            result.add(Question);
            String Answer=eElement.getElementsByTagName("Answer").item(0).getTextContent();
            result.add(Answer);
        }
        
        return result;
    }
    
    public String Send_ChanglePasswordQuestion(int AppID,int CodeFunction, String UserName,int result)
    {
        String string=  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Application id=\""+AppID+"\" code=\""+CodeFunction+"\">\n" +
                        "    <ChangPasswordQuestion>\n" +
                        "        <UserName>"+UserName+"</UserName>\n" +
                        "        <Result>"+result+"</Result>\n" +
                        "    </ChangPasswordQuestion>\n" +
                        "</Application>";
        return string;
    }
}
