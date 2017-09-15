package javaproject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

//메인창
public class Main extends JFrame implements ActionListener {
	
	User user;
	JButton fareManagementB, chargeManagementB, additionalServiceB, roamingB, logout;
	JButton membershipB, homeServiceB, customerCenterB;
	Main(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("메인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임 설정
		
		logout = new JButton("로그아웃");
		logout.setLocation(280, 10);
		logout.setSize(90, 30);
		logout.addActionListener(this);
		add(logout);
		//로그아웃 버튼
		
		JPanel userP = new JPanel();
		userP.setLayout(new FlowLayout(FlowLayout.CENTER,100,3));
		userP.setBorder((Border) new TitledBorder(null, "회원정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		userP.setBounds(30, 50, 320, 90);
		add(userP);
		//모바일 패널

		Font font = new Font("돋음", Font.BOLD, 13);
		//폰트 
		
		JLabel name = new JLabel(user.name + " 님");
		name.setFont(font);
		userP.add(name);
		//사용자 이름 출력
		
		JLabel phone = new JLabel(user.phone);
		phone.setFont(font);
		userP.add(phone);
		//사용자 핸드폰 번호 출력
		
		JLabel flat_rate = new JLabel(user.flat_rate);
		flat_rate.setFont(font);
		userP.add(flat_rate);
		//사용자가 이용하는 요금제 출력
		
		fareManagementB = new JButton("요금관리");
		fareManagementB.setLocation(70,180);
		fareManagementB.setSize(120, 40);
		fareManagementB.addActionListener(this);
		add(fareManagementB);
		//요금관리 버튼
		
		chargeManagementB = new JButton("청구관리");
		chargeManagementB.setLocation(200,180);
		chargeManagementB.setSize(120, 40);
		chargeManagementB.addActionListener(this);
		add(chargeManagementB);
		//청구관리 버튼
		
		additionalServiceB = new JButton("부가서비스");
		additionalServiceB.setLocation(70,240);
		additionalServiceB.setSize(120, 40);
		additionalServiceB.addActionListener(this);
		add(additionalServiceB);
		//부가서비스 버튼
		
		roamingB = new JButton("해외로밍");
		roamingB.setLocation(200,240);
		roamingB.setSize(120, 40);
		roamingB.addActionListener(this);
		add(roamingB);
		//해외로밍 버튼
		
		JPanel mobileP = new JPanel();
		mobileP.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		mobileP.setBorder((Border) new TitledBorder(null, "모바일", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mobileP.setBounds(30, 150, 320, 150);
		add(mobileP);
		//모바일 패널
		
		membershipB = new JButton("멤버쉽");
		membershipB.setLocation(50,345);
		membershipB.setSize(90, 40);
		membershipB.addActionListener(this);
		add(membershipB);
		//멤버쉽 버튼
		
		homeServiceB = new JButton("홈서비스");
		homeServiceB.setLocation(150,345);
		homeServiceB.setSize(90, 40);
		homeServiceB.addActionListener(this);
		add(homeServiceB);
		//홈서비스 버튼
				
		customerCenterB = new JButton("고객센터");
		customerCenterB.setLocation(250,345);
		customerCenterB.setSize(90, 40);
		customerCenterB.addActionListener(this);
		add(customerCenterB);
		//고객센터 버튼
		
		JPanel etcServiceP = new JPanel();
		etcServiceP.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		etcServiceP.setBorder((Border) new TitledBorder(null, "기타서비스", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		etcServiceP.setBounds(30, 310, 320, 100);
		add(etcServiceP);
		//기타서비스 패널
		
		setVisible(true); // 프레임을 화면에 출력	
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(logout)) //로그아웃 버튼을 누를 경우
		{
			User user = null;
			
			JLabel msg = new JLabel("정말로 로그아웃 하시겠습니까?");
			msg.setFont(new Font("sanSerif",0,12));
			msg.setForeground(Color.red);
			int n= JOptionPane.showConfirmDialog(this, msg,"로그아웃", JOptionPane.YES_NO_OPTION);
			
			if(n==0)
			{
				FirstPage firstPage = new FirstPage(user);
				setVisible(false);
				dispose();
			}
		}
		else if (e.getSource().equals(fareManagementB)) //요금관리 버튼을 누를 경우
		{
			FlatRate flatRate = new FlatRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(chargeManagementB)) //청구관리 버튼을 누를 경우
		{
			Charge charge = new Charge(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(additionalServiceB)) //부가서비스 버튼을 누를 경우
		{
			try {
				additionalService add = new additionalService(user);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(roamingB)) //로밍 버튼을 누를 경우
		{
			roaming roaming = new roaming(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(membershipB)) //멤버쉽 버튼을 누를 경우
		{
			Membership membership = new Membership(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(homeServiceB)) //홈서비스 버튼을 누를 경우
		{
			HomeService home = new HomeService(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(customerCenterB)) //고객센터 버튼을 누를 경우
		{
			CustomerCenter center = new CustomerCenter(user);
			setVisible(false);
		    dispose();
		}
	}
}
