// 멤버십이 없는 경우, 이 페이지로 넘어감
package teamJDBC;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class No_membershipPage extends JFrame{

	public No_membershipPage(String id)
	{
		JPanel newMemPanel = new JPanel(new BorderLayout());
		JLabel one = new JLabel("현재 없음!");
		JLabel two = new JLabel("새로 만드시겠습니까?");
		
		setLayout(new GridLayout(2,1));
		
		newMemPanel.add(one, BorderLayout.NORTH);
		newMemPanel.add(two, BorderLayout.SOUTH);
		
		JPanel btnPanel = new JPanel(new BorderLayout());
	    JButton yes = new JButton("예");
	    JButton no = new JButton("아니오");
	    
	    btnPanel.add(yes, BorderLayout.WEST);
	    btnPanel.add(no, BorderLayout.EAST);
	    
	    add(newMemPanel);
	    add(btnPanel);
	    
	    yes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		dispose();
        		new MakeMembership(id);   // 멤버십 만들기
        	}
        });
	    
	    no.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		dispose();
        		new Mypage_real(id);
        	}
        });
	    
	    setVisible(true);
	    setSize(500,350);
	    setLocationRelativeTo(null);    
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	}
}
