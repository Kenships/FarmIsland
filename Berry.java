import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CopyOfCarrot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Berry extends Plant
{
    public static final int Y_OFFSET = 32;
    public static final int DEFAULT_GROWTHRATE = 1;
    public static final int GROWTH_STAGES = 4;
    /**
     * NEW: ID moved and initialized in constructor
     */
    public Berry(){
        super(Y_OFFSET);
        ID = ObjectID.CARROT;
        initialize();
        yield = 1;
        sellPrice = 3;
    }

    public Berry(int yeild){
        super(32);
        ID = ObjectID.WHEAT;
        initialize();
        this.yield = yield;
    }
    public void act()
    {
        super.act();
    }
    
    public void initialize(){
        //add yOffsets
        //setYOffset(_growthStage_, _pixels_)
        for(int stage = 0; stage < GROWTH_STAGES; stage++){
            setYOffset(stage, -Y_OFFSET);
        }
        
        growthRate = DEFAULT_GROWTHRATE;
        
        growthAnimations = new GreenfootImage[GROWTH_STAGES][3];
        for(int stage = 0; stage < GROWTH_STAGES; stage++){
            for(int frame = 0; frame < growthAnimations[stage].length; frame++){
                growthAnimations[stage][frame] = new GreenfootImage("Plants/Carrot/Stage " + stage + "/"+ frame + ".png");
            }

        }

        setImage(growthAnimations[growthStage][0]);
    }
    
    public void grow(){
        maturity += growthRate;
        if(maturity % 300 == 0 && growthStage < GROWTH_STAGES - 1){
            growthStage ++;
            //fade before setting
            fadeOval(growthAnimations[growthStage][0]);
            setImage(growthAnimations[growthStage][0]);
        }
        
        //when the growthStage is at max the crop is mature
        if(growthStage == GROWTH_STAGES - 1){
            mature = true;
        }
        
    }
    
    public void nextFrame(){
        animationIndex += deltaIndex;
        if(animationIndex >= 0 && animationIndex < growthAnimations[growthStage].length){
            fadeOval(growthAnimations[growthStage][animationIndex]);
            setImage(growthAnimations[growthStage][animationIndex]);
        }
        else{
            deltaIndex *= -1;
            animationIndex += 2 * deltaIndex;
            fadeOval(growthAnimations[growthStage][animationIndex]);
            setImage(growthAnimations[growthStage][animationIndex]);
        }
    }
    
    public void checkKeypressAction(){
        
    }
    
    public void checkMouseAction(){
        if(mature && hoveringThis() && Cursor.leftClicked()){
            CollectionHandler.collect(this);
        }
    }
    public void playPlaceSound(){
        
    }
    public void playRemoveSound(){
        
    }
    
    //temporary
    public GreenfootImage getItemImage(){
        //fill
        return new GreenfootImage(1,1);
    }
}
