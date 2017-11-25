package mobs;

import java.awt.Image;

import javax.swing.ImageIcon;

import maps.*;

public class Mob {
		//private Icon pic;
		private int health;
		private int speed;
		private int armor;
		private int locx;
		private int locy;
		private int attack;
		protected int direction;
		private Image mobImage;
		
		
		public Mob(int health, int speed, int armor, int locx, int locy, int attack) {
			this.health = health;
			this.speed = speed;
			this.armor = armor;
			this.locx = locx;
			this.locy = locy;
			this.attack = attack;
			this.direction = 3;
		}
		public Mob() {
			this(0,0,0,0,0,0);
		}
		public int getHealth() {
			return health;
		}
		public void setHealth(int health) {
			this.health = health;
		}
		public int getSpeed() {
			return speed;
		}
		public void setSpeed(int speed) {
			this.speed = speed;
		}
		public int getArmor() {
			return armor;
		}
		public void setArmor(int armor) {
			this.armor = armor;
		}
		public int getLocx() {
			return locx;
		}
		public void setLocx(int locx) {
			this.locx = locx;
		}
		public int getLocy() {
			return locy;
		}
		public void setLocy(int locy) {
			this.locy = locy;
		}
		public int getAttack() {
			return attack;
		}
		public void setAttack(int attack) {
			this.attack = attack;
		}
		public Image getImage() {return this.mobImage;}
		public void setImage(String path) {
			this.mobImage = (new ImageIcon(this.getClass().getResource(path)).getImage());
		}
		
		public void attack(Map theMap) {
			Tile attackTile = null;
			
			switch(this.direction) { //FIXME ADD ANIMATIONS maybe...
				case 2:
					if (this.locy != 0) {
						attackTile = theMap.getTile(this.locx, this.locy - 1);
					}
					break;
				case 1:
					if (this.locx != 59) {
						attackTile = theMap.getTile(this.locx + 1, this.locy);
					}
					break;
				case 3:
					if (this.locy != 29) {
						attackTile = theMap.getTile(this.locx, this.locy + 1);
					}
					break;
				case 0:
					if (this.locx != 0) {
						attackTile = theMap.getTile(this.locx - 1, this.locy);
					}
					break;
			}
			if (attackTile != null && attackTile.getMob() != null) { //equation for damage
				Mob defender = attackTile.getMob();
				int damage = this.attack / defender.getArmor() + 1;
				defender.setHealth(defender.getHealth() - damage);
				if(defender.getHealth()>0) {
					defender.counterAttack(this);
				}
				else{
					attackTile.setMob(null);
					attackTile.setWalkable(true);
				}
			}
		}
		
		
		private void counterAttack(Mob mob) {
			int damage = this.attack / mob.getArmor() + 1;
			mob.setHealth(mob.getHealth() - damage);
		}
		public void move() {
			// Fix Me maybe?
		}
		
}
