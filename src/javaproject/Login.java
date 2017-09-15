package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//로그인 클래스
public class Login extends JFrame implements ActionListener{
	User user;
	JTextField idText;
	JPasswordField  passwordText;
	JButton submitB, cancelB;
	Component co;
	
	Login(User user)
	{
		this.user=user;
		
		setLayout(null);
		setTitle("환영합니다.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임 설정
		
		JLabel idL = new JLabel("아이디");
		idL.setLocation(50, 140);
		idL.setSize(100, 50);
		add(idL);
		idText = new JTextField("");
		idText.setLocation(140, 150);
		idText.setSize(200, 25);
		add(idText);
		idText.addActionListener(this);
		//아이디 
		
		JLabel passwordL = new JLabel("비밀번호");
		passwordL.setLocation(45, 200);
		passwordL.setSize(120, 50);
		add(passwordL);
		passwordText = new JPasswordField();
		passwordText.setLocation(140, 210);
		passwordText.setSize(200, 25);
		add(passwordText);
		passwordText.addActionListener(this);
		//비밀번호 - 안보이게 처리
		
		submitB = new JButton("확인");
		submitB.setLocation(100,300);
		submitB.setSize(80, 25);
		submitB.addActionListener(this);
		add(submitB);
		//확인
		
		cancelB = new JButton("취소");
		cancelB.setLocation(210,300);
		cancelB.setSize(80, 25);
		cancelB.addActionListener(this);
		add(cancelB);
		//취소
		
		setVisible(true); // 프레임을 화면에 출력
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(submitB)) // 확인 버튼 눌렀을 때
		{
			if(idText.getText()== null || idText.getText().length() ==0)
			{
				JOptionPane.showMessageDialog(co,"아이디를 입력하여 주십시오");
			}//아이디 입력안했을 경우
			else if(passwordText.getText()== null || passwordText.getText().length() ==0)
			{
				JOptionPane.showMessageDialog(co,"비밀번호를 입력하여 주십시오");
			}//비밀번호 입력 안했을 경우
			else
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//해당 데이터베이스 사용
					
					String id = idText.getText(); //입력 아이디
					String pw = null; //데이터베이스에서 받아 올 비밀번호
					ResultSet rs = st.executeQuery("select * from login where id = '" + id + '\''); //해당 아이디의 레코드를 가져옴
					while(rs.next()){
						pw = rs.getString("password"); //데이터베이스의 비밀번호를 가져온다
					}
					
					if(pw != null && passwordText.getText().equals(pw)) //비밀번호가 존재하고 입력된 비밀번호와 일치 시
					{
						rs = st.executeQuery("select * from profile where id = '" + idText.getText() + '\''); //사용자 정보를 가져옴
						while(rs.next()){
							user = new User((String)rs.getString("id"), (String)rs.getString("name"), (String)rs.getString("phone")
								, (String)rs.getString("flat_rate"), Integer.parseInt((String)rs.getString("call_volume"))
								, Long.parseLong((String)rs.getString("data")), Integer.parseInt((String)rs.getString("message")));
							//사용자의 객체를 만든다
						}
						
						Main main = new Main(user);
						setVisible(false);
						dispose();
						//메인화면으로 넘어간다.
					}
					else
						JOptionPane.showMessageDialog(null, "아이디나 비밀번호를 확인해주세요.");
					
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
			}//모두 다 입력했을 경우			
		}
		else if(e.getSource().equals(cancelB)) // 취소 버튼 눌렀을 때
		{
			FirstPage first = new FirstPage(user);
			setVisible(false);
			dispose();
		}
	}
}
