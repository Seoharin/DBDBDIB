package teamproject3;

import java.awt.*;
import javax.swing.*;

public class OptionSearch extends JFrame {

	public OptionSearch(String title)
	{
		super(title);
		super.setSize(1000, 650);
		
		JPanel opsearchpanel = new JPanel(new BorderLayout()); //전체 패널(옵션패널+버튼 담음)
		JPanel optionpanel = new JPanel(); //옵션패널 담는 패널
		JPanel typepanel = new JPanel(new GridLayout(4,1)); //타입옵션들 있는 패널
		JPanel genrepanel = new JPanel(new GridLayout(11, 1)); //장르옵션들 있는 패널
		JPanel versionpanel = new JPanel(new GridLayout(7,1)); //버전옵션들 있는 패널
		
		JLabel typelabel = new JLabel("TYPE");
		JLabel genrelabel = new JLabel("GENRE");
		JLabel versionlabel = new JLabel("VERSION");
		
		JButton optionsearchbtn = new JButton("조회");

		JCheckBox type1 = new JCheckBox("Movie", false);
		JCheckBox type2 = new JCheckBox("Drama", false);
		JCheckBox type3 = new JCheckBox("Knu MovieDB Original", false);
		
		JCheckBox genre1 = new JCheckBox("Horror", false);
		JCheckBox genre2 = new JCheckBox("Thriller", false);
		JCheckBox genre3 = new JCheckBox("Sci-Fi", false);
		JCheckBox genre4 = new JCheckBox("Crime", false);
		JCheckBox genre5 = new JCheckBox("Drama", false);
		JCheckBox genre6 = new JCheckBox("Fantasy", false);
		JCheckBox genre7 = new JCheckBox("Animation", false);
		JCheckBox genre8 = new JCheckBox("Comedy", false);
		JCheckBox genre9 = new JCheckBox("Romance", false);
		JCheckBox genre10 = new JCheckBox("Action", false);
		
		JCheckBox version1 = new JCheckBox("Korean", false);
		JCheckBox version2 = new JCheckBox("English(US)", false);
		JCheckBox version3 = new JCheckBox("English(UK)", false);
		JCheckBox version4 = new JCheckBox("Japanese", false);
		JCheckBox version5 = new JCheckBox("Chinese", false);
		JCheckBox version6 = new JCheckBox("French", false);
		
		typepanel.add(typelabel);
		typepanel.add(type1);
		typepanel.add(type2);
		typepanel.add(type3);
		
		genrepanel.add(genrelabel);
		genrepanel.add(genre1);
		genrepanel.add(genre2);
		genrepanel.add(genre3);
		genrepanel.add(genre4);
		genrepanel.add(genre5);
		genrepanel.add(genre6);
		genrepanel.add(genre7);
		genrepanel.add(genre8);
		genrepanel.add(genre9);
		genrepanel.add(genre10);
		
		versionpanel.add(versionlabel);
		versionpanel.add(version1);
		versionpanel.add(version2);
		versionpanel.add(version3);
		versionpanel.add(version4);
		versionpanel.add(version5);
		versionpanel.add(version6);

		optionpanel.add(typepanel);
		optionpanel.add(genrepanel);
		optionpanel.add(versionpanel);
		opsearchpanel.add(optionpanel, "Center");
		opsearchpanel.add(optionsearchbtn, "South");
		this.add(opsearchpanel);
		
		
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OptionSearch("KNU movie");
	}

}
