package Controller;

import static MyCode.MyException.*;
import Interfaces.IActionController;
import Model.*;
import static MyCode.MyCodeFunction.CREATE_ACT;
import static MyCode.MyCodeFunction.CREATE_ROLE_ACT;
import static MyCode.MyCodeFunction.CREATE_USER_ACT;
import static MyCode.MyCodeFunction.DELETE_ACT;
import static MyCode.MyCodeFunction.DELETE_ROLE_ACT;
import static MyCode.MyCodeFunction.DELETE_USER_ACT;
import static MyCode.MyCodeFunction.LIST_ACTION_BY_APP;
import static MyCode.MyCodeFunction.LIST_ACTION_BY_ROLE;
import static MyCode.MyCodeFunction.LIST_ACTION_BY_USER;
import static MyCode.MyCodeFunction.UPDATE_ACT;
import Table.*;
import XML.ParseXML.MainParse;
import java.util.ArrayList;

public class ActionContronller implements IActionController
{
    private final ActionTable actTable;
    private final User_actionTable user_actTable;
    private final Role_actionTable role_actTable;
    private final User_roleTable user_roleTable;
    private final UserTable userTable;
    private final RoleTable roleTable;
    private final MainParse parse;

    public ActionContronller()
    {
        this.actTable = new ActionTable();
        this.user_actTable = new User_actionTable();
        this.role_actTable = new Role_actionTable();
        this.user_roleTable=new User_roleTable();
        this.userTable=new UserTable();
        roleTable=new  RoleTable();
        this.parse=new MainParse();
    }

    public String action(int function,String string)
    {        
        if(function==CREATE_ACT)
        {
            Action action=parse.Receive_Action(string);
            int result=CreateAct(action);
            return parse.Send_Action(action.getAppID(), function, action, result);
        }
        else if(function==DELETE_ACT)    
        {
            Action action=parse.Receive_Action(string);
            int result=DeleteAct(action.getAppID(),action.getActName());
            return parse.Send_Action(action.getAppID(), function, action, result);
        }
        else if(function==UPDATE_ACT)
        {
            Action action=parse.Receive_Action(string);
            int result=UpdateAct(action);
            return parse.Send_Action(action.getAppID(), function, action, result);
        }
        else if(function==LIST_ACTION_BY_APP)
        {
            ArrayList<String> arr=parse.Receive_List(string);
            int AppID=Integer.parseInt(arr.get(0));
            ArrayList<Action> list=ListActionByApp(AppID);
            return parse.Send_ListAction(AppID, arr.get(1), function, list);
        }
//        else if(function==FIND_ACT)
//        {
//            Action action=parse.Receive_Action(string);
//            Action tmp=Find(action.getID());
//            return parse
//        }
        else if(function==LIST_ACTION_BY_ROLE)
        {
            ArrayList<String> arr=parse.Receive_List(string);
            int AppID=Integer.parseInt(arr.get(0));
            String RoleName=arr.get(1);
            
            ArrayList<Action> list=ListActionByRole(AppID, RoleName);
            return parse.Send_ListAction(AppID, RoleName, function, list);
        }
        else if(function==LIST_ACTION_BY_USER)
        {
            ArrayList<String> arr=parse.Receive_List(string);
            int AppID=Integer.parseInt(arr.get(0));
            String UserName=arr.get(1);
            ArrayList<Action> list=ListActionByUser(AppID, UserName);
            return parse.Send_ListAction(AppID, UserName, function, list);
        }
        else if(function==CREATE_ROLE_ACT)
        {
            int result;
            ArrayList<String> arr=parse.Receive_RoleAction(string);
            
            int AppID=Integer.parseInt(arr.get(0));
            String RoleName=arr.get(1);
            String ActName=arr.get(2);
            result=AddRoleAct(AppID, RoleName, ActName);
            
            return parse.Send_RoleAction(1, CREATE_ROLE_ACT, arr, result);
        }
        else if(function==CREATE_USER_ACT)
        {
            int result;
            ArrayList<String> arr=parse.Receive_UserAction(string);
            
            int AppID=Integer.parseInt(arr.get(0));
            String UserName=arr.get(1);
            String ActName=arr.get(2);
            result=AddUserAct(AppID, UserName, ActName);
            
            return  parse.Send_UserAction(1, function, arr, result);
        }
        else if(function==DELETE_ROLE_ACT)
        {
            int result;
            
            ArrayList<String> arr=parse.Receive_RoleAction(string);
            int AppID=Integer.parseInt(arr.get(0));
            String RoleName=arr.get(1);
            String ActName=arr.get(2);
            
            result=DeleteRoleAct(AppID, RoleName, ActName);
            
            return parse.Send_RoleAction(AppID, function, arr, result);
        }
        else if(function==DELETE_USER_ACT)
        {
            int result;
            ArrayList<String> arr=parse.Receive_UserAction(string);

            int AppID=Integer.parseInt(arr.get(0));
            String UserName=arr.get(1);
            String ActName=arr.get(2);
            
            result=DeleteUserAct(AppID, UserName, ActName);
            
            return  parse.Send_UserAction(1, function, arr, result);
        }
        else
        {
            return null;
        }
    }
    /*=============================================
                        TEST
    =============================================*/
    @Override
    public int CreateAct(Action act) 
    {
        Action temp=actTable.Find(act.getAppID(),act.getActName());
        if(temp!=null)
            return REALLY_HAVE_ACTION;
         boolean check=actTable.Create(act); 
         if(check!=true)
            return ADD_ACTION_ERROR;
        else 
            return ADD_ACTION_OK;
    }
    
