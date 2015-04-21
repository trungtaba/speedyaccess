package Table;

import java.util.Objects;

public class Role 
{
    private String RoleName;
    private String RoleDes;
    private int AppID;
    private int ID;
    public Role(int ID, String RoleName, String RoleDes,int AppID) 
    {
        this.ID=ID;
        this.RoleName = RoleName;
        this.RoleDes = RoleDes;
        this.AppID=AppID;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role other = (Role) obj;
        if (this.ID != other.ID) {
            return false;
        }
         if (this.AppID != other.AppID) {
            return false;
        }
        return Objects.equals(this.RoleName, other.RoleName);
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }

    public String getRoleDes() {
        return RoleDes;
    }

    public void setRoleDes(String RoleDes) {
        this.RoleDes = RoleDes;
    }

    public int getAppID() {
        return AppID;
    }

    public void setAppID(int AppID) {
        this.AppID = AppID;
    }

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.RoleName);
        hash = 97 * hash + this.AppID;
        hash = 97 * hash + this.ID;
        return hash;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Role{" + "RoleName=" + RoleName + ", RoleDes=" + RoleDes + ", AppID=" + AppID + ", ID=" + ID + '}';
    }
       
}
