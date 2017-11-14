package maps;

import java.awt.Graphics;
import java.awt.Graphics2D;

import gui.PlayScreen;
import mobs.*;

public class Tile {
	private Mob mob;
	private int width,height;
	public Tile() {
		this.mob = null;
	}
	
	public Tile(Mob m) {
		this.mob = m;
	}
	public void draw(Graphics g,int x, int y,PlayScreen ps) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawRect(x*width, y*height, width, height);
		if(mob!=null) {
			g2d.drawImage(mob.getImage(), x, y, ps);
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
}
