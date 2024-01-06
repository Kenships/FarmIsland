import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Button extends SuperSmoothMover
{
    protected GreenfootImage[] mainImage;
    protected GreenfootImage[] hoverImage;
    protected GreenfootImage[] clickImage;
    protected String imageName;
    protected int width, height;
    private boolean clicked;
    public Button(String imageName){
        this.imageName = imageName;
        mainImage = new GreenfootImage[1];
        hoverImage = new GreenfootImage[1];
        clickImage = new GreenfootImage[1];

        clickImage[0] = new GreenfootImage("Buttons/" + imageName + " 1.png");        
        hoverImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        mainImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");
        setImage(mainImage[0]);
        width = mainImage[0].getWidth();
        height = mainImage[0].getHeight();
    }
    public void addedToWorld(World w){
        clicked = false;
    }
    public void act()
    {
        //mouse pressed + mouse release combo to check for hold
        if(Greenfoot.mousePressed(this)){
            click();
            clicked = true;
        }
        if(Greenfoot.mouseClicked(null) && clicked){
            release();
            clicked = false;
        }
        //hovering animation
        if(!clicked){
            if(hoveringThis()){
                hover();
            }
            else{
                release();
            }
        }
    }

    public abstract void hover();

    public abstract void click();

    public abstract void release();

    public abstract boolean hoveringThis();
}
