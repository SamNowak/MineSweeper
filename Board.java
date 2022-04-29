public class Board {
    //public static ArrayList<Color> colorBoard;
    public static int size = 7;
    private static int[][] key;
    public static int[][] gameBoard;
    private static int totalMines;

    public Board(){
        key = new int[size][size];
        gameBoard = new int[size][size];
        //colorList();
        MMethod();
        totalMines = (int)((size * size) * 0.15625);
        putBombs();
        fillKey();
    }

    //fills the board with mines randomly
    public void putBombs(){
        int row = (int)(size * Math.random());
        int col = (int)(size * Math.random());
        for(int i = 0; i < totalMines; i++){
            while(key[row][col] == 10)
            {
                row = (int)(size * Math.random());
                col = (int)(size * Math.random());
            }
            key[row][col] = 10;
        }
    }

    public void fillKey(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(key[i][j] != 10){
                    key[i][j] = findNumberOfMines(i,j);
                }
            }
        }
    }

    public int findNumberOfMines(int row, int col)
    {
        int numberOfBomb = 0;

        if( row -1 >= 0 &&
                col -1 >= 0 &&
                key[row-1][col-1] == 10)
        {
            numberOfBomb++;
        }

        if( row -1 >= 0 &&
                col >= 0 &&
                key[row-1][col] == 10)
        {
            numberOfBomb++;
        }

        if( row -1 >= 0 &&
                col +1 < key[0].length &&
                key[row-1][col+1] == 10)
        {
            numberOfBomb++;
        }

        if( row >= 0 &&
                col -1 >= 0 &&
                key[row][col-1] == 10)
        {
            numberOfBomb++;
        }

        if( row >= 0 &&
                col +1 < key[0].length &&
                key[row][col+1] ==10)
        {
            numberOfBomb++;
        }

        if( row + 1 < key.length &&
                col -1 >= 0 &&
                key[row+1][col-1] == 10)
        {
            numberOfBomb++;
        }

        if( row +1 < key.length &&
                col >= 0 &&
                key[row+1][col] == 10)
        {
            numberOfBomb++;
        }

        if( row +1 < key.length &&
                col +1 < key[0].length &&
                key[row+1][col+1] ==10)
        {
            numberOfBomb++;
        }
        //changing the int to a string
        //return numberOfBombs;
        return numberOfBomb;
    }

    public static void keyToBoard(int row, int col){
        if(gameBoard[row][col] != 11) gameBoard[row][col] = key[row][col];
    }

    public static void printGameBoard(){
        for(int i =0; i < size; i++){
            for(int j=0; j < size;j++){
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void MMethod(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                gameBoard[i][j] = -1;
            }
        }
    }

    public static int getIndex(int row, int col){
        return key[row][col];
    }

    public static void clickedBomb(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                gameBoard[i][j] = 10;
                key[i][j] = 10;
            }
        }
    }

    public static void putFlag(int row, int col){
        //11 means flag
        gameBoard[row][col]= 11;
    }
    public static boolean checkWinOrLost(){
        int bombToFlag = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(key[i][j] == 10 && gameBoard[i][j] ==11){
                    bombToFlag++;
                }
            }
        }
        return bombToFlag == totalMines;
    }

    public static int checkFlags(){
        int flagsPlaced = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(gameBoard[i][j] ==11){
                    flagsPlaced++;
                }
            }
        }
        return flagsPlaced;
    }

    public static int numOfMines(){
        return totalMines;
    }

}
