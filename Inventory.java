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
    private static InventoryDisplay display;
    public void act()
    {
        // Add your action code here.
    }
    
    
    public static void initialize(String saveFile, InventoryDisplay d){
        display = d;
        inventory = new HashMap<>();
        loadGame(saveFile);
    }
    
    public static void loadGame(String saveFile){
        //will read from game save file
        resetData();
    }
    
    public static void resetData(){
        inventory.clear();
    }
    
    public static HashMap <ObjectID, Integer> getInventory(){
        return inventory;
    }
    
    public static int getAmount(ObjectID id){
        if(!inventory.containsKey(id)){
            return 0;
        }
        return inventory.get(id);
    }
   
    public static boolean removeAll(ObjectID id){
        if(inventory.containsKey(id)){;
            inventory.remove(id);
            return true;
        }
        
        return false;
    }
    
    public static boolean remove(ObjectID id, int amount){
        if(inventory.containsKey(id)){
            if(inventory.get(id) - amount == 0){
                inventory.remove(id);
                display.removeItem(id);
            }
            else if(inventory.get(id) - amount < 0){
                return false;
            }
            else{
                inventory.put(id,inventory.get(id) - amount);
            }
            
            return true;
        }
        
        return false;
    }
    
    public static boolean remove(ObjectID id){
        return remove(id, 1);
        
    }
    
    //adds 1 item to inventory
    public static void add(ObjectID id){
        add(id, 1);
    }
    
    //adds multiple items to inventory
    public static void add(ObjectID id, int amount){
        if(!inventory.containsKey(id)){;
            inventory.put(id,amount);
            display.addItem(id);
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
    }
}
