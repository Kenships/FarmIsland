import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Rain Drop
 * 
 * @author (your name) 
 * @version December 2023
 */
public class RainDrop extends Actor
{
    public RainDrop()
    {
        GreenfootImage image = getImage();
        image.setTransparency(200);
        setImage(image);
    }
    
    public void act()
    {
        setLocation(getX(), getY() + 1);
        if(getY() > getWorld().getHeight())
        {
            getWorld().removeObject(this);
        }
    }
}
