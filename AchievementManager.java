import java.util.ArrayList;
/**
 * Write a description of class AchievementManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AchievementManager
{
    public static final String AGRICULTURE_A = "AgricultureA";
    public static int totalPlants;
    public static int totalTiles;
    public static ObjectID currentPlant;
    public static ArrayList<Achievement> agricultureA, agricultureB;
    
    public AchievementManager(){
        totalTiles = 0;
        totalPlants = 0;
        initializeAgricultureA();
        initializeAgricultureB();
    }
    
    private void initializeAgricultureA(){
        agricultureA = new ArrayList<>();
        agricultureA.add(new Achievement("Green Begginings", "Plant 1 seed!", AGRICULTURE_A, 0));
        agricultureA.add(new Achievement("Novice Planter", "Plant 10 seeds!", AGRICULTURE_A, 1));
        agricultureA.add(new Achievement("Veteran Planter", "Plant 100 seeds!", AGRICULTURE_A, 2));
        agricultureA.add(new Achievement("Planting Master", "Plant 1000 seeds!", AGRICULTURE_A, 3));
    }
    
    
    private void initializeAgricultureB(){
        agricultureB = new ArrayList<>();
        agricultureB.add(new Achievement("Looking Stubby", "Unlock Stubby Wheat!", "AgricultureB",0));
    }
    
    public static void updateTotalPlant(){
        totalPlants ++;
        switch(totalPlants){
            case 1:
                agricultureA.get(0).setCompleted();
                break;
            case 10:
                agricultureA.get(1).setCompleted();
                break;
            case 100:
                agricultureA.get(2).setCompleted();
                break;
            case 1000:
                agricultureA.get(3).setCompleted();
        }
    }
    
    // WORK IN PROGRESS
    
    /*
    public static void updateLatestPlant(ObjectID id){
        System.out.println("Helooooooooo");
        if(id.getSeedID().equals(ObjectID.STUBBY_WHEAT_SEED) && NEXT_PLANT.equals(ObjectID.STUBBY_WHEAT)){
            agricultureB.get(0).setCompleted();
            System.out.println("Completed 1");
        }
    }
    */
    
}