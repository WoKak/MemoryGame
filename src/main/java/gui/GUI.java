package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Michał on 2017-01-06.
 */
public class GUI {
    public static void main(String args[]) {
        System.out.println("ZACZYNAMY");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyFrame frame = new MyFrame();
                frame.setTitle("memory");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
                frame.setVisible(true);
            }
        });
    }
}
