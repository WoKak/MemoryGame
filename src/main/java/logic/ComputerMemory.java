package logic;

/**
 * Created by Michał on 2017-01-06.
 */
public class ComputerMemory {

    private Information[] knowledge;
    private int pointer;

    public ComputerMemory() {

        this.knowledge = new Information[1];
        this.pointer = 0;
    }

    public Information[] getKnowledge() {

        return knowledge;
    }

    public void myQucikSort(Information table[], int start, int length) {

        int i,j;
        Information v, tmp;

        i = start;
        j = length;
        v = table[(start + length) / 2];

        do {

            while (table[i].compareTo(v) == -1)

                i++;

            while (v.compareTo(table[j]) == -1)

                j--;

            if (i <= j) {

                tmp = table[i];
                table[i] = table[j];
                table[j] = tmp;
                i++;
                j--;
            }
        }
        while (i <= j);

        if (start < j)

            myQucikSort(table,start,j);

        if (i < length)

            myQucikSort(table,i,length);
    }

    public void add(Information info) {

        for(int i = 0; i < knowledge.length; i++) {

            if(knowledge[i] != null)
                if(knowledge[i].equals(info))
                    return;
        }

        if (pointer == knowledge.length) {
            Information[] newKnowledge = new Information[knowledge.length + 1];
            for(int i = 0; i < knowledge.length; i++) {
                newKnowledge[i] = knowledge[i];
            }
            knowledge = newKnowledge;
        }
        knowledge[pointer] = info;
        pointer++;
    }

    public Information find(Information info) {

        for (int i = 0; i < knowledge.length; i++) {

            if(knowledge[i].hasSameNumber(info)) {

                return knowledge[i];
            }
        }

        return null;
    }

    public Information find(int index) {

        for (int i = 0; i < knowledge.length; i++) {

            if(i == index) {

                return knowledge[i];
            }
        }

        return null;
    }

    public int isTwoKnown() {

        if(this.pointer > 0) {

            this.myQucikSort(this.getKnowledge(), 0, getKnowledge().length - 1);

            for (int i = 1; i < this.getKnowledge().length; i++) {

                if (this.getKnowledge()[i].hasSameNumber(this.getKnowledge()[i - 1])) {

                    if (this.getKnowledge()[i].getNumber() != 99 )
                        return i;
                }
            }

            return -1;
        }

        return -1;
    }

    public int isSecondKnown(Information info) {

        if(this.pointer > 0) {

            this.myQucikSort(this.getKnowledge(), 0, getKnowledge().length - 1);

            for (int i = 0; i < this.getKnowledge().length; i++) {

                if (this.getKnowledge()[i].hasSameNumber(info)) {

                    return i;
                }
            }
        }

        return -1;
    }

    public void forget() {

        if (this.pointer > 0) {

            this.myQucikSort(this.getKnowledge(), 0, getKnowledge().length - 1);

            for (int i = 1; i < this.getKnowledge().length; i++) {

                if (this.getKnowledge()[i].hasSameNumber(this.getKnowledge()[i - 1])) {

                    this.getKnowledge()[i].setNumber(99);
                    this.getKnowledge()[i-1].setNumber(99);
                }
            }
        }
    }
}