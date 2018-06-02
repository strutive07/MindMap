package algorithm;

import IO.treeIO;

import java.awt.*;
import java.util.ArrayList;
import layout.MainFrame;
import layout.MainLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class DisplayLabel {
    JPanel centerPanel;
    JScrollPane jScrollPane;
    public void display(){
        ArrayList<treeIO> root_list = layout.MainLayout.getTree();
        treeIO root = root_list.get(0);
        centerPanel = layout.MainLayout.getCenterPanel();


        sub_displayNode(root);
        centerPanel.revalidate();
        centerPanel.repaint();
    }


    private void sub_displayNode(treeIO node){
        JLabel label = new JLabel(node.getStringName());
        label.setBounds((int)node.getX(), (int)node.getY(), (int)node.getW(), (int)node.getH());
//        label.setSize((int)node.getW(), (int)node.getH());
//        label.setLocation((int)node.getX(), (int)node.getY());
        label.setBorder(new TitledBorder(new LineBorder(Color.black, 3)));
        label.setOpaque(true);

        centerPanel.add(label);
        System.out.println(label);
        centerPanel.revalidate();
        centerPanel.repaint();
        label.setVisible(true);


        for(int i=0; i<node.getChildCount(); i++){
            sub_displayNode(node.getChildAt(i));
        }
    }
}
