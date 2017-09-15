package javaproject;

import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

//�Խñ� ���� Ŭ����
public class WriteBoard extends JFrame implements ActionListener{

	User user;
	JTextField titleText;
	JTextArea contentText;
	JButton submit, cancel;
	
	WriteBoard(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("�Խñ� ����");
		setSize(400,500);
		setResizable(false);
		//��ü ������
			
		JPanel pn = new JPanel();
		pn.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 15));
		pn.setBorder((Border) new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn.setBounds(20, 20, 340, 350);
		add(pn);
		//�Խ��� �г�
		
		JLabel tLabel = new JLabel("���� ");
		tLabel.setFont(new Font("����", Font.BOLD, 18));
		pn.add(tLabel);
		titleText = new JTextField();
		titleText.setColumns(17);
		titleText.setFont(new Font("����", Font.PLAIN, 20));
		pn.add(titleText);
		//���� �κ�
		
		contentText = new JTextArea(13, 20);
		contentText.setLineWrap(true);
		contentText.setFont(new Font("����", Font.PLAIN, 18));
		JScrollPane scroll = new JScrollPane(contentText);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pn.add(scroll);
		//���� �κ�
		
		submit = new JButton("���");
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
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(submit)) //�ڷΰ��� ��ư ���ý�
		{
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//�ش� �����ͺ��̽� ���
				
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String datetime = sdf.format(cal.getTime());
				//���� �ð�
				
				PreparedStatement ps = con.prepareStatement("insert into board set id = ?, date = ?, title = ?, content = ?");
				ps.setString(1, user.id);
				ps.setString(2, datetime);
				ps.setString(3, titleText.getText());
				ps.setString(4, contentText.getText());
				ps.executeUpdate();
				//�Խñ��� �����ͺ��̽��� �����Ѵ�
			}
		    catch(SQLException e1){
		    }finally{
		    	if(con != null)
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    }
			
			//�ۼ� �� ������ ȭ������ ���ư���.
			CustomerCenter back = new CustomerCenter(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(cancel)) //���
		{
			CustomerCenter back = new CustomerCenter(user);
			setVisible(false);
		    dispose();
		}
	}
}

