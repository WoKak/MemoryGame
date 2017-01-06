package logic;

/**
 * Created by Michał on 2017-01-06.
 */
public class ComputerMemory {

    private Information[] knowledge;

    public ComputerMemory() {

        this.knowledge = new Information[64];
    }

    public Information[] getKnowledge() {
        return knowledge;
    }
}
