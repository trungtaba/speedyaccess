package speedaccess;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SpeedAccess 
{
    public static void main(String[] args) 
    {
        SocketServer server = new SocketServer(9000);
        new Thread(server).start();
    }
    public String getCurrentTime()
    {
        long time=System.currentTimeMillis();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // tạo 1 đối tượng có định dạng thời gian yyyy-MM-dd HH:mm:ss
        Date date = new Date(time);	// lấy thời gian hệ thống
        String stringDate = dateFormat.format(date);//Định dạng thời gian theo trên
        return stringDate;
    }
}
