import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
 */
public class GameWorld extends World
{
    //720p resolution 16:9 aspect ratio
    private static boolean editMode;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
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
        
    }

    public void initialize(String saveFile){
        /**
         * PLEASE REMOVE EDITMODE = TRUE LATOR
         */
        editMode = true;
        //add objects
        setPaintOrder(Seed.class, Plant.class, DirtTile.class, LandPlot.class);

        addObject(new LandPlot(), SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

        //sets up the enventory from previous save
        Inventory.initialize(saveFile);

        addObject(new Seed(ObjectID.WHEAT_SEED), 1200, 650);
        addObject(new Seed(ObjectID.STUBBY_WHEAT_SEED), 1100, 650);

    }
    /**
     * NEW: removed zsort method
     */
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
}