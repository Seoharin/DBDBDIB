package teamproject3;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Searchresult extends JFrame{

	public Searchresult()
	{
		JPanel result = new JPanel();
		super.setSize(1000, 650);
		JPanel nextpanel = new JPanel(new GridLayout(1,3));
        JButton more = new JButton("상세보기");
        JButton close = new JButton("취소");
        
        super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
