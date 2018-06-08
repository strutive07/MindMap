package listeners;

import IO.treeIO;
import algorithm.SetPosition;
import layout.AttributePane;
import layout.CenterPanel;
import util.FindNodeByTarget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AttributeApply extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        AttributePane attributePane = layout.MainLayout.getRightPanel();
        int selectedLabelNumber = attributePane.getSelectedLabelNumber();
        int selectedNodeNumber = attributePane.getSelectedNodeNumber();
        ArrayList<treeIO> root_list = layout.MainLayout.getTree();
        treeIO target = null;
        for(int i=0; i<root_list.size(); i++){
            treeIO root = root_list.get(i);
            target = FindNodeByTarget.dfs(root, selectedNodeNumber);
            if(target != null)
                break;
        }
        if(target == null){
            return;
        }

        target.setX(Double.parseDouble(attributePane.getText_x().getText()));
        target.setY(Double.parseDouble(attributePane.getText_y().getText()));
        target.setW(Double.parseDouble(attributePane.getText_w().getText()));
        target.setH(Double.parseDouble(attributePane.getText_h().getText()));

        int color_int  = Integer.parseInt(attributePane.getText_color().getText(), 16);

        target.setLabelColor(color_int);

        ArrayList<JLabel> labels = layout.MainLayout.getLabels();
        JLabel jLabel = labels.get(selectedLabelNumber);
        jLabel.setSize((int)target.getW(), (int)target.getH());
        jLabel.setLocation((int) target.getX(), (int) target.getY());

        Color color = Color.decode('#' + attributePane.getText_color().getText());
        jLabel.setBackground(color);
        CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
        SetPosition setPosition = new SetPosition(centerPanel.getSize().getWidth(), centerPanel.getSize().getHeight());
        setPosition.set_line();
        centerPanel.setFinish();
    }
}
