package logic;

import gui.Block;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Created by Michał on 2017-01-11.
 */
public class LogicTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void playerMove() throws Exception {

        Logic logic = Logic.getInstance();

        Block firstImage = new Block(1, new JButton(), null, 1, 1);
        Block secondImage = new Block(1, new JButton(), null, 2, 2);

        logic.playerMove(firstImage, secondImage);

        assertTrue(logic.getRank()[0] == 1);
        assertTrue(!firstImage.isEnabled());
        assertTrue(!secondImage.isEnabled());

    }    //Fail because of the fact that Board is not fully implemented

    @Test
    public void playerMove1() throws Exception {

        Logic.destroyLogic();

        Logic logic = Logic.getInstance();

        Block firstImage = new Block(1, new JButton(), null, 1, 1);
        Block secondImage = new Block(2, new JButton(), null, 2, 2);

        logic.playerMove(firstImage, secondImage);

        assertTrue(logic.getRank()[0] == 0);
        assertTrue(firstImage.isEnabled());
        assertTrue(secondImage.isEnabled());
        assertTrue(logic.getComputerMemory().getKnowledge()[0] != null);
        assertTrue(logic.getComputerMemory().getKnowledge()[1] != null);

    } //Fail because of the same reason as before


    @Test
    public void computerMove() throws Exception {

        Logic.destroyLogic();

        Logic logic = Logic.getInstance();

        logic.getComputerMemory().add(new Information(1,1,1));
        logic.getComputerMemory().add(new Information(2,2,1));

        logic.computerMove();

        assertTrue(logic.getRank()[1] == 1);

    }   //Fail because of the same reason as before

    @Test
    public void mark() throws Exception {

        Logic.destroyLogic();

        Information trueOne = new Information(1, 1, 1);
        Information falseOne = new Information(3,3,3);

        Logic logic = Logic.getInstance();

        logic.mark(trueOne, falseOne);

        assertTrue(logic.getComputerMemory().getKnowledge().length == 2);
    }


    @Test
    public void mark1() throws Exception {

        Logic.destroyLogic();

        Logic logic = Logic.getInstance();

        Information trueOne = new Information(1, 1, 1);
        Information trueTwo = new Information(3,3,1);

        logic.mark(trueOne, trueTwo);

        assertTrue(logic.getRank()[1] == 1);

    }   //Fail because of the same reason as before



    @Test
    public void chooseOneImage() throws Exception {

        Logic logic = Logic.getInstance();
        Information tmp = logic.chooseOneImage(null);

        assertNotNull(tmp);

    }   //Fail because of the same reason as before
}