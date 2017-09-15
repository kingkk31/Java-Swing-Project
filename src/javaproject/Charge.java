package javaproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Charge extends JFrame implements ActionListener{
	User user;
	JButton b1, b2, b3;
	
	Charge(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("û������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������
	
		JLabel title;
		title = new JLabel("û������");
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
		
		b2 = new JButton("û����ȸ");
		b2.setLocation(40, 160);
		b2.setSize(140, 50);
		b2.addActionListener(this);
		add(b2);
		//û����ȸ ��ư
		
		b3 = new JButton("û������");
		b3.setLocation(200, 160);
		b3.setSize(140, 50);
		b3.addActionListener(this);
		add(b3);
		//û������ ��ư
		
		setVisible(true); //ȭ���� �����ӿ� ���
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //�ڷΰ��� ��ư ���ý�
		{
			Main back = new Main(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b2)) //û����ȸ ��ư ���ý�
		{
			ChargeInquiry next = new ChargeInquiry(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b3)) //û������ ��ư ���ý�
		{
			ChangeCharge next = new ChangeCharge(user);
			setVisible(false);
		    dispose();
		}
	}
}
