package IO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import algorithm.DisplayLabel;
import algorithm.SetPosition;
import layout.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveEvent implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "저장":
                boolean isSaved = layout.MainLayout.isSaved();
                saveMethod(isSaved);
                break;
            case "다른 이름으로 저장":
                saveMethod(false);
                break;
            case "불러오기":
                CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
                MainFrame frame = layout.MainLayout.getFrame();
                centerPanel.setPreferredSize(new Dimension( 2 * (int)frame.getSize().getWidth()/3, (int)frame.getHeight()));

                loadMethod();
                break;
            case "새로 만들기":


                resetMethod();
                break;
        }
    }
    private void saveMethod(boolean isSaved){
        int return_value;
        JFileChooser jFileChooser;
        jFileChooser = new JFileChooser();
        if(!isSaved){
            jFileChooser.setFileFilter(new FileNameExtensionFilter("JSON 파일", "json"));
            return_value = jFileChooser.showSaveDialog(layout.MainLayout.getFrame());
            layout.MainLayout.setIsSaved(true);
        }else{
            return_value = JFileChooser.APPROVE_OPTION;
        }
        if(return_value == JFileChooser.APPROVE_OPTION){
            String path;
            if(!isSaved){
                path = jFileChooser.getSelectedFile().getPath();
                layout.MainLayout.setFilePath(path);
            }else{
                path = layout.MainLayout.getFilePath();
            }
            ArrayList<treeIO> root_list = MainLayout.getTree();
            if(root_list.size() == 0){
                layout.MainLayout.getLeftPanel().makeTree();
            }

            if(!isSaved){
                fileIO.export_Tree(root_list, path + ".json");
            }else{
                fileIO.export_Tree(root_list, path);
            }

            layout.MainLayout.getFrame().setTitle("Mind Map - FILE : " + path);
        }
    }

    private void loadMethod(){
        System.out.println("load 호출");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new FileNameExtensionFilter("JSON 파일", "json"));
        int return_value = jFileChooser.showOpenDialog(layout.MainLayout.getFrame());
        if(return_value == JFileChooser.APPROVE_OPTION){
            String path = jFileChooser.getSelectedFile().getPath();
            fileIO.import_Json(path);
            DisplayLabel displayLabel = new DisplayLabel();
            displayLabel.display();
            String file_name = jFileChooser.getSelectedFile().getName();
            layout.MainLayout.getFrame().setTitle("Mind Map - FILE : " + file_name);

            CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
            SetPosition setPosition = new SetPosition(centerPanel.getSize().getWidth(), centerPanel.getSize().getHeight());
            setPosition.set_line();
            centerPanel.setFinish();

            layout.MainLayout.setFilePath(path);
            layout.MainLayout.getFrame().setTitle("Mind Map - FILE : " + path);
            layout.MainLayout.setIsSaved(true);
        }
    }
    private void resetMethod(){
        TextEditorPane textEditorPane = layout.MainLayout.getLeftPanel();
        CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
        AttributePane attributePane = layout.MainLayout.getRightPanel();

        MainFrame frame = layout.MainLayout.getFrame();
        centerPanel.setPreferredSize(new Dimension( 2 * (int)frame.getSize().getWidth()/3, (int)frame.getHeight()));

        layout.MainLayout.getFrame().setTitle("MindMap By 장원준, 문태진 - FILE : 문서 1(저장 안됨)");
        layout.MainLayout.setFilePath(null);
        layout.MainLayout.setIsSaved(false);
        textEditorPane.getTextArea().setText("");
        layout.MainLayout.getTree().clear();
        centerPanel.clear_list();
        centerPanel.removeAll();
        centerPanel.revalidate();
        centerPanel.repaint();
        centerPanel.setExtensionPoint(null);
        centerPanel.setSelected_Label(null);
        centerPanel.setSelected_Node(null);

        attributePane.getText_TEXT().setText("");
        attributePane.getText_x().setText("");
        attributePane.getText_y().setText("");
        attributePane.getText_w().setText("");
        attributePane.getText_h().setText("");
        attributePane.getText_color().setText("");

    }
}
