import javax.swing.*;
import java.awt.*;
public class Labels {
   public JLabel createLabel(String text, ImageIcon icon, Color foregroundColor){
       JLabel label = new JLabel();
       label.setText(text);
       label.setIcon(icon);
       label.setForeground(foregroundColor);
       return label;
   }

}
