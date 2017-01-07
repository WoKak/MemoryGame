package logic;

import gui.Block;
import gui.Board;

import javax.swing.*;

/**
 * Created by Michał on 2017-01-06.
 */
public class Moves {

    public void playerMove(Block firstImage, Block secondImage, int rank[], ComputerMemory memory) {

        if(firstImage.getValue() == secondImage.getValue()) {

            firstImage.getButton().setEnabled(false);
            secondImage.getButton().setEnabled(false);

            rank[0]++;

        } else {

            memory.add(new Information(firstImage.getRow(), firstImage.getColumn(), firstImage.getValue()));
            memory.add(new Information(secondImage.getRow(), secondImage.getColumn(), secondImage.getValue()));
        }

        computerMove(firstImage, secondImage, rank, memory);
    }

    public void computerMove(Block firstImage, Block secondImage, int rank[], ComputerMemory memory) {

        int tmp = memory.isTwoKnown();

        if(tmp != -1) {

            Information firstImageFromDecison = new Information(memory.find(tmp-1).getRow(),
                    memory.find(tmp-1).getColumn(), memory.find(tmp-1).getNumber());

            Information secondImageFromDecison = new Information(memory.find(tmp).getRow(),
                    memory.find(tmp).getColumn(), memory.find(tmp).getNumber());

            mark(firstImageFromDecison, secondImageFromDecison);

        } else {


        }
    }

    public void mark(Information first, Information second) {

    }
}
