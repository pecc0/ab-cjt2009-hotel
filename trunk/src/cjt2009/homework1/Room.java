package cjt2009.homework1;

import java.io.Serializable;

public class Room implements Serializable{
	private static final long serialVersionUID = -1162482088332479429L;
	private int number; 
	private int capacity; 
	private float rate;
	public Room(){
		
	}
	public Room(int number, int capacity, float rate){
		setNumber(number);
		setCapacity(capacity);
		setRate(rate);
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumber() {
		return number;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public float getRate() {
		return rate;
	} 


}
