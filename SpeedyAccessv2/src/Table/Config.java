package Table;

public class Config
{
    private int AppID;
    private String PasswordFormat;
    private String UserNameValidate;
    private String PasswordValidate;
    private int MaxFailedPassword;

    public Config(int appID, String passwordFormat, String userNameValidate,int maxFailedPassword, String passwordValidate)
    {
        AppID = appID;
        PasswordFormat = passwordFormat;
        UserNameValidate = userNameValidate;
        PasswordValidate = passwordValidate;
        MaxFailedPassword = maxFailedPassword;
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

    public String getUserNameValidate() {
        return UserNameValidate;
    }

    public void setUserNameValidate(String userNameValidate) {
        UserNameValidate = userNameValidate;
    }

    public String getPasswordValidate() {
        return PasswordValidate;
    }

    public void setPasswordValidate(String passwordValidate) {
        PasswordValidate = passwordValidate;
    }

    public int getMaxFailedPassword() {
        return MaxFailedPassword;
    }

    public void setMaxFailedPassword(int maxFailedPassword) {
        MaxFailedPassword = maxFailedPassword;
    }
}
