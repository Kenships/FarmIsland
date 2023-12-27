import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Button extends Actor
{
    protected GreenfootImage[] mainImage;
    protected GreenfootImage[] hoverImage;
    protected GreenfootImage[] clickImage;
    protected String text;
    protected int width, height;
    private boolean clicked;
    public Button(String text, int width, int height){
        this.text = text;
        this.width = width;
        this.height = height;
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
