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
public class find extends JFrame{

	public find ()
	{
		JPanel idpanel = new JPanel(new GridLayout(1,3));

		JPanel id_name_panel = new JPanel();
		JLabel id_name_label = new JLabel("이름: ");
		JTextField id_name_field = new JTextField(30);
		id_name_panel.add(id_name_label);
		id_name_panel.add(id_name_field);
		
		JPanel id_phone_panel = new JPanel();
		JLabel id_phone_lanbel = new JLabel("전화번호 :");
		JTextField id_phone_field = new JTextField(30);
		id_phone_panel.add(id_phone_lanbel);
		id_phone_panel.add(id_phone_field);
		
		JButton findidbtn = new JButton("ID 찾기");
		
		idpanel.add(id_name_panel);
		idpanel.add(id_phone_panel);
		idpanel.add(findidbtn);
		
		
		JPanel pwpanel = new JPanel(new GridLayout(1,3));

		JPanel pw_name_panel = new JPanel();
		JLabel pw_name_label = new JLabel("이름: ");
		JTextField pw_name_field = new JTextField(30);
		pw_name_panel.add(pw_name_label);
		pw_name_panel.add(pw_name_field);
		
		JPanel pw_id_panel = new JPanel();
		JLabel pw_id_lanbel = new JLabel("ID :");
		JTextField pw_id_field = new JTextField(30);
		pw_id_panel.add(pw_id_lanbel);
		pw_id_panel.add(pw_id_field);
		
		JButton findpwbtn = new JButton("PW 찾기");
		
		pwpanel.add(pw_name_panel);
		pwpanel.add(pw_id_panel);
		pwpanel.add(findpwbtn);
		
		GridLayout gl = new GridLayout(2,1);
		setLayout(gl);

		add(idpanel);
		add(pwpanel);
		
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		findidbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 	//id찾기 버튼 눌렀을 때
				if() {	//일치하는 정보가 있을 떄
					
				}
				else {	//일치하는 정보가 없을 떄
					JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
					id_name_field.setText("");
					id_phone_field.setText("");
				}
				
			}
		});
		
		
		findpwbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{	//pw찾기 버튼 눌렀을 때
				if() { //일치하는 정보가 있을 때
					
				}
				else { //일치하는 정보가 없을 때
					JOptionPane.showMessageDialog(null,"일치하는 정보가 없습니다.");
					pw_name_field.setText("");
					pw_id_field.setText("");
					
				}
			
			}

			});
		
	}
}
