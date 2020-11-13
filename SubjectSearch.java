package teamproject3;

import java.awt.*;
import javax.swing.*;

public class SubjectSearch extends JFrame {
	
	public SubjectSearch(String title)
	{
		super(title);
		super.setSize(1000, 650);
		JPanel searchpanel = new JPanel();
		JLabel titlelabel = new JLabel("제목으로 검색하기");
		JTextField inputbox = new JTextField(20);
		JButton searchbtn = new JButton("조회");
		
		searchpanel.add(titlelabel);
		searchpanel.add(inputbox);
		searchpanel.add(searchbtn);
		
		this.add(searchpanel);
		
		
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SubjectSearch("KNU movie");
	}

}
