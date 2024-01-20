import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner;
import java.util.NoSuchElementException;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.awt.Desktop;
/**
 * Changes:
 * StartWorld
 */
public class StartWorld extends World
{
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    
    private GreenfootImage background = new GreenfootImage("BackGrounds/Start BG Back.png");
    private GreenfootImage clouds = new GreenfootImage("BackGrounds/Start BG Clouds.png");
    private GreenfootImage island = new GreenfootImage("BackGrounds/Start BG Island.png");
    
    private GreenfootImage screen = new GreenfootImage(430, 720);
    
    private Button startButton;
    private Button load;
    private Button settings;
    private Button quit;
    
    private Scanner scan;
    private Scanner fileScanner;
    private String fileName;
    private static ArrayList<String> saveFile;
    public StartWorld()
    {   
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1, false);
        screen.setColor(new Color(255,255,255,45));
        screen.fillPolygon(new int[] {0,0,430,390}, new int[] {0,720,720,0}, 4);
        
        background.drawImage(screen, 0, 0);
        setBackground(background);
        
        initialize();
    }
    
    public void act(){
        //button actions here
        if(Greenfoot.mouseClicked(startButton)){
            Greenfoot.setWorld(new GameWorld(null));
        }
        if(Greenfoot.mouseClicked(load)){
            //Open();
        
            scan = new Scanner(System.in);
            System.out.println("Enter Your File Name: ");
            fileName = scan.nextLine();
            scan.close();
            try{
                fileScanner = new Scanner (new File(fileName));
                Greenfoot.setWorld(new GameWorld(fileName));
            }
            catch(FileNotFoundException e){
                System.out.println("Invalid File");
            }
            fileScanner.close();
        }
        if(Greenfoot.mouseClicked(settings)){
            //brings you to settings world/menu
        }
        if(Greenfoot.mouseClicked(quit)){
            Greenfoot.stop();
        }
    }
    
    public void initialize(){
        setPaintOrder(Button.class, Effect.class);
        startButton = new MenuButton("Start");
        load = new MenuButton("Load");
        settings = new MenuButton("Settings");
        quit = new MenuButton("Quit");
        addObject(startButton,160,200);
        addObject(load,160,320);
        addObject(settings,160,440);
        addObject(quit,160,560);
        addObject(new Effect(Effect.ROCK, island), SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
        addObject(new Effect(Effect.PULSE, clouds), SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
    }
    
    /*
    private void Open(){
        Desktop desktop = null;
        if (!Desktop.isDesktopSupported()) {
            return;
        }
        desktop = Desktop.getDesktop();
        String path = "C:\\Users";
        try {
            File fPath=new File(path);
            if(!fPath.exists()){
                return;
            }
            if(!fPath.isDirectory()){
                return;
                
            }
            desktop.open(fPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
    */
}
