package main.game.gui;

import main.game.model.Item;
import main.game.model.Room;
import main.game.Player;

import javax.swing.*;
import java.awt.*;

public class GameGUI {
    // GUI components
    private JFrame frame;
    private JTextArea displayArea;
    private JButton northButton, southButton, eastButton, westButton;
    private Player player;
    private JTextArea inventoryArea;
    private InventoryManager inventoryManager;

    public GameGUI() {
        createRooms();
        initializeGUI();
    }
    // Creates the rooms and their connections (exits).
    private void createRooms() {
        Room room1 = new Room("You are in a small room. There is a door to the north.");
        Room room2 = new Room("You are in a large hall. There is a door to the south and east.");
        Room room3 = new Room("You are in a dark cave. There is a door to the west.");

        room1.setExit("north", room2);
        room2.setExit("south", room1);
        room2.setExit("east", room3);
        room3.setExit("west", room2);

        player= new Player(room1);
    }

    private void initializeGUI() {
        frame = new JFrame("Adventure Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create and configure the display area for room descriptions
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        displayArea.setText(player.getCurrentRoom().getDescription());
        // Create a panel for direction buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,2));
        // Create direction buttons
        northButton = new JButton("Go North");
        southButton = new JButton("Go South");
        eastButton = new JButton("Go East");
        westButton = new JButton("Go West");
        // Add buttons to the panel
        buttonPanel.add(northButton);
        buttonPanel.add(southButton);
        buttonPanel.add(eastButton);
        buttonPanel.add(westButton);
        // Add action listeners to the buttons
        northButton.addActionListener(new DirectionButtonListener("north", player, displayArea));
        southButton.addActionListener(new DirectionButtonListener("south", player, displayArea));
        eastButton.addActionListener(new DirectionButtonListener("east", player, displayArea));
        westButton.addActionListener(new DirectionButtonListener("west", player, displayArea));

        // Create a panel for inventory display
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BorderLayout());

        // Create inventory display area
        inventoryArea = new JTextArea("Inventory:\n");
        inventoryArea.setEditable(false);
        inventoryPanel.add(new JScrollPane(inventoryArea), BorderLayout.CENTER);

        // Add components to the frame
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(displayArea));
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        frame.setVisible(true); // Make the frame visible
    }
    public void addItemToInventory(Item item) {
        inventoryManager.addItemToInventory(item);
    }
}
