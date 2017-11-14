package items;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Item {
		private ImageIcon pic;
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
		public ImageIcon getPic() {
			return pic;
		}
		public void setPic(ImageIcon pic) {
			this.pic = pic;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
}
