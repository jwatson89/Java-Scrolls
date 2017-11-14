package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import items.*;
import maps.Map;
import mobs.*;

public class PlayScreen extends JPanel implements ActionListener {
		private Player p1;
		private Timer t1;
		private Map m1;
		JPanel inventory = new JPanel(); //new
		JLabel[] inventoryPics = new JLabel[10]; //new
		
		public PlayScreen() {
			init();
		}
		
		public void init() {
			addKeyListener(new TAdapter());
	        setFocusable(true);
	        setBackground(Color.WHITE);
	        p1 = new Player(this);
	        // from danny
	        Weapon smallHammer = new Weapon();
			ImageIcon smallHammerPic =new ImageIcon(this.getClass().getResource("/weapons/smallHammer.png"));
	        smallHammer.setPic(smallHammerPic);
	        p1.addInventoryItem(smallHammer);
	        
	        Weapon bigHammer = new Weapon();
			ImageIcon bigHammerPic =new ImageIcon(this.getClass().getResource("/weapons/bigHammer.png"));
	        bigHammer.setPic(bigHammerPic);
	        p1.addInventoryItem(bigHammer);
			
	        if(p1.getInventory().size() == 0) {				
	        	inventory.setLayout(new GridLayout(1,10,2,2));
	        	/*for(int i = 0; i < 10; i++) {
		        	inventoryPics[i] = blankPic;
		        	inventory.add(inventoryPics[i]);
		        }*/
	        }
	        else {
	        	inventory.setLayout(new GridLayout(1, 10, 2, 2/*p1.getInventory().size()*/));
	        	for(int i = 0; i < p1.getInventory().size(); i++) {  
	        		inventoryPics[i] = new JLabel(p1.getInventory().get(i).getPic());
	        		inventory.add(inventoryPics[i]);
		        }
	        }
	        
	        setLayout(new BorderLayout());
	        inventory.setPreferredSize(new Dimension(400, 100));
	        inventory.setBackground(Color.DARK_GRAY);
			this.add(inventory, BorderLayout.SOUTH);											
		//*END new//
	        p1.scale(50,50);
	        p1.setSpeed(3);
//	        t1 = new Timer(20, this);
//	        t1.start();
	        setVisible(true);
	        
	        m1=new Map();
		}
		

	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        m1.draw(g, this);
	        doDrawing(g);

	        Toolkit.getDefaultToolkit().sync();
	    }
		
		private void doDrawing(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
		    g2d.drawImage(p1.getImage(), p1.getX(), p1.getY(), this);        
			
		}

		   @Override
		    public void actionPerformed(ActionEvent e) {
//		        p1.move();
//		        p1.boundsCheck(getWidth(),getHeight());
//		        repaint();  
		    }

		    private class TAdapter extends KeyAdapter {
		    	
		        @Override

		        public void keyPressed(KeyEvent e) {
		       
		            int key = e.getKeyCode();

		            if (key == KeyEvent.VK_LEFT) {
		            	p1.moveTile(m1.getTileSize(),0);
//		                p1.setDX(-1);
		                //p1.setX(p1.getX()-20);
		            }

		            if (key == KeyEvent.VK_RIGHT) {
		            	p1.moveTile(m1.getTileSize(),1);
//		                p1.setDX(1);
		                //p1.setX(p1.getX()+20);
		            }

		            if (key == KeyEvent.VK_UP) {
		            	p1.moveTile(m1.getTileSize(),2);
//		                p1.setDY(-1);
		                //p1.setY(p1.getY()-20);
		            }

		            if (key == KeyEvent.VK_DOWN) {
		            	p1.moveTile(m1.getTileSize(),3);
//		                p1.setDY(1);
		                //p1.setY(p1.getY()+20);
		            }
		            
		        }
		        @Override
		        public void keyReleased(KeyEvent e) {
		            p1.setN(0);
		    }
		    }
}
