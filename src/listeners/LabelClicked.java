package listeners;

import IO.treeIO;
import algorithm.SetPosition;
import layout.AttributePane;
import layout.CenterPanel;
import layout.MainFrame;
import layout.MainLayout;
import util.FindNodeByTarget;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;

public class LabelClicked extends MouseAdapter{

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        JLabel jLabel = (JLabel) e.getSource();
        int nodeNumber = Integer.parseInt(jLabel.getName());
        System.out.println("nodeNumber : " + nodeNumber);
        ArrayList<treeIO> root_list = layout.MainLayout.getTree();
        treeIO target = null;
        for(int i=0; i<root_list.size(); i++){
            treeIO root = root_list.get(i);
            target = FindNodeByTarget.dfs(root, nodeNumber);
            if(target != null)
                break;
        }
        ArrayList<JLabel> labels = layout.MainLayout.getLabels();
        int label_number = -1;
        for(int i=0; i<labels.size(); i++){
            if(Integer.parseInt(labels.get(i).getName()) == nodeNumber){
                label_number = i;
                break;
            }
        }
        if (target == null || label_number == -1){
            //ERROR
            System.out.println("ERROR : CANT FIND TARGET NODE");
        }else{
            AttributePane attributePane = layout.MainLayout.getRightPanel();
            System.out.println(target.getStringName());
            System.out.println(attributePane);
            attributePane.getText_x().setText(Integer.toString((int)target.getX()));
            attributePane.getText_y().setText(Integer.toString((int)target.getY()));
            attributePane.getText_w().setText(Integer.toString((int)target.getW()));
            attributePane.getText_h().setText(Integer.toString((int)target.getH()));
            attributePane.getText_color().setText(Integer.toHexString(target.getLabelColor()));
            attributePane.getText_TEXT().setText(target.getStringName());
            attributePane.setSelectedLabelNumber(label_number);
            attributePane.setSelectedNodeNumber(nodeNumber);
        }
        JLabel[] extensionPoint = new JLabel[8];
        CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
        centerPanel.setSelected_Node(target);
        if(centerPanel.getExtensionPoint() != null){
            JLabel[] pp = centerPanel.getExtensionPoint();
            for(int i=0; i<pp.length; i++){
                if(i == 0 || i == 1 || i == 3){
                    continue;
                }
                centerPanel.remove(pp[i]);
            }
        }

        centerPanel.setExtensionPoint(extensionPoint);
        for(int i=0; i<8; i++){
            if(i == 0 || i == 1 || i == 3){
                continue;
            }
            extensionPoint[i] = new JLabel();
            extensionPoint[i].setSize(10,10);
            extensionPoint[i].setBackground(Color.black);
            extensionPoint[i].setOpaque(true);
            extensionPoint[i].setVisible(true);
            extensionPoint[i].addMouseListener(new dragLabel());
        }

//        extensionPoint[0].setLocation(jLabel.getLocation().x - 5, jLabel.getLocation().y - 5);
//        extensionPoint[1].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth()/2 - 2, jLabel.getLocation().y - 5);
        extensionPoint[2].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth(), jLabel.getLocation().y - 5);
//        extensionPoint[3].setLocation(jLabel.getLocation().x - 5, jLabel.getLocation().y  + (int)jLabel.getSize().getHeight()/2);
        extensionPoint[4].setLocation(jLabel.getLocation().x  + (int)jLabel.getSize().getWidth(), jLabel.getLocation().y  + (int)jLabel.getSize().getHeight()/2);
        extensionPoint[5].setLocation(jLabel.getLocation().x - 5 , jLabel.getLocation().y  + (int)jLabel.getSize().getHeight());
        extensionPoint[6].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth()/2  - 2, jLabel.getLocation().y  + (int)jLabel.getSize().getHeight());
        extensionPoint[7].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth(), jLabel.getLocation().y  + (int)jLabel.getSize().getHeight());



        for(int i=0; i<8; i++){
            if(i == 0 || i == 1 || i == 3){
                continue;
            }
            centerPanel.add(extensionPoint[i]);
        }

        centerPanel.revalidate();
        centerPanel.repaint();
        centerPanel.setFinish();
        centerPanel.setSelected_Label(jLabel);
    }
}

class dragLabel extends MouseAdapter{
    int x, y;
    int cx, cy;
    JLabel label = null;
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        System.out.println("ho11111");

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        label = (JLabel)e.getSource();
        x = label.getLocation().x;
        y = label.getLocation().y;
        System.out.println(x + " : " + y);
        cx = MouseInfo.getPointerInfo().getLocation().x;
        cy = MouseInfo.getPointerInfo().getLocation().y;
        System.out.println(cx + " : " + cy);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if(label != null){
            System.out.println("ho");
            int ccx = x + (MouseInfo.getPointerInfo().getLocation().x - cx);
            int ccy = y + (MouseInfo.getPointerInfo().getLocation().y - cy);
            System.out.println(ccx + " : " + ccy);
            CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
            JLabel jLabel = centerPanel.getSelected_Label();

            int c_size_w = (int)jLabel.getSize().getWidth() + (MouseInfo.getPointerInfo().getLocation().x - cx);
            int c_size_h = (int)jLabel.getSize().getHeight() + (MouseInfo.getPointerInfo().getLocation().y - cy);
            label.setLocation(ccx, ccy);
            jLabel.setSize(c_size_w, c_size_h);

            label = null;
            JLabel[] extensionPoint = centerPanel.getExtensionPoint();
//            extensionPoint[0].setLocation(jLabel.getLocation().x - 5, jLabel.getLocation().y - 5);
//            extensionPoint[1].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth()/2 - 2, jLabel.getLocation().y - 5);
            extensionPoint[2].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth(), jLabel.getLocation().y - 5);
//            extensionPoint[3].setLocation(jLabel.getLocation().x - 5, jLabel.getLocation().y  + (int)jLabel.getSize().getHeight()/2);
            extensionPoint[4].setLocation(jLabel.getLocation().x  + (int)jLabel.getSize().getWidth(), jLabel.getLocation().y  + (int)jLabel.getSize().getHeight()/2);
            extensionPoint[5].setLocation(jLabel.getLocation().x - 5 , jLabel.getLocation().y  + (int)jLabel.getSize().getHeight());
            extensionPoint[6].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth()/2  - 2, jLabel.getLocation().y  + (int)jLabel.getSize().getHeight());
            extensionPoint[7].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth(), jLabel.getLocation().y  + (int)jLabel.getSize().getHeight());

            treeIO node = centerPanel.getSelected_Node();
            node.setX((double) jLabel.getLocation().x);
            node.setY((double) jLabel.getLocation().y);
            node.setW((double) c_size_w);
            node.setH((double) c_size_h);

            SetPosition setPosition = new SetPosition(centerPanel.getSize().getWidth(), centerPanel.getSize().getHeight());
            setPosition.set_line();
            centerPanel.setFinish();
        }
    }
}
