package javaproject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

//����â
public class Main extends JFrame implements ActionListener {
	
	User user;
	JButton fareManagementB, chargeManagementB, additionalServiceB, roamingB, logout;
	JButton membershipB, homeServiceB, customerCenterB;
	Main(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������ ����
		
		logout = new JButton("�α׾ƿ�");
		logout.setLocation(280, 10);
		logout.setSize(90, 30);
		logout.addActionListener(this);
		add(logout);
		//�α׾ƿ� ��ư
		
		JPanel userP = new JPanel();
		userP.setLayout(new FlowLayout(FlowLayout.CENTER,100,3));
		userP.setBorder((Border) new TitledBorder(null, "ȸ������", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		userP.setBounds(30, 50, 320, 90);
		add(userP);
		//����� �г�

		Font font = new Font("����", Font.BOLD, 13);
		//��Ʈ 
		
		JLabel name = new JLabel(user.name + " ��");
		name.setFont(font);
		userP.add(name);
		//����� �̸� ���
		
		JLabel phone = new JLabel(user.phone);
		phone.setFont(font);
		userP.add(phone);
		//����� �ڵ��� ��ȣ ���
		
		JLabel flat_rate = new JLabel(user.flat_rate);
		flat_rate.setFont(font);
		userP.add(flat_rate);
		//����ڰ� �̿��ϴ� ����� ���
		
		fareManagementB = new JButton("��ݰ���");
		fareManagementB.setLocation(70,180);
		fareManagementB.setSize(120, 40);
		fareManagementB.addActionListener(this);
		add(fareManagementB);
		//��ݰ��� ��ư
		
		chargeManagementB = new JButton("û������");
		chargeManagementB.setLocation(200,180);
		chargeManagementB.setSize(120, 40);
		chargeManagementB.addActionListener(this);
		add(chargeManagementB);
		//û������ ��ư
		
		additionalServiceB = new JButton("�ΰ�����");
		additionalServiceB.setLocation(70,240);
		additionalServiceB.setSize(120, 40);
		additionalServiceB.addActionListener(this);
		add(additionalServiceB);
		//�ΰ����� ��ư
		
		roamingB = new JButton("�ؿܷι�");
		roamingB.setLocation(200,240);
		roamingB.setSize(120, 40);
		roamingB.addActionListener(this);
		add(roamingB);
		//�ؿܷι� ��ư
		
		JPanel mobileP = new JPanel();
		mobileP.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		mobileP.setBorder((Border) new TitledBorder(null, "�����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mobileP.setBounds(30, 150, 320, 150);
		add(mobileP);
		//����� �г�
		
		membershipB = new JButton("�����");
		membershipB.setLocation(50,345);
		membershipB.setSize(90, 40);
		membershipB.addActionListener(this);
		add(membershipB);
		//����� ��ư
		
		homeServiceB = new JButton("Ȩ����");
		homeServiceB.setLocation(150,345);
		homeServiceB.setSize(90, 40);
		homeServiceB.addActionListener(this);
		add(homeServiceB);
		//Ȩ���� ��ư
				
		customerCenterB = new JButton("������");
		customerCenterB.setLocation(250,345);
		customerCenterB.setSize(90, 40);
		customerCenterB.addActionListener(this);
		add(customerCenterB);
		//������ ��ư
		
		JPanel etcServiceP = new JPanel();
		etcServiceP.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		etcServiceP.setBorder((Border) new TitledBorder(null, "��Ÿ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		etcServiceP.setBounds(30, 310, 320, 100);
		add(etcServiceP);
		//��Ÿ���� �г�
		
		setVisible(true); // �������� ȭ�鿡 ���	
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(logout)) //�α׾ƿ� ��ư�� ���� ���
		{
			User user = null;
			
			JLabel msg = new JLabel("������ �α׾ƿ� �Ͻðڽ��ϱ�?");
			msg.setFont(new Font("sanSerif",0,12));
			msg.setForeground(Color.red);
			int n= JOptionPane.showConfirmDialog(this, msg,"�α׾ƿ�", JOptionPane.YES_NO_OPTION);
			
			if(n==0)
			{
				FirstPage firstPage = new FirstPage(user);
				setVisible(false);
				dispose();
			}
		}
		else if (e.getSource().equals(fareManagementB)) //��ݰ��� ��ư�� ���� ���
		{
			FlatRate flatRate = new FlatRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(chargeManagementB)) //û������ ��ư�� ���� ���
		{
			Charge charge = new Charge(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(additionalServiceB)) //�ΰ����� ��ư�� ���� ���
		{
			try {
				additionalService add = new additionalService(user);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(roamingB)) //�ι� ��ư�� ���� ���
		{
			roaming roaming = new roaming(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(membershipB)) //����� ��ư�� ���� ���
		{
			Membership membership = new Membership(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(homeServiceB)) //Ȩ���� ��ư�� ���� ���
		{
			HomeService home = new HomeService(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(customerCenterB)) //������ ��ư�� ���� ���
		{
			CustomerCenter center = new CustomerCenter(user);
			setVisible(false);
		    dispose();
		}
	}
}
