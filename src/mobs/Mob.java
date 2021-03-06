package mobs;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import maps.*;

public class Mob implements Serializable{
		//private Icon pic;
		private int health,maxHealth;
		private int speed;
		private int armor;
		private int gold;
		private int locx;
		private int locy;
		private int attack;
		protected int direction;
		transient private BufferedImage mobImage;
		
		
		public Mob(int health, int speed, int armor, int locx, int locy, int attack) {
			this.health = health;
			this.speed = speed;
			this.armor = armor;
			this.locx = locx;
			this.locy = locy;
			this.attack = attack;
			maxHealth=health;
			gold=0;
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
			try {
				this.mobImage = (ImageIO.read(this.getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void addGold(int g) {
			gold+=g;
		}
		public int getGold() {
			return gold;
		}
		public void setGold(int gold) {
			this.gold = gold;
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
					if(this.getClass().getName().equals("mobs.Player")) {
						this.addGold(defender.getGold());
					}
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
		private void writeObject(ObjectOutputStream out) throws IOException {
		    out.defaultWriteObject();
		    
		        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		        if(mobImage!=null) {
		          	ImageIO.write(mobImage, "png", buffer);
		        }
		        out.writeInt(buffer.size()); // Prepend image with byte count
		        buffer.writeTo(out);         // Write image
		}

		private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		    in.defaultReadObject();

		    
		        int size = in.readInt(); // Read byte count
		        if(size != 0) {
			        byte[] buffer = new byte[size];
			        in.readFully(buffer); // Make sure you read all bytes of the image
	
			        mobImage=(ImageIO.read(new ByteArrayInputStream(buffer)));
		        }
		}
		public int getMaxHealth() {
			return maxHealth;
		}
		public void setMaxHealth(int maxHealth) {
			this.maxHealth = maxHealth;
		}
}
