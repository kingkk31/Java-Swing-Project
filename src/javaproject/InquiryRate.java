package javaproject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

//요금조회 클래스
class InquiryRate extends JFrame implements ActionListener{
	
	User user;
	JButton b1;
	
	InquiryRate(User user)
	{
		this.user = user; //유저 클래스 받아오기
		
		setLayout(null);
		setTitle("요금조회");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임
	
		JPanel body;
		JLabel title, name, flat_rate, call_volume, data, message;
		
		title = new JLabel("요금조회");
		title.setLocation(140, 20);
		title.setSize(100, 20);
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
		flat_rate = new JLabel();
		flat_rate.setFont(bodyFont);
		call_volume = new JLabel();
		call_volume.setFont(bodyFont);
		data = new JLabel();
		data.setFont(bodyFont);
		message = new JLabel();
		message.setFont(bodyFont);
		//폰트지정
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//해당 데이터베이스 사용
			
			ResultSet rs = st.executeQuery("select * from profile where id = '" + user.id + '\'');
			while(rs.next())
			{
				name.setText(rs.getString("name") + "님의 요금조회 내역 ");
				flat_rate.setText("이용 요금제 : " + rs.getString("flat_rate") + "     ");
				call_volume.setText("잔여 음성 : " + rs.getString("call_volume") + "분           ");
				data.setText("잔여 데이터 : " + rs.getString("data") + "MB       ");
				message.setText("잔여 메세지 : " + rs.getString("message") + "건         ");
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
		body.add(flat_rate);
		body.add(call_volume);
		body.add(data);
		body.add(message);
		
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
		if (e.getSource().equals(b1))//뒤로가기 버튼을 누를 경우
		{
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		}
	}
}