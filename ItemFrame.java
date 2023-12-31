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
    public ItemFrame(ObjectID ID){
        this(ID, 128,128);
        
    }
    
    public ItemFrame(ObjectID ID, int width, int height){
        if(ID != ObjectID.NONE){
            this.ID = ID;
            String s = ID.toString();
            foreground = ID.getDisplayImage(); 
        }
        else{
            foreground = new GreenfootImage(1,1);
        }
        mainImage = new GreenfootImage(width, height);
        background = new GreenfootImage("Frame.png");
        background.scale(width, height);
        drawFrame();
        
    }
    
    public ObjectID getID(){
        return ID;
    }
    
    public void updateID(ObjectID ID){
        foreground = ID.getDisplayImage();
        drawFrame();
    }
    //use this method to set your own image
    public void drawFrame(){
        mainImage.drawImage(background, 0, 0);
        int marginY = (background.getHeight() - foreground.getHeight())/2;
        int marginX = (background.getWidth() - foreground.getWidth())/2;
        if(marginY < 0 || marginX < 0){
            foreground.scale(background.getWidth(), background.getHeight());
            marginY = 0;
            marginX = 0;
        }
        mainImage.drawImage(foreground, marginX, marginY);
        setImage(mainImage);
    }
}
