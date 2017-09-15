package javaproject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

//�����ȸ
class HomeInquiry extends JFrame implements ActionListener{
	
	User user;
	JButton b1;
	
	HomeInquiry(User user)
	{
		this.user = user; //���� Ŭ���� �޾ƿ���
		
		setLayout(null);
		setTitle("Ȩ���� ��ȸ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������
	
		JPanel body;
		JLabel title, name, tv, internet, IoT;
		
		title = new JLabel("Ȩ���� ��ȸ");
		title.setLocation(110, 20);
		title.setSize(160, 20);
		title.setFont(new Font("����", Font.BOLD, 22));
		add(title);
		//Ÿ��Ʋ
		
		body = new JPanel();
		body.setBackground(Color.white);
		body.setSize(330,350);
		body.setLocation(20, 70);
		//���� �ٵ� �κ�
		
		Font bodyFont = new Font("����", Font.PLAIN, 18);
		name = new JLabel();
		name.setFont(bodyFont);
		name.setText(user.name + "���� Ȩ���� ��ȸ ���� ");
		//������� Ȩ���� ��ȸ���� ���
		
		tv = new JLabel();
		tv.setFont(bodyFont);
		internet = new JLabel();
		internet.setFont(bodyFont);
		IoT = new JLabel();
		IoT.setFont(bodyFont);
		//���� ������ ���� �󺧵� ����
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//�ش� �����ͺ��̽� ���
			
			ResultSet rs = st.executeQuery("select * from homeService where id = '" + user.id + '\'');
			while(rs.next())
			{
				if(rs.getString("tv") == null)
					tv.setText("TV : ����                                                                ");
				else
					tv.setText("TV : " + rs.getString("tv") + "                            ");
				   
				if(rs.getString("internet") == null)
					internet.setText("���ͳ� : ����                                                                    ");
				else
					internet.setText("���ͳ� : " + rs.getString("internet") + "                               ");
				
				if(rs.getString("IoT") == null)
					IoT.setText("IoT : ����");
				else
					IoT.setText("IoT : " + rs.getString("IoT"));
			}
			//����� �󺧿� �Է�	
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		body.setLayout(new FlowLayout(FlowLayout.LEFT));
		body.add(name);
		body.add(tv);
		body.add(internet);
		body.add(IoT);
		
		add(body);
		//���ιٵ� ��ȸ��� �ֱ�
		
		b1 = new JButton("�ڷΰ���");
		b1.setLocation(280, 10);
		b1.setSize(90, 30);
		b1.addActionListener(this);
		add(b1);
		//�ڷΰ��� ��ư
			
		setVisible(true); //�������� ȭ�鿡 ���
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) // �ڷΰ��� ��ư ���� ���
		{
			HomeService back = new HomeService(user);
			setVisible(false);
		    dispose();
		}
	}
}