package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;
import items.*;
import maps.Grasslands;
import maps.Map;
import mobs.*;

public class PlayScreen extends JPanel implements Serializable{
		private Player p1;
		private Map m1;
		transient private PauseMenu pauseMenu;
		
		
		public PlayScreen() {
			init();
		}
		public void linkPauseMenu(PauseMenu pm) {
			this.pauseMenu=pm;
			addKeyListener(new TAdapter());
			p1.callAfterLoad(this);
		}
		public void init() {
			addKeyListener(new TAdapter());
	        setFocusable(true);
	        setBackground(Color.WHITE);
	        p1 = new Player(this);
		
	        m1=new Grasslands(5);
	        //p1.scale(m1.getTileSize(),m1.getTileSize());
	        p1.setSpeed(3);
//	        t1 = new Timer(20, this);
//	        t1.start();
	        setVisible(true);
	        
		}


		//TODO display player stats (health, armor, attack) at bottom of screen, above inventory

		
		
	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        m1.draw(g, this);
	        doDrawing(g);
	        //statBar.repaint();
	        Toolkit.getDefaultToolkit().sync();
	    }
		
		
		private void doDrawing(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
		    g2d.drawImage(p1.getImage(),null, p1.getX(), p1.getY());  
		    
			
		}
 

		    private class TAdapter extends KeyAdapter {
		    	@Override
		    	public void keyTyped(KeyEvent e) {
		    		if(e.getKeyChar()=='a') {
		            	p1.attack(m1);
		            	repaint();
		            }
		    	}
		        @Override
 		        public void keyPressed(KeyEvent e) {
		        	
		            int key = e.getKeyCode();
		        	if(key == KeyEvent.VK_ESCAPE) {
		        		pauseMenu.pause();
		        	}
		        	else if(!p1.moving) {
		        		p1.setN(0);		
		        	}
		        }
		        @Override
		        public void keyReleased(KeyEvent e) {

		            int key = e.getKeyCode();
		            if(!p1.moving) {
			            if (key == KeyEvent.VK_LEFT) {
			            	p1.moveTile(m1.getTileSize(),0,m1);
	//		                p1.setDX(-1);
			                //p1.setX(p1.getX()-20);
			            }
	
			            if (key == KeyEvent.VK_RIGHT) {
			            	p1.moveTile(m1.getTileSize(),1,m1);
	//		                p1.setDX(1);
			                //p1.setX(p1.getX()+20);
			            }
	
			            if (key == KeyEvent.VK_UP) {
			            	p1.moveTile(m1.getTileSize(),2,m1);
	//		                p1.setDY(-1);
			                //p1.setY(p1.getY()-20);
			            }
	
			            if (key == KeyEvent.VK_DOWN) {
			            	p1.moveTile(m1.getTileSize(),3,m1);
	//		                p1.setDY(1);
			                //p1.setY(p1.getY()+20);
			            }
			            
			        }
		        }
		    }

			public static void save(PlayScreen ps) { 
				try {
					FileOutputStream fout=new FileOutputStream("save1.sv");
					ObjectOutputStream out=new ObjectOutputStream(fout);
					out.writeObject(ps);
//					out.writeObject(ps.p1);
//					out.writeObject(ps.m1);
					out.close();
				} catch (IOException en) {
						en.printStackTrace();	
				}
				
			}

			public static PlayScreen load() {
				PlayScreen y=null;
				try {
					FileInputStream fin=new FileInputStream("save1.sv");
					ObjectInputStream in=new ObjectInputStream(fin);
					y = (PlayScreen)in.readObject();
//					y.setPlayer((Player)in.readObject());
//					y.setMap((Map)in.readObject());
					in.close();
					
				} catch (IOException | ClassNotFoundException c) {
							c.printStackTrace();
				}
				return y;
			}
			public void setPlayer(Player p) {
				this.p1=p;
			}
			public void setMap(Map m) {
				this.m1=m;
			}
			public Player getPlayer() {
				return p1;
			}
}
