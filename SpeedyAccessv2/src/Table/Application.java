package Table;

public class Application
{
    private int ID;
    private String AppName;
    private String AppDes;
    private String KeyApp;

    public Application(int ID, String AppName, String AppDes,String KeyApp)
    {
        this.ID = ID;
        this.AppName = AppName;
        this.AppDes = AppDes;
        this.KeyApp=KeyApp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;

        Application that = (Application) o;

        if (getID() != that.getID()) return false;
        if (!getAppName().equals(that.getAppName())) return false;
        if (!getAppDes().equals(that.getAppDes())) return false;
        return getKeyApp().equals(that.getKeyApp());

    }

    @Override
    public int hashCode() {
        int result = getID();
        result = 31 * result + getAppName().hashCode();
        result = 31 * result + getAppDes().hashCode();
        result = 31 * result + getKeyApp().hashCode();
        return result;
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
}
