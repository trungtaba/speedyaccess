package Backup.Entity;

public class ConfigBackup
{
    private int AppID;
    private String PasswordFormat;
    private String PasswordValidate;
    private String UserNameValidate;
    private int MaxFailedPassword;
    private String TimeDelete;

    public ConfigBackup(int appID, String passwordFormat, String passwordValidate,
                        String userNameValidate, int maxFailedPassword, String timeDelete)
    {
        AppID = appID;
        PasswordFormat = passwordFormat;
        PasswordValidate = passwordValidate;
        UserNameValidate = userNameValidate;
        MaxFailedPassword = maxFailedPassword;
        TimeDelete = timeDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfigBackup)) return false;

        ConfigBackup that = (ConfigBackup) o;

        if (getAppID() != that.getAppID()) return false;
        if (getMaxFailedPassword() != that.getMaxFailedPassword()) return false;
        if (!getPasswordFormat().equals(that.getPasswordFormat())) return false;
        if (!getPasswordValidate().equals(that.getPasswordValidate())) return false;
        if (!getUserNameValidate().equals(that.getUserNameValidate())) return false;
        return !(getTimeDelete() != null ? !getTimeDelete().equals(that.getTimeDelete()) : that.getTimeDelete() != null);

    }

    @Override
    public int hashCode() {
        int result = getAppID();
        result = 31 * result + getPasswordFormat().hashCode();
        result = 31 * result + getPasswordValidate().hashCode();
        result = 31 * result + getUserNameValidate().hashCode();
        result = 31 * result + getMaxFailedPassword();
        result = 31 * result + (getTimeDelete() != null ? getTimeDelete().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ConfigBackup{" +
                "AppID=" + AppID +
                ", PasswordFormat='" + PasswordFormat + '\'' +
                ", PasswordValidate='" + PasswordValidate + '\'' +
                ", UserNameValidate='" + UserNameValidate + '\'' +
                ", MaxFailedPassword=" + MaxFailedPassword +
                ", TimeDelete='" + TimeDelete + '\'' +
                '}';
    }

    public int getAppID() {
        return AppID;
    }

    public void setAppID(int appID) {
        AppID = appID;
    }

    public String getPasswordFormat() {
        return PasswordFormat;
    }

    public void setPasswordFormat(String passwordFormat) {
        PasswordFormat = passwordFormat;
    }

    public String getPasswordValidate() {
        return PasswordValidate;
    }

    public void setPasswordValidate(String passwordValidate) {
        PasswordValidate = passwordValidate;
    }

    public String getUserNameValidate() {
        return UserNameValidate;
    }

    public void setUserNameValidate(String userNameValidate) {
        UserNameValidate = userNameValidate;
    }

    public int getMaxFailedPassword() {
        return MaxFailedPassword;
    }

    public void setMaxFailedPassword(int maxFailedPassword) {
        MaxFailedPassword = maxFailedPassword;
    }

    public String getTimeDelete() {
        return TimeDelete;
    }

    public void setTimeDelete(String timeDelete) {
        TimeDelete = timeDelete;
    }
}
