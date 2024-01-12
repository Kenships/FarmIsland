import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class for common tools.
 * 
 * @author (your name)  
 * @version December 2023
 */
public class Common extends Tools
{
    /**
     * @param ID the object ID of the desired tool
     */
    public Common(ObjectID ID)
    {
        toolImage = ID.getToolImage(this.getClass().getName());
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
    public Common(){
        
        
    }
    public void effect()
    {
        
    }
    
    public void act(){
        super.act();
    }
}