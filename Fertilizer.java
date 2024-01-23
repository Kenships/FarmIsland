import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fertilizer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fertilizer extends Item
{
    private boolean disapearWhenEmpty;
    private GreenfootImage fertilizerImage;
    private ObjectID ID;
    private boolean display;
    private int fixedX, fixedY;
    private static int fertilizerSize;
    
    public Fertilizer(ObjectID ID, int amount, boolean disapear){
        fertilizerSize = 5;
        disapearWhenEmpty = disapear;

        Inventory.add(ID, amount);
        this.ID = ID;
        
        fertilizerImage = ID.getSeedID().getDisplayImage();
        display = false;
        setImage(fertilizerImage);
    }

    public Fertilizer(ObjectID ID, int amount){
        this(ID, amount, false);
    }

    public Fertilizer(ObjectID ID){
        this(ID, -1);
    }
    
    public void addedToWorld(){
        fixLocation(getX(),getY());
    }
    
    public void act(){
        if(getWorld()==null){
            return;
        }
        reposition();
        checkMouseAction();
    }
    
    public void reposition(){
        //check if Fertilizer is held in cursor
        if(Cursor.getItem() == this){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if(mouse != null){
                setLocation (mouse.getX(), mouse.getY());
            }
        }
    }
    
    public void checkMouseAction(){
        if(Greenfoot.mousePressed(this)){
            Cursor.pickUp(this);
        }
        if(Greenfoot.mouseClicked(this)){
            //setLocation (fixedX, fixedY);
            Cursor.release();
        }
    }
    
    public void fixLocation(int x, int y){
        fixedX = x;
        fixedY = y;
    }
    
    public void removeOne(){
        fertilizerSize --;
    }
    
    public int getFertilizerSize(){
        return fertilizerSize;
    }
}
