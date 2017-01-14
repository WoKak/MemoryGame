package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.Information;

/**
 * Created by Michał on 2017-01-06.
 */
public class Board extends JPanel {

    private static Block[][] blocks;
    // private static Logic logic;

    private Block firstChosenImage;

    public Board() {

        blocks = new Block[8][8];
        firstChosenImage = null; // no image has been chosen

        int[] whereToPutImages = ImageGiver.giveImageNumber(); // creates an array; indexes are numbers of buttons on board
        // and content are numbers of images to place

        // for( int i =0; i<64; i++)
        //   System.out.println(""+whereToPutImages[i]);
        //Creates and adds buttons to board
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                int imageIndex = 10 * i + j - i * 3;
                ImageIcon imageToPut = new ImageIcon("img/" + whereToPutImages[imageIndex] + ".jpg");

                JButton button = new JButton("",imageToPut);
                button.setActionCommand("" + i + j);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //    button.setEnabled(false);
                        String rowAndColumn = e.getActionCommand();
                        Block currentBlock = blocks[Integer.parseInt(rowAndColumn.substring(0, 1))][Integer.parseInt(rowAndColumn.substring(1, 2))];
                        onClicked(currentBlock);
                        // what happens here is that every button has hidden String field called ActionCommand;
                        // here the ActionCommand string is set as a "ij", where i is row number and j is column number ( or the other way around XD )
                        // with getActionCommand the String is got, then used to get clicked button's Block from blocks[][] array
                    }
                });
                button.setPreferredSize(new Dimension(100, 100));
                
               // System.out.println("" + imageIndex);

                blocks[i][j] = new Block(whereToPutImages[imageIndex], button, imageToPut, i, j);
                add(button);
            }
        }
    }

    public void onClicked(Block current) {

        if (this.firstChosenImage == null) {
            this.firstChosenImage = current;
            System.out.println("" + current.getValue());
        } else {
            System.out.println("" + current.getValue());
            System.out.println("PARA CZY NIE PARA?");
            Logic.getInstance().playerMove(firstChosenImage, current);
            this.firstChosenImage = null;
        }

    }

    public static JButton[][] getBlocks() {
        return blocks;
    }

    public static Block getBlockWithInformation(Information info) {
        int x = info.getRow();
        int y = info.getColumn();

        return blocks[x][y];
    }

    public static Block getBlockWithCoordinates(int x, int y) {

        return blocks[x][y];

    }
}
