package gui;

import javax.swing.*;

/**
 * Created by Michał on 2017-01-06.
 */
public class Block extends JButton {

    private int value;
    private JButton button;
    private ImageIcon image;

    public Block(int value, JButton button, ImageIcon image) {
        this.value = value;
        this.button = button;
        this.image = image;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
