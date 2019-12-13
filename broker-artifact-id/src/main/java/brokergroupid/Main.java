package brokergroupid;
import java.net.*; //Socket
import java.io.*; //Exception
import java.util.Scanner;
public class Main{
    public static Scanner scanner;
    public static Socket socket;
    public static void main(String[] args){
        try{
            socket = new Socket("localhost", 5000);
            scanner = new Scanner(System.in);
            while(true){
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println(scanner.nextLine()); printWriter.flush();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputStreamLine = bufferedReader.readLine();
                if(inputStreamLine == null){
                    System.out.println("\nServer disconnected:\n");
                    break;
                }
                System.out.println("\nServer\n" + inputStreamLine + "\n");
            }
        } catch (IOException e){System.err.println(e.getMessage());}
    }
}
