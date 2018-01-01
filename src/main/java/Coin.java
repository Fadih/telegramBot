public class Coin implements Comparable<Coin>{

    private String id = null;
    private String name = null;
    private String symbol = null;
    private double rank = 0 ;
    private double price_usd  = 0;
    private double price_btc = 0;
    private double day_volume_usd = 0;
    private double market_cap_usd = 0;
    private double available_supply = 0;
    private double total_supply = 0;
    private double max_suply = 0;
    private double percent_change_1h = 0;
    private double percent_change_24h = 0;
    private double percent_change_7d = 0;
    private double last_updated = 0;
    private double mytotalcoins = 0 ;


    public double getMytotal() {
        return mytotalcoins;
    }

    public void setMytotal(double mytotal) {
        this.mytotalcoins = mytotal;
    }




    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public void setPrice_usd(double price_usd) {
        this.price_usd = price_usd;
    }

    public void setPrice_btc(double price_btc) {
        this.price_btc = price_btc;
    }

    public void setDay_volume_usd(double day_volume_usd) {
        this.day_volume_usd = day_volume_usd;
    }

    public void setMarket_cap_usd(double market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public void setAvailable_supply(double available_supply) {
        this.available_supply = available_supply;
    }

    public void setTotal_supply(double total_supply) {
        this.total_supply = total_supply;
    }

    public void setMax_suply(double max_suply) {
        this.max_suply = max_suply;
    }

    public void setPercent_change_1h(double percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public void setPercent_change_24h(double percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public void setPercent_change_7d(double percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public void setLast_updated(double last_updated) {
        this.last_updated = last_updated;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getRank() {
        return rank;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public double getPrice_btc() {
        return price_btc;
    }

    public double getDay_volume_usd() {
        return day_volume_usd;
    }

    public double getMarket_cap_usd() {
        return market_cap_usd;
    }

    public double getAvailable_supply() {
        return available_supply;
    }

    public double getTotal_supply() {
        return total_supply;
    }

    public double getMax_suply() {
        return max_suply;
    }

    public double getPercent_change_1h() {
        return percent_change_1h;
    }

    public double getPercent_change_24h() {
        return percent_change_24h;
    }

    public double getPercent_change_7d() {
        return percent_change_7d;
    }

    public double getLast_updated() {
        return last_updated;
    }


    @Override
    public int compareTo(Coin o) {

        if(this.rank >o.getRank())
            return 1;
        if(this.rank < o.getRank())
            return -1;
        else
            return 0;

    }
}
