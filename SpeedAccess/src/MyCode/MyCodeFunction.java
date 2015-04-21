package MyCode;
public interface MyCodeFunction
{       
//===================ACTION==================
    public final int DELETE_ACT=01;
    public final int UPDATE_ACT=02;
    public final int FIND_ACT=03;
    public final int LIST_ACTION_BY_APP=04;
    public final int LIST_ACTION_BY_ROLE=05;
    public final int LIST_ACTION_BY_USER=06;
    public final int CREATE_ROLE_ACT=7; 
    public final int CREATE_USER_ACT=8;
    public final int DELETE_ROLE_ACT=9;
    public final int DELETE_USER_ACT=10;
    public final int CREATE_ACT=11;
    
//=====================APP==================
    public final int CREATE_APP=21;
    public final int DELETE_APP=22;
    public final int UPDATE_APP=23;
    public final int FIND_APP=24;
    public final int CHECK_SIGN_IN=25;
//    
////=====================MEM=================== 
//    public final int CREATE_MEM=41;
//    public final int DELETE_MEM=42;
//    public final int UPDATE_MEM=43;
//    public final int FIND_MEM=44;
//    public final int CHANGE_PASSWORD=45;
//    public final int LIST_MEM_BY_APP=46;
//    public final int CHANGE_PASSWORD_QUE=47;
    
//=====================ROLE===================   
    public final int CREATE_ROLE=61;
    public final int DELETE_ROLE=62;
    public final int UPDATE_ROLE=63;
    public final int FIND_ROLE=64;
    public final int LIST_ROLE_BY_APP=65;
    public final int LIST_ROLE_BY_USER=66;
    public final int LIST_ROLE_BY_ACT=67;
    
    
//=====================USER===================
    public final int CREATE_USER=81;
    public final int DELETE_USER=82;
    public final int UPDATE_USER=83;
    public final int FIND_USER=84;
    public final int LIST_USER_BY_APP=85;
    public final int LIST_USER_BY_ROLE=86;
    public final int LIST_USER_BY_ACT=87;
    public final int DELETE_USER_ROLE=88;
    public final int CREATE_USER_ROLE=89;
    public final int CHANGE_PASS_BY_USER=90;
    public final int CHANGE_PASS_BY_ADMIN=91;
    public final int CHANGE_PASS_QUE_BY_USER=92;
    public final int CHANGE_PASS_QUE_BY_ADMIN=93;
    
}
