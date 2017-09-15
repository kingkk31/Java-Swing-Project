package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//회원가입
public class newMember extends JFrame implements ActionListener {
	User user;
	JButton nextB, cancelB;
	JTextField nameText, idText, phoneFirstText, phoneMiddleText, phoneLastText;
	JRadioButton man, woman;
	JPasswordField passwordText;
	JComboBox YearCombo, MonthCombo, DayCombo;
	Component co;
	
	newMember(User user){
		this.user=user;
		
		setLayout(null);
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임 설정
		
		JLabel intro = new JLabel("회원가입하러 오신 것을 환영합니다.");
		intro.setLocation(100,30);
		intro.setSize(250,40);
		add(intro);
		//회원가입 환영 글
		
		JLabel nameL = new JLabel("이름");
		nameL.setLocation(60, 80);
		nameL.setSize(50, 40);
		add(nameL);
		nameText = new JTextField("");
		nameText.setLocation(140, 88);
		nameText.setSize(200, 25);
		add(nameText);
		//이름
		
		man = new JRadioButton("남자");
		woman = new JRadioButton("여자",true);
		//성별 라디오 버튼 지정
		
		ButtonGroup sexG = new ButtonGroup();
		sexG.add(woman);
		sexG.add(man);
		//한 그룹으로 묶는다
		
		man.addActionListener(this);
		woman.addActionListener(this);
		//라디오 이벤트 리스너 등록
		
		JPanel sexP = new JPanel();
		JLabel sexL = new JLabel("성별");
		sexL.setLocation(60,125);
		sexL.setSize(50,40);
		add(sexL);
		sexP.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 7));
		sexP.setBounds(80,120,300,50);
		add(sexP);
		//성별 라디오 버튼 넣을 틀
		
		sexP.add(woman);
		sexP.add(man);
		//라디오 버튼을 넣는다.
		
		JLabel idL = new JLabel("아이디");
		idL.setLocation(50, 160);
		idL.setSize(100, 50);
		add(idL);
		idText = new JTextField("");
		idText.setLocation(140, 170);
		idText.setSize(200, 25);
		add(idText);
		//아이디 
		
		JLabel passwordL = new JLabel("비밀번호");
		passwordL.setLocation(45, 210);
		passwordL.setSize(120, 50);
		add(passwordL);
		passwordText = new JPasswordField();
		passwordText.setLocation(140, 220);
		passwordText.setSize(200, 25);
		add(passwordText);
		//비밀번호 - 안보이게 처리
		
		JLabel birthL = new JLabel("생년월일");
		birthL.setLocation(45, 260);
		birthL.setSize(100, 50);
		add(birthL);
		//생일
		
		JPanel birthP = new JPanel();
		birthP.setLayout(new FlowLayout());
		birthP.setBounds(120, 265, 250, 65); //x, y, width, height
		add(birthP);
		//생년월일 선택 리스트 담을 패널
		
		YearCombo = new JComboBox();
		MonthCombo = new JComboBox();
		DayCombo = new JComboBox();
		//콤보박스 틀 만들기
		
		for(int i=2015; i>=1950; i--)
			YearCombo.addItem(String.valueOf(i));
		for(int i=1; i<13; i++)
			MonthCombo.addItem(String.valueOf(i));
		for(int i=1; i<32; i++)
			DayCombo.addItem(String.valueOf(i));
		//년, 월, 일 값 넣기
		
		YearCombo.setSelectedIndex(0);
		MonthCombo.setSelectedIndex(0);
		DayCombo.setSelectedIndex(0);
		//년, 월, 일 별 첫번째 값 선택
		
		YearCombo.addActionListener(this);
		MonthCombo.addActionListener(this);
		DayCombo.addActionListener(this);
		//리스너 등록
		
		birthP.add(YearCombo);
		birthP.add(MonthCombo);
		birthP.add(DayCombo);
		//생년월일
		
		JLabel phoneL = new JLabel("전화번호");
		phoneL.setLocation(50, 320);
		phoneL.setSize(100, 50);
		add(phoneL);
		phoneFirstText = new JTextField("");
		phoneFirstText.setDocument(new JTextLimit()); 
		//숫자만 입력하게제한
		phoneFirstText.setLocation(140, 330);
		phoneFirstText.setSize(50, 25);
		add(phoneFirstText);
		
		phoneMiddleText = new JTextField("");
		phoneMiddleText.setDocument(new JTextLimit());
		//숫자만 입력하게 제한
		phoneMiddleText.setLocation(200, 330);
		phoneMiddleText.setSize(70, 25);
		add(phoneMiddleText);
		
		phoneLastText = new JTextField("");
		phoneLastText.setDocument(new JTextLimit());
		//숫자만 입력하게 제한
		phoneLastText.setLocation(280, 330);
		phoneLastText.setSize(70, 25);
		add(phoneLastText);
		//전화번호
		
		ToolTipManager m = ToolTipManager.sharedInstance();
		m.setInitialDelay(10); // 툴팁이 나타나는 최초 지연시간 제어
		phoneFirstText.setToolTipText("숫자만 입력해주세요");
		phoneMiddleText.setToolTipText("숫자만 입력해주세요");
		phoneLastText.setToolTipText("숫자만 입력해주세요");
		m.setDismissDelay(1000); // 툴팁이 켜져있는 지연 시간 제어
		// 툴팁
	
		nextB = new JButton("다음");
		nextB.setLocation(210,390);
		nextB.setSize(80, 25);
		nextB.addActionListener(this);
		add(nextB);
		//다음버튼
		
		cancelB = new JButton("취소");
		cancelB.setLocation(100,390);
		cancelB.setSize(80, 25);
		cancelB.addActionListener(this);
		add(cancelB);
		//취소버튼
		
		setVisible(true); //프레임을 화면에 출력
	}
	
	public boolean idCheck(String id) //아이디 검사하는 부분
	{
		int result = 0;
		
		Connection con = null;
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			// 해당 데이터베이스 사용
			ResultSet resultSet = st.executeQuery("select * from login where id = '"+ id + "'");
			
			if(resultSet.next())
				result++; 
		}
		catch (SQLException e){
		}finally{
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return (result > 0) ? true : false;
	}	
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(nextB))//다음버튼을 누른 경우
		{
			String idValue = idText.getText();
			String nameValue = nameText.getText();
		    String sexValue = woman.isEnabled() ? "여자" : "남자";
			String pwValue = passwordText.getText();
		    String phoneValue = phoneFirstText.getText()+"-"+phoneMiddleText.getText()+"-"+phoneLastText.getText();
		    String birthValue = (String)YearCombo.getSelectedItem()+"-"+(String)MonthCombo.getSelectedItem()+"-"+(String)DayCombo.getSelectedItem();
			
		    
		    if(nameText.getText()== null || nameText.getText().length() ==0)
		    	//이름을 입력하지 않았을 경우
				JOptionPane.showMessageDialog(co,"이름을 입력하여 주십시오");
			else if(idText.getText()== null || idText.getText().length() ==0)
				//아이디를 입력하지 않았을 경우
				JOptionPane.showMessageDialog(co, "아이디를 입력하여 주십시오");
			else if(idText.getText()!=null && idCheck(idText.getText()))
			{
				//아이디가 존재하는 경우
				JOptionPane.showMessageDialog(co, "사용할 수 없는 아이디입니다!");
				idText.setText(null);
				idText.requestFocus();
			}
			else if(idText.getText().trim().length()>20 || idText.getText().trim().length()<=5)
				//아이디 글자 수 제한
				JOptionPane.showMessageDialog(co, "아이디는 5자 이상, 20자 이내로 입력해야합니다.");
			else if(passwordText.getText().trim().length()>20 || passwordText.getText().trim().length()<=7)
				//비밀번호 글자 수 제한
				JOptionPane.showMessageDialog(co, "비밀번호는 7자 이상, 20자 이내로 입력해야합니다.");
			else if(passwordText.getText()== null || passwordText.getText().length() ==0)
				//비밀번호를 입력하지 않은 경우
				JOptionPane.showMessageDialog(co, "비밀번호를 입력하여 주십시오");
			else if(phoneFirstText.getText()== null || phoneFirstText.getText().length() ==0)
				//전화번호를 입력하지 않은 경우
				JOptionPane.showMessageDialog(co, "전화번호를 입력하여 주십시오");
			else if(phoneMiddleText.getText()== null || phoneMiddleText.getText().length() ==0)
				//전화번호를 끝까지 입력하지 않은 경우(중간 번호를 입력하지 않은 경우)
				JOptionPane.showMessageDialog(co, "전화번호를 마저 입력하여 주십시오");
			else if(phoneLastText.getText()== null || phoneLastText.getText().length() ==0)
				//전화번호를 끝까지 입력하지 않은 경우(마지막 번호를 입력하지 않은 경우)
				JOptionPane.showMessageDialog(co, "전화번호를 마저 입력하여 주십시오");
			else //사용 가능한 아이디인 경우
			{
				JOptionPane.showMessageDialog(co, "사용 가능한 아이디입니다!");
				payRegister pay = new payRegister(user, idValue, nameValue, sexValue, pwValue, phoneValue, birthValue);
				setVisible(false);
				dispose();
			}
		}
		else if(e.getSource().equals(cancelB)) // 취소 버튼 눌렀을 때
		{
			FirstPage first = new FirstPage(user);
			setVisible(false);
			dispose();
		}
	}
}
