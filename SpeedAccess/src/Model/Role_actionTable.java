package Model;

import Table.Role_action;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Role_actionTable 
{
     private final Connect connnect=Connect.getConnect();
    private ResultSet  resultSet;
        
    //test
    public boolean Create(Role_action roac) 
    {       
        String query="INSERT INTO access.role_action (RoleID, ActID) "
                + "VALUES ("+roac.getRoleID()+" , "+roac.getActID()+");";
        return connnect.executeUpdate(query);
    }
  
    /*==============================================
                        TEST
    ==============================================*/
    public boolean Delete(int ID) 
    {
        boolean check;
        String query="DELETE FROM access.role_action WHERE ID="+ID+";";
        check=connnect.executeUpdate(query);
        return check;
    }
   
    public boolean Update(Role_action roac) 
    {       
        String query="UPDATE access.role_action "
                + "SET RoleID="+roac.getRoleID()+", ActionID="+roac.getActID()+" WHERE ID="+roac.getID()+";";
        return connnect.executeUpdate(query);
    }
   
    /*=============================================
                        TEST
    =============================================*/
    public ArrayList<Role_action> FindByRole(int RoleID)
    {
        ArrayList<Role_action> array=new ArrayList<>();
        
        String query=" select * from access.role_action where RoleID="+RoleID+";";
        resultSet=connnect.executeQuery(query);
         try {
             while(resultSet.next())
             {
                 array.add(new Role_action(resultSet.getInt("ID"), resultSet.getInt("RoleID"), resultSet.getInt("ActID")));
             }
         } catch (SQLException ex) {
             Logger.getLogger(Role_actionTable.class.getName()).log(Level.SEVERE, null, ex);
         }
         return array;
    }
    /*=============================================
                        TEST
    =============================================*/
    public ArrayList<Role_action> FindByAction(int ActID)
    {
        ArrayList<Role_action> array=new ArrayList<>();
        
        String query=" select * from access.role_action where ActID="+ActID+";";
        resultSet=connnect.executeQuery(query);
         try {
             while(resultSet.next())
             {
                 array.add(new Role_action(resultSet.getInt("ID"), resultSet.getInt("RoleID"), resultSet.getInt("ActID")));
             }
         } catch (SQLException ex) {
             Logger.getLogger(Role_actionTable.class.getName()).log(Level.SEVERE, null, ex);
         }
         return array;
    }
    
    /*==============================================
                        TEST
    ==============================================*/
    public Role_action Find(int RoleID,int ActID)
    {
        Role_action temp=null;
        String query="SELECT * FROM access.role_action where ActID="+ActID+" and RoleID="+RoleID+";";
        resultSet=connnect.executeQuery(query);
         try {
            while(resultSet.next())
            {
                temp=new Role_action(resultSet.getInt("ID"), resultSet.getInt("RoleID"), resultSet.getInt("ActID"));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(User_actionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
}
