package main.game.gui;

import main.game.model.Room;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayManager {

    private BufferedImage smallRoomImage;
    private BufferedImage largeHallImage;
    private BufferedImage darkCaveImage;

    public DisplayManager() {
        try {
            smallRoomImage = ImageIO.read(new File("src/main/game/gui/displays/backgrounds/med_room.jpg"));
            largeHallImage = ImageIO.read(new File("src/main/game/gui/displays/backgrounds/med_hall.jpg"));
            darkCaveImage = ImageIO.read(new File("src/main/game/gui/displays/backgrounds/med_cave.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JPanel getDisplayForRoom(Room room) {
        JPanel roomPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage image = null;

                // Choose image based on room description or ID
                if (room.getDescription().contains("small room")) {
                    image = smallRoomImage;
                } else if (room.getDescription().contains("large hall")) {
                    image = largeHallImage;
                } else if (room.getDescription().contains("dark cave")) {
                    image = darkCaveImage;
                }

                // Draw the image if it exists
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        roomPanel.setPreferredSize(new Dimension(600, 400));
        return roomPanel;
    }
}
