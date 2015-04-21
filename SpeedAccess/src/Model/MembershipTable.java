package Model;

import java.sql.ResultSet;
import Table.Membership;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MembershipTable 
{
    private final Connect connnect=Connect.getConnect();
    private ResultSet  resultSet;

    //test
    public boolean Create(Membership mem) 
    {       
        String query="insert into access.memberships "
                + "SET UserID="+mem.getUserID()+", Password='"+mem.getPassWord()+"',"
                + " PasswordFormat='"+mem.getPassWordFormat()+"',IsApproved="+mem.getIsApproved()+",IsLockedOut="+mem.getIsLockOut()+" "
                + ",Email='"+mem.getEmail()+"', PasswordQuestion='"+mem.getPassWordQuestion()+"',"
                + "PasswordAnswer='"+mem.getPassWordAnswer()+"', CreateDate='"+mem.getCreateDate()+"',  "
                + "FailedPasswordAttemptCount="+mem.getFailedPasswordAttemptCount()+","
                + "FailedPasswordAnswerAttemptCount="+mem.getFailedPasswordAnswerAttemptCount()+","
                + " AppID="+mem.getAppID()+","
                + "MaxFailedPassword="+mem.getMaxFailedPassword()+";" ;
        System.out.println(query);
        return connnect.executeUpdate(query);
    }
    
    public boolean Delete(int MemID)
    {
        String query="DELETE FROM access.memberships WHERE MemID="+MemID+";";
        return connnect.executeUpdate(query);
    }
   
    //test
    public boolean DeleteByUserId(int UserID)
    {
        Membership mem=FindByUser(UserID);
        if(mem!=null)
            return Delete(mem.getID());
        return true;
    }
    
    public boolean Update(Membership mem) 
    {
        String query="UPDATE access.memberships "
                + "SET  Password='"+mem.getPassWord()+"', "
                + "PasswordFormat='"+mem.getPassWordFormat()+"', Email='"+mem.getEmail()+"',"
                + "PasswordQuestion='"+mem.getPassWordQuestion()+"', PasswordAnswer='"+mem.getPassWordAnswer()+"', "
                + "IsApproved="+mem.getIsApproved()+", IsLockedOut="+mem.getIsLockOut()+", CreateDate='"+mem.getCreateDate()+"', "
                + "LastLoginDate='"+mem.getLastLoginDate()+"', LastPasswordChangedDate='"+mem.getLastPasswordChangedDate()+"', "
                + "LastLockoutDate='"+mem.getLastLockoutDate()+"',"
                + " FailedPasswordAttemptCount="+mem.getFailedPasswordAttemptCount()+","
                + "FailedPasswordAttemptWindowStart='"+mem.getFailedPasswordAttemptWindowStart()+"', "
                + "FailedPasswordAnswerAttemptCount="+mem.getFailedPasswordAttemptWindowStart()+", "
                + "FailedPasswordAnswerAttemptWindowsStart='"+mem.getFailedPasswordAnswerAttemptWindowsStart()+"' "
                + "WHERE MemID=;";
        return connnect.executeUpdate(query);
    }
   
    public Membership FindByUser(int UserID) 
    {
        Membership mem=null;
        
        String quey="SELECT * FROM access.memberships WHERE UserID="+UserID+";";
        System.out.println(quey);
        resultSet=connnect.executeQuery(quey);
        try {
            while(resultSet.next())
                mem=new Membership(resultSet.getInt("MemID"), resultSet.getInt("UserID"),
                    resultSet.getInt("AppID"), resultSet.getString("Password"), resultSet.getString("PasswordFormat"),
                    resultSet.getString("Email"), resultSet.getString("PasswordQuestion"), resultSet.getString("PassWordAnswer"),
                    resultSet.getInt("IsApproved"), resultSet.getInt("IsLockedOut"), resultSet.getString("CreateDate"),
                    resultSet.getString("LastLoginDate"), resultSet.getString("LastPasswordChangedDate"),
                    resultSet.getString("LastLockoutDate"),
                    resultSet.getInt("FailedPasswordAttemptCount"), resultSet.getString("FailedPasswordAttemptWindowStart"),
                    resultSet.getInt("FailedPasswordAnswerAttemptCount"),
                    resultSet.getString("FailedPasswordAnswerAttemptWindowsStart"),
                    resultSet.getInt("MaxFailedPassword"));
        } catch (SQLException ex) {
            Logger.getLogger(MembershipTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mem;
    }
    
    public ArrayList<Membership> FindByAppId(int AppId)
    {
        ArrayList<Membership> array=new ArrayList<>();
        
        String query="SELECT * FROM access.memberships where AppID="+AppId+";";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            {
               Membership mem=new Membership(resultSet.getInt("MemID"), resultSet.getInt("UserID"),
                    resultSet.getInt("AppID"), resultSet.getString("Password"), resultSet.getString("PasswordFormat"),
                    resultSet.getString("Email"), resultSet.getString("PasswordQuestion"), resultSet.getString("PassWordAnswer"),
                    resultSet.getInt("IsApproved"), resultSet.getInt("IsLockOut"), resultSet.getString("CreateDate"),
                    resultSet.getString("LastLoginDate"), resultSet.getString("LastPasswordChangedDate"),
                    resultSet.getString("LastLockoutDate"),
                    resultSet.getInt("FailedPasswordAttemptCount"), resultSet.getString("FailedPasswordAttemptWindowStart"),
                    resultSet.getInt("FailedPasswordAnswerAttemptCount"),
                    resultSet.getString("FailedPasswordAnswerAttemptWindowsStart"),
                    resultSet.getInt("MaxFailedPassword"));
                
                array.add(mem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembershipTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    public Membership Find(int MemID) 
    {
        Membership mem=null;
        
        String quey="SELECT * FROM access.memberships WHERE= MemID="+MemID+";";
        resultSet=connnect.executeQuery(quey);
        try {
            mem=new Membership(resultSet.getInt("MemID"), resultSet.getInt("UserID"),
                    resultSet.getInt("AppID"), resultSet.getString("Password"), resultSet.getString("PasswordFormat"),
                    resultSet.getString("Email"), resultSet.getString("PasswordQuestion"), resultSet.getString("PassWordAnswer"),
                    resultSet.getInt("IsApproved"), resultSet.getInt("IsLockOut"), resultSet.getString("CreateDate"),
                    resultSet.getString("LastLoginDate"), resultSet.getString("LastPasswordChangedDate"),
                    resultSet.getString("LastLockoutDate"),
                    resultSet.getInt("FailedPasswordAttemptCount"), resultSet.getString("FailedPasswordAttemptWindowStart"),
                    resultSet.getInt("FailedPasswordAnswerAttemptCount"),
                    resultSet.getString("FailedPasswordAnswerAttemptWindowsStart"),
                    resultSet.getInt("MaxFailedPassword"));
        } catch (SQLException ex) {
            Logger.getLogger(MembershipTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mem;
    }    
    
    public boolean ChanglePassword(int MemID,String Password)
    {
        String query="UPDATE access.memberships"
                + " SET Password='"+Password+"' WHERE MemID="+MemID+";";
        return connnect.executeUpdate(query);
    }
    
    public boolean ChangPasswordQuestion(int MemID, String Question, String Answer)
    {
        String query="UPDATE access.memberships "
                + "SET PasswordQuestion='"+Question+"', PasswordAnswer='"+Answer+"', "
                + "FailedPasswordAnswerAttemptCount=0, FailedPasswordAnswerAttemptWindowsStart=null "
                + "WHERE MemID="+MemID+";";
        
        return connnect.executeUpdate(query);
    }
    
//    public Membership CheckPassword(int UserID,String PassWord)
//    {
//        Membership mem=null;
//        String query="SELECT * FROM access.memberships where UserID ="+UserID+" and password='"+PassWord+"';";
//        System.out.println(query);
//        resultSet=connnect.executeQuery(query);
//        try {
//            if(resultSet.next())
//            {
//                mem=new Membership(resultSet.getInt("MemID"), resultSet.getInt("UserID"),
//                    resultSet.getInt("AppID"), resultSet.getString("Password"), resultSet.getString("PasswordFormat"),
//                    resultSet.getString("Email"), resultSet.getString("PasswordQuestion"), resultSet.getString("PassWordAnswer"),
//                    resultSet.getInt("IsApproved"), resultSet.getInt("IsLockedOut"), resultSet.getString("CreateDate"),
//                    resultSet.getString("LastLoginDate"), resultSet.getString("LastPasswordChangedDate"),
//                    resultSet.getString("LastLockoutDate"),
//                    resultSet.getInt("FailedPasswordAttemptCount"), resultSet.getString("FailedPasswordAttemptWindowStart"),
//                    resultSet.getInt("FailedPasswordAnswerAttemptCount"),
//                    resultSet.getString("FailedPasswordAnswerAttemptWindowsStart"));
//                
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MembershipTable.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return mem;
//    }          
    
    public boolean Update(String query)
    {
        return connnect.executeUpdate(query);
    }
    
}
