package javaproject;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.sql.*;

//����� ���� Ŭ����
public class ChangeRate extends JFrame implements ActionListener{
	
	User user;
	JButton b1, submit, cancel;	
	JTextField currentRate, pay, call, data, message;
	JComboBox rateList;
	ChangeRate(User user)
	{
		this.user = user; //���� Ŭ���� �޾ƿ���
		
		setLayout(null);
		setTitle("����� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������ ����
		
		JLabel title;
		title = new JLabel("����� ����");
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
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		pn1.setBorder((Border) new TitledBorder(null, "����� ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn1.setBounds(60, 60, 120, 70);
		add(pn1);
		//����� ���� ����Ʈ�� ���� �г�
		
		Font font = new Font("����", Font.PLAIN, 20);
		
		String []rates = {"LTE 34", "LTE 42", "LTE 52", "LTE 62", "LTE 72", "LTE 85", "LTE 100", "LTE 120"};
		rateList = new JComboBox(rates);
		rateList.setSelectedIndex(0);
		rateList.setFont(font);
		rateList.addActionListener(this);
		pn1.add(rateList);
		//����� ���� �޺��ڽ�
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		pn2.setBorder((Border) new TitledBorder(null, "���� �����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(210, 60, 120, 70);
		add(pn2);
		//���� ����� �г�
		
		currentRate = new JTextField(user.flat_rate, 6);
		currentRate.setFont(font);
		currentRate.setHorizontalAlignment(JTextField.CENTER);
		currentRate.setBorder(null);
		currentRate.setEditable(false);
		pn2.add(currentRate);		
		//���� �����
		
		JPanel pn3 = new JPanel();
		pn3.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		pn3.setBorder((Border) new TitledBorder(null, "���� ����� ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn3.setBounds(40, 140, 300, 240);
		add(pn3);
		//���� ����� �г�
		
		Font font2 = new Font("����", Font.PLAIN, 18);
		JLabel la1 = new JLabel("    ������(��)     ");
		pn3.add(la1); 
		la1.setFont(font2); 
		
		pay = new JTextField("34000", 6); 
		pay.setEditable(false);
		pay.setHorizontalAlignment(JTextField.RIGHT);
		pn3.add(pay); 
		pay.setFont(font2); 
		//���� ���
		
		JLabel la2 = new JLabel("  ���� ����(��)   "); 
		pn3.add(la2); 
		la2.setFont(font2); 
		
		call = new JTextField("160", 6); 
		call.setEditable(false);
		call.setHorizontalAlignment(JTextField.RIGHT);
		pn3.add(call); 
		call.setFont(font2); 
		//���� ����
		
		JLabel la3 = new JLabel("���� ������(MB)"); 
		pn3.add(la3); 
		la3.setFont(font2); 
		
		data = new JTextField("750", 6); 
		data.setEditable(false);
		data.setHorizontalAlignment(JTextField.RIGHT);
		pn3.add(data); 
		data.setFont(font2); 
		//���� ������
		
		JLabel la4 = new JLabel("���� �޼���(��)  "); 
		pn3.add(la4); 
		la4.setFont(font2); 
		
		message = new JTextField("200", 6); 
		message.setEditable(false);
		message.setHorizontalAlignment(JTextField.RIGHT);
		pn3.add(message); 
		message.setFont(font2); 
		//���� �޼���
		
		
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
		if (e.getSource().equals(b1)) //�ڷΰ��� ��ư ���ý�
		{
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(submit)) //���� ��ư ���ý�
		{
			int callValue = Integer.parseInt(call.getText());
			long dataValue = Long.parseLong(data.getText());
			int messageValue = Integer.parseInt(message.getText());
			
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//�ش� �����ͺ��̽� ���
								
				PreparedStatement ps = con.prepareStatement("update profile set flat_rate = ?, call_volume = ?, data = ?, message = ? where id = '" + user.id + '\'');
				ps.setString(1, (String)rateList.getSelectedItem());
				ps.setInt(2, callValue);
				ps.setLong(3, dataValue);
				ps.setInt(4, messageValue);
				ps.executeUpdate();
				//���� �� ������� �����Ѵ�
				
				ps = con.prepareStatement("update membership set rank = ?, point = ? where id = '" + user.id + '\'');
				switch ((String) rateList.getSelectedItem()) {
				case "LTE 34":
				case "LTE 42":
					ps.setString(1, "Bronze");
					ps.setInt(2, 60000);
					break;
				case "LTE 52":
				case "LTE 62":
					ps.setString(1, "Silver");
					ps.setInt(2, 80000);
					break;
				case "LTE 72":
				case "LTE 85":
					ps.setString(1, "Gold");
					ps.setInt(2, 100000);
					break;
				case "LTE 100":
				case "LTE 120":
					ps.setString(1, "VIP");
					ps.setInt(2, 120000);
					break;
				default:
					ps.setString(1, "Bronze");
					ps.setInt(2, 60000);
					break;
				}
				ps.executeUpdate();
				//����� ���濡 ���� ����� ����
						
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
			
			FlatRate back = new FlatRate(new User(user.id, user.name, user.phone, (String)rateList.getSelectedItem(), callValue, dataValue, messageValue));
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(cancel)) //��� ��ư ���ý�
		{
			JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�.");
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(rateList)) //�޺��ڽ� ���� �� ����� ���� ����
		{
			String str = (String)rateList.getSelectedItem();
			switch(str)
			{
			case "LTE 34": pay.setText("34000"); call.setText("160"); data.setText("750"); message.setText("200"); break;
			case "LTE 42": pay.setText("42000"); call.setText("200"); data.setText("1536"); message.setText("200"); break;
			case "LTE 52": pay.setText("52000"); call.setText("250"); data.setText("2662"); message.setText("250"); break;
			case "LTE 62": pay.setText("62000"); call.setText("350"); data.setText("6144"); message.setText("350"); break;
			case "LTE 72": pay.setText("72000"); call.setText("500"); data.setText("10240"); message.setText("450"); break;
			case "LTE 85": pay.setText("85000"); call.setText("750"); data.setText("14336"); message.setText("650"); break;
			case "LTE 100": pay.setText("100000"); call.setText("1200"); data.setText("20480"); message.setText("1000"); break;
			case "LTE 120": pay.setText("120000"); call.setText("1500"); data.setText("24576"); message.setText("1000"); break;
			default: pay.setText("34000"); call.setText("160"); data.setText("750"); message.setText("200"); break;
			}
		}
		
	}
}
