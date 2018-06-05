package layout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CenterPanel extends JPanel{
    Graphics g;
    int a;
    boolean isFinish = false;
    public ArrayList<Double> start_x;
    public ArrayList<Double> start_y;
    public ArrayList<Double> end_x;
    public ArrayList<Double> end_y;

    public JLabel[] getExtensionPoint() {
        return extensionPoint;
    }

    public void setExtensionPoint(JLabel[] extensionPoint) {
        this.extensionPoint = extensionPoint;
    }

    JLabel[] extensionPoint;

    public CenterPanel(){
        start_x=new ArrayList<Double>();
        start_y=new ArrayList<Double>();
        end_x=new ArrayList<Double>();
        end_y=new ArrayList<Double>();
        isFinish = false;
    }

    public void clear_list(){
        start_x.clear();
        start_y.clear();
        end_x.clear();
        end_y.clear();
        isFinish = false;
    }

    public void setFinish(){
        isFinish = true;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isFinish == true){
            for(int i=0; i<start_x.size(); i++){
                double sx = start_x.get(i);
                double sy = start_y.get(i);
                double ex = end_x.get(i);
                double ey = end_y.get(i);
                g.drawLine((int)sx, (int)sy, (int)ex, (int)ey);
            }
            isFinish = false;
        }
    }
}
