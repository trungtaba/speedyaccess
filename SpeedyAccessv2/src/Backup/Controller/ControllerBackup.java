package Backup.Controller;

import Backup.Entity.*;
import Backup.Model.*;

public class ControllerBackup
{
    private final MApplicationBackup mApplicationBackup;
    private final MActionBackup mActionBackup;
    private final MRoleBackup mRoleBackup;
    private final MUserBackup mUserBackup;
    private final MConfigBackup mConfigBackup;
    private static ControllerBackup ourInstance = new ControllerBackup();

    public static ControllerBackup getInstance()
    {
        return ourInstance;
    }

    private ControllerBackup()
    {
        this.mActionBackup=new MActionBackup();
        this.mApplicationBackup=new MApplicationBackup();
        this.mRoleBackup=new MRoleBackup();
        this.mUserBackup=new MUserBackup();
        this.mConfigBackup=new MConfigBackup();
    }

    public int action(Object o)
    {
        if(o instanceof ActionBackup)
            mActionBackup.Create((ActionBackup)o);
        else if(o instanceof ApplicationBackup)
            mApplicationBackup.Create((ApplicationBackup)o);
        else if(o instanceof RoleBackup)
            mRoleBackup.Create((RoleBackup) o);
        else if(o instanceof UserBackup)
            mUserBackup.Create((UserBackup) o);
        else if(o instanceof ConfigBackup)
            mConfigBackup.Create((ConfigBackup)o);
        return -1;
    }
}
