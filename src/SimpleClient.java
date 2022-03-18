import java.io.*;
        import java.net.Socket;
        import java.util.Scanner;

public class SimpleClient {
    private String ip;
    private int port;

    public SimpleClient(String ip, int port){
        this.ip =ip;
        this.port = port;
    }

    public void startClient() throws IOException {
        Socket socket = new Socket(ip, port);
        Scanner scanner = new Scanner(System.in);
        String message;

        Scanner socketIn = new Scanner(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        try
        {
            // open a socket connection



            while(true) {
                System.out.println("Inserisci stringa da inviare al server: ");
                String s = scanner.nextLine();

                socketOut.println(s);
                socketOut.flush();
                String number = socketIn.nextLine();
                System.out.println("Ricevuto dal server:");
                System.out.println("Il numero di caratteri è "+ number);
            }

        }
        catch(Exception e) { System.out.println(e.getMessage());}
        finally {
            scanner.close();
            socketOut.close();
            socketIn.close();
            socket.close();

        }

    }
    public static void main(String args[])
    {
        SimpleClient client = new SimpleClient("localhost", 22222);
        try {
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
