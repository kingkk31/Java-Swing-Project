package javaproject;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.sql.*;

//홈서비스 변경
public class HomeRegistration extends JFrame implements ActionListener{
	
	User user;
	JButton b1, submit, cancel;	
	JTextField tv, internet, IoT;
	JComboBox tvList, internetList, IoTList;
	
	HomeRegistration(User user)
	{
		this.user = user; //유저 클래스 받아오기
		
		setLayout(null);
		setTitle("홈서비스 등록/해지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임 설정
		
		JLabel title;
		title = new JLabel("홈서비스 등록/해지");
		title.setLocation(80, 20);
		title.setSize(200, 20);
		title.setFont(new Font("돋음", Font.BOLD, 22));
		add(title);
		//타이틀
		
		b1 = new JButton("뒤로가기");
		b1.setLocation(280, 10);
		b1.setSize(90, 30);
		b1.addActionListener(this);
		add(b1);
		//뒤로가기 버튼
		
		Font font = new Font("돋음", Font.PLAIN, 20);
				
		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn1.setBorder((Border) new TitledBorder(null, "tv,인터넷,IoT 서비스 선택", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn1.setBounds(30, 60, 320, 80);
		add(pn1);
		//변경 홈서비스 패널
			
		String []tvs = {"없음", "스마트TV", "실시간TV", "PCTV"};
		tvList = new JComboBox(tvs);
		tvList.setSelectedIndex(0);
		tvList.addActionListener(this);
		pn1.add(tvList);
		//tv 선택 콤보박스		
		
		String []internets = {"없음", "GIGA", "GIGA 라이트", "GIGA WiFi"};
		internetList = new JComboBox(internets);
		internetList.setSelectedIndex(0);
		internetList.addActionListener(this);
		pn1.add(internetList);
		//인터넷 선택 콤보박스
		
		String []IoTs = {"없음", "허브", "에너지", "안전", "가전", "PET"};
		IoTList = new JComboBox(IoTs);
		IoTList.setSelectedIndex(0);
		IoTList.addActionListener(this);
		pn1.add(IoTList);
		//IoT 선택 콤보박스
				
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		pn2.setBorder((Border) new TitledBorder(null, "변경 홈서비스 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(40, 150, 300, 220);
		add(pn2);
		//변경 홈서비스 패널
		
		Font font2 = new Font("돋음", Font.PLAIN, 18);
		JLabel la1 = new JLabel("    TV        ");
		pn2.add(la1); 
		la1.setFont(font2); 
		
		tv = new JTextField("없음", 9); 
		tv.setEditable(false);
		tv.setHorizontalAlignment(JTextField.RIGHT);
		pn2.add(tv); 
		tv.setFont(font2); 
		//선택된 TV상품
		
		JLabel la2 = new JLabel("  인터넷    "); 
		pn2.add(la2); 
		la2.setFont(font2); 
		
		internet = new JTextField("없음", 9); 
		internet.setEditable(false);
		internet.setHorizontalAlignment(JTextField.RIGHT);
		pn2.add(internet); 
		internet.setFont(font2); 
		//선택된 인터넷상품
		
		JLabel la3 = new JLabel("    IoT        "); 
		pn2.add(la3); 
		la3.setFont(font2); 
		
		IoT = new JTextField("없음", 9); 
		IoT.setEditable(false);
		IoT.setHorizontalAlignment(JTextField.RIGHT);
		pn2.add(IoT); 
		IoT.setFont(font2); 
		//선택된 IoT상품
		
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
		if (e.getSource().equals(b1)) //뒤로가기 버튼 누를 경우
		{
			HomeService back = new HomeService(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(submit)) //변경버튼 누를 경우
		{
			String tvValue = tv.getText();
			String internetValue = internet.getText();
			String IoTValue = IoT.getText();
			
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//해당 데이터베이스 사용
								
				PreparedStatement ps = con.prepareStatement("update homeService set tv = ?, internet = ?, IoT = ? where id = '" + user.id + '\'');
				ps.setString(1, tvValue);
				ps.setString(2, internetValue);
				ps.setString(3, IoTValue);
				ps.executeUpdate();
				//변경된 홈서비스를 저장한다
				
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
			
			HomeService back = new HomeService(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(cancel)) //취소버튼 누를 경우
		{
			JOptionPane.showMessageDialog(null, "취소되었습니다.");
			HomeService back = new HomeService(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(tvList)) //콤보박스 변경 시 tv 내용 변경
		{
			String str = (String)tvList.getSelectedItem();
			tv.setText(str);
		}
		else if (e.getSource().equals(internetList)) //콤보박스 변경 시 인터넷 내용 변경
		{
			String str = (String)internetList.getSelectedItem();
			internet.setText(str);
		}
		else if (e.getSource().equals(IoTList)) //콤보박스 변경 시 IoT 내용 변경
		{
			String str = (String)IoTList.getSelectedItem();
			IoT.setText(str);
		}
	}
}
