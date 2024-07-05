import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectionButtonListener implements ActionListener {
    private String direction;
    private Player player;
    private JTextArea displayArea;

    /**
     * Constructor to initialize the direction, player, and display area.
     * @param direction The direction to move.
     * @param player The player object.
     * @param displayArea The text area to update with room descriptions.
     */
    public DirectionButtonListener(String direction, Player player, JTextArea displayArea) {
        this.direction = direction;
        this.player = player;
        this.displayArea = displayArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the room in the specified direction
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom != null) {
            // Move the player to the next room and update display
            player.setCurrentRoom(nextRoom);
            displayArea.setText(player.getCurrentRoom().getDescription());
        } else {
            // Show a message if there's no exit in that direction
            displayArea.setText("You can't go that way.\n" + player.getCurrentRoom().getDescription());
        }
    }
}