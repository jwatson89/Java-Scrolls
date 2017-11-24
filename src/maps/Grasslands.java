package maps;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import mobs.Mob;

public class Grasslands extends Map {
	Random rand;
	public Grasslands(int dificulty) {// 1-5
		super();

		 rand = new Random();
		try {
			this.setBG(ImageIO.read(this.getClass().getResourceAsStream("../skyrimmap.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addBadGuys(dificulty);
	}
	public void addBadGuys(int hard) {
		for(int i=0;i<5*hard;i++) {
			int  x = rand.nextInt(59)+1;
			int  y = rand.nextInt(29)+1;
			while(getTile(x,y).getMob()!=null) {
				x = rand.nextInt(28)+1;
				y = rand.nextInt(58)+1;
			}
			getTile(x, y).setMob(randomMob(hard,x,y));
		}
	}
	private Mob randomMob(int hard,int x, int y) {
		Mob m = new Mob();
		switch (0) {//rand.nextInt(5)){ //Uncomment this to get randomized values, using 0 for testing 
		case 0:
			//super easy mob// maybe loot
			m.setImage("../ppl/rogue.png");
			m.setHealth(10);
			m.setArmor(1);
			m.setAttack(2);
			m.setLocx(x);
			m.setLocy(y);
			break;
		case 1:
			// easy mob
			
			break;
		case 2:
			//normal
			
			break;
		case 3:
			// a little harder
			
			break;
		case 4:
			// quite challenging
			
			break;
		}
		return m;
	}

}
