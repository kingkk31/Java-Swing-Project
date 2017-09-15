package javaproject;

import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

//게시글 쓰기 클래스
public class WriteBoard extends JFrame implements ActionListener{

	User user;
	JTextField titleText;
	JTextArea contentText;
	JButton submit, cancel;
	
	WriteBoard(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("게시글 쓰기");
		setSize(400,500);
		setResizable(false);
		//전체 프레임
			
		JPanel pn = new JPanel();
		pn.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 15));
		pn.setBorder((Border) new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn.setBounds(20, 20, 340, 350);
		add(pn);
		//게시판 패널
		
		JLabel tLabel = new JLabel("제목 ");
		tLabel.setFont(new Font("돋움", Font.BOLD, 18));
		pn.add(tLabel);
		titleText = new JTextField();
		titleText.setColumns(17);
		titleText.setFont(new Font("돋움", Font.PLAIN, 20));
		pn.add(titleText);
		//제목 부분
		
		contentText = new JTextArea(13, 20);
		contentText.setLineWrap(true);
		contentText.setFont(new Font("돋움", Font.PLAIN, 18));
		JScrollPane scroll = new JScrollPane(contentText);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pn.add(scroll);
		//내용 부분
		
		submit = new JButton("등록");
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
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(submit)) //뒤로가기 버튼 선택시
		{
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
				Statement st = con.createStatement();
				st.executeQuery("use javaproject");
				//해당 데이터베이스 사용
				
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String datetime = sdf.format(cal.getTime());
				//현재 시각
				
				PreparedStatement ps = con.prepareStatement("insert into board set id = ?, date = ?, title = ?, content = ?");
				ps.setString(1, user.id);
				ps.setString(2, datetime);
				ps.setString(3, titleText.getText());
				ps.setString(4, contentText.getText());
				ps.executeUpdate();
				//게시글을 데이터베이스에 저장한다
			}
		    catch(SQLException e1){
		    }finally{
		    	if(con != null)
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    }
			
			//작성 후 고객센터 화면으로 돌아간다.
			CustomerCenter back = new CustomerCenter(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(cancel)) //취소
		{
			CustomerCenter back = new CustomerCenter(user);
			setVisible(false);
		    dispose();
		}
	}
}

