package maps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gui.PlayScreen;
import mobs.*;

public class Map {
	private static int tilesH=30, tilesW=60;
		private BufferedImage background;
		//private soundtrack bgMusic;
		private ArrayList<Monster> monsters;
		private ArrayList<People> people;
		private Tile[][] grid;
		public void setBG(BufferedImage bg) {
			background=bg;
		}
		public Map() {
			//this.bgMusic = null;
			this.background = null;
			this.monsters  = new ArrayList<Monster>();
			this.people = new ArrayList<People>();
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
}
