package logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Michał on 2017-01-11.
 */
public class ComputerMemoryTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void myQucikSort() throws Exception {

        ComputerMemory memory = new ComputerMemory();
        memory.add(new Information(1,1,1));
        memory.add(new Information(2,2,6));
        memory.add(new Information(3,3,3));
        memory.add(new Information(4,4,5));
        memory.add(new Information(5,5,10));
        memory.add(new Information(6,6,2));

        memory.myQucikSort(memory.getKnowledge(), 0, memory.getKnowledge().length-1);

        for(int i = 1; i < memory.getKnowledge().length; i++) {

            assertTrue(memory.getKnowledge()[i].getNumber() > memory.getKnowledge()[i-1].getNumber());
        }
    }

    @Test
    public void add() throws Exception {

        ComputerMemory memory = new ComputerMemory();
        memory.add(new Information(5,5,10));

        assertEquals(memory.getKnowledge()[0], new Information(5, 5, 10));
    }

    @Test
    public void find() throws Exception {

        ComputerMemory memory = new ComputerMemory();
        memory.add(new Information(5,5,10));

        Information tmp = memory.find(new Information(5,5, 10));

        assertEquals(tmp, new Information(5,5,10));
    }


    @Test
    public void find1() throws Exception {

        ComputerMemory memory = new ComputerMemory();
        memory.add(new Information(5,5,10));

        Information tmp = memory.find(0);

        assertEquals(tmp, new Information(5,5,10));
    }


    @Test
    public void isTwoKnown() throws Exception {

        ComputerMemory memory = new ComputerMemory();
        memory.add(new Information(5,5,10));
        memory.add(new Information(5,6,10));

        int tmp = memory.isTwoKnown();

        assertTrue(1 == tmp);
    }


    @Test
    public void isSecondKnown() throws Exception {

        ComputerMemory memory = new ComputerMemory();
        memory.add(new Information(5,5,10));

        int tmp =  memory.isSecondKnown(new Information(5, 6, 10));

        assertTrue(0 == tmp);

    }

}