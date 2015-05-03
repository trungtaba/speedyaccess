package Backup.Model;

import Backup.Entity.ConfigBackup;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MConfigBackup extends ConnectBackup
{
    public MConfigBackup()
    {
        super();
    }

    public int Create(ConfigBackup configBackup)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call Create_Config(?,?,?,?,?,?)}");
            this.stmt.setInt(1, configBackup.getAppID());
            this.stmt.setString(2, configBackup.getPasswordFormat().trim());
            this.stmt.setString(3, configBackup.getPasswordValidate().trim());
            this.stmt.setString(4, configBackup.getUserNameValidate().trim());
            this.stmt.setInt(5, configBackup.getMaxFailedPassword());
            this.stmt.registerOutParameter(6, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(6);

        } catch (SQLException ex) {
            Logger.getLogger(MConfigBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
