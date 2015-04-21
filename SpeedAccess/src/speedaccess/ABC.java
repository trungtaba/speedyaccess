package speedaccess;
import Controller.MainController;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.URLDecoder;

public class ABC {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        //server.createContext("/user", new MyHandler2());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler 
    {
        @Override
        public void handle(HttpExchange t) throws IOException 
        {
            MainController mainController=new MainController();
            
            String[] res = t.getRequestURI().toString().split("/");
            
            String response = URLDecoder.decode( res[1],"utf-8");
            //System.out.println(response);
            String result=mainController.action(response);
            System.out.println("result="+result);
            //String response = "This is the response";
            t.sendResponseHeaders(200, result.length());
            
            //os.write(response.getBytes());
            try (OutputStream os = t.getResponseBody()) {
                //os.write(response.getBytes());
                os.write(result.getBytes());
            }
        }
    }
//    static class MyHandler2 implements HttpHandler {
//        @Override
//        public void handle(HttpExchange t) throws IOException {
//            
//            String[] res = t.getRequestURI().toString().split("/");
//            
//            String response = URLDecoder.decode( res[2],"utf-8");
//            System.out.println(response);
//            t.sendResponseHeaders(200, response.length());
//            DataOutputStream out=new DataOutputStream(t.getResponseBody());
//            out.writeUTF("hello client");
//            out.close();
////            OutputStream os = t.getResponseBody();
////            out
////            os.write;
//            //os.close();
//        }
//    }

}
