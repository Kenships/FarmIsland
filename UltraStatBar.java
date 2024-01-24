import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * <p>New and Improved Stat Bar (Formerly Health Bar). This stat bar can be set to follow
 * an Actor or stay in one place (see constructors). This stat bar may have customized colors,
 * can hide when at full, and can have a customized border. This class aims to be as flexible
 * as possible, allowing it to be simple to use for beginners (easy 0 or 2 parameter constructor)
 * while also highly flexible for those who want to provide more specific parameters (multiple bars
 * in custom colours and sizes with custom offsets and borders).</p>
 * 
 * <p>Instructions:</p>
 * <ol>
 *  <li>Choose a constructor. They range from super-simple to ultra-customizable. 
 *  Each one has a set of Parameters for which you must set values. 
 *  These may be integers (like maximum hit points) or Colors. In order to follow another
 *  Actor around, msot constructors require a Target, which can be any Actor. If you don't want
 *  the bar to follow anything, use null for this parameter and the bar will stay wherever you place it</li>
 *  <li>Declare the UltraStatBar in your class variable section. This may be done in your World or in the Actor.</li>
 *  <li>Initialize the statBar.
 *   <ul>
 *    <li>If you're placing the bar inside the Actor, be sure to use <code>this</code></li> for the Target, 
 *       for example:<br><code>statBar = new StatBar (100, player);</code></li>
 *    <li>If you're placing this in the World, then pass it the reference to the Actor, 
 *       for example <br><code>statBar = new StatBar (100, player)</code>;</li>
 *   </ul> 
 *  </li>
 *  <li> </li>
 * </ol>
 * <p>
 * Implementation - If using multiple bars, all arrays must be the same size. To 
 * optimize the appearance choose a height such that:
 * <p><code> (height - (borderThickness * 2)) % numBars == 0  </code></p>
 * <p>In other words, after factoring out the border, the size should be evenly divisible by 
 * the number of bars, so that all bars end up the same size.</p>
 * <p><b>Version Notes:</b></p>
 * <ul>
 * <li>Now has a boolean to determine whether it will hide itself when Val is full.</li>
 * <li>Now has a set of constructors to allow simple and complex implementation.</li>
 * <li>2.1.0 --> Added a border feature, allows customization of thickness and colour</li>
 * <li>2.1.2 --> Improved naming of some variables, improved efficiency of update</li>
 * <li>2.2.0 --> Better control of hideAtMax, now with default constant, accessor and mutator</li>
 * </ul>
 * 
 * @author Jordan Cohen
 * @version 2.2.0 - Jan 2023
 */
public class UltraStatBar extends SuperSmoothMover 
{
    
    public HashMap<Integer,Slider> bars;
    Slider dragSlider;
    
    private boolean isAdjustable;
    private boolean isDragging = false;
    private int totalHeight;
    private int totalWidth;
    double totalDistance;

    private int sliderCount;
    private int sliderHeight;
    private int sliderOffset;
    private int actorOffset;
    
    private GreenfootImage statSlider;
    private GreenfootImage blank;

    private Actor target;
    
    public UltraStatBar(Slider slider){
        this(slider, 36, null);
    }
    
    public UltraStatBar(Slider slider, int actorOffset, Actor target){
        bars = new HashMap<>();        
        //default settings
        sliderOffset = 0;
        isAdjustable = false;
        
        // the key is the order in which the stat bars will appear (top down)
        bars.put(0 , slider);
        this.target = (Actor) target;
        
        sliderCount = bars.size();
        this.actorOffset = actorOffset;       
        
        statSlider = new GreenfootImage (slider.getWidth(), slider.getHeight());
        blank = new GreenfootImage (1, 1);
        
        rescale(slider);
        
        forceUpdate(slider,slider.getCurrVal());
    }
    
    public void makeAdjustable(){
        isAdjustable = true;
    }
    
    public void rescale(Slider slider){
        totalHeight += slider.getHeight();
        if(!(bars.isEmpty() || bars.size() == 1)){
            totalHeight += sliderOffset;
        }
         
        if(slider.getWidth() > totalWidth){
            totalWidth = slider.getWidth();
        }
        statSlider.clear();
        statSlider.scale(totalWidth, totalHeight);
    }
    
    public void addSlider(Slider slider){
        bars.put(getNextSliderNumber(), slider);
        rescale(slider);
        forceUpdate(slider, slider.getCurrVal());
    }
    
    public void addSlider(int sliderNumber, Slider slider){//////////////////////
        bars.put(sliderNumber, slider);
        rescale(slider);
        forceUpdate(slider, slider.getCurrVal());
    }
    
    private int getNextSliderNumber (){
        int i = 0;
        while(bars.containsKey(i)){
            i++;
        }
        return i;        
    }
    
    public Slider getSlider(int sliderNumber){
        return bars.get(sliderNumber);
    }
    
    public int getValueFromSlider(int sliderNumber){
        return bars.get(sliderNumber).getCurrVal();
    }
    
    public void setSliderOffset(int sliderOffset){
        totalHeight -= this.sliderOffset * (bars.size() - 1);
        totalHeight += sliderOffset * (bars.size() - 1);
        this.sliderOffset = sliderOffset;
        statSlider.scale(totalWidth, totalHeight);
    }
    
    public void addedToWorld (World w){
        moveMe();
    }
    
