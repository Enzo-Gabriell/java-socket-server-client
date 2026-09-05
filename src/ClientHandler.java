import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientHandler implements Runnable{

    private static final int CLIENT_TIMEOUT = 10000;

    private final Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            client.setSoTimeout(CLIENT_TIMEOUT);

            try (
                    Socket socket = this.client;
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            ) {

                while (true) {

                    String request = input.readUTF();

                    if (request.equals("quit")) {
                        break;
                    }

                    if (request.equals("echo") || request.equals("echo ")) {
                        output.writeUTF("ERROR: echo requires a message");
                        output.flush();
                        continue;
                    }

                    if (request.startsWith("echo ")) {
                        String message = request.substring(5);

                        output.writeUTF(message);
                        output.flush();

                        continue;
                    }

                    output.writeUTF("ERROR: unknown command");
                    output.flush();
                }
            }

        } catch (EOFException ex) {
            System.out.println("Client disconnected.");
        } catch (SocketTimeoutException ex) {
            System.out.println("Client timed out.");
        } catch (IOException ex) {
            System.err.println("Communication error: " + ex.getMessage());
        }
    }
}
