package javaproject;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//카드 및 통장 등록
public class payRegister extends JFrame implements ActionListener {
	User user;
	JButton submitB, backB;
	JTextField accountNum, cardNum;
	JRadioButton pay1, pay2;
	JComboBox bank, company;
	ButtonGroup gr;
	String idValue, nameValue, sexValue, pwValue, phoneValue, birthValue; // 앞페이지의
																			// 내용들

	payRegister(User user, String idValue, String nameValue, String sexValue, String pwValue, String phoneValue,
			String birthValue) {
		this.user = user;
		this.idValue = idValue;
		this.nameValue = nameValue;
		this.sexValue = sexValue;
		this.pwValue = pwValue;
		this.phoneValue = phoneValue;
		this.birthValue = birthValue;

		setLayout(null);
		setTitle("카드 및 통장 등록");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setResizable(false);
		// 전체 프레임 설정

		JLabel title;
		title = new JLabel("결제 방법을 선택하여 저장해주세요");
		title.setLocation(90, 20);
		title.setSize(220, 20);
		add(title);
		// 타이틀

		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		pn1.setBorder((Border) new TitledBorder(null, "청구방법 선택", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn1.setBounds(60, 50, 260, 60);
		add(pn1);
		// 청구방법 라디오 버튼을 담는 패널

		pay1 = new JRadioButton("계좌이체", true);
		pay1.addActionListener(this);
		pay2 = new JRadioButton("신용카드");
		pay2.addActionListener(this);
		// 계좌이체 및 신용카드 라디오 버튼

		gr = new ButtonGroup();
		gr.add(pay1);
		gr.add(pay2);
		pn1.add(pay1);
		pn1.add(pay2);
		// 청구방법 선택

		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 8));
		pn2.setBorder((Border) new TitledBorder(null, "계좌이체", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(60, 120, 260, 130);
		add(pn2);
		// 계좌이체 패널

		Font font = new Font("돋음", Font.PLAIN, 15);
		JLabel lb1 = new JLabel("은행 선택");
		lb1.setFont(font);
		pn2.add(lb1);

		String[] banks = { "기업은행", "우리은행", "신한은행", "국민은행", "하나은행", "농협은행" };
		bank = new JComboBox(banks);
		bank.setSelectedIndex(0);
		bank.addActionListener(this);
		bank.setFont(new Font("돋음", Font.PLAIN, 12));
		pn2.add(bank);
		// 은행 종류별로 적어놓은 콤보박스

		JLabel lb2 = new JLabel("계좌번호 입력 ('-'도 함께)");
		lb2.setFont(font);
		pn2.add(lb2);

		accountNum = new JTextField("", 20);
		pn2.add(accountNum);
		// 계좌이체 선택 시 입력

		JPanel pn3 = new JPanel();
		pn3.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 6));
		pn3.setBorder((Border) new TitledBorder(null, "신용카드", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn3.setBounds(60, 260, 260, 130);
		add(pn3);
		// 신용카드 패널

		JLabel lb3 = new JLabel("카드사 선택");
		lb3.setFont(font);
		pn3.add(lb3);

		String[] companys = { "삼성카드", "LG카드", "현대카드", "롯데카드", "신한카드", "외환카드", "농협카드", "국민카드", "BC카드" };
		company = new JComboBox(companys);
		company.setSelectedIndex(0);
		company.addActionListener(this);
		company.setFont(new Font("돋음", Font.PLAIN, 12));
		pn3.add(company);
		// 카드 종류 선택하게 하는 콤보박스

		JLabel lb4 = new JLabel("카드번호 입력 ('-'도 함께)");
		lb4.setFont(font);
		pn3.add(lb4);

		cardNum = new JTextField("", 20);
		pn3.add(cardNum);
		// 신용카드 선택 시 입력

		bank.setEnabled(true);
		accountNum.setEnabled(true);
		// 초기엔 계좌이체 선택이므로 계좌이체 부분 활성화
		company.setEnabled(false);
		cardNum.setEnabled(false);
		// 신용카드 부분 비활성화

		submitB = new JButton("확인");
		submitB.setLocation(210, 400);
		submitB.setSize(70, 30);
		submitB.addActionListener(this);
		add(submitB);
		// 변경 버튼

		backB = new JButton("뒤로");
		backB.setLocation(110, 400);
		backB.setSize(70, 30);
		backB.addActionListener(this);
		add(backB);
		// 뒤로가기 버튼

		setVisible(true); // 프레임을 화면에 출력
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(pay1)) // 계좌이체 선택 시 계좌이체 창 활성화, 신용카드 창 비활성화
		{
			bank.setEnabled(true);
			accountNum.setEnabled(true);

			company.setEnabled(false);
			cardNum.setEnabled(false);
		} else if (e.getSource().equals(pay2)) // 신용카드 선택 시 계좌이체 창 비활성화, 신용카드 창
												// 활성화
		{
			bank.setEnabled(false);
			accountNum.setEnabled(false);

			company.setEnabled(true);
			cardNum.setEnabled(true);
		} else if (e.getSource().equals(submitB)) // 확인 버튼 눌렀을 시
		{
			String method, com, num;
			if (bank.isEnabled()) { 
				method = "계좌이체";
				com = (String) bank.getSelectedItem();
			}
			else {
				method = "신용카드";
				com = (String) company.getSelectedItem();
			}

			if (accountNum.isEnabled())
				num = accountNum.getText();
			else
				num = cardNum.getText();

			// 저장할 내용 준비

			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				// 해당 데이터베이스 사용

				PreparedStatement ps = con.prepareStatement("insert into login set id = '" + idValue 
						+ "', password = '" + pwValue + "'");
				ps.executeUpdate();
				// 로그인 테이블에 레코드 추가

				ps = con.prepareStatement("insert into profile set id = ?, name = ?, phone = ?, "
						+ "flat_rate = 'LTE 34', call_volume = 160, data = 750, message = 200, sex = ?, birth = ?");
				ps.setString(1, idValue);
				ps.setString(2, nameValue);
				ps.setString(3, phoneValue);
				ps.setString(4, sexValue);
				ps.setString(5, birthValue);
				ps.executeUpdate();
				// 회원정보 테이블에 레코드 추가

				ps = con.prepareStatement("insert into chargemethod set id = '" + idValue
						+ "', method = ?, bank_company = ?, number = ?");
				ps.setString(1, method);
				ps.setString(2, com);
				ps.setString(3, num);
				ps.executeUpdate();
				// 청구정보 테이블에 레코드 추가

				ps = con.prepareStatement("insert into membership set id = ?, rank = ?, point = ?");
				ps.setString(1, idValue);
				ps.setString(2, "Bronze");
				ps.setInt(3, 60000);
				ps.executeUpdate();
				// 멤버쉽 테이블에 레코드 추가

				ps = con.prepareStatement("insert into homeservice set id = ?, tv = '없음', "
						+ "internet = '없음', IoT = '없음'");
				ps.setString(1, idValue);
				ps.executeUpdate();
				// 홈서비스 테이블에 레코드 추가

				ps = con.prepareStatement("insert into roaming set id = ?, country = null");
				ps.setString(1, idValue);
				ps.executeUpdate();
				// 로밍 테이블에 레코드 추가

				ps = con.prepareStatement(
						"insert into additional set id = ?, basic = false, best = false, study = false,"
						+ " smart = false, kakao = false, music = false;");
				ps.setString(1, idValue);
				ps.executeUpdate();
				// 부가서비스 테이블에 레코드 추가

				String query = "create table charge_" + idValue
						+ "(date datetime not null primary key, pay Integer not null)";
				st.executeUpdate(query);
				// 해당 아이디 청구 테이블 생성

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
			// 데이터베이스에 저장

			user = new User(idValue, nameValue, phoneValue, "LTE 34", 160, 750, 200);
			JOptionPane.showMessageDialog(null, "회원가입되었습니다.");
			Main main = new Main(user);
			setVisible(false);
			dispose();
		} 
		else if (e.getSource().equals(backB)) { //취소버튼 선택시 
			newMember newMember = new newMember(user);
			setVisible(false);
			dispose();
			// 취소시 저장하지 않고 복귀
		}
	}
}