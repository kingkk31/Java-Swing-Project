package javaproject;

//사용자 정보를 담은 클래스
public class User{
	String id, name, phone, flat_rate;
	int call, message;
	long data;
	
	User(String id, String name, String phone, String flat_rate, int call, long data, int message)
	{
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.flat_rate = flat_rate;
		this.call = call;
		this.data = data;
		this.message = message;
	}
}