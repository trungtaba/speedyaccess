package Controller;

import Backup.Controller.ControllerBackup;
import Backup.Entity.ActionBackup;
import Interfaces.IAction;
import Model.ActionTable;
import Model.Role_ActTable;
import Model.UserTable;
import Model.User_ActTable;
import Table.Action;
import Table.User;
import XML.ParseXML.ActionParse;

import java.util.ArrayList;

import static MyCode.MyCodeFunction.*;
import static MyCode.MyException.*;

public class CAction implements IAction
{
    private final ActionTable actionTable;
    private final Role_ActTable role_actTable;
    private final User_ActTable user_actTable;
    private final UserTable userTable;
    private ActionParse actionParse;
    private ControllerBackup controllerBackup;

    CAction()
    {
        this.actionTable=new ActionTable();
        this.role_actTable=new Role_ActTable();
        this.user_actTable=new User_ActTable();
        this.actionParse=new ActionParse();
        this.userTable=new UserTable();
        controllerBackup=ControllerBackup.getInstance();
    }

    String action(int AppID,String key,int ID,int function,String string)
    {
        int result=-1;
        if(function==CreateAct)
        {
            ArrayList<String> arrayList=actionParse.Receive_Create_Action(string);
            Action action=new Action(1,AppID,arrayList.get(0),arrayList.get(1));
            result=CreateAct(action);
            return actionParse.Send_Result_Action(AppID,key,CreateAct,ID,result);
        }
        else if(function==DeleteAct)
        {
            String ActName=actionParse.Receive_Delete_Find_Action(string);
            result=DeleteAct(AppID,ActName);
            return actionParse.Send_Result_Action(AppID,key,function,ID,result);
        }
        else if(function==UpdateAct)
        {
            ArrayList<String> arrayList=actionParse.Receive_Update_Action(string);
            result=UpdateAct(AppID,arrayList.get(0),arrayList.get(1),arrayList.get(2));
            return actionParse.Send_Result_Action(AppID,key,UpdateAct,ID,result);
        }
        else if(function==AddRoleAct)
        {
            ArrayList<String> arrayList=actionParse.Receive_Role_Action(string);
            result=AddRoleAct(AppID,arrayList.get(0),arrayList.get(1));
            return actionParse.Send_RoleAction(AppID,key,function,ID,result);
        }
        else if(function==AddUserAct)
        {
            ArrayList<String> arrayList=actionParse.Receive_User_Action(string);
            result=AddUserAct(AppID, arrayList.get(0), arrayList.get(1));
            return actionParse.Send_UserAction(AppID, key, function, ID, result);
        }
        else if(function==FindAction)
        {
            String str=actionParse.Receive_Delete_Find_Action(string);
            Action action=Find(AppID,str);
            return actionParse.Send_Find_Action(AppID,key,function,ID,action);
        }
        else if(function==ListActionByApp)
        {
            String Name=actionParse.Receive_ListAction(string);
            ArrayList<Action>actionArrayList=ListActionByApp(AppID);
            return actionParse.Send_ListAction(AppID,key,function,ID,Name,actionArrayList);
        }
        else if(function==ListActionByRole)
        {
            String Name=actionParse.Receive_ListAction(string);
            ArrayList<Action>actionArrayList=ListActionByRole(AppID,Name);
            return actionParse.Send_ListAction(AppID,key,function,ID,Name,actionArrayList);
        }
        else if(function==ListActionByUser)
        {
            String Name=actionParse.Receive_ListAction(string);
            ArrayList<Action>actionArrayList=ListActionByUser(AppID, Name);
            return actionParse.Send_ListAction(AppID,key,function,ID,Name,actionArrayList);
        }
        else if(function==DeleteRoleAct)
        {
            ArrayList<String> arrayList=actionParse.Receive_Role_Action(string);
            result=DeleteRoleAct(AppID,arrayList.get(0),arrayList.get(1));
            return actionParse.Send_RoleAction(AppID,key,function,ID,result);
        }
        else if(function==DeleteUserAct)
        {
            ArrayList<String> arrayList=actionParse.Receive_User_Action(string);
            result=DeleteUserAct(AppID, arrayList.get(0), arrayList.get(1));
            return actionParse.Send_UserAction(AppID, key, function, ID, result);
        }
        else return null;
    }
    @Override
    public int CreateAct(Action act)
    {
        int result = -1;
        if (act.getActName() == null || act.getActName().equals(""))
            return ACTION_NAME_NULL;
        result = actionTable.Create(act.getAppID(), act.getActName().trim(), act.getActDes().trim());
        if (result == 0)
            return ERROR;
        else if (result == 1)
            return SUCCESS;
        else if (result == 2)
            return ACTION_REALLY_HAVE;
        return result;

    }
    @Override
    public int DeleteAct(int AppID, String ActName)
    {
        int result=-1;
        if(ActName==null||ActName.equals(""))
            return ACTION_NAME_NULL;
        Action action=Find(AppID,ActName);
        if(action==null)
            return ACTION_NOT_HAVE;
        ActionBackup actionBackup=new ActionBackup(action.getID(),action.getAppID(),action.getActName(),action.getActDes(),null);
        controllerBackup.action(actionBackup);
        result =actionTable.Delete(AppID, ActName);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        return result;
    }

