package maps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import gui.*;
import mobs.*;

public class Map implements Serializable{
	private static int tilesH=30, tilesW=60;
		transient private BufferedImage background;
		//private soundtrack bgMusic;
		//private ArrayList<Monster> monsters; // do we even need these?
		//private ArrayList<People> people;
		private Tile[][] grid;
		public void setBG(BufferedImage bufferedImage) {
			background=bufferedImage;
		}
		public Map() {
			//this.bgMusic = null;
			this.background = null;
			//this.monsters  = new ArrayList<Monster>();
			//this.people = new ArrayList<People>();
			this.grid = new Tile[tilesH][tilesW];
			tileInit();
		}
		public void tileInit() {
			for(int i=0; i<tilesH;i++) {
				for(int j=0;j<tilesW;j++) {
					grid[i][j]=new Tile();
					grid[i][j].setHeight(32);
					grid[i][j].setWidth(32);
				}
			}
		}
		public BufferedImage getBG() {
			return this.background;
		}
		public void draw(Graphics g,PlayScreen ps) {
			if(background !=null) {
				Graphics2D g2=(Graphics2D)g;
				g2.drawImage(background,0,0,ps.getWidth(),ps.getHeight(),ps);
			}
				for(int i=0; i<tilesH;i++) {
					for(int j=0;j<tilesW;j++) {
						grid[i][j].draw(g, j, i, ps);
					}
				}
			
		}
		public Tile getTile(int x, int y) {return this.grid[y][x];}
		public int getTileSize() {
			return grid[0][0].getHeight();
		}
		private void writeObject(ObjectOutputStream out) throws IOException {
		    out.defaultWriteObject();
		    
		        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		        ImageIO.write((RenderedImage) background, "png", buffer);

		        out.writeInt(buffer.size()); // Prepend image with byte count
		        buffer.writeTo(out);         // Write image
		}

		private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		    in.defaultReadObject();

		    
		        int size = in.readInt(); // Read byte count

		        byte[] buffer = new byte[size];
		        in.readFully(buffer); // Make sure you read all bytes of the image

		       background=(ImageIO.read(new ByteArrayInputStream(buffer)));
		    
		}
}
