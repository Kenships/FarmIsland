import greenfoot.*;

/**
 * Achievements for the game.
 * 
 * @author Ryan Du
 * @version Janurary 2024
 */
public class Achievement
{
    private GreenfootImage image;
    private String name;
    private String description;
    private boolean completed;
    private String completeImageIcon, incompleteImageIcon;
    private String notificationIcon;
    
    /**
     * Initializes the achievement and sets up the image icons.
     * 
     * @param name Achievement name.
     * @param description Achievement description.
     * @param type Achievement type.
     * @param num Index of the achievement.
     */
    public Achievement(String name, String description, String type, int num)
    {
        this.name = name;
        this.description = description;
        completed = false;
        completeImageIcon = type + "_" + num + "C";
        incompleteImageIcon = type + "_" + num + "I";
        notificationIcon = type + "_" + num;
    }
    
    /**
     * Gets the name of the achievement.
     * 
     * @return String name of the achievement.
     */
    public String getName(){
        return name;
    }
    /**
     * Sets the name of the achievement to the specified name.
     * 
     * @param n The specified name. 
     */
    public void setName(String n){
        name = n;
    }
    /**
     * Gets the description of the achievement.
     * 
     * @return String Description of the achievement.
     */
    public String getDescription(){
        return description;
    }
    /**
     * Sets the description of the achievement to the specified description.
     * 
     * @param d The specified description. 
     */
    public void setDescription(String d){
        description = d;
    }
    /**
     * Gets the completion status of the achievement.
     * 
     * @return boolean Completion status of the achievement.
     */
    public boolean getCompleted(){
        return completed;
    }
    /**
     * Sets the completion status to be true.
     * 
     */
    public void setCompleted(){
        completed = true;
    }
    /**
     * Sets the image of the achievement to the image of the specified String.
     * 
     * @param img The specified String.
     */
    public void setImage(String img){
        image = new GreenfootImage(img);
    }
    /**
     * Gets the incomplete image icon.
     * 
     * @return String The incomplete image icon.
     */
    public String getIncompleteIcon(){
        return incompleteImageIcon;
    }
    /**
     * Gets the complete image icon.
     * 
     * @return String The complete image icon.
     */
    public String getCompleteIcon(){
        return completeImageIcon;
    }
    /**
     * Gets the notification image icon.
     * 
     * @return String The notification image icon.
     */
    public String getNotificationIcon(){
        return notificationIcon;
    }
}
