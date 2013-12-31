package com.markp3rry.intheirhallsofstone;

import javax.swing.JFrame;

public class Start extends JFrame {
	public Start(){
		add(new Board());
		setTitle("In Their Halls Of Stone");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args){
		new Start();
	}
}