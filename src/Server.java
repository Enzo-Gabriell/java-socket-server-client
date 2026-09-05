import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        ExecutorService executor =
                Executors.newFixedThreadPool(4);

        try (ServerSocket server = new ServerSocket(5000)) {

            System.out.println("Server initialized successfully.");

            while (true) {
                Socket client = server.accept();

                executor.submit(new ClientHandler(client));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}