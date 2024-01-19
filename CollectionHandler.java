import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CollectionHandler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CollectionHandler extends SuperSmoothMover
{
    private static GameWorld w;
    
    public static void initialize(GameWorld world){
        w = world;
    }
    public void act()
    {
        // Add your action code here.
    }

    public static void collect(Plant plant){
        if(Cursor.getItem() != null && Cursor.getItem() instanceof Tool){
            Tool tool = (Tool) Cursor.getItem();
            int multiplier = 1 + tool.getEffiency()/10;
            int netYield = plant.getYield();
            if(Greenfoot.getRandomNumber(100) < tool.getEffiency()){
                netYield *= multiplier;
            }
            
            Inventory.add(plant.getID(), netYield);
            CurrencyHandler.deposit(netYield * 2); 
            w.removeObject(plant);
            plant.getTile().unPlant();
            
        }
    }
}
