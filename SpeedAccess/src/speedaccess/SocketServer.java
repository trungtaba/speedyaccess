package speedaccess;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable
{
    private int Port;
    private ServerSocket server;
    private boolean isClose;

    public SocketServer(int Port) 
    {
        this.Port = Port;
    }
    
    public synchronized boolean isClose()
    {
        return this.isClose;
    }
    
    public synchronized void stop()
    {
        try 
        {
            this.server.close();
        } 
        catch (IOException ex) 
        {
            throw new RuntimeException("Error closing server..");
        }
    }
    
    private void openServer()
    {
        try
        {
            this.server=new ServerSocket(Port);
        }
        catch (IOException ex)
        {
            if(isClose())
                throw new RuntimeException("cannot open port "+this.Port);
        }
    }
    
    @Override
    public void run()
    {
        openServer();
        
        while(!isClose())
        {
            Socket client=null;
            InetAddress inetAddress;
            try 
            {
                client=this.server.accept();
                inetAddress=client.getInetAddress();
            } 
            catch (IOException ex)
            {
                if(isClose())
                {
                    System.out.println("Server stoppted");
                }
                else
                {
                    throw new RuntimeException("Error accepting client connection", ex);
                }
            }
            
            new Thread(new HandleClient(client)).start();
        }
    }
}
