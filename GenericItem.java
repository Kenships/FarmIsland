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
    private GreenfootImage hover;
    private int width, height;
    public GenericItem(ObjectID ID){
        this.ID = ID;
        image = new GreenfootImage(ID.getDisplayImage());
        hover = new GreenfootImage(image);
        double ratio = (double) image.getHeight()/image.getWidth();
        image.scale(56, (int) (56 * ratio + 0.5));
        hover.scale(64, (int) (64 * ratio + 0.5));
        width = 56;
        height = (int) (56 * ratio + 0.5);
        setImage(image);
    }
    public void act()
    {
        checkMouseAction();
    }
    
    public void checkMouseAction(){
        if(hoveringThis()){
            setImage(hover);
        }
        else{
            setImage(image);
        }    
    }
    
    public boolean hoveringThis(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){

            int leftBound = getX() - width/2;
            int rightBound = getX() + width/2;
            int topBound = getY() - height/2;
            int bottomBound = getY() + height/2;
            return mouse.getX() < rightBound && mouse.getX() > leftBound && mouse.getY() < bottomBound && mouse.getY() > topBound;
        }
        return false;
    }
}
