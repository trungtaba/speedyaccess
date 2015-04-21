package Table;

import java.util.Objects;

public class Application
{
    private int ID;
    private String AppName;
    private String AppDes;

    public Application(int ID, String AppName, String AppDes) 
    {
        this.ID = ID;
        this.AppName = AppName;
        this.AppDes = AppDes;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.AppName);
        hash = 67 * hash + Objects.hashCode(this.ID);
        hash = 67 * hash + Objects.hashCode(this.AppDes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Application other = (Application) obj;
        if (!Objects.equals(this.AppName, other.AppName)) {
            return false;
        }
        if (!Objects.equals(this.AppDes, other.AppDes)) {
            return false;
        }
        return Objects.equals(this.ID, other.ID);
    }

   

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String AppName) {
        this.AppName = AppName;
    }

    public String getAppDes() {
        return AppDes;
    }

    public void setAppDes(String AppDes) {
        this.AppDes = AppDes;
    }

    @Override
    public String toString() {
        return "Application{" + "ID=" + ID + ", AppName=" + AppName + ", AppDes=" + AppDes + '}';
    }   
}
