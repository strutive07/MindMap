package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import IO.SaveEvent;
import IO.treeIO;
import algorithm.DisplayLabel;
import algorithm.SetPosition;
import com.sun.java.swing.action.ApplyAction;
import listeners.AttributeApply;
import sun.plugin.javascript.JSContext;
import util.ColorTable;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	JSplitPane sp, sp2;
	TextEditorPane leftPanel;
	JPanel centerPanel;
    AttributePane rightPanel;
	JToolBar toolBar;
	int now_selected_root;

	public int getNow_selected_root() {
		return now_selected_root;
	}

	public void setNow_selected_root(int now_selected_root) {
		this.now_selected_root = now_selected_root;
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(JToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public MainFrame(){
		now_selected_root = 0;
		setTitle("MindMap By 장원준, 문태진 - FILE : 문서 1(저장 안됨)");

		
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
		SaveEvent saveEvent = new SaveEvent();
		JMenuBar menuBar = new JMenuBar();
		JMenu filemenu = new JMenu("파일");
		JMenuItem saveItem = new JMenuItem("저장");
		saveItem.addActionListener(saveEvent);
		JMenuItem loadItem = new JMenuItem("열기");
		loadItem.addActionListener(saveEvent);
		JMenuItem reSaveItem = new JMenuItem("다른 이름으로 저장");
		reSaveItem.addActionListener(saveEvent);
		JMenuItem resetItem = new JMenuItem("새로 만들기");
		resetItem.addActionListener(saveEvent);
		filemenu.add(saveItem);
		filemenu.add(loadItem);
		filemenu.add(reSaveItem);
		filemenu.add(resetItem);
		menuBar.add(filemenu);

		JMenu editMenu = new JMenu("편집");
		JMenuItem ApplyItem = new JMenuItem("적용");
		ApplyItem.addActionListener(new ApplyEvent());
		JMenuItem ChangeItem = new JMenuItem("변경");
		ChangeItem.addActionListener(new AttributeApply());
		editMenu.add(ApplyItem);
		editMenu.add(ChangeItem);
		menuBar.add(editMenu);

		JMenu windowMenu = new JMenu("윈도우");
		JMenuItem exitItem = new JMenuItem("닫기");
		exitItem.addActionListener(new ExitEvent());
		windowMenu.add(exitItem);
		menuBar.add(windowMenu);


		menuBar.setBackground(ColorTable.MenuBar_blue);
		filemenu.setBackground(ColorTable.MenuBar_blue);
		menuBar.setOpaque(true);
		filemenu.setOpaque(true);
		this.setJMenuBar(menuBar);
	}
	private void createToolBar() {
		SaveEvent saveEvent = new SaveEvent();
		toolBar = new JToolBar("ToolBar");
		toolBar.setBackground(ColorTable.ToolBar_blue);
		toolBar.setFloatable(false);
		JButton saveButton = new JButton("저장");
		saveButton.addActionListener(saveEvent);
		JButton loadButton = new JButton("불러오기");
		loadButton.addActionListener(saveEvent);
		JButton reSaveButton = new JButton("다른 이름으로 저장");
		reSaveButton.addActionListener(saveEvent);
		JButton resetButton = new JButton("새로 만들기");
		resetButton.addActionListener(saveEvent);
		JButton ApplyButton = new JButton("적용");
		ApplyButton.addActionListener(new ApplyEvent());
		JButton modifiyButton = new JButton("변경");
		modifiyButton.addActionListener(new AttributeApply());
		JButton closeButton = new JButton("닫기");
		closeButton.addActionListener(new ExitEvent());

		toolBar.add(saveButton);
		toolBar.add(loadButton);
		toolBar.add(reSaveButton);
		toolBar.add(resetButton);
		toolBar.add(ApplyButton);
		toolBar.add(modifiyButton);
		toolBar.add(closeButton);
		getContentPane().add(toolBar, BorderLayout.NORTH);


	}

	private void createSplitPane() {
		
		this.getContentPane().setLayout(new BorderLayout());
		
		leftPanel = new TextEditorPane((int)this.getSize().getWidth()/6);

		JScrollPane leftScrollPanel = new JScrollPane(leftPanel);
		
		
        CenterPanel centerPanel = MainLayout.getCenterPanel();

        centerPanel.setSize( 2 * (int)this.getSize().getWidth() * 2, (int)this.getSize().getHeight() * 2);
        centerPanel.setPreferredSize(new Dimension( 2 * (int)this.getSize().getWidth()/3, (int)this.getSize().getHeight()));
        centerPanel.setLayout(null);
        centerPanel.setBackground(ColorTable.PaneBackground_blue2);
        centerPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if(centerPanel.getExtensionPoint() != null){

					JLabel[] pp = centerPanel.getExtensionPoint();
					for(int i=0; i<pp.length; i++){
//						if(i == 0 || i == 1 || i == 3){
//							continue;
//						}

						centerPanel.remove(pp[i]);
					}
					AttributePane attributePane = layout.MainLayout.getRightPanel();
					attributePane.getText_TEXT().setText("");
					attributePane.getText_x().setText("");
					attributePane.getText_y().setText("");
					attributePane.getText_w().setText("");
					attributePane.getText_h().setText("");
					attributePane.getText_color().setText("");

					centerPanel.revalidate();
					centerPanel.repaint();
					centerPanel.setFinish(now_selected_root);
				}
				JLabel before_label = centerPanel.getSelected_Label();
				if(before_label != null){
					before_label.setBackground(ColorTable.ReverseColor(before_label.getBackground()));
				}
				centerPanel.setSelected_Label(null);
			}
		});

        JScrollPane centerScrollPanel= new JScrollPane();
