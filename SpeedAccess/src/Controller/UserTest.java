
package Controller;

import static MyCode.MyCodeFunction.*;

public class UserTest 
{
    public static void main(String[] args)
    {
        MainController mainController=new MainController();
        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Application id=\"1\" code=\""+CHANGE_PASS_BY_USER+"\">\n" +
                        "    <ChangPassword>\n" +
                        "        <UserName>trung</UserName>\n" +
                        "        <OldPassword>fsdf</OldPassword>\n" +
                        "        <NewPassword>justonlyyou153</NewPassword>\n" +
                        "    </ChangPassword>   \n" +
                        "</Application>";
        String result=mainController.action(query);
        System.out.println(result);
    }
}
