package javaproject;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//�ι� â
public class roaming extends JFrame implements ActionListener {
	User user;
	JButton backB, roamingChangeB, roamingGuideB;
	JLabel roaming, bRoaming;
	roaming(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("�ι�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setResizable(true); //������ ũ�� ����
		// ��ü ������ ����

		roaming = new JLabel("ROAMING");
		roaming.setFont(new Font("Snap ITC", Font.PLAIN, 35));
		roaming.setBounds(90, 60, 400, 200); 
		add(roaming);
		// roaming �۾�
		
		bRoaming = new JLabel("ROAMING");
		bRoaming.setFont(new Font("Snap ITC", Font.PLAIN, 35));
		bRoaming.setForeground(new Color(204, 204, 204));
		bRoaming.setBounds(90, 70, 400, 200); 
		add(bRoaming);
		// ���� �ϳ��� ����-�׸���ȿ��
		
		backB = new JButton("�ڷΰ���");
		backB.setLocation(240, 30);
		backB.setSize(100, 20);
		backB.addActionListener(this);
		add(backB);
		// �ڷΰ���

		roamingGuideB = new JButton("�ι־ȳ�");
		roamingGuideB.setLocation(60,280);
		roamingGuideB.setSize(120, 40);
		roamingGuideB.addActionListener(this);
		add(roamingGuideB);
		//�ι� �ȳ��� ���� ��ư
		
		roamingChangeB = new JButton("�ι� ��ȸ");
		roamingChangeB.setLocation(210,280);
		roamingChangeB.setSize(120, 40);
		roamingChangeB.addActionListener(this);
		add(roamingChangeB);
		//������ �ι� ��ȸ, ��û, ������ ���� ��ư
		
		setVisible(true); // �������� ȭ�鿡 ���
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(backB)) // �ڷΰ��� ��ư ������ ��
		{
			Main main = new Main(user); 
			setVisible(false);
			dispose();
		}
		else if(e.getSource().equals(roamingGuideB)) // �ι־ȳ� ��ư ������ ��
		{
			try {
				roamingGuide guide = new roamingGuide(user);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			setVisible(false);
			dispose();
		}
		else if(e.getSource().equals(roamingChangeB)) //�ι� ��ȸ ��ư ������ ��
		{
			foreignCountryRoaming roaming = new foreignCountryRoaming(user);
			setVisible(false);
			dispose();
		}
	}
}
