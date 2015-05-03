package Backup.Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Backup.Model.DatabaseConfig.*;

public class ConnectBackup
{
    protected CallableStatement stmt;
    protected Connection connection;

    protected ConnectBackup()
    {
        CreateConnect();
    }

    private void CreateConnect()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://"+SERVER+"/"+DATABASE, USERNAME, PASSWORD);
        }catch(ExceptionInInitializerError ex)
        {
            System.err.println("Error:"+ex.toString());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


