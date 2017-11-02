package items;

public class Armor extends Item {
		private int armor;
		
		public Armor(int armor,String name, int weight, int value) {
			super(name,weight,value);
			this.armor=armor;
		}
		public Armor() {
			super("",0,0);
			this.armor=0;
		}
}
