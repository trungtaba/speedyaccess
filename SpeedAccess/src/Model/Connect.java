package Model;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect 
{
    public static Connect connect=new Connect();
    
    private final String HOST_NAME="127.0.0.1:3306";
    private final String DATABASE_NAME="access";
    private final String USER_NAME="root";
    private final String PASSWORD="justonlyyou153";  

    private Statement statement;
    private Connection connection;
    private ResultSet resultSet;
    
    private Connect()
    {
        CreateConnect();
    }
    
    private void CreateConnect()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //connection= DriverManager.getConnection("jdbc:mysql://"+SERVER_NAME+"/"+DATABASE_NAME,USER_NAME,PASSWORD);
            connection= DriverManager.getConnection("jdbc:mysql://localhost/access","root","justonlyyou153");            
            statement = connection.createStatement(); 
        }catch(ExceptionInInitializerError ex)
        {
            System.err.println("Error:"+ex.toString());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connect getConnect()
    {
        return connect;
    }
    public boolean CheckSignIn(String UserName,String PassWord)
    {
        String UserID="";
       
        
        try {
             //Kiem tra co duoc quyen dang nhap
            String query="SELECT UserID FROM access.users,access.actions,access.user_action "
                + "where (access.users.UserID=access.user_action.UserID)&"
                + "(access.actions.ActID=access.user_action.ActionID)&(access.users.UserName='"+UserName+"');";
            resultSet=statement.executeQuery(query);
            if(resultSet.next()!=false)
            {
                UserID=resultSet.getString("UserID");
            }
             //Kiem tra password co dung hay khong
            query="SELECT FailedPasswordAttemptCount,IsApproved FROM access.memberships "
                    + "where (Password='"+PassWord+"')&(UserID="+UserID+");";
            resultSet=statement.executeQuery(query);
            //Neu kiem tra dung, cap nhat thong tin
            if(resultSet.next())
            {       
                //kiem tra IsApproved =1?
                int IsApproved=resultSet.getInt("IsApproved");
                if(IsApproved==0)
                    return false;
                else
                {
                    //Cap nhat thong tin khi dang nhap thanh cong
                    query="UPDATE acces.memberships SET  IsLockedOut=0, LastLoginDate=now() WHERE UserID="+UserID+";";
                    statement.executeUpdate(query);
                    return true;
                }
            }
            //Kiem tra sai,cap nhat FailedPasswordAttemptCount,FailedPasswordAttemptWindowStart
            else
            {
                query="select FailedPasswordAttemptCount from access.memberships where UserID="+UserID+";";
                resultSet=statement.executeQuery(query);
                
                if(resultSet.next())
                {
                    int FailedPasswordAttemptCount=resultSet.getInt("FailedPasswordAttemptCount");
                    FailedPasswordAttemptCount++;
                    query="UPDATE access.memberships "
                            + "SET FailedPasswordAttemptCount="+FailedPasswordAttemptCount+","
                            + " FailedPasswordAttemptWindowStart=now() WHERE UserID="+UserID+";";   
                    statement.executeUpdate(query);
                }
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  
    }
    //test
    public ResultSet executeQuery(String query)
    {
        ResultSet result=null;
        try {
            result=statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return result;
    }
    //test
    public boolean executeUpdate(String query)
    {
        try {            
            statement.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean CheckAction(String UserName,String Action)
    {
        String query;
        query="select users.UserID"
                + "from users,user_action,actions "
                + "where(users.UserID=user_action.UserID)&(actions.ActID=user_action.ActionID) "
                + "&(users.UserName='"+UserName+"')&(actions.ActName='"+Action+"');";
        try {
            resultSet=statement.executeQuery(query);
            if(resultSet.next())
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 
}
