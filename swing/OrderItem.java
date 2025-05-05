package swing;

import java.awt.Color;
import javax.swing.JPanel;

public class OrderItem extends JPanel {

    public OrderItem() {
        setBackground(new Color(254,194,8));
        setLayout(new WrapLayout(WrapLayout.LEFT, 0, 5));
    }
}