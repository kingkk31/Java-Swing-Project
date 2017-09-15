package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//멤버쉽
public class Membership extends JFrame implements ActionListener{

	User user;
	JButton b1, b2, b3;
	
	Membership(User user)
	{
		this.user = user; //유저 클래스 받아오기
		
		setLayout(null);
		setTitle("멤버쉽");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임
	
		JLabel title;
		title = new JLabel("멤버쉽");
		title.setLocation(140, 20);
		title.setSize(100, 20);
		title.setFont(new Font("돋음", Font.BOLD, 22));
		add(title);
		//타이틀
		
		b1 = new JButton("뒤로가기");
		b1.setLocation(280, 10);
		b1.setSize(90, 30);
		b1.addActionListener(this);
		add(b1);
		//뒤로가기 버튼
		
		b2 = new JButton("멤버쉽 조회");
		b2.setLocation(40, 160);
		b2.setSize(140, 50);
		b2.addActionListener(this);
		add(b2);
		//멤버쉽조회 버튼
		
		b3 = new JButton("맴버쉽 제휴사");
		b3.setLocation(200, 160);
		b3.setSize(140, 50);
		b3.addActionListener(this);
		add(b3);
		//멤버쉽 제휴사 버튼
		
		setVisible(true); //프레임을 화면에 출력
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //뒤로가기 버튼을 눌렀을 경우
		{
			Main back = new Main(user);
			setVisible(false);
		    dispose();
		}
		if (e.getSource().equals(b2)) //멤버쉽 조회 버튼을 눌렀을 경우
		{
			MembershipInquiry back = new MembershipInquiry(user);
			setVisible(false);
		    dispose();
		}
		if (e.getSource().equals(b3)) //멤버쉽 제휴사버튼을 눌렀을 경우
		{
			MembershipAffiliate back = new MembershipAffiliate(user);
			setVisible(false);
		    dispose();
		}
	}

}
