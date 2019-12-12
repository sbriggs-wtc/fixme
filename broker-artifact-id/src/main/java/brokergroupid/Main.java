package brokergroupid;
import java.net.*; //Socket
import java.io.*; //Exception
import java.util.Scanner;
public class Main
{
    public static Scanner scanner;
    public static void main(String[] args){
        System.out.println( "Broker: Hello World!" );
        try{
            Socket socket = new Socket("localhost", 5000);
/*             OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os); */
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                String outString = scanner.nextLine();
                printWriter.println(outString);
                printWriter.flush();
/*                 InputStream is = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr); */
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputStreamLine = bufferedReader.readLine();
                //System.out.println("Server: " + inputStreamLine);
                System.out.println("\nMessage from:\nServer\nContent:\n" + inputStreamLine + "\n");
            }
        } catch (IOException e){
        System.err.println(e.getMessage());
        }
    }
}
