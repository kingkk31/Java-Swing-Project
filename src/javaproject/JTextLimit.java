package javaproject;

import javax.swing.text.*;

public class JTextLimit extends PlainDocument {
	private int limit;
	public JTextLimit(int limit)
	{
		super();
		this.limit=limit;
	}//생성자 : 제한할 길이를 인자로 받음
	public JTextLimit() {
		super();
	}//생성자 : 숫자만 입력하게할때
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
	{
		if(str==null)
			return;
		if(getLength()+str.getBytes().length <=limit) //길이제한
			super.insertString(offset,str,attr);
		if(str.charAt(0)>='0' && str.charAt(0)<='9') //숫자만 입력하게함
			super.insertString(offset, str, attr);
	}//텍스트 필드를 채우는 메소드 : 오버라이드
	
}
