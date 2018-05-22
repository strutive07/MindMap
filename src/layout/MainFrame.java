package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import util.ColorTable;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	JSplitPane sp, sp2;
	TextEditorPane leftPanel;
	JPanel centerPanel;
	AttributePane rightPanel;


	public MainFrame(){
		setTitle("MindMap By 장원준, 문태진");

		
		setSize(1280, 640);
		createSplitPane();
		createMenu();
		createToolBar();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				JFrameResize();
			}
		});
		setVisible(true);
		
		
	}
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu filemenu = new JMenu("파일");
		filemenu.add(new JMenuItem("저장"));
		filemenu.add(new JMenuItem("불러오기"));
		menuBar.add(filemenu);
		
		menuBar.setBackground(ColorTable.MenuBar_blue);
		filemenu.setBackground(ColorTable.MenuBar_blue);
		menuBar.setOpaque(true);
		filemenu.setOpaque(true);
		this.setJMenuBar(menuBar);
	}
	private void createToolBar() {
		JToolBar toolBar = new JToolBar("ToolBar");
		toolBar.setBackground(ColorTable.ToolBar_blue);
		toolBar.setFloatable(false);
		toolBar.add(new JButton("저장"));
		toolBar.add(new JButton("불러오기"));
		getContentPane().add(toolBar, BorderLayout.NORTH);
	}
	private void createSplitPane() {
		
		this.getContentPane().setLayout(new BorderLayout());
		
		leftPanel = new TextEditorPane((int)this.getSize().getWidth()/6);
		JScrollPane leftScrollPanel = new JScrollPane(leftPanel);
		
		
        centerPanel = new JPanel();
        centerPanel.setBackground(ColorTable.PaneBackground_blue2);

        rightPanel = new AttributePane((int)this.getSize().getWidth()/6);
        JScrollPane rightScrollPanel = new JScrollPane(rightPanel);
        
        

        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setLeftComponent(leftScrollPanel);
        sp.setRightComponent(centerPanel);
        
        sp2.setLeftComponent(sp);
        sp2.setRightComponent(rightScrollPanel);
        
        sp.setDividerSize(0);
        sp2.setDividerSize(0);

        
        sp.setDividerLocation((int)this.getSize().getWidth()/6);
        sp2.setDividerLocation((int)this.getSize().getWidth() - (int)this.getSize().getWidth()/6);
        
        this.add(sp2, BorderLayout.CENTER);
        
	}

	private void JFrameResize(){
		sp.setDividerLocation((int)this.getSize().getWidth()/6);
		sp2.setDividerLocation((int)this.getSize().getWidth() - (int)this.getSize().getWidth()/6);
		leftPanel.setTopLabelFontSize((int)this.getSize().getWidth()/6);
		rightPanel.setTopLabelFontSize((int)this.getSize().getWidth()/6);
	}
}