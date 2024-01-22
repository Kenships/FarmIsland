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
    
    /**
     * new
     */
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
        hoverSound.setVolume(90);
        
        releaseSound = new GreenfootSound[40];
        clickSound = new GreenfootSound[40];
        for (int i = 0; i < releaseSound.length; i++){
            releaseSound[i] = new GreenfootSound ("Releasemp3.mp3");
            releaseSound[i].setVolume(90);
            clickSound[i] = new GreenfootSound ("Clickmp3.mp3");
            clickSound[i].setVolume(90);
        }
        soundIndex = 0;
        
        /*releaseSound = new GreenfootSound("Releasemp3.mp3");
        releaseSound.setVolume(100);
        clickSound = new GreenfootSound("Clickmp3.mp3");
        clickSound.setVolume(100);*/

        
        mouseOver = false;
        
        clickImage[0] = new GreenfootImage("Buttons/" + imageName + " 1.png");        
        hoverImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        mainImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");
        setImage(mainImage[0]);
        width = mainImage[0].getWidth();
        height = mainImage[0].getHeight();
    }
    public Button(String imageName, int width, int height){
        this.imageName = imageName;
        mainImage = new GreenfootImage[1];
        hoverImage = new GreenfootImage[1];
        clickImage = new GreenfootImage[1];

        clickImage[0] = new GreenfootImage("Buttons/" + imageName + " 1.png");        
        hoverImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");        
        mainImage[0] = new GreenfootImage("Buttons/" + imageName + ".png");
        setImage(mainImage[0]);
        width = mainImage[0].getWidth();
        height = mainImage[0].getHeight();
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
            //clickSound.play();
            //Greenfoot.playSound("Clickmp3.mp3");
            clicked = true;
        }
        if(Greenfoot.mouseClicked(null) && clicked){
            release();
            releaseSound();
            //releaseSound.play();
            //Greenfoot.playSound("Releasemp3.mp3");
            clicked = false;
        }
        //hovering animation
        if(!clicked){
            if(hoveringThis()){
                hover();
            }
            else{
                release();
            }
        }
        hoverAudio();
    }

    
    //if user hovers over button, will play hover sound
    private void hoverAudio()
    {
        if (Greenfoot.mouseMoved(null)) // mouse moved?
        {
            if (mouseOver != Greenfoot.mouseMoved(this)) // change in hover state?
            {
                mouseOver = ! mouseOver; // save change
                if (mouseOver) // hover begins?
                {
                    hoverSound.play();
                    //Greenfoot.playSound("HoverSoundmp3.mp3");
                }
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
}