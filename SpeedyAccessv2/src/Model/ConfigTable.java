package Model;

import Table.Config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigTable extends Connect
{
    public int Create(int AppID,String UserNameValidate,int MaxFailedPassword,String PasswordValidate)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call create_config(?,?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserNameValidate);
            this.stmt.setInt(3, MaxFailedPassword);
            this.stmt.setString(4, PasswordValidate);
            this.stmt.registerOutParameter(5, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(5);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int Update(int AppID,String UserNameValidate,int MaxFailedPassword,String PasswordValidate)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call access.update_config(?,?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserNameValidate);
            this.stmt.setInt(3, MaxFailedPassword);
            this.stmt.setString(4, PasswordValidate);
            this.stmt.registerOutParameter(5, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(5);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<Config> LoadConfig()
    {
        ArrayList<Config> array=new ArrayList<>();
        Config con=null;
        try {
            this.stmt=this.connection.prepareCall("{call locad_config(?)}");
            this.stmt.registerOutParameter(1, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(1);
            if(result==0)
                return null;

            while(resultSet.next())
            {
                con=new Config(resultSet.getInt("AppID"),resultSet.getString("PasswordFormat"),resultSet.getString("UserNameValidate"),
                        resultSet.getInt("MaxFailedPassword"),resultSet.getString("PasswordValidate"));
                array.add(con);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public Config Find(int AppID)
    {
        Config config=null;
        try {
            this.stmt=this.connection.prepareCall("{call find_config(?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.registerOutParameter(2, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(2);
            if(result==0)
                return null;
            if(resultSet.next())
            {
                config=new Config(AppID,resultSet.getString("PasswordFormat"),
                        resultSet.getString("PasswordValidate"),resultSet.getInt("MaxFailedPassword"),resultSet.getString("UserNameValidate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return config;
    }

    public int Delete(int AppID)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call access.delete_config(?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.registerOutParameter(2, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(2);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
