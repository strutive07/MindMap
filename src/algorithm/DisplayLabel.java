package algorithm;

import IO.treeIO;

import java.util.ArrayList;
import layout.MainFrame;

import javax.swing.*;

public class DisplayLabel {
    public void display(){
        ArrayList<treeIO> root_list = layout.MainLayout.getTree();
        treeIO root = root_list.get(0);
        JPanel centerPanel = layout.MainLayout.getCenterPane();
//        System.out.println(centerPanel);
        printNode(root);
    }


    private void printNode(treeIO node){
        System.out.println("name : " + node.getStringName() + "   |    x : " + node.getX() + "   |    y : " + node.getY() + "   |    w : " + node.getW() + "   |    h : " + node.getH() + "   |    LableColor : " + node.getLabelColor());

        for(int i=0; i<node.getChildCount(); i++){
            printNode(node.getChildAt(i));
        }
    }
}
