package gui;

import logic.Information;
import logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Michał on 2017-01-06.
 */
public class Board extends JPanel {

    private static Block[][] blocks;

    private Block firstChosenImage;
    private static int isNeedToMakeMove = 0;

    public Board() {

        blocks = new Block[8][8];
        firstChosenImage = null; // no image has been chosen

        int[] whereToPutImages = ImageGiver.giveImageNumber(); // creates an array; indexes are numbers of buttons on board
        // and content are numbers of images to place

        //Creates and adds buttons to board
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                JButton button = new JButton();
                button.setActionCommand("" + i + j);
                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String rowAndColumn = e.getActionCommand();
                        Block currentBlock =
                                blocks[Integer.parseInt(rowAndColumn.substring(0, 1))]
                                        [Integer.parseInt(rowAndColumn.substring(1, 2))];
                        onClicked(currentBlock);

                    }
                });
                button.setPreferredSize(new Dimension(100, 100));

                int imageIndex = 8 * i + j;

                String srcFolder = "img/";
                ImageIcon imageToPut = new ImageIcon(srcFolder + whereToPutImages[imageIndex] + ".jpg");

                blocks[i][j] = new Block(whereToPutImages[imageIndex], button, imageToPut, i, j);
                //button.setIcon(imageToPut);
                add(button);
            }
        }
    }

    public void onClicked(Block current) {

        current.getButton().setIcon(current.getImage());
        isNeedToMakeMove++;

        if(isNeedToMakeMove%2 != 0) {

            firstChosenImage = current;

        } else {

            Logic.getInstance().playerMove(firstChosenImage, current);
        }

        if(Logic.getInstance().getRank()[0] + Logic.getInstance().getRank()[1] == 32) {

            if(Logic.getInstance().getRank()[0] > Logic.getInstance().getRank()[1]) {

                JOptionPane.showMessageDialog(this.getParent(),
                        "Wygrał gracz! - " + Logic.getInstance().getRank()[0] +
                                " - " + Logic.getInstance().getRank()[1],
                        "Koniec gry!", 1);

            } else {

                JOptionPane.showMessageDialog(this.getParent(),
                        "Wygrał komputer! - " + Logic.getInstance().getRank()[0] +
                                " - " + Logic.getInstance().getRank()[1],
                        "Koniec gry!", 1);
            }
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
