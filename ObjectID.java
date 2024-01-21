import greenfoot.*;
/**
 * Write a description of class ObjectID here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum ObjectID  
{
    NONE, DIRT_TILE, LANDPLOT, PORCUS_WHEAT, PORCUS_WHEAT_SEED, WHEAT, WHEAT_SEED, CARROT, CARROT_SEED,
    BASIC_TOOL, DIAMOND_TOOL, FERTILIZER;
    
    public ObjectID getSeedID(){
        switch(this){
            case WHEAT:
                return WHEAT_SEED;
            case PORCUS_WHEAT:
                return PORCUS_WHEAT_SEED; 
            case CARROT:
                return CARROT_SEED;
            case FERTILIZER:
                return FERTILIZER;
        }
        return WHEAT_SEED;
    }
    
    public GreenfootImage getDisplayImage(){
        return new GreenfootImage("Displays/Items/" + this.toString() + ".png");
    }
    
    public GreenfootImage getToolImage(){
        return new GreenfootImage("Tools/" + this.toString() + ".png");
    }
}
