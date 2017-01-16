
package gui;

/**
 *
 * @author pawel
 */
public class MyList {

    private int[] DynamicArray;
    private int size;

    public MyList(int size) {

        this.DynamicArray = new int[size];
        for (int i = 0; i < size; i++) {
            this.DynamicArray[i] = 99;
        }
        this.size = size;
    }

    public void add(int numberToAdd) {

        for (int i = 0; i < this.size; i++) {
            if (this.DynamicArray[i] == 99) {
                this.DynamicArray[i] = numberToAdd;
                return;
            }
        }
        this.size++;
        int[] tmp = new int[this.size];
        System.arraycopy(this.DynamicArray, 0, tmp, 0, this.size);
        tmp[this.size] = numberToAdd;
        this.DynamicArray = tmp;
    }

    public int get(int index) {
        return this.DynamicArray[index];
    }

    public void remove(int index) {
        System.out.println("" + index);
        int[] tmp = new int[this.size - 1];

        for (int i = 0; i < index; i++) {
            tmp[i] = this.DynamicArray[i];
        }

        for (int k = index + 1; k < this.size; k++) {
            tmp[k - 1] = this.DynamicArray[k];
        }
        this.size--;
        
        this.DynamicArray = tmp;

    }

    public int size() {
        return this.size;
    }

}
