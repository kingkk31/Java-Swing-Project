package javaproject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//요금 관리
public class FlatRate extends JFrame implements ActionListener{
	
	User user;
	JButton back, b1, b2, b3, b4;
	
	FlatRate(User user)
	{
		this.user = user;
		setLayout(null); //직접 컴포넌트의 위치와 크기를 설정할 수 있다.
		setTitle("요금관리"); //프레임 타이틑
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 윈도우 닫으면 프로그램 종료
		setSize(400,500); //프레임크기설정
		setResizable(false);
		//전체 프레임 설정
		
		JLabel title = new JLabel("요금관리");
		title.setLocation(140, 80);
		title.setSize(100, 20);
		title.setFont(new Font("돋음", Font.BOLD, 25));
		add(title);
		//타이틀
		
		back = new JButton("뒤로가기");
		back.setLocation(280, 10);
		back.setSize(90, 30);
		back.addActionListener(this);
		add(back);
		//뒤로가기 버튼

		b1 = new JButton("요금조회");
		b1.setLocation(40, 160);
		b1.setSize(140, 50);
		b1.addActionListener(this);
		add(b1);
		//요금조회 버튼
		
		b2 = new JButton("요금충전");
		b2.setLocation(200, 160);
		b2.setSize(140, 50);
		b2.addActionListener(this);
		add(b2);
		//요금충전 버튼
		
		b3 = new JButton("요금제 변경");
		b3.setLocation(40, 250);
		b3.setSize(140, 50);
		b3.addActionListener(this);
		add(b3);
		//요금제 변경 버튼
		
		b4 = new JButton("맞춤 요금제 찾기");
		b4.setLocation(200, 250);
		b4.setSize(140, 50);
		b4.addActionListener(this);
		add(b4);
		//맞춤 요금제 찾기 버튼
		
		setVisible(true); //프레임을 화면에 출력
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(back)) //메인으로
		{
			Main ex = new Main(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b1)) //요금조회로
		{
			InquiryRate inq = new InquiryRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b2)) //요금충전으로
		{
			ChargeRate charge = new ChargeRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b3)) //요금제 변경으로
		{
			ChangeRate change = new ChangeRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(b4)) //맞춤 요금제 찾기로
		{
			FindRate find = new FindRate(user);
			setVisible(false);
		    dispose();
		}
		//해당 버튼이 눌리면 해당 기능으로 넘어간다
	}
	
}
