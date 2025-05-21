package Java.Snake;
import java.util.ArrayList;
import java.util.List;

/*
 * Store data about where our snake is and which direction it is heading,
 * very little else
 */
public class Snake {

    //variables
    private List<int[]> body;
    private int[] direction;

    //initialize
    public Snake(List<int[]> initBody, int[] initDirection){
        this.body = new ArrayList<>();
        for(int[] pos : initBody){
            this.body.add(new int[]{pos[0], pos[1]});
        }
        this.direction = initDirection;
    }

    public void takeStep(int[] newHeadPosition){
        body.remove(0); //remove tail
        body.add(newHeadPosition);  //add new head
    }

    public void setDirection(int[] newDirection){
        this.direction = newDirection;  //set new direction
    }

    public int[] head(){
        return body.get(body.size() - 1);   //last element is head
    }

    public List<int[]> getBody(){
        return body;
    }
    
}