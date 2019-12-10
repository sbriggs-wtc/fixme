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
            Socket s = new Socket("localhost", 5000); //connects to the specified port number on the named host
            OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                String brokerStr = scanner.nextLine();
                pw.println(brokerStr);
                pw.flush();
                InputStream is = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String str = br.readLine();
                System.out.println("server: " + str);
            }
        } catch (IOException e){
        System.err.println(e.getMessage());
        }
    }
}
