package Backup.Model;

import Backup.Entity.UserBackup;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MUserBackup extends ConnectBackup
{
    public MUserBackup()
    {
        super();
    }

    public int Create(UserBackup userBackup)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call Create_Userbackup(?,?,?,?,?,?,?)}");
            this.stmt.setInt(1, userBackup.getID());
            this.stmt.setInt(2, userBackup.getAppID());
            this.stmt.setString(3, userBackup.getUserName().trim());
            this.stmt.setString(4, userBackup.getPasswordUser().trim());
            this.stmt.setString(5, userBackup.getEmail().trim());
            this.stmt.setString(6, userBackup.getCreateDate().trim());
            this.stmt.registerOutParameter(7, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(7);

        } catch (SQLException ex) {
            Logger.getLogger(MUserBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
