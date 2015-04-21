package Model;

import Table.Application;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationTable
{
    private final Connect connnect=Connect.getConnect();
    private ResultSet  resultSet;

    /*==============================================
                        TEST
    ==============================================*/
    public boolean Create(Application app) 
    {  
        boolean check=false;
        String query="INSERT INTO access.applications ( AppName, AppDes) "
                + "VALUES ('"+app.getAppName()+"', '"+app.getAppDes()+"');";
        check=connnect.executeUpdate(query);
        return check;
    }
    public boolean Delete(int AppID)
    {
        String query="DELETE FROM access.applications WHERE AppID="+AppID+";";
        return connnect.executeUpdate(query);
    }
    
    public boolean Delete(String AppName)
    {
        Application app=Find(AppName);
        return Delete(app.getID());
    }

    /*==============================================
                        TEST
    ==============================================*/
    public boolean Update(Application app) 
    {       
        String query="UPDATE access.applications "
                + "SET AppName='"+app.getAppName()+"', AppDes='"+app.getAppDes()+"' WHERE AppID="+app.getID()+";";
        System.out.println(query);
        return connnect.executeUpdate(query);
    }
    
    public Application Find(String name) 
    {
        Application app=null;
        String query="SELECT * FROM access.applications WHERE AppName='"+name+"';";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
                app=new Application(resultSet.getInt("AppID"), resultSet.getString("AppName"), resultSet.getString("AppDes"));
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return app;
    }
    
    /*==============================================
                        TEST
    ==============================================*/
    public Application Find(int ID) 
    {
        Application app=null;
        String query="SELECT * FROM access.applications WHERE AppID='"+ID+"';";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
                app=new Application(resultSet.getInt("AppID"), resultSet.getString("AppName"), resultSet.getString("AppDes"));
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return app;
    }   
}
