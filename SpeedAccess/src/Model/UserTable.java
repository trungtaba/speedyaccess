package Model;

import Table.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserTable
{
    private final Connect connnect=Connect.getConnect();
    private ResultSet  resultSet;
    
    //test
    public boolean Create(User user) 
    {
        String query="INSERT INTO access.users (UserName, LastActivityDate, AppID)"
                + " VALUES ('"+user.getUserName()+"', now(),"+user.getAppID()+" );";
        System.out.println(query);
        return connnect.executeUpdate(query);
    }

    //test
    public boolean Delete(int ID) 
    {
        String query="DELETE FROM access.users WHERE UserID="+ID+";";
        return connnect.executeUpdate(query);
    }

    
    public boolean Delete(String name,int AppID)
    {
        User user=Find(AppID,name);
        return Delete(user.getID());
    }

    
    public boolean Update(User user) 
    {       
        String query="UPDATE access.users "
                + "SET UserName='"+ user.getUserName()+"', LastActivityDate='"+user.getLastActiveDate()+"'"
                + " WHERE UserID="+user.getID()+";";
        System.out.println(query);
        return connnect.executeUpdate(query);
    }
    
    //test
    public User Find(int AppID,String UserName) 
    {
        User user=null;
        
        String query="SELECT * FROM access.users WHERE UserName='"+UserName+"'and AppID="+AppID+";";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
                user=new User(resultSet.getInt("UserID"), resultSet.getInt("AppID"),
                    resultSet.getString("UserName"), resultSet.getString("LastActivityDate"));
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
   
    //test
    public User Find(int ID) 
    {
         User user=null;
        
        String query="SELECT * FROM access.users WHERE UserID='"+ID+"';";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            user=new User(resultSet.getInt("UserID"), resultSet.getInt("AppID"),
                    resultSet.getString("UserName"), resultSet.getDate("LastActivityDate").toString());
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    //test
    public ArrayList<User> FindByApp(int AppID) 
    {
        ArrayList<User> array=new ArrayList<>();
        
        String query="SELECT * FROM access.users WHERE AppID="+AppID+";";

        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            {
                array.add(new User(resultSet.getInt("UserID"), resultSet.getInt("AppID"),
                        resultSet.getString("UserName"), resultSet.getString("LastActivityDate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(User us:array)
            System.out.println(us.toString());
        return array;
    }
}
