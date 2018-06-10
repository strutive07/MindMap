package listeners;

import IO.treeIO;
import algorithm.SetPosition;
import layout.AttributePane;
import layout.CenterPanel;
import layout.MainFrame;
import layout.MainLayout;
import util.ColorTable;
import util.FindNodeByTarget;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;

public class LabelClicked extends MouseAdapter{
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        JLabel jLabel = (JLabel)e.getSource();

        CenterPanel centerPanel = layout.MainLayout.getCenterPanel();

        treeIO node = centerPanel.getSelected_Node();
        int x = e.getX() - (int)jLabel.getSize().getWidth() / 2;
        int y = e.getY() - (int) jLabel.getSize().getHeight() / 2;
        jLabel.setLocation(jLabel.getLocation().x + x, jLabel.getLocation().y + y);
        AttributePane attributePane = layout.MainLayout.getRightPanel();
        node.setX(jLabel.getLocation().x);
        node.setY(jLabel.getLocation().y);
        attributePane.getText_x().setText(Integer.toString((int)node.getX()));
        attributePane.getText_y().setText(Integer.toString((int)node.getY()));

        JLabel[] extensionPoint = centerPanel.getExtensionPoint();
        if(extensionPoint != null){
            System.out.println(extensionPoint[2].getLocation());
            extensionPoint[0].setLocation(extensionPoint[0].getLocation().x + x, extensionPoint[0].getLocation().y + y);
            extensionPoint[1].setLocation(extensionPoint[1].getLocation().x + x, extensionPoint[1].getLocation().y + y);
            extensionPoint[2].setLocation(extensionPoint[2].getLocation().x + x, extensionPoint[2].getLocation().y + y);
            extensionPoint[3].setLocation(extensionPoint[3].getLocation().x + x, extensionPoint[3].getLocation().y + y);
            extensionPoint[4].setLocation(extensionPoint[4].getLocation().x + x, extensionPoint[4].getLocation().y + y);
            extensionPoint[5].setLocation(extensionPoint[5].getLocation().x + x, extensionPoint[5].getLocation().y + y);
            extensionPoint[6].setLocation(extensionPoint[6].getLocation().x + x, extensionPoint[6].getLocation().y + y);
            extensionPoint[7].setLocation(extensionPoint[7].getLocation().x + x, extensionPoint[7].getLocation().y + y);
        }
        SetPosition setPosition = new SetPosition(centerPanel.getSize().getWidth(), centerPanel.getSize().getHeight());
        setPosition.set_line();
        MainFrame frame = layout.MainLayout.getFrame();
        centerPanel.setFinish(frame.getNow_selected_root());


    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("CCCCCCCCCC");
        CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
        super.mousePressed(e);
        JLabel jLabel = (JLabel) e.getSource();
        jLabel.setBackground(ColorTable.ReverseColor(jLabel.getBackground()));
        jLabel.setOpaque(true);
        JLabel before_label = centerPanel.getSelected_Label();
        if(before_label != null){
            before_label.setBackground(ColorTable.ReverseColor(jLabel.getBackground()));
        }

        int nodeNumber = Integer.parseInt(jLabel.getName());
//        System.out.println("nodeNumber : " + nodeNumber);
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

        centerPanel.setSelected_Node(target);
        centerPanel.setSelected_Label(jLabel);

        if(centerPanel.getExtensionPoint() != null){
            JLabel[] pp = centerPanel.getExtensionPoint();
            for(int i=0; i<pp.length; i++){
//                if(i == 0 || i == 1 || i == 3){
//                    continue;
//                }
                centerPanel.remove(pp[i]);
            }
        }

        centerPanel.setExtensionPoint(extensionPoint);
        for(int i=0; i<8; i++){
//            if(i == 0 || i == 1 || i == 3){
//                continue;
//            }
            extensionPoint[i] = new JLabel();
            extensionPoint[i].setSize(10,10);
            extensionPoint[i].setBackground(Color.black);
            extensionPoint[i].setOpaque(true);
            extensionPoint[i].setVisible(true);
            extensionPoint[i].addMouseListener(new dragLabel());
            extensionPoint[i].addMouseMotionListener(new dragLabel());
            extensionPoint[i].setName(Integer.toString(i));
        }

