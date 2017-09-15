package javaproject;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//ī�� �� ���� ���
public class payRegister extends JFrame implements ActionListener {
	User user;
	JButton submitB, backB;
	JTextField accountNum, cardNum;
	JRadioButton pay1, pay2;
	JComboBox bank, company;
	ButtonGroup gr;
	String idValue, nameValue, sexValue, pwValue, phoneValue, birthValue; // ����������
																			// �����

	payRegister(User user, String idValue, String nameValue, String sexValue, String pwValue, String phoneValue,
			String birthValue) {
		this.user = user;
		this.idValue = idValue;
		this.nameValue = nameValue;
		this.sexValue = sexValue;
		this.pwValue = pwValue;
		this.phoneValue = phoneValue;
		this.birthValue = birthValue;

		setLayout(null);
		setTitle("ī�� �� ���� ���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setResizable(false);
		// ��ü ������ ����

		JLabel title;
		title = new JLabel("���� ����� �����Ͽ� �������ּ���");
		title.setLocation(90, 20);
		title.setSize(220, 20);
		add(title);
		// Ÿ��Ʋ

		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		pn1.setBorder((Border) new TitledBorder(null, "û����� ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn1.setBounds(60, 50, 260, 60);
		add(pn1);
		// û����� ���� ��ư�� ��� �г�

		pay1 = new JRadioButton("������ü", true);
		pay1.addActionListener(this);
		pay2 = new JRadioButton("�ſ�ī��");
		pay2.addActionListener(this);
		// ������ü �� �ſ�ī�� ���� ��ư

		gr = new ButtonGroup();
		gr.add(pay1);
		gr.add(pay2);
		pn1.add(pay1);
		pn1.add(pay2);
		// û����� ����

		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 8));
		pn2.setBorder((Border) new TitledBorder(null, "������ü", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(60, 120, 260, 130);
		add(pn2);
		// ������ü �г�

		Font font = new Font("����", Font.PLAIN, 15);
		JLabel lb1 = new JLabel("���� ����");
		lb1.setFont(font);
		pn2.add(lb1);

		String[] banks = { "�������", "�츮����", "��������", "��������", "�ϳ�����", "��������" };
		bank = new JComboBox(banks);
		bank.setSelectedIndex(0);
		bank.addActionListener(this);
		bank.setFont(new Font("����", Font.PLAIN, 12));
		pn2.add(bank);
		// ���� �������� ������� �޺��ڽ�

		JLabel lb2 = new JLabel("���¹�ȣ �Է� ('-'�� �Բ�)");
		lb2.setFont(font);
		pn2.add(lb2);

		accountNum = new JTextField("", 20);
		pn2.add(accountNum);
		// ������ü ���� �� �Է�

		JPanel pn3 = new JPanel();
		pn3.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 6));
		pn3.setBorder((Border) new TitledBorder(null, "�ſ�ī��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn3.setBounds(60, 260, 260, 130);
		add(pn3);
		// �ſ�ī�� �г�

		JLabel lb3 = new JLabel("ī��� ����");
		lb3.setFont(font);
		pn3.add(lb3);

		String[] companys = { "�Ｚī��", "LGī��", "����ī��", "�Ե�ī��", "����ī��", "��ȯī��", "����ī��", "����ī��", "BCī��" };
		company = new JComboBox(companys);
		company.setSelectedIndex(0);
		company.addActionListener(this);
		company.setFont(new Font("����", Font.PLAIN, 12));
		pn3.add(company);
		// ī�� ���� �����ϰ� �ϴ� �޺��ڽ�

		JLabel lb4 = new JLabel("ī���ȣ �Է� ('-'�� �Բ�)");
		lb4.setFont(font);
		pn3.add(lb4);

		cardNum = new JTextField("", 20);
		pn3.add(cardNum);
		// �ſ�ī�� ���� �� �Է�

		bank.setEnabled(true);
		accountNum.setEnabled(true);
		// �ʱ⿣ ������ü �����̹Ƿ� ������ü �κ� Ȱ��ȭ
		company.setEnabled(false);
		cardNum.setEnabled(false);
		// �ſ�ī�� �κ� ��Ȱ��ȭ

		submitB = new JButton("Ȯ��");
		submitB.setLocation(210, 400);
		submitB.setSize(70, 30);
		submitB.addActionListener(this);
		add(submitB);
		// ���� ��ư

		backB = new JButton("�ڷ�");
		backB.setLocation(110, 400);
		backB.setSize(70, 30);
		backB.addActionListener(this);
		add(backB);
		// �ڷΰ��� ��ư

		setVisible(true); // �������� ȭ�鿡 ���
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(pay1)) // ������ü ���� �� ������ü â Ȱ��ȭ, �ſ�ī�� â ��Ȱ��ȭ
		{
			bank.setEnabled(true);
			accountNum.setEnabled(true);

			company.setEnabled(false);
			cardNum.setEnabled(false);
		} else if (e.getSource().equals(pay2)) // �ſ�ī�� ���� �� ������ü â ��Ȱ��ȭ, �ſ�ī�� â
												// Ȱ��ȭ
		{
			bank.setEnabled(false);
			accountNum.setEnabled(false);

			company.setEnabled(true);
			cardNum.setEnabled(true);
		} else if (e.getSource().equals(submitB)) // Ȯ�� ��ư ������ ��
		{
			String method, com, num;
			if (bank.isEnabled()) { 
				method = "������ü";
				com = (String) bank.getSelectedItem();
			}
			else {
				method = "�ſ�ī��";
				com = (String) company.getSelectedItem();
			}

			if (accountNum.isEnabled())
				num = accountNum.getText();
			else
				num = cardNum.getText();

			// ������ ���� �غ�

			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				// �ش� �����ͺ��̽� ���

				PreparedStatement ps = con.prepareStatement("insert into login set id = '" + idValue 
						+ "', password = '" + pwValue + "'");
				ps.executeUpdate();
				// �α��� ���̺� ���ڵ� �߰�

				ps = con.prepareStatement("insert into profile set id = ?, name = ?, phone = ?, "
						+ "flat_rate = 'LTE 34', call_volume = 160, data = 750, message = 200, sex = ?, birth = ?");
				ps.setString(1, idValue);
				ps.setString(2, nameValue);
				ps.setString(3, phoneValue);
				ps.setString(4, sexValue);
				ps.setString(5, birthValue);
				ps.executeUpdate();
				// ȸ������ ���̺� ���ڵ� �߰�

				ps = con.prepareStatement("insert into chargemethod set id = '" + idValue
						+ "', method = ?, bank_company = ?, number = ?");
				ps.setString(1, method);
				ps.setString(2, com);
				ps.setString(3, num);
				ps.executeUpdate();
				// û������ ���̺� ���ڵ� �߰�

				ps = con.prepareStatement("insert into membership set id = ?, rank = ?, point = ?");
				ps.setString(1, idValue);
				ps.setString(2, "Bronze");
				ps.setInt(3, 60000);
				ps.executeUpdate();
				// ����� ���̺� ���ڵ� �߰�

				ps = con.prepareStatement("insert into homeservice set id = ?, tv = '����', "
						+ "internet = '����', IoT = '����'");
				ps.setString(1, idValue);
				ps.executeUpdate();
				// Ȩ���� ���̺� ���ڵ� �߰�

				ps = con.prepareStatement("insert into roaming set id = ?, country = null");
				ps.setString(1, idValue);
				ps.executeUpdate();
				// �ι� ���̺� ���ڵ� �߰�

				ps = con.prepareStatement(
						"insert into additional set id = ?, basic = false, best = false, study = false,"
						+ " smart = false, kakao = false, music = false;");
				ps.setString(1, idValue);
				ps.executeUpdate();
				// �ΰ����� ���̺� ���ڵ� �߰�

				String query = "create table charge_" + idValue
						+ "(date datetime not null primary key, pay Integer not null)";
				st.executeUpdate(query);
				// �ش� ���̵� û�� ���̺� ����

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
			// �����ͺ��̽��� ����

			user = new User(idValue, nameValue, phoneValue, "LTE 34", 160, 750, 200);
			JOptionPane.showMessageDialog(null, "ȸ�����ԵǾ����ϴ�.");
			Main main = new Main(user);
			setVisible(false);
			dispose();
		} 
		else if (e.getSource().equals(backB)) { //��ҹ�ư ���ý� 
			newMember newMember = new newMember(user);
			setVisible(false);
			dispose();
			// ��ҽ� �������� �ʰ� ����
		}
	}
}