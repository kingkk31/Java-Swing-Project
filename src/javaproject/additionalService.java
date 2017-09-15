package javaproject;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

//부가서비스
public class additionalService extends JFrame implements ActionListener {
	User user;
	JButton backB, myServiceCheckB, logoutB;
	JTextArea text;
	additionalService(User user) throws IOException
	{
		this.user =user;
		setLayout(null);
		setTitle("부가서비스");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setResizable(false);
		// 전체 프레임 설정

		myServiceCheckB = new JButton("이용중인 부가서비스 확인 & 부가서비스 신청");
		myServiceCheckB.setLocation(45,415);
		myServiceCheckB.setSize(300,25);
		add(myServiceCheckB);
		myServiceCheckB.addActionListener(this);
		//이용중인 부가서비스 확인 버튼
		
		JPanel additionalServiceP = new JPanel();
		additionalServiceP.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		additionalServiceP.setSize(345,280);
		additionalServiceP.setLocation(20,120);
		//부가서비스 내용출력할 패널
		
		text = new JTextArea(11,25);
		text.setFont(new Font("돋음", Font.PLAIN, 15));
		print(text);
		text.setEditable(false);
		text.setCaretPosition(0);
		text.setLineWrap(true);
		
		JScrollPane scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		additionalServiceP.add(scroll);
		//스크롤
		
		add(additionalServiceP);
		
		backB = new JButton("뒤로가기");
		backB.setLocation(260,35);
		backB.setSize(90,20);
		add(backB);
		backB.addActionListener(this);
		//뒤로가기 버튼
		
		JLabel info = new JLabel("스팸차단, 국제전화 수신차단 서비스는 무료이며");
		info.setLocation(40, 60);
		info.setSize(300, 20);
		add(info);
		info=new JLabel("기본으로 신청되어있습니다.");
		info.setLocation(40, 80);
		info.setSize(300, 20);
		add(info);
		//기본정보
		
		JPanel memberP = new JPanel();
		memberP.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		memberP.setBorder((Border) new TitledBorder(null, "기본정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		memberP.setBounds(20, 20, 345, 100);
		add(memberP);
		//기본부가서비스 정보
		
		setVisible(true); // 프레임을 화면에 출력
	}
	public void print(JTextArea text) throws IOException { // 부가서비스 정보가 담긴 파일 읽기
		BufferedReader br = new BufferedReader(new FileReader("부가서비스정보.txt"));
		String temp;
		while ((temp = br.readLine()) != null) // 한줄 한줄 읽어드리는 부분
			text.append(temp + "\n");
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(backB)) // 뒤로가기 버튼 눌렀을 때
		{
			Main main = new Main(user);
			setVisible(false);
			dispose();
		}
		else if(e.getSource().equals(myServiceCheckB)) // 이용중인 부가서비스 버튼 눌렀을 때
		{
			myServiceCheck check = new myServiceCheck(user);
			setVisible(false);
			dispose();
		}
	}
}