package javaproject;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

//맞춤 요금제 찾기
public class FindRate extends JFrame implements ActionListener{

	User user;
	JButton b1, submit;
	JTextField call, data, message, resultCall, resultData, resultMessage;
	
	FindRate(User user)
	{
		this.user = user;
		
		setLayout(null);
		setTitle("맞춤 요금제 찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		setResizable(false);
		//전체 프레임 설정
		
		JLabel title;
		title = new JLabel("맞춤 요금제 찾기");
		title.setLocation(100, 20);
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
		
		Font font = new Font("돋음", Font.BOLD, 16);

		JLabel lb1 = new JLabel("예상 음성(분)");
		lb1.setFont(font);
		lb1.setLocation(40, 60);
		lb1.setSize(120, 20);
		call = new JTextField("", 6);
		call.setFont(font);
		call.setHorizontalAlignment(JTextField.RIGHT);
		call.setLocation(40, 90);
		call.setSize(130, 30);
		add(lb1);
		add(call);
		//예상 음성
		
		JLabel lb2 = new JLabel("예상 데이터(MB)");
		lb2.setFont(font);
		lb2.setLocation(210, 60);
		lb2.setSize(140, 20);
		data = new JTextField("", 6);
		data.setFont(font);
		data.setHorizontalAlignment(JTextField.RIGHT);
		data.setLocation(210, 90);
		data.setSize(130, 30);
		add(lb2);
		add(data);
		//예상 데이터
		
		JLabel lb3 = new JLabel("예상 메세지(건)");
		lb3.setFont(font);
		lb3.setLocation(40, 140);
		lb3.setSize(140, 20);
		message = new JTextField("", 6);
		message.setFont(font);
		message.setHorizontalAlignment(JTextField.RIGHT);
		message.setLocation(40, 170);
		message.setSize(130, 30);
		add(lb3);
		add(message);
		//예상 메세지
		
		submit = new JButton("찾기");
		submit.setLocation(225, 170);
		submit.setSize(100, 30);
		submit.addActionListener(this);
		add(submit);
		//찾기 버튼
		
		JPanel result = new JPanel();
		result.setLayout(new FlowLayout(FlowLayout.CENTER, 20,20));
		result.setBorder((Border) new TitledBorder(null, "맞춤 결과", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		result.setBounds(30, 230, 320, 200);
		add(result);
		//결과 출력할 패널
		
		JLabel lb4 = new JLabel("음성 맞춤   ");
		lb4.setFont(font);
		lb4.setSize(140, 20);
		resultCall = new JTextField("", 8);
		resultCall.setFont(font);
		resultCall.setEditable(false);
		resultCall.setHorizontalAlignment(JTextField.RIGHT);
		resultCall.setSize(130, 30);
		result.add(lb4);
		result.add(resultCall);
		//입력한 음성에 맞는 요금제 출력
		
		JLabel lb5 = new JLabel("데이터 맞춤");
		lb5.setFont(font);
		lb5.setSize(140, 20);
		resultData = new JTextField("", 8);
		resultData.setFont(font);
		resultData.setEditable(false);
		resultData.setHorizontalAlignment(JTextField.RIGHT);
		resultData.setSize(130, 30);
		result.add(lb5);
		result.add(resultData);
		//입력한 데이터에 맞는 요금제 출력
		
		JLabel lb6 = new JLabel("메세지 맞춤");
		lb6.setFont(font);
		lb6.setSize(140, 20);
		resultMessage = new JTextField("", 8);
		resultMessage.setFont(font);
		resultMessage.setEditable(false);
		resultMessage.setHorizontalAlignment(JTextField.RIGHT);
		resultMessage.setSize(130, 30);
		result.add(lb6);
		result.add(resultMessage);
		//입력한 메세지에 맞는 요금제 출력
		
		setVisible(true); //프레임을 화면에 출력
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) //뒤로가기 버튼 누를 경우
		{
			FlatRate back = new FlatRate(user);
			setVisible(false);
		    dispose();
		}
		else if(e.getSource().equals(submit))//찾기 버튼 누를 경우
		{
			int callValue = Integer.parseInt(call.getText());
			if(callValue <= 160) resultCall.setText("LTE 34");
			else if(callValue <= 200) resultCall.setText("LTE 42");
			else if(callValue <= 250) resultCall.setText("LTE 52");
			else if(callValue <= 300) resultCall.setText("LTE 62");
			else if(callValue <= 500) resultCall.setText("LTE 72");
			else if(callValue <= 750) resultCall.setText("LTE 85");
			else if(callValue <= 1200) resultCall.setText("LTE 100");
			else resultCall.setText("LTE 120");
			//입력한 음성에 관한 값 찾아서 출력하는 부분
			
			long dataValue = Long.parseLong(data.getText());
			if(dataValue <= 750) resultData.setText("LTE 34");
			else if(dataValue <= 1536) resultData.setText("LTE 42");
			else if(dataValue <= 2662) resultData.setText("LTE 52");
			else if(dataValue <= 6144) resultData.setText("LTE 62");
			else if(dataValue <= 10240) resultData.setText("LTE 72");
			else if(dataValue <= 14336) resultData.setText("LTE 85");
			else if(dataValue <= 20480) resultData.setText("LTE 100");
			else resultData.setText("LTE 120");
			//입력한 데이터에 관한 값 찾아서 출력하는 부분	
			
			int messageValue = Integer.parseInt(message.getText());
			if(messageValue <= 200) resultMessage.setText("LTE 34");
			else if(messageValue <= 250) resultMessage.setText("LTE 52");
			else if(messageValue <= 350) resultMessage.setText("LTE 62");
			else if(messageValue <= 450) resultMessage.setText("LTE 72");
			else if(messageValue <= 650) resultMessage.setText("LTE 85");
			else resultMessage.setText("LTE 100");			
			//입력한 메세지에 관한 값 찾아서 출력하는 부분
		}
	}

}
