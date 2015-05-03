package Backup.Entity;

public class RoleBackup
{
    private String RoleName;
    private String RoleDes;
    private int AppID;
    private int ID;
    private String TimeDelete;

    public RoleBackup(int ID, String RoleName, String RoleDes,int AppID,String timeDelete)
    {
        this.ID=ID;
        this.RoleName = RoleName;
        this.RoleDes = RoleDes;
        this.AppID=AppID;
        this.TimeDelete=timeDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleBackup)) return false;

        RoleBackup that = (RoleBackup) o;

        if (getAppID() != that.getAppID()) return false;
        if (getID() != that.getID()) return false;
        if (getRoleName() != null ? !getRoleName().equals(that.getRoleName()) : that.getRoleName() != null)
            return false;
        if (getRoleDes() != null ? !getRoleDes().equals(that.getRoleDes()) : that.getRoleDes() != null) return false;
        return !(getTimeDelete() != null ? !getTimeDelete().equals(that.getTimeDelete()) : that.getTimeDelete() != null);

    }

    @Override
    public int hashCode() {
        int result = getRoleName() != null ? getRoleName().hashCode() : 0;
        result = 31 * result + (getRoleDes() != null ? getRoleDes().hashCode() : 0);
        result = 31 * result + getAppID();
        result = 31 * result + getID();
        result = 31 * result + (getTimeDelete() != null ? getTimeDelete().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleBackup{" +
                "RoleName='" + RoleName + '\'' +
                ", RoleDes='" + RoleDes + '\'' +
                ", AppID=" + AppID +
                ", ID=" + ID +
                ", TimeDelete='" + TimeDelete + '\'' +
                '}';
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getRoleDes() {
        return RoleDes;
    }

    public void setRoleDes(String roleDes) {
        RoleDes = roleDes;
    }

    public int getAppID() {
        return AppID;
    }

    public void setAppID(int appID) {
        AppID = appID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTimeDelete() {
        return TimeDelete;
    }

    public void setTimeDelete(String timeDelete) {
        TimeDelete = timeDelete;
    }
}
