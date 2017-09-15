package javaproject;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.sql.*;

//Ȩ���� ����
public class HomeRegistration extends JFrame implements ActionListener{
	
	User user;
	JButton b1, submit, cancel;	
	JTextField tv, internet, IoT;
	JComboBox tvList, internetList, IoTList;
	
	HomeRegistration(User user)
	{
		this.user = user; //���� Ŭ���� �޾ƿ���
		
		setLayout(null);
		setTitle("Ȩ���� ���/����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������ ����
		
		JLabel title;
		title = new JLabel("Ȩ���� ���/����");
		title.setLocation(80, 20);
		title.setSize(200, 20);
		title.setFont(new Font("����", Font.BOLD, 22));
		add(title);
		//Ÿ��Ʋ
		
		b1 = new JButton("�ڷΰ���");
		b1.setLocation(280, 10);
		b1.setSize(90, 30);
		b1.addActionListener(this);
		add(b1);
		//�ڷΰ��� ��ư
		
		Font font = new Font("����", Font.PLAIN, 20);
				
		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn1.setBorder((Border) new TitledBorder(null, "tv,���ͳ�,IoT ���� ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn1.setBounds(30, 60, 320, 80);
		add(pn1);
		//���� Ȩ���� �г�
			
		String []tvs = {"����", "����ƮTV", "�ǽð�TV", "PCTV"};
		tvList = new JComboBox(tvs);
		tvList.setSelectedIndex(0);
		tvList.addActionListener(this);
		pn1.add(tvList);
		//tv ���� �޺��ڽ�		
		
		String []internets = {"����", "GIGA", "GIGA ����Ʈ", "GIGA WiFi"};
		internetList = new JComboBox(internets);
		internetList.setSelectedIndex(0);
		internetList.addActionListener(this);
		pn1.add(internetList);
		//���ͳ� ���� �޺��ڽ�
		
		String []IoTs = {"����", "���", "������", "����", "����", "PET"};
		IoTList = new JComboBox(IoTs);
		IoTList.setSelectedIndex(0);
		IoTList.addActionListener(this);
		pn1.add(IoTList);
		//IoT ���� �޺��ڽ�
				
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		pn2.setBorder((Border) new TitledBorder(null, "���� Ȩ���� ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(40, 150, 300, 220);
		add(pn2);
		//���� Ȩ���� �г�
		
		Font font2 = new Font("����", Font.PLAIN, 18);
		JLabel la1 = new JLabel("    TV        ");
		pn2.add(la1); 
		la1.setFont(font2); 
		
		tv = new JTextField("����", 9); 
		tv.setEditable(false);
		tv.setHorizontalAlignment(JTextField.RIGHT);
		pn2.add(tv); 
		tv.setFont(font2); 
		//���õ� TV��ǰ
		
		JLabel la2 = new JLabel("  ���ͳ�    "); 
		pn2.add(la2); 
		la2.setFont(font2); 
		
		internet = new JTextField("����", 9); 
		internet.setEditable(false);
		internet.setHorizontalAlignment(JTextField.RIGHT);
		pn2.add(internet); 
		internet.setFont(font2); 
		//���õ� ���ͳݻ�ǰ
		
		JLabel la3 = new JLabel("    IoT        "); 
		pn2.add(la3); 
		la3.setFont(font2); 
		
		IoT = new JTextField("����", 9); 
		IoT.setEditable(false);
		IoT.setHorizontalAlignment(JTextField.RIGHT);
		pn2.add(IoT); 
		IoT.setFont(font2); 
		//���õ� IoT��ǰ
		
		submit = new JButton("����");
		submit.setLocation(110, 390);
		submit.setSize(70, 30);
		submit.addActionListener(this);
		add(submit);
		//���� ��ư
		
		cancel = new JButton("���");
		cancel.setLocation(210, 390);
		cancel.setSize(70, 30);
		cancel.addActionListener(this);
		add(cancel);
		//��� ��ư
			
		setVisible(true); //�������� ȭ�鿡 ���
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //�ڷΰ��� ��ư ���� ���
		{
			HomeService back = new HomeService(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(submit)) //�����ư ���� ���
		{
			String tvValue = tv.getText();
			String internetValue = internet.getText();
			String IoTValue = IoT.getText();
			
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//�ش� �����ͺ��̽� ���
								
				PreparedStatement ps = con.prepareStatement("update homeService set tv = ?, internet = ?, IoT = ? where id = '" + user.id + '\'');
				ps.setString(1, tvValue);
				ps.setString(2, internetValue);
				ps.setString(3, IoTValue);
				ps.executeUpdate();
				//����� Ȩ���񽺸� �����Ѵ�
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}finally{
				if(con != null)
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			
			JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.");
			
			HomeService back = new HomeService(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(cancel)) //��ҹ�ư ���� ���
		{
			JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�.");
			HomeService back = new HomeService(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(tvList)) //�޺��ڽ� ���� �� tv ���� ����
		{
			String str = (String)tvList.getSelectedItem();
			tv.setText(str);
		}
		else if (e.getSource().equals(internetList)) //�޺��ڽ� ���� �� ���ͳ� ���� ����
		{
			String str = (String)internetList.getSelectedItem();
			internet.setText(str);
		}
		else if (e.getSource().equals(IoTList)) //�޺��ڽ� ���� �� IoT ���� ����
		{
			String str = (String)IoTList.getSelectedItem();
			IoT.setText(str);
		}
	}
}
