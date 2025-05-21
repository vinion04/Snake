package Java.Snake;
import java.util.List;
import java.util.ArrayList;
/*
 * Input from the player,
 * display the board,
 * keep track of point totals,
 * manage everything else
 */
public class Game {
    
    //variables
    private int height;
    private int width;

    private Snake snake;

    private String[][] board;

    //directions
    private int[] up = new int[]{0,1};
    private int[] down = new int[]{0, -1};
    private int[] left = new int[]{-1, 0};
    private int[] right = new int[]{1, 0};


    //initializer 
    public Game(int height, int width){
        this.height = height;
        this.width = width;

        List<int[]> initialBody = new ArrayList<>();
        initialBody.add(new int[]{0,0});
        initialBody.add(new int[]{1,0});
        initialBody.add(new int[]{2,0});
        initialBody.add(new int[]{3,0});

        this.snake = new Snake(initialBody, up);
    }

    //create matrix
    public String[][] boardMatrix(){
        String[][] matrix = new String[height][width];

        return matrix;
    }

    //render game state
    public void render(){

        //render board
        board = this.boardMatrix();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++)
                board[i][j] = " ";
        }

        //render snake body
        List<int[]> body = snake.getBody();
        for (int i = 0; i < body.size() - 1; i++){
            int[] pos = body.get(i);
            board[pos[1]][pos[0]] = "O";    
        }

        //render snake head
        int[] head = snake.head();
        board[head[1]][head[0]] = "X";

        //print top border
        System.out.print("+");
        System.out.print("-".repeat(width));
        System.out.println("+");

        //print board
        for (int i = 0; i < height; i++){
            System.out.print("|");
            for (int j = 0; j < width; j++)
                System.out.print(board[i][j]);
            System.out.println("|");
        }

        //print bottom border
        System.out.print("+");
        System.out.print("-".repeat(width));
        System.out.println("+");
    }

    public static void main(String[] args){
        Game game = new Game(10, 20);
        game.render();
    }
}
