package layout;

import IO.treeIO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainLayout{
	private static ArrayList<treeIO> root_list;
	private static JPanel centerPanel;
	private static JScrollPane centerScrollPanel;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		root_list = new ArrayList<treeIO>();
		centerPanel =  new JPanel();
		centerScrollPanel = new JScrollPane();
		MainFrame frame = new MainFrame();
	}
	public static ArrayList<treeIO> getTree(){
		return root_list;
	}
	public static JPanel getCenterPanel(){
		return centerPanel;
	}

	public static JScrollPane getCenterScrollPanel() {
		return centerScrollPanel;
	}
}

