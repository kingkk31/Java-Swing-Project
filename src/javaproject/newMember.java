package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//ȸ������
public class newMember extends JFrame implements ActionListener {
	User user;
	JButton nextB, cancelB;
	JTextField nameText, idText, phoneFirstText, phoneMiddleText, phoneLastText;
	JRadioButton man, woman;
	JPasswordField passwordText;
	JComboBox YearCombo, MonthCombo, DayCombo;
	Component co;
	
	newMember(User user){
		this.user=user;
		
		setLayout(null);
		setTitle("ȸ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������ ����
		
		JLabel intro = new JLabel("ȸ�������Ϸ� ���� ���� ȯ���մϴ�.");
		intro.setLocation(100,30);
		intro.setSize(250,40);
		add(intro);
		//ȸ������ ȯ�� ��
		
		JLabel nameL = new JLabel("�̸�");
		nameL.setLocation(60, 80);
		nameL.setSize(50, 40);
		add(nameL);
		nameText = new JTextField("");
		nameText.setLocation(140, 88);
		nameText.setSize(200, 25);
		add(nameText);
		//�̸�
		
		man = new JRadioButton("����");
		woman = new JRadioButton("����",true);
		//���� ���� ��ư ����
		
		ButtonGroup sexG = new ButtonGroup();
		sexG.add(woman);
		sexG.add(man);
		//�� �׷����� ���´�
		
		man.addActionListener(this);
		woman.addActionListener(this);
		//���� �̺�Ʈ ������ ���
		
		JPanel sexP = new JPanel();
		JLabel sexL = new JLabel("����");
		sexL.setLocation(60,125);
		sexL.setSize(50,40);
		add(sexL);
		sexP.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 7));
		sexP.setBounds(80,120,300,50);
		add(sexP);
		//���� ���� ��ư ���� Ʋ
		
		sexP.add(woman);
		sexP.add(man);
		//���� ��ư�� �ִ´�.
		
		JLabel idL = new JLabel("���̵�");
		idL.setLocation(50, 160);
		idL.setSize(100, 50);
		add(idL);
		idText = new JTextField("");
		idText.setLocation(140, 170);
		idText.setSize(200, 25);
		add(idText);
		//���̵� 
		
		JLabel passwordL = new JLabel("��й�ȣ");
		passwordL.setLocation(45, 210);
		passwordL.setSize(120, 50);
		add(passwordL);
		passwordText = new JPasswordField();
		passwordText.setLocation(140, 220);
		passwordText.setSize(200, 25);
		add(passwordText);
		//��й�ȣ - �Ⱥ��̰� ó��
		
		JLabel birthL = new JLabel("�������");
		birthL.setLocation(45, 260);
		birthL.setSize(100, 50);
		add(birthL);
		//����
		
		JPanel birthP = new JPanel();
		birthP.setLayout(new FlowLayout());
		birthP.setBounds(120, 265, 250, 65); //x, y, width, height
		add(birthP);
		//������� ���� ����Ʈ ���� �г�
		
		YearCombo = new JComboBox();
		MonthCombo = new JComboBox();
		DayCombo = new JComboBox();
		//�޺��ڽ� Ʋ �����
		
		for(int i=2015; i>=1950; i--)
			YearCombo.addItem(String.valueOf(i));
		for(int i=1; i<13; i++)
			MonthCombo.addItem(String.valueOf(i));
		for(int i=1; i<32; i++)
			DayCombo.addItem(String.valueOf(i));
		//��, ��, �� �� �ֱ�
		
		YearCombo.setSelectedIndex(0);
		MonthCombo.setSelectedIndex(0);
		DayCombo.setSelectedIndex(0);
		//��, ��, �� �� ù��° �� ����
		
		YearCombo.addActionListener(this);
		MonthCombo.addActionListener(this);
		DayCombo.addActionListener(this);
		//������ ���
		
		birthP.add(YearCombo);
		birthP.add(MonthCombo);
		birthP.add(DayCombo);
		//�������
		
		JLabel phoneL = new JLabel("��ȭ��ȣ");
		phoneL.setLocation(50, 320);
		phoneL.setSize(100, 50);
		add(phoneL);
		phoneFirstText = new JTextField("");
		phoneFirstText.setDocument(new JTextLimit()); 
		//���ڸ� �Է��ϰ�����
		phoneFirstText.setLocation(140, 330);
		phoneFirstText.setSize(50, 25);
		add(phoneFirstText);
		
		phoneMiddleText = new JTextField("");
		phoneMiddleText.setDocument(new JTextLimit());
		//���ڸ� �Է��ϰ� ����
		phoneMiddleText.setLocation(200, 330);
		phoneMiddleText.setSize(70, 25);
		add(phoneMiddleText);
		
		phoneLastText = new JTextField("");
		phoneLastText.setDocument(new JTextLimit());
		//���ڸ� �Է��ϰ� ����
		phoneLastText.setLocation(280, 330);
		phoneLastText.setSize(70, 25);
		add(phoneLastText);
		//��ȭ��ȣ
		
