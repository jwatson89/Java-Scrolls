package maps;

import mobs.*;

public class Tile {
	private Mob mob;
	
	public Tile() {
		this.mob = null;
	}
	
	public Tile(Mob m) {
		this.mob = m;
	}
	
	public Mob getMob() {return this.mob;}
	public void setMob(Mob m) {this.mob = m;}
}
