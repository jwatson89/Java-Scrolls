package mobs;

import items.*;

import java.awt.Image;
import java.util.ArrayList;

public class Player extends Mob {
	//this is a test comment
		private Image pic;
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
			this.name = name;
			this.exp = exp;
			this.inventory = new ArrayList<Item>();
			this.gold = gold;
			this.skills = new ArrayList<Integer>();
			this.stats = new ArrayList<Integer>();
			this.carryWeight=cw;
			this.armorEquipped=null;
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
		
		
}
