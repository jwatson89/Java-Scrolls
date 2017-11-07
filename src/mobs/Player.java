package mobs;

import items.*;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player extends Mob {
		private Image pic;
		private Image stand;
		private Image[] walks;
		private int x,y,dx,dy,dir,n;
		private String name;
		private int carryWeight;
		private int exp;
		private Armor armorEquipped;
		private ArrayList<Item> inventory;
		private int gold;
		private ArrayList<Integer> skills;
		private ArrayList<Integer> stats;
		public Player() {
			this(0,0,0,0,0,0,"",0,0,0);
		}
		public Player(int health, int speed, int armor, int locx, int locy, int attack, String name, int exp,
				 int gold,int cw) {
			super(health, speed, armor, locx, locy, attack);
			walks=new Image[15];
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
		}
		public void initwalk() {
			for(int i=0;i<10;i++) {
				walks[i]=(new ImageIcon(this.getClass().getResource("/ppl/IMC/walk/crusader_walk_2000"+i+".png"))).getImage();
			}
			for(int i=10;i<15;i++) {
				walks[i]=(new ImageIcon(this.getClass().getResource("/ppl/IMC/walk/crusader_walk_200"+i+".png"))).getImage();
			}
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
		public void move() {
			if(n==15) {
				n=0;
			}
			if(dx==-1) {
				
			}
			else if(dx==1) {
				
			}
			else if(dy==-1) {
				pic=walks[n];
				n++;
			}
			else {
				pic=stand;
				n=0;
			}
			x+=(getSpeed()*dx);
			y+=(getSpeed()*dy);
		}
		
}
