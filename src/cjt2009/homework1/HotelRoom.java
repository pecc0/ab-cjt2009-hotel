package cjt2009.homework1;

import java.util.ArrayList;


public class HotelRoom extends Room {
	private static final long serialVersionUID = -482459580656499567L;
	private ArrayList<String> guestEmails = new ArrayList<String>();
	
	public HotelRoom(Room r){
		setCapacity(r.getCapacity());
		setNumber(r.getNumber());
		setRate(r.getRate());
	}
	
	public boolean isOccupied(){
		return !getGuestEmails().isEmpty();
	}

	public void setGuestEmails(ArrayList<String> guestEmails) {
		
		this.guestEmails = guestEmails;
	}

	public ArrayList<String> getGuestEmails() {
		return guestEmails;
	}
	
}
