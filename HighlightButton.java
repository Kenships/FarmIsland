import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HighlightButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HighlightButton extends Button
{
    public HighlightButton(String name){
        super(name);
        clickImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        hoverImage[0] = new GreenfootImage("Buttons/" + imageName + " H.png");        
        mainImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");
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
        mainImage[0].drawImage(box.getImage(), x, y);
        hoverImage[0].drawImage(box.getImage(), x, y);
        clickImage[0].drawImage(box.getImage(), x, y);
        setImage(mainImage[0]);
    }
    
    public void setTransparancy(int transparency){
        mainImage[0].setTransparency(transparency);
        hoverImage[0].setTransparency(transparency);
        clickImage[0].setTransparency(transparency);
        setImage(mainImage[0]);
    }
}
