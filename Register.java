package teamproject3;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Register extends JFrame{
	
	public Register(String title)
	{
		super(title);
		super.setSize(1000, 650);
		
		JPanel registerPanel = new JPanel(new BorderLayout());
		JPanel P = new JPanel(new BorderLayout());
		JPanel P1 = new JPanel(new GridLayout(12,1));
		JPanel P2 = new JPanel(new GridLayout(12,1));
		JPanel btnPanel = new JPanel();
		
		JLabel registerlabel = new JLabel("등록하기");
		JLabel titlelabel = new JLabel("제목");
		JLabel isAdultlabel = new JLabel("청소년 관람불가");
		JLabel runtimelabel = new JLabel("상영 시간");
		JLabel ostlabel = new JLabel("OST");
		JLabel audiencelabel = new JLabel("관객 수"); 
		JLabel hascliplabel = new JLabel("클립 유무");
		JLabel directorlabel = new JLabel("감독");
		JLabel writerlabel = new JLabel("작가");
		JLabel startlabel = new JLabel("개봉일");
		JLabel endyearlabel = new JLabel("상영 종료일");
		JLabel typelabel = new JLabel("종류");
		
		JTextField titlefield = new JTextField(255);
		JCheckBox isAdultcheck = new JCheckBox("청소년 관람불가",false);
		JTextField runtimefield = new JTextField(255);
		JTextField ostfield = new JTextField(255);
		JTextField audiencefield = new JTextField(255);
		JCheckBox hasclipcheck = new JCheckBox("클립 있음", false);
		JTextField directorfield = new JTextField(255);
		JTextField writerfield = new JTextField(255);
		JTextField startfield = new JTextField("mm-dd-yyyy", 255);
		JTextField endfield = new JTextField("mm-dd-yyyy", 255);
		String[] typelist = {"Movie", "Drama", "Knu MovieDB original"};
		JComboBox<String> typecombo = new JComboBox<String>(typelist);
		
		JButton registerbtn = new JButton("등록");
		JButton cancelbtn = new JButton("취소");
		
		P1.add(registerlabel);
		P1.add(titlelabel);
		P1.add(isAdultlabel);
		P1.add(runtimelabel);
		P1.add(ostlabel);
		P1.add(audiencelabel);
		P1.add(hascliplabel);
		P1.add(directorlabel);
		P1.add(writerlabel);
		P1.add(startlabel);
		P1.add(endyearlabel);
		P1.add(typelabel);
		
		P2.add(titlefield);
		P2.add(isAdultcheck);
		P2.add(runtimefield);
		P2.add(ostfield);
		P2.add(audiencefield);
		P2.add(hasclipcheck);
		P2.add(directorfield);
		P2.add(writerfield);
		P2.add(startfield);
		P2.add(endfield);
		P2.add(typecombo);
		
		
		
		btnPanel.add(registerbtn);
		btnPanel.add(cancelbtn);
		
		
		P.add(P1, "West");
		P.add(P2, "Center");
		
		registerPanel.add(registerlabel, "North");
		registerPanel.add(P, "Center");
		registerPanel.add(btnPanel, "South");
		
		this.add(registerPanel);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton)e.getSource();
		
		if (b.getText().equals("취소"))
		{
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Register("KNU movie");
	}

}
