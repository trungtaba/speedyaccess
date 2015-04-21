package Interfaces;

import Table.Application;

public interface IApplicationController 
{
    public int CreateApp(Application app);
    public int DeleteApp(int AppID);
    public int DeleteApp(String AppName);
    public int UpdateApp(Application app);
    public Application Find(int AppID);
    public Application Find(String AppName);
}
