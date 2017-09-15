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
	String roamingTableName[]={"국가","전화발신","전화수신","문자발신", "데이터"};
	JPanel memberP;
	JLabel myRoaming;
	
	final int Search_country = 0;
	
	foreignCountryRoaming(User user){
		
		this.user = user;
		
		setLayout(null);
		setTitle("해외로밍");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임 설정
		
		JLabel title = new JLabel("국가별 로밍 요금 조회, 신청(변경) 및 해지");
		title.setLocation(70, 35);
		title.setSize(300, 20);
		add(title);
		//타이틀
		
		memberP = new JPanel();
		memberP.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		memberP.setBorder((Border) new TitledBorder(null, "로밍정보조회", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		memberP.setBounds(30, 60, 320, 75);
		myRoaming = new JLabel();
		memberP.add(myRoaming);
		//현재 로밍정보를 담는 패널
		
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//해당 데이터베이스 사용
			
			ResultSet rs = st.executeQuery("select * from roaming where id = '" + user.id + "'");
			while(rs.next())
			{
				if(rs.getString("country") == null)
					myRoaming.setText("현재 신청 된 로밍 : 없음");
				else
					myRoaming.setText("현재 신청 된 로밍 : " + rs.getString("country"));
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
		//현재 로밍정보 가져오기
		
		searchB = new JButton("검색");
		searchB.setLocation(260,150);
		searchB.setSize(70,20);
		add(searchB);
		searchB.addActionListener(this);
		//검색 버튼
		
		searchText=new JTextField("");
		searchText.setLocation(50,150);
		searchText.setSize(200, 20);
		add(searchText);
		searchText.setColumns(10);
		//검색 텍스트창
		
		backB = new JButton("뒤로가기");
		backB.setLocation(270,10);
		backB.setSize(90,20);
		add(backB);
		backB.addActionListener(this);
		//뒤로가기 버튼
		
		JPanel body = new JPanel();
		body.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		body.setBackground(Color.white);
		body.setSize(370,220);
		body.setLocation(10,180);
		//내용 출력할 패녈
		
		changeB = new JButton("변경");
		changeB.setLocation(120,420);
		changeB.setSize(70,20);
		add(changeB);
		changeB.addActionListener(this);
		//변경 버튼
		
		cancelB = new JButton("해지");
		cancelB.setLocation(220,420);
		cancelB.setSize(70,20);
		add(cancelB);
		cancelB.addActionListener(this);
		//해지 버튼
		
		model = new DefaultTableModel(roamingTableName,0);
		roamingTable = new JTable(model);
		roamingTable.getColumnModel().getColumn(Search_country ).setMinWidth(80);
		roamingTable.getColumnModel().getColumn(1).setMinWidth(55);
		roamingTable.getColumnModel().getColumn(2).setMinWidth(55);
		roamingTable.getColumnModel().getColumn(3).setMinWidth(55);
		roamingTable.getColumnModel().getColumn(4).setMinWidth(53);
		roamingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//단일선택
		
		roamingTable.setRowHeight(30);
		String arr[]={"국가","전화발신","전화수신","문자발신","데이터"};
		
		//로밍 테이블
		
		JScrollPane scroll = new JScrollPane(roamingTable);
		scroll.setPreferredSize(new Dimension(350,190));
		body.add(scroll);
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//해당 데이터베이스 사용
			
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
			//데이터베이스에서 로밍정보를 읽어온다
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
			roamingTable.setRowSelectionInterval(0, 0); // 첫째줄에 포커싱
		
		add(body);
		
		setVisible(true); // 프레임을 화면에 출력
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
			// 국가
			case Search_country:
				if(KeyWord.equals("")) //전부 검색
					rs = st.executeQuery("select * from roamingCost");
				else //특정 검색
					rs = st.executeQuery("select * from roamingCost where country = '" + KeyWord + "'");
				break;
			}

			// DefaultTableModel 초기화
			model.setRowCount(0);
			String arr[]={"국가","전화발신","전화수신","문자발신","데이터"};
			
			while(rs.next())
			{
				arr[0] = rs.getString("country");
				arr[1] = rs.getString("callSend");
				arr[2] = rs.getString("callRecieve");
				arr[3] = rs.getString("messageSend");
				arr[4] = rs.getString("dataCost");
				model.addRow(arr);
			}//쿼리 결과를 새로 테이블에 출력
			rs.close();
			
			if (model.getRowCount() > 0)
				roamingTable.setRowSelectionInterval(0, 0); // 첫째줄에 포커싱
		}
		catch (SQLException e) {
		}finally{
			if(con != null)
				con.close();
		}
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(backB)) // 뒤로가기 버튼 눌렀을 때
		{
			roaming back = new roaming(user);
			setVisible(false);
			dispose();
		}
		else if(e.getSource().equals(searchB)) // 검색 버튼 눌렀을 때
		{
			try {
				searchMember(Search_country, searchText.getText()); //검색 메소드
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(changeB)) //변경버튼 눌렀을 때
		{
			Connection con = null;
			try{
				con = DriverManager.getConnection("jdbc:mysql://localhost","root", "kkiinngg31");
		    	Statement st = con.createStatement();
		    	st.executeQuery("use javaproject");
		    	
		    	int row = roamingTable.getSelectedRow();
		    	int col = 0;
		    	Object value = roamingTable.getValueAt(row, col);
		    	//해당 테이블의 첫번째 열(국가)정보를 가져온다
		    	
		    	st.executeUpdate("update roaming set country = '" + String.valueOf(value) + "' where id = '" + user.id + "'");
		    	//로밍 변경
		    	
		    	JOptionPane.showMessageDialog(null, "변경되었습니다.");
				myRoaming.setText("현재 신청 된 로밍 : " + String.valueOf(value));
				//변경 내용 화면에 갱신
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
			//패널 다시 그리기
		}
		else if(e.getSource().equals(cancelB)) //해지버튼 눌렀을 때
		{
			Connection con = null;
			try{
				con = DriverManager.getConnection("jdbc:mysql://localhost","root", "kkiinngg31");
		    	Statement st = con.createStatement();
		    	st.executeQuery("use javaproject");	
		    	ResultSet rs = st.executeQuery("select * from roaming where id = '" + user.id + "'");
		    	
		    	while(rs.next())
		    	{
		    		if(rs.getString("country") == null) //로밍 등록이 안되있을 경우
		    			JOptionPane.showMessageDialog(null, "신청된 로밍이 없습니다.");	
		    		else
		    		{
		    			int result = JOptionPane.showConfirmDialog(null, "현재 신청된 로밍을 해지하시겠습니까?", "로밍 해지", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		    			//확인창으로 해지 결정
		    			if(result == JOptionPane.YES_OPTION)
		    			{
		    				st.executeUpdate("update roaming set country = null where id = '" + user.id + "'");
		    				JOptionPane.showMessageDialog(null, "해지되었습니다.");
		    				myRoaming.setText("현재 신청 된 로밍 : 없음");
		    				//해지 후 변경 내용 화면에 갱신
		    				memberP.repaint();
		    				//패널을 다시 그린다
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
