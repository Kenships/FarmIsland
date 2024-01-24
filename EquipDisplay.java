import greenfoot.*;

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
    
    private EquipFrame lastSelected;
    
    private GreenfootSound[] clickSound;
    private int soundIndex;
    public static int gameVolumeMax = 100; 
    public EquipDisplay(GameWorld w)
    {

        this.w = w;
        toolFrame = new EquipFrame(ObjectID.NONE, 86, 86);
        seedFrame = new EquipFrame(ObjectID.NONE, 86, 86);
        fertilizerFrame = new EquipFrame(ObjectID.NONE, 86, 86);

        clickSound = new GreenfootSound[6];
        for(int i = 0; i < clickSound.length; i++){
            clickSound[i] = new GreenfootSound("Clickmp3.mp3");
            clickSound[i].setVolume(gameVolumeMax);
        }
    }

    public void addedToWorld(World w){
        showDisplay();
        //fill in later
    }

    public void act(){
        if(w.getScreen() == GameWorld.GAME && !w.getEditMode()){
            checkMouseAction();
            checkSelection();
        }

        toolFrame.setLocation(getX() - SPACING, getY());
        seedFrame.setLocation(getX(), getY());
        fertilizerFrame.setLocation(getX() + SPACING, getY()); 
    }
    
    public void deselectAll(){
        seedFrame.unselect();
        fertilizerFrame.unselect();
        toolFrame.unselect();
        checkSelection();
    }
    
    public void selectPrevious(){
        if(lastSelected != null){
            lastSelected.select();
        }
        
    }
    
    public void checkSelection(){
        if(toolFrame.isSelected()){
            lastSelected = toolFrame;
            seedFrame.unselect();
            fertilizerFrame.unselect();
            if(tool != null && tool.getWorld() == null){
                w.addObject(tool, Cursor.getX(), Cursor.getY());
            }
        }
        else{
            if(tool != null && tool.getWorld() != null){
                w.removeObject(tool);
            }
        }
        if(seedFrame.isSelected()){
            lastSelected = seedFrame;
            fertilizerFrame.unselect();
            toolFrame.unselect();
            if(seed != null && seed.getWorld() == null){
                w.addObject(seed, Cursor.getX(), Cursor.getY());
            }
        }
        else{
            if(seed != null && seed.getWorld() != null){
                w.removeObject(seed);
            }
        }
        if(fertilizerFrame.isSelected()){
            lastSelected = fertilizerFrame;
            toolFrame.unselect();
            seedFrame.unselect();
            if(fertilizer != null && fertilizer.getWorld() == null){
                w.addObject(fertilizer, Cursor.getX(), Cursor.getY());
            }
        }
        else{
            if(fertilizer != null && fertilizer.getWorld() != null){
                w.removeObject(fertilizer);
            }
        }
    }

    public void checkMouseAction(){
        if(toolFrame.hoveringThis() && Greenfoot.mousePressed(null) && Cursor.leftClicked()){
            clickSound();
            if(clickedItself(toolFrame)){
                toolFrame.unselect();
            }
            else{
                toolFrame.select();
                Cursor.pickUp(tool);
                seedFrame.unselect();
                fertilizerFrame.unselect();
            }

        }
        if(seedFrame.hoveringThis() && Greenfoot.mousePressed(null) && Cursor.leftClicked()){
            clickSound();
            if(clickedItself(seedFrame)){
                seedFrame.unselect();
            }
            else{
                Cursor.pickUp(seed);
                seedFrame.select();
                toolFrame.unselect();
                fertilizerFrame.unselect();
            }

        }
        if(fertilizerFrame.hoveringThis() && Greenfoot.mousePressed(null) && Cursor.leftClicked()){
            clickSound();
            if(clickedItself(fertilizerFrame)){
                fertilizerFrame.unselect();
            }
            else{
                Cursor.release();
                fertilizerFrame.select();
                seedFrame.unselect();
                toolFrame.unselect();                
            }

        }

    }

    public boolean clickedItself(EquipFrame frame){
        if(frame.isSelected()){
            return true;
        }
        return false;
    }

    public void showDisplay(){
        w.addObject(toolFrame,getX() - SPACING, getY());
        w.addObject(seedFrame, getX(), getY());
        w.addObject(fertilizerFrame, getX() + SPACING, getY());
    }

    public void hideDisplay(){
        w.removeObject(toolFrame);
        w.removeObject(seedFrame);
        w.removeObject(fertilizerFrame);
        w.removeObject(tool);
        w.removeObject(seed);
    }

    public void equipTool(Tool tool){
        if(this.tool != null && this.tool.getWorld() != null){
            w.removeObject(this.tool);
        }
        Cursor.pickUp(tool);
        this.tool = tool;
        toolFrame.updateID(tool.getID());
        toolFrame.select();
    }

    public void equipSeed(Seed seed){
        if(this.seed != null && this.seed.getWorld() != null){
            w.removeObject(this.seed);
        }
        this.seed = seed;
        Cursor.pickUp(seed);
        seedFrame.updateID(seed.getID());
        seedFrame.select();
    }

    public void equipFertilizer(Fertilizer fertilizer){
        if(this.fertilizer != null && this.fertilizer.getWorld() != null){
            w.removeObject(this.fertilizer);
        }
        Cursor.pickUp(fertilizer);
        this.fertilizer = fertilizer;

        fertilizerFrame.updateID(fertilizer.getID());
        fertilizerFrame.select();                                                             
    }

    public void unEquipTool(){
        if(this.tool != null && this.tool.getWorld() != null){
            getWorld().removeObject(this.tool);
        }

        this.tool = null;
        toolFrame.updateID(ObjectID.NONE);
        toolFrame.unselect();
    }

    public void unEquipSeed(){
        if(this.seed != null && this.seed.getWorld() != null){
            w.removeObject(this.seed);
        }
        this.seed = null;
        seedFrame.updateID(ObjectID.NONE);
        seedFrame.unselect();
    }

    public void unEquipFertilizer(){
        if(this.fertilizer != null && this.fertilizer.getWorld() != null){
            w.removeObject(this.fertilizer);
        }

        this.fertilizer = null;
        fertilizerFrame.updateID(ObjectID.NONE);
        fertilizerFrame.unselect();
    }

    public void unEquipItem(ObjectID ID){
        switch(ID){
            case PORCUS_WHEAT_SEED:
            case WHEAT_SEED:
            case CARROT_SEED:
            case TOMATO_SEED:
            case SILVER_TOMATO_SEED:
            case GOLDEN_TOMATO_SEED:
            case STRAWBERRY_SEED:
            case BLUEBERRY_SEED:
            case DRAGONFRUIT_SEED:
                unEquipSeed();
                break;
            case DIAMOND_TOOL: 
            case BASIC_TOOL:
            case SHOVEL:    
                unEquipTool();
                break;
            case FERTILIZER:
                unEquipFertilizer();
                break;    
        }
    }

    public void equipItem(ObjectID ID){
        switch(ID){
            case PORCUS_WHEAT_SEED:
            case WHEAT_SEED:
            case CARROT_SEED:
            case TOMATO_SEED:
            case BLUEBERRY_SEED:
            case STRAWBERRY_SEED:
            case DRAGONFRUIT_SEED:
            case SILVER_TOMATO_SEED: 
            case GOLDEN_TOMATO_SEED:
                equipSeed(new Seed(ID));
                break;
            case DIAMOND_TOOL: 
            case BASIC_TOOL:
            case SHOVEL:
                equipTool(new Tool(ID));
                break;
            case FERTILIZER:
                equipFertilizer(new Fertilizer(ID));
                break;    
        }
    }

    public void clickSound()
    {
        clickSound[soundIndex].play();
        soundIndex++;
        if (soundIndex == clickSound.length){
            soundIndex = 0;
        }
    }
    
    public static void setVolumeMax(int newMax)
    {
        gameVolumeMax = newMax;
    }
    public static int getVolumeMax()
    {
        return gameVolumeMax;
    }
}
