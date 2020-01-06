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
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("I am a broker"); printWriter.flush();

            while(true){

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputStreamLine = bufferedReader.readLine();
                if(inputStreamLine == null){System.out.println("\nServer disconnected\n");break;}
                System.out.println("\nServer\n" + inputStreamLine + "\n");
                printWriter = new PrintWriter(socket.getOutputStream());
                System.out.println("Press 1 for NYSE instruments\nPress 2 for LSE instruments\nPress 3 for JSE instruments");
                String brokerInput = scanner.nextLine();
                printWriter.println(brokerInput); printWriter.flush();
            }
        } catch (IOException e){System.err.println(e.getMessage());}
    }
}

