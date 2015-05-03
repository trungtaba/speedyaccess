package Model;

import Table.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleTable extends Connect
{
    public int Create(int AppID,String RoleName,String RoleDes)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call create_role(?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, RoleName.trim());
            this.stmt.setString(3, RoleDes.trim());
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int Delete(int AppID,String RoleName)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call Delete_role(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, RoleName.trim());
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(3);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int Update(int AppID,String OldRoleName, String NewRoleName,String RoleDes)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call update_role(?,?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, OldRoleName.trim());
            this.stmt.setString(3, NewRoleName.trim());
            this.stmt.registerOutParameter(5, java.sql.Types.INTEGER);
            this.stmt.setString(4, RoleDes);

            this.stmt.execute();
            result=stmt.getInt(5);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public Role Find(int AppID,String RoleName)
    {
        Role role=null;
        try {
            this.stmt=this.connection.prepareCall("{call find_role(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, RoleName.trim());
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(3);
            if(result==0)
                return null;
            if(resultSet.next())
            {
                role=new Role(resultSet.getInt("RoleID"),resultSet.getString("RoleName"),resultSet.getString("RoleDes"),AppID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }

    public ArrayList<Role> FindByApp(int AppID)
    {
        ArrayList<Role> array=new ArrayList<>();
        Role role=null;
        try {
            this.stmt=this.connection.prepareCall("{call ListRoleByApp(?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.registerOutParameter(2, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(2);
            if(result==0)
                return null;

            while(resultSet.next())
            {
                role=new Role(resultSet.getInt("RoleID"),resultSet.getString("RoleName"),resultSet.getString("RoleDes"),AppID);
                array.add(role);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public ArrayList<Role> FindByAct(int AppID,String ActName)
    {
        ArrayList<Role> array=new ArrayList<>();
        Role role=null;
        try {
            this.stmt=this.connection.prepareCall("{call ListRoleByAct(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, ActName);
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(3);
            if(result==0||result==2)
                return null;
            while(resultSet.next())
            {
                role=new Role(resultSet.getInt("RoleID"),resultSet.getString("RoleName"),resultSet.getString("RoleDes"),AppID);
                array.add(role);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public ArrayList<Role> FindByUser(int AppID,String UserName)
    {
        ArrayList<Role> array=new ArrayList<>();
        Role role=null;
        try {
            this.stmt=this.connection.prepareCall("{call ListRoleByUser(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName);
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(3);
            if(result==0||result==2)
                return null;
            while(resultSet.next())
            {
                role=new Role(resultSet.getInt("RoleID"),resultSet.getString("RoleName"),resultSet.getString("RoleDes"),AppID);
                array.add(role);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
}
