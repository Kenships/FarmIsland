import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Effect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Effect extends SuperSmoothMover
{
    /**
     * NEW: everything
     */
    public static final int PULSE = 0;
    public static final int ROCK = 1;
    
    private GreenfootImage image;
    private int effect;
    private double index, deltaIndex;
    private int width, height;
    private int initX, initY;
    private double ratio;
    private int actCounter = 0;
    public Effect(int effect, String image){
        this(effect, new GreenfootImage(image));
    }
    public Effect(int effect, GreenfootImage i){
        this.image = i;
        this.effect = effect;
        
        width = image.getWidth();
        height = image.getHeight();
        ratio = 1.0 * width / height;
        
        setImage(image);
        index = 0;
        deltaIndex = 0.1;
    }
    public void addedToWorld(World w){
        initX = getX();
        initY = getY();
    }
    public void act()
    {
        
            switch(effect){
                case PULSE:
                    pulse();
                    break;
                case ROCK:
                    rock();
                    break;    
            }
        
        
        
        actCounter ++;
    }
    
    public void pulse(){
        GreenfootImage scaled = new GreenfootImage(image);
        if(index <= 10){
            scaled.scale(width + (int) (index * ratio), height + (int) index);
        }
        if(index == 0){
            scaled.scale(width, height);
        }
        
        index += deltaIndex;
        if(index > 10 || index < 0){
            deltaIndex *= -1;
        }
        setImage(scaled);
    }
    public void rock(){
        if(index <= 10){
            setLocation(initX + (int)index, initY);
        }
        if(index == 0){
            setLocation(initX, initY);
        }
        
        index += deltaIndex;
        if(index > 10 || index < 0){
            deltaIndex *= -1;
        }
    }
}
