package javaproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//홈서비스
public class HomeService extends JFrame implements ActionListener{
	User user;
	JButton b1, b2, b3;
	
	HomeService(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("홈서비스");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임
	
		JLabel title;
		title = new JLabel("홈서비스");
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
		
		b2 = new JButton("홈서비스 조회");
		b2.setLocation(110, 170);
		b2.setSize(150, 50);
		b2.addActionListener(this);
		add(b2);
		//홈서비스 조회 버튼
		 
		b3 = new JButton("홈서비스 등록/해지");
		b3.setLocation(110, 260);
		b3.setSize(150, 50);
		b3.addActionListener(this);
		add(b3);
		//홈서비스 변경 버튼
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //뒤로가기 버튼 누를 경우
		{
			Main back = new Main(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b2)) //홈서비스 조회 버튼 누를 경우 홈서비스  조회
		{
			HomeInquiry next = new HomeInquiry(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b3)) //홈서비스 조회 버튼 누를 경우 홈서비스 변경
		{
			HomeRegistration next = new HomeRegistration(user);
			setVisible(false);
		    dispose();
		}
	}
}
