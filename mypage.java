package teamproject3;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;

public class mypage extends JFrame{

	public mypage()
	{
        JPanel titlepanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("����������");
        JButton evalBtn = new JButton("�򰡳���");
        
        titlepanel.add(title, BorderLayout.WEST);
        titlepanel.add(evalBtn, BorderLayout.EAST);
        
        setLayout(new GridLayout(11,1));
        
        JPanel namepanel = new JPanel();
        JLabel namelabel = new JLabel("�̸�: ");
        JTextField name = new JTextField(30);
        
        JPanel idpanel = new JPanel();
        JLabel idlabel = new JLabel("ID: ");
        JTextField id = new JTextField(30);
        
        JPanel pwpanel = new JPanel();
        JLabel pwlabel = new JLabel("PW: ");
        JTextField pw = new JTextField(30);
        
        JPanel onemorepwpanel = new JPanel();
        JLabel onemorepwlabel = new JLabel("PW Ȯ��: ");
        JTextField onemorepw = new JTextField(30);
        
        JPanel birthpanel = new JPanel();
        JLabel birthlabel = new JLabel("�������(yyyymmdd): ");
        JTextField birth = new JTextField(30);
        
        JPanel genderpanel = new JPanel();
        JLabel genderlabel = new JLabel("����: ");
        
        CheckboxGroup g = new CheckboxGroup();
        Checkbox w = new Checkbox("��",false, g);
        Checkbox m = new Checkbox("��",false, g);
        
        JPanel jobpanel = new JPanel();
        JLabel joblabel = new JLabel("����: ");
        JTextField job = new JTextField(30);
        
        JPanel addresspanel = new JPanel();
        JLabel addresslabel = new JLabel("�ּ�: ");
        JTextField address = new JTextField(30);
        
        JPanel phonepanel = new JPanel();
        JLabel phonelabel = new JLabel("��ȭ��ȣ: ");
        JTextField phone = new JTextField(30);
        
        JPanel nextpanel = new JPanel(new GridLayout(1,3));
        JButton revise = new JButton("����");
        JButton close = new JButton("�ݱ�");
        JButton getout = new JButton("Ż��");
        
        namepanel.add(namelabel);
        namepanel.add(name);
        
        idpanel.add(idlabel);
        idpanel.add(id);
        
        pwpanel.add(pwlabel);
        pwpanel.add(pw);
        
        onemorepwpanel.add(onemorepwlabel);
        onemorepwpanel.add(onemorepw);
        
        birthpanel.add(birthlabel);
        birthpanel.add(birth);
        
        addresspanel.add(addresslabel);
        addresspanel.add(address);
        
        genderpanel.add(genderlabel);
        genderpanel.add(m);
        genderpanel.add(w);
        
        jobpanel.add(joblabel);
        jobpanel.add(job);
        
        phonepanel.add(phonelabel);
        phonepanel.add(phone);
        
        nextpanel.add(revise);
        nextpanel.add(close);
        nextpanel.add(getout);
        
        add(titlepanel);
        add(namepanel);
        add(idpanel);
        add(pwpanel);
        add(onemorepwpanel);
        add(birthpanel);
        add(addresspanel);
        add(genderpanel);
        add(jobpanel);
        add(phonepanel);
        add(nextpanel);
        
        setVisible(true);
        setSize(1000,650);
        setLocationRelativeTo(null);    
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
