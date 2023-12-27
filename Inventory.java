import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.ArrayList;

/**
 * This class will store all the items that have been attained by the user
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventory
{
    private static HashMap <ObjectID, Integer> inventory;
    private static int currency;
    public void act()
    {
        // Add your action code here.
    }
    
    public static void initialize(String saveFile){
        inventory = new HashMap<>();
        loadGame(saveFile);
    }
    
    public static void loadGame(String saveFile){
        //will read from game save file
        resetData();
    }
    
    public static void resetData(){
        currency = 0;
        inventory.clear();
    }
    
    public static void deposit(int amount){
        currency += amount;
        output();
    }
    
    public static void withdraw (int amount){
        currency -= amount;
    }
    
    public static int getBallance(){
        return currency;
    }
    
    public static int getAmount(ObjectID id){
        if(!inventory.containsKey(id)){
            return 0;
        }
        return inventory.get(id);
    }
    /**
     * NEW:
     */
    public static boolean removeAll(ObjectID id){
        if(inventory.containsKey(id)){;
            inventory.remove(id);
            return true;
        }
        output();
        return false;
    }
    
    public static boolean remove(ObjectID id, int amount){
        if(inventory.containsKey(id)){
            inventory.put(id,inventory.get(id) - amount);
            return true;
        }
        output();
        return false;
    }
    
    public static boolean remove(ObjectID id){
        if(inventory.containsKey(id)){;
            inventory.put(id,inventory.get(id) - 1);
            return true;
        }
        output();
        return false;
    }
    
    //adds 1 item to inventory
    public static void add(ObjectID id){
        add(id, 1);
    }
    
    //adds multiple items to inventory
    public static void add(ObjectID id, int amount){
        if(!inventory.containsKey(id)){;
            inventory.put(id,amount);
        }
        else{
            inventory.put(id, inventory.get(id) + amount);
        }
        output();
    }
    //method for debugging only
    public static void output(){
        System.out.println("--------------------");
        for(ObjectID id : inventory.keySet()){
            System.out.println(id + " : " + inventory.get(id));
        }
        System.out.println("Currency : " + currency);
    }
}
