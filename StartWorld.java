import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Changes:
 * StartWorld
 */
public class StartWorld extends World
{
    /**
     * NEW
     */
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    
    private GreenfootImage background = new GreenfootImage("Start BG Back.png");
    private GreenfootImage clouds = new GreenfootImage("Start BG Clouds.png");
    private GreenfootImage island = new GreenfootImage("Start BG Island.png");
    
    private GreenfootImage screen = new GreenfootImage(430, 720);
    
    private Button startButton;
    private Button load;
    private Button settings;
    private Button quit;
    public StartWorld()
    {   
        /**
         * NEW
         */
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1, false);
        screen.setColor(new Color(255,255,255,45));
        screen.fillPolygon(new int[] {0,0,430,390}, new int[] {0,720,720,0}, 4);
        
        background.drawImage(screen, 0, 0);
        setBackground(background);
        
        initialize();
    }
    
    public void act(){
        //button actions here
        if(Greenfoot.mouseClicked(startButton)){
            Greenfoot.setWorld(new GameWorld(null));
        }
        if(Greenfoot.mouseClicked(load)){
            //brings you to load world/menu
        }
        if(Greenfoot.mouseClicked(settings)){
            //brings you to settings world/menu
        }
        if(Greenfoot.mouseClicked(quit)){
            Greenfoot.stop();
        }
    }
    
    public void initialize(){
        /**
         * NEW: initialize method edited
         */
        setPaintOrder(Button.class, Effect.class);
        startButton = new StartMenuButton("Start");
        load = new StartMenuButton("Load");
        settings = new StartMenuButton("Settings");
        quit = new StartMenuButton("Quit");
        addObject(startButton,160,200);
        addObject(load,160,320);
        addObject(settings,160,440);
        addObject(quit,160,560);
        addObject(new Effect(Effect.ROCK, island), SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
        addObject(new Effect(Effect.PULSE, clouds), SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
    }
}
