package layout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CenterPanel extends JPanel{
    Graphics g;
    int a;
    public ArrayList<Double> start_x;
    public ArrayList<Double> start_y;
    public ArrayList<Double> end_x;
    public ArrayList<Double> end_y;

    public CenterPanel(){
        start_x=new ArrayList<Double>();
        start_y=new ArrayList<Double>();
        end_x=new ArrayList<Double>();
        end_y=new ArrayList<Double>();

    }

    public void clear_list(){
        start_x.clear();
        start_y.clear();
        end_x.clear();
        end_y.clear();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
    }
}
