package Interfaces;

import Table.User;

import java.util.ArrayList;

public interface IUser
{
    int CreateUser(int AppID, String UserName, String Password, String Email);
    int DeleteUser(int AppID, String UserName);
    int CloseUser(int AppID, String UserName);
    int UpdateUserName(int AppID, String OldUserName, String NewUserName);
    int UpdateEmail(int Appid, String UserName, String newEmail);
    User Find(int AppID, String UserName);
    ArrayList<User> ListUserByApp(int AppID);
    ArrayList<User> ListUserByRole(int AppID, String RoleName);
    ArrayList<User> ListUserByAct(int AppID, String ActName);
    int DeleteUserRole(int AppID, String UserName, String RoleName);
    int AddUserRole(int AppID, String UserName, String RoleName);
    int ChangePasswordByUser(int AppID, String Username, String OldPassword, String NewPassword);
    int ChangePasswordByAdmin(int AppID, String Username, String NewPassword);
    int SignIn(int AppID, String UserName, String Password);
    int LogOut(int AppID, String UserName);
    int CheckUserAct(int AppID, String UserName, String ActName);
}
