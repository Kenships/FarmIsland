import java.util.Comparator;

/**
 * Write a description of class GridComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GridComparator implements Comparator<GridPath>
{
    // instance variables - replace the example below with your own
    public int compare(GridPath g1, GridPath g2) {
        if(g1.getTotalCost() != g2.getGCost()){
            return Integer.compare(g1.getTotalCost(), g2.getTotalCost());
        }
        else{
            return Integer.compare(g1.getHCost(), g2.getHCost());
        }
    }
}