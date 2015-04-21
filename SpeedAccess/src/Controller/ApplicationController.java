package Controller;

import static MyCode.MyException.ADD_APP_ERROR;
import static MyCode.MyException.ADD_APP_OK;
import static MyCode.MyException.DELETE_ACTION_OK;
import static MyCode.MyException.DELETE_APP_ERROR;
import static MyCode.MyException.DELETE_APP_OK;
import static MyCode.MyException.DELETE_ROLE_OK;
import static MyCode.MyException.DELETE_USER_OK;
import static MyCode.MyException.NOT_HAVE_APP;
import static MyCode.MyException.UPDATE_APP_ERROR;
import static MyCode.MyException.UPDATE_APP_OK;
import Interfaces.IApplicationController;
import Model.*;
import static MyCode.MyCodeFunction.CREATE_APP;
import static MyCode.MyCodeFunction.DELETE_APP;
import static MyCode.MyCodeFunction.UPDATE_APP;
import Table.Action;
import Table.Application;
import Table.Role;
import Table.User;
import XML.ParseXML.MainParse;
import java.util.ArrayList;

public class ApplicationController implements IApplicationController
{
    private final ApplicationTable appTable;
    private final UserTable userTable;
    private final UserController userCon;
    private final ActionContronller actCon;
    private final RoleController roleCon;
    private final MembershipTable memTable;
    private final MainParse parse;
    
    public ApplicationController() 
    {
        this.appTable = new ApplicationTable();
        this.userTable=new UserTable();
        this.actCon=new ActionContronller();
        this.roleCon=new RoleController();
        this.userCon=new UserController();
        this.memTable=new MembershipTable();
        this.parse=new MainParse();
    }
    
    public String action(int function,String string)
    {
        if(function==CREATE_APP)
        {
            Application app=parse.Receive_App(string);
            
            int result=CreateApp(app);
            return parse.Send_App(app, function,result);
        }
        else if(function==DELETE_APP)
        {
            Application app=parse.Receive_App(string);
            System.out.println(app.toString());
            int result=DeleteApp(app.getID());
            return parse.Send_App(app, function,result);
        }
        else if(function==UPDATE_APP)
        {
            Application app=parse.Receive_App(string);            
            int result=UpdateApp(app);
            return parse.Send_App(app, function,result);
        }
//        else if(function==FIND_APP)
//        {
//            Application app=parse.Receive_App(string);
//            Application tmp=Find(app.getID());
//            
//        }
        else
        {
            return null;
        }
    }
    
    /*==============================================
                        TEST
    ==============================================*/
    @Override
    public int CreateApp(Application app) 
    {
        boolean check= appTable.Create(app);
        if(check==false)
            return ADD_APP_ERROR;
        else
            return ADD_APP_OK;
    }

    //Delete all roles in app
    //Delete all action in app
    //Delete all user in app
    /*==============================================
                        TEST
    ==============================================*/
    @Override
    public int DeleteApp(int AppID) 
    {
        Application appCheck=appTable.Find(AppID);
        if(appCheck==null)
            return NOT_HAVE_APP;
        //Delete all user in app
        ArrayList<User> arrUser=userTable.FindByApp(AppID);
        if(arrUser!=null)
        {
            for(User tmp:arrUser)
            {
                int check=userCon.DeleteUser(tmp.getID());
                if(check!=DELETE_USER_OK)
                        return check;
            }
        }
        
        //Delete all action in app
        ArrayList<Action> arrAct=actCon.ListActionByApp(AppID);
        if(arrAct!=null)
        {
            for(Action act:arrAct)
            {
                int check=actCon.DeleteAct(act.getID());
                if(check!=DELETE_ACTION_OK)
                    return check;
            }
        }
        
        //Delete all roles in app
         ArrayList<Role> arrRole=roleCon.ListRoleByApp(AppID);
         if(arrRole!=null)
         {
             for(Role role:arrRole)
            {
                int check=roleCon.DeleteRole(role.getID());
                if(check!=DELETE_ROLE_OK)
                    return check;                       
            }
         }
         
         boolean check=appTable.Delete(AppID);
         if(check==false)
             return DELETE_APP_ERROR;
         else
             return DELETE_APP_OK;
    }

    @Override
    public int DeleteApp(String AppName) 
    {
        Application app=appTable.Find(AppName);
        if(app==null)
            return NOT_HAVE_APP;
        return DeleteApp(app.getID());
    }
    
    /*==============================================
                        TEST
    ==============================================*/
    @Override
    public int UpdateApp(Application app) 
    {
        Application appCheck=appTable.Find(app.getID());
        if(appCheck==null)
            return NOT_HAVE_APP;
        boolean check=appTable.Update(app);
        if(check==false)
            return UPDATE_APP_ERROR;
        else
            return UPDATE_APP_OK;       
    }
    
    /*==============================================
                        TEST
    ==============================================*/
    @Override
    public Application Find(int AppID) 
    {
        return appTable.Find(AppID);
    }

    //test
    @Override
    public Application Find(String AppName) 
    {
        AppName=AppName.toLowerCase().trim();
        return appTable.Find(AppName);
    }    
}
