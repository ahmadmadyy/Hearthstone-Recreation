package engine;


import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import view.Hearthstone;
import view.PickHero;
import view.Winner;

public class GameController implements GameListener,ActionListener{
	private Hearthstone view;
	private PickHero view1;
	private Winner view2;
	private Game model;
	private ArrayList<JButton> hero1Buttons;
	private ArrayList<JButton> hero2Buttons;
	private ArrayList<JButton> hand1;
	private ArrayList<JButton> hand2;
	private ArrayList<JButton> field1;
	private ArrayList<JButton> field2;
	private Hero hero1; 
	private Hero hero2;
	private JButton selectedMinion1;
	private JButton selectedMinion2;
	private JButton selectedSpell1;
	private JButton selectedSpell2;
	private Spell spell1;
	private Spell spell2;
	private JButton attackingMinion1;
	private JButton attackingMinion2;
	private Minion attacker1;
	private Minion attacker2;
	private Minion minion1;
	private Minion minion2;
	private boolean power1;
	private boolean power2;
	private boolean fullHand1;
	private boolean fullHand2;
	
	public GameController(){
		view1=new PickHero();
		hero1Buttons = new ArrayList<JButton>();
		hero2Buttons = new ArrayList<JButton>();
		hand1 = new ArrayList<JButton>();
		hand2 = new ArrayList<JButton>();
		field1 = new ArrayList<JButton>();
		field2 = new ArrayList<JButton>();
		String[] names={"Hunter","Mage","Paladin","Priest","Warlock","hunter","mage","paladin","priest","warlock"};
		for(int i=0;i<5;i++){
			JButton jb = new JButton();
			jb.setText(names[i]);
			jb.addActionListener(this);
			hero1Buttons.add(jb);
			view1.getPick1().add(jb);
		}
		for(int i=5;i<10;i++){
			JButton jb = new JButton();
			jb.setText(names[i]);
			jb.addActionListener(this);
			hero2Buttons.add(jb);
			view1.getPick2().add(jb);
		}
		JButton heroB1=new JButton();
		hand1.add(heroB1);
		heroB1.addActionListener(this);
		JButton heroB2=new JButton();
		hand2.add(heroB2);
		heroB2.addActionListener(this);
		JButton heroPower1 = new JButton("Use Hero Power");
		hand1.add(heroPower1);
		heroPower1.addActionListener(this);
		JButton heroPower2 = new JButton("Use Hero Power");
		hand2.add(heroPower2);
		heroPower2.addActionListener(this);
		JButton endTurn1 = new JButton("End Turn");
		hand1.add(endTurn1);
		endTurn1.addActionListener(this);
		JButton endTurn2 = new JButton("End Turn");
		hand2.add(endTurn2);
		endTurn2.addActionListener(this);
		JButton play1= new JButton ("Add to field");
		field1.add(play1);
		play1.addActionListener(this);
		JButton play2= new JButton ("Add to field");
		field2.add(play2);
		play2.addActionListener(this);
		JButton spellButton1=new JButton("Cast AOE/Field Spell");
		field1.add(spellButton1);
		spellButton1.addActionListener(this);
		JButton spellButton2=new JButton("Cast AOE/Field Spell");
		field2.add(spellButton2);
		spellButton2.addActionListener(this);
		power1=false;
		power2=false;
	}
	
