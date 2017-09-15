package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//ó�� â Ŭ����
public class FirstPage extends JFrame implements ActionListener{
	User user;
	JButton newMemberB, loginB;
	JLabel mobileTelecom, bMobileTelecom;
	FirstPage(User user)
	{
		this.user=user;
		
		setLayout(null);
		setTitle("ȯ���մϴ�.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������ ����
		
		mobileTelecom = new JLabel("�ѽ� �����");
		mobileTelecom.setFont(new Font("HY����M", Font.PLAIN, 35));
		mobileTelecom.setBounds(90, 60, 400, 200); 
		add(mobileTelecom);
		// roaming �۾�
		
		bMobileTelecom = new JLabel("�ѽ� �����");
		bMobileTelecom.setFont(new Font("HY����M", Font.PLAIN, 35));
		bMobileTelecom.setForeground(new Color(204, 204, 204));
		bMobileTelecom.setBounds(90, 65, 400, 200); 
		add(bMobileTelecom);
		// ���� �ϳ��� ����-�׸���ȿ��
		
		newMemberB = new JButton("ȸ������");
		newMemberB.setLocation(60,280);
		newMemberB.setSize(120, 40);
		newMemberB.addActionListener(this);
		add(newMemberB);
		//ȸ������
		
		loginB = new JButton("�α���");
		loginB.setLocation(210,280);
		loginB.setSize(120, 40);
		loginB.addActionListener(this);
		add(loginB);
		//�α���
		
		setVisible(true); // �������� ȭ�鿡 ���
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(newMemberB)) // ȸ������ ��ư ������ ��
		{
			newMember newMember = new newMember(user);
			setVisible(false);
			dispose();
		}
		else if(e.getSource().equals(loginB)) //�α��� ��ư ������ ��
		{
			Login login = new Login(user);
			setVisible(false);
			dispose();
		}
	}
	public static void main(String args[]){
		User user = null;
		FirstPage first = new FirstPage(user);
	}
}
