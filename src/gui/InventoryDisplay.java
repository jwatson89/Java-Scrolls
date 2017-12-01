package gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import items.*;
import mobs.Player;

public class InventoryDisplay extends JPanel {
	
	//private JButton[] inventoryPics = new JButton[10]; //new
	private Player p1;
	private BufferedImage[][] pics;
	private String[][] itemDescriptions;
	
	public InventoryDisplay(Player p) {
		this.p1=p;
		init();
	}
	public void init() {
		pics = new BufferedImage[2][27];
		itemDescriptions = new String[2][27];
		this.addMouseListener(new InventoryListener());
		this.setMinimumSize(new Dimension(1020,120));
		update();
	}
	public void update() {
		int i=0;
		int j=0;
		for(Item item :p1.getInventory()) {
			pics[i][j]= item.getPic();
			if(item.getClass().getName().equals("items.Armor")) {
				itemDescriptions[i][j]= ("Armor: "+((Armor)item).getArmor());
			}
			else if(item.getClass().getName().equals("items.Weapon")) {
				itemDescriptions[i][j]= ("Attack: "+((Weapon)item).getAttack());
			}
			else if(item.getClass().getName().equals("items.Misc")) {
				itemDescriptions[i][j]= (item.getName());
			}
			j++;
			if(j==27) {
				i++;
				j=0;
			}
		}
		repaint();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D)g;
		for(int i=0;i<2;i++) {
			for(int j=0;j<27;j++) {
				if(pics[i][j]!=null) {
					g2.drawImage(pics[i][j],j*70+5,i*60,60,50,null);
					g2.drawString(itemDescriptions[i][j], j*70,((i+1)*60)-5);
				}
			}
		}
	}
	
	
	private class InventoryListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX()/70;
			int y = e.getY()/60;
			if(x>=0 && x<27 && y>=0 && y<2) {
				if((x+(27*y))<p1.getInventory().size()) {
					Item thing = p1.getInventory().get(x+(27*y));
					if(thing.getClass().getName().equals("items.Armor")) {
						p1.setArmorEquipped((Armor)thing);
					}
					else if(thing.getClass().getName().equals("items.Weapon")) {
						p1.setWeaponEquipped((Weapon)thing);
					}
					else if(thing.getClass().getName().equals("items.Misc")) {
						
					}
				}
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {		}
		@Override
		public void mouseExited(MouseEvent arg0) {		}
		@Override
		public void mousePressed(MouseEvent arg0) {		}
		@Override
		public void mouseReleased(MouseEvent arg0) {		}
	}
	

	public void setPlayer(Player p) {
		this.p1=p;
	}
}
