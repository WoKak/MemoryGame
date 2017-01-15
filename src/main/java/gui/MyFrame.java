package gui;

import logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Michał on 2017-01-06.
 */
public class MyFrame extends JFrame {

    public MyFrame() {

        setSize(new Dimension(900, 920));
        setLocationByPlatform(true);
        Board board = new Board();
        add(board);
    }
}
