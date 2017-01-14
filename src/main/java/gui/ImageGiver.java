package gui;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pawel
 */
public class ImageGiver {

    public static int[] giveImageNumber() {

        int[] whereToPutImages = new int[64];
        for (int i = 0; i < 64; i++) {
            whereToPutImages[i] = 99;
        }

        int buttonIndex = 0;

        ArrayList<Integer> numbersAvaibleToGet = new ArrayList<>(64);
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < 32; i++) {
                numbersAvaibleToGet.add(i);
            }
        }

        Random generator = new Random();

        while (buttonIndex < 64) {

            int randomNumber = generator.nextInt(numbersAvaibleToGet.size());

            if (whereToPutImages[buttonIndex] == 99) {
                whereToPutImages[buttonIndex] = numbersAvaibleToGet.get(randomNumber);
                numbersAvaibleToGet.remove(randomNumber);
                buttonIndex++;
            } else {
                // do nothing, go to new button
            }
        }
         

        // mini test
        int[] tmp = new int[64];
        for (int i = 0; i < 64; i++) {
            tmp[i] = 0;
        }
        for (int i = 0; i < 64; i++) {
            tmp[whereToPutImages[i]]++;
        }

        for (int i = 0; i < 64; i++) {
            if (tmp[i] > 2) {
                System.out.println("BŁĄD" + i);
            }
        }
        // end of mini test

        // returns 64 int's array where indexes are button's numbers 
        // and content are numbers of images ( from 0 to 31 ) to put in these buttons 
        return whereToPutImages;
    }

}
