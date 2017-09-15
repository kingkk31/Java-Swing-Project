package javaproject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//����� ���޻�
public class MembershipAffiliate extends JFrame implements ActionListener{

	User user;
	JButton b1;
	
	MembershipAffiliate(User user)
	{
		this.user = user; //���� Ŭ���� �޾ƿ���
		
		setLayout(null);
		setTitle("����� ���޻�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400,500);
		//��ü ������
	
		JLabel title;
		title = new JLabel("����� ���޻�");
		title.setLocation(120, 20);
		title.setSize(160, 20);
		title.setFont(new Font("����", Font.BOLD, 22));
		add(title);
		//Ÿ��Ʋ
		
		b1 = new JButton("�ڷΰ���");
		b1.setLocation(280, 10);
		b1.setSize(90, 30);
		b1.addActionListener(this);
		add(b1);
		//�ڷΰ��� ��ư
			
		Font font = new Font("����", Font.PLAIN, 18);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn2.setBorder((Border) new TitledBorder(null, "����� ��� �� �ѵ�", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(20, 60, 340, 370);
		add(pn2);
		//����� ���޻� �г�
		
		String tableName[] = {"���޻�", "����", "����Ʈ ����"};
		DefaultTableModel model = new DefaultTableModel(tableName, 0); 
		JTable table = new JTable(model);
		table.setEnabled(false);
		table.getColumnModel().getColumn(0).setMinWidth(80); 
		table.getColumnModel().getColumn(1).setMinWidth(120);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		table.setRowHeight(40); 
		String arr[] = {"���޻�", "����", "����Ʈ ����"};
		//����� ���޻� ���̺�
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(320, 330));
		pn2.add(scroll);
		//��ũ��
		
		arr[0] = "CGV"; arr[1] = "���� ��ȭ ����"; arr[2] = "7000��"; model.addRow(arr);
		arr[0] = "�Ե��ó׸�"; arr[1] = "���� ��ȭ ����"; arr[2] = "7000��"; model.addRow(arr);
		arr[0] = "��Ÿ����"; arr[1] = "�Ƹ޸�ī��(Tall) ����"; arr[2] = "4100��"; model.addRow(arr);
		arr[0] = "Ž��Ž��"; arr[1] = "�Ƹ޸�ī��(Tall) ����"; arr[2] = "3800��"; model.addRow(arr);
		arr[0] = "���̳�����"; arr[1] = "20% ����"; arr[2] = "�ݾ״� 1��"; model.addRow(arr);
		arr[0] = "�̽�������"; arr[1] = "20% ����"; arr[2] = "�ݾ״� 1��"; model.addRow(arr);
		arr[0] = "���﷣��"; arr[1] = "�����̿�� 50% ����"; arr[2] = "�ݾ״� 1��"; model.addRow(arr);
		arr[0] = "GS25"; arr[1] = "15% ����"; arr[2] = "�ݾ״� 1��"; model.addRow(arr);
		arr[0] = "�ĸ��ٰԶ�"; arr[1] = "õ���� 100�� ����"; arr[2] = "�ݾ״� 1��"; model.addRow(arr);
		arr[0] = "�ѷ��긣"; arr[1] = "õ���� 100�� ����"; arr[2] = "�ݾ״� 1��"; model.addRow(arr);
		arr[0] = "ĳ����Ⱥ���"; arr[1] = "30% ����"; arr[2] = "�ݾ״� 1��"; model.addRow(arr);
		arr[0] = "�����̽���"; arr[1] = "15% ����"; arr[2] = "�ݾ״� 1��"; model.addRow(arr);
		//���̺� �Է�
	
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
