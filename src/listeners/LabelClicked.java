package listeners;

import IO.treeIO;
import layout.AttributePane;
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
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
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

    }
}
