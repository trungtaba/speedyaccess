package Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect
{
    public static Connect connect=new Connect();


    protected CallableStatement stmt;
    protected Connection connection;

    public Connect()
    {
        CreateConnect();
    }

    private void CreateConnect()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //connection= DriverManager.getConnection("jdbc:mysql://"+SERVER_NAME+"/"+DATABASE_NAME,USER_NAME,PASSWORD);
            connection= DriverManager.getConnection("jdbc:mysql://localhost/access","root","justonlyyou153");
        }catch(ExceptionInInitializerError ex)
        {
            System.err.println("Error:"+ex.toString());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

