import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The AchievementMenu class represents an in-game menu for displaying achievements.
 * Players can navigate through different pages of achievements and return to the
 * game by clicking the button.
 * 
 * @author Ryan Du
 * @version January 2024
 */
public class AchievementMenu extends SuperSmoothMover
{
    public static final int ROW_MAX = 3;
    public static final int SPACING = AchievementBanner.WIDTH + 32;
    public static final int TOP_MARGIN = 180;
    public static final int LINE_SPACING = AchievementBanner.HEIGHT + 32;
    public static final int LEFT_MARGIN = 140;
    public static final int LEFT_MARGIN_RIGHTSIDE = 750;

    private GreenfootImage background;
    private Button returnButton;
    private Button left;
    private Button right;
    private int page;
    private int currentRow, currentCol;

    private GameWorld myWorld;
    private static ArrayList<AchievementBanner> achievementGallery;

    /**
     * Called when the AchievementMenu is added to the world.
     * 
     * @param w The world that the AchievementMenu is added to.
     */
    public void addedToWorld(World world){
        myWorld = (GameWorld) world;
        myWorld.setScreen(myWorld.ACHIEVEMENT);
        page = 1;
        initialize();
        setScreen(page);
    }
    
    /**
     * Initializes the buttons and achievement gallery.
     */
    public void initialize(){
        returnButton = new MenuButton("Achievement Exit");
        left = new MenuButton("Small Arrow");
        left.setRotation(270);
        right = new MenuButton("Small Arrow");
        right.setRotation(90);
        achievementGallery = new ArrayList<>();
    }
    
    /**
     * Checks for mouse actions when the achievement screen is active.
     */
    public void act(){
        if(myWorld.isScreen(myWorld.ACHIEVEMENT)){
            checkMouseAction();
        }
    }
    
    /**
     * Sets the display for the specified achievement menu page.
     * 
     * @param pageNum The page number.
     */
    public void setScreen(int pageNum){
        clearAchievementMenu();
        switch(pageNum){
            case 1:
                myWorld.addObject(returnButton, 46,46);
                myWorld.addObject(right, 1200, 650);
                setAchievements(1);
                break;
            case 2:
                myWorld.addObject(returnButton, 46,46);
                myWorld.addObject(left, 1120, 650);
                myWorld.addObject(right, 1200, 650);
                setAchievements(2);
                break;
            case 3:
                myWorld.addObject(returnButton, 46,46);
                myWorld.addObject(left, 1120, 650);
                setAchievements(3);
                break;
        }
        background = new GreenfootImage("BackGrounds/Achievement Background " + page + ".png");
        setImage(background);
    }
    
    /**
     * Sets up the visual elements for the achievements.
     * 
     * @param p The page number for which achievements are set.
     */
     
     
    public void setAchievements(int pageNum){
        switch(pageNum){
            case 1:
                setBanners(AchievementManager.agricultureA, true);
                setBanners(AchievementManager.agricultureB, false);
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
    
    private void setBanners(ArrayList<Achievement> achievements, boolean isLeft){
        achievementGallery.clear();
        currentRow = 0;
        currentCol = 0;
        for(int i = 0; i<= getLatestIndex(achievements); i++){
            achievementGallery.add(new AchievementBanner(achievements.get(i),achievements.get(i).getCompleted()));
            if((i+1)%(ROW_MAX + 1) == 0){
                currentRow ++;
                currentCol = 0;
            }
            int margin = isLeft? LEFT_MARGIN: LEFT_MARGIN_RIGHTSIDE;
            getWorld().addObject(achievementGallery.get(i), margin + (SPACING * currentCol), TOP_MARGIN + (LINE_SPACING * currentRow));
            currentCol++;
        }
    }
    
    /**
     * Gets the index of latest incomplete achievement in the given list.
     * 
     * @param a The list of achievements.
     * @return int The index of latest incomplete achievement completed. Returns the highest index if all are completed.
     */
     
    private int getLatestIndex(ArrayList<Achievement> achievements ){
        for(int i = 0; i < achievements.size(); i++){
            if(!achievements.get(i).getCompleted()){
                return i;
            }
        }
        return achievements.size() -1;
    }
    
   
    /**
     * Clears the graphical elements of the achievement menu from the world.
     */
    public void clearAchievementMenu(){
        ArrayList<Actor> components = new ArrayList<>();
        components.add(returnButton);
        components.add(left);
        components.add(right);
        components.addAll(myWorld.getObjects(AchievementBanner.class));
        for(Actor component : components){
            myWorld.removeObject(component);
        }
    }
    
    /**
     * Checks for mouse clicks on the achievement menu buttons.
     */
    public void checkMouseAction(){
        if(Greenfoot.mouseClicked(returnButton)){
            myWorld.setScreen(myWorld.GAME);
            clearAchievementMenu();
            myWorld.removeObject(this);
        }
        if(Greenfoot.mouseClicked(left)){
            page --;
            setScreen(page);
        }
        if(Greenfoot.mouseClicked(right)){
            page ++;
            setScreen(page);
        }
    }
}
