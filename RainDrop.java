import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Rain Drop
 * 
 * @author: Zhaoqi Xu
 * @version December 2023
 */
public class RainDrop extends SuperSmoothMover {

    /**
     * Constructor for RainDrop class.
     * Sets the transparency of the raindrop image.
     */
    public RainDrop() {
        GreenfootImage image = getImage();
        image.setTransparency(200);
        setImage(image);
    }

    /**
     * Act method for RainDrop.
     * Controls the behavior of the raindrop, making it fall vertically.
     * Removes the raindrop if it goes beyond the bottom of the world.
     */
    public void act() {
        setLocation(getX(), getY() + 1);
        if (getY() > getWorld().getHeight()) {
            getWorld().removeObject(this);
        }
    }
}