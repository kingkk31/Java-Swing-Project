package javaproject;

import javax.swing.text.*;

public class JTextLimit extends PlainDocument {
	private int limit;
	public JTextLimit(int limit)
	{
		super();
		this.limit=limit;
	}//������ : ������ ���̸� ���ڷ� ����
	public JTextLimit() {
		super();
	}//������ : ���ڸ� �Է��ϰ��Ҷ�
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
	{
		if(str==null)
			return;
		if(getLength()+str.getBytes().length <=limit) //��������
			super.insertString(offset,str,attr);
		if(str.charAt(0)>='0' && str.charAt(0)<='9') //���ڸ� �Է��ϰ���
			super.insertString(offset, str, attr);
	}//�ؽ�Ʈ �ʵ带 ä��� �޼ҵ� : �������̵�
	
}