    @Override
    public int UpdateAct(int AppID,String OldActName,String NewActName,String ActDes)
    {
        int result=-1;
        if(NewActName==null||NewActName.equals(""))
            return ACTION_NAME_NULL;
        result=this.actionTable.Update(AppID, OldActName, NewActName, ActDes);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return ACTION_NOT_HAVE;
        else if(result==3)
            return ACTION_NAME_DUPLICATE;
        return result;
    }

    @Override
    public int AddRoleAct(int AppID, String RoleName, String ActName)
    {
        if(RoleName==null||RoleName.equals(""))
            return ROLE_NAME_NULL;
        if(ActName==null||ActName.equals(""))
            return ACTION_NAME_NULL;

        int result=-1;
        result =role_actTable.Create(AppID,RoleName,ActName);
        if(result==0)
            return ERROR;
        else if(result==2)
            return ACTION_NOT_HAVE;
        else if(result==3)
            return ROLE_NOT_HAVE;
        else if(result==4)
            return ACTION_ROLE_REALLY_HAVE;

        ArrayList<User> userArrayList=userTable.FindByRole(AppID, RoleName);
        for(User u:userArrayList)
        {
            result=user_actTable.Create(AppID,u.getUserName(),ActName);
            if(result==ERROR)
                return result;
        }
        return result;
    }
    @Override
    public int AddUserAct(int AppID, String UserName, String ActName)
    {
        if(UserName==null||UserName.equals(""))
            return USER_NAME_NULL;
        if(ActName==null||ActName.equals(""))
            return ACTION_NAME_NULL;

        int result=-1;
        result =user_actTable.Create(AppID,UserName,ActName);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return ACTION_NOT_HAVE;
        else if(result==3)
            return USER_NOT_HAVE;
        else if(result==4)
            return ACTION_USER_REALLY_HAVE;
        return result;
    }

    @Override
    public Action Find(int AppID, String ActName)
    {
        Action act=actionTable.Find(AppID, ActName.trim());
        return act;
    }
    @Override
    public ArrayList<Action> ListActionByApp(int AppID)
    {
        ArrayList<Action> array=actionTable.FindByApp(AppID);
        return array;
    }
    @Override
    public ArrayList<Action> ListActionByRole(int AppID, String RoleName)
    {
        ArrayList<Action> array=actionTable.FindByRole(AppID, RoleName);
        return  array;
    }

    @Override
    public ArrayList<Action> ListActionByUser(int AppID, String UserName)
    {
        ArrayList<Action> array=actionTable.FindByUser(AppID, UserName);
        return  array;
    }

    @Override
    public int DeleteRoleAct(int AppID, String RoleName, String ActName)
    {
        if(RoleName==null||RoleName.equals(""))
            return ROLE_NAME_NULL;
        if(ActName==null||ActName.equals(""))
            return ACTION_NAME_NULL;

        int result=-1;
        result=role_actTable.Delete(AppID,RoleName,ActName);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return ACTION_NOT_HAVE;
        else if(result==3)
            return ROLE_NOT_HAVE;
        else if(result==4)
            return ACTION_ROLE_NOT_HAVE;
        return result;
    }

    @Override
    public int DeleteUserAct(int AppID, String UserName, String ActName)
    {
        if(UserName==null||UserName.equals(""))
            return USER_NAME_NULL;
        if(ActName==null||ActName.equals(""))
            return ACTION_NAME_NULL;

        int result=-1;
        result =user_actTable.Delete(AppID,UserName,ActName);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return ACTION_NOT_HAVE;
        else if(result==3)
            return USER_NOT_HAVE;
        else if(result==4)
            return ACTION_USER_NOT_HAVE;
        return result;
    }
}
