package Controller;

import Table.Application;
import XML.ParseXML.MainParse;

import java.util.ArrayList;

import static MyCode.MyCodeFunction.CreateApp;

public class MainController
{
    private CAction cAction;
    private CApplication cApplication;
    private CRole cRole;
    private CUser cUser;
    private ArrayList<Application> applicationArrayList;
    private MainParse mainParse;
    private static MainController ourInstance = new MainController();

    public static MainController getInstance()
    {
        return ourInstance;
    }

    private MainController()
    {
        this.cAction=new CAction();
        this.cApplication=new CApplication();
        this.cRole=new CRole();
        this.cUser=new CUser();
        this.mainParse=new MainParse();
        applicationArrayList=cApplication.ListApplications();

    }

    private boolean CheckKeyApp(int AppID,String KepApp)
    {
        for(Application app:applicationArrayList)
        {
            if(app.getID()==AppID)
            {
                return app.getKeyApp().equals(KepApp);
            }
        }
        return false;
    }

    public String action(String string)
    {

        boolean tmp=true;
        boolean check=true;
        ArrayList<String> parameter=mainParse.FindParameter(string);
        int AppID=Integer.parseInt(parameter.get(0));
        String key=parameter.get(1);
        int function=Integer.parseInt(parameter.get(2));
        int ID=Integer.parseInt(parameter.get(3).trim());

        if(function!=CreateApp)
        {
            tmp=false;
            check=CheckKeyApp(AppID,key);
            System.out.println("Check="+check);
        }
        if (check==false&&tmp==false)
        {
            String keyApp=null;
            for(Application app:applicationArrayList)
            {
                if(app.getID()==Integer.parseInt(parameter.get(0)))
                {
                    keyApp=app.getKeyApp();
                }
            }
            return mainParse.Send_KeyError(Integer.parseInt(parameter.get(0)),keyApp,Integer.parseInt(parameter.get(3)),
                    parameter.get(1));
        }
        else
        {
            if((function>0)&&(function<21))
                return cAction.action(AppID,key,ID,function,string);
            if((function>20)&&(function<41))
                return cApplication.action(AppID,key,ID,function,string);
            if((function>60)&&(function<81))
                return cRole.action(AppID,key,ID,function,string);
            if((function>80)&&(function<101))
                return cUser.action(AppID,key,ID,function,string);
            else return null;
        }
    }
}
