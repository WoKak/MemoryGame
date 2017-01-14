package gui;

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

        int[] numberOfImageCopies = new int[32];
        for (int i = 0; i < 32; i++) {
            numberOfImageCopies[i] = 0;
        }

        int buttonIndex = 0;

        Random generator = new Random();

        while (buttonIndex < 64) {
            int randomNumber = generator.nextInt(32);

            if (numberOfImageCopies[randomNumber] > 2) {
            } else if (whereToPutImages[buttonIndex] == 99) {
                whereToPutImages[buttonIndex] = randomNumber;
                numberOfImageCopies[randomNumber]++;
            } else {
                buttonIndex++;
            }
        }
        // returns 64 int's array where indexes are button's numbers 
        // and content are numbers of images ( from 0 to 31 ) to put in these buttons

        return whereToPutImages;
    }

}
