package Interfaces;

import Table.Role;
import java.util.ArrayList;

public interface IRoleController
{
    public int CreateRole(Role role);
    public int DeleteRole(int RoleID);
    public int DeleteRole(int AppID, String RoleName);
    public int UpdateRole(Role app);
    public Role Find(int RoleID);
    public Role Find(String RoleName,int AppID);
    public ArrayList<Role> ListRoleByApp(int AppID);
    public ArrayList<Role> ListRoleByAction(int ActID);
    public ArrayList<Role> ListRoleByAction(int AppID,String ActName);
    public ArrayList<Role> ListRoleByUser(int UserID);
    public ArrayList<Role> ListRoleByUser(int AppID,String UserName);
    
}
