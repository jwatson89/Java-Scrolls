package mobs;

import java.util.ArrayList;

import items.Item;

public class Monster extends Mob {
		private int level;
		private int value;
		private ArrayList<Item> drops;
		
		public Monster() {
			super();
			level=1;
			value=1;
			drops=new ArrayList<Item>();
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public ArrayList<Item> getDrops() {
			return drops;
		}

		public void setDrops(ArrayList<Item> drops) {
			this.drops = drops;
		}
		
}
