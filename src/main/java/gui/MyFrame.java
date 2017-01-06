package gui;

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


        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu game = new JMenu("Gra");
        menuBar.add(game);

        JMenuItem start = new JMenuItem("Start");
        game.add(start);

        Board board = new Board();
        add(board);
    }
}
