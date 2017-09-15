package javaproject;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

//�ΰ�����
public class additionalService extends JFrame implements ActionListener {
	User user;
	JButton backB, myServiceCheckB, logoutB;
	JTextArea text;
	additionalService(User user) throws IOException
	{
		this.user =user;
		setLayout(null);
		setTitle("�ΰ�����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setResizable(false);
		// ��ü ������ ����

		myServiceCheckB = new JButton("�̿����� �ΰ����� Ȯ�� & �ΰ����� ��û");
		myServiceCheckB.setLocation(45,415);
		myServiceCheckB.setSize(300,25);
		add(myServiceCheckB);
		myServiceCheckB.addActionListener(this);
		//�̿����� �ΰ����� Ȯ�� ��ư
		
		JPanel additionalServiceP = new JPanel();
		additionalServiceP.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		additionalServiceP.setSize(345,280);
		additionalServiceP.setLocation(20,120);
		//�ΰ����� ��������� �г�
		
		text = new JTextArea(11,25);
		text.setFont(new Font("����", Font.PLAIN, 15));
		print(text);
		text.setEditable(false);
		text.setCaretPosition(0);
		text.setLineWrap(true);
		
		JScrollPane scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		additionalServiceP.add(scroll);
		//��ũ��
		
		add(additionalServiceP);
		
		backB = new JButton("�ڷΰ���");
		backB.setLocation(260,35);
		backB.setSize(90,20);
		add(backB);
		backB.addActionListener(this);
		//�ڷΰ��� ��ư
		
		JLabel info = new JLabel("��������, ������ȭ �������� ���񽺴� �����̸�");
		info.setLocation(40, 60);
		info.setSize(300, 20);
		add(info);
		info=new JLabel("�⺻���� ��û�Ǿ��ֽ��ϴ�.");
		info.setLocation(40, 80);
		info.setSize(300, 20);
		add(info);
		//�⺻����
		
		JPanel memberP = new JPanel();
		memberP.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		memberP.setBorder((Border) new TitledBorder(null, "�⺻����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		memberP.setBounds(20, 20, 345, 100);
		add(memberP);
		//�⺻�ΰ����� ����
		
		setVisible(true); // �������� ȭ�鿡 ���
	}
	public void print(JTextArea text) throws IOException { // �ΰ����� ������ ��� ���� �б�
		BufferedReader br = new BufferedReader(new FileReader("�ΰ���������.txt"));
		String temp;
		while ((temp = br.readLine()) != null) // ���� ���� �о�帮�� �κ�
			text.append(temp + "\n");
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(backB)) // �ڷΰ��� ��ư ������ ��
		{
			Main main = new Main(user);
			setVisible(false);
			dispose();
		}
		else if(e.getSource().equals(myServiceCheckB)) // �̿����� �ΰ����� ��ư ������ ��
		{
			myServiceCheck check = new myServiceCheck(user);
			setVisible(false);
			dispose();
		}
	}
}