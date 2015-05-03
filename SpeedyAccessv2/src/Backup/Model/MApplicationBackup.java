package Backup.Model;

import Backup.Entity.ApplicationBackup;
import Model.ActionTable;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MApplicationBackup extends ConnectBackup
{
    public MApplicationBackup()
    {
        super();
    }
    public int Create(ApplicationBackup applicationBackup)
    {
        int result=-1;
        try {
            this.stmt=this.connection.prepareCall("call Create_AppBackup(?,?,?,?,?)");
            this.stmt.setInt(1, applicationBackup.getID());
            this.stmt.setString(2, applicationBackup.getAppName().trim());
            this.stmt.setString(3, applicationBackup.getAppDes().trim());
            this.stmt.setString(4, applicationBackup.getKeyApp().trim());
            this.stmt.registerOutParameter(5, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(5);

        } catch (SQLException ex) {
            Logger.getLogger(MApplicationBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
