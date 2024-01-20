import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Write a description of class GameInfo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameInfo
{
    private static World world;
    private static ArrayList<DirtTile> dirtTiles;
    private static HashMap<ObjectID, Integer> inventory;
    private static ArrayList<DirtTile> dirtInfo;
    private int savedCurrency;
    private static int dirtCounter;
    
    public static void saveGame(World w){
        world = w;
        dirtTiles = new ArrayList<DirtTile>();
        inventory = Inventory.getInventory();
        try{
            FileWriter out = new FileWriter ("FarmIslandSave.txt");
            PrintWriter output = new PrintWriter (out);
            output.println(CurrencyHandler.getBallance());
            output.println("Tiles Start");
            dirtTiles.addAll(world.getObjects(DirtTile.class));
            dirtCounter = 0;
            for(DirtTile d : dirtTiles){
                if(d.isActive()){
                    dirtCounter ++;
                    output.println(d.getRow());
                    output.println(d.getCol());
                    output.println(d.getPlant());
                    output.println(d.getGrowthMultiplier());
                }
            }
            output.println("Tiles End");
            output.println("Plants Inventory Start");
            for (Map.Entry<ObjectID, Integer> entry : inventory.entrySet()) {
                output.println(entry.getKey());
                if(entry.getKey().equals(ObjectID.DIRT_TILE)){
                    output.println(entry.getValue() + dirtCounter - 1);
                }
                else{
                    output.println(entry.getValue());
                }
            }
            output.println("Plants Inventory End");
            output.println("Achievements Start");
            output.println(AchievementManager.totalPlants);
            output.println("Achievements End");
            output.close();
        }
        catch (IOException e){
            System.out.println("Error: "  + e);
        }
    }
    
    public static void loadMoney(String savedFile){
        try{
            Scanner fileScanner = new Scanner (new File(savedFile));
            CurrencyHandler.deposit(Integer.valueOf(fileScanner.nextLine()));
            fileScanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Invalid File");
        }
    }
    
    public static void loadInventory(String savedFile){
        try{
            Scanner fileScanner = new Scanner (new File(savedFile));
            while(fileScanner.hasNext()){
                if("Plants Inventory Start".equals(fileScanner.nextLine())){
                    break;
                }
            }
            while(fileScanner.hasNext()){
                String line = fileScanner.nextLine();
                if("Plants Inventory End".equals(line)){
                    break;
                }
                ObjectID id = ObjectID.valueOf(line);
                int quantity = Integer.valueOf(fileScanner.nextLine());
                Inventory.add(id, quantity);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Invalid File");
        }
    }
    /*
    public static void loadGame(String savedFile){
        try{
            Scanner fileScanner= new Scanner (new File(savedFile));
            if(fileScanner.hasNextLine()){
                String temp = fileScanner.nextLine();
                savedCurrency = Integer.parseInt(temp);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Invalid File");
        }
    }
    */
   
   
}
