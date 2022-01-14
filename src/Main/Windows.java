package Main;

import Utility.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Windows {
    private static void initAlegere() {
        JFrame f = new JFrame("Luxembourg");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 100);
        f.setVisible(true);

        Button dijkstra = new Button("Dijkstra");
        Button bellman_ford = new Button("Bellman Ford");


        dijkstra.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configuration.DIJKSTRA = true;
                initUI();
            }
        }));
        bellman_ford.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configuration.DIJKSTRA = false;
                initUI();
            }
        }));
        f.add(dijkstra);
        f.add(bellman_ford);
        f.setLayout(new FlowLayout());

        f.setVisible(true);
    }

    private static void initUI() {
        JFrame f = new JFrame("Luxembourg");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.setSize(1500, 1000);
        f.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() //new Thread()
        {
            public void run() {
                initAlegere();
            }
        });
    }


}
