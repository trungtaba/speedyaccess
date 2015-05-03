package SpeedyAccessv2;

import Controller.MainController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;

public class MyHttpServer
{
    public static void main(String[] args) throws Exception
    {

        HttpServer server = HttpServer .create(new InetSocketAddress(8000), 0);
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
            MainController mainController=MainController.getInstance();

            String[] res = t.getRequestURI().toString().split("/");

            String response = URLDecoder.decode( res[1],"utf-8");
            String result=mainController.action(response);
            //String response = "This is the response";
            t.sendResponseHeaders(200, result.length());

            //os.write(response.getBytes());
            try (OutputStream os = t.getResponseBody()) {
                //os.write(response.getBytes());
                os.write(result.getBytes());
            }
        }
    }
}
