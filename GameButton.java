import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameButton extends Button
{
    private int size;
    public GameButton(String name){
        super(name);
        
        clickImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        hoverImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        mainImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");
        clickImage[0].scale(80, 80);
        hoverImage[0].scale(90, 90);
        mainImage[0].scale(80, 80);
        setImage(mainImage[0]);
        
        width = mainImage[0].getWidth();
        height = mainImage[0].getHeight();
    }
    public GameButton(String name, int size){
        super(name);
        this.size = size;
        clickImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        hoverImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        mainImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");
        width = mainImage[0].getWidth();
        height = mainImage[0].getHeight();
        
        double ratio = (double) height/width;
        clickImage[0].scale(size, (int) (size * ratio + 0.5));
        
        hoverImage[0].scale(size + 10, (int) ((size + 10) * ratio + 0.5));
        mainImage[0].scale(size, (int) (size * ratio + 0.5));
        setImage(mainImage[0]);
        
        
        
        width = mainImage[0].getWidth();
        height = mainImage[0].getHeight();
    }
    public void act()
    {
        super.act();
    }
    public boolean hoveringThis(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null && getWorld() != null){
            int leftBound = getX() - width/2;
            int rightBound = getX() + width/2;
            int topBound = getY() - height/2;
            int bottomBound = getY() + height/2;
            return mouse.getX() < rightBound && mouse.getX() > leftBound && mouse.getY() < bottomBound && mouse.getY() > topBound;
        }
        return false;
    }
    public void release(){
        setImage(mainImage[0]);
    }
    public void click(){
        setImage(clickImage[0]);
    }
    public void hover(){
        setImage(hoverImage[0]);
    }
    public void drawText(String text, int x, int y, int textSize){
        Font font = new Font("Tekton Pro", true, false,  textSize);
        SuperTextBox box = new SuperTextBox(text, new Color(0,0,0,0), Color.BLACK, font, true, mainImage[0].getWidth(), 0, null);
        clickImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        hoverImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        mainImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");
        double ratio = (double) height/width;
        clickImage[0].scale(size, (int) (size * ratio + 0.5));
        hoverImage[0].scale(size + 10, (int) ((size + 10) * ratio + 0.5));
        mainImage[0].scale(size, (int) (size * ratio + 0.5));
        setImage(mainImage[0]);
        
        mainImage[0].drawImage(box.getImage(), x, y);
        hoverImage[0].drawImage(box.getImage(), x, y);
        clickImage[0].drawImage(box.getImage(), x, y);
        setImage(mainImage[0]);
    }
}
