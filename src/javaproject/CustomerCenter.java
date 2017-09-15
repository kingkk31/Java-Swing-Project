package javaproject;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CustomerCenter extends JFrame implements ActionListener, MouseListener {
	User user;
	JTabbedPane boardTab = new JTabbedPane();
	JPanel noticeBoardP, freeBoardP, frequentlyQuestionP, pn3, innerPn3;
	JButton pre1, next1, pre2, next2, backB, pre3, next3, questionB, writeB;
	DefaultTableModel model1, model2, model3; 
	JTable table1, table2, table3;
	JScrollPane scroll1, scroll2, scroll3;
	int page1, cnt1, max_page1, page2, cnt2, max_page2, page3, cnt3, max_page3;
	JLabel currentPage1, currentPage2, currentPage3;
	
	CustomerCenter(User user)
	{
		this.user = user;
		
		setTitle("������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400,500);
		setResizable(false);
		// ��ü ������ ����
		
		Container con = getContentPane();
		
		noticeBoardP = new JPanel();
		noticeBoardP.setLayout(new FlowLayout(FlowLayout.CENTER,30,15));
		boardTab.addTab("��������", noticeBoardP);
		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn1.setBorder((Border) new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn1.setBounds(20, 60, 340, 300);
		noticeBoardP.add(pn1);
		//�������� �г�
		
		String tableName1[] = {"��ȣ", "����", "�����"};
		model1 = new DefaultTableModel(tableName1, 0); 
		table1 = new JTable(model1);
		table1.getColumnModel().getColumn(0).setMinWidth(40); 
		table1.getColumnModel().getColumn(1).setMinWidth(160);
		table1.getColumnModel().getColumn(2).setMinWidth(80);
		table1.setRowHeight(50); 
		String arr1[] = {"no", "title", "date"};
		//�������� ���̺�
		
		scroll1 = new JScrollPane(table1);
		scroll1.setPreferredSize(new Dimension(320, 280));
		pn1.add(scroll1);
		//��ũ��
	
		Connection con1 = null;
		try {
			con1 = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st1 = con1.createStatement();
			st1.executeQuery("use javaproject");
			//�ش� �����ͺ��̽� ���
			
			ResultSet rs1 = st1.executeQuery("select * from notice\n");
			rs1.last();
			cnt1 = rs1.getRow();
			rs1.beforeFirst(); 
			//���̺��� ���� �� �о����
			
			page1 = 0; //ù ������
			max_page1 = (int) Math.ceil(cnt1/5); //�ִ� ������
	
			rs1 = st1.executeQuery("select * from notice order by no desc limit 5 offset 0\n"); //���� ũ�⸸ŭ �߶�´�
			
			while(rs1.next())
			{
				arr1[0] = rs1.getString("no");
				arr1[1] = rs1.getString("title");
				arr1[2] = rs1.getString("date").substring(0, 11);
				model1.addRow(arr1);
			}//���̺� ���
			rs1.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con1 != null)
				try {
					con1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		table1.addMouseListener(this);
	
		pre1 = new JButton("pre");
		pre1.setLocation(60,370);
		pre1.setSize(80, 30);
		pre1.addActionListener(this);
		noticeBoardP.add(pre1);
		//������ư
		
		JPanel innerPn1 = new JPanel();
		innerPn1.setBounds(170, 370, 50, 50);
		noticeBoardP.add(innerPn1);
		
		currentPage1 = new JLabel();
		currentPage1.setText(String.valueOf(page1+1));
		innerPn1.add(currentPage1);
		//�� ������ ǥ��
		
		next1 = new JButton("next");
		next1.setLocation(240, 370);
		next1.setSize(80, 30);
		next1.addActionListener(this);
		noticeBoardP.add(next1);
		//������ư
		

		freeBoardP = new JPanel();
		freeBoardP.setLayout(new FlowLayout(FlowLayout.CENTER,30,15));
		boardTab.addTab("�����Խ���", freeBoardP);
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn2.setBorder((Border) new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(20, 60, 340, 300);
		freeBoardP.add(pn2);
		//�Խ��� �г�
		
		String tableName2[] = {"��ȣ", "����", "�����", "�ۼ���"};
		model2 = new DefaultTableModel(tableName2, 0); 
		table2 = new JTable(model2);
		table2.getColumnModel().getColumn(0).setMinWidth(40); 
		table2.getColumnModel().getColumn(1).setMinWidth(120);
		table2.getColumnModel().getColumn(2).setMinWidth(80);
		table2.getColumnModel().getColumn(3).setMinWidth(80);
		table2.setRowHeight(50); 
		String arr2[] = {"no", "title", "date", "id"};
		//�Խ��� ���̺�
		
		scroll2 = new JScrollPane(table2);
		scroll2.setPreferredSize(new Dimension(320, 280));
		pn2.add(scroll2);
		//��ũ��
	
		Connection con2 = null;
		try {
			con2 = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st2 = con2.createStatement();
			st2.executeQuery("use javaproject");
			//�ش� �����ͺ��̽� ���
			
			ResultSet rs2 = st2.executeQuery("select * from board\n");
			rs2.last();
			cnt2 = rs2.getRow();
			rs2.beforeFirst(); 
			//���̺��� ���� �� �о����
			
			page2 = 0; //ù ������
			max_page2 = (int) Math.ceil(cnt2/5); //�ִ� ������
			
			rs2 = st2.executeQuery("select * from board order by no desc limit 5 offset 0\n");
			
			while(rs2.next())
			{
				arr2[0] = rs2.getString("no");
				arr2[1] = rs2.getString("title");
				arr2[2] = rs2.getString("date").substring(0, 11);
				arr2[3] = rs2.getString("id");
				model2.addRow(arr2);
			}//���̺� ���
			rs2.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con2 != null)
				try {
					con2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		table2.addMouseListener(this);
	
		pre2 = new JButton("pre");
		pre2.setLocation(60,370);
		pre2.setSize(80, 30);
		pre2.addActionListener(this);
		freeBoardP.add(pre2);
		//������ư
		
		JPanel innerPn2 = new JPanel();
		innerPn2.setBounds(170, 370, 50, 50);
		freeBoardP.add(innerPn2);
		
		currentPage2 = new JLabel();
		currentPage2.setText(String.valueOf(page2+1));
		innerPn2.add(currentPage2);
		//���� ������ ǥ��

		next2 = new JButton("next");
		next2.setLocation(240, 370);
		next2.setSize(80, 30);
		next2.addActionListener(this);
		freeBoardP.add(next2);
		//������ư
		
		writeB = new JButton("�۾���");
		writeB.setSize(80, 25);
		writeB.addActionListener(this);
		freeBoardP.add(writeB);
		//�۾��� ��ư
		
		frequentlyQuestionP = new JPanel();
		frequentlyQuestionP.setLayout(new FlowLayout(FlowLayout.CENTER,30,15));
		boardTab.addTab("���ֹ��� ����", frequentlyQuestionP);
		pn3 = new JPanel();
		pn3.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn3.setBorder((Border) new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn3.setBounds(20, 60, 340, 300);
		frequentlyQuestionP.add(pn3);
		//���ֹ��� ���� �г�
		
		questionB = new JButton("�������");
		questionB.setSize(90, 40);
		questionB.addActionListener(this);
		//���ֹ��� �������� ������� ���ư��� ��ư
		
		String tableName3[] = {"��ȣ", "����"};
		model3 = new DefaultTableModel(tableName3, 0); 
		table3 = new JTable(model3);
		table3.getColumnModel().getColumn(0).setMinWidth(40); 
		table3.getColumnModel().getColumn(1).setMinWidth(280);
		table3.setRowHeight(50); 
		String arr3[] = {"no", "title"};
		//���ֹ��� ���� ���̺�
		
		scroll3 = new JScrollPane(table3);
		scroll3.setPreferredSize(new Dimension(320, 280));
		pn3.add(scroll3);
		//��ũ��
	
		Connection con3 = null;
		try {
			con3 = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st3 = con3.createStatement();
			st3.executeQuery("use javaproject");
			//�ش� �����ͺ��̽� ���
			
			ResultSet rs3 = st3.executeQuery("select * from QA\n");
			rs3.last();
			cnt3 = rs3.getRow();
			rs3.beforeFirst(); 
			//���̺��� ���� �� �о����
			
			page3 = 0; //ù ������
			max_page3 = (int) Math.ceil(cnt3/5); //�ִ� ������
			
			rs3 = st3.executeQuery("select * from QA order by no desc limit 5 offset 0\n");
			
			while(rs3.next())
			{
				arr3[0] = rs3.getString("no");
				arr3[1] = rs3.getString("title");
				model3.addRow(arr3);
			}//���̺� ���
			rs3.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con3 != null)
				try {
					con3.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		table3.addMouseListener(this);
	
		pre3 = new JButton("pre");
		pre3.setLocation(60,370);
		pre3.setSize(80, 30);
		pre3.addActionListener(this);
		frequentlyQuestionP.add(pre3);
		//������ư
		
		innerPn3 = new JPanel();
		innerPn3.setBounds(170, 370, 50, 50);
		frequentlyQuestionP.add(innerPn3);
		
		currentPage3 = new JLabel();
		currentPage3.setText(String.valueOf(page3+1));
		innerPn3.add(currentPage3);
		//���� ������ ǥ��

		next3 = new JButton("next");
		next3.setLocation(240, 370);
		next3.setSize(80, 30);
		next3.addActionListener(this);
		frequentlyQuestionP.add(next3);
		//������ư
	
		
		backB = new JButton("�ڷΰ���");
		backB.setSize(87, 25);
		backB.setLocation(280, 420);
		backB.addActionListener(this);
		add(backB);
		//�ڷΰ���
		
		con.add(boardTab);
		
		setVisible(true); //�������� ȭ�鿡 ���
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(backB)) //�ڷΰ���
		{
			Main back = new Main(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(next1)) //�������� ��������
		{
			if(max_page1 >= page1+1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//�ش� �����ͺ��̽� ���
					
					page1++; //������ ����
					model1.setRowCount(0);
					int offset1 = page1 * 5;
					currentPage1.setText(String.valueOf(page1+1));
					ResultSet rs = st.executeQuery("select * from notice order by no desc limit 5 offset " + String.valueOf(offset1));
					//������ �κ� �ٽ� �ҷ�����
					String arr[] = {"no", "title", "date"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						arr[2] = rs.getString("date").substring(0, 11);
						model1.addRow(arr);
					}//���̺� ���
					rs.close();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}

			}
		}
		else if (e.getSource().equals(pre1)) //�������� ��������
		{
			if(0 <= page1-1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//�ش� �����ͺ��̽� ���
					
					page1--; //������ ����
					model1.setRowCount(0);
					int offset1 = page1 * 5;
					currentPage1.setText(String.valueOf(page1+1));
					ResultSet rs = st.executeQuery("select * from notice order by no desc limit 5 offset " + String.valueOf(offset1));
					//������ �κ� �ٽ� �ҷ�����
					String arr[] = {"no", "title", "date"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						arr[2] = rs.getString("date").substring(0, 11);
						model1.addRow(arr);
					}//���̺� ���
					rs.close();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}

			}
		}
		else if (e.getSource().equals(next2)) //�Խ��� ��������
		{
			if(max_page2 >= page2+1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//�ش� �����ͺ��̽� ���
					
					page2++; //������ ����
					model2.setRowCount(0);
					int offset2 = page2 * 5;
					currentPage2.setText(String.valueOf(page2+1));
					ResultSet rs = st.executeQuery("select * from board order by no desc limit 5 offset " + String.valueOf(offset2));
					//������ �κ� �ٽ� �ҷ�����
					String arr[] = {"no", "title", "date", "id"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						arr[2] = rs.getString("date").substring(0, 11);
						arr[3] = rs.getString("id");
						model2.addRow(arr);
					}//���̺� ���
					rs.close();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}

			}
		}
		else if (e.getSource().equals(pre2)) //�Խ��� ��������
		{
			if(0 <= page2-1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//�ش� �����ͺ��̽� ���
					
					page2--; //������ ����
					model2.setRowCount(0);
					int offset2 = page2 * 5;
					currentPage2.setText(String.valueOf(page2+1));
					ResultSet rs = st.executeQuery("select * from board order by no desc limit 5 offset " + String.valueOf(offset2));
					//������ �κ� �ٽ� �ҷ�����
					String arr[] = {"no", "title", "date", "id"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						arr[2] = rs.getString("date").substring(0, 11);
						arr[3] = rs.getString("id");
						model2.addRow(arr);
					}//���̺� ���
					rs.close();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
		}
		else if (e.getSource().equals(next3)) //���ֹ��� ���� ��������
		{
			if(max_page3 >= page3+1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//�ش� �����ͺ��̽� ���
					
					page3++; //������ ����
					model3.setRowCount(0);
					int offset3 = page3 * 5;
					currentPage3.setText(String.valueOf(page3+1));
					ResultSet rs = st.executeQuery("select * from QA order by no desc limit 5 offset " + String.valueOf(offset3));
					//������ �κ� �ٽ� �ҷ�����
					String arr[] = {"no", "title"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						model3.addRow(arr);
					}//���̺� ���
					rs.close();
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
		}
		else if (e.getSource().equals(pre3)) //���ֹ������� ��������
		{
			if(0 <= page3-1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//�ش� �����ͺ��̽� ���
					
					page3--; //������ ����
					model3.setRowCount(0);
					int offset3 = page3 * 5;
					currentPage3.setText(String.valueOf(page3+1));
					ResultSet rs = st.executeQuery("select * from QA order by no desc limit 5 offset " + String.valueOf(offset3));
					//������ �κ� �ٽ� �ҷ�����
					String arr[] = {"no", "title"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						model3.addRow(arr);
					}//���̺� ���
					rs.close();
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}

			}
		}
		else if (e.getSource().equals(questionB)) //�����ϴ� �������� ������� ���ư��� ��ư
		{
			frequentlyQuestionP.removeAll(); //�г� ������ �����ٰ�
			frequentlyQuestionP.add(pn3);
			frequentlyQuestionP.add(pre3);
			frequentlyQuestionP.add(innerPn3);
			frequentlyQuestionP.add(next3);
			frequentlyQuestionP.repaint(); //��ϵ��� �ٽ� �߰��Ѵ�
		}
		else if (e.getSource().equals(writeB))
		{
			WriteBoard write = new WriteBoard(user);
			setVisible(false);
		    dispose();
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		CustomerCenter board = null;
		if(boardTab.getSelectedIndex() == 0) //�������� �� ���б�
		{
			int row = table1.getSelectedRow();
	    	int col = 0;
	    	Object value = null;
	    	value = table1.getValueAt(row, col);
	    	//�۹�ȣ �˾ƿ���
	    	
	    	String num = String.valueOf(value);
	    	ReadNotice read = new ReadNotice(num);
	    	//�ش� ���б�
		}
		else if(boardTab.getSelectedIndex() == 1) //�Խ��� �� ���б�
		{	
			int row = table2.getSelectedRow();
	    	int col = 0;
	    	Object value = null;
	    	value = table2.getValueAt(row, col);
	    	//�۹�ȣ �˾ƿ���
	    	
	    	String num = String.valueOf(value);
	    	ReadBoard read = new ReadBoard(num);
	    	//�ش� ���б�
		}
		else if(boardTab.getSelectedIndex() == 2) //���ֹ��� ���� �� �� �б�
		{	
			int row = table3.getSelectedRow();
	    	int col = 0;
	    	Object value = null;
	    	value = table3.getValueAt(row, col);
	    	//�۹�ȣ �˾ƿ���
	    	
	    	String num = String.valueOf(value);
	    	
	    	Connection con = null;
	    	try {
	    		con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
	    		Statement st = con.createStatement();
	    		st.executeQuery("use javaproject");
	    		//�ش� �����ͺ��̽� ���
	    		
	    		ResultSet rs = st.executeQuery("select * from QA where no = " + num);
	    		
	    		String str1 = null; //����
	    		String str4 = null; //����
	    		
	    		//���� �г��� ������ ����� �ش� �� ������ �����ش�
	    		frequentlyQuestionP.removeAll();
		    	while(rs.next())
	    		{
	    			str1 = (String)rs.getString("title");
	    			JTextField titleText = new JTextField(str1, 21);
	    			titleText.setFont(new Font("����", Font.PLAIN, 18));
	    			titleText.setBackground(Color.white);
	    			titleText.setEditable(false);
	    			frequentlyQuestionP.add(titleText);
	    			//���� ���
	    							
	    			str4 = (String)rs.getString("content");
	    			JTextArea contentText = new JTextArea(13, 20);
	    			contentText.setCaretPosition(0);
	    			contentText.setLineWrap(true);
	    			contentText.append(str4);
	    			contentText.setFont(new Font("����", Font.PLAIN, 18));
	    			contentText.setEditable(false);
	    			//���� ���
	    				
	    			JScrollPane scroll = new JScrollPane(contentText);
	    			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    			frequentlyQuestionP.add(scroll);
	    			//��ũ��
	    		}
	    		rs.close();
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

	    	frequentlyQuestionP.add(questionB);
	    	frequentlyQuestionP.repaint();
	    	//������� ��ư �߰� �� �ٽ� �׸���	    	
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}