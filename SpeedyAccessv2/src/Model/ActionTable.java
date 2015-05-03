package Model;

import Table.Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActionTable extends Connect
{
    public int Create(int AppID,String ActName,String ActDes)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call create_act(?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, ActName.trim());
            this.stmt.setString(3, ActDes.trim());
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);

            this.stmt.execute();
            result=stmt.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int Delete(int AppID,String ActName)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call Delete_act(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, ActName);
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            this.stmt.executeQuery();
            result=stmt.getInt(3);
        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int Update(int AppID,String OldActName, String NewActName,String ActDes)
    {
        int result=0;
        try {
            this.stmt=this.connection.prepareCall("{call update_act(?,?,?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, OldActName.trim());
            this.stmt.setString(3, ActDes.trim());
            this.stmt.registerOutParameter(4, java.sql.Types.INTEGER);
            this.stmt.setString(5, NewActName);

            this.stmt.execute();
            result=stmt.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Action Find(int AppID,String ActName)
    {
        Action act=null;
        try {
            this.stmt=this.connection.prepareCall("{call find_action(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, ActName.trim());
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(3);
            if(result==0)
                return null;
            if(resultSet.next())
            {
                act=new  Action(resultSet.getInt("ActID"), resultSet.getInt("AppID"), resultSet.getString("ActName"),
                        resultSet.getString("ActDes"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return act;
    }

    public ArrayList<Action> FindByApp(int AppID)
    {
        ArrayList<Action> array=new ArrayList<>();
        Action act=null;
        try {
            this.stmt=this.connection.prepareCall("{call ListActionByApp(?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.registerOutParameter(2, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(2);
            if(result==0)
                return null;

            while(resultSet.next())
            {
                act=new  Action(resultSet.getInt("ActID"), resultSet.getInt("AppID"), resultSet.getString("ActName"),
                        resultSet.getString("ActDes"));
                array.add(act);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public ArrayList<Action> FindByRole(int AppID,String RoleName)
    {
        ArrayList<Action> array=new ArrayList<>();
        Action act=null;
        try {
            this.stmt=this.connection.prepareCall("{call ListActionByRole(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, RoleName);
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(3);
            if(result==0||result==2)
                return null;
            while(resultSet.next())
            {
                act=new  Action(resultSet.getInt("ActID"), AppID, resultSet.getString("ActName"),
                        resultSet.getString("ActDes"));
                array.add(act);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public ArrayList<Action> FindByUser(int AppID,String UserName)
    {
        ArrayList<Action> array=new ArrayList<>();
        Action act=null;
        try {
            this.stmt=this.connection.prepareCall("{call ListActionByUser(?,?,?)}");
            this.stmt.setInt(1, AppID);
            this.stmt.setString(2, UserName);
            this.stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            ResultSet resultSet=this.stmt.executeQuery();
            int result=stmt.getInt(3);
            if(result==0||result==2)
                return null;
            while(resultSet.next())
            {
                act=new  Action(resultSet.getInt("ActID"), AppID, resultSet.getString("ActName"),
                        resultSet.getString("ActDes"));
                //System.out.println(act.toString());
                array.add(act);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

}
