package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import mobs.Player;

public class PlayScreen extends JPanel implements ActionListener {
		private Player p1;
		private Timer t1;
		
		public PlayScreen() {
			init();
		}
		
		public void init() {
			addKeyListener(new TAdapter());
	        setFocusable(true);
	        setBackground(Color.WHITE);

	        p1 = new Player();
	        p1.scale(150,100);
	        p1.setSpeed(3);
	        t1 = new Timer(20, this);
	        t1.start();
	        setVisible(true);
		}
		

	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        doDrawing(g);

	        Toolkit.getDefaultToolkit().sync();
	    }
		
		private void doDrawing(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
		    g2d.drawImage(p1.getImage(), p1.getX(), p1.getY(), this);        
			
		}

		   @Override
		    public void actionPerformed(ActionEvent e) {
		        p1.move();
		        p1.boundsCheck(getWidth(),getHeight());
		        repaint();  
		    }

		    private class TAdapter extends KeyAdapter {

		        @Override

		        public void keyPressed(KeyEvent e) {

		            int key = e.getKeyCode();

		            if (key == KeyEvent.VK_LEFT) {
		                p1.setDX(-1);
		                //p1.setX(p1.getX()-20);
		            }

		            if (key == KeyEvent.VK_RIGHT) {
		                p1.setDX(1);
		                //p1.setX(p1.getX()+20);
		            }

		            if (key == KeyEvent.VK_UP) {
		                p1.setDY(-1);
		                //p1.setY(p1.getY()-20);
		            }

		            if (key == KeyEvent.VK_DOWN) {
		                p1.setDY(1);
		                //p1.setY(p1.getY()+20);
		            }
		            
		        }
		        @Override
		        public void keyReleased(KeyEvent e) {
		            
		            int key = e.getKeyCode();

		            if (key == KeyEvent.VK_LEFT) {
		            	p1.setDX(0);
		            }

		            if (key == KeyEvent.VK_RIGHT) {
		            	p1.setDX(0);
		            }

		            if (key == KeyEvent.VK_UP) {
		            	p1.setDY(0);
		            }

		            if (key == KeyEvent.VK_DOWN) {
		            	p1.setDY(0);
		            }
		    }
		    }
}