	public void onGameOver() {
		view.setVisible(false);
		view2=new Winner(hero1,hero2);
	}

	
	public static void main (String[] args){
		new GameController();
		
	}

	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)(e.getSource());
		String name = b.getText();
		switch (name){
		case "Hunter": try {
				hero2=new Hunter();
				for(int i=0;i<5;i++){
					hero1Buttons.get(i).setEnabled(false);
					hero1Buttons.get(i).setVisible(false);
				}
				
			} catch (IOException | CloneNotSupportedException e1) {
				
			}break;
		case "hunter": try {
				hero1= new Hunter();
				for(int i=0;i<5;i++){
					hero2Buttons.get(i).setEnabled(false);
					hero2Buttons.get(i).setVisible(false);
				}
				
			} catch (IOException | CloneNotSupportedException e1) {
				
			}break;
		case "Mage": try {
				hero2=new Mage();
				for(int i=0;i<5;i++){
					hero1Buttons.get(i).setEnabled(false);
					hero1Buttons.get(i).setVisible(false);
					}
			} catch (IOException | CloneNotSupportedException e1) {
				
			}break;
		case "mage": try {
			hero1=new Mage();
			for(int i=0;i<5;i++){
				hero2Buttons.get(i).setEnabled(false);
				hero2Buttons.get(i).setVisible(false);
			}
		} catch (IOException | CloneNotSupportedException e1) {
			
		}break;
		case "Paladin": try {
				hero2=new Paladin();
				for(int i=0;i<5;i++){
					hero1Buttons.get(i).setEnabled(false);
					hero1Buttons.get(i).setVisible(false);
					}
			} catch (IOException | CloneNotSupportedException e1) {
				
			}break;
		case "paladin": try {
			hero1=new Paladin();
			for(int i=0;i<5;i++){
				hero2Buttons.get(i).setEnabled(false);
				hero2Buttons.get(i).setVisible(false);
			}
		} catch (IOException | CloneNotSupportedException e1) {
			
		}break;
		case "Priest":try {
				hero2=new Priest();
				for(int i=0;i<5;i++){
					hero1Buttons.get(i).setEnabled(false);
					hero1Buttons.get(i).setVisible(false);
					}
			} catch (IOException | CloneNotSupportedException e1) {
				
			}break;
		case "priest":try {
			hero1=new Priest();
			for(int i=0;i<5;i++){
				hero2Buttons.get(i).setEnabled(false);
				hero2Buttons.get(i).setVisible(false);
			}
		} catch (IOException | CloneNotSupportedException e1) {
			
		}break;
		case "Warlock": try {
				hero2=new Warlock();
				for(int i=0;i<5;i++){
					hero1Buttons.get(i).setEnabled(false);
					hero1Buttons.get(i).setVisible(false);
					}
			} catch (IOException | CloneNotSupportedException e1) {
				
			}break;
		case "warlock": try {
			hero1=new Warlock();
			for(int i=0;i<5;i++){
				hero2Buttons.get(i).setEnabled(false);
				hero2Buttons.get(i).setVisible(false);
			}
		} catch (IOException | CloneNotSupportedException e1) {
	
		}break;
		default:break;
		}
		
		if(b.getActionCommand().equals("Use Hero Power")&&hero1==model.getCurrentHero()){
			if(hero1.getName().equals("Rexxar")||hero1.getName().equals("Uther Lightbringer")||hero1.getName().equals("Gul'dan")){
				try {
					hero1.useHeroPower();
					hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
							+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
							"<html>");
					hand2.get(0).setText("<html>Opponent<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
							+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
							"<br/>Cards in hand:"+hero2.getHand().size()+"<html>");
					int handSize=hand1.size();
					int fieldSize=field1.size();
					for(int i=2;i<fieldSize;i++){
						field1.remove(2);
						view.getHero1Field().remove(2);
					}
					for(int i=3;i<handSize;i++){
						hand1.remove(3);
						view.getHero1Hand().remove(3);
					}
					for(int i=0;i<hero1.getHand().size();i++){
						Card c= hero1.getHand().get(i);
						String n=c.getName();
						int co=c.getManaCost();
						Rarity r=c.getRarity();
						JButton jb=new JButton();
						if ((c) instanceof Minion){
							Minion m = (Minion)(c);
							int hp =m.getCurrentHP();
							int a = m.getAttack();
							String t=(m.isTaunt()?"yes":"no");
							String d=(m.isDivine()?"yes":"no");
							String ch=(m.isSleeping()?"no":"yes");
							jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<br/>HP:"+hp+
									"<br/>Attack:"+a+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Charge: "+ch+"<html>");
						}
						else{
							jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<html>");
							
						}
						jb.addActionListener(this);
						hand1.add(jb);
					}
					for(int i=0;i<hero1.getField().size();i++){
						Minion m=hero1.getField().get(i);
						String t=(m.isTaunt()?"yes":"no");
						String d=(m.isDivine()?"yes":"no");
						String s=(m.isSleeping()?"yes":"no");
						JButton jb = new JButton();
						jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
							+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
							+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
						field1.add(jb);
						jb.addActionListener(this);
					}
					for(int i=2;i<field1.size();i++){
						view.getHero1Field().add(field1.get(i));
					}
					for(int i=3;i<hand1.size();i++){
						view.getHero1Hand().add(hand1.get(i));
					}
					
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{
				power1=true;
			}
			selectedMinion1=null;
			selectedSpell1=null;
		}
		
		if(b.getActionCommand().equals("Use Hero Power")&&hero2==model.getCurrentHero()){
			if(hero2.getName().equals("Rexxar")||hero2.getName().equals("Uther Lightbringer")||hero2.getName().equals("Gul'dan")){
				try {
					hero2.useHeroPower();
					hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
							+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
							"<html>");
					hand1.get(0).setText("<html>Opponent<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
							+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
							"<br/>Cards in hand:"+hero1.getHand().size()+"<html>");
					int handSize=hand2.size();
					int fieldSize=field2.size();
					for(int i=2;i<fieldSize;i++){
						field2.remove(2);
						view.getHero2Field().remove(2);
					}
					for(int i=3;i<handSize;i++){
						hand2.remove(3);
						view.getHero2Hand().remove(3);
					}
					for(int i=0;i<hero2.getHand().size();i++){
						Card c= hero2.getHand().get(i);
						String n=c.getName();
						int co=c.getManaCost();
						Rarity r=c.getRarity();
						JButton jb=new JButton();
						if ((c) instanceof Minion){
							Minion m = (Minion)(c);
							int hp =m.getCurrentHP();
							int a = m.getAttack();
							String t=(m.isTaunt()?"yes":"no");
							String d=(m.isDivine()?"yes":"no");
							String ch=(m.isSleeping()?"no":"yes");
							jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<br/>HP:"+hp+
									"<br/>Attack:"+a+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Charge: "+ch+"<html>");
						}
						else{
							jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<html>");
							
						}
						jb.addActionListener(this);
						hand2.add(jb);
					}
					for(int i=0;i<hero2.getField().size();i++){
						Minion m=hero2.getField().get(i);
						String t=(m.isTaunt()?"yes":"no");
						String d=(m.isDivine()?"yes":"no");
						String s=(m.isSleeping()?"yes":"no");
						JButton jb = new JButton();
						jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
							+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
							+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
						field2.add(jb);
						jb.addActionListener(this);
					}
					for(int i=2;i<field2.size();i++){
						view.getHero2Field().add(field2.get(i));
					}
					for(int i=3;i<hand2.size();i++){
						view.getHero2Hand().add(hand2.get(i));
					}
					
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{
				power2=true;
			}
			selectedMinion2=null;
			selectedSpell2=null;
		}
		if(power1==true&&field1.contains(b)&&b!=field1.get(0)&&b!=field1.get(1)&&hero1==model.getCurrentHero()){
			power1=false;
			int index=field1.indexOf(b);
			Minion min=hero1.getField().get(index-2);
			if (hero1 instanceof Priest){
				Priest priest1=(Priest) hero1;
				try {
					priest1.useHeroPower(min);
					
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (hero1 instanceof Mage){
				Mage mage1=(Mage) hero1;
				try {
					mage1.useHeroPower(min);
					
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
					+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
					"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+"<html>");
			int size=field1.size();
			for(int i=2;i<size;i++){
				field1.remove(2);
				view.getHero1Field().remove(2);
			}
			view.revalidate();
			view.repaint();
			for(int i=0;i<hero1.getField().size();i++){
				Minion m=hero1.getField().get(i);
				String t=(m.isTaunt()?"yes":"no");
				String d=(m.isDivine()?"yes":"no");
				String s=(m.isSleeping()?"yes":"no");
				JButton jb = new JButton();
				jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
					+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
					+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
				field1.add(jb);
				jb.addActionListener(this);
			}
			for(int i=2;i<field1.size();i++){
				view.getHero1Field().add(field1.get(i));
			}
			
			view.revalidate();
			view.repaint();
		}
		
		if(power1==true&&field2.contains(b)&&b!=field2.get(0)&&b!=field2.get(1)&&hero1==model.getCurrentHero()){
			int index=field2.indexOf(b);
			Minion min=hero2.getField().get(index-2);
			if (hero1 instanceof Priest){
				Priest priest1=(Priest) hero1;
				try {
					priest1.useHeroPower(min);
					
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (hero1 instanceof Mage){
				Mage mage1=(Mage) hero1;
				try {
					mage1.useHeroPower(min);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
					+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
					"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+"<html>");
			int size=field2.size();
			for(int i=2;i<size;i++){
				field2.remove(2);
				view.getHero2Field().remove(2);
			}
			for(int i=0;i<hero2.getField().size();i++){
				Minion m=hero2.getField().get(i);
				String t=(m.isTaunt()?"yes":"no");
				String d=(m.isDivine()?"yes":"no");
				String s=(m.isSleeping()?"yes":"no");
				JButton jb = new JButton();
				jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
					+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
					+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
				field2.add(jb);
				jb.addActionListener(this);
			}
			for(int i=2;i<field2.size();i++){
				view.getHero2Field().add(field2.get(i));
			}
			power1=false;
			view.revalidate();
			view.repaint();
		}
		
		if(power2==true&&field1.contains(b)&&b!=field1.get(0)&&b!=field1.get(1)&&hero2==model.getCurrentHero()){
			int index=field1.indexOf(b);
			Minion min=hero1.getField().get(index-2);
			if (hero2 instanceof Priest){
				Priest priest2=(Priest) hero2;
				try {
					priest2.useHeroPower(min);
					
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (hero2 instanceof Mage){
				Mage mage2=(Mage) hero2;
				try {
					mage2.useHeroPower(min);
					
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
					+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
					"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+"<html>");
			int size=field1.size();
			for(int i=2;i<size;i++){
				field1.remove(2);
				view.getHero1Field().remove(2);
			}
			for(int i=0;i<hero1.getField().size();i++){
				Minion m=hero1.getField().get(i);
				String t=(m.isTaunt()?"yes":"no");
				String d=(m.isDivine()?"yes":"no");
				String s=(m.isSleeping()?"yes":"no");
				JButton jb = new JButton();
				jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
					+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
					+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
				field1.add(jb);
				jb.addActionListener(this);
			}
			for(int i=2;i<field1.size();i++){
				view.getHero1Field().add(field1.get(i));
			}
			power2=false;
			view.revalidate();
			view.repaint();
		}
		
		if(power2==true&&field2.contains(b)&&b!=field2.get(0)&&b!=field2.get(1)&&hero2==model.getCurrentHero()){
			int index=field2.indexOf(b);
			Minion min=hero2.getField().get(index-2);
			if (hero2 instanceof Priest){
				Priest priest2=(Priest) hero2;
				try {
					priest2.useHeroPower(min);
					
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (hero2 instanceof Mage){
				Mage mage2=(Mage) hero2;
				try {
					mage2.useHeroPower(min);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
					+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
					"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+"<html>");
			int size=field2.size();
			for(int i=2;i<size;i++){
				field2.remove(2);
				view.getHero2Field().remove(2);
			}
			for(int i=0;i<hero2.getField().size();i++){
				Minion m=hero2.getField().get(i);
				String t=(m.isTaunt()?"yes":"no");
				String d=(m.isDivine()?"yes":"no");
				String s=(m.isSleeping()?"yes":"no");
				JButton jb = new JButton();
				jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
					+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
					+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
				field2.add(jb);
				jb.addActionListener(this);
			}
			for(int i=2;i<field2.size();i++){
				view.getHero2Field().add(field2.get(i));
			}
			power2=false;
			view.revalidate();
			view.repaint();
		}
		
		if(power1==true&&b==hand1.get(0)&&hero1==model.getCurrentHero()){
			if(hero1 instanceof Priest){
				Priest priest1=(Priest) hero1;
				try {
					priest1.useHeroPower(hero1);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(hero1 instanceof Mage){
				Mage mage1=(Mage) hero1;
				try {
					mage1.useHeroPower(hero1);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
					+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
					"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+"<html>");
			power1=false;
			view.revalidate();
			view.repaint();
		}
		
		if(power1==true&&b==hand2.get(0)&&hero1==model.getCurrentHero()){
			if(hero1 instanceof Priest){
				Priest priest1=(Priest) hero1;
				try {
					priest1.useHeroPower(hero2);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(hero1 instanceof Mage){
				Mage mage1=(Mage) hero1;
				try {
					mage1.useHeroPower(hero2);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
					+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
					"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+"<html>");
			hand2.get(0).setText("<html>Opponent<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
					+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
					"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
					"<br/>Cards in hand:"+hero2.getHand().size()+"<html>");
			power1=false;
			view.revalidate();
			view.repaint();
		}
		
		if(power2==true&&b==hand1.get(0)&&hero2==model.getCurrentHero()){
			if(hero2 instanceof Priest){
				Priest priest2=(Priest) hero2;
				try {
					priest2.useHeroPower(hero1);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(hero2 instanceof Mage){
				Mage mage2=(Mage) hero2;
				try {
					mage2.useHeroPower(hero1);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
					+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
					"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+"<html>");
			hand1.get(0).setText("<html>Opponent<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
					+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
					"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
					"<br/>Cards in hand:"+hero1.getHand().size()+"<html>");
			power2=false;
			view.revalidate();
			view.repaint();
		}
		
		if(power2==true&&b==hand2.get(0)&&hero2==model.getCurrentHero()){
			if(hero2 instanceof Priest){
				Priest priest2=(Priest) hero2;
				try {
					priest2.useHeroPower(hero2);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(hero2 instanceof Mage){
				Mage mage2=(Mage) hero2;
				try {
					mage2.useHeroPower(hero2);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
					+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
					"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+"<html>");
			power2=false;
			view.revalidate();
			view.repaint();
		}
		
		if(hand1.contains(b)&&!b.getActionCommand().equals("Use Hero Power")&&
				!b.getActionCommand().equals("End Turn")&&b!=hand1.get(0)){
			int index=hand1.indexOf(b);
			Card card =  hero1.getHand().get(index-3);
			if (card instanceof Minion){
				minion1=(Minion) card;
				selectedMinion1=b;
				selectedSpell1=null;
				
		}
			else{
				spell1=(Spell) card;
				selectedSpell1=b;
				selectedMinion1=null;
				
			}
			attackingMinion1=null;
			power1=false;
		}
		if(b.getActionCommand().equals("Add to field")&&hero1==model.getCurrentHero()&&selectedMinion1!=null){
			try {
				hero1.playMinion(minion1);
				hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
						+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+"<html>");
				String t=(minion1.isTaunt()?"yes":"no");
				String d=(minion1.isDivine()?"yes":"no");
				String s=(minion1.isSleeping()?"yes":"no");
				selectedMinion1.setText("<html>"+minion1.getName()+"<br/>Mana Cost:"+minion1.getManaCost()
						+"<br/>Rarity:"+minion1.getRarity()+"<br/>HP:"+minion1.getCurrentHP()+"<br/>Attack:"
						+minion1.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
				field1.add(selectedMinion1);
				view.getHero1Field().add(selectedMinion1);
				hand1.remove(selectedMinion1);

				view.revalidate();
				view.repaint();
			} catch (NotYourTurnException | NotEnoughManaException
					| FullFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			selectedMinion1=null;
			view.revalidate();
			view.repaint();
			
		}
		
		
		
		if(hand2.contains(b)&&!b.getActionCommand().equals("Use Hero Power")&&
				!b.getActionCommand().equals("End Turn")&&b!=hand2.get(0)){
			int index=hand2.indexOf(b);
			Card card =  hero2.getHand().get(index-3);
			if (card instanceof Minion){
				minion2=(Minion) card;
				selectedMinion2=b;
				selectedSpell2=null;
				
		}
			else{
				spell2=(Spell) card;
				selectedSpell2=b;
				selectedMinion2=null;
				
			}
			power2=false;
			attackingMinion2=null;
		}
		
		if(b.getActionCommand().equals("Add to field")&&hero2==model.getCurrentHero()&&selectedMinion2!=null){
			try {
				hero2.playMinion(minion2);
				hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
						+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+"<html>");
				String t=(minion2.isTaunt()?"yes":"no");
				String d=(minion2.isDivine()?"yes":"no");
				String s=(minion2.isSleeping()?"yes":"no");
				selectedMinion2.setText("<html>"+minion2.getName()+"<br/>Mana Cost:"+minion2.getManaCost()
						+"<br/>Rarity:"+minion2.getRarity()+"<br/>HP:"+minion2.getCurrentHP()+"<br/>Attack:"
						+minion2.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
				field2.add(selectedMinion2);
				view.getHero2Field().add(selectedMinion2);
				hand2.remove(selectedMinion2);

				view.revalidate();
				view.repaint();
			} catch (NotYourTurnException | NotEnoughManaException
					| FullFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			selectedMinion2=null;
			view.revalidate();
			view.repaint();	
		}
		
if(b.getActionCommand().equals("Cast AOE/Field Spell")&&selectedSpell1!=null&&hero1==model.getCurrentHero()
&&(spell1 instanceof AOESpell || spell1 instanceof FieldSpell)){
			
			if(spell1 instanceof AOESpell){
				AOESpell AOESpell1=(AOESpell) spell1;
				try {
					hero1.castSpell(AOESpell1, hero2.getField());
					hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
							+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+"<html>");
						int size1=field1.size();
						int size2=field2.size();
						for(int i=2;i<size1;i++){
							field1.remove(2);
							view.getHero1Field().remove(2);
						}
						for(int i=2;i<size2;i++){
							field2.remove(2);
							view.getHero2Field().remove(2);
						}
						for(int i=0;i<hero1.getField().size();i++){
							Minion m=hero1.getField().get(i);
							String t=(m.isTaunt()?"yes":"no");
							String d=(m.isDivine()?"yes":"no");
							String s=(m.isSleeping()?"yes":"no");
							JButton jb = new JButton();
							jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
								+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
								+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
							field1.add(jb);
							jb.addActionListener(this);
						}
						for(int i=0;i<hero2.getField().size();i++){
							Minion m=hero2.getField().get(i);
							String t=(m.isTaunt()?"yes":"no");
							String d=(m.isDivine()?"yes":"no");
							String s=(m.isSleeping()?"yes":"no");
							JButton jb = new JButton();
							jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
								+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
								+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
							field2.add(jb);
							jb.addActionListener(this);
						}
						for(int i=2;i<field1.size();i++){
							view.getHero1Field().add(field1.get(i));
						}
						for(int i=2;i<field2.size();i++){
							view.getHero2Field().add(field2.get(i));
						}
						view.getHero1Hand().remove(selectedSpell1);
						hand1.remove(selectedSpell1);
						selectedSpell1=null;
						view.revalidate();
						view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(spell1 instanceof FieldSpell){
				FieldSpell fieldSpell1=(FieldSpell) spell1;
				try {
					hero1.castSpell(fieldSpell1);
					hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
							+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+"<html>");
						int size1=field1.size();
						int size2=field2.size();
						for(int i=2;i<size1;i++){
							field1.remove(2);
							view.getHero1Field().remove(2);
						}
						for(int i=2;i<size2;i++){
							field2.remove(2);
							view.getHero2Field().remove(2);
						}
						for(int i=0;i<hero1.getField().size();i++){
							Minion m=hero1.getField().get(i);
							String t=(m.isTaunt()?"yes":"no");
							String d=(m.isDivine()?"yes":"no");
							String s=(m.isSleeping()?"yes":"no");
							JButton jb = new JButton();
							jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
								+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
								+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
							field1.add(jb);
							jb.addActionListener(this);
						}
						for(int i=0;i<hero2.getField().size();i++){
							Minion m=hero2.getField().get(i);
							String t=(m.isTaunt()?"yes":"no");
							String d=(m.isDivine()?"yes":"no");
							String s=(m.isSleeping()?"yes":"no");
							JButton jb = new JButton();
							jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
								+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
								+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
							field2.add(jb);
							jb.addActionListener(this);
						}
						for(int i=2;i<field1.size();i++){
							view.getHero1Field().add(field1.get(i));
						}
						for(int i=2;i<field2.size();i++){
							view.getHero2Field().add(field2.get(i));
						}
						view.getHero1Hand().remove(selectedSpell1);
						hand1.remove(selectedSpell1);
						selectedSpell1=null;
						view.revalidate();
						view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
		}
		
if(b.getActionCommand().equals("Cast AOE/Field Spell")&&selectedSpell2!=null&&hero2==model.getCurrentHero()
&&(spell2 instanceof AOESpell || spell2 instanceof FieldSpell)){
			if(spell2 instanceof AOESpell){
				AOESpell AOESpell2=(AOESpell) spell2;
				try {
					hero2.castSpell(AOESpell2, hero1.getField());
					hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
							+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+"<html>");
						int size1=field1.size();
						int size2=field2.size();
						for(int i=2;i<size1;i++){
							field1.remove(2);
							view.getHero1Field().remove(2);
						}
						for(int i=2;i<size2;i++){
							field2.remove(2);
							view.getHero2Field().remove(2);
						}
						for(int i=0;i<hero1.getField().size();i++){
							Minion m=hero1.getField().get(i);
							String t=(m.isTaunt()?"yes":"no");
							String d=(m.isDivine()?"yes":"no");
							String s=(m.isSleeping()?"yes":"no");
							JButton jb = new JButton();
							jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
								+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
								+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
							field1.add(jb);
							jb.addActionListener(this);
						}
						for(int i=0;i<hero2.getField().size();i++){
							Minion m=hero2.getField().get(i);
							String t=(m.isTaunt()?"yes":"no");
							String d=(m.isDivine()?"yes":"no");
							String s=(m.isSleeping()?"yes":"no");
							JButton jb = new JButton();
							jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
								+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
								+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
							field2.add(jb);
							jb.addActionListener(this);
						}
						for(int i=2;i<field1.size();i++){
							view.getHero1Field().add(field1.get(i));
						}
						for(int i=2;i<field2.size();i++){
							view.getHero2Field().add(field2.get(i));
						}
						view.getHero2Hand().remove(selectedSpell2);
						hand2.remove(selectedSpell2);
						selectedSpell2=null;
						view.revalidate();
						view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(spell2 instanceof FieldSpell){
				FieldSpell fieldSpell2=(FieldSpell) spell2;
				try {
					hero2.castSpell(fieldSpell2);
					hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
							+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+"<html>");
						int size1=field1.size();
						int size2=field2.size();
						for(int i=2;i<size1;i++){
							field1.remove(2);
							view.getHero1Field().remove(2);
						}
						for(int i=2;i<size2;i++){
							field2.remove(2);
							view.getHero2Field().remove(2);
						}
						for(int i=0;i<hero1.getField().size();i++){
							Minion m=hero1.getField().get(i);
							String t=(m.isTaunt()?"yes":"no");
							String d=(m.isDivine()?"yes":"no");
							String s=(m.isSleeping()?"yes":"no");
							JButton jb = new JButton();
							jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
								+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
								+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
							field1.add(jb);
							jb.addActionListener(this);
						}
						for(int i=0;i<hero2.getField().size();i++){
							Minion m=hero2.getField().get(i);
							String t=(m.isTaunt()?"yes":"no");
							String d=(m.isDivine()?"yes":"no");
							String s=(m.isSleeping()?"yes":"no");
							JButton jb = new JButton();
							jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
								+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
								+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
							field2.add(jb);
							jb.addActionListener(this);
						}
						for(int i=2;i<field1.size();i++){
							view.getHero1Field().add(field1.get(i));
						}
						for(int i=2;i<field2.size();i++){
							view.getHero2Field().add(field2.get(i));
						}
						view.getHero2Hand().remove(selectedSpell2);
						hand2.remove(selectedSpell2);
						selectedSpell2=null;
						view.revalidate();
						view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
		if(field1.contains(b)&&b!=field1.get(0)&&b!=field1.get(1)&&model.getCurrentHero()==hero1&&selectedSpell1==null){
			int index=field1.indexOf(b);
			attacker1=hero1.getField().get(index-2);
			attackingMinion1=b;
			selectedMinion1=null;
			power1=false;
		}
		
		if(field2.contains(b)&&b!=field2.get(0)&&b!=field2.get(1)&&model.getCurrentHero()==hero2&&selectedSpell1==null){
			int index=field2.indexOf(b);
			attacker2=hero2.getField().get(index-2);
			attackingMinion2=b;
			selectedMinion2=null;
			power2=false;
		}
		
		if(field2.contains(b)&&b!=field2.get(0)&&b!=field2.get(1)&&attackingMinion1!=null&&model.getCurrentHero()==hero1){
			int index=field2.indexOf(b);
			Minion min = hero2.getField().get(index-2);
			try {
				hero1.attackWithMinion(attacker1, min);
				int size1=field1.size();
				int size2=field2.size();
				for(int i=2;i<size1;i++){
					field1.remove(2);
					view.getHero1Field().remove(2);
				}
				for(int i=2;i<size2;i++){
					field2.remove(2);
					view.getHero2Field().remove(2);
				}
				for(int i=0;i<hero1.getField().size();i++){
					Minion m=hero1.getField().get(i);
					String t=(m.isTaunt()?"yes":"no");
					String d=(m.isDivine()?"yes":"no");
					String s=(m.isSleeping()?"yes":"no");
					JButton jb = new JButton();
					jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
						+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
						+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
					field1.add(jb);
					jb.addActionListener(this);
				}
				for(int i=0;i<hero2.getField().size();i++){
					Minion m=hero2.getField().get(i);
					String t=(m.isTaunt()?"yes":"no");
					String d=(m.isDivine()?"yes":"no");
					String s=(m.isSleeping()?"yes":"no");
					JButton jb = new JButton();
					jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
						+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
						+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
					field2.add(jb);
					jb.addActionListener(this);
				}
				for(int i=2;i<field1.size();i++){
					view.getHero1Field().add(field1.get(i));
				}
				for(int i=2;i<field2.size();i++){
					view.getHero2Field().add(field2.get(i));
				}
				view.revalidate();
				view.repaint();
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | InvalidTargetException
					| NotSummonedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(field1.contains(b)&&b!=field1.get(0)&&b!=field1.get(1)&&attackingMinion2!=null&&model.getCurrentHero()==hero2){
			int index=field1.indexOf(b);
			Minion min = hero1.getField().get(index-2);
			try {
				hero2.attackWithMinion(attacker2, min);
				int size1=field1.size();
				int size2=field2.size();
				for(int i=2;i<size1;i++){
					field1.remove(2);
					view.getHero1Field().remove(2);
				}
				for(int i=2;i<size2;i++){
					field2.remove(2);
					view.getHero2Field().remove(2);
				}
				for(int i=0;i<hero1.getField().size();i++){
					Minion m=hero1.getField().get(i);
					String t=(m.isTaunt()?"yes":"no");
					String d=(m.isDivine()?"yes":"no");
					String s=(m.isSleeping()?"yes":"no");
					JButton jb = new JButton();
					jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
						+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
						+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
					field1.add(jb);
					jb.addActionListener(this);
				}
				for(int i=0;i<hero2.getField().size();i++){
					Minion m=hero2.getField().get(i);
					String t=(m.isTaunt()?"yes":"no");
					String d=(m.isDivine()?"yes":"no");
					String s=(m.isSleeping()?"yes":"no");
					JButton jb = new JButton();
					jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
						+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
						+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
					field2.add(jb);
					jb.addActionListener(this);
				}
				for(int i=2;i<field1.size();i++){
					view.getHero1Field().add(field1.get(i));
				}
				for(int i=2;i<field2.size();i++){
					view.getHero2Field().add(field2.get(i));
				}
				view.revalidate();
				view.repaint();
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | InvalidTargetException
					| NotSummonedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(field2.contains(b)&&b!=field2.get(0)&&b!=field2.get(1)&&selectedSpell1!=null&&
				(spell1 instanceof MinionTargetSpell||spell1 instanceof LeechingSpell)&&hero1==model.getCurrentHero()){
			int index = field2.indexOf(b);
			Minion min=hero2.getField().get(index-2);
			if(spell1 instanceof MinionTargetSpell){
				MinionTargetSpell minionSpell1=(MinionTargetSpell) spell1;
				try {
					hero1.castSpell(minionSpell1,min );
					hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
							+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
							"<html>");
					int size=field2.size();
					for(int i=2;i<size;i++){
						field2.remove(2);
						view.getHero2Field().remove(2);
					}
					for(int i=0;i<hero2.getField().size();i++){
						Minion m=hero2.getField().get(i);
						String t=(m.isTaunt()?"yes":"no");
						String d=(m.isDivine()?"yes":"no");
						String s=(m.isSleeping()?"yes":"no");
						JButton jb = new JButton();
						jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
							+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
							+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
						field2.add(jb);
						jb.addActionListener(this);
					}
					for(int i=2;i<field2.size();i++){
						view.getHero2Field().add(field2.get(i));
					}
					hand1.remove(selectedSpell1);
					view.getHero1Hand().remove(selectedSpell1);
					selectedSpell1=null;
					view.revalidate();
					view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException
						| InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
				}	
			}
			else if (spell1 instanceof LeechingSpell){
				LeechingSpell leechingSpell1=(LeechingSpell) spell1;
				try {
					hero1.castSpell(leechingSpell1, min);
					hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
							+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
							"<html>");
					int size=field2.size();
					for(int i=2;i<size;i++){
						field2.remove(2);
						view.getHero2Field().remove(2);
					}
					for(int i=0;i<hero2.getField().size();i++){
						Minion m=hero2.getField().get(i);
						String t=(m.isTaunt()?"yes":"no");
						String d=(m.isDivine()?"yes":"no");
						String s=(m.isSleeping()?"yes":"no");
						JButton jb = new JButton();
						jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
							+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
							+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
						field2.add(jb);
						jb.addActionListener(this);
					}
					for(int i=2;i<field2.size();i++){
						view.getHero2Field().add(field2.get(i));
					}
					hand1.remove(selectedSpell1);
					view.getHero1Hand().remove(selectedSpell1);
					selectedSpell1=null;
					view.revalidate();
					view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();		
				}	
			}	
		}
		
		if(field1.contains(b)&&b!=field1.get(0)&&b!=field1.get(1)&&selectedSpell1!=null&&
				(spell1 instanceof MinionTargetSpell||spell1 instanceof LeechingSpell)&&hero1==model.getCurrentHero()){
	
			int index = field1.indexOf(b);
			Minion min=hero1.getField().get(index-2);
			if(spell1 instanceof MinionTargetSpell){
				MinionTargetSpell minionSpell1=(MinionTargetSpell) spell1;
				try {
					hero1.castSpell(minionSpell1,min );
					hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
							+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
							"<html>");
					int size=field1.size();
					for(int i=2;i<size;i++){
						field1.remove(2);
						view.getHero1Field().remove(2);
					}
					for(int i=0;i<hero1.getField().size();i++){
						Minion m=hero1.getField().get(i);
						String t=(m.isTaunt()?"yes":"no");
						String d=(m.isDivine()?"yes":"no");
						String s=(m.isSleeping()?"yes":"no");
						JButton jb = new JButton();
						jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
							+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
							+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
						field1.add(jb);
						jb.addActionListener(this);
					}
					for(int i=2;i<field1.size();i++){
						view.getHero1Field().add(field1.get(i));
					}
					hand1.remove(selectedSpell1);
					view.getHero1Hand().remove(selectedSpell1);
					selectedSpell1=null;
					view.revalidate();
					view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException
						| InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
				}	
			}
			else if (spell1 instanceof LeechingSpell){
				LeechingSpell leechingSpell1=(LeechingSpell) spell1;
				try {
					hero1.castSpell(leechingSpell1, min);
					hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
							+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
							"<html>");
					int size=field1.size();
					for(int i=2;i<size;i++){
						field1.remove(2);
						view.getHero1Field().remove(2);
					}
					for(int i=0;i<hero1.getField().size();i++){
						Minion m=hero1.getField().get(i);
						String t=(m.isTaunt()?"yes":"no");
						String d=(m.isDivine()?"yes":"no");
						String s=(m.isSleeping()?"yes":"no");
						JButton jb = new JButton();
						jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
							+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
							+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
						field1.add(jb);
						jb.addActionListener(this);
					}
					for(int i=2;i<field1.size();i++){
						view.getHero1Field().add(field1.get(i));
					}
					hand1.remove(selectedSpell1);
					view.getHero1Hand().remove(selectedSpell1);
					selectedSpell1=null;
					view.revalidate();
					view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();		
				}	
			}	
		}
		
		if(field1.contains(b)&&b!=field1.get(0)&&b!=field1.get(1)&&selectedSpell2!=null&&
				(spell2 instanceof MinionTargetSpell||spell2 instanceof LeechingSpell)&&hero2==model.getCurrentHero()){
			int index = field1.indexOf(b);
			Minion min=hero1.getField().get(index-2);
			if(spell2 instanceof MinionTargetSpell){
				MinionTargetSpell minionSpell2=(MinionTargetSpell) spell2;
				try {
					hero2.castSpell(minionSpell2,min );
					hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
							+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
							"<html>");
					int size=field1.size();
					for(int i=2;i<size;i++){
						field1.remove(2);
						view.getHero1Field().remove(2);
					}
					for(int i=0;i<hero1.getField().size();i++){
						Minion m=hero1.getField().get(i);
						String t=(m.isTaunt()?"yes":"no");
						String d=(m.isDivine()?"yes":"no");
						String s=(m.isSleeping()?"yes":"no");
						JButton jb = new JButton();
						jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
							+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
							+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
						field1.add(jb);
						jb.addActionListener(this);
					}
					for(int i=2;i<field1.size();i++){
						view.getHero1Field().add(field1.get(i));
					}
					hand2.remove(selectedSpell2);
					view.getHero2Hand().remove(selectedSpell2);
					selectedSpell2=null;
					view.revalidate();
					view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException
						| InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}	
			}
			else if (spell2 instanceof LeechingSpell){
				LeechingSpell leechingSpell2=(LeechingSpell) spell2;
				try {
					hero2.castSpell(leechingSpell2, min);
					hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
							+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
							"<html>");
					int size=field1.size();
					for(int i=2;i<size;i++){
						field1.remove(2);
						view.getHero1Field().remove(2);
					}
					for(int i=0;i<hero1.getField().size();i++){
						Minion m=hero1.getField().get(i);
						String t=(m.isTaunt()?"yes":"no");
						String d=(m.isDivine()?"yes":"no");
						String s=(m.isSleeping()?"yes":"no");
						JButton jb = new JButton();
						jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
							+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
							+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
						field1.add(jb);
						jb.addActionListener(this);
					}
					for(int i=2;i<field1.size();i++){
						view.getHero1Field().add(field1.get(i));
					}
					hand2.remove(selectedSpell2);
					view.getHero2Hand().remove(selectedSpell2);
					selectedSpell2=null;
					view.revalidate();
					view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	
		}
		
		if(field2.contains(b)&&b!=field2.get(0)&&b!=field2.get(1)&&selectedSpell2!=null&&
				(spell2 instanceof MinionTargetSpell||spell2 instanceof LeechingSpell)&&hero2==model.getCurrentHero()){
	
			int index = field2.indexOf(b);
			Minion min=hero2.getField().get(index-2);
			if(spell2 instanceof MinionTargetSpell){
				MinionTargetSpell minionSpell2=(MinionTargetSpell) spell2;
				try {
					hero2.castSpell(minionSpell2,min );
					hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
							+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
							"<html>");
					int size=field2.size();
					for(int i=2;i<size;i++){
						field2.remove(2);
						view.getHero2Field().remove(2);
					}
					for(int i=0;i<hero2.getField().size();i++){
						Minion m=hero2.getField().get(i);
						String t=(m.isTaunt()?"yes":"no");
						String d=(m.isDivine()?"yes":"no");
						String s=(m.isSleeping()?"yes":"no");
						JButton jb = new JButton();
						jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
							+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
							+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
						field2.add(jb);
						jb.addActionListener(this);
					}
					for(int i=2;i<field2.size();i++){
						view.getHero2Field().add(field2.get(i));
					}
					hand2.remove(selectedSpell2);
					view.getHero2Hand().remove(selectedSpell2);
					selectedSpell2=null;
					view.revalidate();
					view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException
						| InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}	
			}
			else if (spell2 instanceof LeechingSpell){
				LeechingSpell leechingSpell2=(LeechingSpell) spell2;
				try {
					hero2.castSpell(leechingSpell2, min);
					hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
							+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
							"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
							"<html>");
					int size=field2.size();
					for(int i=2;i<size;i++){
						field2.remove(2);
						view.getHero2Field().remove(2);
					}
					for(int i=0;i<hero2.getField().size();i++){
						Minion m=hero2.getField().get(i);
						String t=(m.isTaunt()?"yes":"no");
						String d=(m.isDivine()?"yes":"no");
						String s=(m.isSleeping()?"yes":"no");
						JButton jb = new JButton();
						jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
							+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
							+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
						field2.add(jb);
						jb.addActionListener(this);
					}
					for(int i=2;i<field2.size();i++){
						view.getHero2Field().add(field2.get(i));
					}
					hand2.remove(selectedSpell2);
					view.getHero2Hand().remove(selectedSpell2);
					selectedSpell2=null;
					view.revalidate();
					view.repaint();
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		}
		
		if(b==hand2.get(0)&&selectedSpell1!=null&& spell1 instanceof HeroTargetSpell&&hero1==model.getCurrentHero()){
			HeroTargetSpell heroSpell1= (HeroTargetSpell) spell1;
			try {
				hero1.castSpell(heroSpell1, hero2);
				hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
						+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
						"<html>");
				hand2.get(0).setText("<html>Opponent<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
						+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
						"<br/>Cards in hand:"+hero2.getHand().size()+"<html>");
				hand1.remove(selectedSpell1);
				view.getHero1Hand().remove(selectedSpell1);
				selectedSpell1=null;
				view.revalidate();
				view.repaint();
			} catch (NotYourTurnException | NotEnoughManaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(b==hand1.get(0)&&selectedSpell2!=null&& spell2 instanceof HeroTargetSpell&&hero2==model.getCurrentHero()){
			HeroTargetSpell heroSpell2= (HeroTargetSpell) spell2;
			try {
				hero2.castSpell(heroSpell2, hero1);
				hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
						+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
						"<html>");
				hand1.get(0).setText("<html>Opponent<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
						+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
						"<br/>Cards in hand:"+hero1.getHand().size()+"<html>");
				hand2.remove(selectedSpell2);
				view.getHero2Hand().remove(selectedSpell2);
				selectedSpell2=null;
				view.revalidate();
				view.repaint();
			} catch (NotYourTurnException | NotEnoughManaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(b==hand2.get(0)&&attackingMinion1!=null&&hero1==model.getCurrentHero()){
		
			try {
				hero1.attackWithMinion(attacker1, hero2);
				hand2.get(0).setText("<html>Opponent<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
						+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
						"<br/>Cards in hand:"+hero2.getHand().size()+"<html>");
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | NotSummonedException
					| InvalidTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(b==hand1.get(0)&&attackingMinion2!=null&&hero2==model.getCurrentHero()){
			try {
				hero2.attackWithMinion(attacker2, hero1);
				hand1.get(0).setText("<html>Opponent<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
						+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
						"<br/>Cards in hand:"+hero1.getHand().size()+"<html>");
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | NotSummonedException
					| InvalidTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(b.getActionCommand().equals("End Turn")){
			int handSize1=hand1.size();
			int handSize2=hand2.size();
			
			try {
				model.endTurn();
				
			} catch (FullHandException | CloneNotSupportedException e1) {
				e1.printStackTrace();
				if(hero1==model.getCurrentHero()){
					fullHand1=true;
				}
				else{
					fullHand2=true;
				}
			}
			if(model.getOpponent().equals(hero1)){
				hand1.get(0).setText("<html>Opponent<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
						+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
						"<br/>Cards in hand:"+hero1.getHand().size()+"<html>");
				hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
						+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+"<html>");
			}
			if(model.getOpponent().equals(hero2)){
				hand2.get(0).setText("<html>Opponent<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
						+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
						"<br/>Cards in hand:"+hero2.getHand().size()+"<html>");
				hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
						+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+"<html>");
			}
			for(int i=3;i<handSize1;i++){
				view.getHero1Hand().remove(3);
				hand1.remove(3);
			}
			for(int i=3;i<handSize2;i++){
				view.getHero2Hand().remove(3);
				hand2.remove(3);
				
			}
			for(int i=0;i<hero1.getHand().size();i++){
				Card c= hero1.getHand().get(i);
				String n=c.getName();
				int co=c.getManaCost();
				Rarity r=c.getRarity();
				JButton jb=new JButton();
				if ((c) instanceof Minion){
					Minion m = (Minion)(c);
					int hp =m.getCurrentHP();
					int a = m.getAttack();
					String t=(m.isTaunt()?"yes":"no");
					String d=(m.isDivine()?"yes":"no");
					String ch=(m.isSleeping()?"no":"yes");
					jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<br/>HP:"+hp+
							"<br/>Attack:"+a+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Charge: "+ch+"<html>");
				}
				else{
					jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<html>");
					
				}
				jb.addActionListener(this);
				hand1.add(jb);
				
			}
			for(int i=0;i<hero2.getHand().size();i++){
				Card c= hero2.getHand().get(i);
				String n=c.getName();
				int co=c.getManaCost();
				Rarity r=c.getRarity();
				JButton jb=new JButton();
				jb.setMargin(new Insets(0,0,0,0));
				if ((c) instanceof Minion){
					Minion m = (Minion)(c);
					int hp =m.getCurrentHP();
					int a = m.getAttack();
					String t=(m.isTaunt()?"yes":"no");
					String d=(m.isDivine()?"yes":"no");
					String ch=(m.isSleeping()?"no":"yes");
					
					jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<br/>HP:"+hp+
							"<br/>Attack:"+a+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Charge: "+ch+"<html>");
				}
				else{
					jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<html>");
				}
				jb.addActionListener(this);
				hand2.add(jb);
			}
			view.getHero1Hand().getComponent(1).setVisible(true);
			view.getHero1Hand().getComponent(2).setVisible(true);
			view.getHero2Hand().getComponent(1).setVisible(true);
			view.getHero2Hand().getComponent(2).setVisible(true);
			view.getHero1Field().getComponent(0).setVisible(true);
			view.getHero2Field().getComponent(0).setVisible(true);
			view.getHero1Field().getComponent(1).setVisible(true);
			view.getHero2Field().getComponent(1).setVisible(true);
			for(int i=0;i<hand1.size()-3;i++){
				view.getHero1Hand().add(hand1.get(i+3));
				if(model.getOpponent().equals(hero1)){
					view.getHero1Hand().getComponent(i+3).setVisible(false);
					
					
				}
			}
			for(int i=0;i<hand2.size()-3;i++){
				view.getHero2Hand().add(hand2.get(i+3));
				if(model.getOpponent().equals(hero2)){
					view.getHero2Hand().getComponent(i+3).setVisible(false);
				}
			}
			int fieldSize1=field1.size();
			int fieldSize2=field2.size();
			for(int i=2;i<fieldSize1;i++){
				field1.remove(2);
				view.getHero1Field().remove(2);
				
			}
			for(int i=2;i<fieldSize2;i++){
				field2.remove(2);
				view.getHero2Field().remove(2);
			}
			for(int i=0;i<hero1.getField().size();i++){
				Minion m=hero1.getField().get(i);
				String t=(m.isTaunt()?"yes":"no");
				String d=(m.isDivine()?"yes":"no");
				String s=(m.isSleeping()?"yes":"no");
				JButton jb = new JButton();
				jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
					+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
					+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
				field1.add(jb);
				jb.addActionListener(this);
			}
			for(int i=0;i<hero2.getField().size();i++){
				Minion m=hero2.getField().get(i);
				String t=(m.isTaunt()?"yes":"no");
				String d=(m.isDivine()?"yes":"no");
				String s=(m.isSleeping()?"yes":"no");
				JButton jb = new JButton();
				jb.setText("<html>"+m.getName()+"<br/>Mana Cost:"+m.getManaCost()
					+"<br/>Rarity:"+m.getRarity()+"<br/>HP:"+m.getCurrentHP()+"<br/>Attack:"
					+m.getAttack()+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Sleeping: "+s+"<html>");
				field2.add(jb);
				jb.addActionListener(this);
			}
			for(int i=2;i<field1.size();i++){
				view.getHero1Field().add(field1.get(i));
			}
			for(int i=2;i<field2.size();i++){
				view.getHero2Field().add(field2.get(i));
			}

			
			if(model.getOpponent().equals(hero1)){
				view.getHero1Field().getComponent(0).setVisible(false);
				view.getHero1Field().getComponent(1).setVisible(false);
				view.getHero1Hand().getComponent(1).setVisible(false);
				view.getHero1Hand().getComponent(2).setVisible(false);
			}
			if(model.getOpponent().equals(hero2)){
				view.getHero2Field().getComponent(0).setVisible(false);
				view.getHero2Field().getComponent(1).setVisible(false);
				view.getHero2Hand().getComponent(1).setVisible(false);
				view.getHero2Hand().getComponent(2).setVisible(false);
			}
			power1=false;
			power2=false;
			if(fullHand1==true){
				JButton burnedCard1=new JButton();
				
				Card card1= hero1.getDeck().get(0);
				String n=card1.getName();
				int co=card1.getManaCost();
				Rarity r=card1.getRarity();
				if ((card1) instanceof Minion){
					Minion m = (Minion)(card1);
					int hp =m.getCurrentHP();
					int a = m.getAttack();
					String t=(m.isTaunt()?"yes":"no");
					String d=(m.isDivine()?"yes":"no");
					String ch=(m.isSleeping()?"no":"yes");
					burnedCard1.setText("<html>Burned Card<br/>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<br/>HP:"+hp+
							"<br/>Attack:"+a+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Charge: "+ch+"<html>");
				}
				else{
					burnedCard1.setText("<html>Burned Card<br/>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<html>");
					
				}
				hand1.add(burnedCard1);
				view.getHero1Hand().add(burnedCard1);
			}
			if(fullHand2==true){
				JButton burnedCard2=new JButton();
				Card card2=hero2.getDeck().get(0);
				if(hero2==model.getCurrentHero()){
					String n=card2.getName();
					int co=card2.getManaCost();
					Rarity r=card2.getRarity();
					if ((card2) instanceof Minion){
						Minion m = (Minion)(card2);
						int hp =m.getCurrentHP();
						int a = m.getAttack();
						String t=(m.isTaunt()?"yes":"no");
						String d=(m.isDivine()?"yes":"no");
						String ch=(m.isSleeping()?"no":"yes");
						burnedCard2.setText("<html>Burned Card<br/>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<br/>HP:"+hp+
								"<br/>Attack:"+a+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Charge: "+ch+"<html>");
					}
					else{
						burnedCard2.setText("<html>Burned Card<br/>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<html>");
						
					}
					hand2.add(burnedCard2);
					view.getHero2Hand().add(burnedCard2);
					}
			}
			fullHand1=false;
			fullHand2=false;
			view.revalidate();
			view.repaint();
		}
		
		if(name.equals("Hunter")||name.equals("hunter")||name.equals("Mage")||name.equals("mage")||name.equals("Paladin")
				||name.equals("paladin")||name.equals("Priest")||name.equals("priest")||name.equals("Warlock")
				||name.equals("warlock")){
		if(hero1!=null&& hero2!=null){
			view1.setVisible(false);
			try {
				
				model =new Game(hero1,hero2);
				model.setListener(this);
				view=new Hearthstone(hero1,hero2);
				
			} catch (FullHandException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			view.getHero1Hand().add(hand1.get(0));
			view.getHero2Hand().add(hand2.get(0));
			view.getHero1Hand().add(hand1.get(1));
			view.getHero2Hand().add(hand2.get(1));
			view.getHero1Hand().add(hand1.get(2));
			view.getHero2Hand().add(hand2.get(2));
			for(int i=0;i<hero1.getHand().size();i++){
				Card c= hero1.getHand().get(i);
				String n=c.getName();
				int co=c.getManaCost();
				Rarity r=c.getRarity();
				JButton jb=new JButton();
				if ((c) instanceof Minion){
					Minion m = (Minion)(c);
					int hp =m.getCurrentHP();
					int a = m.getAttack();
					String t=(m.isTaunt()?"yes":"no");
					String d=(m.isDivine()?"yes":"no");
					String ch=(m.isSleeping()?"no":"yes");
					jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<br/>HP:"+hp+
							"<br/>Attack:"+a+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Charge: "+ch+"<html>");
				}
				else{
					jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<html>");
					
				}
				jb.addActionListener(this);
				hand1.add(jb);
				
			}
			for(int i=0;i<hero2.getHand().size();i++){
				Card c= hero2.getHand().get(i);
				String n=c.getName();
				int co=c.getManaCost();
				Rarity r=c.getRarity();
				JButton jb=new JButton();
				if ((c) instanceof Minion){
					Minion m = (Minion)(c);
					int hp =m.getCurrentHP();
					int a = m.getAttack();
					String t=(m.isTaunt()?"yes":"no");
					String d=(m.isDivine()?"yes":"no");
					String ch=(m.isSleeping()?"no":"yes");
					
					jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<br/>HP:"+hp+
							"<br/>Attack:"+a+"<br/>Taunt: "+t+"<br/>Divine Shield: "+d+"<br/>Charge: "+ch+"<html>");
				}
				else{
					jb.setText("<html>"+n+"<br/>Mana Cost:"+co+"<br/>Rarity:"+r+"<html");
				}
				jb.addActionListener(this);
				hand2.add(jb);
			}
			if(model.getOpponent().equals(hero1)){
				hand1.get(0).setText("<html>Opponent<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
						+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+
						"<br/>Cards in hand:"+hero1.getHand().size()+"<html>");
				hand2.get(0).setText("<html>Current Hero<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
						+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+"<html>");
			}
			if(model.getOpponent().equals(hero2)){
				hand2.get(0).setText("<html>Opponent<br/>Hero 2<br/>"+hero2.getName()+"<br/>Current HP:"+hero2.getCurrentHP()
						+"<br/>Total Mana:"+hero2.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero2.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero2.getDeck().size()+
						"<br/>Cards in hand:"+hero2.getHand().size()+"<html>");
				hand1.get(0).setText("<html>Current Hero<br/>Hero 1<br/>"+hero1.getName()+"<br/>Current HP:"+hero1.getCurrentHP()
						+"<br/>Total Mana:"+hero1.getTotalManaCrystals()+
						"<br/>Current Mana:"+hero1.getCurrentManaCrystals()+"<br/>Cards in deck:"+hero1.getDeck().size()+"<html>");
			}
			for(int i=0;i<hand1.size()-3;i++){
				view.getHero1Hand().add(hand1.get(i+3));
				if(model.getOpponent().equals(hero1)){
					view.getHero1Hand().getComponent(i+3).setVisible(false);
					
					
				}
			}
			for(int i=0;i<hand2.size()-3;i++){
				view.getHero2Hand().add(hand2.get(i+3));
				if(model.getOpponent().equals(hero2)){
					view.getHero2Hand().getComponent(i+3).setVisible(false);
				}
			}
			view.getHero1Field().add(field1.get(0));
			view.getHero2Field().add(field2.get(0));
			view.getHero1Field().add(field1.get(1));
			view.getHero2Field().add(field2.get(1));
			if(model.getOpponent().equals(hero1)){
				view.getHero1Field().getComponent(0).setVisible(false);
				view.getHero1Field().getComponent(1).setVisible(false);
				view.getHero1Hand().getComponent(1).setVisible(false);
				view.getHero1Hand().getComponent(2).setVisible(false);
			}
			if(model.getOpponent().equals(hero2)){
				view.getHero2Field().getComponent(0).setVisible(false);
				view.getHero2Field().getComponent(1).setVisible(false);
				view.getHero2Hand().getComponent(1).setVisible(false);
				view.getHero2Hand().getComponent(2).setVisible(false);
			}
			view.revalidate();
			view.repaint();
			
		}
	
		}
	
		
	}
	


	
	

}
