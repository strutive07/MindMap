package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		SaveEvent saveEvent = new SaveEvent();
		JMenuBar menuBar = new JMenuBar();
		JMenu filemenu = new JMenu("파일");
		JMenuItem saveItem = new JMenuItem("저장");

		saveItem.addActionListener(saveEvent);

		JMenuItem loadItem = new JMenuItem("불러오기");

		loadItem.addActionListener(saveEvent);

		filemenu.add(saveItem);
		filemenu.add(loadItem);
		menuBar.add(filemenu);
		
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
