package Controller;

import Backup.Controller.ControllerBackup;
import Backup.Entity.*;
import Interfaces.IApplication;
import Model.*;
import Table.*;
import XML.ParseXML.ApplicationParse;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import static MyCode.MyCodeFunction.*;
import static MyCode.MyException.*;

public class CApplication implements IApplication
{
    private final ApplicationTable applicationTable;
    private final ActionTable actionTable;
    private final RoleTable roleTable;
    private final UserTable userTable;
    private final ConfigTable configTable;
    private final ApplicationParse applicationParse;
    private ControllerBackup controllerBackup;

    public CApplication()
    {
        this.applicationTable =new ApplicationTable();
        this.configTable=new ConfigTable();
        this.applicationParse =new ApplicationParse();
        this.controllerBackup=ControllerBackup.getInstance();
        this.actionTable=new ActionTable();
        this.roleTable=new RoleTable();
        this.userTable=new UserTable();
    }
    String action(int AppID,String key,int ID,int function,String string)
    {
        if(function==CreateApp)
        {
            ArrayList<String> arrayList= applicationParse.Receive_Create_Update_App(string);
            Application application=new Application(AppID,arrayList.get(0),arrayList.get(1),key);
            ArrayList<String> result=CreateApp(application);

            return applicationParse.Send_Result_App(Integer.parseInt(result.get(0).trim()), result.get(1), function, ID, SUCCESS);
        }
        else if(function==DeleteApp)
        {
            int result=DeleteApp(AppID);
            return applicationParse.Send_Result_App(AppID,key,function,ID,result);
        }
        else if(function==UpdateApp)
        {
            ArrayList<String> arrayList= applicationParse.Receive_Create_Update_App(string);
            int result=UpdateApp(AppID,arrayList.get(0),arrayList.get(1));
            return applicationParse.Send_Result_App(AppID,key,function,ID,result);
        }
        else if(function==FindApp)
        {
            Application application=Find(AppID);
            return applicationParse.Send_Find_App(AppID,key,function,ID,application);
        }
        else if(function==CreateConfig)
        {
            ArrayList<String> arrayList= applicationParse.Receive_Create_Update_Config(string);
            Config config=new Config(AppID,arrayList.get(0),arrayList.get(1),Integer.parseInt(arrayList.get(2)),arrayList.get(3));
            int result=CreateConfig(config);
            return applicationParse.Send_Result_Config(AppID,key,function,ID,result);
        }
        else if(function==UpdateConfig)
        {
            ArrayList<String> arrayList= applicationParse.Receive_Create_Update_Config(string);
            Config config=new Config(AppID,arrayList.get(0),arrayList.get(1),Integer.parseInt(arrayList.get(2)),arrayList.get(3));
            int result=UpdateConfig(config);
            return applicationParse.Send_Result_Config(AppID,key,function,ID,result);
        }
        else return null;
    }
    @Override
    public ArrayList<String> CreateApp(Application app)
    {
        ArrayList<String> array=new ArrayList<>();
        CreateKey ck=new CreateKey();
        String keyapp=ck.nextSessionId();
        int result=applicationTable.Create(app.getAppName().trim(),app.getAppDes().trim(),keyapp);
        if(result==0)
            return null;
        array.add(result+"");
        array.add(keyapp);

        return array;
    }

    @Override
    public int DeleteApp(int AppID)
    {
        int result=-1;
        Application application=Find(AppID);
        if(application==null)
            return APP_NOT_HAVE;
        ApplicationBackup applicationBackup=new ApplicationBackup(application.getID(),application.getAppName(),application.getAppDes(),
                application.getKeyApp(),null);
        //Delete Action
        ArrayList<Action> actionArrayList=actionTable.FindByApp(AppID);
        for(Action action:actionArrayList)
        {
            ActionBackup actionBackup=new ActionBackup(action.getID(),action.getAppID(),action.getActName(),action.getActDes(),null);
            controllerBackup.action(actionBackup);
            result =actionTable.Delete(AppID,action.getActName());
        }
        //Delete Role
        ArrayList<Role> roleArrayList=roleTable.FindByApp(AppID);
        for(Role role:roleArrayList)
        {
            System.out.println(role.toString());
            RoleBackup roleBackup=new RoleBackup(role.getID(),role.getRoleName(),role.getRoleDes(),role.getAppID(),null);
            controllerBackup.action(roleBackup);
            result=roleTable.Delete(AppID,role.getRoleName());
        }
        //Delete User
        ArrayList<User> userArrayList=userTable.FindByApp(AppID);
        for(User user:userArrayList)
        {
            UserBackup userBackup=new UserBackup(user.getID(),user.getAppID(),user.getUserName(),user.getPasswordUser(),
                    user.getEmail(),user.getCreateDate(),null);
            controllerBackup.action(userBackup);
            result=userTable.Delete(AppID,user.getUserName());
        }
        //Delete Config
        Config config=configTable.Find(AppID);
        ConfigBackup configBackup=new ConfigBackup(AppID,config.getPasswordFormat(),config.getPasswordValidate(),
                config.getUserNameValidate(),config.getMaxFailedPassword(),null);
        controllerBackup.action(configBackup);
        configTable.Delete(AppID);
        //Delete application
        controllerBackup.action(applicationBackup);
        applicationTable.Delete(AppID);

        return SUCCESS;
    }

    @Override
    public int UpdateApp(int AppID,String NewAppName,String AppDes)
    {
        int result=-1;
        result=applicationTable.Update(AppID,NewAppName,AppDes);
        if(result==0)
            return ERROR;
        else if(result==1)
            return SUCCESS;
        return result;
    }

    @Override
    public Application Find(int AppID)
    {
        return applicationTable.Find(AppID);
    }

    @Override
    public int CreateConfig(Config config)
    {
        if(config.getMaxFailedPassword()<=0)
            return MAX_FAILT_PASS_INCORRECT;
        int result=-1;
        result=configTable.Create(config.getAppID(),config.getUserNameValidate(),config.getMaxFailedPassword(),config.getPasswordValidate());
        if(result==0)
            return ERROR;
        else
            return SUCCESS;
    }

    @Override
    public int UpdateConfig(Config config)
    {
        Application app=Find(config.getAppID());
        if(app==null)
            return ERROR;
        else {
            int result = -1;
            result = configTable.Update(config.getAppID(),config.getUserNameValidate(),config.getMaxFailedPassword(),config.getPasswordValidate());
            if (result == 0)
                return ERROR;
            else
                return SUCCESS;
        }
    }

    public ArrayList<Application> ListApplications()
    {
        return applicationTable.applicationArrayList();
    }

    private static final class CreateKey
    {
        private SecureRandom random = new SecureRandom();

        public String nextSessionId()
        {
            String result= new BigInteger(130, random).toString(32);
            return result;
        }
    }

    public static void main(String[]argv)
    {
        CApplication cApplication=new CApplication();
        cApplication.DeleteApp(3);
    }
}
