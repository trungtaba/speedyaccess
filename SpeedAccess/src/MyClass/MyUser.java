package MyClass;

public class MyUser 
{
        private int ID;
        private int AppID;
        private String UserName;
        private String PassWord;
        private String PassWordFormat;
        private String Email;
        private String PassWordQuestion;
        private String PassWordAnswer;
        private int IsApproved;
        private int MaxFailedPassword;

    public MyUser(int ID, int AppID, String UserName, String PassWord, 
            String PassWordFormat, String Email, String PassWordQuestion,
            String PassWordAnswer, int IsApproved,int MaxFailedPassword) {
        this.ID = ID;
        this.AppID = AppID;
        this.UserName = UserName;
        this.PassWord = PassWord;
        this.PassWordFormat = PassWordFormat;
        this.Email = Email;
        this.PassWordQuestion = PassWordQuestion;
        this.PassWordAnswer = PassWordAnswer;
        this.IsApproved = IsApproved;
        this.MaxFailedPassword=MaxFailedPassword;
    }

    public int getMaxFailedPassword() {
        return MaxFailedPassword;
    }

    public void setMaxFailedPassword(int MaxFailedPassword) {
        this.MaxFailedPassword = MaxFailedPassword;
    }

    public int getAppID() {
        return AppID;
    }

    public void setAppID(int AppID) {
        this.AppID = AppID;
    }

    public String getPassWordFormat() {
        return PassWordFormat;
    }

    public void setPassWordFormat(String PassWordFormat) {
        this.PassWordFormat = PassWordFormat;
    }

    public int getIsApproved() {
        return IsApproved;
    }

    public void setIsApproved(int IsApproved) {
        this.IsApproved = IsApproved;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
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
        
        
}
