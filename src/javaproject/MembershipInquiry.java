package javaproject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//멤버쉽 조회 확인
public class MembershipInquiry extends JFrame implements ActionListener{

	User user;
	JButton b1;
	
	MembershipInquiry(User user)
	{
		this.user = user; //유저 클래스 받아오기
		
		setLayout(null);
		setTitle("멤버쉽 조회");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임
	
		JLabel title;
		title = new JLabel("멤버쉽 조회");
		title.setLocation(120, 20);
		title.setSize(140, 20);
		title.setFont(new Font("돋음", Font.BOLD, 22));
		add(title);
		//타이틀
		
		b1 = new JButton("뒤로가기");
		b1.setLocation(280, 10);
		b1.setSize(90, 30);
		b1.addActionListener(this);
		add(b1);
		//뒤로가기 버튼
			
		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.LEFT,20,15));
		pn1.setBackground(Color.white);
		pn1.setBounds(20, 70, 330, 100);
		add(pn1);
		//멤버쉽 정보를 나타낼 패널
		
		Font font = new Font("돋음", Font.PLAIN, 18);
		JLabel rank = new JLabel(); //등급
		rank.setFont(font);
		JLabel point = new JLabel(); //포인트
		point.setFont(font);
		//패널안에 내용을 라벨
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//해당 데이터베이스 사용
			
			ResultSet rs = st.executeQuery("select * from membership where id = '" + user.id + '\'');
			while(rs.next())
			{
				rank.setText(user.name + "님의 멤버쉽 등급 : " + rs.getString("rank"));
				point.setText("잔여 멤버쉽 포인트 : " + rs.getString("point") + " 점");
			}
			//사용자의 멤버쉽 등급을 받아온다	
			rs.close();
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
		
		pn1.add(rank); 
		pn1.add(point); 
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn2.setBorder((Border) new TitledBorder(null, "멤버쉽 등급 및 한도", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(20, 190, 330, 240);
		add(pn2);
		//멤버쉽 기준 패널
		
		String tableName[] = {"등급", "요금제 월정액", "포인트 한도"};
		DefaultTableModel model = new DefaultTableModel(tableName, 0); 
		JTable table = new JTable(model);
		table.setEnabled(false);
		table.getColumnModel().getColumn(0).setMinWidth(100); 
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.setRowHeight(40); 
		String arr[] = {"등급", "요금제 월정액", "포인트 한도"};
		model.addRow(arr);
		//멤버쉽 기준 출력 테이블
		
		arr[0] = "VIP"; arr[1] = "100000원 이상"; arr[2] = "120000점";	model.addRow(arr);	
		arr[0] = "Gold"; arr[1] = "72000원 이상"; arr[2] = "100000점";	model.addRow(arr);	
		arr[0] = "Silver"; arr[1] = "52000원 이상"; arr[2] = "80000점";	model.addRow(arr);	
		arr[0] = "Bronze"; arr[1] = "34000원 이상"; arr[2] = "60000점";	model.addRow(arr);	
		//기준값들을 설정
		
		pn2.add(table);
		
		setVisible(true); //프레임을 화면에 출력
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //뒤로가기버튼을 눌렀을 경우
		{
			Membership back = new Membership(user);
			setVisible(false);
		    dispose();
		}
	}

}
