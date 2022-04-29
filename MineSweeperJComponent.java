import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MineSweeperJComponent extends JComponent {
     static Board board;

    @Override
    public void paintComponent(Graphics g) {
        // Cast to Graphics2D
        Graphics2D graphicsObj = (Graphics2D)g;
        ArrayList<Color> colorBoard = new ArrayList<>();
        colorBoard.add(new Color(255, 255, 255, 255));
        colorBoard.add(new Color(255, 251, 0));
        colorBoard.add(new Color(255, 106, 0));
        colorBoard.add(new Color(255, 149, 149));
        colorBoard.add(new Color(218, 12, 12));
        colorBoard.add(new Color(126, 73, 4));
        colorBoard.add(new Color(124, 67, 67));
        colorBoard.add(new Color(108, 4, 4));
        colorBoard.add(new Color(61, 58, 58));
        colorBoard.add(new Color(0, 0, 0));

        // Write your drawing instructions
        ArrayList<Rectangle> list = new ArrayList<>();
        for(int i = 0; i < 7; i+=1){
            for(int j = 0; j < 7; j+=1){
                list.add(new Rectangle(10 + i * 20, 10 + j * 20, 20, 20));
            }
        }

        ArrayList<Rectangle> colorBlocks = new ArrayList<>();
        for(int i = 0; i < 10;i++){
            colorBlocks.add(new Rectangle(10, 250 + i * 20,20,20));
        }
        //Add the color block under the grid and text to show what color is what number
        for(int i = 0; i < 10; i++){
            graphicsObj.setColor(colorBoard.get(i));
            graphicsObj.fill(colorBlocks.get(i));
            graphicsObj.setColor(Color.BLACK);
            graphicsObj.drawRect(colorBlocks.get(i).x, colorBlocks.get(i).y, colorBlocks.get(i).width, colorBlocks.get(i).height);
            if(i != 9) graphicsObj.drawString(": This Color represents " + i + " bomb touching", 45,260 + i *20);
            else graphicsObj.drawString(": This is a bomb",45,440);

        }
        //adding intrustions of how to click a flag
        graphicsObj.setColor(Color.magenta);
        graphicsObj.fill(new Rectangle(300,150,20,20));
        graphicsObj.setColor(Color.black);
        graphicsObj.drawRect(300,150,20,20);
        graphicsObj.drawString(": Double click a square to place a flag.", 320,165);

        Color binColor1 = new Color(0, 0, 255);
        for(int i = 0; i < list.size(); i++){ //run this throught this class each click
            // Draw Number/Bomb First
            //LeftOn of getting color.
            if(board.gameBoard[i%board.size][i/board.size] == -1) {
                graphicsObj.setColor(binColor1);
            }
            else if(board.gameBoard[i%board.size][i/board.size] >= 0 && board.gameBoard[i%board.size][i/board.size] < 10){
                int colorIndex = board.findNumberOfMines(i%board.size,i/board.size);
                graphicsObj.setColor(Color.black);
                graphicsObj.setColor(colorBoard.get(colorIndex));
            }
            else if(board.gameBoard[i%board.size][i/board.size] == 11){
                graphicsObj.setColor(Color.MAGENTA);

            }
            else{
                //if you hit a 10
                graphicsObj.drawString("YOU LOST!", 200,200);
                board.clickedBomb();
            }
            graphicsObj.fill(list.get(i));
            graphicsObj.setColor(Color.BLACK);
            graphicsObj.drawRect(list.get(i).x, list.get(i).y, list.get(i).width, list.get(i).height);
        }

        //check if you placed the amount of flags to bombs
        if(board.checkFlags() == board.numOfMines()){
            if(board.checkWinOrLost()){
                graphicsObj.drawString("YOU WIN!", 200,200);
                graphicsObj.setColor(Color.green);
                graphicsObj.fill(new Rectangle(10,10,150,150));
            }
            else{
                graphicsObj.drawString("YOU LOST!", 200,200);
                graphicsObj.setColor(Color.BLACK);
                graphicsObj.fill(new Rectangle(10,10,150,150));
            }
        }

    }

    public static void setBoard(){
        board = new Board();
    }

    public static void updateBoard(int row, int col){
        board.keyToBoard(row,col);
        board.printGameBoard();
    }

}