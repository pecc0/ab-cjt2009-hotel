package cjt2009.homework1;

/**
 * Extends the base guest in order to add field for string the 
 * room number that the guest has rented
 * 
 * @author Petko Petkov
 *
 */
public class HotelGuest extends Guest {
	private static final long serialVersionUID = -4638018475153420490L;
	private int roomNumber = -1;
	public HotelGuest(Guest g){
		setEmail(g.getEmail());
		setName(g.getName());
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
}
