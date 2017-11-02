package items;

public class Weapon extends Item {
		private int attack;
		
		public Weapon(int attack,String name, int weight, int value) {
			super(name,weight,value);
			this.attack=attack;
		}
		public Weapon() {
			super("",0,0);
			this.attack=0;
		}
}
