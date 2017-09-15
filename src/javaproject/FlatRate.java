package javaproject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//��� ����
public class FlatRate extends JFrame implements ActionListener{
	
	User user;
	JButton back, b1, b2, b3, b4;
	
	FlatRate(User user)
	{
		this.user = user;
		setLayout(null); //���� ������Ʈ�� ��ġ�� ũ�⸦ ������ �� �ִ�.
		setTitle("��ݰ���"); //������ Ÿ�̺z
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ������ ������ ���α׷� ����
		setSize(400,500); //������ũ�⼳��
		setResizable(false);
		//��ü ������ ����
		
		JLabel title = new JLabel("��ݰ���");
		title.setLocation(140, 80);
		title.setSize(100, 20);
		title.setFont(new Font("����", Font.BOLD, 25));
		add(title);
		//Ÿ��Ʋ
		
		back = new JButton("�ڷΰ���");
		back.setLocation(280, 10);
		back.setSize(90, 30);
		back.addActionListener(this);
		add(back);
		//�ڷΰ��� ��ư

		b1 = new JButton("�����ȸ");
		b1.setLocation(40, 160);
		b1.setSize(140, 50);
		b1.addActionListener(this);
		add(b1);
		//�����ȸ ��ư
		
		b2 = new JButton("�������");
		b2.setLocation(200, 160);
		b2.setSize(140, 50);
		b2.addActionListener(this);
		add(b2);
		//������� ��ư
		
		b3 = new JButton("����� ����");
		b3.setLocation(40, 250);
		b3.setSize(140, 50);
		b3.addActionListener(this);
		add(b3);
		//����� ���� ��ư
		
		b4 = new JButton("���� ����� ã��");
		b4.setLocation(200, 250);
		b4.setSize(140, 50);
		b4.addActionListener(this);
		add(b4);
		//���� ����� ã�� ��ư
		
		setVisible(true); //�������� ȭ�鿡 ���
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(back)) //��������
		{
			Main ex = new Main(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b1)) //�����ȸ��
		{
			InquiryRate inq = new InquiryRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b2)) //�����������
		{
			ChargeRate charge = new ChargeRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b3)) //����� ��������
		{
			ChangeRate change = new ChangeRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b4)) //���� ����� ã���
		{
			FindRate find = new FindRate(user);
			setVisible(false);
		    dispose();
		}
		//�ش� ��ư�� ������ �ش� ������� �Ѿ��
	}
	
}
