package Model;

import Table.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationTable extends Connect
{
    public int Create(String AppName,String AppDes,String KeyApp)
    {
        int result=-1;
        try {
            this.stmt=this.connection.prepareCall("call create_app(?,?,?,?)");
            this.stmt.setString(1, AppName);
            this.stmt.setString(2, AppDes);
            this.stmt.setString(3, KeyApp);
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(ApplicationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int Update(int AppID,String AppName,String AppDes)
    {
        int result=-1;
        try {
            this.stmt=this.connection.prepareCall("call update_app(?,?,?,?)");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, AppName);
            this.stmt.setString(3, AppDes);
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(ApplicationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int Delete(int AppID)
    {
        int result=-1;
        try {
            this.stmt=this.connection.prepareCall("call delete_app(?,?)");
            this.stmt.setInt(1, AppID);
            this.stmt.registerOutParameter(2, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(2);

        } catch (SQLException ex) {
            Logger.getLogger(ApplicationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public Application Find(int AppID) {
        Application app = null;
        try {
            this.stmt = this.connection.prepareCall("{call find_app(?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.registerOutParameter(2, java.sql.Types.INTEGER);

            ResultSet resultSet = this.stmt.executeQuery();
            int result = stmt.getInt(2);
            if (result == 0)
                return null;
            if (resultSet.next()) {
                app=new Application(AppID,resultSet.getString("AppName"),resultSet.getString("AppDes"),resultSet.getString("KeyApp"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return app;
    }

    public ArrayList<Application> applicationArrayList()
    {
        ArrayList<Application> array = new  ArrayList<>();
        try {
            this.stmt = this.connection.prepareCall("{call access.ListApplication(?)}");
            this.stmt.registerOutParameter(1, java.sql.Types.INTEGER);

            ResultSet resultSet = this.stmt.executeQuery();
            int result = stmt.getInt(1);
            if (result == 0)
                return null;
            while (resultSet.next())
            {
                array.add(new Application(resultSet.getInt("AppID"),resultSet.getString("AppName"),
                        resultSet.getString("AppDes"),resultSet.getString("KeyApp")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
}
