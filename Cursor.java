import greenfoot.*;

/**
 * Write a description of class Cursor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cursor
{
    private static Actor holding;
    
    public static MouseInfo getMouseInfo(){
        return Greenfoot.getMouseInfo();
    }
    
    public static void release(){
        holding = null;
    }
    
    public static Actor getActor(){
        return holding;
    }
    
    public static int getButton(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        return mouse.getButton();
    }
    
    public static int getClickCount(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        return mouse.getClickCount();
    }
    
    public static int getX(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        return mouse.getX();
    }
    
    public static int getY(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        return mouse.getY();
    }
    
    public static void pickUp(Actor newActor){
        holding = newActor;
    }
}
