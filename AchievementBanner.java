import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AchievementBanner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AchievementBanner extends SuperSmoothMover
{
    public static final int WIDTH = 160;
    public static final int HEIGHT = 160;
    private boolean completed;
    private GreenfootImage image;
    private Achievement achievement;
    
    public AchievementBanner(Achievement achievement, boolean completed){
        this.achievement = achievement;
        this.completed = completed;
        initialize();
        setImage(image);
    }
    
    public void initialize(){
        if(completed == false){
            image = new GreenfootImage("Displays/AchievementIcons/" + achievement.getIncompleteIcon() + ".png");
        }
        else{
            image = new GreenfootImage("Displays/AchievementIcons/" + achievement.getCompleteIcon() + ".png");
        }
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            displayDescription();
        }
    }
    
    public void displayDescription(){
        System.out.println(achievement.getDescription());
    }
}
