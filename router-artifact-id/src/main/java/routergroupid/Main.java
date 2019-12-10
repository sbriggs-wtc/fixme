package routergroupid;
import java.net.*;
import java.io.*;
/* import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.*; //Future
import java.nio.channels.*; */

public class Main 
{
    public static void main( String[] args )
    {
        System.out.println( "Router: Hello World!" );
        try{
          
            ServerSocket brokerListener = new ServerSocket(5000); //there is some kind of weird automatic loop thing going on over here
            Socket brokerSocket = brokerListener.accept();
            System.out.println("broker client connected");
            new Thread(new BrokerRunnable(brokerSocket)).start();
           
            ServerSocket marketListener = new ServerSocket(5001);
            Socket marketSocket = marketListener.accept();
            System.out.println("market client connected");
            while(true){
/*                 BufferedReader brokerBufferedReader = new BufferedReader(new InputStreamReader(brokerSocket.getInputStream()));
                String brokerInputStreamLine = brokerBufferedReader.readLine();
                System.out.println("broker: " + brokerInputStreamLine);
                PrintWriter brokerPrintWriter = new PrintWriter(brokerSocket.getOutputStream());
                brokerPrintWriter.println("I am a router");
                brokerPrintWriter.flush(); */

                BufferedReader marketBufferedReader = new BufferedReader(new InputStreamReader(marketSocket.getInputStream()));
                String marketInputStreamLine = marketBufferedReader.readLine();
                System.out.println("market: " + marketInputStreamLine);
                PrintWriter marketPrintWriter = new PrintWriter(marketSocket.getOutputStream());
                marketPrintWriter.println("I am a router");
                marketPrintWriter.flush();
            }
        } catch (/* IO */Exception e){
        System.err.println(e.getMessage());
        }
    }
}

final class BrokerRunnable implements Runnable{
    private Socket socket;
    BrokerRunnable(Socket socket){
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
        } catch (/* IO */Exception e){
            System.err.println(e.getMessage());
        }
    }
}
