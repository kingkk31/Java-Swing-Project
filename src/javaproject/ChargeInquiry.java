package javaproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ChargeInquiry extends JFrame implements ActionListener{
	User user;
	JButton b1;
	
	ChargeInquiry(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("청구조회");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임
	
		JLabel title;
		title = new JLabel("청구조회");
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

		JPanel body = new JPanel();
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		body.setBackground(Color.white);
		body.setSize(330,350);
		body.setLocation(20, 70);
		//내용 출력할 패널
		
		Font bodyFont = new Font("돋음", Font.PLAIN, 18);
		JLabel userName = new JLabel(user.name + "님의 청구내역");
		userName.setFont(bodyFont);
		body.add(userName);
		
		String tableName[] = {"날짜", "금액"};
		DefaultTableModel model = new DefaultTableModel(tableName, 0); 
		JTable table = new JTable(model);
		table.setEnabled(false);
		table.getColumnModel().getColumn(0).setMinWidth(150); 
		table.getColumnModel().getColumn(1).setWidth(100);
		table.setRowHeight(30); 
		String arr[] = {"날짜", "금액"};
		//청구내역 출력 테이블
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(300, 270));
		body.add(scroll);
		//스크롤

		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st = con.createStatement();
			st.executeQuery("use javaproject");
			//해당 데이터베이스 사용
			
			ResultSet rs = st.executeQuery("select * from charge_" + user.id + " order by date desc;\n");
			while(rs.next())
			{
				arr[0] = rs.getString("date");
				arr[1] = rs.getString("pay");
				model.addRow(arr);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		//데이터베이스에서 청구내역 불러와 테이블에 추가
		
		add(body);
		
		setVisible(true); //프레임을 화면에 출력
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //뒤로가기 버튼 선택시
		{
			Charge back = new Charge(user);
			setVisible(false);
		    dispose();
		}
	}
}
