package Interfaces;

import MyClass.MyUser;
import Table.User;
import Table.User_action;
import java.util.ArrayList;

public interface IUserController 
{
    public int CreateUser(MyUser myUser);
    public int DeleteUser(int UserID);
    public int DeleteUser(int AppID,String UserName);
    public int CloseUser(int UserID);
    public int CloseUser(int AppID,String UserName);
    public int UpdateUser(User user);
    public User Find(int UserID);
    public User Find(int AppID,String UserName);
    public ArrayList<MyUser> ListUserByApp(int AppID);
    public ArrayList<MyUser> ListUserByRole(int RoleID);
    public ArrayList<MyUser> ListUserByRole(int AppID,String RoleName);
    public ArrayList<MyUser> ListUserByAct(int ActID);
    public ArrayList<MyUser> ListUserByAct(int AppID,String ActName);
    public int DelteUserRole(int UserID,int RoleID);
    public int DelteUserRole(int AppID,String UserName,String RoleName);
    public int AddUserRole(int UserID,int RoleID);
    public int AddUserRole(int AppID,String UserName,String RoleName);
    
    public User_action FindUserAction(int UserID,int ActID);
    public User_action FindUserAction(int AppID,String UserName,String ActName);
    
    public int ChangePasswordByUser(int AppID,String Username,String OldPassword,String NewPassword);
    public int ChangePasswordByAdmin(int AppID,String Username,String NewPassword);
    //public int ChangPasswordQuestionByAdmin(int UserID,String Question,String Answer);
    public int ChangePasswordQuestionByAdmin(int AppID,String UserName,String Question,String Answer);
    //public int ChangPasswordQuestionByUser(int UserID,String Question,String Answer);
    public int ChangePasswordQuestionByUser(int AppID,String UserName,String Question,String Answer);
    public int CheckSignIn(int AppID,String UserName,String Password);
}
