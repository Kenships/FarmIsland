import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Stack;
import java.util.PriorityQueue;
/**
 * Stores all DirtTiles in play
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LandPlot extends SuperSmoothMover
{
    //where the first tile is spawned
    public static final int STARTING_ROW = 15;
    public static final int STARTING_COL = 15;
    public static final int GRID_ROWS = 32;
    public static final int GRID_COLS = 32;
    /**
     * NEW 
     */
    public static final int[][] DIRECTIONS = {{-1,0},{1,0},{0,1},{0,-1}};

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
        //fillTiles();
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
        plot = new DirtTile[GRID_ROWS][GRID_COLS];

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
        /**
         * NEW: remove from plot
         */
        if(row < 0 || col < 0 || row >= plot.length || col >= plot[row].length){
            return;
        }

        DirtTile remove = plot[row][col];
        if(remove == plot[STARTING_ROW][STARTING_COL]){
            //cannot remove starting tile
            return;
        }
        if(remove != null){
            if(remove.isActive()){
                Inventory.add(remove.getID());
                remove.stopProjection();
            }
            if(remove.getWorld() != null){
                getWorld().removeObject(remove);
            }
            plot[row][col] = null;  
        }

    }

    //removes an in the matrix and world if it exists and is active
    public void removeTile(int row, int col){
        DirtTile remove = plot[row][col];

        if(remove != null && remove.getWorld() != null && remove.isActive()){
            removeFromPlot(row,col);

            for (int i = 0; i < 4; i++){
                int newRow = row + DIRECTIONS[i][0];
                int newCol = col + DIRECTIONS[i][1];
                if(newRow < 0 || newCol < 0 || newRow >= plot.length || newCol >= plot[row].length){
                    continue;
                }
                if (plot[newRow][newCol] != null && plot[newRow][newCol].isActive() && !getPath(plot[newRow][newCol], plot[STARTING_ROW][STARTING_COL])){
                    removeFromPlot(newRow,newCol);
                    removeTiles(newRow,newCol);
                }
            }
        }
    }

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

    public GridPath[][] initMatrix(DirtTile destination){
        GridPath[][] matrix = new GridPath[plot.length][plot[0].length];
        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[0].length; col++){
                matrix[row][col] = new GridPath(row, col);
                matrix[row][col].setHCost(Math.abs(row - destination.getRow()) + Math.abs(col - destination.getCol()));
                matrix[row][col].setTotalCost(Integer.MAX_VALUE);
                if(plot[row][col] == null || !plot[row][col].isActive()){
                    matrix[row][col].setClosed(true);
                }

            }
        }
        return matrix;
    }

    public boolean getPath(DirtTile startLocation, DirtTile endLocation){
        if (startLocation == null) return false;
        GridPath[][] matrix = initMatrix(endLocation);
        PriorityQueue<GridPath> openList = new PriorityQueue<>(new GridComparator());
        boolean pathFound = false;
        GridPath start = matrix[startLocation.getRow()][startLocation.getCol()];
        GridPath end = matrix[endLocation.getRow()][endLocation.getCol()];
        openList.add(start);
        GridPath current;
        while(true){
            current = openList.poll();
            if(current == null) break;
            current.setClosed(true);
            if(current == end){
                pathFound = true;
                break;
            }
            for(int[] direction : DIRECTIONS){
                int row = current.getRow() + direction[1];
                int col = current.getCol() + direction[0];
                if(row < 0 || col < 0 || row >= plot.length || col >= plot[0].length || matrix[row][col] == null || matrix[row][col].isClosed()) {
                    continue; 
                }
                int nextTotalCost = current.getTotalCost() + 1 + matrix[row][col].getHCost();
                GridPath adjacentGrid = matrix[row][col];
                if(nextTotalCost < adjacentGrid.getTotalCost()){
                    adjacentGrid.setTotalCost(nextTotalCost);
                    adjacentGrid.setParent(current);
                    adjacentGrid.setGCost(current.getGCost() + 1);
                    if(!openList.contains(adjacentGrid)){
                        openList.add(adjacentGrid);
                    }
                }
            }
        }
        return pathFound;
    }

    private void removeTiles(int x, int y){
        for (int i = 0; i < 4; i ++){
            int xUpdated = x + DIRECTIONS[i][0];
            int yUpdated = y + DIRECTIONS[i][1];
            if (xUpdated >= 0 && xUpdated < 32 && yUpdated >= 0 && yUpdated < 32 && plot[xUpdated][yUpdated] != null){
                if(plot[xUpdated][yUpdated].isActive()){
                    removeFromPlot(xUpdated, yUpdated);
                    removeTiles(xUpdated, yUpdated);
                }
                else{
                    removeFromPlot(xUpdated, yUpdated);
                }
            }
        }
    }
    
    public void fillTiles(){
        int startX = WIDTH/2;
        int startY = HEIGHT/2 - (STARTING_COL + 1) * DirtTile.HEIGHT;
        for (int row = 0; row < GRID_ROWS; row++){
            for(int col = 0; col < GRID_COLS; col++){
                
                int x = startX + col * DirtTile.WIDTH;
                int y = startY + row * DirtTile.HEIGHT + col * DirtTile.HEIGHT;
                
                if(plot[row][col] == null){
                    plot[row][col] = new DirtTile(this, row, col, true);
                    getWorld().addObject(plot[row][col], x,y);
                }
                else if(!plot[row][col].isActive()){
                    plot[row][col].activate();
                }
            }
        }
    }
}
