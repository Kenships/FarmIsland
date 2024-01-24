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
    
    private static GameWorld myWorld;
    
    private static GreenfootImage background;
    private static GreenfootImage myImage;
    
    private static int x, y;
    public CurrencyHandler(){
        
    }
    public void addedToWorld(World w){
        x = getX();
        y = getY();
    }
    public void act(){
        myImage.clear();
        myImage.drawImage(background, 0, 0);
        myImage.drawString("$" + money, 14, myImage.getHeight()/2);
        setImage(myImage);

    }

    public static void initialize(String savedFile, GameWorld w){
        myWorld = w;
        background = new GreenfootImage("BackGrounds/Money.png");
        myImage = new GreenfootImage(background.getWidth(), background.getHeight());
        myImage.setFont(new Font("Tekton Pro", true, false,  20));
        priceIndex = new HashMap<>();
        priceIndex.put(ObjectID.DIRT_TILE, 10);
        priceIndex.put(ObjectID.PORCUS_WHEAT_SEED, 888);
        priceIndex.put(ObjectID.WHEAT_SEED, 1);
        priceIndex.put(ObjectID.WHEAT, 1);
        priceIndex.put(ObjectID.CARROT_SEED, 2);
        priceIndex.put(ObjectID.CARROT, 1);
        priceIndex.put(ObjectID.TOMATO_SEED, 20);
        priceIndex.put(ObjectID.TOMATO, 10);
        priceIndex.put(ObjectID.BLUEBERRY_SEED, 20);
        priceIndex.put(ObjectID.BLUEBERRY, 10);
        priceIndex.put(ObjectID.DRAGONFRUIT_SEED, 25);
        priceIndex.put(ObjectID.DRAGONFRUIT, 12);
        priceIndex.put(ObjectID.STRAWBERRY_SEED, 30);
        priceIndex.put(ObjectID.STRAWBERRY, 30);
        priceIndex.put(ObjectID.SILVER_TOMATO_SEED, 30);
        priceIndex.put(ObjectID.SILVER_TOMATO, 15);
        priceIndex.put(ObjectID.GOLDEN_TOMATO_SEED, 50);
        priceIndex.put(ObjectID.GOLDEN_TOMATO, 25);
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
        GreenfootImage effect = new GreenfootImage(myImage.getWidth(), 20);
        effect.setColor(new Color(123,168,124));
        effect.setFont(new Font("Tekton Pro", true, false,  20));
        effect.drawString("$" + amount,0, 20);
        myWorld.addObject(new ForegroundEffect(Effect.FLOAT, effect, 20, 0.5), x + 14, y - 10);
        money += amount;
    }

    public static void withdraw (int amount){
        money -= amount;
    }

    private static void resetBalance(){
        money = 0;
    }
}
