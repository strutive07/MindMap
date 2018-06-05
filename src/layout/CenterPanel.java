package layout;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel{
    Graphics g;
    int a;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
    }
}
