package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

import java.awt.*;
import javax.swing.*;

import IO.SaveEvent;
import sun.plugin.javascript.JSContext;
import util.ColorTable;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	JSplitPane sp, sp2;
	TextEditorPane leftPanel;
	JPanel centerPanel;
    AttributePane rightPanel;

    public MainFrame(){
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
		JMenuItem ChangeItem = new JMenuItem("변경");
		editMenu.add(ApplyItem);
		editMenu.add(ChangeItem);
		menuBar.add(editMenu);

		JMenu windowMenu = new JMenu("윈도우");
		JMenuItem exitItem = new JMenuItem("닫기");
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
		JToolBar toolBar = new JToolBar("ToolBar");
		toolBar.setBackground(ColorTable.ToolBar_blue);
		toolBar.setFloatable(false);
		JButton saveButton = new JButton("저장");
		saveButton.addActionListener(saveEvent);
		JButton loadButton = new JButton("불러오기");
		loadButton.addActionListener(saveEvent);
		JButton reSaveButton = new JButton("다른 이름으로 저장");

		toolBar.add(saveButton);
		toolBar.add(loadButton);
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

					centerPanel.revalidate();
					centerPanel.repaint();
					centerPanel.setFinish();
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

		centerScrollPanel.getHorizontalScrollBar().addAdjustmentListener(new ScrollEvent());
		centerScrollPanel.getVerticalScrollBar().addAdjustmentListener(new ScrollEvent());

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
		centerPanel.setFinish();
	}
}
class ScrollEvent implements AdjustmentListener{

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
		centerPanel.setFinish();
	}
}

