package javaproject;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.sql.*;

//������� Ŭ����
public class ChargeRate extends JFrame implements ActionListener{
	
	User user;
	JButton b1, submit, cancel;	
	JRadioButton rb1, rb2, rb3, rb4, pay1, pay2;
	ButtonGroup gr1, gr2;
	JTextField callText, dataText, messageText;
	
	ChargeRate(User user)
	{
		this.user = user; //���� Ŭ���� �޾ƿ���
		
		setLayout(null);
		setTitle("�������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������ ����
		
		JLabel title;
		title = new JLabel("�������");
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
		
		rb1 = new JRadioButton("5000��");
		rb1.setSelected(true);
		rb2 = new JRadioButton("10000��");
		rb3 = new JRadioButton("15000��");
		rb4 = new JRadioButton("20000��");
		//��� ���� ��ư ����
		
		gr1 = new ButtonGroup();
		gr1.add(rb1);
		gr1.add(rb2);
		gr1.add(rb3);
		gr1.add(rb4);
		//�� �׷����� ���´�
		
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);
		rb4.addActionListener(this);
		//���� �̺�Ʈ ������ ���
		
		JPanel pay = new JPanel();
		pay.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		pay.setBorder((Border) new TitledBorder(null, "��� ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pay.setBounds(30, 80, 315, 180);
		add(pay);
		//��� ���� ��ư�� ���� Ʋ
		
		pay.add(rb1);
		pay.add(rb2);
		pay.add(rb3);
		pay.add(rb4);
		//���� ��ư�� �ִ´�
		
		JPanel payInner = new JPanel();
		payInner.setSize(315, 200);
		payInner.setLayout(new GridLayout(3,2,15,10));
		
		payInner.add(new JLabel("�߰� ���� (��)"));
		callText = new JTextField("27", 6);
		callText.setHorizontalAlignment(JTextField.RIGHT);
		callText.setEditable(false);
		payInner.add(callText);
		//�ݾ� �� �߰� ����
		
		payInner.add(new JLabel("�߰� ������ (MB)"));
		dataText = new JTextField("333", 6);
		dataText.setHorizontalAlignment(JTextField.RIGHT);
		dataText.setEditable(false);
		payInner.add(dataText);
		//�ݾ� �� �߰� ������

		payInner.add(new JLabel("�߰� �޼��� (��)"));
		messageText = new JTextField("23", 6);
		messageText.setHorizontalAlignment(JTextField.RIGHT);
		messageText.setEditable(false);
		payInner.add(messageText);
		//�ݾ� �� �߰� �޼���
		
		pay.add(payInner);		
		
		submit = new JButton("����");
		submit.setLocation(110, 330);
		submit.setSize(70, 30);
		submit.addActionListener(this);
		add(submit);
		//����� �Ѿ�� ��ư
		
		cancel = new JButton("���");
		cancel.setLocation(210, 330);
		cancel.setSize(70, 30);
		cancel.addActionListener(this);
		add(cancel);
		//��� ��ư
			
		setVisible(true); //�������� ȭ�鿡 ���
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //�ڷΰ��� ��ư�� ���� ���
		{
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(submit)) //������ �Ѿ��
		{
			int callValue = Integer.parseInt(callText.getText());
			long dataValue = Long.parseLong(dataText.getText());
			int messageValue = Integer.parseInt(messageText.getText());
					
			RatePayment next = new RatePayment(user, callValue, dataValue, messageValue);
			setVisible(false);
		    dispose();
		 }
		else if (e.getSource().equals(cancel)) //���
		{
			JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�.");
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		    //��ҽ� �������� �ʰ� ����
		}
		else if (e.getSource().equals(rb1)) //5000�� ����
		{
			callText.setText("27");
			dataText.setText("333");
			messageText.setText("23");
		}
		else if (e.getSource().equals(rb2)) //10000�� ����
		{
			callText.setText("55");
			dataText.setText("666");
			messageText.setText("47");
		}
		else if (e.getSource().equals(rb3)) //15000�� ����
		{
			callText.setText("83");
			dataText.setText("1000");
			messageText.setText("71");
		}
		else if (e.getSource().equals(rb4)) //20000�� ����
		{
			callText.setText("111");
			dataText.setText("1333");
			messageText.setText("95");
		}
	}	
}