    /**
     * The only purpose of the act method is to follow the target Actor. If you'd rather control this yourself,
     * delete this act() method and call moveMe() directly whenever your Actor moves. 
     */
    public void act () {
        
        if(isAdjustable){
            slideSlider();
        }else{
            //redundancy in case the mouse was let go but dragging was not reset
            isDragging = false;
        }
        moveMe();
    }
    
    public void slideSlider(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                // Start dragging
                if (Greenfoot.mousePressed(this)) {
                    int sliderHeight = this.getY() - statSlider.getHeight()/2;
                    int sliderStart = this.getX() - statSlider.getWidth()/2;
                    for(int key : bars.keySet()){
                        Slider slider = bars.get(key);
                        if(mouse.getY() >= sliderHeight && mouse.getY() <= sliderHeight + slider.getHeight()){
                            //fix me
                            if(mouse.getX() <= sliderStart + slider.getWidth()){
                                System.out.print(key);
                                dragSlider = slider;
                                isDragging = true;
                                break;
                            }
                            
                        }
                        sliderHeight += slider.getHeight() + sliderOffset;
                    }
                    
                }
                if (Greenfoot.mouseDragEnded(null)) {
                    isDragging = false;
                }
                // While dragging, move the actor and calculate distance
                if (isDragging) {
                    totalDistance = mouse.getX() - this.getX() + totalWidth/2;
                    if(totalDistance > dragSlider.getWidth()){
                        update(dragSlider, dragSlider.getMaxVal());
                    }
                    else if(totalDistance < -1 * dragSlider.getWidth()){
                        update (dragSlider, 0);
                    }
                    else{
                        update(dragSlider, (int)(totalDistance/dragSlider.getScale()));
                    }
                    if (Greenfoot.mouseClicked(null)) {
                        //will reset draggin if mouse was clicked really fast and let go
                        isDragging = false;
                    }                                        
                }
                World world = getWorld();
                int worldWidth = world.getWidth();
                int worldHeight = world.getHeight();
                
                int edgeThreshold = 2; // You can change this value as needed
                //prevent dragging to not reset if let go offScreen
                if (mouse.getX() <= edgeThreshold || mouse.getX() >= worldWidth - edgeThreshold ||
                    mouse.getY() <= edgeThreshold || mouse.getY() >= worldHeight - edgeThreshold) {
                    // Mouse has moved to the edge of the screen
                    //isDragging = false; 
                }
            }       // Stop dragging
    }
    
    /**
     * For projects where efficiency is more important, DELETE THE ACT METHOD and call this directly instead.
     * 
     * This allows the statBar object to be reactive, only moving when told, for example only acting
     * when the Actor it is following is moving, rather than acting each act().
     * 
     */
    public void moveMe (){
        if (target != null && getWorld() != null){
            if (target.getWorld() != null)
            {
                setLocation (target.getX(), target.getY() + actorOffset);
            }
            else
            {
                getWorld().removeObject(this);
            }
        }    
    }

    public void update (int newCurrVal){
        update (0, newCurrVal);
    }
    public void update (int sliderNumber, int newCurrVal){
        update(bars.get(sliderNumber), newCurrVal);
    }
    public void update (Slider slider, int newCurrVal)
    {
        //if the current value does not match the new value
        boolean updateRequired = slider.getCurrVal() != newCurrVal;

        if (updateRequired){
            forceUpdate(slider, newCurrVal);
        }
    
    }
    public void forceUpdate(Slider slider, int currVal){
            slider.setCurrVal(currVal);

            if (slider.isHidden()){ // if the hide when full feature is on, figure it if this bar should hide
                if (slider.isFull()) // This will only happen if I looked at all bars, and they are all full
                {
                    this.setImage(blank); // set image to a 1x1 transparent image I created above
                    //redraw();
                } else {

                    redraw();
                    this.setImage(statSlider);   
                }
            }
            else
            {

                redraw();
                this.setImage(statSlider);
            }
    }
    /**
     *   The Actual drawing method that draws the bars onto the image for this Actor
     *      
     *      This method is private because we don't want another method to 
     *      waste time calling this if no changes have been made to the 
     */
    private void redraw(){
        int sliderStartHeight = 0;
        for(int key : bars.keySet()){
            Slider slider = bars.get(key);
            
            if(slider.isHidden() && slider.isFull()){
                continue;
            }
            
            statSlider.setColor (slider.getBorderColor());
            statSlider.fillRect(0, sliderStartHeight, slider.getWidth(), slider.getHeight());
            
            double percentFilled = slider.getPercentFill();
            int sliderWidth = slider.getWidth() - 2*slider.getBorderThickness();
            int sliderHeight = slider.getHeight() - 2*slider.getBorderThickness();
            int filledSliderWidth = (int) (percentFilled * sliderWidth);
            int missingSliderWidth = sliderWidth - filledSliderWidth;
            statSlider.setColor(slider.getFillColor());
            statSlider.fillRect(slider.getBorderThickness(), slider.getBorderThickness() + sliderStartHeight,filledSliderWidth, sliderHeight);
            statSlider.setColor(slider.getEmptyColor());
            statSlider.fillRect(slider.getBorderThickness() + filledSliderWidth, slider.getBorderThickness() + sliderStartHeight, missingSliderWidth, sliderHeight);
            sliderStartHeight += slider.getHeight() + sliderOffset;
        }

    }
}
