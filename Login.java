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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class Login extends JFrame {

	
	public Login() {
		// TODO Auto-generated method stub
		JPanel name = new JPanel(); //knu moive를 적을 panel
		JLabel knu_movie = new JLabel("KNU MOVIE"); //knu_movie가 적힌 label
		name.add(knu_movie);	//name panel에 knu_movie label부착
		
		JPanel logpanel = new JPanel(new GridLayout(1,3));
		JPanel idpanel = new JPanel();
		JPanel pwpanel = new JPanel();
		
		JLabel idlabel = new JLabel("ID :");
		JLabel pwlabel = new JLabel("PW : ");
		JButton logbtn = new JButton("Login");
		JTextField idfield = new JTextField(30);
		JPasswordField pwfield = new JPasswordField(30);
		
		JPanel joinpanel = new JPanel(new GridLayout(1,2));
		JButton findbtn = new JButton("Find ID/PW");
		JButton joinbtn = new JButton("Join");
		
		
		idpanel.add(idlabel);
		idpanel.add(idfield);
		
		pwpanel.add(pwlabel);
		pwpanel.add(pwfield);
		
		
		
		logpanel.add(idpanel);
		logpanel.add(pwpanel);
		logpanel.add(logbtn);
		
		joinpanel.add(joinbtn);
		joinpanel.add(findbtn);
		
		GridLayout gl = new GridLayout(3,1);
		setLayout(gl);
		
		add(name);
		add(logpanel);
		add(joinpanel);
		
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		logbtn.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e)
			{
				//DB에 해당 account 가 있으면 
				if()
				{
					if() { //when administer account
						new Administer(id);
					}
					else { //when customer account
						new MovieList(id);
					}
				}
				
				//DB에 해당 ACCOUNT 가 없으면 
				else
				{
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀립니다.");
					idfield.setText("");
					pwfield.setText("");
				}
					
			}
		});
		
		joinbtn.addActionListener(new ActionListener() { //joinbtn 누르면
			public void actionPerformed(ActionEvent e) {
				new Join(); //join창으로 넘어가고
				dispose();  //login창은 닫힘
			}
		});
		
		findbtn.addActionListener(new ActionListener() { //findbtn 누르면
			public void actionPerformed(ActionEvent e) {
				new find(); //find창으로 넘어가고
				dispose();  //login창은 닫힘 
			}
		});
		
	}
	

}
