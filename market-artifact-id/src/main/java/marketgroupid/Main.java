package marketgroupid;
import java.net.*; //Socket
import java.io.*; //Exception
import java.util.Scanner;
public class Main{
    public static Scanner scanner;
    public static Socket socket;
    public static Market market;
    public static void main(String[] args){
        try{
            socket = new Socket("localhost", 5001);
            scanner = new Scanner(System.in);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("I am a market"); printWriter.flush();
            System.out.println("Press 1 if you are the NYSE\nPress 2 if you are the LSE\nPress 3 if you are the JSE");
            String marketInput;
            switch(scanner.nextLine()){
                case("1"): market = new Market("NYSE"); break;
                case("2"): market = new Market("LSE");break;
                case("3"): market = new Market("JSE");break;
            }

            while(true){

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputStreamLine = bufferedReader.readLine();
                if(inputStreamLine == null){System.out.println("\nServer disconnected:\n");break;}
                System.out.println("\nServer\n" + inputStreamLine + "\n");

                printWriter = new PrintWriter(socket.getOutputStream());
                System.out.println(market.getMarketName() + " instruments :\n" + 
                                                market.getInstrument1Name() + ": " + Integer.toString(market.getInstrument1Val()) +"\n" +
                                                market.getInstrument2Name() + ": " + Integer.toString(market.getInstrument1Val()) +"\n" +
                                                market.getInstrument2Name() + ": " + Integer.toString(market.getInstrument1Val()));
                marketInput = scanner.nextLine();
                printWriter.println(marketInput); printWriter.flush();
            }
        } catch (IOException e){System.err.println(e.getMessage());}
    }
}

class Market {
    private static String marketName;

    private static String instrument1Name;
    private static String instrument2Name;
    private static String instrument3Name;

    private static int instrument1Val;
    private static int instrument2Val;
    private static int instrument3Val;

    public Market(String string) {
        marketName = string;
        switch (string) {
            case "NYSE":
            instrument1Name = "Berkshire Hathaway";
            instrument2Name = "JP Morgan Chase & Co";
            instrument3Name = "Exxon Mobil";
            instrument1Val = 5000;
            instrument2Val = 5000;
            instrument3Val = 5000;
                break;
            case "LSE":
            instrument1Name = "Royal Dutch Shell";
            instrument2Name = "HSBC Holdings";
            instrument3Name = "BP";
            instrument1Val = 5000;
            instrument2Val = 5000;
            instrument3Val = 5000;
                break;
            case "JSE":
            instrument1Name = "Investec";
            instrument2Name = "Sasol";
            instrument3Name = "Naspers";
            instrument1Val = 5000;
            instrument2Val = 5000;
            instrument3Val = 5000;    
                break;
            default:
                break;
        }
    }
    public String getInstrument1Name(){return instrument1Name;}
    public String getInstrument2Name(){return instrument2Name;}
    public String getInstrument3Name(){return instrument3Name;}

    public int getInstrument1Val(){return instrument1Val;}
    public int getInstrument2Val(){return instrument2Val;}
    public int getInstrument3Val(){return instrument3Val;}

    public String getMarketName(){return marketName;}
}
