package javaproject;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//�ι־ȳ�
public class roamingGuide extends JFrame implements ActionListener{
	User user;
	JButton backB;
	JPanel body;
	JTextArea text;
	
	roamingGuide(User user) throws IOException {
		
		this.user = user;
		
		setLayout(null);
		setTitle("�ι�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setResizable(true); // ������ ũ�� ����
		// ��ü ������ ����

		JLabel title;
		title = new JLabel("�ι־ȳ�");
		title.setLocation(140, 20);
		title.setSize(100, 20);
		title.setFont(new Font("����", Font.BOLD, 22));
		add(title);
		//Ÿ��Ʋ
				
		backB = new JButton("�ڷΰ���");
		backB.setLocation(280, 10);
		backB.setSize(90, 30);
		backB.addActionListener(this);
		add(backB);
		//�ڷΰ��� ��ư
		
		body = new JPanel();
		body.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		body.setSize(378,410);
		body.setLocation(0,50);
		//���� ����� �г�
		
		text = new JTextArea(14,25);
		text.setFont(new Font("����", Font.PLAIN, 17));
		print(text);
		text.setEditable(false);
		text.setCaretPosition(0);
		text.setLineWrap(true);
		//�۾�����ϴ� �κ�
		
		JScrollPane scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		body.add(scroll);
		//��ũ��
		
		add(body);
		setVisible(true);// ȭ�鿡 ���
	}
	
	public void print(JTextArea text) throws IOException { // �ι־ȳ��� ��� ���� �б�
		BufferedReader br = new BufferedReader(new FileReader("�ι־ȳ�.txt"));
		String temp;
		while ((temp = br.readLine()) != null) // ���� ���� �о�帮�� �κ�
			text.append(temp + "\n");
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(backB)) // �ڷΰ��� ��ư ������ ��
		{
			roaming roaming = new roaming(user);
			setVisible(false);
			dispose();
		}
	}
}
