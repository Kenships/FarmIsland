import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PorcusWheat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PorcusWheat extends Plant implements ItemConvertible
{
    public static final int DEFAULT_GROWTHRATE = 1;
    public static final int GROWTH_STAGES = 3;
   
    public PorcusWheat(){
        super();
        ID = ObjectID.PORCUS_WHEAT;
        initialize();
        yield = 5;
        sellPrice = 200;
    }

    public PorcusWheat(int yield){
        super();
        initialize();
        this.yield = yield;
    }

    public void act()
    {
        super.act();
    }

    public void initialize(){
        growthRate = DEFAULT_GROWTHRATE;

        growthAnimations = new GreenfootImage[GROWTH_STAGES][3];
        for(int stage = 0; stage < GROWTH_STAGES; stage++){
            //initialize offsets
            setYOffset(stage, -16);
            for(int frame = 0; frame < growthAnimations[stage].length; frame++){
                growthAnimations[stage][frame] = new GreenfootImage("Plants/PorcusWheat/Stage " + stage + "/" + frame + ".png");
            }

        }

        setImage(growthAnimations[growthStage][0]);
    }

    public void grow(){
        maturity += growthRate + myTile.getGrowthMultiplier();
        if(maturity % 12000 == 0 && growthStage < GROWTH_STAGES - 1){
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

    public void playPlaceSound(){

    }

    public void playRemoveSound(){

    }

    //temporary
    public GreenfootImage getItemImage(){
        return new GreenfootImage("Stubby Wheat Stage 0.png");
    }
}
