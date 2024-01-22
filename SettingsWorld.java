import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SettingsWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SettingsWorld extends World
{
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    
    private UltraStatBar consumerSpawn;
    public final int musicVolumeMax = 100;
    public final int musicVolumeMin = 0;
    public static int maxConsumers;
    public int barrMaxVal;
    public int getCurrVal;
    /**
     * Constructor for objects of class SettingsWorld.
     * 
     */
    
    
    public SettingsWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1, false); 
        StartWorld startworld = new StartWorld();
        
        
        Slider slider = new Slider (900, 70, 100, Slider.CONSUMER_BAR);
        consumerSpawn = new UltraStatBar(slider, 0, null);
        barrMaxVal = slider.getMaxVal();
        getCurrVal = slider.getCurrVal();
        slider.setCurrVal(maxConsumers);
        consumerSpawn.makeAdjustable();
        
        
        addObject(consumerSpawn, SCREEN_WIDTH/2, SCREEN_HEIGHT/3);
        System.out.println (barrMaxVal);
        System.out.println (getCurrVal);
        
        

    }
    
    public void act()
    {
        if (StartWorld.getVolumeMax() >= 0)
        {
            addObject(new Label("Music Volume: " + (StartWorld.getVolumeMax()) + " "), SCREEN_WIDTH/2, SCREEN_HEIGHT/6);
            if (StartWorld.getVolumeMax() < 0)
            {
                addObject(new Label(" " + 0 + " "), SCREEN_WIDTH/2, SCREEN_HEIGHT/6);
            }
        }
        //need to fix viual issue

        MouseInfo mouse = Greenfoot.getMouseInfo();
        maxConsumers = consumerSpawn.getValueFromSlider(0);
        if (mouse != null)
        {
            StartWorld.setVolumeMax(maxConsumers);
        }
    }
}
