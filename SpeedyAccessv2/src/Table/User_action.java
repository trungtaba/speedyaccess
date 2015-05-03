package Table;
public class User_action {
    private int ID;
    private int UserID;
    private int ActID;

    public User_action(int ID, int UserID, int ActID) 
    {
        this.ID = ID;
        this.UserID = UserID;
        this.ActID = ActID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 67 * hash + this.UserID;
        hash = 67 * hash + this.ID;
        hash = 67 * hash + this.ActID;
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
        final User_action other = (User_action) obj;
        if (this.UserID != other.UserID) {
            return false;
        }
        if (this.ID != other.ID) {
            return false;
        }
        return this.ActID == other.ActID;
    }

    
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getActID() {
        return ActID;
    }

    public void setActID(int ActID) {
        this.ActID = ActID;
    }

    @Override
    public String toString() {
        return "User_action{" + "ID=" + ID + ", UserID=" + UserID + ", ActID=" + ActID + '}';
    }    
}
