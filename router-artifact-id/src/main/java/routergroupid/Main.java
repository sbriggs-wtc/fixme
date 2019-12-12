package routergroupid;
import java.net.*;
import java.io.*;
import java.nio.channels.*;
public class Main{
    public static void main( String[] args ){
        try{
            Thread brokerThread = new Thread(new ListenerRunnable(new ServerSocket(5000)));
            brokerThread.start();
            Thread marketThread = new Thread(new ListenerRunnable(new ServerSocket(5001)));
            marketThread.start();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

class ListenerRunnable implements Runnable{
    
    ServerSocket serverSocket;

    ListenerRunnable(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        System.out.println("Listener started on: " + serverSocket.getLocalPort());
    }

    @Override
    public void run() {
        try{
            while(true){        
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ClientRunnable(socket));
                thread.start();
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}

class ClientRunnable implements Runnable{
    private Socket socket;
    private String outString;
    //private String uniqueID;
    ClientRunnable(Socket socket){
        this.socket = socket;
        //this.uniqueID = socket.toString();
    }
    @Override
    public void run(){
        System.out.println("\nClient connected:\n" + socket.toString() + "\n");
        try{
            while(true){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputStreamLine = bufferedReader.readLine();
                if(inputStreamLine == null){
                    System.out.println("\nClient disconnected:\n" + socket.toString() + "\n");
                    break;
                }
                System.out.println("\nMessage from: \n" + socket.toString() + "\nContent:\n" + inputStreamLine + "\n");
                if(inputStreamLine.equals("hello")){
                    outString = "hello to you too";
                } else {
                    outString = "say what?";
                }
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println(outString);
                printWriter.flush();
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}

/* 

System.out.println("Listener started on: \n" + 
serverSocket.getLocalPort() + "\n" +
serverSocket.getInetAddress() + "\n" +
serverSocket.getLocalSocketAddress() + "\n");

System.out.println("Client connected: \n" +
socket.getInetAddress() + "\n" +
socket.getLocalAddress() + "\n" +
socket.getLocalPort() + "\n" +
socket.getLocalSocketAddress() + "\n" +
socket.getPort() + "\n" +
socket.getRemoteSocketAddress() + "\n" +
socket.isBound() + "\n" +
socket.isClosed() + "\n" +
socket.isConnected() + "\n" +
socket.isInputShutdown() + "\n" +
socket.isOutputShutdown());
 */
