import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ForegroundEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ForegroundEffect extends Effect
{
    public ForegroundEffect(int effect, String image){
        this(effect, new GreenfootImage(image));
    }
    public ForegroundEffect(int effect, GreenfootImage i){
        this(effect, i, 10);
    }
    public ForegroundEffect(int effect, GreenfootImage i, int duration){
        this(effect, i , duration, 0.1);
    }
    public ForegroundEffect(int effect, GreenfootImage i, double duration, double speed){
        super(effect, i, duration, speed);
    }
    public void act()
    {
        super.act();
    }
}
