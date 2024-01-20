import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
/**
 * Write a description of class Sparkles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sparkles extends SuperSmoothMover
{
    private DirtTile dirtTile;
    private GreenfootImage image;
    private int spawnChance = 20;
    
    public Sparkles(DirtTile d){
        this.dirtTile = d;
        image = new GreenfootImage("sparkles.png");
        setImage(new GreenfootImage(1,1));
    }

    public void act(){
        setLocation(dirtTile.getX(), dirtTile.getY());
        int rand = Greenfoot.getRandomNumber(20);
        if(rand == 11){
            spawnSparkles();
        }
    }
    
    public void spawnSparkles(){
        int randX = Greenfoot.getRandomNumber(65) - 32;
        int topBound = -2 * Math.abs(randX) + 64;
        int bottomBound = 2 * Math.abs(randX) - 64;
        int randY = 0;
        if(topBound != 0){
            randY = Greenfoot.getRandomNumber(topBound) - bottomBound;
        }
        getWorld().addObject(new Effect(Effect.FADE,new GreenfootImage(image)), getX() - 25 + randY - 10, getY() + randX);
    }
}