    @Override
    public int DeleteAct(int ActID)
    {
        ArrayList<Role_action> role_action=new ArrayList<>();
        ArrayList<User_action> user_action=new ArrayList<>();
        boolean check;
        check = false;
        
        //Delete all row in Role_action table where ActID=ActID
        role_action=role_actTable.FindByAction(ActID);
        for(Role_action act:role_action)
        {
            check=role_actTable.Delete(act.getID());
            if(check==false)
                return DELETE_ROLE_ACTION_ERROR;
        }
        //delete all row in User_action where ActID=ActID
        user_action=user_actTable.FindByAct(ActID);
        for(User_action act:user_action)
        {
            check=user_actTable.Delete(act.getID());
            if(check==false)
                return DELETE_USER_ACTION_ERROR;                        
        }
        //detele row in Actions table
        check=actTable.Delete(ActID);
        if(check==false)
            return DELETE_ACTION_ERROR;
        else
            return DELETE_ACTION_OK;
    }
    /*
    Delete all row in Role_action table and\
    User_action table,then delete action
    */
    /*=============================================
                        TEST
    =============================================*/
    @Override
    public int DeleteAct(int AppID,String ActName) 
    {
        Action action=Find(AppID, ActName);
        int ActID=action.getID();
        return DeleteAct(ActID);
    }

    /*=============================================
                        TEST
    =============================================*/
    @Override
    public int UpdateAct(Action act) 
    {
        Action temp=actTable.Find(act.getAppID(),act.getActName());
        if(temp!=null)
            return REALLY_HAVE_ACTION;
        Action action=actTable.Find(act.getID());
        if(action==null)
            return NOT_HAVE_ACTION;
        boolean check= actTable.Update(act);
        if(check==true)
            return UPDATE_ACTION_OK;
        else
            return UPDATE_ACTION_ERROR;
    }

    /*=============================================
                        TEST
    =============================================*/
    @Override
    public Action Find(int AppID,String ActName) 
    {
        return actTable.Find(AppID,ActName);
    }

    @Override
    public Action Find(int ActID)
    {
        return actTable.Find(ActID);
    }
    /*=============================================
                        TEST
    =============================================*/
    @Override
    public ArrayList<Action> ListActionByApp(int AppID) 
    {
        ArrayList<Action> array=new ArrayList<>();
        array=actTable.FindByApp(AppID);
        return array;
    }
    /*
    Tim cac Action trong bang Actions theo ActID
    */
    /*=============================================
                        TEST
    =============================================*/
    @Override
    public ArrayList<Action> ListActionByRole(int AppID,String RoleName) 
    {      
        Role roleFind=roleTable.Find(AppID,RoleName);
        int RoleID=roleFind.getID();
        ArrayList<Action> array=new ArrayList<>();
        ArrayList<Integer> arrayRoleID=new ArrayList<>();
        //Tim tat ca ActID trong bang Role_action
        role_actTable.FindByRole(RoleID).stream().map((role) -> role.getActID()).forEach((id) -> {
            arrayRoleID.add(id);
        });
        //Tim cac Action trong bang Actions theo ActID
        arrayRoleID.stream().forEach((ActID) -> {
            array.add(actTable.Find(ActID));
        });
        return array;
    }

    /*
    Lay thong tin ActID tren bang User_action
    Lay thong tin action trong bang actions theo ActID
    */
    /*=============================================
                        TEST
    =============================================*/
    @Override
    public ArrayList<Action> ListActionByUser(int AppID,String UserName) 
    {
        User userFind=userTable.Find(AppID,UserName);
        int UserID=userFind.getID();
        
        ArrayList<Action> array=new ArrayList<>();
        ArrayList<Integer> arrayUsac=new ArrayList<>();
        
        //Lay toan bo ActID trong bang User_action co UserID tuong ung
        user_actTable.FindByUser(UserID).stream().forEach((usac) -> {
            arrayUsac.add(usac.getActID());
        });
        //Lay thong tin Action theo ActID trong bang actions
        arrayUsac.stream().map((ActID) -> actTable.Find(ActID)).forEach((act) -> {
            array.add(act);
        });
        return array;
    }

