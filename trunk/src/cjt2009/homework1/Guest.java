package cjt2009.homework1;

import java.io.Serializable;

public class Guest implements Serializable{
	private static final long serialVersionUID = 7096767978341777404L;
	private String name;
	private String email;
	public Guest(){
		
	}
	public Guest(String name, String email){
		setName(name);
		setEmail(email);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
}
