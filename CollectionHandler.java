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
    private static GreenfootSound[] harvestingSound;
    private static int soundIndex;

    public static void initialize(GameWorld world){
        w = world;
        harvestingSound = new GreenfootSound[20];
        for (int i = 0; i < harvestingSound.length; i++){
            harvestingSound[i] = new GreenfootSound ("HarvestingSound.wav");
            harvestingSound[i].setVolume(80);
        }
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

            if(tool.getID() == ObjectID.SHOVEL){
                harvestSound();
                shovel(plant);
                return;
            }
            Inventory.add(plant.getID(), netYield);
            CurrencyHandler.deposit(netYield * plant.getSellPrice()); 
            harvestSound();
            if(shovel(plant)){
                return;
            }
            switch(plant.getID()){
                case TOMATO:
                case BLUEBERRY:
                case SILVER_TOMATO:
                case GOLDEN_TOMATO:
                case DRAGONFRUIT:
                case STRAWBERRY:
                    plant.setGrowthStage(plant.getGrowthStage() - 1);
                    break;
                case PORCUS_WHEAT:
                    plant.setGrowthStage(0);
                    break;
                default:
                    w.removeObject(plant);
                    plant.getTile().unPlant();
            }
    
        }
    }

    public static boolean shovel(Plant plant){
        if(Cursor.getItem() != null && Cursor.getItem() instanceof Tool){
            Tool tool = (Tool) Cursor.getItem();            
            if(tool.getID() == ObjectID.SHOVEL){
                Inventory.add(plant.getID().getSeedID(), 1);
                w.removeObject(plant);
                plant.getTile().unPlant();
                return true;
            }
        }
        return false;
    }

    public static void harvestSound(){
        harvestingSound[soundIndex].play();
        soundIndex++;
        if(soundIndex == harvestingSound.length)
        {
            soundIndex=0;
        }   
    }
}
