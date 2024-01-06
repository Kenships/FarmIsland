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
        switch(ID){
            case BASIC_TOOL:
                toolImage = ID.getToolImage(this.getClass().getName());
                efficiency = 1;
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