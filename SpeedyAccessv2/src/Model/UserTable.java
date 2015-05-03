package Model;

import Table.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserTable extends Connect
{
    public int Create(int AppID,String UserName,String Password,String Email)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call create_user(?,?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName);
            this.stmt.setString(3, Password);
            this.stmt.setString(4, Email);
            this.stmt.registerOutParameter(5, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(5);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int Delete(int AppID,String UserName)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call Delete_user(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName);
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            this.stmt.executeQuery();
            result=stmt.getInt(3);
        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public User Find(int AppID,String UserName)
    {
        User user=null;
        try {
            this.stmt=this.connection.prepareCall("{call find_user(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName.trim());
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(3);
            if(result==0)
                return null;
            if(resultSet.next())
            {
                user=new User(AppID,resultSet.getInt("UserID"),resultSet.getString("UserName"),
                        resultSet.getString("PasswordUser"),resultSet.getString("Email"),resultSet.getInt("IsApproved"),
                        resultSet.getInt("IsLockedOut"),resultSet.getString("CreateDate"),
                        resultSet.getString("LastLoginDate"),resultSet.getString("LastPasswordChangedDate"),
                        resultSet.getString("LastLockoutDate"),resultSet.getInt("FailedPasswordAttemptCount"),
                        resultSet.getString("FailedPasswordAttemptWindowStart"),resultSet.getString("LastActiveDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public int Close(int AppID,String UserName)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call Close_user(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName);
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(3);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int UpdateUserName(int AppID, String OldUserName, String NewUserName)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call update_username(?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, OldUserName);
            this.stmt.setString(3, NewUserName);
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(3);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int UpdateEmail(int AppID,String UserName,String Email)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call update_email(?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName);
            this.stmt.setString(3, Email);
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int CheckUserAct(int AppID, String UserName, String ActName)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call CheckUserAct(?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName);
            this.stmt.setString(3, ActName);
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(3);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<User> FindByApp(int AppID)
    {
        ArrayList<User> array=new ArrayList<>();
        User user=null;
        try {
            this.stmt=this.connection.prepareCall("{call listUserByApp(?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.registerOutParameter(2, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(2);
            if(result==0)
                return null;

            while(resultSet.next())
            {
                user=new User(AppID,resultSet.getInt("UserID"),resultSet.getString("UserName"),
                        resultSet.getString("PasswordUser"),resultSet.getString("Email"),resultSet.getInt("IsApproved"),
                        resultSet.getInt("IsLockedOut"),resultSet.getString("CreateDate"),
                        resultSet.getString("LastLoginDate"),resultSet.getString("LastPasswordChangedDate"),
                        resultSet.getString("LastLockoutDate"),resultSet.getInt("FailedPasswordAttemptCount"),
                        resultSet.getString("FailedPasswordAttemptWindowStart"),resultSet.getString("LastActiveDate"));
                array.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public ArrayList<User> FindByRole(int AppID,String RoleName)
    {
        ArrayList<User> array=new ArrayList<>();
        User user=null;
        try {
            this.stmt=this.connection.prepareCall("{call ListUserByRole(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, RoleName);
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(3);
            if(result==0||result==2)
                return null;
            while(resultSet.next())
            {
                user=new User(AppID,resultSet.getInt("UserID"),resultSet.getString("UserName"),
                        resultSet.getString("Passworduser"),resultSet.getString("Email"),resultSet.getInt("IsApproved"),
                        resultSet.getInt("IsLockedOut"),resultSet.getString("CreateDate"),
                        resultSet.getString("LastLoginDate"),resultSet.getString("LastPasswordChangedDate"),
                        resultSet.getString("LastLockoutDate"),resultSet.getInt("FailedPasswordAttemptCount"),
                        resultSet.getString("FailedPasswordAttemptWindowStart"),resultSet.getString("LastActiveDate"));
                array.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public ArrayList<User> FindByAct(int AppID,String ActName)
    {
        ArrayList<User> array=new ArrayList<>();
        User user=null;
        try {
            this.stmt=this.connection.prepareCall("{call ListUserByAct(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, ActName);
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(3);
            if(result==0||result==2)
                return null;
            while(resultSet.next())
            {
                user=new User(AppID,resultSet.getInt("UserID"),resultSet.getString("UserName"),
                        resultSet.getString("Passworduser"),resultSet.getString("Email"),resultSet.getInt("IsApproved"),
                        resultSet.getInt("IsLockedOut"),resultSet.getString("CreateDate"),
                        resultSet.getString("LastLoginDate"),resultSet.getString("LastPasswordChangedDate"),
                        resultSet.getString("LastLockoutDate"),resultSet.getInt("FailedPasswordAttemptCount"),
                        resultSet.getString("FailedPasswordAttemptWindowStart"),resultSet.getString("LastActiveDate"));
                array.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public int ChangePasswordByUser(int AppID, String UserName,String OldPassword, String NewPassword)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call update_password_by_user(?,?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName);
            this.stmt.setString(3, OldPassword);
            this.stmt.setString(4, NewPassword);
            this.stmt.registerOutParameter(5, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(5);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int ChangePasswordByAdmin(int AppID, String UserName, String NewPassword)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call update_password_by_admin(?,?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName);
            this.stmt.setString(3, NewPassword);
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int SignIn(int AppID, String UserName, String Password)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call signin(?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName);
            this.stmt.setString(3, Password);
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int Logout(int AppID, String UserName)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call logout(?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName);
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(3);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
