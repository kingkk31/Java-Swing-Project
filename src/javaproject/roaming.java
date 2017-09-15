package javaproject;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//로밍 창
public class roaming extends JFrame implements ActionListener {
	User user;
	JButton backB, roamingChangeB, roamingGuideB;
	JLabel roaming, bRoaming;
	roaming(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("로밍");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setResizable(true); //프레임 크기 고정
		// 전체 프레임 설정

		roaming = new JLabel("ROAMING");
		roaming.setFont(new Font("Snap ITC", Font.PLAIN, 35));
		roaming.setBounds(90, 60, 400, 200); 
		add(roaming);
		// roaming 글씨
		
		bRoaming = new JLabel("ROAMING");
		bRoaming.setFont(new Font("Snap ITC", Font.PLAIN, 35));
		bRoaming.setForeground(new Color(204, 204, 204));
		bRoaming.setBounds(90, 70, 400, 200); 
		add(bRoaming);
		// 글자 하나더 만들어서-그림자효과
		
		backB = new JButton("뒤로가기");
		backB.setLocation(240, 30);
		backB.setSize(100, 20);
		backB.addActionListener(this);
		add(backB);
		// 뒤로가기

		roamingGuideB = new JButton("로밍안내");
		roamingGuideB.setLocation(60,280);
		roamingGuideB.setSize(120, 40);
		roamingGuideB.addActionListener(this);
		add(roamingGuideB);
		//로밍 안내로 들어가는 버튼
		
		roamingChangeB = new JButton("로밍 조회");
		roamingChangeB.setLocation(210,280);
		roamingChangeB.setSize(120, 40);
		roamingChangeB.addActionListener(this);
		add(roamingChangeB);
		//국가별 로밍 조회, 신청, 해지로 들어가는 버튼
		
		setVisible(true); // 프레임을 화면에 출력
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(backB)) // 뒤로가기 버튼 눌렀을 때
		{
			Main main = new Main(user); 
			setVisible(false);
			dispose();
		}
		else if(e.getSource().equals(roamingGuideB)) // 로밍안내 버튼 눌렀을 때
		{
			try {
				roamingGuide guide = new roamingGuide(user);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			setVisible(false);
			dispose();
		}
		else if(e.getSource().equals(roamingChangeB)) //로밍 조회 버튼 눌렀을 때
		{
			foreignCountryRoaming roaming = new foreignCountryRoaming(user);
			setVisible(false);
			dispose();
		}
	}
}
