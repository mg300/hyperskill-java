import java.util.Map;

public class Storage {
    public int water;
    public int milk;
    public int coffeeBeans;
    public int money;

    public int cups;



    public void updateAmount(){
        water = 400;
        milk = 540;
        coffeeBeans = 120;
        money = 550;
        cups = 9;
    }

    public Storage() {
        updateAmount();
    }

    public void addAmount(int water, int milk, int coffeeBeans, int cups) {
        this.water += water;
        this.milk += milk;
        this.coffeeBeans += coffeeBeans;
        this.cups += cups;
    }
    public void takeMoney (){
            this.money =0;
    }
    public String getAmount(){
        return ("The coffee machine has:\n" +
                +water+" ml of water\n" +
                +milk+" ml of milk\n" +
                +coffeeBeans+" g of coffee beans\n" +
                +cups+" disposable cups\n" +
                "$"+money+" of money\n");
    }
    public boolean takeIngr(Product product){
        Map<String, Integer> productMap = product.getProperties();
        if(productMap.get("WATER")>water){
            return false;
        } else if (productMap.get("MILK")>milk){
            return false;
        }else if (productMap.get("BEANS")>coffeeBeans){
            return false;
        }else if (productMap.get("CUPS")>cups){
            return false;
        }
        water-=productMap.get("WATER");
        milk-=productMap.get("MILK");
        coffeeBeans-=productMap.get("BEANS");
        cups-=productMap.get("CUPS");
        money+=productMap.get("COST");
        return true;
    }
}
