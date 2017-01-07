package logic;

/**
 * Created by Michał on 2017-01-07.
 */
public class Logic {

    private ComputerMemory computerMemory;
    private int[] rank;


    public Logic() {
        this.computerMemory = new ComputerMemory();
        this.rank = new int[] {0, 0};
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
}
