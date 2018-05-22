package layout;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import util.AutoLabel;
import util.ColorTable;

public class TextEditorPane extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel TopLabel;
	JTextArea textArea;
	JButton ApplyButton;
	public TextEditorPane(int size) {
		this.setLayout(new BorderLayout());

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
				String treeText[] = textArea.getText().split("\t\n");
				for (int i=0; i<treeText.length; i++){
					System.out.println("ho : " + treeText[i]);
				}
			}
		});
	}

	public void setTopLabelFontSize(int size){
		AutoLabel.setLabelFontSize(TopLabel, size);
	}
}
