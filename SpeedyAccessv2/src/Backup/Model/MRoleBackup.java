package Backup.Model;

import Backup.Entity.RoleBackup;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MRoleBackup extends ConnectBackup
{
    public MRoleBackup()
    {
        super();
    }

    public int Create(RoleBackup roleBackup)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call Create_RoleBackup(?,?,?,?,?)}");
            this.stmt.setInt(1, roleBackup.getID());
            this.stmt.setInt(2, roleBackup.getAppID());
            this.stmt.setString(3, roleBackup.getRoleName().trim());
            this.stmt.setString(4, roleBackup.getRoleDes().trim());
            this.stmt.registerOutParameter(5, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(5);

        } catch (SQLException ex) {
            Logger.getLogger(MRoleBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
