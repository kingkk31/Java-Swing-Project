package javaproject;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

//�̿����� �ΰ����� Ȯ��
public class myServiceCheck  extends JFrame implements ActionListener {
	User user;
	JButton applyServiceB, cancelServiceB , backB;
	JTextField currentService;
	JCheckBox[] checkbox = new JCheckBox[6];
	Checkbox basicPack, bestPack, studyPack, smartGuard, kakaoPack, musicPack;
	JTable table1, table2;
	DefaultTableModel model1, model2;
	
	myServiceCheck(User user)
	{
		this.user =user;
		setLayout(null);
		setTitle("�̿����� �ΰ����� Ȯ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setResizable(false);
		// ��ü ������ ����
		
		backB = new JButton("�ڷΰ���");
		backB.setLocation(280, 10);
		backB.setSize(90, 30);
		backB.addActionListener(this);
		add(backB);
		//�ڷΰ��� ��ư
				
		JLabel checkT = new JLabel("�̿����� �ΰ�����");
		checkT.setLocation(100, 40);
		checkT.setSize(250, 20);
		checkT.setFont(new Font("����", Font.BOLD, 18));
		add(checkT);
		//Ÿ��Ʋ
				
		JPanel currentServiceP = new JPanel();
		currentServiceP.setLayout(new FlowLayout(FlowLayout.CENTER));
		currentServiceP.setBorder((Border) new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		currentServiceP.setBounds(20,80,345,145);
		add(currentServiceP);
		//�̿����� �ΰ����� �г�
					
		String arr1[] = {"�⺻��", "��¥��", "���͵���"};
		String arr2[] = {"����Ʈ��Ŵ��", "īī����", "������"};
		model1= new DefaultTableModel(arr1,0);
		model2= new DefaultTableModel(arr2,0);
		table1 = new JTable(model1);
		table2 = new JTable(model2);
		table1.setEnabled(false);
		table2.setEnabled(false);
		table1.getColumnModel().getColumn(0).setMinWidth(55);
		table1.getColumnModel().getColumn(1).setMinWidth(55);
		table1.getColumnModel().getColumn(2).setMinWidth(55);
		table2.getColumnModel().getColumn(0).setMinWidth(55);
		table2.getColumnModel().getColumn(1).setMinWidth(55);
		table2.getColumnModel().getColumn(2).setMinWidth(55);
		table1.setRowHeight(30);
		table2.setRowHeight(30);
		//�̿����� �ΰ����� ��� ���̺�
		
		JScrollPane scroll1 = new JScrollPane(table1);
		scroll1.setPreferredSize(new Dimension(340,65));
		currentServiceP.add(scroll1);
		JScrollPane scroll2 = new JScrollPane(table2);
		scroll2.setPreferredSize(new Dimension(340,65));
		currentServiceP.add(scroll2);
		//��ũ��
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//�ش� �����ͺ��̽� ���
			
			ResultSet rs = st.executeQuery("select * from additional where id = '" + user.id + "'");
			while(rs.next()) //��� �ִ� �κ� �б�
			{
				arr1[0] = (rs.getInt("basic") == 0) ? "�̽�û" : "��û"; //üũ �� ��쿡 ��û, üũ �ȵ� ��� �̽�û
				arr1[1] = (rs.getInt("best") == 0) ? "�̽�û" : "��û";
				arr1[2] = (rs.getInt("study") == 0) ? "�̽�û" : "��û";
				arr2[0] = (rs.getInt("smart") == 0) ? "�̽�û" : "��û";
				arr2[1] = (rs.getInt("kakao") == 0) ? "�̽�û" : "��û";
				arr2[2] = (rs.getInt("music") == 0) ? "�̽�û" : "��û";
				model1.addRow(arr1);
				model2.addRow(arr2);
			}
			//���̺� ���� ���
			rs.close();
			con.close();
			//�����ͺ��̽����� �ι������� �о�´�
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
		
		JLabel applyT;
		applyT = new JLabel("�ΰ����� ��û");
		applyT.setLocation(120, 250);
		applyT.setSize(250, 20);
		applyT.setFont(new Font("����", Font.BOLD, 18));
		add(applyT);
		//Ÿ��Ʋ
		
		applyServiceB = new JButton("��û(����)");
		applyServiceB.setLocation(250,320);
		applyServiceB.setSize(100,40);
		add(applyServiceB);
		applyServiceB.addActionListener(this);
		//��û(����) ��ư
		
		cancelServiceB = new JButton("����");
		cancelServiceB.setLocation(250,370);
		cancelServiceB.setSize(100,40);
		add(cancelServiceB);
		cancelServiceB.addActionListener(this);
		//���� ��ư
		
		checkbox[0] = new JCheckBox("�⺻��");
		checkbox[0].setBounds(40,320,80,20);
		checkbox[1] = new JCheckBox("��¥��");
		checkbox[1].setBounds(40,355,80,20);
		checkbox[2] = new JCheckBox("���͵���");
		checkbox[2].setBounds(40,390,80,20);
		checkbox[3] = new JCheckBox("����Ʈ ��Ŵ��");
		checkbox[3].setBounds(130,320,120,20);
		checkbox[4] = new JCheckBox("īī����");
		checkbox[4].setBounds(130,355,100,20);
		checkbox[5] = new JCheckBox("������");
		checkbox[5].setBounds(130,390,80,20);
		add(checkbox[0]);
		add(checkbox[1]);
		add(checkbox[2]);
		add(checkbox[3]);
		add(checkbox[4]);
		add(checkbox[5]);
		// üũ�ڽ��� �� �ΰ����񽺵� üũ�� �� �ְ� ��
		
		JPanel serviceCheckCancelP = new JPanel();
		serviceCheckCancelP.setLayout(new FlowLayout(FlowLayout.CENTER));
		serviceCheckCancelP.setBorder((Border) new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		serviceCheckCancelP.setBounds(20,290,345,145);
		add(serviceCheckCancelP);
		//�̿����� �ΰ����� �г�
	
		setVisible(true); // �������� ȭ�鿡 ���
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource().equals(backB)) //�ڷΰ����ư�� ���� ���
		{
			try {
				additionalService back = new additionalService(user);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			setVisible(false);
		    dispose();
		}
		if(e.getSource().equals(applyServiceB)) //��û��ư ���� ���
		{
			String arr[] = {"basic", "best", "study", "smart", "kakao", "music"};
			ArrayList<String> item = new ArrayList<String>();
			for(int i=0;i<6;i++)
				if(checkbox[i].isSelected()) 
					item.add(arr[i]);
			
			String query = "update additional set ";
			for(int i=0;i<item.size();i++)
			{
				query += (item.get(i) + " = true");
				if(i != item.size()-1)
					query += ", ";
			}
			query += (" where id = '" + user.id + "'");
			
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//�ش� �����ͺ��̽� ���
				
				st.executeUpdate(query);
				con.close();
			} catch (SQLException e1) {
			}finally{
				if(con != null)
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			
			JOptionPane.showMessageDialog(null, "��û�Ǿ����ϴ�.");
		}
		if(e.getSource().equals(cancelServiceB))//������ư ���� ���
		{
			String arr[] = {"basic", "best", "study", "smart", "kakao", "music"};
			ArrayList<String> item = new ArrayList<String>();
			for(int i=0;i<6;i++)
				if(checkbox[i].isSelected())
					item.add(arr[i]);
			
			String query = "update additional set ";
			for(int i=0;i<item.size();i++)
			{
				query += (item.get(i) + " = false");
				if(i != item.size()-1)
					query += ", ";
			}
			query += (" where id = '" + user.id + "'");
			
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//�ش� �����ͺ��̽� ���
				
				st.executeUpdate(query);
				con.close();
			} catch (SQLException e1) {
			}finally{
				if(con != null)
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			
			JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
		}
	}
}
