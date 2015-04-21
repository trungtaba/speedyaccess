package Model;
/*
public boolean Create(Action act)
public boolean Update(Action act)
public boolean Delete(int ActID)
public boolean Delete(String name,int AppID)
public Action Find(String name,int AppID)
public Action Find(int ActID)
public ArrayList<Action> FindByApp(int AppID)
*/
import Table.Action;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActionTable 
{
    private final Connect connnect=Connect.getConnect();
    private ResultSet  resultSet;

    //test
    public boolean Create(Action act)    
    {      
       String query="INSERT INTO access.actions (ActName, ActDes,AppID)"
               + " VALUES ('"+act.getActName()+"', '"+act.getActDes()+"',"+act.getAppID()+");";
       connnect.executeUpdate(query);
       return true;
    }

    /*=============================================
                        TEST
    =============================================*/
    public boolean Update(Action act) 
    {
        String query="UPDATE access.actions "
                + "SET ActDes='"+act.getActDes()+"',ActName='"+act.getActName()+"' WHERE ActID="+act.getID()+";";
        return connnect.executeUpdate(query);
    }

    /*=============================================
                        TEST
    =============================================*/
    public boolean Delete(int ActID)
    {
        String query="DELETE FROM access.actions WHERE ActID="+ActID+";";
        return connnect.executeUpdate(query);
    }
    
    public boolean Delete(int AppID,String ActName)
    {
        Action act=Find(AppID,ActName);
        return Delete(act.getID());
    }
 
    /*=============================================
                        TEST
    =============================================*/
    public Action Find(int AppID,String ActName)
    {
        Action act=null;
               
        String query="SELECT * FROM access.actions where ActName='"+ActName+"'and AppID="+AppID+";";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            {
                act=new Action(resultSet.getInt("ActID"),resultSet.getInt("AppID"),
                    resultSet.getString("ActName"), resultSet.getString("ActDes"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return act;
    }
    

    /*=============================================
                        TEST
    =============================================*/
    public Action Find(int ActID)
    {
        Action act=null;
               
        String query="SELECT * FROM access.actions where ActID="+ActID+";";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            {
                act=new Action(resultSet.getInt("ActID"),resultSet.getInt("AppID"), resultSet.getString("ActName"),
                    resultSet.getString("ActDes"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return act;
    }
    
    //test
    public ArrayList<Action> FindByApp(int AppID)
    {
        ArrayList<Action> array=new ArrayList<>();
        
        String query="SELECT * FROM access.actions where AppID="+AppID+";";
        resultSet=connnect.executeQuery(query);
        try {
            while(resultSet.next())
            {
                array.add(new Action(resultSet.getInt("ActID"), resultSet.getInt("AppID"),
                        resultSet.getString("ActName"), resultSet.getString("ActDes")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
}
