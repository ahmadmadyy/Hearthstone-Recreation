package view;
import javax.swing.*;

import engine.Game;
import model.heroes.Hero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Hearthstone extends JFrame {
	private JPanel hero1Hand;
	private JPanel hero2Hand;
	private JPanel field;
	private JPanel hero1Field;
	private JPanel hero2Field;

	public Hearthstone(Hero h1,Hero h2) {
		super(); 
		setSize(800,800);
		setTitle("Hearthstone");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		hero1Hand = new JPanel();
		hero1Hand.setLayout(new GridLayout(0,14));
		hero1Hand.setPreferredSize(new Dimension(getWidth(),200));
//		hero1Hand.setBackground(Color.BLUE); 
		add(hero1Hand,BorderLayout.SOUTH);
		hero2Hand = new JPanel();
		hero2Hand.setLayout(new GridLayout(0,14));
		hero2Hand.setPreferredSize(new Dimension(getWidth(),200));
//		hero2Hand.setBackground(Color.RED);
		add(hero2Hand,BorderLayout.NORTH);
		field = new JPanel();
		field.setLayout(new GridLayout(2,0));
		field.setPreferredSize(new Dimension(getWidth(),400));
		add(field,BorderLayout.CENTER);
		hero2Field = new JPanel();
		hero2Field.setLayout(new GridLayout(0,9));
		hero2Field.setPreferredSize(new Dimension(getWidth(),200));
//		hero2Field.setBackground(Color.YELLOW);
		field.add(hero2Field);
		hero1Field = new JPanel();
		hero1Field.setLayout(new GridLayout(0,9));
		hero1Field.setPreferredSize(new Dimension(getWidth(),200));
//		hero1Field.setBackground(Color.BLACK);
		field.add(hero1Field);
//		details1= new JTextArea();
//		details2= new JTextArea();
//		details1.setEditable(false);
//		details2.setEditable(false);
//		
//		details1= new JTextArea("Name: "+ h1.getName()+"\nCurrent HP  "+h1.getCurrentHP()
//				+"\nTotal Mana Crystals  "+h1.getTotalManaCrystals()+
//				"\nCurrent Mana Crystals   "+h1.getCurrentManaCrystals()+"\n Cards in deck  "+h1.getDeck().size());
//		details2= new JTextArea("Name: "+h2.getName()+"\nCurrent HP  "+h2.getCurrentHP()
//				+"\nTotal Mana Crystals  "+h2.getTotalManaCrystals()+
//				"\nCurrent Mana Crystals  "+h2.getCurrentManaCrystals()+"\nCards in Deck  "+h2.getDeck().size());
//		hero1Hand.add(details1);
//		hero2Hand.add(details2);
		this.revalidate();
		this.repaint();
		
		} 
//	public JTextArea getDetails1() {
//		return details1;
//	}
//	public void setDetails1(JTextArea details1) {
//		this.details1 = details1;
//	}
//	public JTextArea getDetails2() {
//		return details2;
//	}
//	public void setDetails2(JTextArea details2) {
//		this.details2 = details2;
//	}
	public JPanel getHero1Hand() {
		return hero1Hand;
	}
	public void setHero1Hand(JPanel hero1Hand) {
		this.hero1Hand = hero1Hand;
	}
	public JPanel getHero2Hand() {
		return hero2Hand;
	}
	public void setHero2Hand(JPanel hero2Hand) {
		this.hero2Hand = hero2Hand;
	}
	public JPanel getField() {
		return field;
	}
	public void setField(JPanel field) {
		this.field = field;
	}
	public JPanel getHero1Field() {
		return hero1Field;
	}
	public void setHero1Field(JPanel hero1Field) {
		this.hero1Field = hero1Field;
	}
	public JPanel getHero2Field() {
		return hero2Field;
	}
	public void setHero2Field(JPanel hero2Field) {
		this.hero2Field = hero2Field;
	}
	public static void main (String[] args){
		//Hearthstone h=new Hearthstone();
	}
	} 


