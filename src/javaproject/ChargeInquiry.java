package javaproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ChargeInquiry extends JFrame implements ActionListener{
	User user;
	JButton b1;
	
	ChargeInquiry(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("û����ȸ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������
	
		JLabel title;
		title = new JLabel("û����ȸ");
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

		JPanel body = new JPanel();
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		body.setBackground(Color.white);
		body.setSize(330,350);
		body.setLocation(20, 70);
		//���� ����� �г�
		
		Font bodyFont = new Font("����", Font.PLAIN, 18);
		JLabel userName = new JLabel(user.name + "���� û������");
		userName.setFont(bodyFont);
		body.add(userName);
		
		String tableName[] = {"��¥", "�ݾ�"};
		DefaultTableModel model = new DefaultTableModel(tableName, 0); 
		JTable table = new JTable(model);
		table.setEnabled(false);
		table.getColumnModel().getColumn(0).setMinWidth(150); 
		table.getColumnModel().getColumn(1).setWidth(100);
		table.setRowHeight(30); 
		String arr[] = {"��¥", "�ݾ�"};
		//û������ ��� ���̺�
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(300, 270));
		body.add(scroll);
		//��ũ��

		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//�ش� �����ͺ��̽� ���
			
			ResultSet rs = st.executeQuery("select * from charge_" + user.id + " order by date desc;\n");
			while(rs.next())
			{
				arr[0] = rs.getString("date");
				arr[1] = rs.getString("pay");
				model.addRow(arr);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		//�����ͺ��̽����� û������ �ҷ��� ���̺� �߰�
		
		add(body);
		
		setVisible(true); //�������� ȭ�鿡 ���
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //�ڷΰ��� ��ư ���ý�
		{
			Charge back = new Charge(user);
			setVisible(false);
		    dispose();
		}
	}
}
