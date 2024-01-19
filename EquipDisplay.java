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
    private EquipFrame fertalizerFrame;
    private Tool tool;
    private Seed seed;
    private GameWorld w;
    public EquipDisplay()
    {
        toolFrame = new EquipFrame(ObjectID.NONE, 86, 86);
        seedFrame = new EquipFrame(ObjectID.NONE, 86, 86);
        fertalizerFrame = new EquipFrame(ObjectID.NONE, 86, 86);
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
        fertalizerFrame.setLocation(getX() + SPACING, getY()); 
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
            fertalizerFrame.unselect();
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
            fertalizerFrame.unselect();
        }
        if(fertalizerFrame.hoveringThis() && Cursor.leftClicked()){
            if(tool.getWorld() != null){
                getWorld().removeObject(tool);
            }
            if(seed.getWorld() != null){
                getWorld().removeObject(seed);
            }
            Cursor.release();
            fertalizerFrame.select();
            seedFrame.unselect();
            toolFrame.unselect();
        }
    }

    public void showDisplay(){
        World w = getWorld();
        w.addObject(toolFrame,getX() - SPACING, getY());
        if(tool != null){
            w.addObject(tool,getX() - SPACING, getY());
        }
        w.addObject(seedFrame, getX(), getY());
        if(seed != null){
            w.addObject(seed, getX(), getY());
        }
        w.addObject(fertalizerFrame, getX() + SPACING, getY());
    }

    public void hideDisplay(){
        getWorld().removeObject(toolFrame);
        getWorld().removeObject(seedFrame);
        getWorld().removeObject(fertalizerFrame);
        getWorld().removeObject(tool);
        getWorld().removeObject(seed);
    }

    public void equipTool(Tool tool){
        this.tool = tool;
        toolFrame.updateID(tool.getID());
    }

    public void equipSeed(Seed seed){
        this.seed = seed;
        seedFrame.updateID(seed.getID());
    }

    public void equipItem(){

    }
}
