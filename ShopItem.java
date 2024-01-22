import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShopItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShopItem extends ItemFrame
{
    public static final int FRAME_WIDTH = 128;
    public static final int FRAME_HEIGHT = 128;
    private int stock;
    private boolean unlimited;
    public ShopItem(ObjectID ID, int stock){
        super(ID, FRAME_WIDTH, FRAME_HEIGHT);
        this.stock = stock;
        background = new GreenfootImage("Displays/Frames/Shop Frame.png");
        GreenfootImage glow = new GreenfootImage("Displays/Frames/Frame Glow.png");
        glow.scale(background.getWidth(), background.getHeight());
        background.drawImage(glow, 0,0);
        drawFrame();
    }

    public ShopItem(ObjectID ID, boolean unlimited){
        super(ID, FRAME_WIDTH, FRAME_HEIGHT);
        this.unlimited = unlimited;
        this.stock = stock;
        background = new GreenfootImage("Displays/Frames/Shop Frame.png");
        GreenfootImage glow = new GreenfootImage("Displays/Frames/Frame Glow.png");
        glow.scale(background.getWidth(), background.getHeight());
        background.drawImage(glow, 0,0);
        drawFrame();
    }

    public void act()
    {
        FadeMargin();
    }

    public void FadeMargin(){
        int topBound = ShopMenu.TOP_MARGIN;
        int bottomBound = GameWorld.SCREEN_HEIGHT - topBound;
        
        if(getY() < topBound || getY() > bottomBound){
            int delta = Math.min(Math.abs(topBound - getY()), Math.abs(bottomBound - getY()));
            mainImage.setTransparency(Math.max(255 - delta * 3, 0));
        }
        else{
            mainImage.setTransparency(255);
        }
    }
    
    public boolean isEmpty(){
        return getStock() == 0;
    }
    
    public int getStock(){
        return stock;
    }
    
    public void setStock(int stock){
        this.stock = stock;
    }
    
    
    public boolean removeOne(){
        //will do nothing if unlimeted
        if(unlimited){
            return true;
        }
        
        //return false if zero or remove from stock
        if(stock == 0){
            return false;
        }
        else{
            stock--;
            if(stock == 0){
                mainImage.fill();
            }
            return true;
        }
    }
    
    public void addOne(){
        stock++;
    }
}
