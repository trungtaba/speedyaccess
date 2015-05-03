package Interfaces;

import Table.Action;

import java.util.ArrayList;

public interface IAction
{
    int CreateAct(Action act);
    int DeleteAct(int AppID, String ActName);
    int UpdateAct(int AppID, String OldActName, String NewActName, String ActDes);
    int AddRoleAct(int AppID, String RoleName, String ActName);
    int AddUserAct(int AppID, String UserName, String ActName);
    Action Find(int AppID, String ActName);
    ArrayList<Action> ListActionByApp(int AppID);
    ArrayList<Action> ListActionByRole(int AppID, String RoleName);
    ArrayList<Action> ListActionByUser(int AppID, String UserName);
    int DeleteRoleAct(int AppID, String RoleName, String ActName);
    int DeleteUserAct(int AppID, String UserName, String ActName);
}
