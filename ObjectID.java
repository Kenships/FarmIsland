import greenfoot.*;
/**
 * Write a description of class ObjectID here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum ObjectID  
{
    NONE, DIRT_TILE, LANDPLOT, STUBBY_WHEAT, STUBBY_WHEAT_SEED, WHEAT, WHEAT_SEED;
    
    public ObjectID getSeedID(){
        switch(this){
            case WHEAT:
                return WHEAT_SEED;
            case STUBBY_WHEAT:
                return STUBBY_WHEAT_SEED; 
        }
        return WHEAT_SEED;
    }
    
    public GreenfootImage getDisplayImage(){
        return new GreenfootImage("Displays/Items/" + this.toString() + ".png");
    }
}
