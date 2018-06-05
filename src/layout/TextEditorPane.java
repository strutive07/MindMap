package layout;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import util.AutoLabel;
import util.ColorTable;
import IO.treeIO;
import layout.MainLayout;
import algorithm.SetPosition;
import algorithm.DisplayLabel;

public class TextEditorPane extends JPanel{
	/**
	 * 
	 */

	private void dfs(treeIO node){
		if(node.get_child_number_in_parent() == -1){
			System.out.println("Root 입니다. : " + node.getStringName());
		}else{
			System.out.println("부모 : " + node.getParent().getStringName() + " : " + node.getStringName());
		}
		for (int i=0; i<node.getChildCount(); i++){
			treeIO next_node = node.getChildAt(i);
			if(next_node != null){
				dfs(next_node);
			}
		}
	}



	private static final long serialVersionUID = 1L;
	JLabel TopLabel;
	JTextArea textArea;
	JButton ApplyButton;
	ArrayList<treeIO> root_list;

	public TextEditorPane(int size) {
		this.setLayout(new BorderLayout());
		root_list = MainLayout.getTree();
		TopLabel = new JLabel("Text Editor Pane");
		textArea = new JTextArea();
		textArea.setTabSize(2);
		ApplyButton = new JButton("적용");
		ApplyButton.setBackground(ColorTable.ApplyButton_Red);

		ApplyButton.setOpaque(true);

		LineBorder bd = new LineBorder(ColorTable.LabelBorder, 1, true);
		EmptyBorder embd = new EmptyBorder(8,8,8,8);
		CompoundBorder cbbd = new CompoundBorder(bd, embd);

		TopLabel.setHorizontalAlignment(JLabel.CENTER);
		TopLabel.setSize(size, 10);
		TopLabel.setBorder(cbbd);
		AutoLabel.setLabelFontSize(TopLabel, size);

		this.add(TopLabel, BorderLayout.NORTH);
		this.add(textArea, BorderLayout.CENTER);
		this.add(ApplyButton, BorderLayout.SOUTH);
		textArea.setBackground(ColorTable.PaneBackground_blue1);
		this.setBackground(ColorTable.PaneBackground_blue1);

		ApplyButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				makeTree();
			}
		});
	}

	public void setTopLabelFontSize(int size){
		AutoLabel.setLabelFontSize(TopLabel, size);
	}

	public void makeTree(){
		int nodeNumber = 0;
		String treeText[] = textArea.getText().split("[\n]");
		Stack<Pair<Integer, treeIO>> st = new Stack<Pair<Integer, treeIO>>();

		treeIO root = new treeIO(treeText[0], nodeNumber++);
		Pair<Integer, treeIO> first_root = new Pair<Integer, treeIO>(0, root);

		st.push(first_root);
		root_list.clear();
		root_list.add(root);



		//트리 만들기. 이걸 루트로
		for (int i=1; i<treeText.length; i++){
			int tab_cnt = 0;
			for (int j=0; j<treeText[i].length(); j++){
				if(treeText[i].charAt(j) == '\t'){
					tab_cnt++;
				}
			}
			treeText[i] = treeText[i].substring(tab_cnt,treeText[i].length());
			if(st.peek().left < tab_cnt){
				treeIO node = new treeIO(treeText[i], nodeNumber++);
				st.peek().right.push_child(node);
				Pair<Integer, treeIO> next_node = new Pair<Integer, treeIO>(tab_cnt, node);
				st.push(next_node);
			}else if(st.peek().left == tab_cnt){
				st.pop();
				treeIO node = new treeIO(treeText[i], nodeNumber++);
				if(st.empty()){
					root_list.add(node);
				}else{
					st.peek().right.push_child(node);
				}

				Pair<Integer, treeIO> next_node = new Pair<Integer, treeIO>(tab_cnt, node);
				st.push(next_node);

			}else{
				while(!st.empty()){
					if(st.peek().left == 0){
						st.pop();
						treeIO another_root = new treeIO(treeText[i], nodeNumber++);
						Pair<Integer, treeIO> next_node = new Pair<Integer, treeIO>(tab_cnt, another_root);
						st.push(next_node);
						root_list.add(another_root);
						break;
					}
					if(st.peek().left > tab_cnt){
						st.pop();
					}else if(st.peek().left == tab_cnt){
						st.pop();
						treeIO node = new treeIO(treeText[i], nodeNumber++);
						if(st.empty()){
							root_list.add(node);
						}else{
							st.peek().right.push_child(node);
						}
						Pair<Integer, treeIO> next_node = new Pair<Integer, treeIO>(tab_cnt, node);
						st.push(next_node);
						break;
					}
				}
			}
			System.out.println("cnt : " + tab_cnt + " ho : " + treeText[i]);
		}

		JPanel centerPanel = MainLayout.getCenterPanel();
		SetPosition setPosition = new SetPosition(centerPanel.getSize().getWidth(), centerPanel.getSize().getHeight());
		setPosition.start_SetPosition();
		setPosition.set_line();

		DisplayLabel displayLabel = new DisplayLabel();
		displayLabel.display();

	}

}



class Pair<L,R> {
	final L left;
	final R right;

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	static <L,R> Pair<L,R> of(L left, R right){
		return new Pair<L,R>(left, right);
	}
}