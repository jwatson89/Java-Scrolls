package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class OptionsMenu extends BackgroundPanel{
	
	private JButton res1,res2,res3,res4,res5,back;
	private ButtonListener btn;
	public OptionsMenu() {
		super(new ImageIcon("C:\\Users\\matt0\\eclipse-workspace\\JavaScrolls\\resource\\flower.JPG").getImage());
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();	
	}
	private void buildGUI() {
		btn = new ButtonListener();
		res1=new JButton ("1920x1080");
		res2 = new JButton("1600x900");
		res3 = new JButton("1366x768");
		res4 = new JButton("1280x720");
		res5 = new JButton("1024x557");
		back= new JButton("Back");
		res1.addActionListener(btn);
		res2.addActionListener(btn);
		res3.addActionListener(btn);
		res4.addActionListener(btn);
		res5.addActionListener(btn);
		back.addActionListener(btn);
		add(res1);
		add(res2);
		add(res3);
		add(res4);
		add(res5);
		add(back);
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JButton source=(JButton)e.getSource();
			if(source.equals(res1)) {
				getTopLevelAncestor().resize(1920,1080);
			}
			else if (source.equals(res2)) {
				getTopLevelAncestor().resize(1600,900);
			}
			else if(source.equals(res3)) {
				getTopLevelAncestor().resize(1366,768);
			}
			else if(source.equals(res4)) {
				getTopLevelAncestor().resize(1280,720);
			}
			else if(source.equals(res5)) {
				getTopLevelAncestor().resize(1024,557);
			}
			else if(source.equals(back)) {
				((MainMenu)getTopLevelAncestor()).returnToMain();
			}
		}
	}
}
