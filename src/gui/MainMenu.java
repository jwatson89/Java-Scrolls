package gui;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainMenu extends JFrame {
	private BackgroundPanel background;
	private JButton play, load, options;
	private ButtonListener listener;
	private OptionsMenu opt;
	public MainMenu() {
		opt=new OptionsMenu();
		background = new BackgroundPanel(new ImageIcon("C:\\Users\\matt0\\eclipse-workspace\\JavaScrolls\\resource\\flower.JPG").getImage());
		background.setLayout(new FlowLayout(FlowLayout.LEFT));
		setContentPane(background);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();	
	}
				
	public void buildGUI() 
	{
		listener = new ButtonListener();
     	play=new JButton("Play");
     	play.addActionListener(listener);
     	background.add(play);
     	load=new JButton("Load");
     	load.addActionListener(listener);
     	background.add(load);
     	options=new JButton("Options");
     	options.addActionListener(listener);
     	background.add(options);
     	setSize(1280,720);
		setVisible(true);
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JButton source=(JButton)e.getSource();
			if(source.equals(play)) {
				System.out.println("PLAY");
			}
			else if (source.equals(load)) {
				
			}
			else if(source.equals(options)) {
				setContentPane(opt);
				validate();
			}
		}
	}
	public void returnToMain() {
		setContentPane(background);
		validate();
	}
	public static void main(String[] args) {
		MainMenu m = new MainMenu();
	}
}