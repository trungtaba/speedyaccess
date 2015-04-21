package Interfaces;

import Table.Action;
import java.util.ArrayList;

public interface IActionController
{
    public int CreateAct(Action act);//
    public int DeleteAct(int ActID);
    public int DeleteAct(int AppID,String ActName);//
    //public int DeleteAct(int ActID);
    public int UpdateAct(Action act);//
    public int AddRoleAct(int RoleID,int ActID);
    public int AddRoleAct(int AppID,String RoleName,String ActName);//
    public int AddUserAct(int UserID,int ActID);
    public int AddUserAct(int AppID,String UserName,String ActName);//
    public Action Find(int AppID,String ActName);
    public Action Find(int ActID);
//    public Role_action FindRoleAction(int RoleID,int ActID);
//    public User_action FindUserAction(int UserID,int ActID);
    public ArrayList<Action> ListActionByApp(int AppID);//
    public ArrayList<Action> ListActionByRole(int AppID,String RoleName);//
    public ArrayList<Action> ListActionByUser(int AppID,String UserName);//
    public int DeleteRoleAct(int RoleID, int ActionID);
    public int DeleteRoleAct(int AppID,String RoleName,String ActName);//
    public int DeleteUserAct(int UserID,int ActID);
    public int DeleteUserAct(int AppID,String UserName,String ActName);//
}
