package marketgroupid;
import java.net.*; //Socket
import java.io.*; //Exception
import java.util.Scanner;
public class Main
{   
    public static Scanner scanner;
    public static void main(String[] args){
        System.out.println( "Market: Hello World!" );
        try{
            Socket s = new Socket("localhost", 5001);
            OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                String marketStr = scanner.nextLine();
                pw.println(marketStr);
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
