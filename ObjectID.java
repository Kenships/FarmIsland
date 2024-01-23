import greenfoot.*;
/**
 * Write a description of class ObjectID here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum ObjectID  
{
    NONE, DIRT_TILE, LANDPLOT, PORCUS_WHEAT, PORCUS_WHEAT_SEED, WHEAT, WHEAT_SEED, CARROT, CARROT_SEED, TOMATO, TOMATO_SEED, BLUEBERRY, BLUEBERRY_SEED,
    DRAGONFRUIT, DRAGONFRUIT_SEED, STRAWBERRY, STRAWBERRY_SEED, GOLDEN_TOMATO, GOLDEN_TOMATO_SEED, SILVER_TOMATO, SILVER_TOMATO_SEED, 
    BASIC_TOOL, DIAMOND_TOOL, SHOVEL, FERTILIZER;
    
    public ObjectID getSeedID(){
        switch(this){
            case WHEAT:
                return WHEAT_SEED;
            case PORCUS_WHEAT:
                return PORCUS_WHEAT_SEED; 
            case CARROT:
                return CARROT_SEED;
            case TOMATO:
                return TOMATO_SEED;
            case SILVER_TOMATO:
                return SILVER_TOMATO_SEED;
            case GOLDEN_TOMATO:
                return GOLDEN_TOMATO_SEED;
            case BLUEBERRY:
                return BLUEBERRY_SEED;
            case DRAGONFRUIT:
                return DRAGONFRUIT_SEED;
            case STRAWBERRY: 
                return STRAWBERRY_SEED;
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
