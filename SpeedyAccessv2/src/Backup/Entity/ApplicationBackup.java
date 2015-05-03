package Backup.Entity;

public class ApplicationBackup
{
    private int ID;
    private String AppName;
    private String AppDes;
    private String KeyApp;
    private String TimeDelete;

    public ApplicationBackup(int ID, String appName, String appDes, String keyApp, String timeDelete)
    {
        this.ID = ID;
        AppName = appName;
        AppDes = appDes;
        KeyApp = keyApp;
        TimeDelete = timeDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationBackup)) return false;

        ApplicationBackup that = (ApplicationBackup) o;

        if (getID() != that.getID()) return false;
        if (!getAppName().equals(that.getAppName())) return false;
        if (!getAppDes().equals(that.getAppDes())) return false;
        if (!getKeyApp().equals(that.getKeyApp())) return false;
        return !(getTimeDelete() != null ? !getTimeDelete().equals(that.getTimeDelete()) : that.getTimeDelete() != null);

    }

    @Override
    public int hashCode() {
        int result = getID();
        result = 31 * result + getAppName().hashCode();
        result = 31 * result + getAppDes().hashCode();
        result = 31 * result + getKeyApp().hashCode();
        result = 31 * result + (getTimeDelete() != null ? getTimeDelete().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApplicationBackup{" +
                "ID=" + ID +
                ", AppName='" + AppName + '\'' +
                ", AppDes='" + AppDes + '\'' +
                ", KeyApp='" + KeyApp + '\'' +
                ", TimeDelete='" + TimeDelete + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public String getAppDes() {
        return AppDes;
    }

    public void setAppDes(String appDes) {
        AppDes = appDes;
    }

    public String getKeyApp() {
        return KeyApp;
    }

    public void setKeyApp(String keyApp) {
        KeyApp = keyApp;
    }

    public String getTimeDelete() {
        return TimeDelete;
    }

    public void setTimeDelete(String timeDelete) {
        TimeDelete = timeDelete;
    }
}
