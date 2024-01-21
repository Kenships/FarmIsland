import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FeaturedFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FeaturedFrame extends ItemFrame
{
    public static final int FRAME_WIDTH = 288;
    public static final int FRAME_HEIGHT = 450;
    private ShopItem item;
    private int cost;
    private MenuButton purchase;
    private Font font;
    public FeaturedFrame(MenuButton purchase){
        super(ObjectID.NONE, FRAME_WIDTH, FRAME_HEIGHT);
        cost = CurrencyHandler.getPrice(ID);
        this.purchase = purchase;
        font = new Font("Tekton Pro", true, false,  30);
        SuperTextBox box = new SuperTextBox(" ", new Color(0,0,0,15), Color.BLACK, font, true, FRAME_WIDTH, 0, null);
        background = new GreenfootImage(FRAME_WIDTH,FRAME_HEIGHT);
        background.drawImage(box.getImage(), 0, 70);
        drawFrame();
    }

    public void updateDisplay(ShopItem item){
        ID = item.getID();
        String[] nameSplit = ID.toString().split("_");
        String nameString = "";
        for(int i = 0; i < nameSplit.length; i++){
            nameString += nameSplit[i] + " ";
        }
        SuperTextBox box = new SuperTextBox(nameString, new Color(0,0,0,15), Color.BLACK, font, true, FRAME_WIDTH, 0, null);
        background.clear();
        background.drawImage(box.getImage(), 0, 70);
        this.item = item;
        cost = CurrencyHandler.getPrice(ID);
        purchase.drawText("$" + cost, 0, -8);
        updateID(ID);
    }
    
    public void updatePrice(int amount){
        cost = CurrencyHandler.getPrice(ID, amount);
        purchase.drawText("$" + cost, 0, -8);
    }
    
    public void updateID(ObjectID ID){
        this.ID = ID;
        
        foreground = ID.getDisplayImage();
        if(foreground.getWidth() < 128){
            double ratio = (double) foreground.getHeight()/foreground.getWidth();
            foreground.scale(86, (int)(86 * ratio + 0.5));
        }
        
        drawFrame();
    }
    
    public void drawFrame(){
        mainImage.clear();
        mainImage.drawImage(background, 0, 0);
        int marginY = (background.getHeight() - foreground.getHeight())/2;
        int marginX = (background.getWidth() - foreground.getWidth())/2;
        if(marginY < 0 || marginX < 0){
            double ratio = (double) foreground.getHeight()/foreground.getWidth();
            foreground.scale(background.getWidth(), (int)(background.getWidth() * ratio + 0.5));
            marginY = (background.getHeight() - foreground.getHeight())/2;;
            marginX = (background.getWidth() - foreground.getWidth())/2;
        }
        mainImage.drawImage(foreground, marginX, marginY - 35);
        setImage(mainImage);
    }
    
    public void reset(){
        SuperTextBox box = new SuperTextBox("CHOOSE ITEM", new Color(0,0,0,15), Color.BLACK, font, true, FRAME_WIDTH, 0, null);
        background.clear();
        background.drawImage(box.getImage(), 0, 70);
        purchase.drawText(" ", 0, -8);
        updateID(ObjectID.NONE);
    }
    
    public ShopItem getItem(){
        return item;
    }
}
