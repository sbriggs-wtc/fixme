package routergroupid;
import java.net.*;
import java.io.*;
public class Main{
    public static void main( String[] args ){
        try{
            Thread brokerThread = new Thread(new SuperHandler(new ServerSocket(5000)));brokerThread.start();
            Thread marketThread = new Thread(new SuperHandler(new ServerSocket(5001)));marketThread.start();
        } catch(IOException e){System.err.println(e.getMessage());}
    }
}
class SuperHandler implements Runnable{
    ServerSocket serverSocket;
    SuperHandler(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        System.out.println("Listener started on: " + serverSocket.getLocalPort());
    }
    @Override
    public void run() {
        try{
            while(true){        
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new SubHandler(socket));
                thread.start();
            }
        } catch (IOException e){System.err.println(e.getMessage());}
    }
}
class SubHandler implements Runnable{
    private Socket socket;
    private String uniqueID;
    SubHandler(Socket socket){
        this.socket = socket;
        this.uniqueID = socket.toString();
    }
    @Override
    public void run(){
        System.out.println("\nClient connected:\n" + uniqueID + "\n");
        try{
            while(true){

                //READING
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputStreamLine = bufferedReader.readLine();
                if(inputStreamLine == null){System.out.println("\nClient disconnected:\n" + uniqueID + "\n");break;} //this breaks the loop

                // WRITING
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());    
                System.out.println("\n" + uniqueID + "\n" + inputStreamLine + "\n");
                switch(inputStreamLine){
                    case "1":   printWriter.println("You pressed 1\nPress 1 to view available markets");printWriter.flush(); break;
                    case "2":   printWriter.println("You pressed 2\nPress 1 to view available markets");printWriter.flush(); break;
                    case "3":   printWriter.println("You pressed 3\nPress 1 to view available markets");printWriter.flush(); break;
                    default:    printWriter.println("Press 1 to view available markets");printWriter.flush(); break;
                }
            }
        } catch(IOException e){System.err.println(e.getMessage());}
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
