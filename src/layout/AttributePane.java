package layout;

import util.AutoLabel;
import util.ColorTable;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AttributePane extends JPanel{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	JLabel TopLabel;
	JButton ApplyButton;
	TextField text_x, text_y, text_w, text_h, text_color, text_TEXT;
	GridBagLayout gridBagLayout;
	JPanel grid;

	public AttributePane(int size) {
		this.setLayout(new BorderLayout());

		TopLabel = new JLabel("Attribute Pane");

		grid = new JPanel();
		gridBagLayout = new GridBagLayout();
		grid.setLayout(gridBagLayout);
		grid.setBackground(ColorTable.PaneBackground_blue3);

		JLabel label_Text = new JLabel(" TEXT:");
		JLabel label_x = new JLabel(" x:");
		JLabel label_y = new JLabel(" y:");
		JLabel label_w = new JLabel(" w:");
		JLabel label_h = new JLabel(" h:");
		JLabel label_color = new JLabel(" Color:");

		Font label_font = new Font(label_Text.getFont().getName(), label_Text.getFont().getStyle(), 20);
		label_Text.setFont(label_font);
		label_x.setFont(label_font);
		label_y.setFont(label_font);
		label_w.setFont(label_font);
		label_h.setFont(label_font);
		label_color.setFont(label_font);




		text_TEXT = new TextField(10);
		text_TEXT.setEnabled(false);
		text_x = new TextField(10);
		text_y = new TextField(10);
		text_w = new TextField(10);
		text_h = new TextField(10);
		text_color = new TextField(1);

		gbinsert(label_Text, 0,0,1,1, 0.4);
		gbinsert(text_TEXT, 1,0,3,1, 0.6);

		gbinsert(label_x, 0,1,1,1, 0.4);
		gbinsert(text_x, 1,1,3,1, 0.6);

		gbinsert(label_y, 0,2,1,1, 0.4);
		gbinsert(text_y, 1,2,3,1, 0.6);

		gbinsert(label_w, 0,3,1,1, 0.4);
		gbinsert(text_w, 1,3,3,1, 0.6);

		gbinsert(label_h, 0,4,1,1, 0.4);
		gbinsert(text_h, 1,4,3,1, 0.6);

		gbinsert(label_color, 0,5,1,1, 0.4);
		gbinsert(text_color, 1,5,3,1, 0.6);


		ApplyButton = new JButton("변경");
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
		this.add(grid, BorderLayout.CENTER);
		this.add(ApplyButton, BorderLayout.SOUTH);

		this.setBackground(ColorTable.PaneBackground_blue3);
	}
	public void gbinsert(Component c, int x, int y, int w, int h, double weightx){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill= GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.weightx = weightx;
		gbc.insets = new Insets(20, 1, 20, 1);
		gridBagLayout.setConstraints(c,gbc);
		grid.add(c);
	}

	public void setTopLabelFontSize(int size){
		AutoLabel.setLabelFontSize(TopLabel, size);
	}
}
