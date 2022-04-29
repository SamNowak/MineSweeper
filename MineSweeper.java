import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MineSweeper extends Frame implements MouseListener, ActionListener {

    private static JFrame appFrame;
    public static MineSweeperJComponent mineSweeperJComponent;
    public static Timer timer;
    public static int count;

    MineSweeper() {}
    public static void main(String[] args) {

        //creating the graphics frames
        appFrame = new JFrame();

        //creating the button
        JButton loseButton = new JButton("Play Again");
        loseButton.setBounds(400,400,95,30);
        appFrame.add(loseButton);


        //class where I can change the image
        MineSweeper m = new MineSweeper();
        appFrame.addMouseListener(m);
        mineSweeperJComponent = new MineSweeperJComponent();

        //action a lose button
        loseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(m, loseButton.getText());
                MineSweeperJComponent.setBoard();
                appFrame.repaint();
            }
        });

        //Timer for flags
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        timer.setRepeats(false);

        //using clicking things
        mineSweeperJComponent.setBoard();


        //size, title, close on close
        appFrame.setSize(600,500);
        appFrame.setTitle("MineSweeper");
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //adding from my mineSweeperJComponent class
        appFrame.add(mineSweeperJComponent);



        appFrame.setVisible(true);



    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int row = (y-40)/20;
        int col = (x-17)/20;
        System.out.print("("+ x +", ");
        System.out.println( y + ")");
        System.out.println("Row: " + row + " Col: " + col);
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((x >= 17 && x < 157) && (y >= 40 && y < 180)){
                    appFrame.repaint(); //It is updating!! YAY
                    MineSweeperJComponent.updateBoard(row, col);
                    //if you click a bomb aka a 10 you lose.
                    if(Board.getIndex(row,col) == 10){
                        appFrame.repaint();
                    }
                }
            }
        });
        timer.setRepeats(false);
        timer.restart();

    }
    @Override
    public void mousePressed(MouseEvent e) {

        int clicks = e.getClickCount();
        int x = e.getX();
        int y = e.getY();
        int row = (y-40)/20;
        int col = (x-17)/20;
        if(clicks == 2)
            if ((x >= 17 && x < 157) && (y >= 40 && y < 180)) {
                appFrame.repaint(); //It is updating!! YAY
                MineSweeperJComponent.board.putFlag(row, col);
                }
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}


