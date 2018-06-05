package layout;

import IO.treeIO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainLayout{
	private static ArrayList<treeIO> root_list;
	private static JPanel centerPanel;
    private static AttributePane rightPanel;
    private static ArrayList<JLabel> labels;


    public static void main(String[] args) {
		// TODO Auto-generated method stub
		root_list = new ArrayList<treeIO>();
		centerPanel =  new JPanel();
		MainFrame frame = new MainFrame();
	}


    public static ArrayList<JLabel> getLabels() {
        return labels;
    }

    public static void setLabels(ArrayList<JLabel> labels) {
        MainLayout.labels = labels;
    }
    public static AttributePane getRightPanel() {
        return rightPanel;
    }
    public static void setRightPanel(AttributePane rightPanel) {
        MainLayout.rightPanel = rightPanel;
    }
	public static ArrayList<treeIO> getTree(){
		return root_list;
	}
	public static JPanel getCenterPanel(){
		return centerPanel;
	}

}

