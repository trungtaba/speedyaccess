package speedaccess;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidateDemo
{
    private final Pattern pattern;
    private Matcher matcher;

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

    public UserNameValidateDemo()
    {
            pattern = Pattern.compile(USERNAME_PATTERN);
            System.out.println("result:"+validate("mkyong123456789_-"));
    }

    public boolean validate(final String username){

            matcher = pattern.matcher(username);
            return matcher.matches();

    }
    public static void main(String[] args) 
    {
        UserNameValidateDemo abc=new UserNameValidateDemo();
    }
}