        extensionPoint[0].setLocation(jLabel.getLocation().x - 5, jLabel.getLocation().y - 5);
        extensionPoint[1].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth()/2 - 2, jLabel.getLocation().y - 5);
        extensionPoint[2].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth(), jLabel.getLocation().y - 5);
        extensionPoint[3].setLocation(jLabel.getLocation().x - 5, jLabel.getLocation().y  + (int)jLabel.getSize().getHeight()/2);
        extensionPoint[4].setLocation(jLabel.getLocation().x  + (int)jLabel.getSize().getWidth(), jLabel.getLocation().y  + (int)jLabel.getSize().getHeight()/2);
        extensionPoint[5].setLocation(jLabel.getLocation().x - 5 , jLabel.getLocation().y  + (int)jLabel.getSize().getHeight());
        extensionPoint[6].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth()/2  - 2, jLabel.getLocation().y  + (int)jLabel.getSize().getHeight());
        extensionPoint[7].setLocation(jLabel.getLocation().x + (int)jLabel.getSize().getWidth(), jLabel.getLocation().y  + (int)jLabel.getSize().getHeight());



        for(int i=0; i<8; i++){
//            if(i == 0 || i == 1 || i == 3){
//                continue;
//            }
            centerPanel.add(extensionPoint[i]);
        }

        centerPanel.revalidate();
        centerPanel.repaint();
        MainFrame frame = layout.MainLayout.getFrame();
        centerPanel.setFinish(frame.getNow_selected_root());

        centerPanel.setSelected_Label(jLabel);
    }
}

