package Table;
public class User_role
{
    private int ID;
     private int UserID;
    private int RoleID;

    public User_role(int ID, int UserID, int RoleID)
    {
        this.ID = ID;
        this.UserID = UserID;
        this.RoleID = RoleID;
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
        hash = 73 * hash + this.UserID;
        hash = 73 * hash + this.ID;
        hash = 73 * hash + this.RoleID;
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
        final User_role other = (User_role) obj;
        if (this.UserID != other.UserID) {
            return false;
        }
        if (this.ID != other.ID) {
            return false;
        }
        return this.RoleID == other.RoleID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    @Override
    public String toString() {
        return "User_role{" + "ID=" + ID + ", UserID=" + UserID + ", RoleID=" + RoleID + '}';
    }   
}
