package javaproject;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

//이용중인 부가서비스 확인
public class myServiceCheck  extends JFrame implements ActionListener {
	User user;
	JButton applyServiceB, cancelServiceB , backB;
	JTextField currentService;
	JCheckBox[] checkbox = new JCheckBox[6];
	Checkbox basicPack, bestPack, studyPack, smartGuard, kakaoPack, musicPack;
	JTable table1, table2;
	DefaultTableModel model1, model2;
	
	myServiceCheck(User user)
	{
		this.user =user;
		setLayout(null);
		setTitle("이용중인 부가서비스 확인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setResizable(false);
		// 전체 프레임 설정
		
		backB = new JButton("뒤로가기");
		backB.setLocation(280, 10);
		backB.setSize(90, 30);
		backB.addActionListener(this);
		add(backB);
		//뒤로가기 버튼
				
		JLabel checkT = new JLabel("이용중인 부가서비스");
		checkT.setLocation(100, 40);
		checkT.setSize(250, 20);
		checkT.setFont(new Font("돋음", Font.BOLD, 18));
		add(checkT);
		//타이틀
				
		JPanel currentServiceP = new JPanel();
		currentServiceP.setLayout(new FlowLayout(FlowLayout.CENTER));
		currentServiceP.setBorder((Border) new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		currentServiceP.setBounds(20,80,345,145);
		add(currentServiceP);
		//이용중인 부가서비스 패널
					
		String arr1[] = {"기본팩", "알짜팩", "스터디팩"};
		String arr2[] = {"스마트지킴이", "카카오팩", "음악팩"};
		model1= new DefaultTableModel(arr1,0);
		model2= new DefaultTableModel(arr2,0);
		table1 = new JTable(model1);
		table2 = new JTable(model2);
		table1.setEnabled(false);
		table2.setEnabled(false);
		table1.getColumnModel().getColumn(0).setMinWidth(55);
		table1.getColumnModel().getColumn(1).setMinWidth(55);
		table1.getColumnModel().getColumn(2).setMinWidth(55);
		table2.getColumnModel().getColumn(0).setMinWidth(55);
		table2.getColumnModel().getColumn(1).setMinWidth(55);
		table2.getColumnModel().getColumn(2).setMinWidth(55);
		table1.setRowHeight(30);
		table2.setRowHeight(30);
		//이용중인 부가서비스 출력 테이블
		
		JScrollPane scroll1 = new JScrollPane(table1);
		scroll1.setPreferredSize(new Dimension(340,65));
		currentServiceP.add(scroll1);
		JScrollPane scroll2 = new JScrollPane(table2);
		scroll2.setPreferredSize(new Dimension(340,65));
		currentServiceP.add(scroll2);
		//스크롤
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//해당 데이터베이스 사용
			
			ResultSet rs = st.executeQuery("select * from additional where id = '" + user.id + "'");
			while(rs.next()) //디비에 있는 부분 읽기
			{
				arr1[0] = (rs.getInt("basic") == 0) ? "미신청" : "신청"; //체크 될 경우에 신청, 체크 안될 경우 미신청
				arr1[1] = (rs.getInt("best") == 0) ? "미신청" : "신청";
				arr1[2] = (rs.getInt("study") == 0) ? "미신청" : "신청";
				arr2[0] = (rs.getInt("smart") == 0) ? "미신청" : "신청";
				arr2[1] = (rs.getInt("kakao") == 0) ? "미신청" : "신청";
				arr2[2] = (rs.getInt("music") == 0) ? "미신청" : "신청";
				model1.addRow(arr1);
				model2.addRow(arr2);
			}
			//테이블 내용 출력
			rs.close();
			con.close();
			//데이터베이스에서 로밍정보를 읽어온다
		} catch (SQLException e) {
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
		
		JLabel applyT;
		applyT = new JLabel("부가서비스 신청");
		applyT.setLocation(120, 250);
		applyT.setSize(250, 20);
		applyT.setFont(new Font("돋음", Font.BOLD, 18));
		add(applyT);
		//타이틀
		
		applyServiceB = new JButton("신청(변경)");
		applyServiceB.setLocation(250,320);
		applyServiceB.setSize(100,40);
		add(applyServiceB);
		applyServiceB.addActionListener(this);
		//신청(변경) 버튼
		
		cancelServiceB = new JButton("해지");
		cancelServiceB.setLocation(250,370);
		cancelServiceB.setSize(100,40);
		add(cancelServiceB);
		cancelServiceB.addActionListener(this);
		//해지 버튼
		
		checkbox[0] = new JCheckBox("기본팩");
		checkbox[0].setBounds(40,320,80,20);
		checkbox[1] = new JCheckBox("알짜팩");
		checkbox[1].setBounds(40,355,80,20);
		checkbox[2] = new JCheckBox("스터디팩");
		checkbox[2].setBounds(40,390,80,20);
		checkbox[3] = new JCheckBox("스마트 지킴이");
		checkbox[3].setBounds(130,320,120,20);
		checkbox[4] = new JCheckBox("카카오팩");
		checkbox[4].setBounds(130,355,100,20);
		checkbox[5] = new JCheckBox("음악팩");
		checkbox[5].setBounds(130,390,80,20);
		add(checkbox[0]);
		add(checkbox[1]);
		add(checkbox[2]);
		add(checkbox[3]);
		add(checkbox[4]);
		add(checkbox[5]);
		// 체크박스로 각 부가서비스들 체크할 수 있게 함
		
		JPanel serviceCheckCancelP = new JPanel();
		serviceCheckCancelP.setLayout(new FlowLayout(FlowLayout.CENTER));
		serviceCheckCancelP.setBorder((Border) new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		serviceCheckCancelP.setBounds(20,290,345,145);
		add(serviceCheckCancelP);
		//이용중인 부가서비스 패널
	
		setVisible(true); // 프레임을 화면에 출력
	}
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource().equals(backB)) //뒤로가기버튼을 누를 경우
		{
			try {
				additionalService back = new additionalService(user);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			setVisible(false);
		    dispose();
		}
		if(e.getSource().equals(applyServiceB)) //신청버튼 누를 경우
		{
			String arr[] = {"basic", "best", "study", "smart", "kakao", "music"};
			ArrayList<String> item = new ArrayList<String>();
			for(int i=0;i<6;i++)
				if(checkbox[i].isSelected()) 
					item.add(arr[i]);
			
			String query = "update additional set ";
			for(int i=0;i<item.size();i++)
			{
				query += (item.get(i) + " = true");
				if(i != item.size()-1)
					query += ", ";
			}
			query += (" where id = '" + user.id + "'");
			
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//해당 데이터베이스 사용
				
				st.executeUpdate(query);
				con.close();
			} catch (SQLException e1) {
			}finally{
				if(con != null)
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			
			JOptionPane.showMessageDialog(null, "신청되었습니다.");
		}
		if(e.getSource().equals(cancelServiceB))//해지버튼 누를 경우
		{
			String arr[] = {"basic", "best", "study", "smart", "kakao", "music"};
			ArrayList<String> item = new ArrayList<String>();
			for(int i=0;i<6;i++)
				if(checkbox[i].isSelected())
					item.add(arr[i]);
			
			String query = "update additional set ";
			for(int i=0;i<item.size();i++)
			{
				query += (item.get(i) + " = false");
				if(i != item.size()-1)
					query += ", ";
			}
			query += (" where id = '" + user.id + "'");
			
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//해당 데이터베이스 사용
				
				st.executeUpdate(query);
				con.close();
			} catch (SQLException e1) {
			}finally{
				if(con != null)
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			
			JOptionPane.showMessageDialog(null, "해지되었습니다.");
		}
	}
}
