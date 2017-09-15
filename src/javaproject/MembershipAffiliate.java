package javaproject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//멤버쉽 제휴사
public class MembershipAffiliate extends JFrame implements ActionListener{

	User user;
	JButton b1;
	
	MembershipAffiliate(User user)
	{
		this.user = user; //유저 클래스 받아오기
		
		setLayout(null);
		setTitle("멤버쉽 제휴사");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400,500);
		//전체 프레임
	
		JLabel title;
		title = new JLabel("멤버쉽 제휴사");
		title.setLocation(120, 20);
		title.setSize(160, 20);
		title.setFont(new Font("돋음", Font.BOLD, 22));
		add(title);
		//타이틀
		
		b1 = new JButton("뒤로가기");
		b1.setLocation(280, 10);
		b1.setSize(90, 30);
		b1.addActionListener(this);
		add(b1);
		//뒤로가기 버튼
			
		Font font = new Font("돋음", Font.PLAIN, 18);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		pn2.setBorder((Border) new TitledBorder(null, "멤버쉽 등급 및 한도", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn2.setBounds(20, 60, 340, 370);
		add(pn2);
		//멤버쉽 제휴사 패널
		
		String tableName[] = {"제휴사", "해택", "포인트 차감"};
		DefaultTableModel model = new DefaultTableModel(tableName, 0); 
		JTable table = new JTable(model);
		table.setEnabled(false);
		table.getColumnModel().getColumn(0).setMinWidth(80); 
		table.getColumnModel().getColumn(1).setMinWidth(120);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		table.setRowHeight(40); 
		String arr[] = {"제휴사", "해택", "포인트 차감"};
		//멤버쉽 제휴사 테이블
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(320, 330));
		pn2.add(scroll);
		//스크롤
		
		arr[0] = "CGV"; arr[1] = "무료 영화 예매"; arr[2] = "7000점"; model.addRow(arr);
		arr[0] = "롯데시네마"; arr[1] = "무료 영화 예매"; arr[2] = "7000점"; model.addRow(arr);
		arr[0] = "스타벅스"; arr[1] = "아메리카노(Tall) 무료"; arr[2] = "4100점"; model.addRow(arr);
		arr[0] = "탐앤탐스"; arr[1] = "아메리카노(Tall) 무료"; arr[2] = "3800점"; model.addRow(arr);
		arr[0] = "도미노피자"; arr[1] = "20% 할인"; arr[2] = "금액당 1점"; model.addRow(arr);
		arr[0] = "미스터피자"; arr[1] = "20% 할인"; arr[2] = "금액당 1점"; model.addRow(arr);
		arr[0] = "서울랜드"; arr[1] = "자유이용권 50% 할인"; arr[2] = "금액당 1점"; model.addRow(arr);
		arr[0] = "GS25"; arr[1] = "15% 할인"; arr[2] = "금액당 1점"; model.addRow(arr);
		arr[0] = "파리바게뜨"; arr[1] = "천원당 100원 할인"; arr[2] = "금액당 1점"; model.addRow(arr);
		arr[0] = "뚜레쥬르"; arr[1] = "천원당 100원 할인"; arr[2] = "금액당 1점"; model.addRow(arr);
		arr[0] = "캐리비안베이"; arr[1] = "30% 할인"; arr[2] = "금액당 1점"; model.addRow(arr);
		arr[0] = "더페이스샵"; arr[1] = "15% 할인"; arr[2] = "금액당 1점"; model.addRow(arr);
		//테이블 입력
	
		setVisible(true); //프레임을 화면에 출력
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //뒤로가기버튼을 눌렀을 경우
		{
			Membership back = new Membership(user);
			setVisible(false);
		    dispose();
		}
	}

}
