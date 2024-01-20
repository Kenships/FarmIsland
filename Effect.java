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
    public static final int SLIDE = 2;
    
    private GreenfootImage image;
    private int effect;
    private double index, deltaIndex;
    private int width, height;
    private int initX, initY;
    private double ratio;
    private int actCounter = 0;
    private int duration;
    public Effect(int effect, String image){
        this(effect, new GreenfootImage(image));
    }
    public Effect(int effect, GreenfootImage i){
        this(effect, i, 10);
    }
    public Effect(int effect, GreenfootImage i, int duration){
        this(effect, i , duration, 0.1);
    }
    public Effect(int effect, GreenfootImage i, int duration, double speed){
        this.duration = duration;
        this.image = i;
        this.effect = effect;
        
        width = image.getWidth();
        height = image.getHeight();
        ratio = 1.0 * width / height;
        
        setImage(image);
        index = 0;
        deltaIndex = speed;
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
                case SLIDE:
                    slide();
                    break;
            }
        
        
        
        actCounter ++;
    }
    
    public void pulse(){
        GreenfootImage scaled = new GreenfootImage(image);
        if(index <= duration){
            scaled.scale(width + (int) (index * ratio), height + (int) index);
        }
        if(index == 0){
            scaled.scale(width, height);
        }
        
        index += deltaIndex;
        if(index > duration || index < 0){
            deltaIndex *= -1;
        }
        setImage(scaled);
    }
    public void rock(){
        if(index <= duration){
            setLocation(initX + (int)index, initY);
        }
        if(index == 0){
            setLocation(initX, initY);
        }
        
        index += deltaIndex;
        if(index > duration || index < 0){
            deltaIndex *= -1;
        }
    }
    
    public void slide(){
        if(index <= duration){
            enableStaticRotation();
            setRotation(0);
            move(deltaIndex);
            index += deltaIndex;
        }
        else{
            move(deltaIndex);
            getWorld().removeObject(this);
        }
    }
}
