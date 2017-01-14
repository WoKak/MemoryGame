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
    private static Logic INSTANCE = null;

    private Logic() {
        computerMemory = new ComputerMemory();
        rank = new int[] {0, 0};
    }

    /**
     * Method introduced because Logic is singleton.
     * @return Logic instance
     */
    public static synchronized Logic getInstance() {

        if (INSTANCE == null) {

            INSTANCE = new Logic();

        }

        return INSTANCE;
    }

    /**
     * Destructor of logic.
     */
    public static synchronized void destroyLogic() {

        if(INSTANCE != null) {

            INSTANCE.setComputerMemory(null);
            INSTANCE.setRank(null);

            INSTANCE = null;
        }
    }

    private void setComputerMemory(ComputerMemory computerMemory) {
        this.computerMemory = computerMemory;
    }

    private void setRank(int[] rank) {
        this.rank = rank;
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

    /**
     * Method which is used by AI to make move - all doubts are explained in the body.
     */
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

    /**
     * Method used as a part of AI for checking and deciding whether params are similar or not.
     * In first case rank is increased and proper blocks are enabled.
     * If not AI adds both information to the memory.
     * @param first Information
     * @param second Information
     */
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

    /**
     * Method used for choosing one completely random block from board
     * @return Information for the AI
     */
    public Information chooseOneImage() {

        Random random = new Random();

        int tmpRow = random.nextInt(8);
        int tmpColumn = random.nextInt(8);

        int value = Board.getBlockWithCoordinates(tmpRow, tmpColumn).getValue();

        return new Information(tmpRow, tmpColumn, value);
    }
}
