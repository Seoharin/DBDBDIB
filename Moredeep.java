package teamproject3;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Moredeep extends JFrame{

	public Moredeep()
	{
		JPanel titlepanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("겨울왕국(여기에이러케쓱이)");
        titlepanel.add(title, BorderLayout.CENTER);
        
        setLayout(new GridLayout(6,1));
        
        JPanel firstpanel = new JPanel(new BorderLayout());
        JLabel titlelabel = new JLabel("제목");
        JLabel movieyearlabel = new JLabel("상영년도");
        
        JPanel secondpanel = new JPanel(new BorderLayout());
        JLabel typelabel = new JLabel("종류");
        JLabel genrelabel = new JLabel("장르");
        
        Choice c = new Choice();
        JPanel thirdpanel = new JPanel(new BorderLayout());
        JLabel playtimelabel = new JLabel("재생시간");
        JLabel evaluatelabel = new JLabel("평가");
        
        JTextField answerText = new JTextField(5);
        thirdpanel.add(answerText);
        
        thirdpanel.add(c);
        
        String s1 = "1";
        String s2 = "2";
        String s3 = "3";
        String s4 = "4";
        String s5 = "5";
        String s6 = "6";
        String s7 = "7";
        String s8 = "8";
        String s9 = "9";
        String s10 = "10";
        
        c.add(s1);
        c.add(s2);
        c.add(s3);
        c.add(s4);
        c.add(s5);
        c.add(s6);
        c.add(s7);
        c.add(s8);
        c.add(s9);
        c.add(s10);
        
        JPanel fourthpanel = new JPanel(new BorderLayout());
        JLabel avrlabel = new JLabel("평점");
        
        JPanel fifthpanel = new JPanel(new BorderLayout());
        JButton closeBtn = new JButton("닫기");
        fifthpanel.add(closeBtn, BorderLayout.CENTER);
        
        firstpanel.add(titlelabel, BorderLayout.WEST);
        firstpanel.add(movieyearlabel, BorderLayout.EAST);
        
        secondpanel.add(typelabel, BorderLayout.WEST);
        secondpanel.add(genrelabel, BorderLayout.EAST);
        
        thirdpanel.add(playtimelabel, BorderLayout.WEST);
        thirdpanel.add(evaluatelabel, BorderLayout.EAST);
        
        fourthpanel.add(avrlabel, BorderLayout.WEST);
        
        fifthpanel.add(closeBtn, BorderLayout.CENTER);
        
        add(titlepanel);
        add(firstpanel);
        add(secondpanel);
        add(thirdpanel);
        add(fourthpanel);
        add(fifthpanel);
        
        closeBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		dispose();
        		new Searchresult();
        	}
        });
        
        setVisible(true);
        setSize(1000,650);
        setLocationRelativeTo(null);    
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
