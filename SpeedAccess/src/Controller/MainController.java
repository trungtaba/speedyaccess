package Controller;

import XML.ParseXML.MainParse;

public class MainController
{
    ActionContronller actionController;
    ApplicationController applicationController;
    RoleController roleController;
    UserController userController;
    MainParse parse;
    
    public MainController()
    {
        this.actionController=new ActionContronller();
        this.applicationController=new ApplicationController();
        this.roleController=new RoleController();
        this.userController=new UserController();
        this.parse=new MainParse();
    }
    public String action(String string)
    {
        int function=parse.FindFunction(string);
        if((function>0)&&(function<21))
            return actionController.action(function,string);
        if((function>20)&&(function<41))
            return applicationController.action(function,string);
        if((function>60)&&(function<81))
            return roleController.action(function,string);
        if((function>80)&&(function<101))
            return userController.action(function,string);
        else return null;
    }
}
