package javaproject;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CustomerCenter extends JFrame implements ActionListener, MouseListener {
	User user;
	JTabbedPane boardTab = new JTabbedPane();
	JPanel noticeBoardP, freeBoardP, frequentlyQuestionP, pn3, innerPn3;
	JButton pre1, next1, pre2, next2, backB, pre3, next3, questionB, writeB;
	DefaultTableModel model1, model2, model3; 
	JTable table1, table2, table3;
	JScrollPane scroll1, scroll2, scroll3;
	int page1, cnt1, max_page1, page2, cnt2, max_page2, page3, cnt3, max_page3;
	JLabel currentPage1, currentPage2, currentPage3;
	
	CustomerCenter(User user)
	{
		this.user = user;
		
		setTitle("고객센터");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400,500);
		setResizable(false);
		// 전체 프레임 설정
		
		Container con = getContentPane();
		
		noticeBoardP = new JPanel();
		noticeBoardP.setLayout(new FlowLayout(FlowLayout.CENTER,30,15));
		boardTab.addTab("공지사항", noticeBoardP);
		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn1.setBorder((Border) new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn1.setBounds(20, 60, 340, 300);
		noticeBoardP.add(pn1);
		//공지사항 패널
		
		String tableName1[] = {"번호", "제목", "등록일"};
		model1 = new DefaultTableModel(tableName1, 0); 
		table1 = new JTable(model1);
		table1.getColumnModel().getColumn(0).setMinWidth(40); 
		table1.getColumnModel().getColumn(1).setMinWidth(160);
		table1.getColumnModel().getColumn(2).setMinWidth(80);
		table1.setRowHeight(50); 
		String arr1[] = {"no", "title", "date"};
		//공지사항 테이블
		
		scroll1 = new JScrollPane(table1);
		scroll1.setPreferredSize(new Dimension(320, 280));
		pn1.add(scroll1);
		//스크롤
	
		Connection con1 = null;
		try {
			con1 = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st1 = con1.createStatement();
			st1.executeQuery("use javaproject");
			//해당 데이터베이스 사용
			
			ResultSet rs1 = st1.executeQuery("select * from notice\n");
			rs1.last();
			cnt1 = rs1.getRow();
			rs1.beforeFirst(); 
			//테이블의 행의 수 읽어오기
			
			page1 = 0; //첫 페이지
			max_page1 = (int) Math.ceil(cnt1/5); //최대 페이지
	
			rs1 = st1.executeQuery("select * from notice order by no desc limit 5 offset 0\n"); //지정 크기만큼 잘라온다
			
			while(rs1.next())
			{
				arr1[0] = rs1.getString("no");
				arr1[1] = rs1.getString("title");
				arr1[2] = rs1.getString("date").substring(0, 11);
				model1.addRow(arr1);
			}//테이블 출력
			rs1.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con1 != null)
				try {
					con1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		table1.addMouseListener(this);
	
		pre1 = new JButton("pre");
		pre1.setLocation(60,370);
		pre1.setSize(80, 30);
		pre1.addActionListener(this);
		noticeBoardP.add(pre1);
		//이전버튼
		
		JPanel innerPn1 = new JPanel();
		innerPn1.setBounds(170, 370, 50, 50);
		noticeBoardP.add(innerPn1);
		
		currentPage1 = new JLabel();
		currentPage1.setText(String.valueOf(page1+1));
		innerPn1.add(currentPage1);
		//현 페이지 표시
		
		next1 = new JButton("next");
		next1.setLocation(240, 370);
		next1.setSize(80, 30);
		next1.addActionListener(this);
		noticeBoardP.add(next1);
		//다음버튼
		

		freeBoardP = new JPanel();
		freeBoardP.setLayout(new FlowLayout(FlowLayout.CENTER,30,15));
		boardTab.addTab("자유게시판", freeBoardP);
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn2.setBorder((Border) new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(20, 60, 340, 300);
		freeBoardP.add(pn2);
		//게시판 패널
		
		String tableName2[] = {"번호", "제목", "등록일", "작성자"};
		model2 = new DefaultTableModel(tableName2, 0); 
		table2 = new JTable(model2);
		table2.getColumnModel().getColumn(0).setMinWidth(40); 
		table2.getColumnModel().getColumn(1).setMinWidth(120);
		table2.getColumnModel().getColumn(2).setMinWidth(80);
		table2.getColumnModel().getColumn(3).setMinWidth(80);
		table2.setRowHeight(50); 
		String arr2[] = {"no", "title", "date", "id"};
		//게시판 테이블
		
		scroll2 = new JScrollPane(table2);
		scroll2.setPreferredSize(new Dimension(320, 280));
		pn2.add(scroll2);
		//스크롤
	
		Connection con2 = null;
		try {
			con2 = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st2 = con2.createStatement();
			st2.executeQuery("use javaproject");
			//해당 데이터베이스 사용
			
			ResultSet rs2 = st2.executeQuery("select * from board\n");
			rs2.last();
			cnt2 = rs2.getRow();
			rs2.beforeFirst(); 
			//테이블의 행의 수 읽어오기
			
			page2 = 0; //첫 페이지
			max_page2 = (int) Math.ceil(cnt2/5); //최대 페이지
			
			rs2 = st2.executeQuery("select * from board order by no desc limit 5 offset 0\n");
			
			while(rs2.next())
			{
				arr2[0] = rs2.getString("no");
				arr2[1] = rs2.getString("title");
				arr2[2] = rs2.getString("date").substring(0, 11);
				arr2[3] = rs2.getString("id");
				model2.addRow(arr2);
			}//테이블 출력
			rs2.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con2 != null)
				try {
					con2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		table2.addMouseListener(this);
	
		pre2 = new JButton("pre");
		pre2.setLocation(60,370);
		pre2.setSize(80, 30);
		pre2.addActionListener(this);
		freeBoardP.add(pre2);
		//이전버튼
		
		JPanel innerPn2 = new JPanel();
		innerPn2.setBounds(170, 370, 50, 50);
		freeBoardP.add(innerPn2);
		
		currentPage2 = new JLabel();
		currentPage2.setText(String.valueOf(page2+1));
		innerPn2.add(currentPage2);
		//현재 페이지 표시

		next2 = new JButton("next");
		next2.setLocation(240, 370);
		next2.setSize(80, 30);
		next2.addActionListener(this);
		freeBoardP.add(next2);
		//다음버튼
		
		writeB = new JButton("글쓰기");
		writeB.setSize(80, 25);
		writeB.addActionListener(this);
		freeBoardP.add(writeB);
		//글쓰기 버튼
		
		frequentlyQuestionP = new JPanel();
		frequentlyQuestionP.setLayout(new FlowLayout(FlowLayout.CENTER,30,15));
		boardTab.addTab("자주묻는 질문", frequentlyQuestionP);
		pn3 = new JPanel();
		pn3.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn3.setBorder((Border) new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn3.setBounds(20, 60, 340, 300);
		frequentlyQuestionP.add(pn3);
		//자주묻는 질문 패널
		
		questionB = new JButton("목록으로");
		questionB.setSize(90, 40);
		questionB.addActionListener(this);
		//자주묻는 질문에서 목록으로 돌아가는 버튼
		
		String tableName3[] = {"번호", "제목"};
		model3 = new DefaultTableModel(tableName3, 0); 
		table3 = new JTable(model3);
		table3.getColumnModel().getColumn(0).setMinWidth(40); 
		table3.getColumnModel().getColumn(1).setMinWidth(280);
		table3.setRowHeight(50); 
		String arr3[] = {"no", "title"};
		//자주묻는 질문 테이블
		
		scroll3 = new JScrollPane(table3);
		scroll3.setPreferredSize(new Dimension(320, 280));
		pn3.add(scroll3);
		//스크롤
	
		Connection con3 = null;
		try {
			con3 = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
			Statement st3 = con3.createStatement();
			st3.executeQuery("use javaproject");
			//해당 데이터베이스 사용
			
			ResultSet rs3 = st3.executeQuery("select * from QA\n");
			rs3.last();
			cnt3 = rs3.getRow();
			rs3.beforeFirst(); 
			//테이블의 행의 수 읽어오기
			
			page3 = 0; //첫 페이지
			max_page3 = (int) Math.ceil(cnt3/5); //최대 페이지
			
			rs3 = st3.executeQuery("select * from QA order by no desc limit 5 offset 0\n");
			
			while(rs3.next())
			{
				arr3[0] = rs3.getString("no");
				arr3[1] = rs3.getString("title");
				model3.addRow(arr3);
			}//테이블 출력
			rs3.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con3 != null)
				try {
					con3.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		table3.addMouseListener(this);
	
		pre3 = new JButton("pre");
		pre3.setLocation(60,370);
		pre3.setSize(80, 30);
		pre3.addActionListener(this);
		frequentlyQuestionP.add(pre3);
		//이전버튼
		
		innerPn3 = new JPanel();
		innerPn3.setBounds(170, 370, 50, 50);
		frequentlyQuestionP.add(innerPn3);
		
		currentPage3 = new JLabel();
		currentPage3.setText(String.valueOf(page3+1));
		innerPn3.add(currentPage3);
		//현재 페이지 표시

		next3 = new JButton("next");
		next3.setLocation(240, 370);
		next3.setSize(80, 30);
		next3.addActionListener(this);
		frequentlyQuestionP.add(next3);
		//다음버튼
	
		
		backB = new JButton("뒤로가기");
		backB.setSize(87, 25);
		backB.setLocation(280, 420);
		backB.addActionListener(this);
		add(backB);
		//뒤로가기
		
		con.add(boardTab);
		
		setVisible(true); //프레임을 화면에 출력
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(backB)) //뒤로가기
		{
			Main back = new Main(user);
			setVisible(false);
		    dispose();
		}
		else if (e.getSource().equals(next1)) //공지사항 뒷페이지
		{
			if(max_page1 >= page1+1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//해당 데이터베이스 사용
					
					page1++; //페이지 증가
					model1.setRowCount(0);
					int offset1 = page1 * 5;
					currentPage1.setText(String.valueOf(page1+1));
					ResultSet rs = st.executeQuery("select * from notice order by no desc limit 5 offset " + String.valueOf(offset1));
					//지정된 부분 다시 불러오기
					String arr[] = {"no", "title", "date"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						arr[2] = rs.getString("date").substring(0, 11);
						model1.addRow(arr);
					}//테이블 출력
					rs.close();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}

			}
		}
		else if (e.getSource().equals(pre1)) //공지사항 앞페이지
		{
			if(0 <= page1-1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//해당 데이터베이스 사용
					
					page1--; //페이지 감소
					model1.setRowCount(0);
					int offset1 = page1 * 5;
					currentPage1.setText(String.valueOf(page1+1));
					ResultSet rs = st.executeQuery("select * from notice order by no desc limit 5 offset " + String.valueOf(offset1));
					//지정된 부분 다시 불러오기
					String arr[] = {"no", "title", "date"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						arr[2] = rs.getString("date").substring(0, 11);
						model1.addRow(arr);
					}//테이블 출력
					rs.close();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}

			}
		}
		else if (e.getSource().equals(next2)) //게시판 뒷페이지
		{
			if(max_page2 >= page2+1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//해당 데이터베이스 사용
					
					page2++; //페이지 증가
					model2.setRowCount(0);
					int offset2 = page2 * 5;
					currentPage2.setText(String.valueOf(page2+1));
					ResultSet rs = st.executeQuery("select * from board order by no desc limit 5 offset " + String.valueOf(offset2));
					//지정된 부분 다시 불러오기
					String arr[] = {"no", "title", "date", "id"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						arr[2] = rs.getString("date").substring(0, 11);
						arr[3] = rs.getString("id");
						model2.addRow(arr);
					}//테이블 출력
					rs.close();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}

			}
		}
		else if (e.getSource().equals(pre2)) //게시판 앞페이지
		{
			if(0 <= page2-1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//해당 데이터베이스 사용
					
					page2--; //페이지 감소
					model2.setRowCount(0);
					int offset2 = page2 * 5;
					currentPage2.setText(String.valueOf(page2+1));
					ResultSet rs = st.executeQuery("select * from board order by no desc limit 5 offset " + String.valueOf(offset2));
					//지정된 부분 다시 불러오기
					String arr[] = {"no", "title", "date", "id"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						arr[2] = rs.getString("date").substring(0, 11);
						arr[3] = rs.getString("id");
						model2.addRow(arr);
					}//테이블 출력
					rs.close();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
		}
		else if (e.getSource().equals(next3)) //자주묻는 질문 뒷페이지
		{
			if(max_page3 >= page3+1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//해당 데이터베이스 사용
					
					page3++; //페이지 증가
					model3.setRowCount(0);
					int offset3 = page3 * 5;
					currentPage3.setText(String.valueOf(page3+1));
					ResultSet rs = st.executeQuery("select * from QA order by no desc limit 5 offset " + String.valueOf(offset3));
					//지정된 부분 다시 불러오기
					String arr[] = {"no", "title"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						model3.addRow(arr);
					}//테이블 출력
					rs.close();
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
		}
		else if (e.getSource().equals(pre3)) //자주묻는질문 앞페이지
		{
			if(0 <= page3-1)
			{
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
					Statement st = con.createStatement();
					st.executeQuery("use javaproject");
					//해당 데이터베이스 사용
					
					page3--; //페이지 감소
					model3.setRowCount(0);
					int offset3 = page3 * 5;
					currentPage3.setText(String.valueOf(page3+1));
					ResultSet rs = st.executeQuery("select * from QA order by no desc limit 5 offset " + String.valueOf(offset3));
					//지정된 부분 다시 불러오기
					String arr[] = {"no", "title"};
					
					while(rs.next())
					{
						arr[0] = rs.getString("no");
						arr[1] = rs.getString("title");
						model3.addRow(arr);
					}//테이블 출력
					rs.close();
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					if(con != null)
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}

			}
		}
		else if (e.getSource().equals(questionB)) //자주하는 질문에서 목록으로 돌아가는 버튼
		{
			frequentlyQuestionP.removeAll(); //패널 내용을 지웠다가
			frequentlyQuestionP.add(pn3);
			frequentlyQuestionP.add(pre3);
			frequentlyQuestionP.add(innerPn3);
			frequentlyQuestionP.add(next3);
			frequentlyQuestionP.repaint(); //목록들을 다시 추가한다
		}
		else if (e.getSource().equals(writeB))
		{
			WriteBoard write = new WriteBoard(user);
			setVisible(false);
		    dispose();
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		CustomerCenter board = null;
		if(boardTab.getSelectedIndex() == 0) //공지사항 탭 글읽기
		{
			int row = table1.getSelectedRow();
	    	int col = 0;
	    	Object value = null;
	    	value = table1.getValueAt(row, col);
	    	//글번호 알아오기
	    	
	    	String num = String.valueOf(value);
	    	ReadNotice read = new ReadNotice(num);
	    	//해당 글읽기
		}
		else if(boardTab.getSelectedIndex() == 1) //게시판 탭 글읽기
		{	
			int row = table2.getSelectedRow();
	    	int col = 0;
	    	Object value = null;
	    	value = table2.getValueAt(row, col);
	    	//글번호 알아오기
	    	
	    	String num = String.valueOf(value);
	    	ReadBoard read = new ReadBoard(num);
	    	//해당 글읽기
		}
		else if(boardTab.getSelectedIndex() == 2) //자주묻는 질문 탭 글 읽기
		{	
			int row = table3.getSelectedRow();
	    	int col = 0;
	    	Object value = null;
	    	value = table3.getValueAt(row, col);
	    	//글번호 알아오기
	    	
	    	String num = String.valueOf(value);
	    	
	    	Connection con = null;
	    	try {
	    		con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "kkiinngg31");
	    		Statement st = con.createStatement();
	    		st.executeQuery("use javaproject");
	    		//해당 데이터베이스 사용
	    		
	    		ResultSet rs = st.executeQuery("select * from QA where no = " + num);
	    		
	    		String str1 = null; //제목
	    		String str4 = null; //내용
	    		
	    		//현재 패널의 내용을 지우고 해당 글 내용을 보여준다
	    		frequentlyQuestionP.removeAll();
		    	while(rs.next())
	    		{
	    			str1 = (String)rs.getString("title");
	    			JTextField titleText = new JTextField(str1, 21);
	    			titleText.setFont(new Font("돋움", Font.PLAIN, 18));
	    			titleText.setBackground(Color.white);
	    			titleText.setEditable(false);
	    			frequentlyQuestionP.add(titleText);
	    			//제목 출력
	    							
	    			str4 = (String)rs.getString("content");
	    			JTextArea contentText = new JTextArea(13, 20);
	    			contentText.setCaretPosition(0);
	    			contentText.setLineWrap(true);
	    			contentText.append(str4);
	    			contentText.setFont(new Font("돋움", Font.PLAIN, 18));
	    			contentText.setEditable(false);
	    			//내용 출력
	    				
	    			JScrollPane scroll = new JScrollPane(contentText);
	    			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    			frequentlyQuestionP.add(scroll);
	    			//스크롤
	    		}
	    		rs.close();
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

	    	frequentlyQuestionP.add(questionB);
	    	frequentlyQuestionP.repaint();
	    	//목록으로 버튼 추가 후 다시 그리기	    	
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}