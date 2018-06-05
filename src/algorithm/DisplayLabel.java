package algorithm;

import IO.treeIO;

import java.awt.*;
import java.util.ArrayList;

import layout.CenterPanel;
import layout.MainFrame;
import layout.MainLayout;
import listeners.LabelClicked;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class DisplayLabel {
    JPanel centerPanel;
    JScrollPane jScrollPane;

    public void display(){

        ArrayList<treeIO> root_list = layout.MainLayout.getTree();
        //TODO 루트 2개 이상인거 구현하기
        treeIO root = root_list.get(0);
        centerPanel = layout.MainLayout.getCenterPanel();
        centerPanel.removeAll();
        ArrayList<JLabel> labels = new ArrayList<JLabel>();
        sub_displayNode(root, labels);
        centerPanel.revalidate();
        centerPanel.repaint();
        layout.MainLayout.setLabels(labels);
        CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
        centerPanel.setFinish();
    }


    private void sub_displayNode(treeIO node, ArrayList<JLabel> labels){
        JLabel label = new JLabel(node.getStringName(), SwingConstants.CENTER);
        label.setBounds((int)node.getX(), (int)node.getY(), (int)node.getW(), (int)node.getH());
//        label.setSize((int)node.getW(), (int)node.getH());
//        label.setLocation((int)node.getX(), (int)node.getY());
        label.setBorder(new TitledBorder(new LineBorder(Color.black, 1)));
        label.setOpaque(true);
        Color label_color = Color.decode(Integer.toString(node.getLabelColor()));
        label.setBackground(label_color);
        label.setName(Integer.toString(node.getNodeNumber()));
        label.addMouseListener(new LabelClicked());
        labels.add(label);
        centerPanel.add(label);
        System.out.println(label);
        centerPanel.revalidate();
        centerPanel.repaint();
        label.setVisible(true);


        for(int i=0; i<node.getChildCount(); i++){
            sub_displayNode(node.getChildAt(i), labels);
        }
    }
}
