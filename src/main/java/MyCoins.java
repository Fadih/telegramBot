import java.util.HashMap;
import java.util.Map;

public class MyCoins {

    public static double Ripple =  658.50005 ;
    public static double Bitcoin = 0;
    public static double Iota = 20;
    public static double Streamr = 2062.8;
    public static double YOYOW = 376.8;
    public static double POE = 1879;
    public static double TRON = 4296;
    public static double Stellar_Lumens = 1111.887;
    public static double Status = 1553;
    public static double Verge = 3831;
    public static double Time_new_Bank = 1772;
    public static double Bytecoin = 44189;
    public static double BitShares = 498.75;
    public static double DigiByte = 10732;
    public static double Dogecoin = 19950;
    public static double Siacoin = 2994.5;
    public static double FunFair = 5994;
    public static double Cardano = 299.5;
    public static double RedCoin = 17400;



    public static double myinvesting_usd = 4000;


    public static double getMyinvesting_usd() {
        return myinvesting_usd;
    }

public static  Map<String, Double> getMyCoinsMAp(){
     Map<String,Double> mycoins = new HashMap();


    mycoins.put("Ripple",Ripple);
    mycoins.put("Bitcoin",Bitcoin);
    mycoins.put("IOTA",Iota);
    mycoins.put("Streamr DATAcoin",Streamr);
    mycoins.put("YOYOW",YOYOW);
    mycoins.put("Po.et",POE);
    mycoins.put("TRON",TRON);
    mycoins.put("Stellar",Stellar_Lumens);
    mycoins.put("Status",Status);
    mycoins.put("Verge",Verge);
    mycoins.put("Time New Bank",Time_new_Bank);
    mycoins.put("Bytecoin",Bytecoin);
    mycoins.put("BitShares",BitShares);
    mycoins.put("DigiByte",DigiByte);
    mycoins.put("Dogecoin",Dogecoin);
    mycoins.put("Siacoin",Siacoin);
    mycoins.put("FunFair",FunFair);
    mycoins.put("Cardano",Cardano);
    mycoins.put("ReddCoin",RedCoin);


    return mycoins;

}





}
