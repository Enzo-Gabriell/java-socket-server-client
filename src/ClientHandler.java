import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private final Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }


    @Override
    public void run() {

        try(
            Socket socket = this.client;
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {

            while (true) {

                String request = input.readUTF();

                if(request.equals("quit")) {
                    break;
                }

                if(request.startsWith("echo ")) {
                    String message = request.substring(5);

                    output.writeUTF(message);
                    output.flush();

                    continue;
                }

                output.writeUTF("ERROR: unknown command");
                output.flush();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
