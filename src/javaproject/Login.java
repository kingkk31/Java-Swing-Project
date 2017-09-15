package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//�α��� Ŭ����
public class Login extends JFrame implements ActionListener{
	User user;
	JTextField idText;
	JPasswordField  passwordText;
	JButton submitB, cancelB;
	Component co;
	
	Login(User user)
	{
		this.user=user;
		
		setLayout(null);
		setTitle("ȯ���մϴ�.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������ ����
		
		JLabel idL = new JLabel("���̵�");
		idL.setLocation(50, 140);
		idL.setSize(100, 50);
		add(idL);
		idText = new JTextField("");
		idText.setLocation(140, 150);
		idText.setSize(200, 25);
		add(idText);
		idText.addActionListener(this);
		//���̵� 
		
		JLabel passwordL = new JLabel("��й�ȣ");
		passwordL.setLocation(45, 200);
		passwordL.setSize(120, 50);
		add(passwordL);
		passwordText = new JPasswordField();
		passwordText.setLocation(140, 210);
		passwordText.setSize(200, 25);
		add(passwordText);
		passwordText.addActionListener(this);
		//��й�ȣ - �Ⱥ��̰� ó��
		
		submitB = new JButton("Ȯ��");
		submitB.setLocation(100,300);
		submitB.setSize(80, 25);
		submitB.addActionListener(this);
		add(submitB);
		//Ȯ��
		
		cancelB = new JButton("���");
		cancelB.setLocation(210,300);
		cancelB.setSize(80, 25);
		cancelB.addActionListener(this);
		add(cancelB);
		//���
		
		setVisible(true); // �������� ȭ�鿡 ���
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(submitB)) // Ȯ�� ��ư ������ ��
		{
			if(idText.getText()== null || idText.getText().length() ==0)
			{
				JOptionPane.showMessageDialog(co,"���̵� �Է��Ͽ� �ֽʽÿ�");
			}//���̵� �Է¾����� ���
			else if(passwordText.getText()== null || passwordText.getText().length() ==0)
			{
				JOptionPane.showMessageDialog(co,"��й�ȣ�� �Է��Ͽ� �ֽʽÿ�");
			}//��й�ȣ �Է� ������ ���
			else
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//�ش� �����ͺ��̽� ���
					
					String id = idText.getText(); //�Է� ���̵�
					String pw = null; //�����ͺ��̽����� �޾� �� ��й�ȣ
					ResultSet rs = st.executeQuery("select * from login where id = '" + id + '\''); //�ش� ���̵��� ���ڵ带 ������
					while(rs.next()){
						pw = rs.getString("password"); //�����ͺ��̽��� ��й�ȣ�� �����´�
					}
					
					if(pw != null && passwordText.getText().equals(pw)) //��й�ȣ�� �����ϰ� �Էµ� ��й�ȣ�� ��ġ ��
					{
						rs = st.executeQuery("select * from profile where id = '" + idText.getText() + '\''); //����� ������ ������
						while(rs.next()){
							user = new User((String)rs.getString("id"), (String)rs.getString("name"), (String)rs.getString("phone")
								, (String)rs.getString("flat_rate"), Integer.parseInt((String)rs.getString("call_volume"))
								, Long.parseLong((String)rs.getString("data")), Integer.parseInt((String)rs.getString("message")));
							//������� ��ü�� �����
						}
						
						Main main = new Main(user);
						setVisible(false);
						dispose();
						//����ȭ������ �Ѿ��.
					}
					else
						JOptionPane.showMessageDialog(null, "���̵� ��й�ȣ�� Ȯ�����ּ���.");
					
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
			}//��� �� �Է����� ���			
		}
		else if(e.getSource().equals(cancelB)) // ��� ��ư ������ ��
		{
			FirstPage first = new FirstPage(user);
			setVisible(false);
			dispose();
		}
	}
}
