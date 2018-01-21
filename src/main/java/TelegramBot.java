import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

public class TelegramBot extends TelegramLongPollingBot  {

    final static Logger logger = Logger.getLogger(TelegramBot.class.getName());

    private final String botusername = "fadihusseinbot";
    private final String botToken = "";
    private final String coinmarketcapURL = "https://api.coinmarketcap.com/v1/ticker/?limit=0";
    ArrayList<Coin> coins = new ArrayList<Coin>();
    long chat_id = 0;
    Map<String ,Double> mycoinsmap ;



    public TelegramBot(){


        Runnable r = new Runnable() {
            public void run() {
                try {
                    while (true) {

                        if(chat_id!=0){
                            SendMessage message = null;

                            message = new SendMessage() // Create a message object object
                                    .setChatId(chat_id)
                                    .setText(getText("profit"));
                            logger.info("send by timer \n\n"+getText("profit"));
                            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                            // Create the keyboard (list of keyboard rows)
                            List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
                            // Create a keyboard row
                            KeyboardRow row = new KeyboardRow();
                            // Set each button, you can also use KeyboardButton objects if you need something else than text
                            row.add("showmycoins");
                            row.add("coinsvalue");

                            // Add the second row to the keyboard
                            keyboard.add(row);
                            // Set the keyboard to the markup
                            keyboardMarkup.setKeyboard(keyboard);
                            // Add it to the message
                            message.setReplyMarkup(keyboardMarkup);

                            execute(message);




                        }

                        Thread.sleep(1000*60*60*2);
                    }
                } catch (InterruptedException iex) {} catch (TelegramApiException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread thr = new Thread(r);
        thr.start();

    }

    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return botusername;
    }


    public String getBotToken() {
        // Return bot token from BotFather
        return botToken;
    }

    public void onUpdateReceived(Update update) {


        // Set variables
        String message_text = update.getMessage().getText();
          chat_id = update.getMessage().getChatId();




      SendMessage message = null;

        try {
            message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(getText(message_text));
            logger.info(getText(message_text));

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            // Create the keyboard (list of keyboard rows)
            List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
            // Create a keyboard row
            KeyboardRow row = new KeyboardRow();
            // Set each button, you can also use KeyboardButton objects if you need something else than text
            row.add("showmycoins");
            row.add("coinsvalue");

            // Add the second row to the keyboard
            keyboard.add(row);
            // Set the keyboard to the markup
            keyboardMarkup.setKeyboard(keyboard);
            // Add it to the message
            message.setReplyMarkup(keyboardMarkup);
        } catch (IOException e) {
            logger.error(e);
        }

        try {

                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
             logger.error(e);
            }



    }




    private String getText(String message_text) throws IOException {
        initcoinsList();

        String text = "";

        try {


            if (message_text.equalsIgnoreCase("profit")) {


                int counter = 1;
                double totalprofit = 0;
                for(Coin coin :coins)
                {
                    if(coin.getMytotal()!=0){
                        totalprofit+=coin.getMytotal()*coin.getPrice_usd();
                        DecimalFormat numFormat = new DecimalFormat("00.####");
                        String number  = numFormat.format(coin.getMytotal()*coin.getPrice_usd());
                        counter++;
                    }
                }
                DecimalFormat numFormat = new DecimalFormat("00.####");
                String number  = numFormat.format(totalprofit);
                text+="\nTotal_usd = "+number+"$\n" +"TotalProfit =  "+numFormat.format(totalprofit-MyCoins.getMyinvesting_usd())+"$";

                return text;

            }

            if (message_text.equalsIgnoreCase("coinsvalue")) {
                  double coinvalue= 0;
                DecimalFormat numFormat = new DecimalFormat("00.####");
                int counter = 1;
                double totalprice= 0;
                for(Coin coin :coins)
                {
                    if(coin.getMytotal()!=0){
                        coinvalue+=coin.getMytotal()*coin.getPrice_usd();
                        totalprice+=coin.getMytotal()*coin.getPrice_usd();
                        String number  = numFormat.format(coin.getMytotal()*coin.getPrice_usd());
                        text +=counter+") "+ coin.getName() + "  " +coin.getMytotal()+" "+number+"$";
                        text += "\n";
                        counter++;
                    }
                }

                String number  = numFormat.format(totalprice);

                text += "\n total price "+number+"$";
                return text;

            }

            if (message_text.equalsIgnoreCase("showmycoins")) {
               int counter = 1;
               double totalprofit = 0;
                for(Coin coin :coins)
                {
                    if(coin.getMytotal()!=0){
                        totalprofit+=coin.getMytotal()*coin.getPrice_usd();
                        DecimalFormat numFormat = new DecimalFormat("00.####");
                        String number  = numFormat.format(coin.getMytotal()*coin.getPrice_usd());
                        text +=counter+") "+ coin.getName() + " price = "+coin.getPrice_usd()+"$ "+coin.getPercent_change_1h()+"%h "+ coin.getPercent_change_24h()+"%24h"+" mytotalcoins = "+coin.getMytotal()+"\nMytotalcoin price = "+number+"$";
                        text += "\n\n";
                        counter++;
                    }
                }
                DecimalFormat numFormat = new DecimalFormat("00.####");
                String number  = numFormat.format(totalprofit);
                text+="\nTotal_usd = "+number+"$\n" +"TotalProfit =  "+numFormat.format(totalprofit-MyCoins.getMyinvesting_usd())+"$";

                return text;
            }


            if (message_text.startsWith("Getcoins(")) {
                String str = message_text.substring(message_text.indexOf("(") + 1, message_text.indexOf(")"));
                if(!isInteger(str))
                    return  "wrong format: \n" +
                            "Get(coinSymbol) \n" +
                            "Getcoins(limit) \n" +
                            "Gettotalprofit \n" +
                            "showmycoins\n" +
                            "coinsvalue";

                for(int i=0; i<Integer.parseInt(str);i++)
                {
                    Coin coin = coins.get(i);
                    text += (i+1)+") " +coin.getName() + " price = "+coin.getPrice_usd()+"$ "+coin.getPercent_change_1h()+"%h "+ coin.getPercent_change_24h()+"%24h"+" mytotalcoins = "+coin.getMytotal();
                    text += "\n";
                }

            return text;
            }


            if (message_text.startsWith("Get(")) {
                String str = message_text.substring(message_text.indexOf("(")+1,message_text.indexOf(")"));
                for(Coin coin : coins){
                    if(coin.getSymbol().equalsIgnoreCase(str)||coin.getName().equalsIgnoreCase(str)){
                        text = coin.getName() + " price = "+coin.getPrice_usd()+"$ "+coin.getPercent_change_1h()+"%h "+ coin.getPercent_change_24h()+"%24h"+" mytotalcoins = "+coin.getMytotal();
                         return text;
                    }

                }
                return "no coin found with this name or symbol";

            }
            else{
                text = "wrong format: \n" +
                        "Get(coinSymbol) \n" +
                        "Getcoins(limit) \n" +
                        "Gettotalprofit \n" +
                        "showmycoins\n" +
                        "coinsvalue";

                return text;
            }
        }catch (Exception e){

            return  "wrong format: \n" +
                    "Get(coinSymbol) \n" +
                    "Getcoins(limit) \n" +
                    "Gettotalprofit \n" +
                    "showmycoins\n" +
                    "coinsvalue";

        }

    }



    private String httpGet(String url) throws IOException {


        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        StringBuffer result = new StringBuffer();
        BufferedReader rd = new BufferedReader
                (new InputStreamReader(
                        response.getEntity().getContent()));

        String line = "";
        while (( line = rd.readLine()) != null) {

            result.append(line+"\n");
        }


        return result.toString();
    }


    private void initcoinsList(){

        Map<String ,Double> mycoinsmap = MyCoins.getMyCoinsMAp();
        String response = null;
        try {
            response = httpGet(coinmarketcapURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonarray = new JSONArray(response);
        coins.clear();
        for (int i = 0; i < jsonarray.length(); i++) {
            Coin coin = new Coin();

            try {
                JSONObject jsonobject = jsonarray.getJSONObject(i);



                coin.setId(jsonobject.getString("id"));
                coin.setName(jsonobject.getString("name"));
                if(mycoinsmap.containsKey(jsonobject.getString("name"))){
                    coin.setMytotal(mycoinsmap.get(jsonobject.getString("name")));
                }
                coin.setSymbol(jsonobject.getString("symbol"));




                    if (!jsonobject.get("rank").equals(null)) {
                        coin.setRank(Double.parseDouble(jsonobject.getString("rank")));
                    }
                    if (!jsonobject.get("price_usd").equals(null)) {
                        coin.setPrice_usd(Double.parseDouble(jsonobject.getString("price_usd")));
                    }
                    if (!jsonobject.get("price_btc").equals(null)) {
                        coin.setPrice_btc(Double.parseDouble(jsonobject.getString("price_btc")));
                    }
                    if (!jsonobject.get("24h_volume_usd").equals(null)) {
                        coin.setDay_volume_usd(Double.parseDouble(jsonobject.getString("24h_volume_usd")));
                    }
                    if (!jsonobject.get("market_cap_usd").equals(null)) {
                        coin.setMarket_cap_usd(Double.parseDouble(jsonobject.getString("market_cap_usd")));
                    }
                    if (!jsonobject.get("available_supply").equals(null)) {
                        coin.setAvailable_supply(Double.parseDouble(jsonobject.getString("available_supply")));
                    }
                    if (!jsonobject.get("total_supply").equals(null)) {
                        coin.setTotal_supply(Double.parseDouble(jsonobject.getString("total_supply")));
                    }
                    if (!jsonobject.get("max_supply").equals(null)) {
                        coin.setMax_suply(Double.parseDouble(jsonobject.getString("max_supply")));
                    }
                    if (!jsonobject.get("percent_change_1h").equals(null)) {
                        coin.setPercent_change_1h(Double.parseDouble(jsonobject.getString("percent_change_1h")));
                    }
                    if (!jsonobject.get("percent_change_24h").equals(null)) {
                        coin.setPercent_change_24h(Double.parseDouble(jsonobject.getString("percent_change_24h")));
                    }
                    if (!jsonobject.get("percent_change_7d").equals(null)) {
                        coin.setPercent_change_7d(Double.parseDouble(jsonobject.getString("percent_change_7d")));
                    }
                    if (!jsonobject.get("last_updated").equals(null)) {
                        coin.setLast_updated(Double.parseDouble(jsonobject.getString("last_updated")));
                    }







                coins.add(coin);



            }catch(Exception e){
                logger.error(e);
                System.err.println("failed to init coin = "+ coin.getName());
                continue;


            }



        }

        Collections.sort(coins);




    }



    private static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try
        {
            Integer.parseInt(s);

            // s is a valid integer

            isValidInteger = true;
        }
        catch (NumberFormatException e)
        {
            logger.error(e);
            // s is not an integer
        }

        return isValidInteger;
    }



}
