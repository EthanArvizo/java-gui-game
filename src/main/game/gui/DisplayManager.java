package main.game.gui;

import main.game.gui.displays.FirstRoom;
import main.game.model.Room;

import javax.swing.*;
import java.awt.*;

public class DisplayManager {

    public JPanel getDisplayForRoom(Room room){
        if (room.getDescription().contains("small room")){
            return new FirstRoom();
        }
        JPanel defaultPanel = new JPanel();
        defaultPanel.setBackground(Color.WHITE);
        return defaultPanel;
    }

}
