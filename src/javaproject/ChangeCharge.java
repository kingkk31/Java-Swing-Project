package javaproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

//청구방법 변경 클래스
public class ChangeCharge extends JFrame implements ActionListener{
	User user;
	JButton b1, submit, cancel;
	JTextField accountNum, cardNum;
	JRadioButton pay1, pay2;
	JComboBox bank, company;
	ButtonGroup gr;
	
	ChangeCharge(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("청구변경");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임
	
		JPanel body;
		JLabel title, name, flat_rate, call_volume, data, message;
		
		title = new JLabel("청구변경");
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
		
		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER,20,5));
		pn1.setBorder((Border) new TitledBorder(null, "청구방법 선택", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn1.setBounds(60, 50, 260, 60);
		add(pn1);
		//청구방법 라디오 버튼을 담는 패널

		pay1 = new JRadioButton("계좌이체", true);
		pay1.addActionListener(this);
		pay2 = new JRadioButton("신용카드");
		pay2.addActionListener(this);		
		//계좌이체, 신용카드 라디오버튼
		
		gr = new ButtonGroup();
		gr.add(pay1);
		gr.add(pay2);
		pn1.add(pay1);
		pn1.add(pay2);
		//청구방법 선택
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.LEFT,15,8));
		pn2.setBorder((Border) new TitledBorder(null, "계좌이체", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(60, 120, 260, 130);
		add(pn2);
		//계좌이체패널
		
		Font font = new Font("돋음", Font.PLAIN, 15);
		JLabel lb1 = new JLabel("은행 선택");
		lb1.setFont(font);
		pn2.add(lb1);
		
		String []banks = {"기업은행","우리은행","신한은행","국민은행","하나은행", "농협은행"};
		bank = new JComboBox(banks);
		bank.setSelectedIndex(0);
		bank.addActionListener(this);
		bank.setFont(new Font("돋음", Font.PLAIN, 12));
		pn2.add(bank);
		//은행 선택 콤보박스
		
		JLabel lb2 = new JLabel("계좌번호 입력 ('-'도 함께)");
		lb2.setFont(font);
		pn2.add(lb2);
		//계좌번호입력하는 창
		
		accountNum = new JTextField("", 20); 
		pn2.add(accountNum); 
		//계좌이체 선택 시 입력
		
		JPanel pn3 = new JPanel();
		pn3.setLayout(new FlowLayout(FlowLayout.LEFT,20,6));
		pn3.setBorder((Border) new TitledBorder(null, "신용카드", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn3.setBounds(60, 260, 260, 130);
		add(pn3);
		//신용카드 패널
		
		JLabel lb3 = new JLabel("카드사 선택");
		lb3.setFont(font);
		pn3.add(lb3);
		
		String []companys = {"삼성카드", "LG카드", "현대카드", "롯데카드", "신한카드", "외환카드", "농협카드", "국민카드", "BC카드"};
		company = new JComboBox(companys);
		company.setSelectedIndex(0);
		company.addActionListener(this);
		company.setFont(new Font("돋음", Font.PLAIN, 12));
		pn3.add(company);
		//카드 선택하는 콤보박스
		
		JLabel lb4 = new JLabel("카드번호 입력 ('-'도 함께)");
		lb4.setFont(font);
		pn3.add(lb4);
		//카드번호 입력하는 창
		
		cardNum = new JTextField("", 20); 
		pn3.add(cardNum); 
		//신용카드 선택 시 입력
			
		bank.setEnabled(true);
		accountNum.setEnabled(true);
		//초기엔 계좌이체 선택이므로 계좌이체 부분 활성화
		
		company.setEnabled(false);
		cardNum.setEnabled(false);
		//신용카드 부분 비활성화
		
		submit = new JButton("변경");
		submit.setLocation(110, 400);
		submit.setSize(70, 30);
		submit.addActionListener(this);
		add(submit);
		//변경 버튼
		
		cancel = new JButton("취소");
		cancel.setLocation(210, 400);
		cancel.setSize(70, 30);
		cancel.addActionListener(this);
		add(cancel);
		//취소 버튼
				
		setVisible(true); // 프레임을 화면에 출력
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //뒤로가기
		{
			Charge back = new Charge(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(pay1)) //계좌이체 선택 시
		{
			bank.setEnabled(true);
			accountNum.setEnabled(true);
			
			company.setEnabled(false);
			cardNum.setEnabled(false);		
		}
		else if (e.getSource().equals(pay2)) //신용카드 선택 시
		{
			bank.setEnabled(false);
			accountNum.setEnabled(false);
			
			company.setEnabled(true);
			cardNum.setEnabled(true);
		}
		else if (e.getSource().equals(submit)) //변경 버튼 선택시
		{
			String method, com, num;
			if(bank.isEnabled()) 
			{
				method = "계좌이체";
				com = (String)bank.getSelectedItem();
			}
			else
			{
				method = "신용카드";
				com = (String)company.getSelectedItem();
			}
			
			if(accountNum.isEnabled())
				num = accountNum.getText();
			else
				num = cardNum.getText();
			
			Connection con = null;
			//저장할 내용 준비
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//해당 데이터베이스 사용
								
				PreparedStatement ps = con.prepareStatement("update chargeMethod set method = ?, bank_company = ?, number = ? where id = '" + user.id + '\'');
				ps.setString(1, method);
				ps.setString(2, com);
				ps.setString(3, num);
				ps.executeUpdate();
				//변경 된 청구내역를 저장한다
				
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
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
			//데이터베이스에 저장
			
			JOptionPane.showMessageDialog(null, "변경되었습니다.");
			Charge charge = new Charge(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(cancel)) //취소버튼 선택 시
		{
			JOptionPane.showMessageDialog(null, "취소되었습니다.");
			Charge back = new Charge(user);
			setVisible(false);
		    dispose();
		    //취소시 저장하지 않고 복귀
		}
	}
}
