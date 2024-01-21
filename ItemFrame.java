import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ItemFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemFrame extends SuperSmoothMover
{
    protected ObjectID ID;
    protected GreenfootImage background;
    protected GreenfootImage foreground;
    protected GreenfootImage mainImage;
    protected int width, height;
    public ItemFrame(ObjectID ID){
        this(ID, 128,128);
        
    }
    
    public ItemFrame(ObjectID ID, int width, int height){
        this.width = width;
        this.height = height;
        mainImage = new GreenfootImage(width, height);
        background = new GreenfootImage("Displays/Frames/Frame.png");
        background.scale(width, height);
        updateID(ID);   
    }
    
    public ObjectID getID(){
        return ID;
    }
    
    public void updateID(ObjectID ID){
        this.ID = ID;
        foreground = ID.getDisplayImage();
        drawFrame();
    }
    //use this method to set your own image
    public void drawFrame(){
        mainImage.clear();
        mainImage.drawImage(background, 0, 0);
        int marginY = (background.getHeight() - foreground.getHeight())/2;
        int marginX = (background.getWidth() - foreground.getWidth())/2;
        if(marginY < 0 || marginX < 0){
            double ratio = (double) foreground.getHeight()/foreground.getWidth();
            foreground.scale(background.getWidth(), (int)(background.getWidth() * ratio + 0.5));
            marginY = (background.getHeight() - foreground.getHeight())/2;;
            marginX = (background.getWidth() - foreground.getWidth())/2;
        }
        mainImage.drawImage(foreground, marginX, marginY);
        setImage(mainImage);
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
