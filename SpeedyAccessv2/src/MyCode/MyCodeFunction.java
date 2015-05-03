package MyCode;
public interface MyCodeFunction
{
      int Key_Error=0;
//===================ACTION==================
      int CreateAct=1;//
      int DeleteAct=2;
      int UpdateAct=3;//
      int AddRoleAct=4;//
      int AddUserAct=5;
      int FindAction=6;//
      int ListActionByApp=7;//
      int ListActionByRole=8;
      int ListActionByUser=9;
      int DeleteRoleAct=10;//
      int DeleteUserAct=11;
    
//=====================APP==================
      int CreateApp=21;//
      int DeleteApp=22;//
      int UpdateApp=23;//
      int FindApp=24;//
      int CreateConfig=25;
      int UpdateConfig=26;
    
//=====================ROLE===================   
      int CreateRole=61;//
      int DeleteRole=62;
      int UpdateRole=63;//
      int FindRole=64;//
      int ListRoleByApp=65;//
      int ListRoleByAction=66;
      int ListRoleByUser=67;
    
//=====================USER===================
      int CreateUser=81;
      int DeleteUser=82;
      int CloseUser=83;
      int UpdateUserName=84;
      int UpdateEmail=85;
      int FindUser=86;
      int ListUserByApp=87;//
      int ListUserByRole=88;//
      int ListUserByAct=89;
      int DeleteUserRole=90;//
      int AddUserRole=91;//
      int ChangePasswordByUser=92;
      int ChangePasswordByAdmin=93;
      int SignIn=94;
      int LogOut=94;
      int CheckUserAct=94;
}
