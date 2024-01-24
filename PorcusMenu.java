import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
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

    private ArrayList<Milestone> milestones;
    private ArrayList<Milestone> complete;

    private Milestone m1;
    private Milestone m2;

    private ItemFrame milestone1;
    private SuperTextBox status1;
    private int statusAmount1;
    private ItemFrame milestone2;
    private SuperTextBox status2;
    private int statusAmount2;
    public PorcusMenu(Button porcus){
        milestones = new ArrayList<>();
        complete = new ArrayList<>();
        InitializeMilestones();
        this.porcus = porcus;
        ID = ObjectID.NONE;
        pigStages = new GreenfootImage[4];

        for(int i = 0; i < pigStages.length; i++){
            pigStages[i] = new GreenfootImage("Backgrounds/PIG " + i + ".png");
        }

        setPigStage(0);

        Font font = new Font("Tekton Pro", true, false,  20);
        m1 = milestones.remove(0);
        m2 = milestones.remove(0);
        milestone1 = new ItemFrame(m1.getID(), "Frame Glow", 32, 32);
        milestone2 = new ItemFrame(m2.getID(), "Frame Glow", 32, 32);
        statusAmount1 = 0;
        statusAmount2 = 0;
        status1 = new SuperTextBox(statusAmount1 + "/" + m1.getAmount(), new Color(0,0,0,0), Color.BLACK, font, false, 86, 0, null);
        status2 = new SuperTextBox(statusAmount2 + "/" + m2.getAmount(), new Color(0,0,0,0), Color.BLACK, font, false, 86, 0, null);

        actTimer = new SimpleTimer();
        frame = new ItemFrame(ID, "Shop Frame", 90, 90);
        plus = new GameButton("Plus", 50);
        minus = new GameButton("Minus", 50);

        objectTimer = new SimpleTimer();
        feed = new GameButton("Buy", 90);
        sell = new GameButton("Buy", 90);
        feed.drawText("FEED", 0, -10);
        sell.drawText("SELL", 0, -10);

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
        w.addObject(feed, frame.getX() + 48, frame.getY() + 84);
        w.addObject(sell, frame.getX() - 48, frame.getY() + 84);
        w.addObject(milestone1, getX() - 90, getY() - 200);
        w.addObject(milestone2, getX() + 30, milestone1.getY());
        w.addObject(status1, milestone1.getX() + 64, milestone1.getY());
        w.addObject(status2, milestone2.getX() + 64, milestone2.getY());
    }

    public void setPigStage(int stage){
        background = new GreenfootImage("Backgrounds/Porcus.png");
        background.drawImage(pigStages[stage], 120 - pigStages[stage].getWidth()/2, 160 - pigStages[stage].getHeight()/2);
        setImage(background);
    }

    public void updateID(ObjectID ID){
        switch(ID){
            case WHEAT:
            case CARROT:
            case TOMATO:
            case PORCUS_WHEAT:
            case BLUEBERRY:
            case DRAGONFRUIT:
            case GOLDEN_TOMATO:
            case SILVER_TOMATO:
            case STRAWBERRY:
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

    public void InitializeMilestones(){
        milestones.add(new Milestone(ObjectID.WHEAT, 10));
        milestones.add(new Milestone(ObjectID.CARROT, 10));
        milestones.add(new Milestone(ObjectID.WHEAT, 15));
        milestones.add(new Milestone(ObjectID.WHEAT, 15));
        milestones.add(new Milestone(ObjectID.WHEAT, 20));
        milestones.add(new Milestone(ObjectID.TOMATO, 10));
        milestones.add(new Milestone(ObjectID.WHEAT, 50));
        milestones.add(new Milestone(ObjectID.TOMATO, 50));
        milestones.add(new Milestone(ObjectID.CARROT, 50));
        milestones.add(new Milestone(ObjectID.BLUEBERRY, 100));
        milestones.add(new Milestone(ObjectID.STRAWBERRY, 100));
        milestones.add(new Milestone(ObjectID.WHEAT, 100));
        milestones.add(new Milestone(ObjectID.TOMATO, 100));
        milestones.add(new Milestone(ObjectID.SILVER_TOMATO, 30));
        milestones.add(new Milestone(ObjectID.GOLDEN_TOMATO, 30));
        milestones.add(new Milestone(ObjectID.DRAGONFRUIT, 30));
        milestones.add(new Milestone(ObjectID.PORCUS_WHEAT, 10));
    }
    public void reset(){
        getWorld().addObject(porcus, getX() + background.getWidth()/2 + BUTTON_OFFSET, GameWorld.SCREEN_HEIGHT - BUTTON_OFFSET);
        reposition();
    }
    public void forceClose(){
        setLocation(- background.getWidth()/2, GameWorld.SCREEN_HEIGHT - background.getHeight()/2 - 32);
        reposition();
        getWorld().removeObject(porcus);
    }
    
    public void feed(ObjectID ID, int amount){

        if(ID == m1.getID()){
            if(statusAmount1 + amount > m1.getAmount()){
                int delta = statusAmount1 + amount - m1.getAmount();
                if(Inventory.remove(ID, amount - delta)){
                    statusAmount1 += amount - delta;
                }

            }
            else{
                if(Inventory.remove(ID, amount)){
                    statusAmount1 += amount;
                }
            }
            if(statusAmount1 == m1.getAmount()){
                complete.add(m1);
                if(milestones.get(0) != null){
                    statusAmount1 = 0;
                    m1 = milestones.remove(0);
                }
            }
            updateMilestones();

        }
        else if(ID == m2.getID()){
            if(statusAmount2 + amount > m2.getAmount()){
                int delta = statusAmount2 + amount - m2.getAmount();
                if(Inventory.remove(ID, amount - delta)){
                    statusAmount2 += amount - delta;
                }

            }
            else{
                if(Inventory.remove(ID, amount)){
                    statusAmount2 += amount;
                }
            }
            if(statusAmount2 == m2.getAmount()){
                complete.add(m2);
                if(milestones.get(0) != null){
                    statusAmount2 = 0;
                    m2 = milestones.remove(0);
                }
            }
            updateMilestones();
        }
    }

    public void updateMilestones(){
        milestone1.updateID(m1.getID());
        milestone2.updateID(m2.getID());
        status1.update(statusAmount1 + "/" + m1.getAmount());
        status2.update(statusAmount2 + "/" + m2.getAmount());
    }

    public void checkMouseAction(){
        if(Greenfoot.mousePressed(null)){
            objectTimer.mark();
            dragging = true;
        }
        if(Greenfoot.mouseClicked(null)){
            dragging = false;
        }
        if(sell.leftClickedThis()){
            if(Inventory.remove(ID, amount)){
                int sellPrice = CurrencyHandler.getPrice(ID, amount);
                CurrencyHandler.deposit(sellPrice);
            }

        }
        if(feed.leftClickedThis()){
            feed(ID, amount);
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
        feed.setLocation(frame.getX() + 48, frame.getY() + 84);
        sell.setLocation(frame.getX() - 48, frame.getY() + 84);
        milestone1.setLocation(getX() - 146, getY() - 205);
        milestone2.setLocation(getX() - 36, milestone1.getY());
        status1.setLocation(milestone1.getX() + 50, milestone1.getY());
        status2.setLocation(milestone2.getX() + 50, milestone2.getY());
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
