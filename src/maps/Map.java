package maps;

import java.util.ArrayList;

import mobs.*;

public class Map {
		private Picture background;
		private soundtrack bgMusic;
		private ArrayList<Monster> monsters;
		private ArrayList<People> people;
		private Tile[][] grid;
		
		public Map() {
			this.bgMusic = null;
			this.background = null;
			this.monsters  = new ArrayList<Monster>();
			this.people = new ArrayList<People>();
			this.grid = new Tile[800][450];
		}
		
		public Tile getTile(int x, int y) {return this.grid[x][y];}
}
