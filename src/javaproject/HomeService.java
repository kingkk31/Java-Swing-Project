package javaproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Ȩ����
public class HomeService extends JFrame implements ActionListener{
	User user;
	JButton b1, b2, b3;
	
	HomeService(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("Ȩ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������
	
		JLabel title;
		title = new JLabel("Ȩ����");
		title.setLocation(140, 20);
		title.setSize(100, 20);
		title.setFont(new Font("����", Font.BOLD, 22));
		add(title);
		//Ÿ��Ʋ
		
		b1 = new JButton("�ڷΰ���");
		b1.setLocation(280, 10);
		b1.setSize(90, 30);
		b1.addActionListener(this);
		add(b1);
		//�ڷΰ��� ��ư
		
		b2 = new JButton("Ȩ���� ��ȸ");
		b2.setLocation(110, 170);
		b2.setSize(150, 50);
		b2.addActionListener(this);
		add(b2);
		//Ȩ���� ��ȸ ��ư
		 
		b3 = new JButton("Ȩ���� ���/����");
		b3.setLocation(110, 260);
		b3.setSize(150, 50);
		b3.addActionListener(this);
		add(b3);
		//Ȩ���� ���� ��ư
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //�ڷΰ��� ��ư ���� ���
		{
			Main back = new Main(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b2)) //Ȩ���� ��ȸ ��ư ���� ��� Ȩ����  ��ȸ
		{
			HomeInquiry next = new HomeInquiry(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b3)) //Ȩ���� ��ȸ ��ư ���� ��� Ȩ���� ����
		{
			HomeRegistration next = new HomeRegistration(user);
			setVisible(false);
		    dispose();
		}
	}
}
