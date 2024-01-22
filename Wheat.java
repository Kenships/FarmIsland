import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wheat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wheat extends Plant
{
    public static final int Y_OFFSET = 32;
    public static final int DEFAULT_GROWTHRATE = 1;
    public static final int GROWTH_STAGES = 4;
    private GreenfootSound[] harvestingSound;
    private int soundIndex;
    /**
     * NEW: ID moved and initialized in constructor
     */
    public Wheat(){
        super(Y_OFFSET);
        sellPrice = 2;
        ID = ObjectID.WHEAT;
        initialize();
        yield = 1;
        
        harvestingSound = new GreenfootSound[40];

        for (int i = 0; i < harvestingSound.length; i++){
            harvestingSound[i] = new GreenfootSound ("HarvestingSound.wav");
            harvestingSound[i].setVolume(90);
        }
        soundIndex = 0;
    }

    public Wheat(int yeild){
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

        growthAnimations = new GreenfootImage[GROWTH_STAGES][5];
        for(int stage = 0; stage < GROWTH_STAGES; stage++){
            for(int frame = 0; frame < growthAnimations[stage].length; frame++){
                growthAnimations[stage][frame] = new GreenfootImage("Plants/Wheat/Stage " + stage + "/"+ frame + ".png");
            }

        }

        setImage(growthAnimations[growthStage][0]);
    }

    public void grow(){
        maturity += growthRate + myTile.getGrowthMultiplier();
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
    //MOVE TO COLLECTION HANDLER
    public void collect(){
        Inventory.add(ID, yeild);
        CurrencyHandler.deposit(2);
        myTile.unPlant();
        getWorld().removeObject(this);
        
        harvestingSound[soundIndex].play();
        soundIndex++;
        if(soundIndex == harvestingSound.length)
        {
            soundIndex=0;
        }   
    }
    
    public void checkKeypressAction(){

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
