import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class for tools that the user can use.
 * 
 * @author (your name) 
 * @version December 2023
 */
public abstract class Tools extends SuperSmoothMover
{
    protected ObjectID ID;
    protected int durability;
    protected int efficiency;
    protected String type;
    protected boolean unbreakable;
    protected GreenfootImage toolImage;
    /**
     * Gets the name of the tool.
     * 
     * @return ObjectID - ID of the tool
     */
    public ObjectID getID()
    {
        return ID;
    }
    
    /**
     * Gets the efficency of the tool.
     * 
     * @return int - The efficency of the tool.
     */
    public int getEffiency()
    {
        return efficiency;
    }
    
    /**
     * Takes away from the durability of the tool.
     */
    public void takeDurability()
    {
        if(unbreakable){
            return;
        }
        durability --;
    }
    
    /**
     * Effect that the tool has.
     */
    public abstract void effect();
    
    /**
     * Act - do whatever the Tools wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(getWorld() == null){
            return;
        }
        
        if(Cursor.getTool() == this && Cursor.getMouseInfo() != null){
            setLocation(Cursor.getX(), Cursor.getY());
        }
    }
}