import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerRunnable {
    public static void main(String[] args)  {
        ServerSocket server ;
        Socket client;
        try{
            server = new ServerSocket(12345);
            System.out.println("Server started...");

            while(true){
                client = server.accept();
                System.out.println("Client connected !!");

                ClientHandler handler = new ClientHandler(client);
                Thread thread = new Thread(handler);
                thread.start();
            }

        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
class ClientHandler implements Runnable{

    Socket client = new Socket();
    BufferedReader in ;
    PrintStream out;
    Scanner sc;


    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run(){
        try{
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream());
            out.println("Enter your name :");
            String name = in.readLine();
            if(name != null){
                out.println("Hii " +name+ " nice to meet you !!");
            }
            else {
                System.out.println("Enter a valid name :)");
            }

        }
        catch(IOException e){
            //ignore
        }

    }

}
