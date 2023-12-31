import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.HashMap;
import java.util.ArrayList;
/**
 * Write a description of class GameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/**
 * CHANGES:
 * - GameWorld
 * - new Util class
 * - LandPlot
 * - DirtTile
 * - Plant
 * - Tile
 * - Seed
 * - new class Grid Comparator
 * - new class GridPath
 * - Stubby Wheat
 * - Wheat
 * - new class Effect
 * 
 */
public class GameWorld extends World
{
    //720p resolution 16:9 aspect ratio
    private static boolean editMode;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public static final String GAME = "Game";
    public static final String SHOP = "Shop";
    public static final String ACHIEVEMENT = "Achievement";
    
    //used to indicate which screen is currently in priority/active
    private String screen;
    private ShopMenu shop;
    private Button openShop;
    /**
     * Constructor for objects of class GameWorld.
     * 
     */
    public GameWorld(String saveFile)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1, false); 
        initialize(saveFile);
    }

    public void act(){
        if(screen.equals(GAME)){
            if(openShop.getWorld() == null){
                addObject(openShop, 64, 656);
            }
            checkMouseAction();
        }
    }

    public void initialize(String saveFile){
        /**
         * PLEASE REMOVE EDITMODE = TRUE LATOR
         */
        editMode = true;
        
        //initializes starting screen
        
        screen = GAME;
        
        //add objects
        setPaintOrder(CurrencyHandler.class, Button.class, ItemFrame.class, ShopMenu.class, Seed.class, Plant.class, DirtTile.class, LandPlot.class);

        addObject(new LandPlot(), SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

        //sets up the enventory from previous save
        Inventory.initialize(saveFile);
        CurrencyHandler.initialize(saveFile);
        
        addObject(new CurrencyHandler(), 1200, 100);
        addObject(new Seed(ObjectID.WHEAT_SEED, 1, false), 1200, 650);
        addObject(new Seed(ObjectID.STUBBY_WHEAT_SEED, 0, false), 1100, 650);
        
        HashMap<ObjectID, Integer> temp = new HashMap<>();
        temp.put(ObjectID.DIRT_TILE, -1);
        temp.put(ObjectID.WHEAT_SEED, -1);
        temp.put(ObjectID.STUBBY_WHEAT_SEED, -1);
        shop = new ShopMenu(temp);
        openShop = new MenuButton("Shop");
        addObject(openShop, 64, 656);
    }
    
    public void checkMouseAction(){
        if(Greenfoot.mouseClicked(openShop)){
            removeObject(openShop);
            addObject(shop, SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
        }
    }
    
    public static boolean getEditMode(){
        return editMode;
    }
    //sets edit mode
    public static void setEditMode(boolean mode){
        editMode = mode;
    }
    //changes edit mode from true to false and vice verca
    public static void toggleEditMode(){
        editMode ^= true;
    }
    public boolean isScreen(String screen){
        return this.screen.equals(screen);
    }
    public String getScreen(){
        return screen;
    }
    public void setScreen(String screen){
        this.screen = screen;
    }
}
