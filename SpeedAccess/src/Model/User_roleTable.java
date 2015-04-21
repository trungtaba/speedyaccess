package Model;

import java.sql.ResultSet;
import Table.User_role;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User_roleTable
{
    private final Connect connnect=Connect.getConnect();
    private ResultSet  resultSet;

    /*==============================================
                        TEST
    ==============================================*/
    public boolean Create(User_role user) 
    {        
        String query="INSERT INTO access.user_role ( UserID, RoleID) "
                + "VALUES ("+user.getUserID()+" ,"+user.getRoleID()+" );";
        return connnect.executeUpdate(query);
    }

    //test
    public boolean Delete(int ID) 
    {
        String query="DELETE FROM access.user_role WHERE ID="+ID+";";
        return connnect.executeUpdate(query);
    }
  
    public boolean Update(User_role user)
    {        
        String quety="UPDATE access.user_role "
                + "SET UserID="+user.getUserID()+", RoleID="+user.getRoleID()+" WHERE ID="+user.getID()+";";
        return connnect.executeUpdate(quety);
    }

    //test
    public  ArrayList<User_role> FindByUser(int ID)
    {
        ArrayList<User_role> array=new ArrayList<>();
        
        String query="SELECT * FROM access.user_role where UserID="+ID+";";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            {
                array.add(new User_role(resultSet.getInt("ID"), resultSet.getInt("UserID"), resultSet.getInt("RoleID")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User_roleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    //test
    public  ArrayList<User_role> FindByRole(int ID)
    {
        ArrayList<User_role> array=new ArrayList<>();
        String query="SELECT * FROM access.user_role where RoleID="+ID+";";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            {
                array.add(new User_role(resultSet.getInt("ID"), resultSet.getInt("UserID"), resultSet.getInt("RoleID")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User_roleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    /*==============================================
                        TEST
    ==============================================*/
    public User_role Find(int UserID,int RoleID)
    {
        User_role resul=null;
        String query="SELECT * FROM access.user_role where UserID="+UserID+" and RoleID="+RoleID+";";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            {
                resul=new User_role(resultSet.getInt("ID"), resultSet.getInt("UserID"), resultSet.getInt("RoleID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User_roleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resul;
    }
}
