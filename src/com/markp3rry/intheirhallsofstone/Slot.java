package com.markp3rry.intheirhallsofstone;

public class Slot {
	private int number;
	private Actor actor;

	public Slot(int num){
		this.number = num;
	}

	public Actor getActor(){
		return this.actor;
	}

	public int getNumber(){
		return this.number;
	}

	public void setActor(Actor actor){
		this.actor = actor;
	}


}