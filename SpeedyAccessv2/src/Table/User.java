package Table;

public class User
{
    private int ID;
    private int AppID;
    private String UserName;
    private String PasswordUser;
    private String Email;
    private int IsApproved;
    private int IsLockedOut;
    private String CreateDate;
    private String LastLoginDate;
    private String LastPasswordChangedDate;
    private String LastLockoutDate;
    private int FailedPasswordAttemptCount;
    private String FailedPasswordAttemptWindowStart;
    private String LastActiveDate;

    public User(int appID, int ID, String userName, String passwordUser,
                String email, int isApproved, int isLockedOut, String createDate,
                String lastLoginDate, String lastPasswordChangedDate, String lastLockoutDate,
                int failedPasswordAttemptCount, String failedPasswordAttemptWindowStart, String lastActiveDate) {
        AppID = appID;
        this.ID = ID;
        UserName = userName;
        PasswordUser = passwordUser;
        Email = email;
        IsApproved = isApproved;
        IsLockedOut = isLockedOut;
        CreateDate = createDate;
        LastLoginDate = lastLoginDate;
        LastPasswordChangedDate = lastPasswordChangedDate;
        LastLockoutDate = lastLockoutDate;
        FailedPasswordAttemptCount = failedPasswordAttemptCount;
        FailedPasswordAttemptWindowStart = failedPasswordAttemptWindowStart;
        LastActiveDate = lastActiveDate;
    }

    public String getLastActiveDate() {
        return LastActiveDate;
    }

    public void setLastActiveDate(String lastActiveDate) {
        LastActiveDate = lastActiveDate;
    }

    public String getFailedPasswordAttemptWindowStart() {
        return FailedPasswordAttemptWindowStart;
    }

    public void setFailedPasswordAttemptWindowStart(String failedPasswordAttemptWindowStart) {
        FailedPasswordAttemptWindowStart = failedPasswordAttemptWindowStart;
    }

    public int getFailedPasswordAttemptCount() {
        return FailedPasswordAttemptCount;
    }

    public void setFailedPasswordAttemptCount(int failedPasswordAttemptCount) {
        FailedPasswordAttemptCount = failedPasswordAttemptCount;
    }

    public String getLastLockoutDate() {
        return LastLockoutDate;
    }

    public void setLastLockoutDate(String lastLockoutDate) {
        LastLockoutDate = lastLockoutDate;
    }

    public String getLastPasswordChangedDate() {
        return LastPasswordChangedDate;
    }

    public void setLastPasswordChangedDate(String lastPasswordChangedDate) {
        LastPasswordChangedDate = lastPasswordChangedDate;
    }

    public String getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public int getIsLockedOut() {
        return IsLockedOut;
    }

    public void setIsLockedOut(int isLockedOut) {
        IsLockedOut = isLockedOut;
    }

    public int getIsApproved() {
        return IsApproved;
    }

    public void setIsApproved(int isApproved) {
        IsApproved = isApproved;
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
}