class dragLabel extends MouseAdapter{
    int x, y;
    int cx, cy;
    JLabel label = null;

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
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
        JLabel target_label = centerPanel.getSelected_Label();
        if(target_label != null){
            JLabel extensionLabel_one = (JLabel)e.getSource();
            int extensionLabelNumber = Integer.parseInt(extensionLabel_one.getName());
            int c_size_w;
            int c_size_h;
            int x = e.getX() - (int)extensionLabel_one.getSize().getWidth() / 2;
            int y = e.getY() - (int) extensionLabel_one.getSize().getHeight() / 2;

            switch (extensionLabelNumber){
                case 0:
                    c_size_w = (int)target_label.getSize().getWidth() - x;
                    c_size_h = (int)target_label.getSize().getHeight() - y;
                    extensionLabel_one.setLocation(extensionLabel_one.getLocation().x + x, extensionLabel_one.getLocation().y + y);
                    target_label.setLocation(target_label.getLocation().x + x, target_label.getLocation().y + y);

                    break;
                case 1:
                    c_size_w = (int)target_label.getSize().getWidth();
                    c_size_h = (int)target_label.getSize().getHeight() - y;
                    extensionLabel_one.setLocation(extensionLabel_one.getLocation().x, extensionLabel_one.getLocation().y + y);
                    target_label.setLocation(target_label.getLocation().x, target_label.getLocation().y + y);

                    break;
                case 2:
                    c_size_w = (int)target_label.getSize().getWidth() + x;
                    c_size_h = (int)target_label.getSize().getHeight() - y;
                    extensionLabel_one.setLocation(extensionLabel_one.getLocation().x + x, extensionLabel_one.getLocation().y + y);
                    target_label.setLocation(target_label.getLocation().x, target_label.getLocation().y + y);

                    break;
                case 3:
                    c_size_w = (int)target_label.getSize().getWidth() - x;
                    c_size_h = (int)target_label.getSize().getHeight();
                    extensionLabel_one.setLocation(extensionLabel_one.getLocation().x + x, extensionLabel_one.getLocation().y);
                    target_label.setLocation(target_label.getLocation().x + x, target_label.getLocation().y);

                    break;
                case 4:
                    c_size_w = (int)target_label.getSize().getWidth() + x;
                    c_size_h = (int)target_label.getSize().getHeight();
                    extensionLabel_one.setLocation(extensionLabel_one.getLocation().x + x, extensionLabel_one.getLocation().y);
//                    target_label.setLocation(target_label.getLocation().x + x, target_label.getLocation().y);

                    break;
                case 5:
                    c_size_w = (int)target_label.getSize().getWidth() - x;
                    c_size_h = (int)target_label.getSize().getHeight() + y;
                    extensionLabel_one.setLocation(extensionLabel_one.getLocation().x + x, extensionLabel_one.getLocation().y + y);
                    target_label.setLocation(target_label.getLocation().x + x, target_label.getLocation().y);

                    break;
                case 6:
                    c_size_w = (int)target_label.getSize().getWidth();
                    c_size_h = (int)target_label.getSize().getHeight() + y;
                    extensionLabel_one.setLocation(extensionLabel_one.getLocation().x, extensionLabel_one.getLocation().y + y);
                    target_label.setLocation(target_label.getLocation().x, target_label.getLocation().y);

                    break;
                case 7:
                    c_size_w = (int)target_label.getSize().getWidth() + x;
                    c_size_h = (int)target_label.getSize().getHeight() + y;
                    extensionLabel_one.setLocation(extensionLabel_one.getLocation().x + x, extensionLabel_one.getLocation().y + y);

                    break;
                    default:
                        c_size_w = (int)target_label.getSize().getWidth() + x;
                        c_size_h = (int)target_label.getSize().getHeight() + y;
                        extensionLabel_one.setLocation(extensionLabel_one.getLocation().x + x, extensionLabel_one.getLocation().y + y);
                        target_label.setLocation(target_label.getLocation().x + x, target_label.getLocation().y + y);

                        break;
            }

            if(c_size_w <= 20){
                c_size_w = 20;
            }
            if(c_size_h <= 25){
                c_size_h = 25;
            }
            AttributePane attributePane = layout.MainLayout.getRightPanel();
            attributePane.getText_w().setText(Integer.toString(c_size_w));
            attributePane.getText_h().setText(Integer.toString(c_size_h));
            target_label.setSize(c_size_w, c_size_h);


            label = null;
            JLabel[] extensionPoint = centerPanel.getExtensionPoint();
            extensionPoint[0].setLocation(target_label.getLocation().x - 5, (int)target_label.getLocation().y - 5);
            extensionPoint[1].setLocation(target_label.getLocation().x + (int)target_label.getSize().getWidth()/2 - 2, (int)target_label.getLocation().y - 5);
            extensionPoint[2].setLocation(target_label.getLocation().x + (int)target_label.getSize().getWidth(), target_label.getLocation().y - 5);
            extensionPoint[3].setLocation(target_label.getLocation().x - 5, target_label.getLocation().y  + (int)target_label.getSize().getHeight()/2);
            extensionPoint[4].setLocation(target_label.getLocation().x  + (int)target_label.getSize().getWidth(), target_label.getLocation().y  + (int)target_label.getSize().getHeight()/2);
            extensionPoint[5].setLocation(target_label.getLocation().x - 5 , target_label.getLocation().y  + (int)target_label.getSize().getHeight());
            extensionPoint[6].setLocation(target_label.getLocation().x + (int)target_label.getSize().getWidth()/2  - 2, target_label.getLocation().y  + (int)target_label.getSize().getHeight());
            extensionPoint[7].setLocation(target_label.getLocation().x + (int)target_label.getSize().getWidth(), target_label.getLocation().y  + (int)target_label.getSize().getHeight());

            treeIO node = centerPanel.getSelected_Node();
            node.setX((double) target_label.getLocation().x);
            node.setY((double) target_label.getLocation().y);
            node.setW((double) c_size_w);
            node.setH((double) c_size_h);

            SetPosition setPosition = new SetPosition(centerPanel.getSize().getWidth(), centerPanel.getSize().getHeight());
            setPosition.set_line();
            MainFrame frame = layout.MainLayout.getFrame();
            centerPanel.setFinish(frame.getNow_selected_root());

        }
    }
}
