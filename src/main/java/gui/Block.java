package gui;

import javax.swing.*;

/**
 * Created by Michał on 2017-01-06.
 */
public class Block extends JButton {

    private int value;
    private JButton button;
    private ImageIcon image;
    private int row;
    private int column;


    public Block(int value, JButton button, ImageIcon image, int row, int column) {
        this.value = value;
        this.button = button;
        this.image = image;
        this.row = row;
        this.column = column;
    }

    public int getValue() {
        return value;
    }

    public JButton getButton() {
        return button;
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
