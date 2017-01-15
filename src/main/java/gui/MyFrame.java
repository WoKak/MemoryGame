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


        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu game = new JMenu("Gra");
        menuBar.add(game);

        JMenuItem start = new JMenuItem("Start");
        game.add(start);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for(int i = 0; i < Logic.getInstance().getComputerMemory().getKnowledge().length; i++) {

                    System.out.println(Logic.getInstance().getComputerMemory().getKnowledge()[i]);
                }
            }
        });

        Board board = new Board();
        add(board);
    }
}
