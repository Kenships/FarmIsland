import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EquipFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EquipFrame extends ItemFrame
{
    /**
     * Act - do whatever the EquipFrame wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public static final Color HIGHLIGHT_COLOR = new Color(190, 149, 92);
    public static final int MARGIN = 4;
    private GreenfootImage highlight;
    private Tool tool;
    private boolean selected;
    public EquipFrame(ObjectID ID, int width, int height){
        super(ID, width, height);
        mainImage = new GreenfootImage(width, height);
        background = new GreenfootImage("Displays/Frames/Wooden Frame.png");
        background.scale(width, height);
        updateID(ID);   
        updateHighlight();
    }
    public void act()
    {
        checkMouseAction();
        
    }
    public void checkMouseAction(){
        if(hoveringThis() || selected){
            setImage(highlight);
        }
        else{
            setImage(mainImage);
        }
    }
    
    public void select(){
        selected = true;
    }
    
    public void unselect(){
        selected = false;
    }
    
    public void updateHighlight(){
        highlight = new GreenfootImage(mainImage.getWidth() + 2 * MARGIN, mainImage.getHeight() + 2 * MARGIN);
        highlight.setColor(HIGHLIGHT_COLOR);
        highlight.fill();
        highlight.drawImage(mainImage, MARGIN, MARGIN);
    }
    public void updateID(ObjectID ID){
        super.updateID(ID);
        updateHighlight();
    }
}
