public class GameModel {

    private int width, height;
    private boolean[][] board;

    public GameModel(int width, int height){
        this.width=width;
        this.height=height;
        board = new boolean[height][width];
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public boolean isON(int i, int j){
        return (i<height && j<width && board[i][j]);
    }

    public void reset(){
        board = new boolean[height][width];
    }

    public void set(int i, int j, boolean value){
        board[j][i]=value;
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