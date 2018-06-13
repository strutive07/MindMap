package layout;

import IO.SaveEvent;
import IO.treeIO;
import algorithm.DisplayLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ApplyEvent implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
        final MainFrame frame = layout.MainLayout.getFrame();
        centerPanel.setPreferredSize(new Dimension( 2 * (int)frame.getSize().getWidth()/3, (int)frame.getHeight()));
        centerPanel.setSize(new Dimension( 2 * (int)frame.getSize().getWidth()/3, (int)frame.getHeight()));

        layout.MainLayout.getLeftPanel().makeTree();

        ArrayList<treeIO> root_list = layout.MainLayout.getTree();
        JToolBar jToolBar = layout.MainLayout.getFrame().getToolBar();
        jToolBar.removeAll();

        SaveEvent saveEvent = new SaveEvent();
        JButton saveButton = new JButton("저장");
        saveButton.addActionListener(saveEvent);
        JButton loadButton = new JButton("불러오기");
        loadButton.addActionListener(saveEvent);
        JButton reSaveButton = new JButton("다른 이름으로 저장");
        reSaveButton.addActionListener(saveEvent);
        jToolBar.add(saveButton);
        jToolBar.add(loadButton);
        jToolBar.add(reSaveButton);
        jToolBar.addSeparator();


        for(int i=0; i<root_list.size(); i++){
            final int idx = i;
            JButton root_button = new JButton("마인드맵 " + (i + 1));
            root_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DisplayLabel displayLabel = new DisplayLabel();
                    frame.setNow_selected_root(idx);
                    displayLabel.display(frame.getNow_selected_root());
                    layout.MainLayout.getCenterPanel().setExtensionPoint(null);
                    layout.MainLayout.getCenterPanel().setSelected_Label(null);
                    layout.MainLayout.getCenterPanel().setSelected_Node(null);

                    AttributePane attributePane = layout.MainLayout.getRightPanel();
                    attributePane.getText_TEXT().setText("");
                    attributePane.getText_x().setText("");
                    attributePane.getText_y().setText("");
                    attributePane.getText_w().setText("");
                    attributePane.getText_h().setText("");
                    attributePane.getText_color().setText("");
                }
            });
            jToolBar.add(root_button);
        }
        jToolBar.revalidate();
        jToolBar.repaint();

        layout.MainLayout.getCenterPanel().setExtensionPoint(null);
        layout.MainLayout.getCenterPanel().setSelected_Label(null);
        layout.MainLayout.getCenterPanel().setSelected_Node(null);
    }
}
