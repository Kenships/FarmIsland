import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Seed here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Seed extends Actor
{
    private boolean disapearWhenEmpty;

    private GreenfootImage[] seedImages;
    private ObjectID ID;
    //is this item a display item or item in play
    private boolean display;
    private int frame;
    private int actCounter;
    private int fixedX, fixedY;
    
    public Seed(ObjectID ID, int amount, boolean disapear){

        disapearWhenEmpty = disapear;

        Inventory.add(ID, amount);
        this.ID = ID;
        Plant plant = newPlant();
        seedImages = plant.getSeedImages();
        display = false;
        setImage(seedImages[0]);
    }

    public Seed(ObjectID ID, int amount){
        this(ID, amount, true);
    }

    public Seed(ObjectID ID){
        this(ID, -1);
    }

    public void addedToWorld(World w){
        fixLocation(getX(), getY());
    }

    public void act()
    {
        if(getWorld() == null){
            return;
        }
        actCounter++;
        reposition();
        checkMouseAction();
        animate();

    }

    public Plant newPlant(){
        //add new plants here
        Plant plant = null;
        switch(ID){
            case WHEAT_SEED:
                plant = new Wheat();
                break;
            case STUBBY_WHEAT_SEED:
                plant = new StubbyWheat();
                break;
        }
        return plant;
    }

    /**
     * NOTE:
     * animations may not be accurate to what is wanted change if nessesary
     */
    public void animate(){
        if(actCounter % 12 == 0){
            frame++;
            if(frame == seedImages.length){
                frame = 0;
            }
        }
    }

    public void reposition(){
        //check if seed is held in cursor
        if(Cursor.getActor() == this){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if(mouse != null){
                setLocation (mouse.getX(), mouse.getY());
            }
        }
    }

    public void checkMouseAction(){
        if(Greenfoot.mousePressed(this)){
            Cursor.pickUp(this);
        }
        if(Greenfoot.mouseClicked(this)){
            //setLocation (fixedX, fixedY);
            Cursor.release();
        }
    }
    /**
     * Plants and returns plant
     * 
     * FIX: messy if statements could simplify
     */
    public Plant plant(LandPlot plot, DirtTile tile){
        //if amount is less than zero there is "infinity" stock
        if(getWorld() == null){
            return null;
        }
        
        Plant plant = newPlant();
        /**
         * NEW: tile.getY() + Yoffset
         */
        if(Inventory.getAmount(ID) > 0 || Inventory.getAmount(ID) < 0){

            //remove one count of seed in inventory
            Inventory.remove(ID);
            plant.plant(plot, tile);
            getWorld().addObject(plant, tile.getX(), tile.getY() + tile.getTileYOffset()/2);

            if(Inventory.getAmount(ID) == 0){
                if(disapearWhenEmpty){
                    getWorld().removeObject(this);
                }
                else{
                    return null;
                }
            }
        }

        return plant;
    }

    public void fixLocation(int x, int y){
        fixedX = x;
        fixedY = y;
    }

    public void setDisplay(boolean toggle){
        display = toggle;
    }

    public boolean isDisplayed(){
        return display;
    }

}
