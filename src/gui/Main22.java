package gui;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.*;

public class Main {
	static MainMenu menu;
	static OptionsMenu opt;
	static JFrame mainframe;
	static BackgroundPanel bg;
	static ImageIcon im;
	/*public static void main(String[] args) {
		System.out.println("starting");
		mainframe=new JFrame();
		menu=new MainMenu();
		opt = new OptionsMenu();
		im=new ImageIcon("C:\\Users\\matt0\\eclipse-workspace\\JavaScrolls\\resource\\flower.JPG");
		bg =new BackgroundPanel(im.getImage());
		bg.add(menu.getContentPane());
		mainframe.setContentPane(bg);
		//mainframe.add(menu.getContentPane());
		mainframe.setSize(1280,720);
		mainframe.setLayout(new FlowLayout(FlowLayout.LEFT));
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainframe.setr
		mainframe.setVisible(true);		
	}*/
	public static void resize(int x, int y) {
		mainframe.setSize(x,y);
	}
	public static void fromOptionsToMain() {
		mainframe.setContentPane(menu.getContentPane());
		mainframe.add(menu.getpic());
		mainframe.revalidate();
	}
	public static void fromMainToOptions() {
		//JLabel x=opt.getpic();
		//x.add(opt.getContentPane());
		mainframe.setContentPane(opt.getContentPane());
		//mainframe.add(opt.getContentPane());
		mainframe.revalidate();
	}

}
