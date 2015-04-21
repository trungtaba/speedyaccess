package Table;
public class Role_action 
{
    private int ID;
    private int RoleID;
    private int ActID;

    public Role_action(int ID,int RoleID, int ActID) 
    {
        this.ID=ID;
        this.RoleID = RoleID;
        this.ActID = ActID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRoleID() 
    {
        return RoleID;
    }

    public void setRoleID(int RoleID) 
    {
        this.RoleID = RoleID;
    }

    public int getActID()
    {
        return ActID;
    }

    public void setActID(int ActID)
    {
        this.ActID = ActID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.RoleID;
        hash = 43 * hash + this.ActID;
        hash = 43 * hash + this.ID;
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
        final Role_action other = (Role_action) obj;
        if (this.RoleID != other.RoleID) {
            return false;
        }
        if (this.ActID != other.ActID) {
            return false;
        }
        return this.ID == other.ID;
    }

    @Override
    public String toString() {
        return "Role_action{" + "ID=" + ID + ", RoleID=" + RoleID + ", ActID=" + ActID + '}';
    }
    
    
}
