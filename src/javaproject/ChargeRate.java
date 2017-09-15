package javaproject;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.sql.*;

//요금충전 클래스
public class ChargeRate extends JFrame implements ActionListener{
	
	User user;
	JButton b1, submit, cancel;	
	JRadioButton rb1, rb2, rb3, rb4, pay1, pay2;
	ButtonGroup gr1, gr2;
	JTextField callText, dataText, messageText;
	
	ChargeRate(User user)
	{
		this.user = user; //유저 클래스 받아오기
		
		setLayout(null);
		setTitle("요금충전");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임 설정
		
		JLabel title;
		title = new JLabel("요금충전");
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
		
		rb1 = new JRadioButton("5000원");
		rb1.setSelected(true);
		rb2 = new JRadioButton("10000원");
		rb3 = new JRadioButton("15000원");
		rb4 = new JRadioButton("20000원");
		//요금 라디오 버튼 지정
		
		gr1 = new ButtonGroup();
		gr1.add(rb1);
		gr1.add(rb2);
		gr1.add(rb3);
		gr1.add(rb4);
		//한 그룹으로 묶는다
		
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);
		rb4.addActionListener(this);
		//라디오 이벤트 리스너 등록
		
		JPanel pay = new JPanel();
		pay.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		pay.setBorder((Border) new TitledBorder(null, "요금 선택", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pay.setBounds(30, 80, 315, 180);
		add(pay);
		//요금 라디오 버튼을 넣을 틀
		
		pay.add(rb1);
		pay.add(rb2);
		pay.add(rb3);
		pay.add(rb4);
		//라디오 버튼을 넣는다
		
		JPanel payInner = new JPanel();
		payInner.setSize(315, 200);
		payInner.setLayout(new GridLayout(3,2,15,10));
		
		payInner.add(new JLabel("추가 음성 (분)"));
		callText = new JTextField("27", 6);
		callText.setHorizontalAlignment(JTextField.RIGHT);
		callText.setEditable(false);
		payInner.add(callText);
		//금액 당 추가 음성
		
		payInner.add(new JLabel("추가 데이터 (MB)"));
		dataText = new JTextField("333", 6);
		dataText.setHorizontalAlignment(JTextField.RIGHT);
		dataText.setEditable(false);
		payInner.add(dataText);
		//금액 당 추가 데이터

		payInner.add(new JLabel("추가 메세지 (건)"));
		messageText = new JTextField("23", 6);
		messageText.setHorizontalAlignment(JTextField.RIGHT);
		messageText.setEditable(false);
		payInner.add(messageText);
		//금액 당 추가 메세지
		
		pay.add(payInner);		
		
		submit = new JButton("다음");
		submit.setLocation(110, 330);
		submit.setSize(70, 30);
		submit.addActionListener(this);
		add(submit);
		//결재로 넘어가는 버튼
		
		cancel = new JButton("취소");
		cancel.setLocation(210, 330);
		cancel.setSize(70, 30);
		cancel.addActionListener(this);
		add(cancel);
		//취소 버튼
			
		setVisible(true); //프레임을 화면에 출력
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //뒤로가기 버튼을 누를 경우
		{
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(submit)) //결제로 넘어간다
		{
			int callValue = Integer.parseInt(callText.getText());
			long dataValue = Long.parseLong(dataText.getText());
			int messageValue = Integer.parseInt(messageText.getText());
					
			RatePayment next = new RatePayment(user, callValue, dataValue, messageValue);
			setVisible(false);
		    dispose();
		 }
		else if (e.getSource().equals(cancel)) //취소
		{
			JOptionPane.showMessageDialog(null, "취소되었습니다.");
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		    //취소시 저장하지 않고 복귀
		}
		else if (e.getSource().equals(rb1)) //5000원 충전
		{
			callText.setText("27");
			dataText.setText("333");
			messageText.setText("23");
		}
		else if (e.getSource().equals(rb2)) //10000원 충전
		{
			callText.setText("55");
			dataText.setText("666");
			messageText.setText("47");
		}
		else if (e.getSource().equals(rb3)) //15000원 충전
		{
			callText.setText("83");
			dataText.setText("1000");
			messageText.setText("71");
		}
		else if (e.getSource().equals(rb4)) //20000원 충전
		{
			callText.setText("111");
			dataText.setText("1333");
			messageText.setText("95");
		}
	}	
}
