package dbdbdib;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class MovieList extends JFrame{
	
	public MovieList(String id) {
		// TODO Auto-generated method stub
		
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("MENU");
		
		JMenuItem search_name = new JMenuItem("제목으로 검색");
		JMenuItem search_condition = new JMenuItem("조건으로 검색");
		JMenuItem go_mypage = new JMenuItem("My page");
		JMenuItem logout = new JMenuItem("Logout");
		
		menu.add(search_name);
		menu.add(search_condition);
		menu.add(go_mypage);
		menu.add(logout);
		
		mb.add(menu);
		
		JLabel show = new JLabel(":::MOVIE 목록:::");
		JTextArea showmovie = new JTextArea();
		JScrollPane sp = new JScrollPane(showmovie);
		
		
		setJMenuBar(mb);
		setLayout(new BorderLayout());
		add(show,BorderLayout.NORTH);
		add(sp,BorderLayout.CENTER);
		
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		search_name.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{	//이름으로 찾기
				
			}
		});
		
		search_condition.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{	//조건으로 찾기
				
			}
		});
		
		go_mypage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{	//마이페이지
				
			}
		});
		
		logout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{	//로그아웃
				dispose();		
				new Login(); //다시 로그인창으로 돌아감
			}
		});
		
		
	} 

}
