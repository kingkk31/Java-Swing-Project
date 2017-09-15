package javaproject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

//�����ȸ Ŭ����
class InquiryRate extends JFrame implements ActionListener{
	
	User user;
	JButton b1;
	
	InquiryRate(User user)
	{
		this.user = user; //���� Ŭ���� �޾ƿ���
		
		setLayout(null);
		setTitle("�����ȸ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������
	
		JPanel body;
		JLabel title, name, flat_rate, call_volume, data, message;
		
		title = new JLabel("�����ȸ");
		title.setLocation(140, 20);
		title.setSize(100, 20);
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
		flat_rate = new JLabel();
		flat_rate.setFont(bodyFont);
		call_volume = new JLabel();
		call_volume.setFont(bodyFont);
		data = new JLabel();
		data.setFont(bodyFont);
		message = new JLabel();
		message.setFont(bodyFont);
		//��Ʈ����
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//�ش� �����ͺ��̽� ���
			
			ResultSet rs = st.executeQuery("select * from profile where id = '" + user.id + '\'');
			while(rs.next())
			{
				name.setText(rs.getString("name") + "���� �����ȸ ���� ");
				flat_rate.setText("�̿� ����� : " + rs.getString("flat_rate") + "     ");
				call_volume.setText("�ܿ� ���� : " + rs.getString("call_volume") + "��           ");
				data.setText("�ܿ� ������ : " + rs.getString("data") + "MB       ");
				message.setText("�ܿ� �޼��� : " + rs.getString("message") + "��         ");
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
		body.add(flat_rate);
		body.add(call_volume);
		body.add(data);
		body.add(message);
		
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
		if (e.getSource().equals(b1))//�ڷΰ��� ��ư�� ���� ���
		{
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		}
	}
}