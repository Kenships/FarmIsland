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
    public static final int ACTS_TO_HOME = 30;
    public static final String GAME = "Game";
    public static final String SHOP = "Shop";
    public static final String ACHIEVEMENT = "Achievement";

    private LandPlot landPlot;
    private Button homeButton;
    private boolean shouldMove;
    private double exactDistancePerFrame;
    private int i;
    private double distance;
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
            checkMouseAction();
            homeIslands();
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
        setPaintOrder(CurrencyHandler.class, Tools.class, Button.class, ItemFrame.class, ShopMenu.class, Seed.class, Plant.class, DirtTile.class, LandPlot.class);
        landPlot = new LandPlot();
        addObject(landPlot, SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

        //sets up the enventory from previous save
        Inventory.initialize(saveFile);
        CurrencyHandler.initialize(saveFile);
        //Inventory.add(ObjectID.DIRT_TILE, 10000);
        
        addObject(new CurrencyHandler(), 1200, 100);
        addObject(new Seed(ObjectID.WHEAT_SEED, 1, false), 1200, 650);
        addObject(new Seed(ObjectID.STUBBY_WHEAT_SEED, 0, false), 1100, 650);
        
        Common tool = new Common(ObjectID.DIAMOND_TOOL);
        addObject(tool, 0,0);
        Cursor.setTool(tool);
        
        HashMap<ObjectID, Integer> temp = new HashMap<>();
        temp.put(ObjectID.DIRT_TILE, -1);
        temp.put(ObjectID.WHEAT_SEED, -1);
        temp.put(ObjectID.STUBBY_WHEAT_SEED, -1);
        shop = new ShopMenu(temp);
        openShop = new MenuButton("Shop");
        homeButton = new MenuButton("Home");
        addObject(openShop, 64, 656);
        addObject(homeButton, 64, 600);
    }
    public void homeIslands(){
        DirtTile centreTile = landPlot.getTile(LandPlot.STARTING_ROW, LandPlot.STARTING_COL);
        int centreX = SCREEN_WIDTH/2;
        int centreY = SCREEN_HEIGHT/2;
        int centreTileX = centreTile.getX();
        int centreTileY = centreTile.getY();
        double deltaX = centreX - centreTileX;
        double deltaY = centreY - centreTileY;
        distance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        if(i == ACTS_TO_HOME){
            shouldMove = false;
            i = 0;
        }

        if(shouldMove)
        {
            if(i == 0){
                exactDistancePerFrame = distance/ACTS_TO_HOME;

                centreTile.enableStaticRotation();
                centreTile.turnTowards(centreX, centreY);
                double rotation = centreTile.getPreciseRotation();

                for (Tile object : getObjects(DirtTile.class)) {
                    object.enableStaticRotation();
                    object.setRotation(rotation);
                }

            }
            for (Tile object : getObjects(DirtTile.class)) {
                object.move(exactDistancePerFrame);
            }

            i++;
        }
    }
    public void checkMouseAction(){
        if(openShop.leftClickedThis()){
            System.out.println("open");
            setScreen(SHOP);
        }
        if(homeButton.leftClickedThis() && !shouldMove && distance  != 0){
            shouldMove = true;
        }
    }

    public void removeButtons(){
        removeObject(openShop);
        removeObject(homeButton);
    }

    public void resetButtons(){
        addObject(openShop, 64, 656);
        addObject(homeButton, 64, 600);
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
        MouseInfo mouse = Cursor.getMouseInfo();
        switch(screen){
            case GAME:
                resetButtons();
                addObject(Cursor.getTool(), mouse.getX(), mouse.getY());
                break;
            case SHOP:
                removeButtons();
                removeObject(Cursor.getTool());
                addObject(shop, SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
                break;
        }
    }
}
