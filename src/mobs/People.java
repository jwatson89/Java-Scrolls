package mobs;

import java.util.ArrayList;

public class People extends Mob {
		private ArrayList<String> dialogue;
		private boolean isGuard;
		
		public People() {
			dialogue=new ArrayList<String>();
			isGuard=false;
		}
}
