package Main;

import Town.Town;
import Utility.Configuration;
import Utility.ReadMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyPanel extends JPanel {
    public Town town = new Town();
    public int[] path;
    public int start = -1;
    public int end = -1;


    public MyPanel() {
        ReadMap.read(town, "src\\map2.xml");

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1)
                    start = town.getNearestArc(e.getX(), e.getY());
                if (e.getButton() == MouseEvent.BUTTON3)
                    end = town.getNearestArc(e.getX(), e.getY());
                if (start != -1 && end != -1 && start != end) {
                    if (Configuration.DIJKSTRA) {
                        path = town.dijkstra(start, end);
                        repaint();
                    } else {
                        path=town.bellmanFord(start, end);
                        repaint();
                    }
                }
            }
        });
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        town.drawLuxembourg(g, start, end, path);
    }

}
