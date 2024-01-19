import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GenericItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenericItem extends Item
{
    private GreenfootImage image;
    public GenericItem(ObjectID ID){
        this.ID = ID;
        image = new GreenfootImage(ID.getDisplayImage());
        double ratio = (double) image.getHeight()/image.getWidth();
        image.scale(56, (int) (56 * ratio + 0.5));
        setImage(image);
    }
    public void act()
    {
        
    }
}
