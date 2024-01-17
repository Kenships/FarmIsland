import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CollectionHandler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CollectionHandler extends SuperSmoothMover
{
    public static World w;
    public void act()
    {
        // Add your action code here.
    }

    public static void collect(Plant plant){
        if(Cursor.getItem() != null && Cursor.getItem() instanceof Tool){
            int netYield = plant.getYield();
            Inventory.add(plant.getID(), netYield);
            CurrencyHandler.deposit(netYield * 2);
            plant.getTile().unPlant();
            w.removeObject(plant);
        }
    }
}
