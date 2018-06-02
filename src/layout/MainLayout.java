package layout;

import IO.treeIO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainLayout{
	private static ArrayList<treeIO> root_list = new ArrayList<treeIO>();
	private static JPanel centerPanel;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame frame = new MainFrame();

	}
	public static ArrayList<treeIO> getTree(){
		return root_list;
	}
	public static JPanel getCenterPane(){
		return centerPanel;
	}
}
