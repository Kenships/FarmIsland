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
    public MenuButton(String text){
        //(text, width, height)
        super(text,350,60);

        offset = 5;
        offseted = false;

        mainImage = new GreenfootImage[1];
        hoverImage = new GreenfootImage[1];
        clickImage = new GreenfootImage[1];

        clickImage[0] = new GreenfootImage(text + " Button 1.png");        
        hoverImage[0] = new GreenfootImage(text + " Button.png");        
        mainImage[0] = new GreenfootImage(text + " Button.png");
        setImage(mainImage[0]);

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
        if(mouse != null){
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
}
