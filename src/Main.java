import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new); // Create and run the game GUI on the Swing event dispatch thread
    }
}