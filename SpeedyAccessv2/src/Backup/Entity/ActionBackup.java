package Backup.Entity;

public class ActionBackup
{
    private int ID;
    private int AppID;
    private String ActName;
    private String ActDes;
    private String TimeDelete;

    public ActionBackup(int ID, int appID, String actName, String actDes, String timeDelete)
    {
        this.ID = ID;
        AppID = appID;
        ActName = actName;
        ActDes = actDes;
        TimeDelete = timeDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionBackup)) return false;

        ActionBackup that = (ActionBackup) o;

        if (getID() != that.getID()) return false;
        if (getAppID() != that.getAppID()) return false;
        if (!getActName().equals(that.getActName())) return false;
        if (!getActDes().equals(that.getActDes())) return false;
        return getTimeDelete().equals(that.getTimeDelete());

    }

    @Override
    public int hashCode() {
        int result = getID();
        result = 31 * result + getAppID();
        result = 31 * result + getActName().hashCode();
        result = 31 * result + getActDes().hashCode();
        result = 31 * result + getTimeDelete().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CActionBackup{" +
                "ID=" + ID +
                ", AppID=" + AppID +
                ", ActName='" + ActName + '\'' +
                ", ActDes='" + ActDes + '\'' +
                ", TimeDelete='" + TimeDelete + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAppID() {
        return AppID;
    }

    public void setAppID(int appID) {
        AppID = appID;
    }

    public String getActName() {
        return ActName;
    }

    public void setActName(String actName) {
        ActName = actName;
    }

    public String getActDes() {
        return ActDes;
    }

    public void setActDes(String actDes) {
        ActDes = actDes;
    }

    public String getTimeDelete() {
        return TimeDelete;
    }

    public void setTimeDelete(String timeDelete) {
        TimeDelete = timeDelete;
    }
}

