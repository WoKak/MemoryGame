package logic;

import gui.Block;
import gui.Board;

import java.util.Random;

/**
 * Created by Michał on 2017-01-07.
 */
public class Logic {

    private ComputerMemory computerMemory;
    private int[] rank;
    private Board board;


    public Logic() {
        this.computerMemory = new ComputerMemory();
        this.rank = new int[] {0, 0};
        this.board = new Board();
    }

    public ComputerMemory getComputerMemory() {
        return computerMemory;
    }

    public void setComputerMemory(ComputerMemory computerMemory) {
        this.computerMemory = computerMemory;
    }

    public int[] getRank() {
        return rank;
    }

    public void setRank(int[] rank) {
        this.rank = rank;
    }

    public void playerMove(Block firstImage, Block secondImage) {

        if (firstImage.getValue() == secondImage.getValue()) {

            firstImage.getButton().setEnabled(false);
            secondImage.getButton().setEnabled(false);

            rank[0]++;

        } else {

            this.computerMemory.add(new Information(firstImage.getRow(), firstImage.getColumn(), firstImage.getValue()));
            this.computerMemory.add(new Information(secondImage.getRow(), secondImage.getColumn(), secondImage.getValue()));
        }

        computerMove();
    }

    public void computerMove() {

        int indexOfPair = this.computerMemory.isTwoKnown();

        //checking whether position of two images is known
        if (indexOfPair != -1) {

            Information firstImageFromDecision = new Information(
                    this.computerMemory.find(indexOfPair - 1).getRow(),
                    this.computerMemory.find(indexOfPair - 1).getColumn(),
                    this.computerMemory.find(indexOfPair - 1).getNumber());

            Information secondImageFromDecision = new Information(
                    this.computerMemory.find(indexOfPair).getRow(),
                    this.computerMemory.find(indexOfPair).getColumn(),
                    this.computerMemory.find(indexOfPair).getNumber());

            mark(firstImageFromDecision, secondImageFromDecision);

        } else {

            //choosing one then checking if similar is known if not choosing second
            Information firstImageFromDecision = chooseOneImage();
            Information secondImageFromDecision;

            int indexOfSecond = this.computerMemory.isSecondKnown(firstImageFromDecision);

            if(indexOfSecond != -1) {

                secondImageFromDecision = new Information(
                        this.computerMemory.find(indexOfSecond).getRow(),
                        this.computerMemory.find(indexOfSecond).getColumn(),
                        this.computerMemory.find(indexOfSecond).getNumber());

                mark(firstImageFromDecision, secondImageFromDecision);

            } else {

                secondImageFromDecision = chooseOneImage();

                mark(firstImageFromDecision, secondImageFromDecision);
            }
        }
    }

    public void mark(Information first, Information second) {

        if(first.getNumber() != second.getNumber()) {

            this.computerMemory.add(first);
            this.computerMemory.add(second);

        } else {

            this.rank[1]++;
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
