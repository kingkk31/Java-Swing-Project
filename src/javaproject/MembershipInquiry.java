package javaproject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//����� ��ȸ Ȯ��
public class MembershipInquiry extends JFrame implements ActionListener{

	User user;
	JButton b1;
	
	MembershipInquiry(User user)
	{
		this.user = user; //���� Ŭ���� �޾ƿ���
		
		setLayout(null);
		setTitle("����� ��ȸ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������
	
		JLabel title;
		title = new JLabel("����� ��ȸ");
		title.setLocation(120, 20);
		title.setSize(140, 20);
		title.setFont(new Font("����", Font.BOLD, 22));
		add(title);
		//Ÿ��Ʋ
		
		b1 = new JButton("�ڷΰ���");
		b1.setLocation(280, 10);
		b1.setSize(90, 30);
		b1.addActionListener(this);
		add(b1);
		//�ڷΰ��� ��ư
			
		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.LEFT,20,15));
		pn1.setBackground(Color.white);
		pn1.setBounds(20, 70, 330, 100);
		add(pn1);
		//����� ������ ��Ÿ�� �г�
		
		Font font = new Font("����", Font.PLAIN, 18);
		JLabel rank = new JLabel(); //���
		rank.setFont(font);
		JLabel point = new JLabel(); //����Ʈ
		point.setFont(font);
		//�гξȿ� ������ ��
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//�ش� �����ͺ��̽� ���
			
			ResultSet rs = st.executeQuery("select * from membership where id = '" + user.id + '\'');
			while(rs.next())
			{
				rank.setText(user.name + "���� ����� ��� : " + rs.getString("rank"));
				point.setText("�ܿ� ����� ����Ʈ : " + rs.getString("point") + " ��");
			}
			//������� ����� ����� �޾ƿ´�	
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
		
		pn1.add(rank); 
		pn1.add(point); 
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn2.setBorder((Border) new TitledBorder(null, "����� ��� �� �ѵ�", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(20, 190, 330, 240);
		add(pn2);
		//����� ���� �г�
		
		String tableName[] = {"���", "����� ������", "����Ʈ �ѵ�"};
		DefaultTableModel model = new DefaultTableModel(tableName, 0); 
		JTable table = new JTable(model);
		table.setEnabled(false);
		table.getColumnModel().getColumn(0).setMinWidth(100); 
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.setRowHeight(40); 
		String arr[] = {"���", "����� ������", "����Ʈ �ѵ�"};
		model.addRow(arr);
		//����� ���� ��� ���̺�
		
		arr[0] = "VIP"; arr[1] = "100000�� �̻�"; arr[2] = "120000��";	model.addRow(arr);	
		arr[0] = "Gold"; arr[1] = "72000�� �̻�"; arr[2] = "100000��";	model.addRow(arr);	
		arr[0] = "Silver"; arr[1] = "52000�� �̻�"; arr[2] = "80000��";	model.addRow(arr);	
		arr[0] = "Bronze"; arr[1] = "34000�� �̻�"; arr[2] = "60000��";	model.addRow(arr);	
		//���ذ����� ����
		
		pn2.add(table);
		
		setVisible(true); //�������� ȭ�鿡 ���
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //�ڷΰ����ư�� ������ ���
		{
			Membership back = new Membership(user);
			setVisible(false);
		    dispose();
		}
	}

}
