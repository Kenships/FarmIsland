import java.util.HashMap;
import greenfoot.*;
/**
 * Write a description of class CostHandler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CurrencyHandler extends SuperSmoothMover 
{
    private static int money;

    private static HashMap<ObjectID, Integer> priceIndex;

    private static SuperTextBox CashDisplay;
    public CurrencyHandler(){
        //setImage(new GreenfootImage(64, 128));
    }

    public void act(){
        GreenfootImage i = new GreenfootImage("BackGrounds/Money.png");
        i.setFont(new Font("Tekton Pro", true, false,  20));
        i.drawString("$" + money, 14, i.getHeight()/2);
        setImage(i);

    }

    public static void initialize(String savedFile){
        priceIndex = new HashMap<>();
        priceIndex.put(ObjectID.DIRT_TILE, 10);
        priceIndex.put(ObjectID.PORCUS_WHEAT_SEED, 888);
        priceIndex.put(ObjectID.WHEAT_SEED, 1);
        priceIndex.put(ObjectID.CARROT_SEED, 2);
        priceIndex.put(ObjectID.TOMATO_SEED, 20);
        priceIndex.put(ObjectID.DIAMOND_TOOL, 1000);
        priceIndex.put(ObjectID.BASIC_TOOL, 10);
        priceIndex.put(ObjectID.FERTILIZER, 5);
        resetBalance();
        if(savedFile != null){
            GameInfo.loadMoney(savedFile);
        }
    }

    public static void purchase(ObjectID ID, int amount){
        if(isAffordable(ID, amount)){
            int total = 0;
            if(ID == ObjectID.DIRT_TILE){
                for(int i = 0; i < amount; i++){
                    total += (AchievementManager.totalTiles + i) * 5;
                }
                AchievementManager.totalTiles += amount;
            }
            else{
                total += priceIndex.get(ID) * amount;
            }
            money -= total;
            adjustPrice(ID);
            Inventory.add(ID, amount);
        }
    }

    public static boolean isAffordable(ObjectID ID){
        return isAffordable(ID, 1);
    }

    public static boolean isAffordable(ObjectID ID, int amount){
        int total = 0;
        if(ID == ObjectID.DIRT_TILE){
            for(int i = 0; i < amount; i++){
                total += (AchievementManager.totalTiles + i) * 5;
            }
        }
        else{
            total += priceIndex.get(ID) * amount;
        }
        return money >= total;
    }

    public static void adjustPrice(ObjectID ID){

    }

    public static int getPrice(ObjectID ID){
        return getPrice(ID, 1);
    }

    public static int getPrice(ObjectID ID, int amount){

        if(priceIndex.containsKey(ID)){
            int total = 0;
            if(ID == ObjectID.DIRT_TILE){
                for(int i = 0; i < amount; i++){
                    total += (AchievementManager.totalTiles + i) * 5;
                }
            }
            else{
                total += priceIndex.get(ID) * amount;
            }

            return total;
        }
        else{
            return -1;
        }
    }

    public static int getBallance(){
        return money;
    }

    public static void deposit(int amount){
        money += amount;
    }

    public static void withdraw (int amount){
        money -= amount;
    }

    private static void resetBalance(){
        money = 0;
    }
}
