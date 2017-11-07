package items;

public abstract class Item {
		private Icon pic;
		private String name;
		private int weight;
		private int value;
		
		public Item(String name, int weight, int value) {
			this.name=name;
			this.weight=weight;
			this.value=value;
		}
		public Item() {
			this("",0,0);
		}
}
