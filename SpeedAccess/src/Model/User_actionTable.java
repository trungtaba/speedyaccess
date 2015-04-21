package Model;
import java.sql.ResultSet;
import Table.User_action;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User_actionTable
{
    private final Connect connnect=Connect.getConnect();
    private ResultSet  resultSet;
    
    //test
    public boolean Create(User_action user) 
    {        
        String query="INSERT INTO access.user_action (UserID, ActionID) "
                + "VALUES ("+user.getUserID()+","+user.getActID()+");";
        return connnect.executeUpdate(query);
    }

    /*==============================================
                        TEST
    ==============================================*/
    public boolean Delete(int ID) 
    {
        String query="DELETE FROM access.user_action WHERE ID="+ID+";";
        return connnect.executeUpdate(query);
    }
    
    public void Modify(User_action user) 
    {       
        String query="UPDATE access.user_action "
                + "SET UserID="+user.getUserID()+", ActionID="+user.getActID()+" WHERE ID="+user.getID()+";";
        connnect.executeUpdate(query);
    }
   
    /*=============================================
                        TEST
    =============================================*/
    public ArrayList<User_action> FindByUser(int UserID)
    {
        ArrayList<User_action> array=new ArrayList<>();

        String query="SELECT * FROM access.user_action where UserID="+UserID+";";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
                array.add(new User_action(resultSet.getInt("ID"), resultSet.getInt("UserID"), resultSet.getInt("ActionID")));
        } catch (SQLException ex) {
            Logger.getLogger(User_actionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    /*=============================================
                        TEST
    =============================================*/
    public ArrayList<User_action> FindByAct(int ActID)
    {
        ArrayList<User_action> array=new ArrayList<>();

        String query="SELECT * FROM access.user_action where ActionID="+ActID+";";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            {
                array.add(new User_action(resultSet.getInt("ID"), resultSet.getInt("UserID"), resultSet.getInt("ActionID")));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(User_actionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    /*==============================================
                        TEST
    ==============================================*/
    public User_action Find(int UserID,int ActID)
    {
        User_action temp=null;
        String query="SELECT * FROM access.user_action where ActionID="+ActID+" and UserID="+UserID+";";
        resultSet=connnect.executeQuery(query);
         try {
            while(resultSet.next())
            {
                temp=new User_action(resultSet.getInt("ID"), resultSet.getInt("UserID"), resultSet.getInt("ActionID"));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(User_actionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
    
    
}

