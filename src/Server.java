import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(5000)) {

            System.out.println("Server initialized successfully.");

            while (true) {
                Socket client = server.accept();

                ClientHandler handler = new ClientHandler(client);

                Thread thread = new Thread(handler);
                thread.start();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}