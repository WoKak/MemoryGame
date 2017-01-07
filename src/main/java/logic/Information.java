package logic;

/**
 * Created by Michał on 2017-01-06.
 */
public class Information implements Comparable<Information> {

    private int row;
    private int column;
    private int number;

    public Information(int row, int column, int number) {
        this.row = row;
        this.column = column;
        this.number = number;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Information info) {

        if(this.getNumber() > info.getNumber()) {

            return 1;

        } else if (this.getNumber() < info.getNumber()) {

            return -1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Information that = (Information) o;

        return number == that.number;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        result = 31 * result + number;
        return result;
    }
}
