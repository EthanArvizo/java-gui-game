import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private Labels labels;

    MyFrame(){

        this.setTitle("JFrame Title");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,500);
        this.getContentPane().setBackground(new Color(57, 55, 54));
        this.setVisible(true);

        labels = new Labels();

        ImageIcon image = new ImageIcon("icon.jpg");
        JLabel label = labels.createLabel("Bro, throw the fireball.",image, Color.white);
        add(label);
    }
}
