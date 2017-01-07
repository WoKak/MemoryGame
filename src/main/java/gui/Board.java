package gui;

import logic.ComputerMemory;
import logic.Logic;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Michał on 2017-01-06.
 */
public class Board extends JPanel {

    private static Block[][] blocks;
    private static Logic logic;

    public Board() {

        blocks = new Block[8][8];

        //Creates and adds buttons to board
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                JButton button = new JButton();
                button.setPreferredSize(new Dimension(100, 100));
                blocks[i][j] = new Block(1, button, null, i, j); //instead of "1" should be algorythm and also image needs
                add(button);                                          //replacement
            }
        }
    }

    public static JButton[][] getBlocks() {
        return blocks;
    }
}
