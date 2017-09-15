package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//처음 창 클래스
public class FirstPage extends JFrame implements ActionListener{
	User user;
	JButton newMemberB, loginB;
	JLabel mobileTelecom, bMobileTelecom;
	FirstPage(User user)
	{
		this.user=user;
		
		setLayout(null);
		setTitle("환영합니다.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임 설정
		
		mobileTelecom = new JLabel("한신 모바일");
		mobileTelecom.setFont(new Font("HY엽서M", Font.PLAIN, 35));
		mobileTelecom.setBounds(90, 60, 400, 200); 
		add(mobileTelecom);
		// roaming 글씨
		
		bMobileTelecom = new JLabel("한신 모바일");
		bMobileTelecom.setFont(new Font("HY엽서M", Font.PLAIN, 35));
		bMobileTelecom.setForeground(new Color(204, 204, 204));
		bMobileTelecom.setBounds(90, 65, 400, 200); 
		add(bMobileTelecom);
		// 글자 하나더 만들어서-그림자효과
		
		newMemberB = new JButton("회원가입");
		newMemberB.setLocation(60,280);
		newMemberB.setSize(120, 40);
		newMemberB.addActionListener(this);
		add(newMemberB);
		//회원가입
		
		loginB = new JButton("로그인");
		loginB.setLocation(210,280);
		loginB.setSize(120, 40);
		loginB.addActionListener(this);
		add(loginB);
		//로그인
		
		setVisible(true); // 프레임을 화면에 출력
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(newMemberB)) // 회원가입 버튼 눌렀을 때
		{
			newMember newMember = new newMember(user);
			setVisible(false);
			dispose();
		}
		else if(e.getSource().equals(loginB)) //로그인 버튼 눌렀을 때
		{
			Login login = new Login(user);
			setVisible(false);
			dispose();
		}
	}
	public static void main(String args[]){
		User user = null;
		FirstPage first = new FirstPage(user);
	}
}
