package Table;

import java.util.Objects;

public class Action
{
    private int ID;
    private int AppID;
    private String ActName;
    private String ActDes;

    public Action(int ID, int AppID, String ActName, String ActDes) 
    {
        this.ID = ID;
        this.AppID = AppID;
        this.ActName = ActName;
        this.ActDes = ActDes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.ID;
        hash = 79 * hash + this.AppID;
        hash = 79 * hash + Objects.hashCode(this.ActName);
        hash = 79 * hash + Objects.hashCode(this.ActDes);
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
        final Action other = (Action) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.AppID != other.AppID) {
            return false;
        }
        if (!Objects.equals(this.ActName, other.ActName)) {
            return false;
        }
        if (!Objects.equals(this.ActDes, other.ActDes)) {
            return false;
        }
        return true;
    }

    
    public int getAppID() {
        return AppID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAppID(int AppID) {
        this.AppID = AppID;
    }
    
    public String getActName() 
    {
        return ActName;
    }

    public void setActName(String ActName) 
    {
        this.ActName = ActName;
    }

    public String getActDes() {
        return ActDes;
    }

    public void setActDes(String ActDes)
    {
        this.ActDes = ActDes;
    }

    @Override
    public String toString() {
        return "Action{" + "ID=" + ID + ", AppID=" + AppID + ", ActName=" + ActName + ", ActDes=" + ActDes + '}';
    }
}
