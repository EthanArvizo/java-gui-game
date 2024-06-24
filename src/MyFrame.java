import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private Labels labels;

    MyFrame(){

        setTitle("JFrame Title");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        //setSize(500,500);
        getContentPane().setBackground(new Color(57, 55, 54));
        //setLayout(null);


        labels = new Labels();

        ImageIcon image = new ImageIcon("icon.jpg");

        Font customFont = new Font("Serif", Font.BOLD, 20);

        JLabel label = labels.createLabel("Bro, throw the fireball.",image, Color.black, customFont);
        label.setVerticalAlignment(JLabel.CENTER);// set vertical position of icon+text of label
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(-20);
        //label.setBounds(100,0,250,250);
        add(label);
        pack();
        setVisible(true);
    }
}
