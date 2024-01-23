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

import javax.swing.*;


/**
 * Manages the saving and loading aspects for the game.
 * 
 * @author Ryan Du
 * @version January 2024
 */
public class GameInfo
{
    private static GameWorld world;
    private static ArrayList<DirtTile> dirtTiles;
    private static HashMap<ObjectID, Integer> inventory;
    private static ArrayList<DirtTile> dirtInfo;
    private int savedCurrency;
    private static int dirtCounter;
    
    /**
     * Saves the contents of the world to a file in the saves folder.
     * 
     * @param w World that is being saved.
     */
    public static void saveGame(World w){
        world = (GameWorld) w;
        dirtTiles = new ArrayList<DirtTile>();
        inventory = Inventory.getInventory();
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Game");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File("./Saves"));
        
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
    
            if (!filePath.endsWith(".txt")) {
                filePath += ".txt";
            }

            try{
                FileWriter out = new FileWriter (filePath);
                PrintWriter output = new PrintWriter (out);
                output.println(CurrencyHandler.getBallance());
                output.println("Tiles Start");
                dirtTiles.addAll(world.getObjects(DirtTile.class));
                for(DirtTile d : dirtTiles){
                    if(d.isActive()){
                        output.println(d.getRow());
                        output.println(d.getCol());
                        if(d.getPlant() == null){
                            output.println("null");
                        }
                        else{
                            output.println(d.getPlant().getID());
                            output.println(d.getPlant().getGrowthStage());
                            output.println(d.getPlant().getMaturity());
                            output.println(d.getPlant().isMature());
                        }
                        //output.println(d.getGrowthMultiplier());
                    }
                }
                output.println("Tiles End");
                output.println("Plants Inventory Start");
                for (Map.Entry<ObjectID, Integer> entry : inventory.entrySet()) {
                    output.println(entry.getKey());
                    output.println(entry.getValue());
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
    }
    
    /**
     * Loads the money using the information from a saved file.
     * 
     * @param savedFile File that is being used.
     */
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
    
    /**
     * Loads the inventory using the information from a saved file.
     * 
     * @param savedFile File that is being used.
     */
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
            fileScanner.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Invalid File");
        }
    }
    
    /**
     * Loads the tiles using the information from a saved file.
     * 
     * @param savedFile File that is being used.
     * @param w World that the tiles are added to.
     */
    public static void loadTiles(String savedFile, World w){
        dirtInfo = new ArrayList<DirtTile>();
        world = (GameWorld) w;
        try{
            Scanner fileScanner = new Scanner (new File(savedFile));
            while(fileScanner.hasNext()){
                if("Tiles Start".equals(fileScanner.nextLine())){
                    break;
                }
            }
            while(fileScanner.hasNext()){
                String line = fileScanner.nextLine();
                if("Tiles End".equals(line)){
                    break;
                }
                int row = Integer.valueOf(line);
                int col = Integer.valueOf(fileScanner.nextLine());
                DirtTile tile = new DirtTile(world.getLandPlot(),row, col, true);
                String plantLine = fileScanner.nextLine();
                if(!"null".equals(plantLine)){ 
                    ObjectID id = ObjectID.valueOf(plantLine).getSeedID();
                    Seed seed = new Seed(id);
                    Plant plant = seed.newPlant();
                    plant.setGrowthStage(Integer.valueOf(fileScanner.nextLine()));
                    plant.setMaturity(Integer.valueOf(fileScanner.nextLine()));
                    plant.setMature(Boolean.valueOf(fileScanner.nextLine()));
                    tile.setPlant(plant);
                }
                dirtInfo.add(tile);
            }
            fileScanner.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Invalid File");
        }
        world.getLandPlot().fillTiles(dirtInfo);
    }
}
