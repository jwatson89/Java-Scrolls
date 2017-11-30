package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import mobs.Player;

public class StatBar extends JPanel {
	private JLabel health,armor,attack,gold,exp;
	public Player p1;
	Timer t1;
	public StatBar(Player p) {
		super();
		this.p1=p;
		init();
	}
	public void init() {
		 this.setLayout(new FlowLayout(FlowLayout.CENTER));
	        health=new JLabel();
	        armor=new JLabel();
	        attack=new JLabel();
	        gold=new JLabel();
	        exp=new JLabel();
	        this.add(gold);
	        this.add(new JLabel("                 "));
	        this.add(armor);
	        this.add(new JLabel("                 "));
	        this.add(attack);
	        this.add(new JLabel("                 "));
	        this.add(health);
	        this.add(new JLabel("                 "));
	        this.add(exp);
	        this.setBackground(Color.LIGHT_GRAY);
	        t1= new Timer(25,new Updater());
	        t1.start();
	        this.setSize(1920, 32);
	        
	}
	private class Updater implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			update1();			
			repaint();
		}
	}
	
	public void setPlayer(Player p) {
		this.p1=p;
		
	}
	public void update1() {
		health.setText("Health: "+ p1.getHealth());
		armor.setText("Armor: "+ p1.getArmor());
		attack.setText("Attack: "+ p1.getAttack());
		gold.setText("Gold: "+ p1.getGold());
		exp.setText("Exp: "+ p1.getExp()+"/"+p1.getMaxExp());
	}
}
