import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * Write a description of class EquipDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EquipDisplay extends SuperSmoothMover 
{
    public static final int SPACING = 90;
    private GreenfootImage background;
    private EquipFrame toolFrame;
    private EquipFrame seedFrame;
    private EquipFrame fertilizerFrame;
    private Tool tool;
    private Seed seed;
    private Fertilizer fertilizer;
    private GameWorld w;
    
    public EquipDisplay()
    {
        toolFrame = new EquipFrame(ObjectID.NONE, 86, 86);
        seedFrame = new EquipFrame(ObjectID.NONE, 86, 86);
        fertilizerFrame = new EquipFrame(ObjectID.NONE, 86, 86);
    }

    public void addedToWorld(World w){
        this.w = (GameWorld) w;
        showDisplay();
        //fill in later
    }

    public void act(){
        if(w.getScreen() == GameWorld.GAME){
            checkMouseAction();
        }

        toolFrame.setLocation(getX() - SPACING, getY());
        seedFrame.setLocation(getX(), getY());
        fertilizerFrame.setLocation(getX() + SPACING, getY()); 
    }

    public void checkMouseAction(){
        if(toolFrame.hoveringThis() && Cursor.leftClicked()){
            if(tool != null){
                getWorld().addObject(tool,getX() - SPACING, getY());
            }
            if(seed.getWorld() != null){
                getWorld().removeObject(seed);
            }
            toolFrame.select();
            Cursor.pickUp(tool);
            seedFrame.unselect();
            fertilizerFrame.unselect();
        }
        if(seedFrame.hoveringThis() && Cursor.leftClicked()){
            if(seed != null){
                getWorld().addObject(seed ,getX() - SPACING, getY());
            }
            if(tool.getWorld() != null){
                getWorld().removeObject(tool);
            }
            Cursor.pickUp(seed);
            seedFrame.select();
            toolFrame.unselect();
            fertilizerFrame.unselect();
        }
        if(fertilizerFrame.hoveringThis() && Cursor.leftClicked()){
            
            if(tool.getWorld() != null){
                getWorld().removeObject(tool);
            }
            if(seed.getWorld() != null){
                getWorld().removeObject(seed);
            }
            Cursor.release();
            fertilizerFrame.select();
            seedFrame.unselect();
            toolFrame.unselect();
        }
    }

    public void showDisplay(){
        World w = getWorld();
        w.addObject(toolFrame,getX() - SPACING, getY());
        w.addObject(seedFrame, getX(), getY());
        w.addObject(fertilizerFrame, getX() + SPACING, getY());
    }

    public void hideDisplay(){
        getWorld().removeObject(toolFrame);
        getWorld().removeObject(seedFrame);
        getWorld().removeObject(fertilizerFrame);
        getWorld().removeObject(tool);
        getWorld().removeObject(seed);
    }

    public void equipTool(Tool tool){
        if(tool.getWorld() != null){
            getWorld().removeObject(tool);
        }

        this.tool = tool;
        toolFrame.updateID(tool.getID());
    }

    public void equipSeed(Seed seed){
        if(seed.getWorld() != null){
            getWorld().removeObject(seed);
        }
        this.seed = seed;
        seedFrame.updateID(seed.getID());
    }
    
    public void equipFertilizer(Fertilizer fertilizer){
        if(fertilizer.getWorld() != null){
            getWorld().removeObject(fertilizer);
        }

        this.fertilizer = fertilizer;
        fertilizerFrame.updateID(fertilizer.getID());
    }
    
    public void equipItem(ObjectID ID){
        switch(ID){
            case STUBBY_WHEAT_SEED:
            case WHEAT_SEED:
            case CARROT_SEED:
                equipSeed(new Seed(ID));
                break;
            case DIAMOND_TOOL: 
            case BASIC_TOOL:
                equipTool(new Tool(ID));
                break;
            case FERTILIZER:
                
                break;    
        }
    }
}
