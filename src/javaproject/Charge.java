package javaproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Charge extends JFrame implements ActionListener{
	User user;
	JButton b1, b2, b3;
	
	Charge(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("청구관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임
	
		JLabel title;
		title = new JLabel("청구관리");
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
		
		b2 = new JButton("청구조회");
		b2.setLocation(40, 160);
		b2.setSize(140, 50);
		b2.addActionListener(this);
		add(b2);
		//청구조회 버튼
		
		b3 = new JButton("청구변경");
		b3.setLocation(200, 160);
		b3.setSize(140, 50);
		b3.addActionListener(this);
		add(b3);
		//청구변경 버튼
		
		setVisible(true); //화면을 프레임에 출력
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //뒤로가기 버튼 선택시
		{
			Main back = new Main(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b2)) //청구조회 버튼 선택시
		{
			ChargeInquiry next = new ChargeInquiry(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b3)) //청구변경 버튼 선택시
		{
			ChangeCharge next = new ChangeCharge(user);
			setVisible(false);
		    dispose();
		}
	}
}
