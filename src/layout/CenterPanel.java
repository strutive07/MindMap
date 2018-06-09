package layout;

import IO.treeIO;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CenterPanel extends JPanel{
    Graphics g;
    int a;
    boolean isFinish = false;
    JLabel selected_Label;
    treeIO selected_Node;
    public ArrayList<ArrayList<Double>> start_x;
    public ArrayList<ArrayList<Double>> start_y;
    public ArrayList<ArrayList<Double>> end_x;
    public ArrayList<ArrayList<Double>> end_y;
    int index;

    public JLabel[] getExtensionPoint() {
        return extensionPoint;
    }

    public void setExtensionPoint(JLabel[] extensionPoint) {
        this.extensionPoint = extensionPoint;
    }

    JLabel[] extensionPoint;

    public JLabel getSelected_Label() {
        return selected_Label;
    }

    public void setSelected_Label(JLabel selected_Label) {
        this.selected_Label = selected_Label;
    }

    public treeIO getSelected_Node() {
        return selected_Node;
    }

    public void setSelected_Node(treeIO selected_Node) {
        this.selected_Node = selected_Node;
    }

    public CenterPanel(){
        start_x=new ArrayList<ArrayList<Double>>();
        start_y=new ArrayList<ArrayList<Double>>();
        end_x=new ArrayList<ArrayList<Double>>();
        end_y=new ArrayList<ArrayList<Double>>();
        isFinish = false;
        extensionPoint = null;
        selected_Label = null;
        selected_Node = null;

    }

    public void clear_list(){
        start_x.clear();
        start_y.clear();
        end_x.clear();
        end_y.clear();
        isFinish = false;
    }

    public void setFinish(int i){
        isFinish = true;
        index = i;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isFinish == true){
            for (int idx=0;idx<start_x.size();idx++) {
                for (int i = 0; i < start_x.get(idx).size(); i++) {
                    double sx = start_x.get(idx).get(i);
                    double sy = start_y.get(idx).get(i);
                    double ex = end_x.get(idx).get(i);
                    double ey = end_y.get(idx).get(i);
                    g.drawLine((int) sx, (int) sy, (int) ex, (int) ey);
                }
            }
            index = 0;
            isFinish = false;
        }
    }
}