    /*
    //them du lieu vao bang Role_act
    them quyen cho nhung nguoi dung thuoc role
    */
    //test
    @Override
    public int AddRoleAct( int RoleID,int ActID) 
    {
        Role_action ro=role_actTable.Find(RoleID,ActID);
        if(ro!=null)
            return REALLY_HAVE_ROLE_ACTION;
        int temp=0;
        //them du lieu vao bang role_action
        boolean check=role_actTable.Create(new Role_action(1, RoleID, ActID));
        if(check==false)
            return ADD_ROLE_ACTION_ERROR;

         //them du lieu vao bang user_action
         for(User_role us:user_roleTable.FindByRole(RoleID))
         {          
            temp=AddUserAct(us.getUserID(),ActID);
            if(temp==ADD_USER_ACTION_ERROR)
                return temp;
         }
        return ADD_ROLE_ACTION_OK;
    }
    
    @Override
    public int AddRoleAct(int AppID,String RoleName,String ActName)
    {
        Role role=roleTable.Find(AppID, RoleName);
        int RoleID=role.getID();
        Action action=actTable.Find(AppID, ActName);
        int ActID=action.getID();
        return AddRoleAct(RoleID,ActID);
    }
    
    @Override
    public int AddUserAct(int UserID,int ActID)
    {
        User_action temp=user_actTable.Find(UserID,ActID);
        if(temp!=null)
            return REALLY_HAVE_USER_ACTION;
        boolean check= user_actTable.Create(new User_action(1, UserID, ActID));
        if(check==false)
            return ADD_USER_ACTION_ERROR;
        else 
            return ADD_USER_ACTION_OK;
    }
    /*==============================================
                        TEST
    ==============================================*/
    @Override
    public int AddUserAct(int AppID,String UserName,String ActName) 
    {
        User userFind=userTable.Find(AppID, UserName);
        int UserID=userFind.getID();
        Action actFind=actTable.Find(AppID,ActName );
        int ActID=actFind.getID();
        
        return AddUserAct(UserID,ActID);
    }

    @Override
    public int DeleteRoleAct(int RoleID, int ActionID) 
    {
        Role_action roleAction=role_actTable.Find(RoleID, ActionID);
        if(roleAction==null)
            return NOT_HAVE_ROLE_ACTION;
        
        //Delete all user-action with user in role group
         for(User_role us:user_roleTable.FindByRole(RoleID))
         { 
             int temp=0;
             User_action usac=user_actTable.Find(us.getUserID(), ActionID);
             if(usac!=null)
                temp=DeleteUserAct(us.getUserID(), ActionID);
            //if(temp!=DELETE_USER_ACTION_OK)
                // temp;
         }
        boolean check=role_actTable.Delete(roleAction.getID());
        if(check==true)
        {
            return DELETE_ROLE_ACTION_OK;
        }
        else
        {
            return DELETE_ROLE_ACTION_ERROR;
        }
    }
    
    @Override
    public int DeleteRoleAct(int AppID,String RoleName,String ActName)
    {
        Role role=roleTable.Find(AppID, RoleName);
        int RoleID=role.getID();
        Action action=actTable.Find(AppID, ActName);
        int ActionID=action.getID();
        return DeleteRoleAct(RoleID,ActionID);
    }
    

//    @Override
//    public Role_action FindRoleAction(int RoleID, int ActionID) 
//    {
//        Role_action roleAction=role_actTable.Find( RoleID,ActionID);       
//        return roleAction;
//    }
//
//    @Override
//    public User_action FindUserAction(int UserID, int ActionID) 
//    {
//        User_action userAction=user_actTable.Find(UserID,ActionID);
//        return userAction;
//    }

    /*==============================================
                        TEST
    ==============================================*/
    @Override
    public int DeleteUserAct(int UserID, int ActionID) 
    {
        User_action userAction=user_actTable.Find(UserID, ActionID);
        if(userAction==null)
            return NOT_HAVE_USER_ACTION;
        boolean check=user_actTable.Delete(userAction.getID());
        if(check==false)
            return DELETE_USER_ACTION_ERROR;
        else
            return DELETE_USER_ACTION_OK;
    }
    
    @Override
    public int DeleteUserAct(int AppID, String UserName, String ActName) 
    {
        User user=userTable.Find(AppID, UserName);
        Action action=actTable.Find(AppID, ActName);
        return DeleteUserAct(user.getID(),action.getID());
    }
}
