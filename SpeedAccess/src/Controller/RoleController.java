package Controller;

import static MyCode.MyException.*;
import static MyCode.MyCodeFunction.*;
import Interfaces.IRoleController;
import Table.*;
import Model.*;
import Table.Role_action;
import XML.ParseXML.MainParse;
import java.util.ArrayList;

public class RoleController implements IRoleController
{
    private final RoleTable roleTable;
    private final Role_actionTable roActTable;
    private final User_roleTable UsRoTable;
    private final ActionTable actTable;
    private final UserTable userTable;
    private final MainParse parse;

    public RoleController() 
    {
        this.roleTable = new RoleTable();
        this.roActTable = new Role_actionTable();
        this.UsRoTable = new User_roleTable();
        this.actTable=new ActionTable();
        this.userTable=new UserTable();
        this.parse=new MainParse();
    }
    public String action(int function,String string)
    {
        if(function==CREATE_ROLE)
        {
            Role role=parse.Receive_Role(string);
            int result=CreateRole(role);
            Role tmp=Find(role.getRoleName(), role.getAppID());
            return parse.Send_Role(tmp.getAppID(), function, tmp, result);
        }
        else if(function==DELETE_ROLE)
        {
            Role role=parse.Receive_Role(string);

            int result=DeleteRole(role.getAppID(), role.getRoleName());
            return parse.Send_Role(role.getAppID(), function, role, result);
        }
        else if(function==UPDATE_ROLE)
        {
            Role role=parse.Receive_Role(string);
            int result=UpdateRole(role);
            return parse.Send_Role(role.getAppID(), function, role, result);
        }
        else if(function==LIST_ROLE_BY_APP)
        {
            ArrayList<String> arr=parse.Receive_List(string);
            int AppID=Integer.parseInt(arr.get(0));
            ArrayList<Role> list=ListRoleByApp(AppID);
            return parse.Send_ListRole(AppID, arr.get(1), function, list);
        }
        else if(function==LIST_ROLE_BY_USER)
        {
            ArrayList<String> arr=parse.Receive_List(string);
            int AppID=Integer.parseInt(arr.get(0));
            String UserName=arr.get(1);
            ArrayList<Role> list=ListRoleByUser(AppID, UserName);
            return parse.Send_ListRole(AppID, arr.get(1), function, list);
        }
        else if(function==LIST_ROLE_BY_ACT)
        {
            ArrayList<String> arr=parse.Receive_List(string);
            int AppID=Integer.parseInt(arr.get(0));
            String ActName=arr.get(1);
            ArrayList<Role> list=ListRoleByAction(AppID, ActName);
            return parse.Send_ListRole(AppID, arr.get(1), function, list);
        }
        else
        {
            return null;
        }
    }
    /*==============================================
                        TEST
    ==============================================*/
    @Override
    public int CreateRole(Role role) 
    {
        Role tmp=roleTable.Find(role.getAppID(),role.getRoleName());
        if(tmp!=null)
            return REALLY_HAVE_ROLE;
        boolean check= roleTable.Create(role);
        if(check==false)
            return ADD_ROLE_ERROR;
        else
            return ADD_ROLE_OK;
    }
    /*
    1:Xoa toán bộ thong tin trong bảng Role_action có RoleID tương ứng
    2:Xóa toàn bộ thong tin trong bảng User_role có RoleID tương ứng
    3:Xóa toàn bộ thong tin trong bảng Role có RoleID tương ứng
    */
    @Override
    public int DeleteRole(int RoleID) 
    {
        boolean check;
        
        //1:Xóa toàn bộ thông tin trong bảng Role_action 
        for(Role_action roAct:roActTable.FindByRole(RoleID))
        {
            check=roActTable.Delete(roAct.getID());
            if(check==false) return DELETE_ROLE_ACTION_ERROR;
        }
        
        //2:Xóa toàn bộ thong tin trong bảng User_role có RoleID tương ứng
        for(User_role usRole:UsRoTable.FindByRole(RoleID))
        {
            check=UsRoTable.Delete(usRole.getID());
            if(check==false)return DELETE_USER_ROLE_ERROR;
        }
        
        //3:Xóa toàn bộ thong tin trong bảng Role có RoleID tương ứng
        check=roleTable.Delete(RoleID);
        if(check==false)
            return DELETE_ROLE_ERROR;
        else
            return DELETE_ROLE_OK;
    }
    
    @Override
    public int DeleteRole(int AppID, String RoleName) 
    {
        Role role=Find(RoleName, AppID);
        if(role==null)
            return NOT_HAVE_ROLE;
        return DeleteRole(role.getID());
    }
    
    @Override
    public int UpdateRole(Role role) 
    {
        Role tmp=Find(role.getID());
        if(tmp==null)
            return NOT_HAVE_ROLE;
        boolean check= roleTable.Update(role);
        if(check==false)
            return UPDATE_ROLE_ERROR;
        else
            return UPDATE_ROLE_OK;
    }

    @Override
    public Role Find(int RoleID) 
    {
        return roleTable.Find(RoleID);
    }

    /*==============================================
                        TEST
    ==============================================*/
    @Override
    public Role Find(String RoleName, int AppID) 
    {
        return roleTable.Find(AppID,RoleName);
    }

    @Override
    public ArrayList<Role> ListRoleByApp(int AppID) 
    {
        return roleTable.ListRolebyAppID(AppID);
    }

    /*
    1:lấy RoleID trong bảng User_role theo UserID 
    2:Lấy thông tin trong bảng Role
    */
    @Override
    public ArrayList<Role> ListRoleByUser(int UserID) 
    {
        ArrayList<Role> array=new ArrayList<>();
        
        UsRoTable.FindByUser(UserID).stream().forEach((usRo) -> {
            array.add(roleTable.Find(usRo.getRoleID()));
        });
            
        return array;
    }
    
    @Override
    public ArrayList<Role> ListRoleByUser(int AppID, String UserName) 
    {
        User user=userTable.Find(AppID, UserName);
        return ListRoleByUser(user.getID());
    }
    /*
    1:lấy RoleID trong bảng Role_action theo ActID 
    2:Lấy thông tin trong bảng Role
    */
    
    @Override
    public ArrayList<Role> ListRoleByAction(int ActID) 
    {
         ArrayList<Role> array=new ArrayList<>();
        
        roActTable.FindByAction(ActID).stream().forEach((roAct) -> {
            array.add(roleTable.Find(roAct.getRoleID()));
        });
            
        return array;
    }

    @Override
    public ArrayList<Role> ListRoleByAction(int AppID, String ActName)
    {
        Action action=actTable.Find(AppID, ActName);
        return ListRoleByAction(action.getID());
    }
    
}
