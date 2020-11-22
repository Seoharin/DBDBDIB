// ������� ���� ���, �� �������� �Ѿ
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
		JLabel one = new JLabel("���� ����!");
		JLabel two = new JLabel("���� ����ðڽ��ϱ�?");
		
		setLayout(new GridLayout(2,1));
		
		newMemPanel.add(one, BorderLayout.NORTH);
		newMemPanel.add(two, BorderLayout.SOUTH);
		
		JPanel btnPanel = new JPanel(new BorderLayout());
	    JButton yes = new JButton("��");
	    JButton no = new JButton("�ƴϿ�");
	    
	    btnPanel.add(yes, BorderLayout.WEST);
	    btnPanel.add(no, BorderLayout.EAST);
	    
	    add(newMemPanel);
	    add(btnPanel);
	    
	    yes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		dispose();
        		new MakeMembership(id);   // ����� �����
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
