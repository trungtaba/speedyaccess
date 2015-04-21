package Model;

import java.sql.ResultSet;
import Table.Role;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleTable
{
    private final Connect connnect=Connect.getConnect();
    private ResultSet  resultSet;

    /*==============================================
                        TEST
    ==============================================*/
    public boolean Create(Role role) 
    {       
        String query="INSERT INTO access.roles ( RoleName, RoleDes, AppID) "
                + "VALUES ('"+role.getRoleName()+"', '"+role.getRoleDes()+"', "+role.getAppID()+");";
        return connnect.executeUpdate(query);
    }

    
    public boolean Delete(int ID) 
    {
        String query="DELETE FROM access.roles WHERE RoleID="+ID+";";
       return connnect.executeUpdate(query);
    }
    
    public boolean Delete(String roleName,int AppID) 
    {
        Role role=Find(AppID,roleName);
        return Delete(role.getID());
    }
    
    public boolean Update(Role role) 
    {       
        String query="UPDATE access.roles "
                + "SET RoleName='"+role.getRoleName()+"', RoleDes='"+role.getRoleDes()+"', "
                + "AppID="+role.getAppID()+" WHERE RoleID="+role.getID()+";";
        System.out.println(query);
        return connnect.executeUpdate(query);
    }

    /*==============================================
                        TEST
    ==============================================*/
    public Role Find(int AppID,String RoleName) 
    {
        Role role=null;
        
        String query="SELECT * FROM access.roles WHERE RoleName='"+RoleName+"'and AppID="+AppID+";";
 
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            role=new Role(resultSet.getInt("RoleID"), resultSet.getString("RoleName"),
                    resultSet.getString("RoleDes"), resultSet.getInt("AppID"));
        } catch (SQLException ex) {
            Logger.getLogger(RoleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }
  
    public Role Find(int ID)
    {
        Role role=null;
        
        String query="SELECT * FROM access.roles WHERE RoleID='"+ID+"';";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            role=new Role(resultSet.getInt("RoleID"), resultSet.getString("RoleName"),
                    resultSet.getString("RoleDes"), resultSet.getInt("AppID"));
        } catch (SQLException ex) {
            Logger.getLogger(RoleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }
    
    public ArrayList<Role> ListRolebyAppID(int AppID)
    {
        ArrayList<Role> array=new ArrayList<>();
        
        String query="SELECT * FROM access.roles WHERE AppID='"+AppID+"';";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
                array.add(new Role(resultSet.getInt("RoleID"), resultSet.getString("RoleName"),
                        resultSet.getString("RoleDes"), resultSet.getInt("AppID")));
        } catch (SQLException ex) {
            Logger.getLogger(RoleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
}
