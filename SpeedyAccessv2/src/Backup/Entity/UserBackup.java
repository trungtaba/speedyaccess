package Backup.Entity;

public class UserBackup
{
    private int ID;
    private int AppID;
    private String UserName;
    private String PasswordUser;
    private String Email;
    private String CreateDate;
    private String TimeDelete;

    public UserBackup(int ID, int appID, String userName, String passwordUser, String email, String createDate, String timeDelete)
    {
        this.ID = ID;
        AppID = appID;
        UserName = userName;
        PasswordUser = passwordUser;
        Email = email;
        CreateDate = createDate;
        TimeDelete = timeDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBackup)) return false;

        UserBackup that = (UserBackup) o;

        if (getID() != that.getID()) return false;
        if (getAppID() != that.getAppID()) return false;
        if (!getUserName().equals(that.getUserName())) return false;
        if (!getPasswordUser().equals(that.getPasswordUser())) return false;
        if (!getEmail().equals(that.getEmail())) return false;
        if (!getCreateDate().equals(that.getCreateDate())) return false;
        return !(getTimeDelete() != null ? !getTimeDelete().equals(that.getTimeDelete()) : that.getTimeDelete() != null);

    }

    @Override
    public int hashCode() {
        int result = getID();
        result = 31 * result + getAppID();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getPasswordUser().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getCreateDate().hashCode();
        result = 31 * result + (getTimeDelete() != null ? getTimeDelete().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserBackup{" +
                "ID=" + ID +
                ", AppID=" + AppID +
                ", UserName='" + UserName + '\'' +
                ", PasswordUser='" + PasswordUser + '\'' +
                ", Email='" + Email + '\'' +
                ", CreateDate='" + CreateDate + '\'' +
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

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPasswordUser() {
        return PasswordUser;
    }

    public void setPasswordUser(String passwordUser) {
        PasswordUser = passwordUser;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getTimeDelete() {
        return TimeDelete;
    }

    public void setTimeDelete(String timeDelete) {
        TimeDelete = timeDelete;
    }
}
