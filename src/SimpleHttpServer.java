import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
public class SimpleHttpServer {
    public static void main(String[] args) throws IOException
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/", new MyHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server is running on port 8000");
    }
    static class MyHandler implements HttpHandler {
        @Override
        public void  handle(HttpExchange exchange) throws IOException
        {
            String response = "sup";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