		ToolTipManager m = ToolTipManager.sharedInstance();
		m.setInitialDelay(10); // ������ ��Ÿ���� ���� �����ð� ����
		phoneFirstText.setToolTipText("���ڸ� �Է����ּ���");
		phoneMiddleText.setToolTipText("���ڸ� �Է����ּ���");
		phoneLastText.setToolTipText("���ڸ� �Է����ּ���");
		m.setDismissDelay(1000); // ������ �����ִ� ���� �ð� ����
		// ����
	
		nextB = new JButton("����");
		nextB.setLocation(210,390);
		nextB.setSize(80, 25);
		nextB.addActionListener(this);
		add(nextB);
		//������ư
		
		cancelB = new JButton("���");
		cancelB.setLocation(100,390);
		cancelB.setSize(80, 25);
		cancelB.addActionListener(this);
		add(cancelB);
		//��ҹ�ư
		
		setVisible(true); //�������� ȭ�鿡 ���
	}
	
	public boolean idCheck(String id) //���̵� �˻��ϴ� �κ�
	{
		int result = 0;
		
		Connection con = null;
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			// �ش� �����ͺ��̽� ���
			ResultSet resultSet = st.executeQuery("select * from login where id = '"+ id + "'");
			
			if(resultSet.next())
				result++; 
		}
		catch (SQLException e){
		}finally{
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return (result > 0) ? true : false;
	}	
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(nextB))//������ư�� ���� ���
		{
			String idValue = idText.getText();
			String nameValue = nameText.getText();
		    String sexValue = woman.isEnabled() ? "����" : "����";
			String pwValue = passwordText.getText();
		    String phoneValue = phoneFirstText.getText()+"-"+phoneMiddleText.getText()+"-"+phoneLastText.getText();
		    String birthValue = (String)YearCombo.getSelectedItem()+"-"+(String)MonthCombo.getSelectedItem()+"-"+(String)DayCombo.getSelectedItem();
			
		    
		    if(nameText.getText()== null || nameText.getText().length() ==0)
		    	//�̸��� �Է����� �ʾ��� ���
				JOptionPane.showMessageDialog(co,"�̸��� �Է��Ͽ� �ֽʽÿ�");
			else if(idText.getText()== null || idText.getText().length() ==0)
				//���̵� �Է����� �ʾ��� ���
				JOptionPane.showMessageDialog(co, "���̵� �Է��Ͽ� �ֽʽÿ�");
			else if(idText.getText()!=null && idCheck(idText.getText()))
			{
				//���̵� �����ϴ� ���
				JOptionPane.showMessageDialog(co, "����� �� ���� ���̵��Դϴ�!");
				idText.setText(null);
				idText.requestFocus();
			}
			else if(idText.getText().trim().length()>20 || idText.getText().trim().length()<=5)
				//���̵� ���� �� ����
				JOptionPane.showMessageDialog(co, "���̵�� 5�� �̻�, 20�� �̳��� �Է��ؾ��մϴ�.");
			else if(passwordText.getText().trim().length()>20 || passwordText.getText().trim().length()<=7)
				//��й�ȣ ���� �� ����
				JOptionPane.showMessageDialog(co, "��й�ȣ�� 7�� �̻�, 20�� �̳��� �Է��ؾ��մϴ�.");
			else if(passwordText.getText()== null || passwordText.getText().length() ==0)
				//��й�ȣ�� �Է����� ���� ���
				JOptionPane.showMessageDialog(co, "��й�ȣ�� �Է��Ͽ� �ֽʽÿ�");
			else if(phoneFirstText.getText()== null || phoneFirstText.getText().length() ==0)
				//��ȭ��ȣ�� �Է����� ���� ���
				JOptionPane.showMessageDialog(co, "��ȭ��ȣ�� �Է��Ͽ� �ֽʽÿ�");
			else if(phoneMiddleText.getText()== null || phoneMiddleText.getText().length() ==0)
				//��ȭ��ȣ�� ������ �Է����� ���� ���(�߰� ��ȣ�� �Է����� ���� ���)
				JOptionPane.showMessageDialog(co, "��ȭ��ȣ�� ���� �Է��Ͽ� �ֽʽÿ�");
			else if(phoneLastText.getText()== null || phoneLastText.getText().length() ==0)
				//��ȭ��ȣ�� ������ �Է����� ���� ���(������ ��ȣ�� �Է����� ���� ���)
				JOptionPane.showMessageDialog(co, "��ȭ��ȣ�� ���� �Է��Ͽ� �ֽʽÿ�");
			else //��� ������ ���̵��� ���
			{
				JOptionPane.showMessageDialog(co, "��� ������ ���̵��Դϴ�!");
				payRegister pay = new payRegister(user, idValue, nameValue, sexValue, pwValue, phoneValue, birthValue);
				setVisible(false);
				dispose();
			}
		}
		else if(e.getSource().equals(cancelB)) // ��� ��ư ������ ��
		{
			FirstPage first = new FirstPage(user);
			setVisible(false);
			dispose();
		}
	}
}
