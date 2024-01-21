import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.*;
import java.awt.Component;
import javax.swing.SwingUtilities;
import greenfoot.core.WorldHandler;

/**
 * Write a description of class ShopMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShopMenu extends SuperSmoothMover 
{
    public static final int ROW_MAX = 4;
    public static final int SPACING = ShopItem.FRAME_WIDTH + 32;
    public static final int TOP_MARGIN = 256;
    public static final int LEFT_MARGIN = 200;
    private static final int UP = 1;
    private static final int DOWN = -1;

    private static final double ITEM_SPEED = 16;
    private boolean moving;
    private double distanceTraveled;
    private int pageNumber;
    private int direction;

    private ArrayList<ShopItem> itemGallery;
    private FeaturedFrame featuredItem;
    private GreenfootImage background;
    private Button returnButton;
    private Button menuUp;
    private Button menuDown;
    private MenuButton purchase;

    private boolean firstOpened;

    private GameWorld myWorld;

    private SimpleTimer actTimer;
    public ShopMenu(ArrayList<ObjectID> galleryIDs){
        initialize(galleryIDs);
    }

    public ShopMenu(HashMap<ObjectID, Integer> galleryIDs){
        initialize(galleryIDs);
    }

    public void addedToWorld(World w){
        myWorld = (GameWorld) w;
        addButtons();
        resetShop();
        firstOpened = true;
    }

    public void act(){
        if(myWorld.isScreen(myWorld.SHOP)){
            checkMouseAction();
            checkKeyAction();
            if(actTimer.millisElapsed() >= 17){
                actTimer.mark();
                if(direction != 0){
                    slide();
                }
            }

        }

    }

    public void initialize(ArrayList<ObjectID> galleryIDs){
        actTimer = new SimpleTimer();

        background = new GreenfootImage("Shop.png");
        
        setImage(background);

        returnButton = new MenuButton("Shop");
        menuUp = new MenuButton("Arrow");
        menuDown = new MenuButton("Arrow");
        menuDown.setRotation(180);
        itemGallery = new ArrayList<>();

        for(ObjectID ID : galleryIDs){
            itemGallery.add(new ShopItem(ID, 1));
        }
    }

    public void initialize(HashMap<ObjectID, Integer> galleryIDs){
        actTimer = new SimpleTimer();
        
        background = new GreenfootImage("Backgrounds/Shop.png");
        background.drawImage(new GreenfootImage("Backgrounds/Shop Mascot.png"), 0, 0);
        setImage(background);

        returnButton = new MenuButton("Shop");
        menuUp = new MenuButton("Shop Up");
        menuDown = new MenuButton("Shop Down");
        purchase = new MenuButton("Buy");
        itemGallery = new ArrayList<>();

        for(ObjectID ID : galleryIDs.keySet()){
            if(galleryIDs.get(ID) == -1){
                itemGallery.add(new ShopItem(ID, true));
            }
            else{
                itemGallery.add(new ShopItem(ID, galleryIDs.get(ID)));
            }

        }
        sortItemGallery();
    }

    public void sortItemGallery(){
        //to be filled
    }

    public void resetShop(){
        int index = 0;
        for(ShopItem item : itemGallery){
            int x = LEFT_MARGIN + SPACING * (index % ROW_MAX);
            int y = TOP_MARGIN + SPACING * (index / ROW_MAX);
            if(item.getWorld() != null){                
                item.setLocation(x, y);
            }
            else{
                myWorld.addObject(item, x,y);
            }
            index++;
        }

        featuredItem = new FeaturedFrame(itemGallery.get(0),purchase);
        int x = 1043;
        int y = 400;
        myWorld.addObject(featuredItem, x, y);

    }

    public void addButtons(){
        int x = LEFT_MARGIN/3;
        int y = GameWorld.SCREEN_HEIGHT - LEFT_MARGIN/3;
        myWorld.addObject(returnButton, x, y);
        getWorld().addObject(returnButton, 64, 656);
        
        myWorld.addObject(menuUp, 814, 250);

        
        myWorld.addObject(menuDown,814, 570);

        myWorld.addObject(purchase, 1040, 526);
    }

    public void clearShop(){
        ArrayList<Actor> components = new ArrayList<>();
        components.add(returnButton);
        components.add(menuUp);
        components.add(menuDown);
        components.add(purchase);
        components.add(featuredItem);
        components.addAll(myWorld.getObjects(ShopItem.class));
        for(Actor component : components){
            myWorld.removeObject(component);
        }
    }
    
    public void checkKeyAction(){
        if(Greenfoot.isKeyDown("b") == !firstOpened){
            firstOpened = false;
            myWorld.setScreen(GameWorld.GAME);
            clearShop();
            myWorld.removeObject(this);
        }
        else{
            firstOpened = false;
        }
    }
    
    public void checkMouseAction(){
        if (returnButton.leftClickedThis() && !firstOpened){
            myWorld.setScreen(myWorld.GAME);
            clearShop();
            myWorld.removeObject(this);
        }
        else{
            firstOpened = false;
        }
        if(direction == 0){
            if(menuUp.leftClickedThis()){
                direction = UP;
                pageNumber++;
            }
            if(menuDown.leftClickedThis()){
                direction = DOWN;
                pageNumber--;
            }
            for(ShopItem item : itemGallery){
                if(Greenfoot.mouseClicked(item)){
                    featuredItem.updateDisplay(item);
                }
            }
        }
        if(featuredItem.getID() != ObjectID.NONE && Greenfoot.mouseClicked(purchase) ){
            ShopItem item = featuredItem.getItem();
            if(CurrencyHandler.isAffordable(item.getID())){
                if(item.removeOne()){
                    CurrencyHandler.purchase(item.getID(), 1);
                }
            }

        }

    }

    public void slide(){
        double end = SPACING * ROW_MAX;
        if(distanceTraveled >= end){
            double delta = distanceTraveled - end;
            for(ShopItem item : itemGallery){
                //redundancy
                if(item.getWorld() == null){
                    break;
                }

                item.setLocation(item.getX(), item.getPreciseY() - delta * direction);
            }
            distanceTraveled = 0;
            direction = 0;
            System.out.println("Distance: " + distanceTraveled + " end: " + end + "Direction: " + direction);
            return;
        }
        for(ShopItem item : itemGallery){
            //redundancy
            if(item.getWorld() == null){
                break;
            }

            item.setLocation(item.getX(), item.getPreciseY() + ITEM_SPEED * direction);
        }
        distanceTraveled += ITEM_SPEED;

    }
}
