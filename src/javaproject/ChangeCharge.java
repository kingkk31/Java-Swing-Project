package javaproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

//û����� ���� Ŭ����
public class ChangeCharge extends JFrame implements ActionListener{
	User user;
	JButton b1, submit, cancel;
	JTextField accountNum, cardNum;
	JRadioButton pay1, pay2;
	JComboBox bank, company;
	ButtonGroup gr;
	
	ChangeCharge(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("û������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������
	
		JPanel body;
		JLabel title, name, flat_rate, call_volume, data, message;
		
		title = new JLabel("û������");
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
		
		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER,20,5));
		pn1.setBorder((Border) new TitledBorder(null, "û����� ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn1.setBounds(60, 50, 260, 60);
		add(pn1);
		//û����� ���� ��ư�� ��� �г�

		pay1 = new JRadioButton("������ü", true);
		pay1.addActionListener(this);
		pay2 = new JRadioButton("�ſ�ī��");
		pay2.addActionListener(this);		
		//������ü, �ſ�ī�� ������ư
		
		gr = new ButtonGroup();
		gr.add(pay1);
		gr.add(pay2);
		pn1.add(pay1);
		pn1.add(pay2);
		//û����� ����
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.LEFT,15,8));
		pn2.setBorder((Border) new TitledBorder(null, "������ü", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(60, 120, 260, 130);
		add(pn2);
		//������ü�г�
		
		Font font = new Font("����", Font.PLAIN, 15);
		JLabel lb1 = new JLabel("���� ����");
		lb1.setFont(font);
		pn2.add(lb1);
		
		String []banks = {"�������","�츮����","��������","��������","�ϳ�����", "��������"};
		bank = new JComboBox(banks);
		bank.setSelectedIndex(0);
		bank.addActionListener(this);
		bank.setFont(new Font("����", Font.PLAIN, 12));
		pn2.add(bank);
		//���� ���� �޺��ڽ�
		
		JLabel lb2 = new JLabel("���¹�ȣ �Է� ('-'�� �Բ�)");
		lb2.setFont(font);
		pn2.add(lb2);
		//���¹�ȣ�Է��ϴ� â
		
		accountNum = new JTextField("", 20); 
		pn2.add(accountNum); 
		//������ü ���� �� �Է�
		
		JPanel pn3 = new JPanel();
		pn3.setLayout(new FlowLayout(FlowLayout.LEFT,20,6));
		pn3.setBorder((Border) new TitledBorder(null, "�ſ�ī��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn3.setBounds(60, 260, 260, 130);
		add(pn3);
		//�ſ�ī�� �г�
		
		JLabel lb3 = new JLabel("ī��� ����");
		lb3.setFont(font);
		pn3.add(lb3);
		
		String []companys = {"�Ｚī��", "LGī��", "����ī��", "�Ե�ī��", "����ī��", "��ȯī��", "����ī��", "����ī��", "BCī��"};
		company = new JComboBox(companys);
		company.setSelectedIndex(0);
		company.addActionListener(this);
		company.setFont(new Font("����", Font.PLAIN, 12));
		pn3.add(company);
		//ī�� �����ϴ� �޺��ڽ�
		
		JLabel lb4 = new JLabel("ī���ȣ �Է� ('-'�� �Բ�)");
		lb4.setFont(font);
		pn3.add(lb4);
		//ī���ȣ �Է��ϴ� â
		
		cardNum = new JTextField("", 20); 
		pn3.add(cardNum); 
		//�ſ�ī�� ���� �� �Է�
			
		bank.setEnabled(true);
		accountNum.setEnabled(true);
		//�ʱ⿣ ������ü �����̹Ƿ� ������ü �κ� Ȱ��ȭ
		
		company.setEnabled(false);
		cardNum.setEnabled(false);
		//�ſ�ī�� �κ� ��Ȱ��ȭ
		
		submit = new JButton("����");
		submit.setLocation(110, 400);
		submit.setSize(70, 30);
		submit.addActionListener(this);
		add(submit);
		//���� ��ư
		
		cancel = new JButton("���");
		cancel.setLocation(210, 400);
		cancel.setSize(70, 30);
		cancel.addActionListener(this);
		add(cancel);
		//��� ��ư
				
		setVisible(true); // �������� ȭ�鿡 ���
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //�ڷΰ���
		{
			Charge back = new Charge(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(pay1)) //������ü ���� ��
		{
			bank.setEnabled(true);
			accountNum.setEnabled(true);
			
			company.setEnabled(false);
			cardNum.setEnabled(false);		
		}
		else if (e.getSource().equals(pay2)) //�ſ�ī�� ���� ��
		{
			bank.setEnabled(false);
			accountNum.setEnabled(false);
			
			company.setEnabled(true);
			cardNum.setEnabled(true);
		}
		else if (e.getSource().equals(submit)) //���� ��ư ���ý�
		{
			String method, com, num;
			if(bank.isEnabled()) 
			{
				method = "������ü";
				com = (String)bank.getSelectedItem();
			}
			else
			{
				method = "�ſ�ī��";
				com = (String)company.getSelectedItem();
			}
			
			if(accountNum.isEnabled())
				num = accountNum.getText();
			else
				num = cardNum.getText();
			
			Connection con = null;
			//������ ���� �غ�
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//�ش� �����ͺ��̽� ���
								
				PreparedStatement ps = con.prepareStatement("update chargeMethod set method = ?, bank_company = ?, number = ? where id = '" + user.id + '\'');
				ps.setString(1, method);
				ps.setString(2, com);
				ps.setString(3, num);
				ps.executeUpdate();
				//���� �� û�������� �����Ѵ�
				
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
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
			//�����ͺ��̽��� ����
			
			JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.");
			Charge charge = new Charge(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(cancel)) //��ҹ�ư ���� ��
		{
			JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�.");
			Charge back = new Charge(user);
			setVisible(false);
		    dispose();
		    //��ҽ� �������� �ʰ� ����
		}
	}
}
