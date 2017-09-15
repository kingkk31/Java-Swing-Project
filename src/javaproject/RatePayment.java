package javaproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

//요금결제
public class RatePayment extends JFrame implements ActionListener{

	User user;
	JButton b1, submit, cancel;
	JRadioButton pay1, pay2;
	JComboBox transfer;
	int callValue, messageValue;
	long dataValue;
	
	RatePayment(User user, int call, long data, int message){
		
		this.user = user;
		this.callValue = call;
		this.dataValue = data;
		this.messageValue = message;
		
		setLayout(null);
		setTitle("요금결제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임 설정
		
		JLabel title;
		title = new JLabel("요금결제");
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
		
		JPanel payment = new JPanel();
		payment.setLayout(new FlowLayout(FlowLayout.CENTER, 30,10));
		payment.setBorder((Border) new TitledBorder(null, "결제 선/후불 선택", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		payment.setBounds(50, 60, 280, 90);
		add(payment);
		//결제 방법 선택 틀
		
		pay1 = new JRadioButton("선불");
		pay1.setFont(new Font("돋음", Font.PLAIN, 18));
		pay1.setSelected(true);
		pay1.addActionListener(this);
		pay2 = new JRadioButton("후불");
		pay2.setFont(new Font("돋음", Font.PLAIN, 18));
		pay2.addActionListener(this);
		//선,후불 결제 라디오
		
		ButtonGroup gr = new ButtonGroup();
		gr.add(pay1);
		gr.add(pay2);
		payment.add(pay1);
		payment.add(pay2);
		//라디오 버튼 묶기
		
		JPanel account = new JPanel();
		account.setLayout(new FlowLayout(FlowLayout.CENTER, 20,15));
		account.setBorder((Border) new TitledBorder(null, "선불(계좌이체)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		account.setBounds(50, 190, 280, 100);
		add(account);
		//선불 틀
		
		String []banks = {"기업은행 012-346344-86-356 김지영", "국민은행 851202-04-108649 권태희"};
		transfer = new JComboBox(banks);
		transfer.setSelectedIndex(0);
		transfer.addActionListener(this);
		transfer.setFont(new Font("돋음", Font.PLAIN, 15));
		account.add(transfer);
		//계좌이체 콤보
		
		submit = new JButton("결제");
		submit.setLocation(110, 350);
		submit.setSize(70, 30);
		submit.addActionListener(this);
		add(submit);
		//결제 버튼
		
		cancel = new JButton("취소");
		cancel.setLocation(210, 350);
		cancel.setSize(70, 30);
		cancel.addActionListener(this);
		add(cancel);
		//취소 버튼
			
		setVisible(true); //프레임을 화면에 출력
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(b1)) //뒤로가기 버튼을 누를 경우
		{
			ChargeRate back = new ChargeRate(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(submit)) //결제버튼을 누를 경우
		{
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//해당 데이터베이스 사용
							
				ResultSet rs = st.executeQuery("select * from profile where id = '" + user.id + '\'');
				while(rs.next()) //디비에 있는 정보를 읽는 부분
				{
					callValue += Integer.parseInt(rs.getString("call_volume")); //음성
					dataValue += Long.parseLong(rs.getString("data"));  //데이터
					messageValue += Integer.parseInt(rs.getString("message")); //문자
				}
				rs.close();
				//값을 읽어와 추가 금액과 합친다
				
				PreparedStatement ps = con.prepareStatement("update profile set call_volume = ?, data = ?, message = ? where id = '" + user.id + '\'');
				ps.setInt(1, callValue);
				ps.setLong(2, dataValue);
				ps.setInt(3, messageValue);
				ps.executeUpdate();
				//합친 값을 다시 저장한다
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
			
			JOptionPane.showMessageDialog(null, "충전되었습니다.");
			
			FlatRate back = new FlatRate(new User(user.id, user.name, user.phone, user.flat_rate, callValue, dataValue, messageValue));
			setVisible(false);
		    dispose();
		    //완료 후 요금관리로 복귀
		}
		else if (e.getSource().equals(cancel)) //취소버튼을 누를 경우
		{
			JOptionPane.showMessageDialog(null, "취소되었습니다.");
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		    //취소시 저장하지 않고 복귀
		}
		else if (e.getSource().equals(pay1)) //선불 선택시 선불 창 열리게 해 높음
		{
			transfer.setEnabled(true);
		}
		else if (e.getSource().equals(pay2)) //후불 선택시 선불 창 닫히게 해 놓음
		{
			transfer.setEnabled(false);
		}
	}
}
