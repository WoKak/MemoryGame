package logic;

import gui.Block;
import gui.Board;

import javax.swing.*;

/**
 * Created by Michał on 2017-01-06.
 */
public class Moves {

    public void playerMove(Block firstImage, Block secondImage) {

        if(firstImage.getValue() == secondImage.getValue()) {

            firstImage.getButton().setEnabled(false);
            secondImage.getButton().setEnabled(false);

            //something to remember points
        } else {

        }
    }

    public void computerMove() {


    }
}
