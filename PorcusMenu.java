import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PorcusMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PorcusMenu extends SuperSmoothMover
{
    public static final int SPEED = 8;
    public static final int BUTTON_OFFSET = 84;
    private Button porcus;
    private boolean open;
    private int direction;
    private GreenfootImage background;
    private SimpleTimer actTimer;
    public PorcusMenu(Button porcus){
        this.porcus = porcus;
        background = new GreenfootImage("Backgrounds/Porcus.png");
        actTimer = new SimpleTimer();
        setImage(background);
    }
    
    public void addedToWorld(World w){
        setLocation(- background.getWidth()/2, GameWorld.SCREEN_HEIGHT - background.getHeight()/2);
        w.addObject(porcus, getX() + background.getWidth()/2 + BUTTON_OFFSET, GameWorld.SCREEN_HEIGHT - BUTTON_OFFSET);
    }
    
    public void act()
    {
       if(actTimer.millisElapsed() >= 4){
            actTimer.mark();
            if(direction == 1){
                open();
            }
            if(direction == -1){
                close();
            }
        }
    }

    public void open(){
        open = true;
        direction = 1;
        if(getX() < background.getWidth()/2){
            setLocation(getX() + SPEED, getY());

            porcus.setLocation(porcus.getX() + SPEED, porcus.getY());

        }
        else{
            direction = 0;
            setLocation(background.getWidth()/2, getY());

            porcus.setLocation(getX() + background.getWidth()/2 + BUTTON_OFFSET, porcus.getY());

        }
    }

    public void close(){
        open = false;
        direction = -1;
        if(getX() > - background.getWidth()/2){
            setLocation(getX() - SPEED, getY());

            porcus.setLocation(porcus.getX() - SPEED, porcus.getY());

        }
        else{
            direction = 0;
            setLocation(-background.getWidth()/2, getY());

            porcus.setLocation(getX() + background.getWidth()/2 + BUTTON_OFFSET, porcus.getY());

        }
    }
    public boolean isOpen(){
        return open;
    }
}
