import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class for tools that the user can use.
 * 
 * @author (your name) 
 * @version December 2023
 */
public class Tool extends Item
{
    private int durability;
    private int efficiency;
    private boolean unbreakable;
    private GreenfootImage toolImage;
    private int rarity;
    public Tool(ObjectID ID){
        this.ID = ID;
        toolImage = ID.getToolImage();
        switch(ID){
            case BASIC_TOOL:
                efficiency = 1;
                unbreakable = true;
                break;
            case DIAMOND_TOOL:
                efficiency = 10;
                unbreakable = true;
                break;
        }
        setImage(toolImage);
    }
    
    public void addedToWorld(World w){
        super.addedToWorld(w);
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
    public void activateEffect(){
        //place effects here
        switch(ID){
            
        }
    }
    
    /**
     * Act - do whatever the Tools wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(getWorld() == null){
            return;
        }
        reposition();
    }
}