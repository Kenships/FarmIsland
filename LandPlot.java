import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Stores all DirtTiles in play
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LandPlot extends Actor
{
    //where the first tile is spawned
    public static final int STARTING_ROW = 15;
    public static final int STARTING_COL = 15;
    
    
    public static final int MARGIN = 128;
    public static final int WIDTH = GameWorld.SCREEN_WIDTH - 2 * MARGIN;
    public static final int HEIGHT = GameWorld.SCREEN_HEIGHT - 2 * MARGIN;;
    public static final Color BABY_BLUE = new Color(137, 207, 240);
    
    private ObjectID ID = ObjectID.LANDPLOT;
    
    private GreenfootImage myImage;
    private DirtTile[][] plot;

    private MouseInfo lastMouseInfo;
    private boolean isDragging;
    public LandPlot(){

        initialize();
    }

    public void addedToWorld (World w){
        startPlot();
    }
    //credit: ChatGPT
    public void act()
    {
        moveAndDrag(); 
    }
    
    public void initialize(){
        //sets a temporary image for the "moveable screen"
        myImage = new GreenfootImage(GameWorld.SCREEN_WIDTH - 2 * MARGIN, GameWorld.SCREEN_HEIGHT - 2 * MARGIN);
        myImage.setColor(BABY_BLUE);
        myImage.fill();
        setImage(myImage);
        
        //puts many objects on screen
        
        
        //initialize the plot
        plot = new DirtTile[32][32];

    }
    
    //credit: ChatGPT for the idea to store mouse info
    //used to check when mouse is dragging and calculates the movement per act and applies it to each tile
    public void moveAndDrag(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            if (Greenfoot.mousePressed(null) && mouse.getButton() == 3) {
                // Begin drag
                lastMouseInfo = mouse;
                isDragging = true;
            } else if (Greenfoot.mouseDragged(null) && isDragging) {
                // Calculate the delta
                int deltaX = mouse.getX() - lastMouseInfo.getX();
                int deltaY = mouse.getY() - lastMouseInfo.getY();

                // Move each object
                for (Tile obj : getWorld().getObjects(DirtTile.class)) {
                    Tile object = obj;
                    object.setLocation(object.getX() + deltaX, object.getY() + deltaY);
                }

                lastMouseInfo = mouse; // Update last mouse info at the end of drag
            } else if (Greenfoot.mouseDragEnded(null)) {
                // End drag
                isDragging = false;
            }
        }
    }
    //check if mouse is hovering over the active plot
    public boolean hoveringThis(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){

            int leftBound = getX() - WIDTH/2;
            int rightBound = getX() + WIDTH/2;
            int topBound = getY() - HEIGHT/2;
            int bottomBound = getY() + HEIGHT/2;
            return mouse.getX() < rightBound && mouse.getX() > leftBound && mouse.getY() < bottomBound && mouse.getY() > topBound;
        }
        return false;
    }
    
    //begins the plot at the center
    public void startPlot(){
        //adds the central plot to the world
        plot[STARTING_ROW][STARTING_COL] = new DirtTile(this, STARTING_ROW, STARTING_COL, true);
        getWorld().addObject(plot[STARTING_ROW][STARTING_COL], getX(), getY());
    }
    
    //creates a new tile at row/col that is "projected"
    public DirtTile createTile(int row, int col){
        if(row >= 0 && col >= 0 && row < plot.length && col < plot.length && plot[row][col] == null){
            plot[row][col] = new DirtTile(this, row, col, false);
            return plot[row][col];
        }
        return null;
    }
    //gets tile at row/col
    public DirtTile getTile(int row, int col){
        return plot[row][col];
    }
    //removes only from the matrix
    public void removeFromPlot(int row, int col){
        plot[row][col] = null;        
    }
    
    //removes an in the matrix and world if it exists and is active
    public void removeTile(int row, int col){
        DirtTile remove = plot[row][col];
        
        if(remove != null && remove.getWorld() != null && remove.isActive()){
            remove.stopProjection();
            getWorld().removeObject(remove);
            removeFromPlot(row,col);
        }
    }
    /**
     * NEW METHOD
     */
    public void zSort(){
        ArrayList<Actor> actors = new ArrayList<>();
        for(int row = 0; row < plot.length; row++){
            for(int col = 0; col < plot[row].length; col++){
                if(plot[row][col] != null){
                    DirtTile temp = plot[row][col];
                    actors.add(temp);
                    if(temp.getPlant() != null){
                        actors.add(temp.getPlant());
                    }
                }
            }
        }
        Util.zSort(actors, getWorld());
    }
}
