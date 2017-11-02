package mobs;

public class Mob {
		private Icon pic;
		private int health;
		private int speed;
		private int armor;
		private int locx;
		private int locy;
		private int attack;
		
		public Mob(int health, int speed, int armor, int locx, int locy, int attack) {
			this.health = health;
			this.speed = speed;
			this.armor = armor;
			this.locx = locx;
			this.locy = locy;
			this.attack = attack;
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
		public void attack(Mob attacker) {
			//FIX ME
		}
		public void move() {
			// Fix Me
		}
		
}
