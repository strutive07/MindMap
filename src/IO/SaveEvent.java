package IO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import algorithm.DisplayLabel;
import layout.MainLayout;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveEvent implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "저장":
                saveMethod();
                break;
            case "불러오기":
                loadMethod();
                break;
        }
    }
    private void saveMethod(){
        System.out.println("SaveMethod 호출");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new FileNameExtensionFilter("JSON 파일", "json"));
        int return_value = jFileChooser.showSaveDialog(layout.MainLayout.getFrame());
        if(return_value == JFileChooser.APPROVE_OPTION){
            String path = jFileChooser.getSelectedFile().getPath();
            ArrayList<treeIO> root_list = MainLayout.getTree();
            if(root_list.size() == 0){
                layout.MainLayout.getLeftPanel().makeTree();
            }
            fileIO.export_Tree(root_list, path + ".json");
            String file_name = jFileChooser.getSelectedFile().getName();
            layout.MainLayout.getFrame().setTitle("Mind Map - FILE : " + file_name);
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
        }



    }



}
