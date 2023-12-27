import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class for tools that the user can use.
 * 
 * @author (your name) 
 * @version December 2023
 */
public abstract class Tools extends Actor
{
    protected String name;
    protected int durability;
    protected int efficiency;
    protected String type;
    
    /**
     * Gets the name of the tool.
     * 
     * @return String - Name of the tool
     */
    public String getName()
    {
        return name;
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
        // Add your action code here.
    }
}