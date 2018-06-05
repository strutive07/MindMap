package layout;

import IO.treeIO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainLayout{
	private static ArrayList<treeIO> root_list;
    private static TextEditorPane leftPanel;
	private static CenterPanel centerPanel;
    private static AttributePane rightPanel;
    private static ArrayList<JLabel> labels;
    private static MainFrame frame;



    public static void main(String[] args) {
		// TODO Auto-generated method stub
		root_list = new ArrayList<treeIO>();
		centerPanel =  new CenterPanel();
        frame = new MainFrame();
	}

    public static TextEditorPane getLeftPanel() {
        return leftPanel;
    }

    public static void setLeftPanel(TextEditorPane leftPanel) {
        MainLayout.leftPanel = leftPanel;
    }

    public static MainFrame getFrame() {
        return frame;
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
	public static CenterPanel getCenterPanel(){
		return centerPanel;
	}

}

