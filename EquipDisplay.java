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
    public static final int SPACING = 68;
    private GreenfootImage background;
    private EquipFrame toolFrame;
    private EquipFrame seedFrame;
    private EquipFrame fertalizerFrame;
    private Tool tool;
    private Seed seed;
    public EquipDisplay()
    {
        toolFrame = new EquipFrame(ObjectID.NONE, 64, 64);
        seedFrame = new EquipFrame(ObjectID.NONE, 64, 64);
        fertalizerFrame = new EquipFrame(ObjectID.NONE, 64, 64);
    }
    
    public void addedToWorld(World w){
        showDisplay();
        //fill in later
    }
    
    public void act(){
        checkMouseAction();
        toolFrame.setLocation(getX() - SPACING, getY());
        seedFrame.setLocation(getX(), getY());
        fertalizerFrame.setLocation(getX() + SPACING, getY()); 
    }
    
    public void checkMouseAction(){
        if(toolFrame.hoveringThis() && Cursor.leftClicked()){
            Cursor.setTool(tool);
            Cursor.release();
            toolFrame.select();
            seedFrame.unselect();
            fertalizerFrame.unselect();
        }
        if(seedFrame.hoveringThis() && Cursor.leftClicked()){
            Cursor.setTool(null);
            Cursor.pickUp(seed);
            seedFrame.select();
            toolFrame.unselect();
            fertalizerFrame.unselect();
        }
        if(fertalizerFrame.hoveringThis() && Cursor.leftClicked()){
            Cursor.setTool(null);
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
