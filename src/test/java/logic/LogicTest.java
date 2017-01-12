package logic;

import gui.Board;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Member;

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

    /*
    @Test
    public void playerMove() throws Exception {

    }

    @Test
    public void computerMove() throws Exception {

    }
    */ //This part is commented and not implemented because the board is not fully implemented

    @Test
    public void mark() throws Exception {

        Information trueOne = new Information(1, 1, 1);
        Information trueTwo = new Information(2, 2, 1);
        Information falseOne = new Information(3,3,3);

        Logic logic = Logic.getInstance();

        logic.mark(trueOne, falseOne);

        assertTrue(logic.getComputerMemory().getKnowledge().length == 2);
    }

    /*
    @Test
    public void mark1() throws Exception {


        logic.mark(trueOne, trueTwo);

        assertTrue(logic.getRank()[1] == 1);

    }*/ //This part is commented because the board is not fully implemented


    /*
    @Test
    public void chooseOneImage() throws Exception {

        Logic logic = Logic.getInstance();
        Information tmp = logic.chooseOneImage();

        assertNotNull(tmp);
    }
    */ //This part is commented because the board is not fully implemented
}