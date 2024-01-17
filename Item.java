import greenfoot.*;
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends SuperSmoothMover 
{
    protected int fixedX, fixedY;

    public void reposition(){
        //check if seed is held in cursor
        if(Cursor.getItem() == this){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if(mouse != null){
                setLocation (mouse.getX(), mouse.getY());
            }
        }
        else{
            setLocation (fixedX, fixedY);
        }
    }
}
