package javaproject;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//로밍안내
public class roamingGuide extends JFrame implements ActionListener{
	User user;
	JButton backB;
	JPanel body;
	JTextArea text;
	
	roamingGuide(User user) throws IOException {
		
		this.user = user;
		
		setLayout(null);
		setTitle("로밍");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setResizable(true); // 프레임 크기 고정
		// 전체 프레임 설정

		JLabel title;
		title = new JLabel("로밍안내");
		title.setLocation(140, 20);
		title.setSize(100, 20);
		title.setFont(new Font("돋음", Font.BOLD, 22));
		add(title);
		//타이틀
				
		backB = new JButton("뒤로가기");
		backB.setLocation(280, 10);
		backB.setSize(90, 30);
		backB.addActionListener(this);
		add(backB);
		//뒤로가기 버튼
		
		body = new JPanel();
		body.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		body.setSize(378,410);
		body.setLocation(0,50);
		//내용 출력할 패녈
		
		text = new JTextArea(14,25);
		text.setFont(new Font("돋음", Font.PLAIN, 17));
		print(text);
		text.setEditable(false);
		text.setCaretPosition(0);
		text.setLineWrap(true);
		//글씨출력하는 부분
		
		JScrollPane scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		body.add(scroll);
		//스크롤
		
		add(body);
		setVisible(true);// 화면에 출력
	}
	
	public void print(JTextArea text) throws IOException { // 로밍안내가 담긴 파일 읽기
		BufferedReader br = new BufferedReader(new FileReader("로밍안내.txt"));
		String temp;
		while ((temp = br.readLine()) != null) // 한줄 한줄 읽어드리는 부분
			text.append(temp + "\n");
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(backB)) // 뒤로가기 버튼 눌렀을 때
		{
			roaming roaming = new roaming(user);
			setVisible(false);
			dispose();
		}
	}
}
