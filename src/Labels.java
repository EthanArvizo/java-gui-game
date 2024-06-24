import javax.swing.*;
import java.awt.*;
public class Labels {
   public JLabel createLabel(String text, ImageIcon icon, Color foregroundColor){
       JLabel label = new JLabel();
       label.setText(text);
       label.setIcon(icon);
       label.setHorizontalTextPosition(JLabel.CENTER);
       label.setVerticalTextPosition(JLabel.TOP);
       label.setForeground(foregroundColor);
       return label;
   }
    public JLabel createLabel(String text, ImageIcon icon, Color foregroundColor, Font font) {
        JLabel label = createLabel(text, icon, foregroundColor);
        label.setFont(font);
        return label;
    }

}
