import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FeaturedFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FeaturedFrame extends ItemFrame
{
    public static final int FRAME_WIDTH = 300;
    public static final int FRAME_HEIGHT = 512;
    private ShopItem item;
    private int cost;
    private MenuButton purchase;
    public FeaturedFrame(ShopItem item, MenuButton purchase){
        super(ObjectID.NONE, FRAME_WIDTH, FRAME_HEIGHT);
        cost = CurrencyHandler.getPrice(ID);
        this.purchase = purchase;
        this.item = item;
    }
    
    public void updateDisplay(ShopItem item){
        ID = item.getID();
        this.item = item;
        cost = CurrencyHandler.getPrice(ID);
        purchase.drawText("$" + cost);
        updateID(ID);
    }
    
    public void drawFrame(){
        mainImage.drawImage(background, 0, 0);
        int marginY = (background.getHeight() - foreground.getHeight())/4;
        int marginX = (background.getWidth() - foreground.getWidth())/2;
        if(marginY < 0 || marginX < 0){
            foreground.scale(background.getWidth(), background.getHeight());
            marginY = 0;
            marginX = 0;
        }
        mainImage.drawImage(foreground, marginX, marginY);          

        setImage(mainImage);
    }     
    
    public ShopItem getItem(){
        return item;
    }
}
