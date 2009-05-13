package cjt2009.homework1;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Assert;

public class TestMyHotel {
	

	@org.junit.Test
	public void testRent1(){
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(101, 3, 5.f));
		rooms.add(new Room(102, 2, 6.f));
		rooms.add(new Room(103, 1, 7.f));
		rooms.add(new Room(104, 3, 6.f));
		rooms.add(new Room(301, 2, 6.f));
		
		ArrayList<Guest> guests = new ArrayList<Guest>();
		guests.add(new Guest("Dancho", "d@a"));
		try {
			Hotel h = new MyHotel();
			h.populateRooms(rooms);
			h.rentARoom(guests);
			Iterator<Guest> i = h.getGuestInRoom(103).iterator();
			Assert.assertTrue(i.next().getEmail().equals("d@a"));
		} catch (Exception e) {
			AssertionError ae = new AssertionError(
	            "Failed to rent a room");
	        ae.initCause(e);
	        throw ae;
		}
		
	}
	@org.junit.Test(expected=NoAvailableRoomException.class)
	public void testRent2() throws NoAvailableRoomException {
		Hotel h = new MyHotel();
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(101, 3, 5.f));
		rooms.add(new Room(102, 2, 6.f));
		rooms.add(new Room(103, 1, 7.f));
		rooms.add(new Room(104, 3, 6.f));
		rooms.add(new Room(301, 2, 6.f));
		h.populateRooms(rooms);
		ArrayList<Guest> guests = new ArrayList<Guest>();
		guests.add(new Guest("Dancho", "d@a"));
		h.rentARoom(guests, 6.5f);
	}
	
	@org.junit.Test
	public void testRent3() {
		
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(101, 3, 5.f));
		rooms.add(new Room(102, 2, 6.f));
		rooms.add(new Room(103, 1, 7.f));
		rooms.add(new Room(104, 3, 6.f));
		rooms.add(new Room(301, 2, 6.f));
		
		ArrayList<Guest> guests = new ArrayList<Guest>();
		guests.add(new Guest("Dancho", "d@a"));
		guests.add(new Guest("Sasho", "s@a"));
		try {
			Hotel h = new MyHotel();
			h.populateRooms(rooms);
			h.rentARoom(guests);
			Assert.assertTrue("The guests in room 102 must be 2 (Dancho and Sasho)", 
					h.getGuestInRoom(102).size() == 2 
					&& h.getRoom(guests.get(0)) == h.getRoom(guests.get(1))
					&& h.getRoom(guests.get(0)) == 102);
		} catch (Exception e) {
			AssertionError ae = new AssertionError(
	            "Failed to rent a room for Dancho and Sasho");
	        ae.initCause(e);
	        throw ae;
		}
	}
	
	@org.junit.Test
	public void testSaveLoad() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(101, 3, 5.f));
		rooms.add(new Room(102, 2, 6.f));
		rooms.add(new Room(103, 1, 7.f));
		rooms.add(new Room(104, 3, 6.f));
		rooms.add(new Room(301, 2, 6.f));
		
		ArrayList<Guest> guests = new ArrayList<Guest>();
		guests.add(new Guest("Dancho", "d@a"));
		guests.add(new Guest("Sasho", "s@a"));
		guests.add(new Guest("Vasko", "v@a"));
		
		ArrayList<Guest> guests1 = new ArrayList<Guest>();
		guests1.add(new Guest("P", "p@a"));
		guests1.add(new Guest("T", "t@a"));
		
		File f = new File("testSaveLoad");
		try {
			Hotel srcHotel = new MyHotel();
			srcHotel.populateRooms(rooms);
			srcHotel.rentARoom(guests);
			srcHotel.rentARoom(guests1);
			srcHotel.store(f);
			
			Hotel dstHotel = new MyHotel();
			dstHotel.load(f);
			Assert.assertTrue("The guests in room 101 must be 3. Probably a save-load error occured", 
					dstHotel.getGuestInRoom(101).size() == 3);
			
			Iterator<Guest> i = dstHotel.getGuestInRoom(102).iterator();
			Guest g = i.next();
			Assert.assertTrue("Room 102 must have guest 'P'. Probably a save-load error occured", 
					dstHotel.getGuestInRoom(102).size() == 2
					&& g.getName().equals("P"));
		} catch (Exception e) {
			AssertionError ae = new AssertionError(
	            "Exception while testing");
	        ae.initCause(e);
	        throw ae;
		}
	}
	
	@org.junit.Test
	public void testGetRoom() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(101, 3, 5.f));
		rooms.add(new Room(102, 2, 6.f));
		
		ArrayList<Guest> guests = new ArrayList<Guest>();
		guests.add(new Guest("Dancho", "d@a"));
		try {
			Hotel h = new MyHotel();
			h.populateRooms(rooms);
			h.rentARoom(guests);
			Assert.assertTrue("Dancho must be in room 102", h.getRoom(guests.get(0)) == 102);
			Assert.assertTrue("Mr. Not Here must not be here", 
					h.getRoom(new Guest("Not Here", "n@h.com")) == -1);
		} catch (Exception e) {
			AssertionError ae = new AssertionError(
	            "Exception while testing");
	        ae.initCause(e);
	        throw ae;
		}
	}
	@org.junit.Test
	public void testGetGuests() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(101, 3, 5.f));
		rooms.add(new Room(102, 2, 6.f));
		
		ArrayList<Guest> guests = new ArrayList<Guest>();
		guests.add(new Guest("Dancho", "d@a"));
		try {
			Hotel h = new MyHotel();
			h.populateRooms(rooms);
			h.rentARoom(guests);
			Collection<Guest> g = h.getGuestInRoom(102);
			
			Assert.assertTrue("Room 102 must be occupied by Dancho", 
					g != null 
					&& g.iterator().next().getEmail() == "d@a");
			
			Assert.assertTrue("Room 101 must be empty", 
					h.getGuestInRoom(101) == null);
		} catch (Exception e) {
			AssertionError ae = new AssertionError(
	            "Exception while testing");
	        ae.initCause(e);
	        throw ae;
		}
	}
	
	@org.junit.Test
	public void testAvailableRooms() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(101, 3, 5.f));
		rooms.add(new Room(102, 2, 6.f));
		
		ArrayList<Guest> guests = new ArrayList<Guest>();
		guests.add(new Guest("Dancho", "d@a"));
		try {
			Hotel h = new MyHotel();
			h.populateRooms(rooms);
			h.rentARoom(guests);
			Assert.assertTrue("there must be 1 available room", h.getAvailableRooms().size() == 1);
			guests = new ArrayList<Guest>();
			guests.add(new Guest("Pencho", "p@a"));
			h.rentARoom(guests);
			Assert.assertTrue("there must be no available room", h.getAvailableRooms() == null);
		} catch (Exception e) {
				AssertionError ae = new AssertionError(
	            "Exception while testing");
	        ae.initCause(e);
	        throw ae;
		}
	}
}
