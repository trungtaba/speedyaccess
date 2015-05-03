package Controller;

import Backup.Controller.ControllerBackup;
import Backup.Entity.UserBackup;
import Interfaces.IUser;
import Model.ConfigTable;
import Model.UserTable;
import Model.User_RoleTable;
import Table.Config;
import Table.User;
import XML.ParseXML.UserParse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static MyCode.MyCodeFunction.*;
import static MyCode.MyException.*;

public class CUser implements IUser
{
    private UserTable userTable;
    private ArrayList<Config> arrayConfigs =new ArrayList<>();
    private final User_RoleTable user_roleTable;
    private final ConfigTable configTable;
    private final UserParse userParse;
    private ControllerBackup controllerBackup;

    public CUser()
    {
        this.userTable=new UserTable();
        this.user_roleTable=new User_RoleTable();
        this.configTable=new ConfigTable();
        this.arrayConfigs=LoadConfig();
        this.userParse=new UserParse();
        this.controllerBackup=ControllerBackup.getInstance();
    }

    String action(int AppID,String key,int ID,int function,String string)
    {
        if(function==CreateUser)
        {
            ArrayList<String> arrayList=userParse.Receive_Create_User(string);
            int result=CreateUser(AppID,arrayList.get(0),arrayList.get(1),arrayList.get(2));
            return userParse.Send_Result_User(AppID,key,function,ID,result);
        }
        else if(function==DeleteUser)
        {
            String UserName=userParse.Receive_Delete_Find_Close_User(string);
            int result=DeleteUser(AppID,UserName);
            return userParse.Send_Result_User(AppID,key,function,ID,result);
        }
        else if(function==CloseUser)
        {
            String name=userParse.Receive_Delete_Find_Close_User(string);
            int result=CloseUser(AppID,name);
            return userParse.Send_Result_User(AppID,key,function,ID,result);
        }
        else if(function==UpdateUserName)
        {
            ArrayList<String> arrayList=userParse.Receive_Update_UserName(string);
            int result=UpdateUserName(AppID,arrayList.get(0),arrayList.get(1));
            return userParse.Send_Result_User(AppID,key,function,ID,result);
        }
        else if(function==UpdateEmail)
        {
            ArrayList<String> arrayList=userParse.Receive_Update_Email(string);
            int result=UpdateEmail(AppID, arrayList.get(0), arrayList.get(1));
            return userParse.Send_Result_User(AppID,key,function,ID,result);
        }
        else if(function==FindUser)
        {
            String name=userParse.Receive_Delete_Find_Close_User(string);
            User user=Find(AppID,name);
            return userParse.Send_Find_User(AppID,key,function,ID,user);
        }
        else if(function==ListUserByApp)
        {
            String name=userParse.Receive_ListUser(string);
            ArrayList<User> arrayList=ListUserByApp(AppID);
            return userParse.Send_ListUser(AppID,key,function,ID,name,arrayList);
        }
        else if(function==ListUserByRole)
        {
            String name=userParse.Receive_ListUser(string);
            ArrayList<User> arrayList=ListUserByRole(AppID,name);
            return userParse.Send_ListUser(AppID,key,function,ID,name,arrayList);
        }
        else if(function==ListUserByAct)
        {
            String name=userParse.Receive_ListUser(string);
            ArrayList<User> arrayList=ListUserByAct(AppID, name);
            return userParse.Send_ListUser(AppID,key,function,ID,name,arrayList);
        }
        else if (function==DeleteUserRole)
        {
            ArrayList<String> arrayList=userParse.Receive_UserRole(string);
            int result=DeleteUserRole(AppID,arrayList.get(0),arrayList.get(1));
            return userParse.Send_UserRole(AppID,key,function,ID,result);
        }
        else if (function==AddUserRole)
        {
            ArrayList<String> arrayList=userParse.Receive_UserRole(string);
            int result=AddUserRole(AppID, arrayList.get(0), arrayList.get(1));
            return userParse.Send_UserRole(AppID,key,function,ID,result);
        }
        else if(function==ChangePasswordByUser)
        {
            ArrayList<String> arrayList=userParse.Receive_ChangePasswordByUser(string);
            int result=ChangePasswordByUser(AppID,arrayList.get(0),arrayList.get(1),arrayList.get(2));
            return userParse.Send_ChangePassword(AppID,key,function,ID,arrayList.get(0),result);
        }

        else if(function==ChangePasswordByAdmin)
        {
            ArrayList<String> arrayList=userParse.Receive_ChangePasswordByAdmin(string);
            int result=ChangePasswordByAdmin(AppID, arrayList.get(0), arrayList.get(1));
            return userParse.Send_ChangePassword(AppID,key,function,ID,arrayList.get(0),result);
        }
        else if(function==SignIn)
        {
            ArrayList<String> arrayList=userParse.Receive_CheckSignIn(string);
            int result=SignIn(AppID,arrayList.get(0),arrayList.get(1));
            return userParse.Send_CheckSignIn(AppID,key,function,ID,arrayList.get(0),arrayList.get(1),result);
        }
        else if(function==LogOut)
        {
            String name=userParse.Receive_Logout(string);
            int result=LogOut(AppID,name);
            return userParse.Send_Logout(AppID,key,function,ID,name,result);
        }
        else if(function==CheckUserAct)
        {
            ArrayList<String> arrayList=userParse.Receive_CheckUserAction(string);
            int result=CheckUserAct(AppID,arrayList.get(0),arrayList.get(1));
            return userParse.Send_CheckUserAction(AppID,key,function,ID,arrayList.get(0),arrayList.get(1),result);
        }
        else return null;
    }
    @Override
    public int CreateUser(int AppID,String UserName,String Password,String Email)
    {
        if(UserName==null||UserName.equals(""))
            return USER_NAME_NULL;
        if(UserName.matches(arrayConfigs.get(AppID).getUserNameValidate().trim()))
            return USER_NAME_NOT_VALIDATE;
        if(Password.matches(arrayConfigs.get(AppID).getPasswordValidate().trim()))
            return PASSWORD_NOT_VALIDATE;
        int result =-1;
        result=userTable.Create(AppID, UserName, CreateMD5(Password), Email);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return USER_REALLY_HAVE;
        return result;
    }

