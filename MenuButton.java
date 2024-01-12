import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartMenuButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuButton extends Button
{
    private boolean offseted;

    private int offset;
    public MenuButton(String imageName){
        //(imageName, width, height)
        super(imageName);
    }
    public MenuButton(String imageName, String text){
        super(imageName);
        mainImage[0].drawString(text,10,height - 10);
    }
    public void addedToWorld(World w){
        super.addedToWorld(w);
        offset = 5;
        offseted = false;
    }
    
    public void act()
    {
        super.act();
    }

    public void hover(){
        if(!offseted){
            setLocation(getX() + offset, getY() - 2 * offset);
            offseted = true;
        }
        setImage(hoverImage[0]);        
    }

    public void click(){
        if(!offseted){
            setLocation(getX() + offset, getY() - 2 * offset);
            offseted = true;
        }
        setImage(clickImage[0]);
    }

    public void release(){
        if(offseted) {
            setLocation(getX() - offset, getY() + 2 * offset);
            offseted = false;
        }
        setImage(mainImage[0]);
    }
    
    public boolean hoveringThis(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null && getWorld() != null){
            int leftBound = getX() - width/2;
            int rightBound = getX() + width/2;
            int topBound = getY() - height/2;
            int bottomBound = getY() + height/2;
            if(offseted){
                leftBound -= offset;
                rightBound -= offset;
                topBound += 2 * offset;
                bottomBound += 2 * offset;
            }
            
            return mouse.getX() < rightBound && mouse.getX() > leftBound && mouse.getY() < bottomBound && mouse.getY() > topBound;
        }
        return false;
    }
    
    public void drawText(String text){
        clickImage[0] = new GreenfootImage("Buttons/" + imageName + " 1.png");        
        hoverImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        mainImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");
        mainImage[0].drawString(text, width/2, height/2);
        hoverImage[0].drawString(text, width/2, height/2);
        clickImage[0].drawString(text, clickImage[0].getWidth()/2, clickImage[0].getHeight()/2);
        setImage(mainImage[0]);
    }
    
    
}
