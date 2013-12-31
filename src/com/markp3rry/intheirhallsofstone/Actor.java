package com.markp3rry.intheirhallsofstone;

import java.util.*;
import java.util.logging.*;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Actor {

	private Image image;
	private int x;
	private int y;
	private String type;
	private int trigger;

	private static Logger logger;

	public Actor(String typeOfActor, int startX, int startY) {

		this.setImage(typeOfActor);
		x = startX;
		y = startY;

	}

	// public void doSomething(Actor north, Actor south, Actor east, Actor west) {

	// 	logger = Logger.getLogger("MyLog");
	// 	List<Decision> decisions = new ArrayList<Decision>(4);
	// 	Decision decision1 = new Decision(north.getTheType());
	// 	Decision decision2 = new Decision(south.getTheType());
	// 	Decision decision3 = new Decision(east.getTheType());
	// 	Decision decision4 = new Decision(west.getTheType());
	// 	decisions.add(decision1);
	// 	decisions.add(decision2);
	// 	decisions.add(decision3);
	// 	decisions.add(decision4);
	// 	Collections.sort(decisions, Comparer);
	// 	logger.info(String.valueOf(decisions.get(0).getScore()));
	// }

	public ArrayList<Slot> doSomething(ArrayList<Slot> slotsToUpdate){
		Actor present = slotsToUpdate.get(0).getActor();
		Actor future = slotsToUpdate.get(1).getActor();
		future.setImage("dwarf");
		present.setImage("empty");
		return slotsToUpdate;
	}

	private static Comparator<Decision> Comparer = new Comparator<Decision>(){
		public int compare(Decision d1, Decision d2){
			return d2.getScore() - d1.getScore();
		}
	};

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage(){
		return image;
	}

	public String getTheType(){
		return type;
	}

	public void setImage(String typeOfActor){
		ImageIcon ii;
		if (typeOfActor == "dwarf"){
			ii = new ImageIcon("img/dwarf.png");	
			this.type = "dwarf";
			this.trigger = 100;
		}
		else if(typeOfActor == "solidrock"){
			ii = new ImageIcon("img/solidrock.png");
			this.type = "solidrock";
			this.trigger = 0;
		}
		else if(typeOfActor == "earth"){
			ii = new ImageIcon("img/earth.png");
			this.type = "earth";
			this.trigger = 0;
		}
		else if(typeOfActor == "empty"){
			ii = new ImageIcon("img/empty.png");
			this.type = "empty";
			this.trigger = 0;
		}
		else if (typeOfActor == "foundation"){
			ii = new ImageIcon("img/foundation.png");
			this.type = "foundation";
			this.trigger = 0;
		}
		else{
			ii = null;
			this.type = "";
			this.trigger = 0;
		}
		
		this.image = ii.getImage();	
	}

	public void moveNorth(){
		y = y - 8;
	}

	public void moveSouth(){
		y = y + 8;
	}

	public void moveEast(){
		x = x + 8;
	}

	public void moveWest(){
		x = x - 8;
	}



}

class Decision{
	String description;
	int score;

	public Decision(String desc){
		//this.description = desc;
		//this.score = sc;
		if (desc == "solidrock"){
			this.score = 0;
		}
		else if (desc == "earth"){
			this.score = 1;
		}
	}

	public String getDescription(){
		return description;
	}

	public int getScore(){
		return score;
	}
}