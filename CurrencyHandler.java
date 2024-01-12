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
    
    public CurrencyHandler(){
        setImage(new GreenfootImage(64, 128));
    }
    
    public void act(){
        GreenfootImage i = new GreenfootImage(64, 128);
        i.drawString("$" + money, 0, i.getHeight()/2);
        setImage(i);
    }
    
    public static void initialize(String saveFile){
        priceIndex = new HashMap<>();
        priceIndex.put(ObjectID.DIRT_TILE, 10);
        priceIndex.put(ObjectID.STUBBY_WHEAT_SEED, 20);
        priceIndex.put(ObjectID.WHEAT_SEED, 1);
    }
    
    
    public static void purchase(ObjectID ID, int amount){
        if(isAffordable(ID, amount)){
            money -= amount * priceIndex.get(ID);
            adjustPrice(ID);
            Inventory.add(ID, amount);
        }
    }
    
    public static boolean isAffordable(ObjectID ID){
        return isAffordable(ID, 1);
    }
    public static boolean isAffordable(ObjectID ID, int amount){
        return money >= amount * priceIndex.get(ID);
    }
    public static void adjustPrice(ObjectID ID){
        //fill method
    }
    
    public static int getPrice(ObjectID ID){
        if(priceIndex.containsKey(ID)){
            return priceIndex.get(ID);
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
}
