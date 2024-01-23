import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PorcusMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PorcusMenu extends SuperSmoothMover
{
    public static final int SPEED = 8;
    public static final int BUTTON_OFFSET = 84;
    private Button porcus;
    private boolean open;
    private int direction;
    private GreenfootImage background;
    private SimpleTimer actTimer;
    private ItemFrame frame;
    private Button plus;
    private Button minus;
    private Button sell;
    private Button feed;
    private SuperTextBox amountDisplay;
    private int amount;
    private boolean dragging;
    private SimpleTimer objectTimer;
    private ObjectID ID;
    private GreenfootImage[] pigStages;
    private int stage;
    public PorcusMenu(Button porcus){
        this.porcus = porcus;
        ID = ObjectID.NONE;
        pigStages = new GreenfootImage[4];
        
        for(int i = 0; i < pigStages.length; i++){
            pigStages[i] = new GreenfootImage("Backgrounds/PIG " + i + ".png");
        }
        
        setPigStage(0);
        
        actTimer = new SimpleTimer();
        frame = new ItemFrame(ID, "Shop Frame", 90, 90);
        plus = new GameButton("Plus", 50);
        minus = new GameButton("Minus", 50);
        Font font = new Font("Tekton Pro", true, false,  20);
        objectTimer = new SimpleTimer();
        feed = new GameButton("Buy", 64);
        sell = new GameButton("Buy", 64);
        feed.drawText("Buy");
        sell.drawText("sell");
        
        amountDisplay = new SuperTextBox(" ", new Color(0,0,0,0), Color.BLACK, font, true, 180, 0, null);
        amount = 0;
        setImage(background);
    }
    
    public void addedToWorld(World w){
        setLocation(- background.getWidth()/2, GameWorld.SCREEN_HEIGHT - background.getHeight()/2 - 32);
        w.addObject(porcus, getX() + background.getWidth()/2 + BUTTON_OFFSET, GameWorld.SCREEN_HEIGHT - BUTTON_OFFSET);
        w.addObject(frame, getX() - 45, getY() + 68);
        w.addObject(plus, frame.getX() + 90, frame.getY());
        w.addObject(minus, frame.getX() - 90, frame.getY());
        w.addObject(amountDisplay, frame.getX(), frame.getY() - 56);
        w.addObject(feed, frame.getX() + 90, frame.getY() + 90);
        w.addObject(sell, frame.getX() - 90, frame.getY() + 90);
    }
    
    public void setPigStage(int stage){
        background = new GreenfootImage("Backgrounds/Porcus.png");
        background.drawImage(pigStages[stage], 120 - pigStages[stage].getWidth()/2, 160 - pigStages[stage].getHeight()/2);
    }
    
    public void updateID(ObjectID ID){
        switch(ID){
            case WHEAT:
            case CARROT:
            case TOMATO:
            case PORCUS_WHEAT:
            case BLUEBERRY:
            case STRAWBERRY:
            case DRAGONFRUIT:
            case SILVER_TOMATO: 
            case GOLDEN_TOMATO: 
                amount = Inventory.getAmount(ID);
                amountDisplay.update(String.valueOf(amount));
                this.ID = ID;
                frame.updateID(ID);
                break;
        }

    }

    public void act()
    {
        checkMouseAction();
        if(actTimer.millisElapsed() >= 4){
            actTimer.mark();
            if(direction == 1){
                open();
            }
            if(direction == -1){
                close();
            }
        }
    }

    public void checkMouseAction(){
        if(Greenfoot.mousePressed(null)){
            objectTimer.mark();
            dragging = true;
        }
        if(Greenfoot.mouseClicked(null)){
            dragging = false;
        }
        if(plus.leftClickedThis()){
            amount++;
            if(amount > Inventory.getAmount(ID)){
                amount = Inventory.getAmount(ID);
            }
            amountDisplay.update(String.valueOf(amount));
        }
        else if(plus.hoveringThis() && dragging && objectTimer.millisElapsed() > 1000){
            amount+=2;
            if(amount > Inventory.getAmount(ID)){
                amount = Inventory.getAmount(ID);
            }
            amountDisplay.update(String.valueOf(amount));
        }
        if(minus.leftClickedThis()){
            amount--;
            if(amount < 0){
                amount = 0;
            }
            amountDisplay.update(String.valueOf(amount));
        }
        else if(minus.hoveringThis() && dragging && objectTimer.millisElapsed() > 1000){
            amount-=2;
            if(amount < 0){
                amount = 0;
            }
            amountDisplay.update(String.valueOf(amount));
        }
    }

    public void reposition(){
        porcus.setLocation(getX() + background.getWidth()/2 + BUTTON_OFFSET, porcus.getY());
        frame.setLocation(getX()-45, getY() + 68);
        plus.setLocation(frame.getX() + 90, frame.getY());
        minus.setLocation(frame.getX() - 90, frame.getY());
        amountDisplay.setLocation(frame.getX(), frame.getY() - 56);
        feed.setLocation( frame.getX() + 90, frame.getY() + 90);
        sell.setLocation(frame.getX() - 90, frame.getY() + 90);
    }

    public void open(){
        open = true;
        direction = 1;
        if(getX() < background.getWidth()/2){
            setLocation(getX() + SPEED, getY());

            reposition();
        }
        else{
            direction = 0;
            setLocation(background.getWidth()/2, getY());
            reposition();
        }
    }

    public void close(){
        open = false;
        direction = -1;
        if(getX() > - background.getWidth()/2){
            setLocation(getX() - SPEED, getY());

            reposition();
        }
        else{
            direction = 0;
            setLocation(-background.getWidth()/2, getY());

            reposition();
        }
    }

    public boolean isOpen(){
        return open;
    }
}
