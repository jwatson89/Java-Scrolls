package mobs;

import items.*;
import maps.Map;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import gui.*;

public class Player extends Mob {
		transient private PlayScreen ps;
		private Timer t1;
		transient private BufferedImage pic;
		transient private BufferedImage stand;
		transient private BufferedImage[][] walks;
		private int x,y,dx,dy,n,width,height;
		private String name;
		private int carryWeight;
		private int exp,maxExp;
		private Armor armorEquipped;
		private Weapon weaponEquipped;
		private ArrayList<Item> inventory;
		transient private PlayerMover playermove;
		private ArrayList<Integer> skills;
		private ArrayList<Integer> stats;
		public boolean moving;
		public Player(PlayScreen ps) {
			this();
			callAfterLoad(ps);
		}
		public Player() {
			this(25,0,1,0,0,5,"me",0,0,0);
		}
		public Player(int health, int speed, int armor, int locx, int locy, int attack, String name, int exp,
				 int gold,int cw) {
			super(health, speed, armor, locx, locy, attack);
			walks=new BufferedImage[4][15];
			this.name = name;
			this.exp = exp;
			maxExp=exp;
			this.inventory = new ArrayList<Item>();
			this.setGold(gold);
			this.skills = new ArrayList<Integer>();
			this.stats = new ArrayList<Integer>();
			this.carryWeight=cw;
			this.armorEquipped=null;
			setX(0);setY(0);
			//ImageIcon ii= new ImageIcon(this.getClass().getResource("/ppl/IMC/idle/crusader_idle_00000.png"));
			try {
				stand = (BufferedImage) ImageIO.read(this.getClass().getResourceAsStream("/ppl/IMC/idle/crusader_idle_00000.png"));
				//ImageIO.write(stand.getSubimage(65, 20, 170, 170), "png", new File("./resources/ppl/IMC/idle/crusader_idle_00000.png"));
				initwalk();
				pic=stand;
				//ImageIO.write(pic, "png", new File("./pic1.png"));
			} catch (IOException e) {
				System.out.println("HELP1");
				e.printStackTrace();
			}
			initInventory();
		}
		private void initInventory() {
			Weapon w1 = new Weapon(5,"small hammer",2,10);
			try {
				w1.setPic(ImageIO.read(this.getClass().getResourceAsStream("../weapons/smallHammer.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.addInventoryItem(w1);
			Weapon w2 = new Weapon(10,"big hammer",4,20);
			try {
				w2.setPic(ImageIO.read(this.getClass().getResourceAsStream("../weapons/bigHammer.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.addInventoryItem(w2);
			Weapon w3 = new Weapon(7,"small sword",3,15);
			try {
				w3.setPic(ImageIO.read(this.getClass().getResourceAsStream("../weapons/sword.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.addInventoryItem(w3);
		}
		public void initwalk() { //commented line were used for one time scaling
			try {
			for(int j=0;j<4;j++) {
				for(int i=0;i<10;i++) {
					walks[j][i]=(BufferedImage) ImageIO.read(this.getClass().getResourceAsStream("../ppl/IMC/walk/crusader_walk_"+j*2+"000"+i+".png"));//(new ImageIcon(this.getClass().getResource("/ppl/IMC/walk/crusader_walk_"+j*2+"000"+i+".png"))).getImage();
//					BufferedImage temp = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR_PRE);
//					Graphics2D g =temp.createGraphics();
//					g.drawImage(walks[j][i], 0, 0, 32, 32, null);
//					ImageIO.write(temp, "png", new File("./resources/ppl/IMC/walk/crusader_walk_"+j*2+"000"+i+".png"));
				}
				for(int i=10;i<15;i++) {
					
						walks[j][i]=(BufferedImage) ImageIO.read(this.getClass().getResourceAsStream("../ppl/IMC/walk/crusader_walk_"+j*2+"00"+i+".png"));
//						BufferedImage temp = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR_PRE);
//						Graphics2D g =temp.createGraphics();
//						g.drawImage(walks[j][i], 0, 0, 32, 32, null);
//						ImageIO.write(temp, "png", new File("./resources/ppl/IMC/walk/crusader_walk_"+j*2+"00"+i+".png"));
					//(new ImageIcon(this.getClass().getResource("/ppl/IMC/walk/crusader_walk_"+j*2+"00"+i+".png"))).getImage();
				}
			}
			} catch (IOException e) {
				System.out.println("HELP");
				e.printStackTrace();
			}
		}
		public void scale(int width, int height) {
//			this.width=width;
//			this.height=height;
//			for(int i=0;i<4;i++) {
//				for(int j=0;j<15;j++) {
//					walks[i][j]= (BufferedImage) walks[i][j].getScaledInstance(width, height, 1);
//				}
//			}
//			stand=(BufferedImage) stand.getScaledInstance(width, height, 1);
//			pic=(BufferedImage) pic.getScaledInstance(width, height, 1);
		}
		public ArrayList<Item> getInventory() {
			return this.inventory;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getExp() {
			return exp;
		}
		public void setExp(int exp) {
			this.exp = exp;
		}
		public void addInventoryItem(Item item) {
			this.inventory.add(item);
		}
		public void setInventory(ArrayList<Item> inventory) {
			this.inventory = inventory;
		}
		public void removeItem(Item item) {
			inventory.remove(item);
		}
		public void increaseSkill(int x) {
			skills.set(x, skills.get(x)+1);
		}
		public int getSkill(int x) {
			return skills.get(x);
		}
		public void levelUp() {
			setMaxHealth(getMaxHealth()+10);
			setHealth(getMaxHealth());
			setExp(0);
			setMaxExp(getMaxExp()+15);
		}
		private void setMaxExp(int i) {
			maxExp=i;
		}
		public int getMaxExp() {
			return maxExp;
		}
		public int getCarryWeight() {
			return carryWeight;
		}
		public void setCarryWeight(int carryWeight) {
			this.carryWeight = carryWeight;
		}
		public Armor getArmorEquipped() {
			return armorEquipped;
		}
		public void setArmorEquipped(Armor armorEquipped) {
			this.setArmor(armorEquipped.getArmor());
			this.armorEquipped = armorEquipped;
		}
		public Item getWeaponEquipped() {
			return weaponEquipped;
		}
		public void setWeaponEquipped(Weapon weaponEquipped) {
			this.setAttack(weaponEquipped.getAttack());
			this.weaponEquipped = weaponEquipped;
		}
		public void increaseStat(int x) {
			stats.set(x, stats.get(x)+1);
		}
		public void setN(int n) {
			this.n=n;
		}
		public int getDX() {
			return dx;
		}
		public void setDX(int x) {
			this.dx = x;
		}
		public int getDY() {
			return dy;
		}
		public void setDY(int y) {
			this.dy = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public BufferedImage getImage() {
			return pic;
		}
		public void moveTile(int tileSize,int dir, Map m1) {
			boolean valid=false;
			if(n==0) {
				if(x==0 && dir == 0) {
					
				}
				else if(y==0 && dir == 2) {
					
				}
				else if(x/32==59 && dir == 1) {
					
				}
				else if(y/32 ==29 && dir == 3) {
					
				}
				else {
					switch (dir) {
						case 0:
							valid = m1.getTile(x/32 - 1, y/32).isWalkable();
							break;
						case 1:
							valid = m1.getTile(x/32 + 1, y/32).isWalkable();
							break;
						case 2:
							valid = m1.getTile(x/32, y/32 - 1).isWalkable();
							break;
						case 3:
							valid = m1.getTile(x/32, y/32 + 1).isWalkable();
							break;					
					}
					if(valid) {
						t1.start();
						moving=true;
					}
					else {
						if(dir==3) {
							pic=walks[0][0];	
						}
						else if(dir==0) {
							pic=walks[3][0];	
						}
						else {
							pic=walks[dir][0];	
						}
							ps.repaint();
					}
					this.direction=dir;
				}
			}			
		}
		private class PlayerMover implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(n==15) {
					t1.stop();
					//n=15;
					move();
					setDX(0);
					setDY(0);
					moving=false;
					setLocx(x/32);
					setLocy(y/32);
				}
				else if(direction==0) {
					setDX(-1);
					move();
				}
				else if(direction ==1) {
					setDX(1);
					move();
				}
				else if(direction ==2) {
					setDY(-1);
					move();					
				}
				else if(direction ==3) {
					setDY(1);
					move();					
				}
				boundsCheck(ps.getWidth(),ps.getHeight());
				ps.repaint();//x-(getSpeed()*dx), y-(getSpeed()*dy), width+(getSpeed()*dx), height+(getSpeed()*dy));
			}
			
		}
		public void move() {
			if(n==15) {
				n=14;
			}
			if(dx==-1) {
				pic=walks[3][n];
				n++;
			}
			else if(dx==1) {
				pic=walks[1][n];
				n++;
			}
			else if(dy==-1) {
				pic=walks[2][n];
				n++;
			}
			else if(dy==1) {
				pic=walks[0][n];
				n++;
			}
			else {
				pic=stand;
				n=0;
			}
			if(dx!=0) {
				x+=(2*dx);
			}
			else {
				y+=(2*dy);
			}
		}
		public void boundsCheck(int width2, int height2) {
			if(x<0) {
				x=0;
			}
			else if(x>width2-width) {
				x=width2-width;
			}
			if(y>height2-height) {
				y=height2-height;
			}
			else if(y<0) {
				y=0;
			}
			
		}
		private void writeObject(ObjectOutputStream out) throws IOException {
		    out.defaultWriteObject();
		    for(int i=0; i<4;i++) {
			    out.writeInt(walks[i].length); // how many images are serialized?
	
			    for (BufferedImage eachImage : walks[i]) {
			        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			        ImageIO.write(eachImage, "png", buffer);
	
			        out.writeInt(buffer.size()); // Prepend image with byte count
			        buffer.writeTo(out);         // Write image
			    }
		    }
		    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	        ImageIO.write(stand, "png", buffer);

	        out.writeInt(buffer.size()); // Prepend image with byte count
	        buffer.writeTo(out);         // Write image
	        buffer = new ByteArrayOutputStream();
	        ImageIO.write(pic, "png", buffer);
	    	
	        out.writeInt(buffer.size()); // Prepend image with byte count
	        buffer.writeTo(out);         // Write image
		}
		private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		    in.defaultReadObject();
		    walks=new BufferedImage[4][];
		    for(int j=0;j<4;j++) {
		    int imageCount = in.readInt();
			    walks[j] = new BufferedImage[imageCount];
			    for (int i = 0; i < imageCount; i++) {
			        int size = in.readInt(); // Read byte count
	
			        byte[] buffer = new byte[size];
			        in.readFully(buffer); // Make sure you read all bytes of the image
	
			        walks[j][i]=(ImageIO.read(new ByteArrayInputStream(buffer)));
			    }
		    }
		    int size = in.readInt(); // Read byte count
			
	        byte[] buffer = new byte[size];
	        in.readFully(buffer); // Make sure you read all bytes of the image

	        stand=(ImageIO.read(new ByteArrayInputStream(buffer)));
	        size = in.readInt(); // Read byte count
	    	
	        buffer = new byte[size];
	        in.readFully(buffer); // Make sure you read all bytes of the image

	        pic=(ImageIO.read(new ByteArrayInputStream(buffer)));
		}
		public void callAfterLoad(PlayScreen ps) {
			this.ps=ps;
			playermove= new PlayerMover();
			t1=new Timer(10, playermove);
		}
}
