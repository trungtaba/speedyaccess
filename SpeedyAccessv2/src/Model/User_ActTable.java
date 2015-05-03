package Model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by trung on 4/22/2015.
 */
public class User_ActTable extends Connect
{
    public int Create(int AppID,String UserName,String ActName)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call AddUserAct(?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName.trim());
            this.stmt.setString(3, ActName.trim());
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int Delete(int AppID,String UserName,String ActName)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call DeleteUserAct(?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName.trim());
            this.stmt.setString(3, ActName.trim());
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
