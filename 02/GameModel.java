import java.util.Random;

public class GameModel {

    private int width, height;
    private boolean[][] board;
    private int clickCount;
    private Solution solution;
    private Random random;

    public GameModel(int width, int height){
        this.width=width;
        this.height=height;
        board = new boolean[height][width];
        random = new Random();
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public boolean isON(int i, int j){
        return (i<height &&  j < width && board[i][j]);
    }

    public void reset(){
        board = new boolean[height][width];
        clickCount = 0;
    }

    public void set(int i, int j, boolean value){
        board[j][i]=value;
    }

    public void click (int i, int j){

        clickCount++;
        board [i][j] = !board [i][j];

        if (i> 0){
            board[i-1][j] = !board [i-1][j];
        }

        if (i < height-1){
            board[i+1][j] = !board [i+1][j];
        }

        if (j> 0){
            board[i][j-1] = !board [i][j-1];
        }

        if (j < width-1){
            board[i][j+1] = !board [i][j+1];
        }
    }

    public int getNumberOfSteps(){
        return clickCount;
    }

    public void resetCount(){
        clickCount = 0;
    }

    public boolean isFinished(){
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                if (!board[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public void randomize(){
        do{
            for (int i=0;i<width;i++){
                for (int j=0;j<height;j++){
                    set(i, j, Math.random() < 0.5);
                }
            }
        }while(LightsOut.solve(this).size()==0);
    }

    public void setSolution(){
        solution = LightsOut.solveShortest(this);
    }

    public boolean solutionSelects(int i, int j){
        return (solution!=null&&solution.get(i,j));
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("[");
        for(int i = 0; i < height; i++){
            out.append("[");
            for(int j = 0; j < width ; j++) {
                if (j>0) {
                    out.append(",");
                }
                out.append(board[i][j]);
            }
            out.append("]"+(i < height -1 ? ",\n" :""));
        }
        out.append("]");
        return out.toString();
    }

}
