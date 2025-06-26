import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Input from the player,
 * display the board,
 * keep track of point totals,
 * manage everything else
 */
public class Game {

    //debugger
    boolean DEBUG = true;
    
    //variables
    private int height;
    private int width;

    private Snake snake;

    private String[][] board;

    //directions
    private int[] up = new int[]{0, -1};
    private int[] down = new int[]{0, 1};
    private int[] left = new int[]{-1, 0};
    private int[] right = new int[]{1, 0};

    //initializer 
    public Game(int height, int width){
        this.height = height;
        this.width = width;

        //initialize snake body
        List<int[]> initialBody = new ArrayList<>();
        initialBody.add(new int[]{0,0});
        initialBody.add(new int[]{1,0});
        initialBody.add(new int[]{2,0});
        initialBody.add(new int[]{3,0});

        this.snake = new Snake(initialBody, down);

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

    //listen for key pressed
    public void readKey(String key){
        if(key.equals("w")){
            moveSnake(up);
        }
        else if(key.equals("a")){
            moveSnake(left);
        }
        else if(key.equals("s")){
            moveSnake(down);
        }
        else if(key.equals("d")){
            moveSnake(right);
        }
    }

    public void moveSnake(int[] direction){
        int[] newDirection = direction;
        //get current head
        int[] currentHead = snake.head();
        //calculate new head position
        int[] newHead = new int[]{
            currentHead[0] + newDirection[0],
            currentHead[1] + newDirection[1]
        };

        //if new head direction is in bounds, move
        if(isInBounds(board, newHead[0], newHead[1])){
            if(DEBUG) System.out.println("No wall ahead");
            //set new direction
            snake.setDirection(newDirection);
            //take step with new head position
            snake.takeStep(newHead);
        } else if(DEBUG)System.out.println("Wall ahead");
    }

    public static boolean isInBounds(String[][] board, int row, int col){
        if(row < 0 || row >= board.length)
            return false;
        
        if(col < 0 || col >=board.length)
            return false;
        
        return true;
    }

    public static void main(String[] args){
        Game game = new Game(10, 20);
        game.render();
        
        Scanner scanner = new Scanner(System.in);
        String input;
        String key;

        while(true){
            System.out.println("Press WASD to move");
            key = scanner.nextLine();
            game.readKey(key);
            System.out.println("Press ENTER to move or EXIT to quit");
            input = scanner.nextLine();

            if(input.isEmpty()){
                game.render();
            }
            else if(input.equalsIgnoreCase("exit")){
                break;
            }
        }
        scanner.close();
    }
}
