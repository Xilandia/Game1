package com.gamedev.executable;

import com.gamedev.resources.Map;
//import java.awt.Color;
//import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

public class JFrameMain extends JPanel{
    static JFrameMain content = new JFrameMain();
    private static Map map1;
    private static boolean mapGenerated = false;
    private BufferedImage[] tiles = new BufferedImage[7];
    /*
    Creates the JFrame, initializes the map, and paints.
     */
    public static void main(String[] args) throws InterruptedException {
        javax.swing.JFrame frame = new javax.swing.JFrame("JFrame Test");
        frame.setContentPane(content);
        frame.setSize(848,960);
        frame.setLocation(20,20);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible(true);
        map1 = new Map(53,9,3);
        mapGenerated = true;
        map1.printMap();
        content.paintMap();
    }
    /*
    This method loads the images into the array of tiles, and calls a repaint.
     */
    private void paintMap() {
        tiles[0] = loadImage("floor");
        tiles[1] = loadImage("wall");
        tiles[2] = loadImage("path");
        tiles[3] = loadImage("closedDoor");
        tiles[4] = loadImage("openDoor");
        tiles[5] = loadImage("void");
        tiles[6] = loadImage("error");
        repaint();
    }
    /*
    Paints the map, by translating the array of tiles into positions of the
    array of tile images.
    The if statement checks if the map has been created, and makes the method
    do nothing if it hasn't to prevent unnecessary errors.
    Input: none, just has to be run from within the panel using repaint.
     */
    public void paintComponent(Graphics surface) {
        int tileSelection = 99;
        String tileValue;
        if (!mapGenerated){
        } else {
            for (int a = 0; a < map1.size; a++) {
                for (int b = 0; b < map1.size; b++) {
                    tileValue = map1.getTileValue(b, a);
                    if (tileValue.contentEquals("V")) {
                        tileSelection = 5;
                    } else if (tileValue.contentEquals("F")) {
                        tileSelection = 0;
                    } else if (tileValue.contentEquals("W")) {
                        tileSelection = 1;
                    } else if (tileValue.contentEquals("P")) {
                        tileSelection = 2;
                    } else if (tileValue.contentEquals("N") || tileValue.contentEquals("T") || tileValue.contentEquals("S") || tileValue.contentEquals("E")) {
                        tileSelection = 3;
                    } else {
                        tileSelection = 6;
                    }
                    surface.drawImage(tiles[tileSelection], 16 * a, 16 * b, 16, 16, null);
                }
            }
        }
    }
    /*
    Loads images from the images folder in the out folder.
    Input: name of file.
     */
    private BufferedImage loadImage(String name) {
        String imgFileName = "/images/" + name +".png";
        URL url = JFrameMain.class.getResource(imgFileName);
        BufferedImage img = null;
        try {
            img =  ImageIO.read(url);
        } catch (Exception e) {
        }
        return img;
    }
}
