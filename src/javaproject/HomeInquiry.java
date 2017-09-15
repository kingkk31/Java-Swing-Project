package javaproject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

//요금조회
class HomeInquiry extends JFrame implements ActionListener{
	
	User user;
	JButton b1;
	
	HomeInquiry(User user)
	{
		this.user = user; //유저 클래스 받아오기
		
		setLayout(null);
		setTitle("홈서비스 조회");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임
	
		JPanel body;
		JLabel title, name, tv, internet, IoT;
		
		title = new JLabel("홈서비스 조회");
		title.setLocation(110, 20);
		title.setSize(160, 20);
		title.setFont(new Font("돋음", Font.BOLD, 22));
		add(title);
		//타이틀
		
		body = new JPanel();
		body.setBackground(Color.white);
		body.setSize(330,350);
		body.setLocation(20, 70);
		//메인 바디 부분
		
		Font bodyFont = new Font("돋음", Font.PLAIN, 18);
		name = new JLabel();
		name.setFont(bodyFont);
		name.setText(user.name + "님의 홈서비스 조회 내역 ");
		//사용자의 홈서비스 조회내역 출력
		
		tv = new JLabel();
		tv.setFont(bodyFont);
		internet = new JLabel();
		internet.setFont(bodyFont);
		IoT = new JLabel();
		IoT.setFont(bodyFont);
		//서비스 내용을 적을 라벨들 생성
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//해당 데이터베이스 사용
			
			ResultSet rs = st.executeQuery("select * from homeService where id = '" + user.id + '\'');
			while(rs.next())
			{
				if(rs.getString("tv") == null)
					tv.setText("TV : 없음                                                                ");
				else
					tv.setText("TV : " + rs.getString("tv") + "                            ");
				   
				if(rs.getString("internet") == null)
					internet.setText("인터넷 : 없음                                                                    ");
				else
					internet.setText("인터넷 : " + rs.getString("internet") + "                               ");
				
				if(rs.getString("IoT") == null)
					IoT.setText("IoT : 없음");
				else
					IoT.setText("IoT : " + rs.getString("IoT"));
			}
			//결과를 라벨에 입력	
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
		
		body.setLayout(new FlowLayout(FlowLayout.LEFT));
		body.add(name);
		body.add(tv);
		body.add(internet);
		body.add(IoT);
		
		add(body);
		//메인바디에 조회결과 넣기
		
		b1 = new JButton("뒤로가기");
		b1.setLocation(280, 10);
		b1.setSize(90, 30);
		b1.addActionListener(this);
		add(b1);
		//뒤로가기 버튼
			
		setVisible(true); //프레임을 화면에 출력
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) // 뒤로가기 버튼 누를 경우
		{
			HomeService back = new HomeService(user);
			setVisible(false);
		    dispose();
		}
	}
}