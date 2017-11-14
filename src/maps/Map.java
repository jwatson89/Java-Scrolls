package maps;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import gui.PlayScreen;
import mobs.*;

public class Map {
	private static int tilesH=15, tilesW=25;
		private Image background;
		//private soundtrack bgMusic;
		private ArrayList<Monster> monsters;
		private ArrayList<People> people;
		private Tile[][] grid;
		
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
					grid[i][j].setHeight(50);
					grid[i][j].setWidth(50);
				}
			}
		}
		public void draw(Graphics g,PlayScreen ps) {
			for(int i=0; i<tilesH;i++) {
				for(int j=0;j<tilesW;j++) {
					grid[i][j].draw(g, j, i, ps);
				}
			}
		}
		public Tile getTile(int x, int y) {return this.grid[x][y];}
		public int getTileSize() {
			return grid[0][0].getHeight();
		}
}
