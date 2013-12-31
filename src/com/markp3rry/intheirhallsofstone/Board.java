package com.markp3rry.intheirhallsofstone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;
import java.io.*;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private Timer timer;
	private Actor actor;
	//private Slot slot;

	private ArrayList<Slot> slots;
	//private ArrayList<Actor> list;

	private static Logger logger;

	//endX and endY need to be odd numbers!
	int startX = 0;
	int endX = 19;
	int startY = 0;
	int endY = 19;

	public Board() {

		logger = Logger.getLogger("MyLog");
		logger.info("Started In Their Halls Of Stone!");

		slots = new ArrayList<Slot>();
		int count = 0;
		for (int j = startY; j < endY; j++){
			//logger.info(String.valueOf(j));
			for (int i = startX; i < endX; i++){
				//logger.info(String.valueOf(i));
				Slot slot = new Slot(count);
				if ((i == startX || j == startY) || (i == endX - 1 || j == endY - 1)){
					actor = new Actor("solidrock", i * 8, j * 8);
					slot.setActor(actor);
				}
				else{
					actor = new Actor("earth", i * 8, j * 8);
					slot.setActor(actor);	
				}
				//logger.info(String.valueOf(slot.getNumber()));
				slots.add(slot);
				count = count + 1;
			}
		}
		Actor b = slots.get(24).getActor();
		b.setImage("dwarf");

		setFocusable(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		
		timer = new Timer(1000, this);
		timer.start();

	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D)g;

		for(Object object: slots){
		 	Slot s = (Slot)object;
		 		if (s.getActor() != null){
		 			Actor a = s.getActor();
		 			g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);		
		 		}
		}
			

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void actionPerformed(ActionEvent e){
		ArrayList<Change> indexOfChanges = new ArrayList<Change>();
		 for(Object object: slots){
		 	Slot s = (Slot) object;
		 	Actor a = s.getActor();
		 	if (a.getTheType() == "dwarf"){
		 		//find out the type of actor that's directly north, south, east and west
		 		//east and west should be easy - it's the index of the slot + / - 1
		 		Slot slotEast = slots.get(s.getNumber() + 1);
		 		Slot slotWest = slots.get(s.getNumber() - 1);
		 		//logger.info(String.valueOf(slotEast.getNumber()));
		 		//logger.info(String.valueOf(slotWest.getNumber()));
		 		//north and south are a little more complicated - need to subtract a full row length from current position
		 		Slot slotNorth = slots.get(s.getNumber() - endX);
		 		Slot slotSouth = slots.get(s.getNumber() + endX);
		 		//logger.info(String.valueOf(slotNorth.getNumber()));
		 		//logger.info(String.valueOf(slotSouth.getNumber()));

		 		Change change = new Change(s.getNumber(), "empty");
		 		indexOfChanges.add(change);
		 		Change change1 = new Change(slotEast.getNumber(), "dwarf");
		 		indexOfChanges.add(change1);
		 		Change change2 = new Change(slotSouth.getNumber(), "foundation");
		 		indexOfChanges.add(change2);
		 	}
		 }
		
		for (Object object : indexOfChanges){
			Change change = (Change)object;
			Actor a = slots.get(change.getIndex()).getActor();
			a.setImage(change.getTheType());
			// if (change.getTheType() == "dwarf"){
			// 	a.setImage("dwarf");
			// }
			// else if (change.getTheType() == "empty"){
			// 	a.setImage("empty");
			// }

		}
		repaint();
	}
}

class Change{
	private int index;
	private String type;
	public Change(int i, String t){
		index = i;
		type = t;
	}
	public int getIndex(){
		return this.index;
	}
	public String getTheType(){
		return this.type;
	}
}
