package mobs;

import items.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import gui.PlayScreen;

public class Player extends Mob {
		private PlayScreen ps;
		private Timer t1;
		private Image pic;
		private Image stand;
		private Image[][] walks;
		private int x,y,dx,dy,n,width,height,dir=0;
		private String name;
		private int carryWeight;
		private int exp;
		private Armor armorEquipped;
		private ArrayList<Item> inventory;
		private int gold;
		private ArrayList<Integer> skills;
		private ArrayList<Integer> stats;
		public Player(PlayScreen ps) {
			this();
			this.ps=ps;
		}
		public Player() {
			this(0,0,0,0,0,0,"",0,0,0);
		}
		public Player(int health, int speed, int armor, int locx, int locy, int attack, String name, int exp,
				 int gold,int cw) {
			super(health, speed, armor, locx, locy, attack);
			walks=new Image[4][15];
			this.name = name;
			this.exp = exp;
			this.inventory = new ArrayList<Item>();
			this.gold = gold;
			this.skills = new ArrayList<Integer>();
			this.stats = new ArrayList<Integer>();
			this.carryWeight=cw;
			this.armorEquipped=null;
			setX(10);setY(10);
			ImageIcon ii= new ImageIcon(this.getClass().getResource("/ppl/IMC/idle/crusader_idle_00000.png"));
			stand = ii.getImage();
			initwalk();
			pic=stand;
			t1=new Timer(20, new PlayerMover());
		}
		public void initwalk() {
			for(int j=0;j<4;j++) {
				for(int i=0;i<10;i++) {
					walks[j][i]=(new ImageIcon(this.getClass().getResource("/ppl/IMC/walk/crusader_walk_"+j*2+"000"+i+".png"))).getImage();
				}
				for(int i=10;i<15;i++) {
					walks[j][i]=(new ImageIcon(this.getClass().getResource("/ppl/IMC/walk/crusader_walk_"+j*2+"00"+i+".png"))).getImage();
				}
			}
		}
		public void scale(int width, int height) {
			this.width=width;
			this.height=height;
			for(int i=0;i<4;i++) {
				for(int j=0;j<15;j++) {
					walks[i][j]=walks[i][j].getScaledInstance(width, height, 1);
				}
			}
			stand=stand.getScaledInstance(width, height, 1);
			pic=pic.getScaledInstance(width, height, 1);
		}
		public ArrayList<Item> getInventory() {
			return this.inventory;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getExp() {
			return exp;
		}
		public void setExp(int exp) {
			this.exp = exp;
		}
		public void addInventoryItem(Item item) {
			this.inventory.add(item);
		}
		public void removeItem(Item item) {
			inventory.remove(item);
		}
		public int getGold() {
			return gold;
		}
		public void setGold(int gold) {
			this.gold = gold;
		}
		public void increaseSkill(int x) {
			skills.set(x, skills.get(x)+1);
		}
		public int getSkill(int x) {
			return skills.get(x);
		}
		public void levelUp() {
			//FIXME
		}
		public int getCarryWeight() {
			return carryWeight;
		}
		public void setCarryWeight(int carryWeight) {
			this.carryWeight = carryWeight;
		}
		public Armor getArmorEquipped() {
			return armorEquipped;
		}
		public void setArmorEquipped(Armor armorEquipped) {
			this.armorEquipped = armorEquipped;
		}
		public void increaseStat(int x) {
			stats.set(x, stats.get(x)+1);
		}
		public void setN(int n) {
			this.n=n;
		}
		public int getDX() {
			return dx;
		}
		public void setDX(int x) {
			this.dx = x;
		}
		public int getDY() {
			return dy;
		}
		public void setDY(int y) {
			this.dy = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public Image getImage() {
			return pic;
		}
		public void moveTile(int tileSize,int dir) {
			if(n==0) {
				t1.start();
				this.dir=dir;
			}			
		}
		private class PlayerMover implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(n==13) {
					t1.stop();
					n=15;
					setDX(0);
					setDY(0);
				}
				else if(dir==0) {
					setDX(-1);
					move();
				}
				else if(dir ==1) {
					setDX(1);
					move();
				}
				else if(dir ==2) {
					setDY(-1);
					move();					
				}
				else if(dir ==3) {
					setDY(1);
					move();					
				}
				ps.repaint(x-(getSpeed()*dx), y-(getSpeed()*dy), width+(getSpeed()*dx), height+(getSpeed()*dy));
			}
			
		}
		public void move() {
//			if(n==15) {
//				n=0;
//			}
			if(dx==-1) {
				pic=walks[3][n];
				n++;
			}
			else if(dx==1) {
				pic=walks[1][n];
				n++;
			}
			else if(dy==-1) {
				pic=walks[2][n];
				n++;
			}
			else if(dy==1) {
				pic=walks[0][n];
				n++;
			}
			else {
				pic=stand;
				n=0;
			}
			if(dx!=0) {
				x+=(getSpeed()*dx)+1;
			}
			else {
				y+=(getSpeed()*dy)+1;
			}
		}
		public void boundsCheck(int width2, int height2) {
			if(x<0) {
				x=0;
			}
			else if(x>width2-width) {
				x=width2-width;
			}
			if(y>height2-height) {
				y=height2-height;
			}
			else if(y<0) {
				y=0;
			}
			
		}
		
}
