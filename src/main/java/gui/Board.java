package gui;

import logic.Logic;

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
    private static Logic logic;

    private Block firstChosenImage;

    public Board() {

        blocks = new Block[8][8];
        firstChosenImage = new Block(99, null, null, 99, 99); // no image has been chosen, default value is 99; 

        //Creates and adds buttons to board
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                JButton button = new JButton();
                button.setActionCommand("" + i + j);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button.setEnabled(false);
                        String rowAndColumn = e.getActionCommand();
                        Block currentBlock = blocks[Integer.parseInt(rowAndColumn.substring(0, 1))][Integer.parseInt(rowAndColumn.substring(1, 2))];
                        onClicked(currentBlock);
                        // what happens here is that every button has hidden field called ActionCommand;
                        // here the ActionCommand string is set as a "ij", where i is row number and j is column number ( or the other way around XD )
                        // with getActionCommand the String is got, then used to get clicked button's Block from blocks[][] array
                    }
                });
                button.setPreferredSize(new Dimension(100, 100));
                blocks[i][j] = new Block(1, button, null, i, j); //instead of "1" should be algorythm and also image needs
                add(button);                                          //replacement
            }
        }
    }

    public void onClicked(Block current) {

        if (this.firstChosenImage.getValue() == 99) {
            this.firstChosenImage = current;
            System.out.println("" + current.getValue());
        } else {
            System.out.println("" + current.getValue());
            System.out.println("PARA CZY NIE PARA?");
            logic.playerMove(firstChosenImage, current);
            this.firstChosenImage = new Block(99, null, null, 99, 99);
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
