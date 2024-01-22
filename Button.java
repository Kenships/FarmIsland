import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Button extends SuperSmoothMover
{
    protected GreenfootImage[] mainImage;
    protected GreenfootImage[] hoverImage;
    protected GreenfootImage[] clickImage;
    protected String imageName;
    protected int width, height;
    private boolean clicked;
    private boolean mouseOver;
    private GreenfootSound hoverSound;
    private GreenfootSound[] releaseSound;
    private GreenfootSound[] clickSound;

    private int soundIndex;
    public Button(String imageName){
        this.imageName = imageName;
        mainImage = new GreenfootImage[1];
        hoverImage = new GreenfootImage[1];
        clickImage = new GreenfootImage[1];

        hoverSound = new GreenfootSound ("HoverSoundmp3.mp3");
        hoverSound.setVolume(50);

        releaseSound = new GreenfootSound[6];
        clickSound = new GreenfootSound[6];
        for(int i = 0; i < releaseSound.length; i++){
            releaseSound[i] = new GreenfootSound("Releasemp3.mp3");
            releaseSound[i].setVolume(50);
            clickSound[i] = new GreenfootSound("Clickmp3.mp3");
            clickSound[i].setVolume(90);
        }
    
        soundIndex = 0;
    }

    public void addedToWorld(World w){
        clicked = false;
    }

    public void act()
    {
        //mouse pressed + mouse release combo to check for hold
        if(Greenfoot.mousePressed(this)){
            click();
            clickSound();
            clicked = true;
        }
        if(Greenfoot.mouseClicked(null) && clicked){
            release();
            releaseSound();
            clicked = false;
        }
        //hovering animation
        if(!clicked){
            if(hoveringThis() != mouseOver){
                mouseOver = ! mouseOver;
                if (mouseOver) // hover begins?
                {
                    hoverSound.play();
                }
            }
            if(hoveringThis()){
                hover();

            }
            else{
                release();
            }
        }
    }

    public void clickSound()
    {
        clickSound[soundIndex].play();
        soundIndex++;
        if (soundIndex == clickSound.length){
            soundIndex = 0;
        }
    }

    public void releaseSound()
    {
        releaseSound[soundIndex].play();
        soundIndex++;
        if (soundIndex == releaseSound.length){
            soundIndex = 0;
        }
    }

    public abstract void hover();

    public abstract void click();

    public abstract void release();

    public abstract boolean hoveringThis();

    public boolean leftClickedThis(){
        return Greenfoot.mouseClicked(null) && hoveringThis() && Cursor.leftClicked();
    }
    
    public void drawText(String text){
        drawText(text, 0, 0);
    }
    public void drawText(String text, int size){
        drawText(text,0,0, size);
    }
    public void drawText(String text, int x, int y){
        drawText(text, x, y, 35);
    }
    public void drawText(String text, int x, int y, int textSize){
        Font font = new Font("Tekton Pro", true, false,  textSize);
        SuperTextBox box = new SuperTextBox(text, new Color(0,0,0,0), Color.BLACK, font, true, mainImage[0].getWidth(), 0, null);
        clickImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        hoverImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        mainImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");
        
        mainImage[0].drawImage(box.getImage(), x, y);
        hoverImage[0].drawImage(box.getImage(), x, y);
        clickImage[0].drawImage(box.getImage(), x, y);
        setImage(mainImage[0]);
    }
}
