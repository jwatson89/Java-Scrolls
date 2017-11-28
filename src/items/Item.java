package items;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Item implements Serializable{
		private BufferedImage pic;
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
		public BufferedImage getPic() {
			return pic;
		}
		public void setPic(BufferedImage pic) {
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
		private void writeObject(ObjectOutputStream out) throws IOException {
		    out.defaultWriteObject();
		    	
		        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		        if(pic!=null) {
		        	ImageIO.write(pic, "png", buffer);
		        }
		        out.writeInt(buffer.size()); // Prepend image with byte count
		        buffer.writeTo(out);         // Write image
		    
		}

		private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		    in.defaultReadObject();
		        int size = in.readInt(); // Read byte count
		        if(size!=0) {
			        byte[] buffer = new byte[size];
			        
			        in.readFully(buffer); // Make sure you read all bytes of the image
		
			        setPic(ImageIO.read(new ByteArrayInputStream(buffer)));
		        }
		}
}