    @Override
    public int DeleteUser(int AppID, String UserName)
    {
        int result=-1;
        if(UserName==null||UserName.equals(""))
            return USER_NAME_NULL;
        User user=Find(AppID,UserName);
        if (user==null)
            return USER_NOT_HAVE;
        UserBackup userBackup=new UserBackup(user.getID(),user.getAppID(),user.getUserName(),user.getPasswordUser(),
                user.getEmail(),user.getCreateDate(),null);
        controllerBackup.action(userBackup);
        result=userTable.Delete(AppID, UserName);
        if (result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        return result;
    }

    @Override
    public int CloseUser(int AppID, String UserName)
    {
        if(UserName.trim()==null||UserName.trim().equals(""))
            return USER_NAME_NULL;
        int result=-1;
        result=userTable.Close(AppID, UserName);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return USER_NOT_HAVE;
        else if(result==3)
            return USER_REALLY_CLOSE;
        return result;
    }

    @Override
    public int UpdateUserName(int AppID, String OldUserName, String NewUserName)
    {
        OldUserName=OldUserName.trim();
        NewUserName=NewUserName.trim();

        if(OldUserName.equals("")||OldUserName==null)
            return USER_NAME_NULL;
        if(NewUserName.equals("")||NewUserName==null)
            return USER_NAME_NEW_NULL;
        if(OldUserName.equals(NewUserName))
            return USER_NAME_NOT_CHANGE;
        int check =CheckUserAct(AppID,OldUserName.trim(),"changneusername");
        if(check!=1)
            return USER_NOT_HAVE_PERMISSION;
        int result=-1;
        result=userTable.UpdateUserName(AppID, OldUserName, NewUserName);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return USER_NOT_HAVE;
        else if(result==3)
            return USER_NOT_APPROVED;
        else if(result==4)
            return USER_IS_LOCKOUT;
        else if(result==5)
            return USER_NAME_NEW_DUPLICATE;
        return result;
    }

    @Override
    public int UpdateEmail(int AppID, String UserName, String newEmail)
    {
        UserName=UserName.trim();
        newEmail=newEmail.trim();

        if(UserName.equals("")||UserName==null)
            return USER_NAME_NULL;
        if(newEmail.equals("")||newEmail==null)
            return EMAIL_NULL;

        int check =CheckUserAct(AppID,UserName,"changeemail");
        if(check!=1)
            return USER_NOT_HAVE_PERMISSION;
        int result=-1;
        result=userTable.UpdateEmail(AppID, UserName, newEmail);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return USER_NOT_HAVE;
        else if(result==3)
            return USER_NOT_APPROVED;
        else if(result==4)
            return USER_IS_LOCKOUT;
        else if(result==5)
            return EMAIL_NOT_CHANGE;
        return result;
    }

    @Override
    public User Find(int AppID, String UserName)
    {
        return userTable.Find(AppID,UserName);
    }

    @Override
    public ArrayList<User> ListUserByApp(int AppID)
    {
        ArrayList<User> result=userTable.FindByApp(AppID);
        return  result;
    }

    @Override
    public ArrayList<User> ListUserByRole(int AppID, String RoleName)
    {
        ArrayList<User> result=userTable.FindByRole(AppID, RoleName);
        return  result;
    }

    @Override
    public ArrayList<User> ListUserByAct(int AppID, String ActName)
    {
        ArrayList<User> result=userTable.FindByAct(AppID, ActName);
        return  result;
    }

    @Override
    public int DeleteUserRole(int AppID, String UserName, String RoleName)
    {
        UserName=UserName.trim();
        RoleName=RoleName.trim();
        if(UserName==null||UserName.equals(""))
            return USER_NAME_NULL;
        if(RoleName==null||RoleName.equals(""))
            return ROLE_NAME_NULL;

        int result=-1;
        result=user_roleTable.Delete(AppID, UserName, RoleName);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return ROLE_NOT_HAVE;
        else if(result==3)
            return USER_NOT_HAVE;
        else if(result==4)
            return USER_ROLE_NOT_HAVE;
        return result;
    }

    @Override
    public int AddUserRole(int AppID, String UserName, String RoleName)
    {
        if(UserName==null||UserName.equals(""))
            return USER_NAME_NULL;
        if(RoleName==null||RoleName.equals(""))
            return ROLE_NAME_NULL;

        int result=-1;
        result=user_roleTable.Create(AppID, UserName, RoleName);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return USER_NOT_HAVE;
        else if(result==3)
            return ROLE_NOT_HAVE;
        else if(result==4)
            return USER_ROLE_REALLY_HAVE;
        return result;
    }

    @Override
    public int ChangePasswordByUser(int AppID, String UserName,String OldPassword, String NewPassword)
    {
        if(UserName==null||UserName.equals(""))
            return USER_NAME_NULL;
        if(NewPassword.matches(arrayConfigs.get(AppID).getPasswordValidate()))
            return NEW_PASS_NOT_VALIDATE;
        int check =CheckUserAct(AppID,UserName,"changepassword");
        if(check!=1)
            return USER_NOT_HAVE_PERMISSION;
        int result=-1;
        result =userTable.ChangePasswordByUser(AppID, UserName, CreateMD5(OldPassword), CreateMD5(NewPassword));
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return USER_NOT_HAVE;
        else if (result==3)
            return USER_NOT_APPROVED;
        else if(result==4)
            return USER_IS_LOCKOUT;
        else if(result==5)
            return PASSWORD_NOT_CORRECT;
        return result;
    }

    @Override
    public int ChangePasswordByAdmin(int AppID, String UserName, String NewPassword)
    {
        if(UserName==null||UserName.equals(""))
            return USER_NAME_NULL;
        if(NewPassword.matches(arrayConfigs.get(AppID).getPasswordValidate()))
            return NEW_PASS_NOT_VALIDATE;

        int result=-1;
        result =userTable.ChangePasswordByAdmin(AppID, UserName, CreateMD5(NewPassword));
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return USER_NOT_HAVE;
        else if (result==3)
            return OLD_NEW_PASS_EQUAL;
        return result;
    }

    @Override
    public int SignIn(int AppID, String UserName, String Password)
    {
        if(UserName==null||UserName.equals(""))
            return USER_NAME_NULL;
        int check=CheckUserAct(AppID,UserName,"signin");
        if(check!=1)
            return USER_NOT_HAVE_PERMISSION;
        int result=-1;
        result=userTable.SignIn(AppID, UserName, CreateMD5(Password));
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return USER_NOT_HAVE;
        else if(result==3)
            return USER_NOT_APPROVED;
        else if(result==4)
            return USER_REALLY_LOGIN;
        else if(result==5)
        {
            //lay thong tin ve so lan dang nhap sai
            User user=Find(AppID,UserName);
            if (user==null)
                return ERROR;
            else
            {

            }
            return PASSWORD_NOT_CORRECT;
        }
         return result;
    }

    @Override
    public int LogOut(int AppID, String UserName)
    {
        if(UserName==null||UserName.equals(""))
            return USER_NAME_NULL;
        int check=CheckUserAct(AppID,UserName,"logout");
        if(check!=1)
            return USER_NOT_HAVE_PERMISSION;
        int result=-1;
        result=userTable.Logout(AppID, UserName);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return USER_NOT_HAVE;
        else if(result==3)
            return USER_NOT_APPROVED;
        else if(result==4)
            return USER_REALLY_LOGOUT;
        return result;
    }

    @Override
    public int CheckUserAct(int AppID, String UserName, String ActName)
    {
        int result=-1;
        result=userTable.CheckUserAct(AppID, UserName.trim(), ActName.trim());
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        else if(result==2)
            return USER_NOT_HAVE;
        else if(result==3)
            return ACTION_NOT_HAVE;
        else if(result==4)
            return USER_NOT_HAVE_PERMISSION;
        return result;
    }

    public ArrayList<Config> LoadConfig()
    {
        ArrayList<Config> array=new ArrayList<>();
        array=configTable.LoadConfig();
        return array;
    }

    private  String CreateMD5(String string)
    {
        MessageDigest md = null;

        try
        {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        md.update(string.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
