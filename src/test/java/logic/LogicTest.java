package logic;

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

    /* NOT IMPLEMENTED
    @Test
    public void playerMove() throws Exception {

    }

    NOT IMPLEMENTED
    @Test
    public void computerMove() throws Exception {

    }
    */

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
    public void chooseOneImage() throws Exception {

    }
    */
}