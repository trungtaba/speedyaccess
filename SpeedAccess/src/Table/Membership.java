package Table;

import java.util.Objects;

public class Membership
{
    private int ID;
    private int UserID;
    private int AppID;
    private String PassWord;
    private String PassWordFormat;
    private String Email;
    private String PassWordQuestion;
    private String PassWordAnswer;
    private int IsApproved;
    private int IsLockOut;
    private String CreateDate;
    private String LastLoginDate;
    private String LastPasswordChangedDate;
    private String LastLockoutDate;
    private int FailedPasswordAttemptCount;
    private String FailedPasswordAttemptWindowStart;
    private int FailedPasswordAnswerAttemptCount;
    private String FailedPasswordAnswerAttemptWindowsStart;
    private int MaxFailedPassword;

    public Membership(int ID,int UserID, int AppID, String PassWord, String PassWordFormat,
            String Email, String PassWordQuestion, String PassWordAnswer, int IsApproved, 
            int IsLockOut, String CreateDate, String LastLoginDate, String LastPasswordChangedDate,
            String LastLockoutDate, int FailedPasswordAttemptCount, String FailedPasswordAttemptWindowStart,
            int FailedPasswordAnswerAttemptCount, String FailedPasswordAnswerAttemptWindowsStart,int MaxFailedPassword) {
        this.ID=ID;
        this.UserID = UserID;
        this.AppID = AppID;
        this.PassWord = PassWord;
        this.PassWordFormat = PassWordFormat;
        this.Email = Email;
        this.PassWordQuestion = PassWordQuestion;
        this.PassWordAnswer = PassWordAnswer;
        this.IsApproved = IsApproved;
        this.IsLockOut = IsLockOut;
        this.CreateDate = CreateDate;
        this.LastLoginDate = LastLoginDate;
        this.LastPasswordChangedDate = LastPasswordChangedDate;
        this.LastLockoutDate = LastLockoutDate;
        this.FailedPasswordAttemptCount = FailedPasswordAttemptCount;
        this.FailedPasswordAttemptWindowStart = FailedPasswordAttemptWindowStart;
        this.FailedPasswordAnswerAttemptCount = FailedPasswordAnswerAttemptCount;
        this.FailedPasswordAnswerAttemptWindowsStart = FailedPasswordAnswerAttemptWindowsStart;
        this.MaxFailedPassword=MaxFailedPassword;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.ID;
        hash = 67 * hash + Objects.hashCode(this.UserID);
        hash = 67 * hash + Objects.hashCode(this.AppID);
        hash = 67 * hash + Objects.hashCode(this.PassWord);
        hash = 67 * hash + Objects.hashCode(this.PassWordFormat);
        hash = 67 * hash + Objects.hashCode(this.Email);
        hash = 67 * hash + Objects.hashCode(this.PassWordAnswer);
        hash = 67 * hash + this.IsApproved;
        hash = 67 * hash + this.IsLockOut;
        hash = 67 * hash + Objects.hashCode(this.CreateDate);
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
        final Membership other = (Membership) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.UserID, other.UserID)) {
            return false;
        }
        if (!Objects.equals(this.AppID, other.AppID)) {
            return false;
        }
        if (!Objects.equals(this.PassWord, other.PassWord)) {
            return false;
        }
        if (!Objects.equals(this.PassWordFormat, other.PassWordFormat)) {
            return false;
        }
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        if (!Objects.equals(this.PassWordQuestion, other.PassWordQuestion)) {
            return false;
        }
        if (!Objects.equals(this.PassWordAnswer, other.PassWordAnswer)) {
            return false;
        }
        if (this.IsApproved != other.IsApproved) {
            return false;
        }
        if (this.IsLockOut != other.IsLockOut) {
            return false;
        }
        if (this.MaxFailedPassword != other.MaxFailedPassword) {
            return false;
        }
        return Objects.equals(this.CreateDate, other.CreateDate);
    }

    public int getMaxFailedPassword() {
        return MaxFailedPassword;
    }

    public void setMaxFailedPassword(int MaxFailedPassword) {
        this.MaxFailedPassword = MaxFailedPassword;
    }
    
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
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

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public String getPassWordFormat() {
        return PassWordFormat;
    }

    public void setPassWordFormat(String PassWordFormat) {
        this.PassWordFormat = PassWordFormat;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassWordQuestion() {
        return PassWordQuestion;
    }

    public void setPassWordQuestion(String PassWordQuestion) {
        this.PassWordQuestion = PassWordQuestion;
    }

    public String getPassWordAnswer() {
        return PassWordAnswer;
    }

    public void setPassWordAnswer(String PassWordAnswer) {
        this.PassWordAnswer = PassWordAnswer;
    }

    public int getIsApproved() {
        return IsApproved;
    }

    public void setIsApproved(int IsApproved) {
        this.IsApproved = IsApproved;
    }

    public int getIsLockOut() {
        return IsLockOut;
    }

    public void setIsLockOut(int IsLockOut) {
        this.IsLockOut = IsLockOut;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(String LastLoginDate) {
        this.LastLoginDate = LastLoginDate;
    }

    public String getLastPasswordChangedDate() {
        return LastPasswordChangedDate;
    }

    public void setLastPasswordChangedDate(String LastPasswordChangedDate) {
        this.LastPasswordChangedDate = LastPasswordChangedDate;
    }

    public String getLastLockoutDate() {
        return LastLockoutDate;
    }

    public void setLastLockoutDate(String LastLockoutDate) {
        this.LastLockoutDate = LastLockoutDate;
    }

    public int getFailedPasswordAttemptCount() {
        return FailedPasswordAttemptCount;
    }

    public void setFailedPasswordAttemptCount(int FailedPasswordAttemptCount) {
        this.FailedPasswordAttemptCount = FailedPasswordAttemptCount;
    }

    public String getFailedPasswordAttemptWindowStart() {
        return FailedPasswordAttemptWindowStart;
    }

    public void setFailedPasswordAttemptWindowStart(String FailedPasswordAttemptWindowStart) {
        this.FailedPasswordAttemptWindowStart = FailedPasswordAttemptWindowStart;
    }

    public int getFailedPasswordAnswerAttemptCount() {
        return FailedPasswordAnswerAttemptCount;
    }

    public void setFailedPasswordAnswerAttemptCount(int FailedPasswordAnswerAttemptCount) {
        this.FailedPasswordAnswerAttemptCount = FailedPasswordAnswerAttemptCount;
    }

    public String getFailedPasswordAnswerAttemptWindowsStart() {
        return FailedPasswordAnswerAttemptWindowsStart;
    }

    public void setFailedPasswordAnswerAttemptWindowsStart(String FailedPasswordAnswerAttemptWindowsStart) {
        this.FailedPasswordAnswerAttemptWindowsStart = FailedPasswordAnswerAttemptWindowsStart;
    }    
}
