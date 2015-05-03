package Interfaces;

import Table.Role;

import java.util.ArrayList;

public interface IRole
{
    int CreateRole(Role role);
    int DeleteRole(int AppID, String RoleName);
    int UpdateRole(int AppID, String OldRoleName, String NewRole, String RoleDes);
    Role Find(int AppID, String RoleName);
    ArrayList<Role> ListRoleByApp(int AppID);
    ArrayList<Role> ListRoleByAction(int AppID, String ActName);
    ArrayList<Role> ListRoleByUser(int AppID, String UserName);
}
