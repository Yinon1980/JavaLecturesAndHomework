
import java.util.Random;
//Yinon Geridy Abokasis
public class Main {
    public static class Cell{    //make a deafult structure of cell
        public boolean state;
        public int neighbors;
        public boolean nextState;

        public Cell(boolean state) {
            this.state = state;
            this.nextState = false;
            this.neighbors = 0;
        }


    }

    public static void NeighborFinder(Cell[][] c){        //bunch of if statements basically, giving us the number of neighbors of a cell
        for (int i = 1; i < c.length - 1; i++){
            for (int j = 1; j < c[i].length - 1; j++){
                if (c[i-1][j-1].state)
                    c[i][j].neighbors++;

                if (c[i-1][j].state)
                    c[i][j].neighbors++;

                if (c[i-1][j+1].state)
                    c[i][j].neighbors++;

                if (c[i][j-1].state)
                    c[i][j].neighbors++;

                if (c[i-1][j+1].state)
                    c[i][j].neighbors++;

                if (c[i+1][j-1].state)
                    c[i][j].neighbors++;

                if (c[i+1][j].state)
                    c[i][j].neighbors++;

                if (c[i+1][j+1].state)
                    c[i][j].neighbors++;
            }
        }
    }

    public static void GameTurn(Cell[][] c){             //1 turn of the game of life will occur
        for (int i = 0; i < c.length - 1; i++){
            for (int j = 0; j < c[i].length - 1; j++){
                if (c[i][j].state){//if the current cell is alive run these checks

                    if (c[i][j].neighbors > 3)
                        c[i][j].nextState = false;
                    else
                        c[i][j].nextState = true;

                    if (c[i][j].neighbors < 2)
                        c[i][j].nextState = false;
                }
                 if (!c[i][j].state){      //if the cell is dead, then run this check
                     if (c[i][j].neighbors == 3)
                         c[i][j].nextState = true;   //what a miracle
                 }
            }
        }
        //now that the evaluation has ended, we can initialize the turn
        for (int i = 1; i < c.length; i++){
            for (int j = 1; j < c[i].length; j++){
                c[i][j].state =  c[i][j].nextState;
                c[i][j].neighbors = 0;  //reset number of neighbors ann the next state
                c[i][j].nextState = false;
            }
        }


    }

    public static Cell[][] createMatrix(int xSize, int ySize){
        Cell[][] matrix = new Cell[xSize][ySize];
        Random rn = new Random();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Cell(rn.nextBoolean());
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            matrix[matrix.length-1][i].state = false;
            matrix[0][i].state = false;
            matrix[i][matrix[i].length - 1].state = false;
            matrix[matrix[i].length - 1][i].state = false;
        }

        return matrix;
    }

    public static void printMatrix(Cell[][] matrix){
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                System.out.print(matrix[i][j].state ? "1 " : "0 ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void GamingTurns(int turns, Cell[][] matrix){
        for (int i = 0; i <= turns; i++){
            printMatrix(matrix);

            NeighborFinder(matrix);

            GameTurn(matrix);
        }
    }

    public static void main(String[] args) {
        Cell[][] matrix = createMatrix(12, 12);

        printMatrix(matrix);

        NeighborFinder(matrix);

        GameTurn(matrix);

        printMatrix(matrix);

    }
}