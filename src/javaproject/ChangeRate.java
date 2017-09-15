package javaproject;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.sql.*;

//요금제 변경 클래스
public class ChangeRate extends JFrame implements ActionListener{
	
	User user;
	JButton b1, submit, cancel;	
	JTextField currentRate, pay, call, data, message;
	JComboBox rateList;
	ChangeRate(User user)
	{
		this.user = user; //유저 클래스 받아오기
		
		setLayout(null);
		setTitle("요금제 변경");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임 설정
		
		JLabel title;
		title = new JLabel("요금제 변경");
		title.setLocation(120, 20);
		title.setSize(140, 20);
		title.setFont(new Font("돋음", Font.BOLD, 22));
		add(title);
		//타이틀
		
		b1 = new JButton("뒤로가기");
		b1.setLocation(280, 10);
		b1.setSize(90, 30);
		b1.addActionListener(this);
		add(b1);
		//뒤로가기 버튼
			
		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		pn1.setBorder((Border) new TitledBorder(null, "요금제 선택", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn1.setBounds(60, 60, 120, 70);
		add(pn1);
		//요금제 선택 리스트를 담을 패널
		
		Font font = new Font("돋음", Font.PLAIN, 20);
		
		String []rates = {"LTE 34", "LTE 42", "LTE 52", "LTE 62", "LTE 72", "LTE 85", "LTE 100", "LTE 120"};
		rateList = new JComboBox(rates);
		rateList.setSelectedIndex(0);
		rateList.setFont(font);
		rateList.addActionListener(this);
		pn1.add(rateList);
		//요금제 선택 콤보박스
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		pn2.setBorder((Border) new TitledBorder(null, "현재 요금제", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(210, 60, 120, 70);
		add(pn2);
		//현재 요금제 패널
		
		currentRate = new JTextField(user.flat_rate, 6);
		currentRate.setFont(font);
		currentRate.setHorizontalAlignment(JTextField.CENTER);
		currentRate.setBorder(null);
		currentRate.setEditable(false);
		pn2.add(currentRate);		
		//현재 요금제
		
		JPanel pn3 = new JPanel();
		pn3.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		pn3.setBorder((Border) new TitledBorder(null, "변경 요금제 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn3.setBounds(40, 140, 300, 240);
		add(pn3);
		//변경 요금제 패널
		
		Font font2 = new Font("돋음", Font.PLAIN, 18);
		JLabel la1 = new JLabel("    월정액(원)     ");
		pn3.add(la1); 
		la1.setFont(font2); 
		
		pay = new JTextField("34000", 6); 
		pay.setEditable(false);
		pay.setHorizontalAlignment(JTextField.RIGHT);
		pn3.add(pay); 
		pay.setFont(font2); 
		//월별 요금
		
		JLabel la2 = new JLabel("  변경 음성(분)   "); 
		pn3.add(la2); 
		la2.setFont(font2); 
		
		call = new JTextField("160", 6); 
		call.setEditable(false);
		call.setHorizontalAlignment(JTextField.RIGHT);
		pn3.add(call); 
		call.setFont(font2); 
		//월별 음성
		
		JLabel la3 = new JLabel("변경 데이터(MB)"); 
		pn3.add(la3); 
		la3.setFont(font2); 
		
		data = new JTextField("750", 6); 
		data.setEditable(false);
		data.setHorizontalAlignment(JTextField.RIGHT);
		pn3.add(data); 
		data.setFont(font2); 
		//월별 데이터
		
		JLabel la4 = new JLabel("변경 메세지(건)  "); 
		pn3.add(la4); 
		la4.setFont(font2); 
		
		message = new JTextField("200", 6); 
		message.setEditable(false);
		message.setHorizontalAlignment(JTextField.RIGHT);
		pn3.add(message); 
		message.setFont(font2); 
		//월별 메세지
		
		
		submit = new JButton("변경");
		submit.setLocation(110, 390);
		submit.setSize(70, 30);
		submit.addActionListener(this);
		add(submit);
		//변경 버튼
		
		cancel = new JButton("취소");
		cancel.setLocation(210, 390);
		cancel.setSize(70, 30);
		cancel.addActionListener(this);
		add(cancel);
		//취소 버튼
			
		setVisible(true); //프레임을 화면에 출력
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //뒤로가기 버튼 선택시
		{
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(submit)) //변경 버튼 선택시
		{
			int callValue = Integer.parseInt(call.getText());
			long dataValue = Long.parseLong(data.getText());
			int messageValue = Integer.parseInt(message.getText());
			
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//해당 데이터베이스 사용
								
				PreparedStatement ps = con.prepareStatement("update profile set flat_rate = ?, call_volume = ?, data = ?, message = ? where id = '" + user.id + '\'');
				ps.setString(1, (String)rateList.getSelectedItem());
				ps.setInt(2, callValue);
				ps.setLong(3, dataValue);
				ps.setInt(4, messageValue);
				ps.executeUpdate();
				//변경 된 요금제를 저장한다
				
				ps = con.prepareStatement("update membership set rank = ?, point = ? where id = '" + user.id + '\'');
				switch ((String) rateList.getSelectedItem()) {
				case "LTE 34":
				case "LTE 42":
					ps.setString(1, "Bronze");
					ps.setInt(2, 60000);
					break;
				case "LTE 52":
				case "LTE 62":
					ps.setString(1, "Silver");
					ps.setInt(2, 80000);
					break;
				case "LTE 72":
				case "LTE 85":
					ps.setString(1, "Gold");
					ps.setInt(2, 100000);
					break;
				case "LTE 100":
				case "LTE 120":
					ps.setString(1, "VIP");
					ps.setInt(2, 120000);
					break;
				default:
					ps.setString(1, "Bronze");
					ps.setInt(2, 60000);
					break;
				}
				ps.executeUpdate();
				//요금제 변경에 따른 멤버쉽 변경
						
			} catch (SQLException ex) {
				ex.printStackTrace();
			}finally{
				if(con != null)
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			
			JOptionPane.showMessageDialog(null, "변경되었습니다.");
			
			FlatRate back = new FlatRate(new User(user.id, user.name, user.phone, (String)rateList.getSelectedItem(), callValue, dataValue, messageValue));
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(cancel)) //취소 버튼 선택시
		{
			JOptionPane.showMessageDialog(null, "취소되었습니다.");
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(rateList)) //콤보박스 변경 시 요금제 내용 변경
		{
			String str = (String)rateList.getSelectedItem();
			switch(str)
			{
			case "LTE 34": pay.setText("34000"); call.setText("160"); data.setText("750"); message.setText("200"); break;
			case "LTE 42": pay.setText("42000"); call.setText("200"); data.setText("1536"); message.setText("200"); break;
			case "LTE 52": pay.setText("52000"); call.setText("250"); data.setText("2662"); message.setText("250"); break;
			case "LTE 62": pay.setText("62000"); call.setText("350"); data.setText("6144"); message.setText("350"); break;
			case "LTE 72": pay.setText("72000"); call.setText("500"); data.setText("10240"); message.setText("450"); break;
			case "LTE 85": pay.setText("85000"); call.setText("750"); data.setText("14336"); message.setText("650"); break;
			case "LTE 100": pay.setText("100000"); call.setText("1200"); data.setText("20480"); message.setText("1000"); break;
			case "LTE 120": pay.setText("120000"); call.setText("1500"); data.setText("24576"); message.setText("1000"); break;
			default: pay.setText("34000"); call.setText("160"); data.setText("750"); message.setText("200"); break;
			}
		}
		
	}
}
