package gui;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainMenu extends JFrame {
	private BackgroundPanel background;
	private JButton play, load, options;
	private ButtonListener listener;
	private OptionsMenu opt;
	private PlayScreen ps;
	private PauseMenu pauseMenu;
	private JPanel playContainer;
	private StatBar statBar;
	private InventoryDisplay invDisp;
	public MainMenu(){
		ps = new PlayScreen();
		playContainer = new JPanel();
		pauseMenu = new PauseMenu(ps,this);
		ps.linkPauseMenu(pauseMenu);
		opt=new OptionsMenu();
		ImageIcon ii =new ImageIcon(this.getClass().getResource("../Skyrimmap.png"));
		background = new BackgroundPanel(ii.getImage());
		background.setLayout(new BorderLayout());
		setContentPane(background);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playContainer.setLayout(new BorderLayout());
		statBar = new StatBar(ps.getPlayer());
		invDisp = new InventoryDisplay(ps.getPlayer());
		invDisp.setPreferredSize(new Dimension(ps.getWidth(),120));
		buildGUI();	
	}
	public void buildGUI(){
		playContainer.add(ps,BorderLayout.CENTER);
		playContainer.add(statBar,BorderLayout.NORTH);
		playContainer.add(invDisp,BorderLayout.SOUTH);
		listener = new ButtonListener();
     	play=new JButton("Play");
     	play.addActionListener(listener);
     	load=new JButton("Load");
     	load.addActionListener(listener);
     	options=new JButton("Options");
     	options.addActionListener(listener);
     	options.setSize(new Dimension(50,100));
     	play.setSize(new Dimension(50,100));
     	load.setSize(new Dimension(50,100));
     	JPanel tmp= new JPanel();
     	tmp.add(play);
     	tmp.add(load);
     	tmp.add(options);
     	JLabel tmp2 = new JLabel();
     	ImageIcon txt=new ImageIcon(this.getClass().getResource("../The Java Scrolls.png"));
		tmp2.setIcon((Icon) txt);
		JPanel tmp3=new JPanel();
		tmp3.setLayout(new FlowLayout(FlowLayout.CENTER));
		tmp3.add(tmp2);
     	background.add(tmp,BorderLayout.CENTER);
     	background.add(new JPanel(),BorderLayout.EAST);
     	background.add(tmp3,BorderLayout.NORTH);
     	background.add(new JPanel(),BorderLayout.WEST);
     	background.add(new JPanel(),BorderLayout.SOUTH);
     	setSize(1920,1080);
		setVisible(true);
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			
			JButton source=(JButton)e.getSource();
			if(source.equals(play)) {
				setContentPane(playContainer);
				validate();
				ps.setFocusable(true);
				ps.requestFocus();
				//System.out.println("PLAY");
			}
			else if (source.equals(load)) {
				PlayScreen pstmp;
				pstmp= PlayScreen.load();
				if(pstmp!=null) {
					ps =pstmp;
					ps.linkPauseMenu(pauseMenu);
					pauseMenu.setPlayScreen(ps);
				}
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
	public void setPlayScreen(PlayScreen ps) { // aka onLoad
		playContainer.remove(this.ps);
		this.ps=ps;		
		playContainer.add(ps,BorderLayout.CENTER);
		statBar.setPlayer(ps.getPlayer());
		invDisp.setPlayer(ps.getPlayer());
		ps.linkPauseMenu(pauseMenu);
		playContainer.repaint();
		//statBar.newTimer();
	}
}
