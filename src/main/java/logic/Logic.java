package logic;

import gui.Block;
import gui.Board;

import java.util.Random;

/**
 * Created by Michał on 2017-01-07.
 */
public class Logic {

    private  ComputerMemory computerMemory;
    private  int[] rank;
    private static Logic INSTANCE = null;


    private Logic() {
        computerMemory = new ComputerMemory();
        rank = new int[] {0, 0};
    }

    public static synchronized Logic getInstance() {

        if (INSTANCE == null) {

            INSTANCE = new Logic();

        }

        return INSTANCE;
    }

    public ComputerMemory getComputerMemory() {
        return computerMemory;
    }

    public int[] getRank() {
        return rank;
    }

    public void playerMove(Block firstImage, Block secondImage) {

        if (firstImage.getValue() == secondImage.getValue()) {

            firstImage.getButton().setEnabled(false);
            secondImage.getButton().setEnabled(false);

            rank[0]++;

        } else {

            computerMemory.add(new Information(firstImage.getRow(), firstImage.getColumn(), firstImage.getValue()));
            computerMemory.add(new Information(secondImage.getRow(), secondImage.getColumn(), secondImage.getValue()));
        }

        computerMove();
    }

    public void computerMove() {

        int indexOfPair = computerMemory.isTwoKnown();

        //checking whether position of two images is known
        if (indexOfPair != -1) {

            Information firstImageFromDecision = new Information(
                    computerMemory.find(indexOfPair - 1).getRow(),
                    computerMemory.find(indexOfPair - 1).getColumn(),
                    computerMemory.find(indexOfPair - 1).getNumber());

            Information secondImageFromDecision = new Information(
                    computerMemory.find(indexOfPair).getRow(),
                    computerMemory.find(indexOfPair).getColumn(),
                    computerMemory.find(indexOfPair).getNumber());

            mark(firstImageFromDecision, secondImageFromDecision);

        } else {

            //choosing one then checking if similar is known if not choosing second
            Information firstImageFromDecision = chooseOneImage();
            Information secondImageFromDecision;

            int indexOfSecond = computerMemory.isSecondKnown(firstImageFromDecision);

            if(indexOfSecond != -1) {

                secondImageFromDecision = new Information(
                        computerMemory.find(indexOfSecond).getRow(),
                        computerMemory.find(indexOfSecond).getColumn(),
                        computerMemory.find(indexOfSecond).getNumber());

                mark(firstImageFromDecision, secondImageFromDecision);

            } else {

                secondImageFromDecision = chooseOneImage();

                mark(firstImageFromDecision, secondImageFromDecision);
            }
        }
    }

    public void mark(Information first, Information second) {

        if(first.getNumber() != second.getNumber()) {

            computerMemory.add(first);
            computerMemory.add(second);

        } else {

            rank[1]++;
            Board.getBlockWithInformation(first).getButton().setEnabled(false);
            Board.getBlockWithInformation(second).getButton().setEnabled(false);
        }

    }

    public Information chooseOneImage() {

        Random random = new Random();

        int tmpRow = random.nextInt(8);
        int tmpColumn = random.nextInt(8);

        int value = Board.getBlockWithCoordinates(tmpRow, tmpColumn).getValue();

        return new Information(tmpRow, tmpColumn, value);
    }
}
