import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Loading here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Loading extends SuperSmoothMover
{
    GreenfootImage image;
    SuperTextBox textBox;
    public Loading(){
        image = new GreenfootImage("BackGrounds/Loading Background.png");
        setImage(image);
        textBox = new SuperTextBox("Type here...", new Font("Arial", 16), 200);
        
    }
    
    
}
