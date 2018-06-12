package IO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import algorithm.DisplayLabel;
import algorithm.SetPosition;
import layout.*;
import listeners.AttributeApply;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveEvent implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "저장":
                boolean isSaved = layout.MainLayout.isSaved();
                System.out.println("isSaved : " + isSaved);
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
            if(!isSaved){
                layout.MainLayout.setIsSaved(true);
            }
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
            JButton resetButton = new JButton("새로 만들기");
            resetButton.addActionListener(saveEvent);
            JButton ApplyButton = new JButton("적용");
            ApplyButton.addActionListener(new ApplyEvent());
            JButton modifiyButton = new JButton("변경");
            modifiyButton.addActionListener(new AttributeApply());
            JButton closeButton = new JButton("닫기");
            closeButton.addActionListener(new ExitEvent());

            jToolBar.add(saveButton);
            jToolBar.add(loadButton);
            jToolBar.add(reSaveButton);
            jToolBar.add(resetButton);
            jToolBar.add(ApplyButton);
            jToolBar.add(modifiyButton);
            jToolBar.add(closeButton);
            jToolBar.addSeparator();


            for(int i=0; i<root_list.size(); i++){
                final int idx = i;
                JButton root_button = new JButton("마인드맵 " + (i + 1));
                root_button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DisplayLabel displayLabel = new DisplayLabel();
                        MainFrame frame = layout.MainLayout.getFrame();
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

            CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
            SetPosition setPosition = new SetPosition(centerPanel.getSize().getWidth(), centerPanel.getSize().getHeight());
            setPosition.set_line();
            layout.MainLayout.getFrame().setNow_selected_root(0);
            DisplayLabel displayLabel = new DisplayLabel();
            displayLabel.display(0);
            String file_name = jFileChooser.getSelectedFile().getName();
            layout.MainLayout.getFrame().setTitle("Mind Map - FILE : " + file_name);


//            centerPanel.setFinish(0);

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
        JToolBar jToolBar = layout.MainLayout.getFrame().getToolBar();
        jToolBar.removeAll();
        SaveEvent saveEvent = new SaveEvent();
        JButton saveButton = new JButton("저장");
        saveButton.addActionListener(saveEvent);
        JButton loadButton = new JButton("불러오기");
        loadButton.addActionListener(saveEvent);
        JButton reSaveButton = new JButton("다른 이름으로 저장");
        reSaveButton.addActionListener(saveEvent);
        JButton resetButton = new JButton("새로 만들기");
        resetButton.addActionListener(saveEvent);
        JButton ApplyButton = new JButton("적용");
        ApplyButton.addActionListener(new ApplyEvent());
        JButton modifiyButton = new JButton("변경");
        modifiyButton.addActionListener(new AttributeApply());
        JButton closeButton = new JButton("닫기");
        closeButton.addActionListener(new ExitEvent());

        jToolBar.add(saveButton);
        jToolBar.add(loadButton);
        jToolBar.add(reSaveButton);
        jToolBar.add(resetButton);
        jToolBar.add(ApplyButton);
        jToolBar.add(modifiyButton);
        jToolBar.add(closeButton);
        jToolBar.addSeparator();

        jToolBar.revalidate();
        jToolBar.repaint();

    }
}
