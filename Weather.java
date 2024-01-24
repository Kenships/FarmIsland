import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Weather here.
 * 
 * @author: ZhaoQi Xu
 * @version: January 2024
 */
public abstract class Weather extends SuperSmoothMover {

    /**
     * Performs the specific weather effect.
     * Subclasses must implement this method to define the effect of the weather.
     */
    public abstract void weatherEffect();

    /**
     * The 'act' method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * Override this method to add additional behavior for the weather.
     */
    public void act() {
        // Add your action code here.
    }
}
