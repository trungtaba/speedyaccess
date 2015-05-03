package Controller;

import Backup.Controller.ControllerBackup;
import Backup.Entity.RoleBackup;
import Interfaces.IRole;
import Model.RoleTable;
import Table.Role;
import XML.ParseXML.RoleParse;

import java.util.ArrayList;

import static MyCode.MyCodeFunction.*;
import static MyCode.MyException.*;

public class CRole implements IRole
{
    private final RoleTable roleTable;
    private final RoleParse roleParse;
    private ControllerBackup controllerBackup;

    public CRole()
    {
        this.roleTable=new RoleTable();
        this.roleParse=new RoleParse();
        this.controllerBackup=ControllerBackup.getInstance();
    }

    String action(int AppID,String key,int ID,int function,String string)
    {
        if(function==CreateRole)
        {
            ArrayList<String>arrayList =roleParse.Receive_Create_Role(string);
            Role role= new Role(1,arrayList.get(0),arrayList.get(1),AppID);

            int result=CreateRole(role);
            return roleParse.Send_Result_Role(AppID,key,function,ID,result);
        }
        else if(function==DeleteRole)
        {
            String RoleName=roleParse.Receive_Delete_Find_Role(string);
            int result=DeleteRole(AppID,RoleName);
            return roleParse.Send_Result_Role(AppID,key,function,ID,result);
        }
        else if(function==UpdateRole)
        {
            ArrayList<String>arrayList =roleParse.Receive_Update_Role(string);
            int result=UpdateRole(AppID,arrayList.get(0),arrayList.get(1),arrayList.get(2));
            return roleParse.Send_Result_Role(AppID,key,function,ID,result);
        }
        else if(function==FindRole)
        {
            String RoleName=roleParse.Receive_Delete_Find_Role(string);
            Role role=Find(AppID,RoleName);
            return roleParse.Send_Find_Role(AppID,key,function,ID,role);
        }
        else if(function==ListRoleByApp)
        {
            String name=roleParse.Receive_ListRole(string);
            ArrayList<Role> arrayList=ListRoleByApp(AppID);
            return roleParse.Send_ListRole(AppID,key,function,ID,name,arrayList);
        }
        else if(function==ListRoleByAction)
        {
            String name=roleParse.Receive_ListRole(string);
            ArrayList<Role> arrayList=ListRoleByAction(AppID,name);
            return roleParse.Send_ListRole(AppID,key,function,ID,name,arrayList);
        }
        else if(function==ListRoleByUser)
        {
            String name=roleParse.Receive_ListRole(string);
            ArrayList<Role> arrayList=ListRoleByUser(AppID,name);
            return roleParse.Send_ListRole(AppID,key,function,ID,name,arrayList);
        }
        else return null;
    }
    @Override
    public int CreateRole(Role role)
    {
        int result = -1;
        if (role.getRoleName() == null || role.getRoleName().equals(""))
            return ROLE_NAME_NULL;
        result = roleTable.Create(role.getAppID(), role.getRoleName().trim(), role.getRoleDes().trim());
        if (result == 0)
            return ERROR;
        else if (result == 1)
            return SUCCESS;
        else if (result == 2)
            return ROLE_REALLY_HAVE;
        return result;
    }

    @Override
    public int DeleteRole(int AppID, String RoleName)
    {
        int result=-1;
        if(RoleName==null||RoleName.equals(""))
            return ROLE_NAME_NULL;
        Role role=Find(AppID,RoleName);
        if(role==null)
            return ROLE_NOT_HAVE;
        RoleBackup roleBackup=new RoleBackup(role.getID(),role.getRoleName(),role.getRoleDes(),role.getAppID(),null);
        controllerBackup.action(roleBackup);
        result=roleTable.Delete(AppID,RoleName);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        return result;
    }

    @Override
    public int UpdateRole(int AppID, String OldRoleName, String NewRoleName,String RoleDes)
    {
        int result=-1;
        if(NewRoleName==null||NewRoleName.equals(""))
            return ROLE_NAME_NULL;
        result=this.roleTable.Update(AppID, OldRoleName.trim(), NewRoleName.trim(), RoleDes.trim());
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return ROLE_NOT_HAVE;
        else if(result==3)
            return ROLE_NAME_DUPLICATE;
        return result;
    }

    @Override
    public Role Find(int AppID, String RoleName)
    {
        return roleTable.Find(AppID,RoleName.trim());
    }

    @Override
    public ArrayList<Role> ListRoleByApp(int AppID)
    {
        ArrayList<Role> array=roleTable.FindByApp(AppID);
        return  array;
    }

    @Override
    public ArrayList<Role> ListRoleByAction(int AppID, String ActName)
    {
        ArrayList<Role> array=roleTable.FindByAct(AppID,ActName.trim());
        return array;
    }

    @Override
    public ArrayList<Role> ListRoleByUser(int AppID, String UserName)
    {
        ArrayList<Role> array=roleTable.FindByUser(AppID,UserName.trim());
        return array;
    }
}
