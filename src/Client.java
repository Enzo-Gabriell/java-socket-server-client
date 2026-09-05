import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try(
            Socket client = new Socket("localhost", 5000);
        ) {
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            DataInputStream input = new DataInputStream(client.getInputStream());

            while(true) {
                String message = scanner.nextLine();
                output.writeUTF(message);
                output.flush();

                if (message.equals("quit")) {
                    break;
                }

                String response;
                try {
                    response = input.readUTF();
                } catch (EOFException ex) {
                    System.err.println("Server disconnected.");
                    break;
                }

                System.out.println("Response: " + response);
            }
        } catch (ConnectException ex) {
            System.err.println("Could not connect to server.");
        } catch (IOException ex) {
            System.err.println("Communication error: " + ex.getMessage());
        }
    }
}
