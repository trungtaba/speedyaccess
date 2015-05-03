package MyCode;

public interface MyException 
{
    int ERROR=0;
    int SUCCESS=1;

    /*********************ACTION********************/
    int ACTION_NAME_NULL=10;
    int ACTION_NAME_DUPLICATE=11;
    int ACTION_REALLY_HAVE=12;
    int ACTION_NOT_HAVE=13;
    int ACTION_ROLE_REALLY_HAVE=14;
    int ACTION_USER_REALLY_HAVE=15;
    int ACTION_ROLE_NOT_HAVE=16;
    int ACTION_USER_NOT_HAVE=17;

  /*********************ROLE********************/
    int ROLE_NAME_NULL=30;
    int ROLE_NAME_DUPLICATE=31;
    int ROLE_REALLY_HAVE=32;
    int ROLE_NOT_HAVE=33;

  /*********************USER********************/
    int USER_NAME_NULL=60;
    int EMAIL_NULL=61;
    int USER_REALLY_HAVE=62;
    int USER_NOT_HAVE=63;
    int USER_NAME_NOT_VALIDATE=64;
    int USER_REALLY_CLOSE=65;
    int USER_NAME_NEW_NULL=66;
    int USER_NAME_NOT_CHANGE=67;
    int USER_NOT_APPROVED=68;
    int USER_IS_LOCKOUT=69;
    int USER_NAME_NEW_DUPLICATE=70;
    int USER_NOT_HAVE_PERMISSION=71;
    int EMAIL_NOT_CHANGE =72;
    int USER_ROLE_REALLY_HAVE=73;
    int USER_ROLE_NOT_HAVE=74;
    int NEW_PASS_NOT_VALIDATE=75;
    int PASSWORD_NOT_CORRECT=76;
    int OLD_NEW_PASS_EQUAL=77;
    int USER_REALLY_LOGIN=78;
    int USER_REALLY_LOGOUT=79;
    int PASSWORD_NOT_VALIDATE=80;

  /*********************APPLICATION********************/
    int MAX_FAILT_PASS_INCORRECT =100;
    int APP_NOT_HAVE=101;

}
