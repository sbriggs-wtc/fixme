package routergroupid;
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Main 
{
    public static void main( String[] args )
    {
        System.out.println( "Router: Hello World!" );
        try{
          
            ServerSocket brokerListener = new ServerSocket(5000); //there is some kind of weird automatic loop thing going on over here
            Socket brokerSocket = brokerListener.accept();
            System.out.println( "broker client connected:" +
                                "\n ListenerLocalPort" + brokerListener.getLocalPort() +
                                "\n ListenerInetAddress" + brokerListener.getInetAddress() +
                                "\n SocketLocalPort" + brokerSocket.getLocalPort() +
                                "\n SocketInetAddress" + brokerSocket.getInetAddress() +
                                "\n SocketPort" + brokerSocket.getPort());

            Thread thread = new Thread(new ClientRunnable(brokerSocket));
            thread.start();
            //thread.join();
           
            ServerSocket marketListener = new ServerSocket(5001);
            Socket marketSocket = marketListener.accept();
            System.out.println("market client connected");
            new Thread(new ClientRunnable(marketSocket)).start();

        } catch (IOException e){
        System.err.println(e.getMessage());
        }
    }
}

final class ClientRunnable implements Runnable{
    private Socket socket;
    ClientRunnable(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try{
            while(true){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputStreamLine = bufferedReader.readLine();
                System.out.println("client: " + inputStreamLine);
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println("I am a router");
                printWriter.flush();
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
