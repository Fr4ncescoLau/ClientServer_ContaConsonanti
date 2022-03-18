import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleServer{

    private int port;
    private ServerSocket serverSocket;
    private final char[] vocali = {'a','e','i','o','u','à','é','è','ì','ò','ù'};

    public SimpleServer(int port){
       this.port= port;
    }


    public void startServer() throws IOException{

        serverSocket= new ServerSocket(port);
        System.out.println("Server socket ready");
        Socket socket =  serverSocket.accept();
        System.out.println("Received connection by "+socket.getInetAddress());

        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream());


        while(true){
            String line = in.nextLine();
            if(line.equals("quit")){
                break;
            }else {
                int cont= line.length();
                for (int i = 0; i < line.length(); i++) {
                    for(int j=0;j< vocali.length;j++) {
                        if (line.charAt(i) == vocali[j]) {
                            cont--;
                            break;
                        }
                    }
                }
                out.println(cont);
                in.close();
                out.close();
                socket.close();
                serverSocket.close();
            }
        }
    }


    public static void main(String[] args) {
        SimpleServer s = new SimpleServer(22222);
        try {
            s.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