//        centerScrollPanel.setViewportView(centerPanel);
//		centerScrollPanel.setBounds((int)this.getSize().getWidth()/6, 0,  2 * (int)this.getSize().getWidth()/3, (int)this.getSize().getHeight());
		centerScrollPanel.getViewport().add(centerPanel);

        rightPanel = new AttributePane((int)this.getSize().getWidth()/6);
        layout.MainLayout.setRightPanel(rightPanel);

        JScrollPane rightScrollPanel = new JScrollPane(rightPanel);
        MainFrame frame = this;

		centerScrollPanel.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
				centerPanel.setFinish(frame.getNow_selected_root());
			}
		});
		centerScrollPanel.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
				centerPanel.setFinish(frame.getNow_selected_root());
			}
		});

        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setLeftComponent(leftScrollPanel);
        sp.setRightComponent(centerScrollPanel);


        sp2.setLeftComponent(sp);
        sp2.setRightComponent(rightScrollPanel);
        
        sp.setDividerSize(0);
        sp2.setDividerSize(0);

        
        sp.setDividerLocation((int)this.getSize().getWidth()/6);
        sp2.setDividerLocation((int)this.getSize().getWidth() - (int)this.getSize().getWidth()/6);
        
        this.getContentPane().add(sp2, BorderLayout.CENTER);
        layout.MainLayout.setLeftPanel(leftPanel);
        
	}

    private void JFrameResize(){
		sp.setDividerLocation((int)this.getSize().getWidth()/6);
		sp2.setDividerLocation((int)this.getSize().getWidth() - (int)this.getSize().getWidth()/6);
		leftPanel.setTopLabelFontSize((int)this.getSize().getWidth()/6);
		rightPanel.setTopLabelFontSize((int)this.getSize().getWidth()/6);
		CenterPanel centerPanel = layout.MainLayout.getCenterPanel();

		SetPosition setPosition = new SetPosition(centerPanel.getSize().getWidth(), centerPanel.getSize().getHeight());
		setPosition.set_line();


		centerPanel.setFinish(now_selected_root);
	}
}
//class ScrollEvent implements AdjustmentListener{
//
//	@Override
//	public void adjustmentValueChanged(AdjustmentEvent e) {
//		CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
//		MainFrame frame = layout.MainLayout.getFrame();
//
//		centerPanel.setFinish(frame.getNow_selected_root());
//	}
//}





