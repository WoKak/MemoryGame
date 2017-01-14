package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.Information;
import logic.Logic;

/**
 * Created by Michał on 2017-01-06.
 */
public class Board extends JPanel {

    private static Block[][] blocks;

    private Block firstChosenImage;

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

                int imageIndex = 8 * i + j;

                String srcFolder = "img/";
                ImageIcon imageToPut = new ImageIcon(srcFolder + whereToPutImages[imageIndex] + ".jpg");

                blocks[i][j] = new Block(whereToPutImages[imageIndex], button, imageToPut, i, j);
                // !!! if you want to see where all pictures are uncomment this line:
                // button.setIcon(imageToPut);
                add(button);
            }
        }
    }

    public void onClicked(Block current) {

        current.getButton().setIcon(current.getImage());

        if (this.firstChosenImage == null) {
            this.firstChosenImage = current;
            System.out.println("" + current.getValue());
        } else {
            System.out.println("" + current.getValue());
            System.out.println("PARA CZY NIE PARA?");
            try {
                Thread.sleep(2000); // sleep so user can see what image is on button that they clicked ( doesn't work! )
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            Logic.getInstance().playerMove(firstChosenImage, current);
            this.firstChosenImage.getButton().setIcon(null);
            this.firstChosenImage = null;
            current.getButton().setIcon(null);
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
