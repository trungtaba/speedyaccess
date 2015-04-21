
package speedaccess;

import Controller.MainController;
import XML.ParseXML.MainParse;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HandleClient implements Runnable
{

    private MainParse parse;
    private MainController controller;
    private Socket clientSocket = null;
    private DataInputStream input;
    //private DataOutputStream output;

    public HandleClient(Socket clientSocket) 
    {
        this.clientSocket = clientSocket;
        parse=new MainParse();
        controller=new MainController();
        
        try 
        {           
            input=new DataInputStream(clientSocket.getInputStream());
            //output=new DataOutputStream(clientSocket.getOutputStream());
        } 
        catch (IOException ex) 
        {
            System.out.println("Error:"+ex);
        }
    }
    
    boolean isStop = false;

    @Override
    public void run() 
    {
        try 
        {
            while(!isStop)
            {
                String a=input.readUTF();
                //System.out.println(a);
                if(a.equalsIgnoreCase("close"))
                {
                    stop();
                }
                else
                {
                    //int function=parse.FindFunction(a);
                   // controller.action(function,a);
                    controller.action(a);
                }
                //System.out.println(a);
            }
        } 
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void stop()
    {
        try 
        {
            this.clientSocket.close();
            this.isStop=true;
            //Thread.currentThread().stop();
        } 
        catch (IOException ex) 
        {
            throw new RuntimeException("Error:can't close connection",ex);
        }
    }
}
