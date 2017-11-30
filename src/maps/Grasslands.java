package maps;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import javax.imageio.ImageIO;
import gui.*;
import mobs.Mob;

public class Grasslands extends Map implements Serializable{
	Random rand;
	public Grasslands(int dificulty) {// 1-5
		super();

		 rand = new Random();
		try {
			this.setBG((BufferedImage)ImageIO.read(this.getClass().getResourceAsStream("../rpgTown/tileset/grass-tile-big.png")));
//			BufferedImage tmp=new BufferedImage(1920,960, BufferedImage.TYPE_INT_RGB);
//			Graphics2D g2 = tmp.createGraphics();
//			for(int i=0;i<20;i++) {
//				for(int j=0;j<40;j++) {
//					g2.drawImage(this.getBG(), j*48, i*48, 48, 48, null);
//				}
//			}
//			ImageIO.write(tmp, "png", new File("./resources/rpgTown/tileset/grass-tile-big.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		fillMap();
		addBadGuys(dificulty);
	}
	private void fillMap() {
		//add stuff to populate landscape... use the tile.setPic() for landscape objects that
		//you can walk on but not interact with, or set tile.setWalkable(false) for landscape you can't walk on
		// use the tile.setItem() for objects (weapons, armor, etc..) that you can walk on and interact with.
		BufferedImage full = null;
		try {
			 full = (BufferedImage) ImageIO.read(this.getClass().getResourceAsStream("../rpgmakerAssets/tf_jungle_tileset.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage flowers[] = new BufferedImage[24];
		for(int i=0;i<8;i++) {
			for(int j=3;j<6;j++) {
				BufferedImage tmp = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2 = tmp.createGraphics();
				g2.drawImage(full.getSubimage(i*16, j*16, 16, 16), 0, 0, 16, 16, null);				
				flowers[(j-3)*8+i]=tmp;				
				g2.dispose();
			}
		}
		BufferedImage tree = new BufferedImage(80, 96, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = tree.createGraphics();
		g2.drawImage(full.getSubimage(240, 80, 80, 96), 0, 0, 80, 96, null);
		g2.dispose();
		createCluster(tree,3,3,49);
		addFlowers(flowers,200);
	}
	private void addFlowers(BufferedImage[] flowers, int num) {
		for(int i=0;i<num;i++) {
			int  x = rand.nextInt(58)+1;
			int  y = rand.nextInt(28)+1;
			while(getTile(x,y).getPic()!=null) {
				x = rand.nextInt(58)+1;
				y = rand.nextInt(28)+1;
			}
			getTile(x,y).setPic(flowers[rand.nextInt(flowers.length)]);	
			
		}
		
	}
	public void createCluster(BufferedImage pic, int width, int height, int clusterSize) {
		int  x = rand.nextInt(58)+1;
		int  y = rand.nextInt(28)+1;
		double sqrt = Math.sqrt(clusterSize);
		if(x > 58-(width+sqrt)) { 
			x= (int)(59-(width+sqrt));
		}
		if(y > 29-(height+sqrt)) {
			y= (int)(29-(height+sqrt));
		}
		for(int i=0;i<sqrt;i++) {
			for(int j=0;j<sqrt;j++) {
				getTile(x+i,y+j).setPic(pic);
			}
		}
	}
	public void addBadGuys(int hard) {
		for(int i=0;i<5*hard;i++) {
			int  x = rand.nextInt(58)+1;
			int  y = rand.nextInt(28)+1;
			while(getTile(x,y).getMob()!=null) {
				x = rand.nextInt(58)+1;
				y = rand.nextInt(28)+1;
			}
			Tile tmp=getTile(x, y);
			tmp.setMob(randomMob(hard,x,y));
			tmp.setWalkable(false);
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
			m.setGold(5);
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
