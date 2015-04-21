package Table;

import java.util.Objects;

public class User
{
    private int ID;
    private int AppID;
    private String UserName;
    private String LastActiveDate;

    public User(int UserID, int AppID, String UserName, String LastActiveDate)
    {
        this.ID=UserID;
        this.AppID = AppID;
        this.UserName = UserName;
        this.LastActiveDate = LastActiveDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAppID() 
    {
        return AppID;
    }

    public void setAppID(int AppID) 
    {
        this.AppID = AppID;
    }

    public String getUserName() 
    {
        return UserName;
    }

    public void setUserName(String UserName) 
    {
        this.UserName = UserName;
    }

    public String getLastActiveDate()
    {
        return LastActiveDate;
    }

    public void setLastActiveDate(String LastActiveDate)
    {
        this.LastActiveDate = LastActiveDate;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 71 * hash + this.ID;
        hash = 71 * hash + Objects.hashCode(this.UserName);
        hash = 71 * hash + Objects.hashCode(this.AppID);
        hash = 71 * hash + Objects.hashCode(this.LastActiveDate);
        return hash;
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
        final User other = (User) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.AppID != other.AppID) {
            return false;
        }
        if (!Objects.equals(this.UserName, other.UserName)) {
            return false;
        }
        return Objects.equals(this.LastActiveDate, other.LastActiveDate);
    }

    @Override
    public String toString() {
        return "User{" + "ID=" + ID + ", AppID=" + AppID + ", UserName=" + 
                UserName + ", LastActiveDate=" + LastActiveDate + '}';
    }  
}
