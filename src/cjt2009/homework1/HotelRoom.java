package cjt2009.homework1;

import java.util.ArrayList;

/**
 * Extends the base Room with a field where to store a list of e-mails 
 * of the guests that are currently using this room
 * Also added method to check whether the room is occupied
 * 
 * @author Petko Petkov
 *
 */
public class HotelRoom extends Room {
	private static final long serialVersionUID = -482459580656499567L;
	private ArrayList<String> guestEmails = new ArrayList<String>();
	
	/**
	 * Constructs a Hotel room from base room
	 * @param r The base room
	 */
	public HotelRoom(Room r){
		setCapacity(r.getCapacity());
		setNumber(r.getNumber());
		setRate(r.getRate());
	}
	/**
	 * Whether the room has guests in it
	 * @return Whether the room has guests in it
	 */
	public boolean isOccupied(){
		return !getGuestEmails().isEmpty();
	}

	/**
	 * Returns the e-mails of the guests in the room. Used to read and manipulate with these e-mails  
	 * @return The e-mails of the guests in the room 
	 */
	public ArrayList<String> getGuestEmails() {
		return guestEmails;
	}
	
}
