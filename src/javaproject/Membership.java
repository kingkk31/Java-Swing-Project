package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//�����
public class Membership extends JFrame implements ActionListener{

	User user;
	JButton b1, b2, b3;
	
	Membership(User user)
	{
		this.user = user; //���� Ŭ���� �޾ƿ���
		
		setLayout(null);
		setTitle("�����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������
	
		JLabel title;
		title = new JLabel("�����");
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
		
		b2 = new JButton("����� ��ȸ");
		b2.setLocation(40, 160);
		b2.setSize(140, 50);
		b2.addActionListener(this);
		add(b2);
		//�������ȸ ��ư
		
		b3 = new JButton("�ɹ��� ���޻�");
		b3.setLocation(200, 160);
		b3.setSize(140, 50);
		b3.addActionListener(this);
		add(b3);
		//����� ���޻� ��ư
		
		setVisible(true); //�������� ȭ�鿡 ���
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //�ڷΰ��� ��ư�� ������ ���
		{
			Main back = new Main(user);
			setVisible(false);
		    dispose();
		}
		if (e.getSource().equals(b2)) //����� ��ȸ ��ư�� ������ ���
		{
			MembershipInquiry back = new MembershipInquiry(user);
			setVisible(false);
		    dispose();
		}
		if (e.getSource().equals(b3)) //����� ���޻��ư�� ������ ���
		{
			MembershipAffiliate back = new MembershipAffiliate(user);
			setVisible(false);
		    dispose();
		}
	}

}
