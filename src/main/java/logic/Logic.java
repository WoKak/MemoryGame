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

        if ( (firstImage.getRow() == secondImage.getRow()) && (firstImage.getColumn() == secondImage.getColumn()) ) {

            return;

        } else {


            if (firstImage.getValue() == secondImage.getValue()) {

                firstImage.getButton().setEnabled(false);
                secondImage.getButton().setEnabled(false);

                rank[0]++;

            }
        }
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
            Information firstImageFromDecision = chooseOneImage(null);
            Information secondImageFromDecision;

            int indexOfSecond = computerMemory.isSecondKnown(firstImageFromDecision);

            if(indexOfSecond != -1) {

                secondImageFromDecision = new Information(
                        computerMemory.find(indexOfSecond).getRow(),
                        computerMemory.find(indexOfSecond).getColumn(),
                        computerMemory.find(indexOfSecond).getNumber());

                mark(firstImageFromDecision, secondImageFromDecision);

            } else {

                secondImageFromDecision = chooseOneImage(firstImageFromDecision);

                while (secondImageFromDecision.equals(firstImageFromDecision)) {

                    secondImageFromDecision = chooseOneImage(firstImageFromDecision);
                }

                mark(firstImageFromDecision, secondImageFromDecision);
            }
        }

        this.computerMemory.forget();
    }

    /**
     * Method used as a part of AI for checking and deciding whether params are similar or not.
     * In first case rank is increased and proper blocks are enabled.
     * If not AI adds both information to the memory.
     * @param first Information
     * @param second Information
     */
    public void mark(Information first, Information second){

        if (first.equals(second))
            return;

        if(first.getNumber() != second.getNumber()) {

            computerMemory.add(first);
            //computerMemory.add(second); Commented in order to increase player's chances

        } else {

            rank[1]++;
            Board.getBlockWithInformation(first).getButton().setEnabled(false);
            Board.getBlockWithInformation(second).getButton().setEnabled(false);
            Board.getBlockWithInformation(first).getButton().setIcon(Board.getBlockWithInformation(first).getImage());
            Board.getBlockWithInformation(second).getButton().setIcon(Board.getBlockWithInformation(second).getImage());
        }

    }

    /**
     * Method used for choosing one completely random block from board
     * @return Information for the AI
     */
    public Information chooseOneImage(Information info) {

        Random random = new Random();

        int tmpRow = random.nextInt(8);
        int tmpColumn = random.nextInt(8);

        int isMethodNeedsChange = 0;

        Block tmp = Board.getBlockWithCoordinates(tmpRow, tmpColumn);

        while (!tmp.getButton().isEnabled()) {

            if (isMethodNeedsChange < 10) {

                tmpRow = random.nextInt(8);
                tmpColumn = random.nextInt(8);

                tmp = Board.getBlockWithCoordinates(tmpRow, tmpColumn);

                isMethodNeedsChange++;

            } else {

                for (int i = 0; i < 8; i++) {

                    for (int j = 0; j < 8; j++) {

                        tmpRow = i;
                        tmpColumn = j;

                        tmp = Board.getBlockWithCoordinates(tmpRow, tmpColumn);

                        if (tmp.getButton().isEnabled()) {

                            Information toReturn;

                            if (info != null) {

                                int value = Board.getBlockWithCoordinates(tmpRow, tmpColumn).getValue();
                                toReturn = new Information(tmpRow, tmpColumn, value);

                                if (!toReturn.equals(info))
                                    return toReturn;

                            } else {

                                int value = Board.getBlockWithCoordinates(tmpRow, tmpColumn).getValue();
                                toReturn = new Information(tmpRow, tmpColumn, value);
                                return toReturn;
                            }
                        }
                    }
                }
            }
        }

        int value = Board.getBlockWithCoordinates(tmpRow, tmpColumn).getValue();

        return new Information(tmpRow, tmpColumn, value);
    }

    public void makeMove(Block first, Block second) {

        Runnable pm = new Runnable() {
            @Override
            public void run() {
                playerMove(first, second);
            }
        };

        Runnable cm = new Runnable() {
            @Override
            public void run() {
                computerMove();
            }
        };

        Runnable display = new Runnable() {
            @Override
            public void run() {
                first.getButton().setIcon(first.getImage());
                second.getButton().setIcon(second.getImage());
            }
        };

        Runnable stopDisplay = new Runnable() {
            @Override
            public void run() {
                first.getButton().setIcon(null);
                second.getButton().setIcon(null);
            }
        };

        final Thread pmt = new Thread(pm);
        final Thread cmt = new Thread(cm);
        final Thread dt = new Thread(display);
        final Thread sdt = new Thread(stopDisplay);

        try {

            pmt.setDaemon(true);
            pmt.start();

            pmt.interrupt();
            pmt.join();

            if(!pmt.isAlive()) {

                dt.setDaemon(true);
                dt.start();

                dt.interrupt();
                dt.join();

                if(!dt.isAlive()) {

                    sdt.setDaemon(true);
                    sdt.start();

                    sdt.interrupt();
                    sdt.join();

                    if (!sdt.isAlive() && (rank[0] + rank[1] != 32)) {

                        cmt.setDaemon(true);
                        cmt.start();

                        cmt.interrupt();
                        cmt.join();
                    }
                }

            }

            if (!cmt.isAlive())
                return;

        } catch (Exception ex) {

        }
    }
}
