package maps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import gui.PlayScreen;
import items.Item;
import mobs.*;

public class Tile implements Serializable{
	private Mob mob;
	private Item item;
	private int width,height;
	private boolean walkable;
	transient private BufferedImage pic;
	public Tile() {
		this.mob = null;
		this.item = null;
		this.setPic(null);
		this.walkable=true;
	}
	
	public Tile(Mob m) {
		this.mob = m;
	}
	public void draw(Graphics g,int x, int y,PlayScreen ps) {
		Graphics2D g2d = (Graphics2D)g;
		//g2d.drawRect(x*width, y*height, width, height);
		if(pic!=null) {
			g2d.drawImage(pic, x*width, y*height, ps);
		}
		if(mob!=null) {
			g2d.drawImage(mob.getImage(), x*width, y*height, ps);
		}
	}
	
	public Mob getMob() {return this.mob;}
	public void setMob(Mob m) {this.mob = m;}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BufferedImage getPic() {
		return pic;
	}

	public void setPic(BufferedImage pic) {
		this.pic = pic;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
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
