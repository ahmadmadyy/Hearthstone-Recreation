package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class PickHero extends JFrame {
	private JPanel pick1;
	private JPanel pick2;
	public PickHero(){
		super();
		setSize(800,600);
		setVisible(true);
		pick1= new JPanel();
		pick1.setLayout(new GridLayout(0,5));
		pick2= new JPanel();
		pick2.setLayout(new GridLayout(0,5));
		add(pick1,BorderLayout.NORTH);
		add(pick2,BorderLayout.SOUTH);
		
	}
	public JPanel getPick1() {
		return pick1;
	}
	public void setPick1(JPanel pick1) {
		this.pick1 = pick1;
	}
	public JPanel getPick2() {
		return pick2;
	}
	public void setPick2(JPanel pick2) {
		this.pick2 = pick2;
	}

}
