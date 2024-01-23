import greenfoot.*;

/**
 * Write a description of class Cursor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cursor
{
    private static Item holding;
    private static Tool activeTool;
    
    public static MouseInfo getMouseInfo(){
        return Greenfoot.getMouseInfo();
    }
    
    public static void release(){
        holding = null;
    }
    
    public static void setTool(Tool tool){
        activeTool = tool;
    }
    
    public static Tool getTool(){
        return activeTool;
    }
    
    public static Item getItem(){
        return holding;
    }
    
    public static int getButton(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            return mouse.getButton();
        }
        else{
            return -1;
        }
        
    }
    
    public static boolean leftClicked(){
        return getButton() == 1;
    }
    public static boolean rightClicked(){
        return getButton() == 3;
    }
    public static int getClickCount(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        return mouse.getClickCount();
    }
    
    public static int getX(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            return mouse.getX();
        }
        else{
            return 0;
        }
    }
    
    public static int getY(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
         if(mouse != null){
            return mouse.getX();
        }
        else{
            return 0;
        }
    }
    
    public static void pickUp(Item item){
        holding = item;
    }
}
