package javaproject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class foreignCountryRoaming extends JFrame implements ActionListener {
	private static final String TableCellRenderer = null;
	User user;
	
	JButton roamingGuide, backB, eachCountryCost, searchB, changeB, cancelB;
	JTable roamingTable;
	JTextField searchText;
	DefaultTableModel model;
	String roamingTableName[]={"����","��ȭ�߽�","��ȭ����","���ڹ߽�", "������"};
	JPanel memberP;
	JLabel myRoaming;
	
	final int Search_country = 0;
	
	foreignCountryRoaming(User user){
		
		this.user = user;
		
		setLayout(null);
		setTitle("�ؿܷι�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//��ü ������ ����
		
		JLabel title = new JLabel("������ �ι� ��� ��ȸ, ��û(����) �� ����");
		title.setLocation(70, 35);
		title.setSize(300, 20);
		add(title);
		//Ÿ��Ʋ
		
		memberP = new JPanel();
		memberP.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		memberP.setBorder((Border) new TitledBorder(null, "�ι�������ȸ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		memberP.setBounds(30, 60, 320, 75);
		myRoaming = new JLabel();
		memberP.add(myRoaming);
		//���� �ι������� ��� �г�
		
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//�ش� �����ͺ��̽� ���
			
			ResultSet rs = st.executeQuery("select * from roaming where id = '" + user.id + "'");
			while(rs.next())
			{
				if(rs.getString("country") == null)
					myRoaming.setText("���� ��û �� �ι� : ����");
				else
					myRoaming.setText("���� ��û �� �ι� : " + rs.getString("country"));
			}
		}
		catch(SQLException e){
		}finally{
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		add(memberP);
		//���� �ι����� ��������
		
		searchB = new JButton("�˻�");
		searchB.setLocation(260,150);
		searchB.setSize(70,20);
		add(searchB);
		searchB.addActionListener(this);
		//�˻� ��ư
		
		searchText=new JTextField("");
		searchText.setLocation(50,150);
		searchText.setSize(200, 20);
		add(searchText);
		searchText.setColumns(10);
		//�˻� �ؽ�Ʈâ
		
		backB = new JButton("�ڷΰ���");
		backB.setLocation(270,10);
		backB.setSize(90,20);
		add(backB);
		backB.addActionListener(this);
		//�ڷΰ��� ��ư
		
		JPanel body = new JPanel();
		body.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		body.setBackground(Color.white);
		body.setSize(370,220);
		body.setLocation(10,180);
		//���� ����� �г�
		
		changeB = new JButton("����");
		changeB.setLocation(120,420);
		changeB.setSize(70,20);
		add(changeB);
		changeB.addActionListener(this);
		//���� ��ư
		
		cancelB = new JButton("����");
		cancelB.setLocation(220,420);
		cancelB.setSize(70,20);
		add(cancelB);
		cancelB.addActionListener(this);
		//���� ��ư
		
		model = new DefaultTableModel(roamingTableName,0);
		roamingTable = new JTable(model);
		roamingTable.getColumnModel().getColumn(Search_country ).setMinWidth(80);
		roamingTable.getColumnModel().getColumn(1).setMinWidth(55);
		roamingTable.getColumnModel().getColumn(2).setMinWidth(55);
		roamingTable.getColumnModel().getColumn(3).setMinWidth(55);
		roamingTable.getColumnModel().getColumn(4).setMinWidth(53);
		roamingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//���ϼ���
		
		roamingTable.setRowHeight(30);
		String arr[]={"����","��ȭ�߽�","��ȭ����","���ڹ߽�","������"};
		
		//�ι� ���̺�
		
		JScrollPane scroll = new JScrollPane(roamingTable);
		scroll.setPreferredSize(new Dimension(350,190));
		body.add(scroll);
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//�ش� �����ͺ��̽� ���
			
			ResultSet rs = st.executeQuery("select * from roamingCost");
			while(rs.next())
			{
				arr[0] = rs.getString("country");
				arr[1] = rs.getString("callSend");
				arr[2] = rs.getString("callRecieve");
				arr[3] = rs.getString("messageSend");
				arr[4] = rs.getString("dataCost");
				model.addRow(arr);
			}
			rs.close();
			//�����ͺ��̽����� �ι������� �о�´�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		if (model.getRowCount() > 0)
			roamingTable.setRowSelectionInterval(0, 0); // ù°�ٿ� ��Ŀ��
		
		add(body);
		
		setVisible(true); // �������� ȭ�鿡 ���
	}

	public void searchMember(int searchMode, String KeyWord) throws SQLException
	{
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.execute("use javaproject");
			ResultSet rs = null;

			switch (searchMode) {
			// ����
			case Search_country:
				if(KeyWord.equals("")) //���� �˻�
					rs = st.executeQuery("select * from roamingCost");
				else //Ư�� �˻�
					rs = st.executeQuery("select * from roamingCost where country = '" + KeyWord + "'");
				break;
			}

			// DefaultTableModel �ʱ�ȭ
			model.setRowCount(0);
			String arr[]={"����","��ȭ�߽�","��ȭ����","���ڹ߽�","������"};
			
			while(rs.next())
			{
				arr[0] = rs.getString("country");
				arr[1] = rs.getString("callSend");
				arr[2] = rs.getString("callRecieve");
				arr[3] = rs.getString("messageSend");
				arr[4] = rs.getString("dataCost");
				model.addRow(arr);
			}//���� ����� ���� ���̺� ���
			rs.close();
			
			if (model.getRowCount() > 0)
				roamingTable.setRowSelectionInterval(0, 0); // ù°�ٿ� ��Ŀ��
		}
		catch (SQLException e) {
		}finally{
			if(con != null)
				con.close();
		}
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(backB)) // �ڷΰ��� ��ư ������ ��
		{
			roaming back = new roaming(user);
			setVisible(false);
			dispose();
		}
		else if(e.getSource().equals(searchB)) // �˻� ��ư ������ ��
		{
			try {
				searchMember(Search_country, searchText.getText()); //�˻� �޼ҵ�
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(changeB)) //�����ư ������ ��
		{
			Connection con = null;
			try{
				con = DriverManager.getConnection("jdbc:mysql://localhost","root", "kkiinngg31");
		    	Statement st = con.createStatement();
		    	st.executeQuery("use javaproject");
		    	
		    	int row = roamingTable.getSelectedRow();
		    	int col = 0;
		    	Object value = roamingTable.getValueAt(row, col);
		    	//�ش� ���̺��� ù��° ��(����)������ �����´�
		    	
		    	st.executeUpdate("update roaming set country = '" + String.valueOf(value) + "' where id = '" + user.id + "'");
		    	//�ι� ����
		    	
		    	JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.");
				myRoaming.setText("���� ��û �� �ι� : " + String.valueOf(value));
				//���� ���� ȭ�鿡 ����
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
			
			memberP.repaint();
			//�г� �ٽ� �׸���
		}
		else if(e.getSource().equals(cancelB)) //������ư ������ ��
		{
			Connection con = null;
			try{
				con = DriverManager.getConnection("jdbc:mysql://localhost","root", "kkiinngg31");
		    	Statement st = con.createStatement();
		    	st.executeQuery("use javaproject");	
		    	ResultSet rs = st.executeQuery("select * from roaming where id = '" + user.id + "'");
		    	
		    	while(rs.next())
		    	{
		    		if(rs.getString("country") == null) //�ι� ����� �ȵ����� ���
		    			JOptionPane.showMessageDialog(null, "��û�� �ι��� �����ϴ�.");	
		    		else
		    		{
		    			int result = JOptionPane.showConfirmDialog(null, "���� ��û�� �ι��� �����Ͻðڽ��ϱ�?", "�ι� ����", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		    			//Ȯ��â���� ���� ����
		    			if(result == JOptionPane.YES_OPTION)
		    			{
		    				st.executeUpdate("update roaming set country = null where id = '" + user.id + "'");
		    				JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
		    				myRoaming.setText("���� ��û �� �ι� : ����");
		    				//���� �� ���� ���� ȭ�鿡 ����
		    				memberP.repaint();
		    				//�г��� �ٽ� �׸���
		    			}
		    		}
		    	}
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
			
		}
	}
}
