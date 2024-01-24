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
    
    private UltraStatBar backgroundMusic;
    public final int musicVolumeMax = 100;
    public final int musicVolumeMin = 0;
    public static int maxBackgroundMusicVolume;
    public int barrMaxVal;
    public int getCurrVal;
    
    private UltraStatBar soundEffect;
    public static int maxSoundEffectVolume;
    
    private UltraStatBar brightness;
    public static int maxBrightness;
    
    
    private Button leaveButton;
    private Label bgMusicLabel;
    private Label soundEffectLabel;
    private Label brightnessLabel;
    
    public static int brightnessValue = 0;
    
    private GreenfootImage originalBackground;
    
    private GreenfootSound SettingsMusic;
    private static int startVolumeMax = 100;
    /**
     * Constructor for objects of class SettingsWorld.
     * 
     */
    
    
    public SettingsWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1, false);
        setBackground(new GreenfootImage("BackGrounds/Cloud BG.png"));
        StartWorld startworld = new StartWorld();
        
        leaveButton = new MenuButton("Leave");
        addObject(leaveButton,50,650);
        
        //Slider for the background music
        Slider backgroundMusicSlider = new Slider (900, 70, 100, Slider.CONSUMER_BAR);
        backgroundMusic = new UltraStatBar(backgroundMusicSlider, 0, null);
        barrMaxVal = backgroundMusicSlider.getMaxVal();
        getCurrVal = backgroundMusicSlider.getCurrVal();
        backgroundMusicSlider.setCurrVal(maxBackgroundMusicVolume);
        backgroundMusic.makeAdjustable();
        
        //Slider for the sound effects
        Slider soundEffectSlider = new Slider (900, 70, 100, Slider.CONSUMER_BAR);
        soundEffect = new UltraStatBar (soundEffectSlider, 0, null);
        barrMaxVal = soundEffectSlider.getMaxVal();
        getCurrVal = soundEffectSlider.getCurrVal();
        soundEffectSlider.setCurrVal(maxSoundEffectVolume);
        soundEffect.makeAdjustable();
        
        //Slider for the brightness
        Slider brightnessSlider = new Slider (900, 70, 100, Slider.CONSUMER_BAR);
        brightness = new UltraStatBar (brightnessSlider, 0, null);
        barrMaxVal = brightnessSlider.getMaxVal();
        getCurrVal = brightnessSlider.getCurrVal();
        brightnessSlider.setCurrVal(maxBrightness);
        brightness.makeAdjustable();
        
        //Add sliders to world
        addObject(backgroundMusic, SCREEN_WIDTH/2, SCREEN_HEIGHT/4);
        addObject(soundEffect, SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
        addObject(brightness, SCREEN_WIDTH/2, SCREEN_HEIGHT/2 + SCREEN_HEIGHT/4);
        System.out.println (barrMaxVal);
        System.out.println (getCurrVal);
        
        //Add text of sliders to world
        bgMusicLabel = new Label("bgMusic");
        addObject(bgMusicLabel, 190, SCREEN_HEIGHT/6); //text
        soundEffectLabel = new Label("soundEffect");
        addObject(soundEffectLabel, 190, 300); //text
        brightnessLabel = new Label("brightness");
        addObject(brightnessLabel, 190, 480); //text
        
        originalBackground = new GreenfootImage(getBackground());
        
        //Music
        SettingsMusic = new GreenfootSound ("SettingsMusic.mp3");
        SettingsMusic.setVolume(startVolumeMax);
    }
    
    public void act()
    {
        //need to fix viual issue
        SettingsMusic.playLoop();
        MouseInfo mouse = Greenfoot.getMouseInfo();
        maxBackgroundMusicVolume = backgroundMusic.getValueFromSlider(0);
        maxSoundEffectVolume = soundEffect.getValueFromSlider(0);
        maxBrightness = brightness.getValueFromSlider(0);
        System.out.println(maxSoundEffectVolume);
        if (mouse != null)
        {
            StartWorld.setVolumeMax(maxBackgroundMusicVolume);
            StartWorld.setBrightnessMax(maxBrightness+150);
            GameWorld.setVolumeMax(maxBackgroundMusicVolume);
            GameWorld.setBrightnessMax(maxBrightness+150);
            Button.setVolumeMax(maxSoundEffectVolume);
            Button.setVolumeMax(maxSoundEffectVolume);
            CollectionHandler.setVolumeMax(maxSoundEffectVolume);
            EquipDisplay.setVolumeMax(maxSoundEffectVolume);
            GenericItem.setVolumeMax(maxSoundEffectVolume);
            Seed.setVolumeMax(maxSoundEffectVolume);
            LandPlot.setVolumeMax(maxSoundEffectVolume);
            DirtTile.setVolumeMax(maxSoundEffectVolume);
            SettingsWorld.setBrightnessMax(maxBrightness+150);
            SettingsWorld.setVolumeMax(maxBackgroundMusicVolume);
            ShopMenu.setBrightnessMax(maxBrightness+150);
            AchievementMenu.setBrightnessMax(maxBrightness+150);
        }
        adjustBackgroundBrightness();

        if(Greenfoot.mouseClicked(leaveButton)){
            Greenfoot.setWorld(new StartWorld());
            SettingsMusic.stop();
        }
    }
    
    public static void setBrightnessMax(int newMax)
    {
        brightnessValue = newMax;
    }
    
    public static int setBrightnessMax()
    {
        return brightnessValue;
    }
    
    public static int getBrightnessMax()
    {
        return brightnessValue;
    }
    
    public static void setVolumeMax(int newMax)
    {
        startVolumeMax = newMax;
    }

    public static int getVolumeMax()
    {
        return startVolumeMax;
    }
    
    public void started()
    {
        SettingsMusic.playLoop();
    }
    
    public void stopped()
    {
        SettingsMusic.pause();
    }
    
    private void adjustBackgroundBrightness() {
        GreenfootImage backgrounds = new GreenfootImage(originalBackground);
    
        // Create a semi-transparent overlay image with adjusted darkness
        GreenfootImage overlay = new GreenfootImage(backgrounds.getWidth(), backgrounds.getHeight());
        overlay.setColor(new Color(0, 0, 0, 255 - brightnessValue));
        overlay.fill();
    
        // Draw the overlay on top of the original image
        backgrounds.drawImage(overlay, 0, 0);
    
        setBackground(backgrounds);
    }
    
    
}
