package IO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import layout.MainLayout;
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
        ArrayList<treeIO> root_list = MainLayout.getTree();
        fileIO.export_Tree(root_list);
    }
    private void loadMethod(){
        System.out.println("load 호출");
        fileIO.import_Json("OutPut.json");

    }

}
