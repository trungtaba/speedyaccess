//package Controller;
//
//import static MyCode.MyCodeFunction.LIST_ACTION_BY_USER;
//import Table.Action;
//import XML.ParseXML.MainParse;
//import java.util.ArrayList;
//
//public class ActionTest
//{
//    public static void main(String[] args) 
//    {
//        ActionContronller actController=new ActionContronller();
//        MainParse parse=new MainParse();
//        
////        String string=  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
////                        "<Application id=\"1\" code=\""+DELETE_ACT+"\">\n" +
////                        "    <action>        \n" +
////                        "        <ID>2</ID>\n" +
////                        "        <ActName>act2</ActName>\n" +
////                        "        <ActDes>act2</ActDes>\n" +
////                        "    </action>   \n" +
////                        "</Application>\n" ;
////        Action act=parse.Receive_Action(string);
////        
////        int function=parse.FindFunction(string);
////        if(function==DELETE_ACT)
////        {
////            int result=actController.DeleteAct(act.getActName(), act.getAppID());
////            parse.Send_Action(1, function, act, result);
////        }
//        //List action by app
////        String string=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
////                        "<Application id=\"1\" code=\""+LIST_ACTION_BY_APP+"\">\n" +
////                        "    <ID>1</ID>   \n" +
////                        "</Application>";
////        int function=parse.FindFunction(string);
////        ArrayList<Integer> arr=parse.Receive_List(string);
////        if(function==LIST_ACTION_BY_APP)
////        {
////            ArrayList<Action> list=actController.ListActionByApp(arr.get(0));
////            parse.Send_ListAction(arr.get(0), arr.get(1), function, list);
////        }
////        
////        //List action by role
////        String string=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
////                        "<Application id=\"1\" code=\""+LIST_ACTION_BY_ROLE+"\">\n" +
////                        "    <ID>1</ID>   \n" +
////                        "</Application>";
////        int function=parse.FindFunction(string);
////        ArrayList<Integer> arr=parse.Receive_List(string);
////        if(function==LIST_ACTION_BY_ROLE)
////        {
////            ArrayList<Action> list=actController.ListActionByRole(arr.get(1));
////            parse.Send_ListAction(arr.get(0), arr.get(1), function, list);
////        }
//        
//         //List action by user
//        String string=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                        "<Application id=\"1\" code=\""+LIST_ACTION_BY_USER+"\">\n" +
//                        "    <ID>2</ID>   \n" +
//                        "</Application>";
//        int function=parse.FindFunction(string);
//        ArrayList<Integer> arr=parse.Receive_List(string);
//        if(function==LIST_ACTION_BY_USER)
//        {
//            ArrayList<Action> list=actController.ListActionByUser(arr.get(1));
//            parse.Send_ListAction(arr.get(0), arr.get(1), function, list);
//        }
//        
//        //receive_action
////        {
////            String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
////                            "<Application id=\"1\" code=\"0\">\n" +
////                            "    <action>\n" +
////                            "        <ID>1</ID>\n" +
////                            "        <ActName>action1</ActName>\n" +
////                            "        <ActDes>des1</ActDes>\n" +
////                            "    </action>   \n" +
////                            "</Application>";
////            System.out.println(query);
////            int function=parse.FindFunction(query);
////            System.out.println("Function="+function);
////            if(function==0)
////            {
////                action=parse.Receive_Action(query);
////                System.out.println("");
////                int result=actController.CreateAct(action);
////                System.out.println(result);
////            }
////        }
//        
//        //Send_action
////        {
////            parse.Send_Action(action, 75);
////        }
//        
//        //List action
////        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
////                        "<Application id=\"1\" code=\"6\">\n" +
////                        "    <ID>1</ID>   \n" +
////                        "</Application>";
////        int function=parse.FindFunction(query);
////        if(function==4)
////        {
////            ArrayList<Integer> a=parse.Receive_List(query);
////            ArrayList<Action> array=actController.ListActionByApp(a.get(0));
////            parse.Send_ListAction(a.get(0),a.get(1), function, array);
////        }
////        else if(function==5)
////        {
////            ArrayList<Integer> a=parse.Receive_List(query);
////            ArrayList<Action> array=actController.ListActionByRole(a.get(1));
////            parse.Send_ListAction(a.get(0),a.get(1), function, array);
////        }
////        else if(function==6)
////        {
////            ArrayList<Integer> a=parse.Receive_List(query);
////            ArrayList<Action> array=actController.ListActionByUser(a.get(1));
////            parse.Send_ListAction(a.get(0),a.get(1), function, array);
////        }
//
//        //add role action
//        
////        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
////                        "<Application id=\"1\" code=\"35\">\n" +
////
////                        "    <RoleAction>\n" +
////                        "        <RoleID>2</RoleID>\n" +
////                        "        <ActionID>3</ActionID>\n" +
////                        "    </RoleAction>    \n" +
////                        "      \n" +
////                        "    <RoleAction>\n" +
////                        "        <RoleID>2</RoleID>\n" +
////                        "        <ActionID>4</ActionID>\n" +
////                        "    </RoleAction>\n" +
////                        "    \n" +
////                        "</Application>";
////        
////        int function=parse.FindFunction(query);
////        ArrayList<Integer> result=new ArrayList<>();
////        if(function==DELETE_ROLE_ACT)
////        {
////            ArrayList<Role_action> arr=parse.Receive_RoleAction(query);
////            for(Role_action roact:arr)
////            {
////                System.out.println(roact.toString());
////                result.add(actController.DeleteRoleAction(roact.getRoleID(), roact.getActID()));
////            }
////            parse.Send_RoleAction(1, DELETE_ROLE_ACT, arr, result);
////        }
////        else 
////            System.out.println("nothing");
//        
//        //add user action
////                String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
////                                "<Application id=\"1\" code=\""+DELETE_USER_ACT+"\">\n" +
////                                "    <UserAction>\n" +
////                                "        <UserID>1</UserID>\n" +
////                                "        <ActionID>1</ActionID>\n" +
////                                "    </UserAction>     \n" +
////                                "     \n" +
////                                "    <UserAction>\n" +
////                                "        <UserID>1</UserID>\n" +
////                                "        <ActionID>2</ActionID>\n" +
////                                "    </UserAction> \n" +
////                                "    \n" +
////                                "</Application>\n";
////        
////        int function=parse.FindFunction(query);
////        ArrayList<Integer> result=new ArrayList<>();
////        if(function==DELETE_USER_ACT)
////        {
////            ArrayList<User_action> arr=parse.Receive_UserAction(query);
////            for(User_action userAction:arr)
////            {
////                System.out.println(userAction.toString());
////                result.add(actController.DeleteUserAction(userAction.getUserID(), userAction.getActID()));
////            }
////            parse.Send_UserAction(1, function, arr, result);
////        }
//        
////        String query=   "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
////                        "<Application id=\"1\" code=\""+CREATE_USER_ROLE+"\">\n" +
////                        "    \n" +
////                        "    <UserRole>\n" +
////                        "        <UserID>1</UserID>\n" +
////                        "        <RoleID>2</RoleID>\n" +
////                        "    </UserRole>     \n" +
////                        "     \n" +
////                        "    <UserRole>\n" +
////                        "        <UserID>1</UserID>\n" +
////                        "        <RoleID>3</RoleID>\n" +
////                        "    </UserRole> \n" +
////                        "    \n" +
////                        "</Application>";
////        int function=parse.FindFunction(query);
////        ArrayList<Integer> result=new ArrayList<>();
////        if(function==CREATE_USER_ROLE)
////        {
////            ArrayList<User_role> arr=parse.Receive_UserRole(query);
////            for(User_role userRole:arr)
////            {
////                System.out.println(userRole.toString());
////                result.add(userController.AddUserRole(userRole.getUserID(), userRole.getRoleID()));
////            }
////            parse.Send_UserRole(1, function, arr, result);
////        }
//    }
//}
