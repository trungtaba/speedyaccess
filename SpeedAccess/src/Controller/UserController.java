package Controller;

import static MyCode.MyException.ADD_MEM_ERROR;
import static MyCode.MyException.ADD_USER_ERROR;
import static MyCode.MyException.ADD_USER_OK;
import static MyCode.MyException.DELETE_USER_ACTION_ERROR;
import static MyCode.MyException.DELETE_USER_ERROR;
import static MyCode.MyException.DELETE_USER_OK;
import static MyCode.MyException.UPDATE_USER_ERROR;
import static MyCode.MyException.UPDATE_USER_OK;
import Interfaces.IUserController;
import Model.*;
import MyClass.MyUser;
import static MyCode.MyCodeFunction.*;
import static MyCode.MyException.ADD_USER_ACTION_ERROR;
import static MyCode.MyException.ADD_USER_ROLE_ERROR;
import static MyCode.MyException.ADD_USER_ROLE_OK;
import static MyCode.MyException.CHANGE_PAWW_ERROR;
import static MyCode.MyException.CHANGE_PAWW_NOT_PERMISSION;
import static MyCode.MyException.CHANGE_PAWW_OK;
import static MyCode.MyException.CHANGE_PAWW_QUE_ERROR;
import static MyCode.MyException.CHANGE_PAWW_QUE_NOT_PERMISSION;
import static MyCode.MyException.CHANGE_PAWW_QUE_OK;
import static MyCode.MyException.CLOSE_USER_ERROR;
import static MyCode.MyException.CLOSE_USER_OK;
import static MyCode.MyException.DELETE_MEM_ERROR;
import static MyCode.MyException.DELETE_USER_ROLE_ERROR;
import static MyCode.MyException.DELETE_USER_ROLE_OK;
import static MyCode.MyException.IS_APPROVED_ERROR;
import static MyCode.MyException.NOT_HAVE_USER;
import static MyCode.MyException.NOT_HAVE_USER_MEM;
import static MyCode.MyException.NOT_HAVE_USER_ROLE;
import static MyCode.MyException.OUT_MAX_FAIL_PASSWORD;
import static MyCode.MyException.PASSWORD_INCORRENT;
import static MyCode.MyException.REALLY_HAVE_USER;
import static MyCode.MyException.REALLY_HAVE_USER_ROLE;
import static MyCode.MyException.SIGN_IN_OK;
import Table.*;
import XML.ParseXML.MainParse;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserController implements IUserController
{
    private final UserTable userTable;
    private final MembershipTable memTable;
    private final RoleTable roleTable;
    private final User_roleTable usRoleTable;
    private final User_actionTable usActTable;
    private final Role_actionTable roActTable;
    private final ActionTable actTable;
    private final MainParse parse;

    public UserController() 
    {
        userTable=new UserTable();
        usRoleTable=new User_roleTable();
        usActTable=new User_actionTable();
        memTable=new MembershipTable();
        roleTable=new RoleTable();
        actTable=new ActionTable();
        parse=new MainParse();
        roActTable=new Role_actionTable();
    }
    
    public String action(int function,String string)
    {
        if(function==CREATE_USER)
        {
            MyUser myUser=parse.Receive_User(string);
            int result=CreateUser(myUser);
            return parse.Send_User(myUser.getAppID(), myUser, function, result);
        }
        else if(function==DELETE_USER)
        {
            MyUser myUser=parse.Receive_User(string);
            int AppID=myUser.getAppID();
            String UserName=myUser.getUserName();
            int result=DeleteUser(AppID, UserName);
            return parse.Send_User(AppID, myUser, function, result);
        }
        else if(function==UPDATE_USER)
        {
            MyUser myUser=parse.Receive_User(string);
            int AppID=myUser.getAppID();
            User user=new User(myUser.getID(), myUser.getAppID(), myUser.getUserName(), getCurrentTime() );
            int result=UpdateUser(user);
            return parse.Send_User(AppID, myUser, function, result);
        }
        else if(function==LIST_USER_BY_APP)
        {
            ArrayList<String> arr=parse.Receive_List(string);
            int AppID=Integer.parseInt(arr.get(0));
            ArrayList<MyUser> list=ListUserByApp(AppID);
            return parse.Send_ListUser(AppID, arr.get(1), function, list);
        }
        else if(function==LIST_USER_BY_ROLE)
        {
            ArrayList<String> arr=parse.Receive_List(string);
            int AppID=Integer.parseInt(arr.get(0));
            String RoleName=arr.get(1);
            
            ArrayList<MyUser> list=ListUserByRole(AppID, RoleName);
            return parse.Send_ListUser(AppID, RoleName, function, list);
        }
        else if(function==LIST_USER_BY_ACT)
        {
            ArrayList<String> arr=parse.Receive_List(string);
            int AppID=Integer.parseInt(arr.get(0));
            String ActName=arr.get(1);
            
            ArrayList<MyUser> list=ListUserByAct(AppID, ActName);
            return parse.Send_ListUser(AppID, ActName, function, list);
        }
        else if(function==DELETE_USER_ROLE)
        {
            ArrayList<String>array=parse.Receive_UserRole(string);
            int AppID=Integer.parseInt(array.get(0));
            String UserName=array.get(1);
            String RoleName=array.get(2);
            int result=DelteUserRole(AppID, UserName, RoleName);
            return parse.Send_UserRole(AppID, function, array, result);
        }
        else if(function==CREATE_USER_ROLE)
        {
            ArrayList<String>array=parse.Receive_UserRole(string);
            int AppID=Integer.parseInt(array.get(0));
            String UserName=array.get(1);
            String RoleName=array.get(2);
            int result=AddUserRole(AppID, UserName, RoleName);
            return parse.Send_UserRole(AppID, function, array, result);
        }
        else if(function==CHANGE_PASS_BY_USER)
        {
            ArrayList<String> array=parse.Receive_ChanglePasswordByUser(string);
            int AppID=Integer.parseInt(array.get(0));
            String UserName=array.get(1);
            String OldPassword=array.get(2);
            String NewPassword=array.get(3);
            
            int result=ChangePasswordByUser(AppID, UserName, OldPassword, NewPassword);
            return parse.Send_ChangePassword(AppID, function, UserName, result);
        }
        else if(function==CHANGE_PASS_BY_ADMIN)
        {
            ArrayList<String> array=parse.Receive_ChanglePasswordByAdmin(string);
            int AppID=Integer.parseInt(array.get(0));
            String UserName=array.get(1);
            String NewPassword=array.get(2);
            
            int result=ChangePasswordByAdmin(AppID, UserName, NewPassword);
            return parse.Send_ChangePassword(AppID, function, UserName, result);
        }
        else if(function==CHANGE_PASS_QUE_BY_USER)
        {
            ArrayList<String> array=parse.Receive_ChanglePasswordQuestion(string);
            int AppID=Integer.parseInt(array.get(0));
            String UserName=array.get(1);
            String Question=array.get(2);
            String Answer=array.get(3);
            
            int result=ChangePasswordQuestionByUser(AppID, UserName, Question, Answer);
            return parse.Send_ChanglePasswordQuestion(AppID, function, UserName, result);
        }
        else if(function==CHANGE_PASS_QUE_BY_ADMIN)
        {
            ArrayList<String> array=parse.Receive_ChanglePasswordQuestion(string);
            int AppID=Integer.parseInt(array.get(0));
            String UserName=array.get(1);
            String Question=array.get(2);
            String Answer=array.get(3);
            
            int result=ChangePasswordQuestionByAdmin(AppID, UserName, Question, Answer);
            return parse.Send_ChanglePasswordQuestion(AppID, function, UserName, result);
        }
        else if(function==CHECK_SIGN_IN)
        {
            ArrayList<String> array=parse.Receive_CheckSignIn(string);
            int AppID=Integer.parseInt(array.get(0));
            String UserName=array.get(1);
            String Password=array.get(2);
            
            int result=CheckSignIn(AppID, UserName, Password);
            return parse.Send_CheckSignIn(AppID, UserName, Password, result);
        }
        else 
        {
            return null;
        }               
    }
    
    //test
    @Override
    public int CreateUser(MyUser myUser) 
    {
        User user=new User(myUser.getID(), myUser.getAppID(), myUser.getUserName(), getCurrentTime() );
        User UserCheck=Find(myUser.getAppID(), myUser.getUserName());
        if(UserCheck!=null)
            return REALLY_HAVE_USER;
        
        boolean check=userTable.Create(user);       
        if(check==false)
            return ADD_USER_ERROR;
        
        User tmp=Find(myUser.getAppID(), myUser.getUserName());
        Membership mem=new Membership(1, tmp.getID(), myUser.getAppID(),
                myUser.getPassWord(), myUser.getPassWordFormat(), myUser.getEmail()
                , myUser.getPassWordQuestion(), myUser.getPassWordAnswer(),
                1, 0, getCurrentTime(), null,
                null, null, 0, null, 0, null,myUser.getMaxFailedPassword());
        
        check=memTable.Create(mem);     
        if(check==false)
            return ADD_MEM_ERROR;
        
        return ADD_USER_OK;
    }

    /*
    1:Delete all row in User_action table have UserID equal UserID of user we want delete
    2:Delete all row in User_role table have UserID equal UserID of user we want delele
    3:Delete membership
    4:Delete row in User table
    */
    //test
    @Override
    public int DeleteUser(int UserID) 
    {
        boolean check;
        
        //1:Delete all row in User_action
        for(User_role usRole:usRoleTable.FindByUser(UserID))
        {
            check=usRoleTable.Delete(usRole.getID());
            if(check==false) return DELETE_USER_ACTION_ERROR;
        }
        
        //2:Delete all row in User_role table 
        for(User_action usAct:usActTable.FindByUser(UserID))
        {
            check=usActTable.Delete(usAct.getID());
            if(check==false) return DELETE_USER_ROLE_ERROR;
        }
        //Delete membership
        check=  memTable.DeleteByUserId(UserID);
        if(check==false)
            return DELETE_MEM_ERROR;
        //4:Delete row in User table
        check=userTable.Delete(UserID);
        if(check==false)
            return DELETE_USER_ERROR;
        else
            return DELETE_USER_OK;
    }
    
    @Override
    public int DeleteUser(int AppID, String UserName) 
    {
        User user=userTable.Find(AppID, UserName);
        return DeleteUser(user.getID());
    }
    
    /**
     *
     * @param User
     * @p
     * @return
     */
    @Override
    public int UpdateUser(User User) 
    {
        boolean check= userTable.Update(User);
        if(check==false)
            return UPDATE_USER_ERROR;
        else
            return UPDATE_USER_OK;
    }

    //test
    @Override
    public User Find(int UserID) 
    {
        return userTable.Find(UserID);
    }

    //test
    @Override
    public User Find(int AppID,String UserName)
    {
        return userTable.Find(AppID,UserName);
    }

    //test
    @Override
    public ArrayList<MyUser> ListUserByApp(int AppID) 
    {
        ArrayList<User>arr= userTable.FindByApp(AppID);
        ArrayList<MyUser> arrMyUser=new ArrayList<>();
        
        for(User us:arr)
        {
            Membership mem=memTable.FindByUser(us.getID());
            arrMyUser.add(new MyUser(us.getID(),AppID, us.getUserName(), mem.getPassWord(),
                    mem.getPassWordFormat(), mem.getEmail(), mem.getPassWordQuestion(),
                    mem.getPassWordAnswer(), mem.getIsApproved(),mem.getMaxFailedPassword()));
        }
        return arrMyUser;
    }
    
    //test
    @Override
    public ArrayList<MyUser> ListUserByRole(int RoleID) 
    {
        ArrayList<User> array=new ArrayList<>();
        
        ArrayList<User_role> arrUsRo=usRoleTable.FindByRole(RoleID) ;
        for(User_role us:arrUsRo)
        {
            array.add(userTable.Find(us.getUserID()));
        }
        
        ArrayList<MyUser> arrMyUser=new ArrayList<>();
        
        for(User us:array)
        {
            Membership mem=memTable.FindByUser(us.getID());
            arrMyUser.add(new MyUser(us.getID(),us.getAppID(), us.getUserName(), mem.getPassWord(),
                    mem.getPassWordFormat(), mem.getEmail(), mem.getPassWordQuestion(),
                    mem.getPassWordAnswer(), mem.getIsApproved(),mem.getMaxFailedPassword()));
        }
        
        return arrMyUser;
    }

    @Override
    public ArrayList<MyUser> ListUserByRole(int AppID, String RoleName) 
    {
        Role role=roleTable.Find(AppID, RoleName);
        return ListUserByRole(role.getID());
    }
    
    @Override
    public ArrayList<MyUser> ListUserByAct(int ActID) 
    {
         ArrayList<User> array=new ArrayList<>();
         
         usActTable.FindByAct(ActID).stream().forEach((usAct) -> {
             array.add(userTable.Find(usAct.getUserID()));
        });    
        
        ArrayList<MyUser> arrMyUser=new ArrayList<>();
        
        for(User us:array)
        {
            Membership mem=memTable.FindByUser(us.getID());
            arrMyUser.add(new MyUser(us.getID(),us.getAppID(), us.getUserName(),
                    mem.getPassWord(),mem.getPassWordFormat(), mem.getEmail(),
                    mem.getPassWordQuestion(),mem.getPassWordAnswer(), mem.getIsApproved(),mem.getMaxFailedPassword()));
        }
        
        return arrMyUser;
        
    }

    @Override
    public ArrayList<MyUser> ListUserByAct(int AppID, String ActName) 
    {
        Action action=actTable.Find(AppID, ActName);
        return ListUserByAct(action.getID());
    }
    
    @Override
    public int DelteUserRole(int UserID, int RoleID) 
    {
        User_role userRole=usRoleTable.Find(UserID, RoleID);
        if(userRole==null)
            return NOT_HAVE_USER_ROLE;
        //xoa tat ca quyen nguoi dung trong role
        
        ArrayList<Role_action> roact=roActTable.FindByRole(RoleID);
        for(Role_action r:roact)
        {
            User_action u=usActTable.Find(UserID, r.getActID());
            boolean tmp;
            if(u!=null)
            {
                tmp=usActTable.Delete(u.getID());
                if(tmp==false)
                    return DELETE_USER_ACTION_ERROR;
            }
                
        }
        boolean check=usRoleTable.Delete(userRole.getID());
        if(check==true)
            return DELETE_USER_ROLE_OK;
        else
            return DELETE_USER_ROLE_ERROR;
    }

    @Override
    public int DelteUserRole(int AppID, String UserName, String RoleName) 
    {
        User user=userTable.Find(AppID, UserName);
        Role role=roleTable.Find(AppID, RoleName);
        
        return DelteUserRole(user.getID(),role.getID());
    }
    
    @Override
    public int AddUserRole(int UserID, int RoleID)
    {
        User_role userRole=usRoleTable.Find(UserID, RoleID);
        if(userRole!=null)
            return REALLY_HAVE_USER_ROLE;
        boolean check=usRoleTable.Create(new User_role(1, UserID, RoleID));
        if(check!=true)
            return ADD_USER_ROLE_ERROR;
        //them quyen cho user theo role
        for(Role_action r:roActTable.FindByRole(RoleID))
        {
            check=usActTable.Create(new User_action(1, UserID, r.getActID()));
            if(check==false)
                return ADD_USER_ACTION_ERROR;
        }
        return ADD_USER_ROLE_OK;
    }

    @Override
    public int AddUserRole(int AppID, String UserName, String RoleName) 
    {
        User user=userTable.Find(AppID, UserName);
        Role role=roleTable.Find(AppID, RoleName);
        
        return AddUserRole(user.getID(),role.getID());
    }   
    
    @Override
    public int ChangePasswordQuestionByUser(int AppID,String UserName,String Question,String Answer)
    {
        User user=userTable.Find(AppID, UserName);
        if(user==null)
            return NOT_HAVE_USER;
        int UserID=user.getID();
        Membership mem=memTable.FindByUser(UserID);
        
        if(mem==null)
        return NOT_HAVE_USER_MEM;
        
        //Kiem tra co duoc chap thuan hay khong
        if(mem.getIsApproved()==0) 
            return IS_APPROVED_ERROR;
            
        //Kiem tra user co duoc quyen thay doi passwordquestion
        User_action usAct=FindUserAction(AppID, UserName, "changepasswordquestion");
        if(usAct==null)
            return CHANGE_PAWW_QUE_NOT_PERMISSION;
        
        boolean check= memTable.ChangPasswordQuestion(mem.getID(), Question, Answer);
        if(check==false)
            return CHANGE_PAWW_QUE_ERROR;
        else
            return CHANGE_PAWW_QUE_OK;
    }

    @Override
    public int ChangePasswordQuestionByAdmin(int AppID,String UserName,String Question,String Answer) 
    {
        User user=userTable.Find(AppID, UserName);
        if(user==null)
            return NOT_HAVE_USER;
        int UserID=user.getID();
        Membership mem=memTable.FindByUser(UserID);
        
        if(mem==null)
        return NOT_HAVE_USER_MEM;
        
        boolean check= memTable.ChangPasswordQuestion(UserID, Question, Answer);
        if(check==false)
            return CHANGE_PAWW_QUE_ERROR;
        else
            return CHANGE_PAWW_QUE_OK;
    }

    @Override
    public User_action FindUserAction(int UserID, int ActID) 
    {
        return usActTable.Find(UserID, ActID);
    }

    @Override
    public User_action FindUserAction(int AppID, String UserName, String ActName) 
    {
        User user=userTable.Find(AppID, UserName);
        if(user==null)
            return null;
        Action action=actTable.Find(AppID, ActName);
        if(action==null)
            return null;
        return FindUserAction(user.getID(),action.getID());
    }

    @Override
    public int ChangePasswordByUser(int AppID, String UserName, String OldPassword,
            String NewPassword) 
    {
        User user=userTable.Find(AppID, UserName);
        if(user==null)
            return NOT_HAVE_USER;
        int UserID=user.getID();
        Membership mem=memTable.FindByUser(UserID);
        
        if(mem==null)
        return NOT_HAVE_USER_MEM;
        
        //Kiem tra co duoc chap thuan hay khong
        if(mem.getIsApproved()==0) 
            return IS_APPROVED_ERROR;
        
        //Kiem tra user co duoc quyen thay doi password
        User_action usAct=FindUserAction(AppID, UserName, "changepassword");
        if(usAct==null)
            return CHANGE_PAWW_NOT_PERMISSION;
        
        //Kiem tra password cu co dung hay khong
        if(!mem.getPassWord().equals(OldPassword))
            return PASSWORD_INCORRENT;
        //Thay doi password
        boolean check=memTable.ChanglePassword(mem.getID(), NewPassword);
        if(check==false)
            return CHANGE_PAWW_ERROR;
        else
            return CHANGE_PAWW_OK;
    }
    
    @Override
    public int ChangePasswordByAdmin(int AppID, String UserName,String NewPassword) 
    {
        User user=userTable.Find(AppID, UserName);
        if(user==null)
            return NOT_HAVE_USER;
        int UserID=user.getID();
        Membership mem=memTable.FindByUser(UserID);
        
        if(mem==null)
        return NOT_HAVE_USER_MEM;
        
        //Thay doi password
        boolean check=memTable.ChanglePassword(mem.getID(), NewPassword);
        if(check==false)
            return CHANGE_PAWW_ERROR;
        else
            return CHANGE_PAWW_OK;
    }
    public String getCurrentTime()
    {
        long time=System.currentTimeMillis();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // tạo 1 đối tượng có định dạng thời gian yyyy-MM-dd HH:mm:ss
        Date date = new Date(time);	// lấy thời gian hệ thống
        String stringDate = dateFormat.format(date);//Định dạng thời gian theo trên
        return stringDate;
    }       

    @Override
    public int CheckSignIn(int AppID, String UserName, String Password) 
    {
        User user=userTable.Find(AppID, UserName);
        if(user==null)
            return NOT_HAVE_USER;
        Membership mem=memTable.FindByUser(user.getID());
        if(mem==null)
            return NOT_HAVE_USER_MEM;
        //Neu password khong chinh xac
        if(!mem.getPassWord().equals(Password))
        {
            //cap nhat lai thong tin mem
            mem.setFailedPasswordAttemptCount(mem.getFailedPasswordAttemptCount()+1);
            mem.setFailedPasswordAttemptWindowStart(getCurrentTime());
            memTable.Update(mem);
            if(mem.getFailedPasswordAnswerAttemptCount()>=mem.getMaxFailedPassword())
                return OUT_MAX_FAIL_PASSWORD;
            return PASSWORD_INCORRENT;
        }
            
        else
        {
            //cap nhat lai thong tin mem
            mem.setFailedPasswordAttemptCount(0);
            mem.setIsLockOut(0);
            memTable.Update(mem);
            return SIGN_IN_OK;
        }  
    }

    @Override
    public int CloseUser(int UserID) 
    {
        Membership mem=memTable.FindByUser(UserID);
        if(mem==null)
            return NOT_HAVE_USER_MEM;
        mem.setIsApproved(0);
        boolean check=memTable.Update(mem);
        if(check==false)
            return CLOSE_USER_ERROR;
        else
            return CLOSE_USER_OK;
    }

    @Override
    public int CloseUser(int AppID, String UserName) 
    {
        User user=Find(AppID);
        return CloseUser(user.getID());
    }
}
