package cjt2009.homework1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * My implementation of the Hotel interface
 * 
 * @author Petko Petkov
 *
 */
public class MyHotel implements Hotel {

	private Hashtable<Integer, HotelRoom> rooms = new Hashtable<Integer, HotelRoom>();
	private Hashtable<String, HotelGuest> guests = new Hashtable<String, HotelGuest>();
	
	@Override
	public void populateRooms(Collection<Room> rooms) {
		for (Room r : rooms){
			this.rooms.put(new Integer(r.getNumber()), new HotelRoom(r));
		}
	}

	@Override
	public Room rentARoom(Collection<Guest> guests)
			throws NoAvailableRoomException {
		return rentARoom(guests, -1.f);
	}

	@Override
	public Room rentARoom(Collection<Guest> guests, float rate)
			throws NoAvailableRoomException {
		
		int guestsNumber = guests.size();
		
		ArrayList<HotelRoom> arrList = new ArrayList<HotelRoom>(this.rooms.values());
		Collections.sort(arrList, new Comparator<HotelRoom>(){
			@Override
			public int compare(HotelRoom o1, HotelRoom o2) {
				int delta = o1.getCapacity() - o2.getCapacity();
				if (delta == 0) {
					//sort the equal-capacity rooms by number
					return o1.getNumber() - o2.getNumber();
				} else {
					return delta;
				}
			}
		});
		HotelRoom result = null;
		for(HotelRoom r : arrList){
			if (!r.isOccupied()
					&& r.getCapacity() >= guestsNumber
					&& (rate == -1.f || rate == r.getRate())) {
				result = r;
				break;
			}
		}
		if(result == null){	
			throw new NoAvailableRoomException();
		} else {
			for(Guest g:guests){
				HotelGuest hg = addGuest(g);
				hg.setRoomNumber(result.getNumber());
				result.getGuestEmails().add(g.getEmail());
			}
			return result;
		}
	}

	@Override
	public Collection<Room> getAvailableRooms() {
		Collection<Room> result = new ArrayList<Room>();
		Enumeration<HotelRoom> e = rooms.elements();
		HotelRoom r = e.nextElement();
		for(; e.hasMoreElements(); r = e.nextElement()){
			if (!r.isOccupied()) {
				result.add(r);
			}
		}
		return result;
	}

	@Override
	public Collection<Guest> getGuestInRoom(int roomNumber) {
		Collection<Guest> result = new ArrayList<Guest>();
		HotelRoom room = rooms.get(new Integer(roomNumber));
		for (String email:room.getGuestEmails()){
			result.add(this.guests.get(email));
		}
		return result;
	}
	
	@Override
	public int getRoom(Guest guest) {
		HotelGuest g = guests.get(guest.getEmail());
		return g.getRoomNumber();
	}
	
	@Override
	public boolean store(File f) {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			//Enumeration<HotelRoom> e = rooms.elements();
			//HotelRoom r = e.nextElement();
			//for(; e.hasMoreElements(); r = e.nextElement()){
			//	oos.writeObject(r);
			//}
			oos.writeObject(rooms);
			oos.writeObject(guests);
			return true;
		} 
		catch (IOException e) {
			return false;
		}
		finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	@Override
	public boolean load(File f) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(f);
	        ois = new ObjectInputStream(fis);
			rooms = (Hashtable<Integer, HotelRoom>)ois.readObject();
			guests = (Hashtable<String, HotelGuest>)ois.readObject();
			return true;
			
		} 
		catch (IOException e) 
		{
			return false;
		}
		catch (ClassNotFoundException e) {
			return false;
		}
		finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	/**
	 * Creates a hotel guest from the input base guest, 
	 * adds the new hotel guest to the guests pool 
	 * and returns a reference to it  
	 * @param g A base guest
	 * @return reference to the new hotel guest
	 */
	private HotelGuest addGuest(Guest g){
		HotelGuest hg = new HotelGuest(g);
		guests.put(g.getEmail(), hg);
		return hg;
	}
	
	
}
