package javaproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

//��ݰ���
public class RatePayment extends JFrame implements ActionListener{

	User user;
	JButton b1, submit, cancel;
	JRadioButton pay1, pay2;
	JComboBox transfer;
	int callValue, messageValue;
	long dataValue;
	
	RatePayment(User user, int call, long data, int message){
		
		this.user = user;
		this.callValue = call;
		this.dataValue = data;
		this.messageValue = message;
		
		setLayout(null);
		setTitle("��ݰ���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������ ����
		
		JLabel title;
		title = new JLabel("��ݰ���");
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
		
		JPanel payment = new JPanel();
		payment.setLayout(new FlowLayout(FlowLayout.CENTER, 30,10));
		payment.setBorder((Border) new TitledBorder(null, "���� ��/�ĺ� ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		payment.setBounds(50, 60, 280, 90);
		add(payment);
		//���� ��� ���� Ʋ
		
		pay1 = new JRadioButton("����");
		pay1.setFont(new Font("����", Font.PLAIN, 18));
		pay1.setSelected(true);
		pay1.addActionListener(this);
		pay2 = new JRadioButton("�ĺ�");
		pay2.setFont(new Font("����", Font.PLAIN, 18));
		pay2.addActionListener(this);
		//��,�ĺ� ���� ����
		
		ButtonGroup gr = new ButtonGroup();
		gr.add(pay1);
		gr.add(pay2);
		payment.add(pay1);
		payment.add(pay2);
		//���� ��ư ����
		
		JPanel account = new JPanel();
		account.setLayout(new FlowLayout(FlowLayout.CENTER, 20,15));
		account.setBorder((Border) new TitledBorder(null, "����(������ü)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		account.setBounds(50, 190, 280, 100);
		add(account);
		//���� Ʋ
		
		String []banks = {"������� 012-346344-86-356 ������", "�������� 851202-04-108649 ������"};
		transfer = new JComboBox(banks);
		transfer.setSelectedIndex(0);
		transfer.addActionListener(this);
		transfer.setFont(new Font("����", Font.PLAIN, 15));
		account.add(transfer);
		//������ü �޺�
		
		submit = new JButton("����");
		submit.setLocation(110, 350);
		submit.setSize(70, 30);
		submit.addActionListener(this);
		add(submit);
		//���� ��ư
		
		cancel = new JButton("���");
		cancel.setLocation(210, 350);
		cancel.setSize(70, 30);
		cancel.addActionListener(this);
		add(cancel);
		//��� ��ư
			
		setVisible(true); //�������� ȭ�鿡 ���
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(b1)) //�ڷΰ��� ��ư�� ���� ���
		{
			ChargeRate back = new ChargeRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(submit)) //������ư�� ���� ���
		{
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//�ش� �����ͺ��̽� ���
							
				ResultSet rs = st.executeQuery("select * from profile where id = '" + user.id + '\'');
				while(rs.next()) //��� �ִ� ������ �д� �κ�
				{
					callValue += Integer.parseInt(rs.getString("call_volume")); //����
					dataValue += Long.parseLong(rs.getString("data"));  //������
					messageValue += Integer.parseInt(rs.getString("message")); //����
				}
				rs.close();
				//���� �о�� �߰� �ݾװ� ��ģ��
				
				PreparedStatement ps = con.prepareStatement("update profile set call_volume = ?, data = ?, message = ? where id = '" + user.id + '\'');
				ps.setInt(1, callValue);
				ps.setLong(2, dataValue);
				ps.setInt(3, messageValue);
				ps.executeUpdate();
				//��ģ ���� �ٽ� �����Ѵ�
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
			
			JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
			
			FlatRate back = new FlatRate(new User(user.id, user.name, user.phone, user.flat_rate, callValue, dataValue, messageValue));
			setVisible(false);
		    dispose();
		    //�Ϸ� �� ��ݰ����� ����
		}
		else if (e.getSource().equals(cancel)) //��ҹ�ư�� ���� ���
		{
			JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�.");
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		    //��ҽ� �������� �ʰ� ����
		}
		else if (e.getSource().equals(pay1)) //���� ���ý� ���� â ������ �� ����
		{
			transfer.setEnabled(true);
		}
		else if (e.getSource().equals(pay2)) //�ĺ� ���ý� ���� â ������ �� ����
		{
			transfer.setEnabled(false);
		}
	}
}
