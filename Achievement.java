import greenfoot.*;

/**
 * Write a description of class Achievement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Achievement
{
    private GreenfootImage image;
    private String name;
    private String description;
    private boolean completed;
    private String completeImageIcon, incompleteImageIcon;
    private String notificationIcon;
    
    public Achievement(String name, String description, String type, int num)
    {
        this.name = name;
        this.description = description;
        completed = false;
        completeImageIcon = type + "_" + num + "C";
        incompleteImageIcon = type + "_" + num + "I";
        notificationIcon = type + "_" + num;
    }
    
    // Getter and Setters
    public String getName(){
        return name;
    }
    public void setName(String n){
        name = n;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String d){
        description = d;
    }
    public boolean getCompleted(){
        return completed;
    }
    public void setCompleted(){
        completed = true;
    }
    public void setImage(String img){
        image = new GreenfootImage(img);
    }
    public String getIncompleteIcon(){
        return incompleteImageIcon;
    }
    public String getCompleteIcon(){
        return completeImageIcon;
    }
    public String getNotificationIcon(){
        return notificationIcon;
    }
}
