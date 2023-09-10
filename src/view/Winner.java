package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.heroes.Hero;

public class Winner extends JFrame {
	public Winner(Hero h1, Hero h2){
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel end=new JPanel();
		end.setPreferredSize(new Dimension(getWidth(),getHeight()));
		end.setLayout(new GridLayout(0,1));
		JLabel label=new JLabel();
		if(h1.getCurrentHP()==0){
			label.setText("The winner is Hero 2:"+h2.getName());
			
		}
		else{
			label.setText("The winner is Hero 1:"+h1.getName());
			
		}
		label.setVisible(true);
		end.add(label);
		add(end);
	}
}
