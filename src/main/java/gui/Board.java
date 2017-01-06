package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Michał on 2017-01-06.
 */
public class Board extends JPanel {

    JButton[][] blocks;

    public Board() {

        blocks = new JButton[8][8];

        //Creates and adds buttons to board
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                JButton button = new JButton();
                button.setPreferredSize(new Dimension(100, 100));
                blocks[i][j] = button;
                add(button);
            }
        }
    }

    public JButton[][] getBlocks() {
        return blocks;
    }
}
