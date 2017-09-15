package javaproject;

import java.awt.*;
import java.awt.Font;

import java.sql.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

//�Խ��� �б� Ŭ����
public class ReadBoard extends JFrame {

	String num;
	ReadBoard(String num)
	{
		this.num = num;
		
		setLayout(null);
		setTitle("�����Խ���");
		setSize(400,500);
		setResizable(false);
		//��ü ������
			
		JPanel pn = new JPanel();
		pn.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 15));
		pn.setBorder((Border) new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn.setBounds(20, 20, 340, 400);
		add(pn);
		//�Խ��� �г�
		
		Font font = new Font("����", Font.PLAIN, 20);
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//�ش� �����ͺ��̽� ���
			
			ResultSet rs = st.executeQuery("select * from board where no = " + num);
			//�ش� ��ȣ�� �Խñ��� �����´�
			
			String str1 = null;
			String str2 = null;
			String str3 = null;
			String str4 = null;
			
			while(rs.next())
			{
				str1 = (String)rs.getString("title");
				JTextField titleText = new JTextField(str1, 21);
				titleText.setBackground(Color.white);
				titleText.setFont(font);
				titleText.setEditable(false);
				pn.add(titleText);
				//�ҷ��� �Խñ��� ���� ���
				
				str2 = (String)rs.getString("id");
				JTextField nameText = new JTextField(str2, 10);
				nameText.setBackground(Color.white);
				nameText.setFont(font);
				nameText.setEditable(false);
				pn.add(nameText);
				//�ҷ��� �Խñ��� �ۼ��� ���̵� ���
				
				str3 = (String)rs.getString("date");
				str3 = str3.substring(0, 11);
				JTextField dateText = new JTextField(str3, 10);
				dateText.setBackground(Color.white);
				dateText.setFont(font);
				dateText.setEditable(false);
				pn.add(dateText);
				//�ҷ��� �Խñ��� ��¥ ���
				
				str4 = (String)rs.getString("content");
				JTextArea contentText = new JTextArea(13, 20);
				contentText.setCaretPosition(0);
				contentText.setLineWrap(true);
				contentText.append(str4);
				contentText.setFont(new Font("����", Font.PLAIN, 18));
				contentText.setEditable(false);
				JScrollPane scroll = new JScrollPane(contentText);
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				pn.add(scroll);
				//�ҷ��� �Խñ��� ���� ���
			}
			rs.close();
		}
	    catch(SQLException e1){
	    }finally{
	    	if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }

		setVisible(true);
	}
}

