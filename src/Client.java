import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try(
            Socket client = new Socket("localHost", 5000);
        ) {
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            DataInputStream input = new DataInputStream(client.getInputStream());

            output.writeUTF("echo Hi server");
            output.flush();

            String response = input.readUTF();

            System.out.println("Resposta: " + response);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
