package Interfaces;

import Table.Application;
import Table.Config;

import java.util.ArrayList;

public interface IApplication
{
    ArrayList<String> CreateApp(Application app);
    int DeleteApp(int AppID);
    int UpdateApp(int AppID, String NewAppName, String AppDes);
    Application Find(int AppID);

    int CreateConfig(Config config);
    int UpdateConfig(Config config);
}
