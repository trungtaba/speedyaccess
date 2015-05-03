package Backup.Model;

import Backup.Entity.ActionBackup;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MActionBackup extends ConnectBackup
{
    public MActionBackup()
    {
        super();
    }

    public int Create(ActionBackup actionBackup)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call Create_ActBackup(?,?,?,?,?)}");
            this.stmt.setInt(1, actionBackup.getID());
            this.stmt.setInt(2, actionBackup.getAppID());
            this.stmt.setString(3, actionBackup.getActName().trim());
            this.stmt.setString(4, actionBackup.getActDes().trim());
            this.stmt.registerOutParameter(5, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(5);

        } catch (SQLException ex) {
            Logger.getLogger(MActionBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
