import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        PrintStream out;
        BufferedReader in;
        Socket client;
        try {
            client = new Socket("localhost", 12345);

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);

            String msg = in.readLine();
            System.out.println("Server: " + msg);

            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            out.println(name);

            String nmsg = in.readLine();
            System.out.println(nmsg);
            in.close();
            out.close();
            client.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
