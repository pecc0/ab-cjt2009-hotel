# ab-cjt2009-hotel
## Домашно 1 ##

Да се направи симулация на система за хотелски стаи. Трябва да имате следните класове:

Room, който има следните полета:
 * int number; // номер на стаята
 * int capacity; // колко гости събира
 * float rate; // колко е тарифата

Guest, който има следните полета:
 * String name;
 * String email;

Следва интерфейса за хотел който вие тряабва да имплементирате в клас наречен MyHotel:
```java
package cjt2009.homework1;

import java.io.File;
import java.util.Collection;

/**
* Basic interface for your hotel
* 
* @author Vesko Georgiev
*/
public interface Hotel {

/**
* Fills the hotel with the passed collection of rooms.
* 
* @param rooms the rooms to fill the hotel with
*/
public void populateRooms(Collection<Room> rooms);

/**
* Settles the passed guests in the first available room considering its capacity.
* 
* @param guests guests to settle
* @return the first room available
* @throws NoAvailableRoomException if there is no available room 
*/
public Room rentARoom(Collection<Guest> guests) throws NoAvailableRoomException;

/**
* Settles the passed guests in the first available room considering its capacity, and its rate.
* 
* @param guests guests to settle
* @param rate rate of the room to rent
* @return the first room available
* @throws NoAvailableRoomException if there is no available room
*/
public Room rentARoom(Collection<Guest> guests, float rate) throws NoAvailableRoomException;

/**
* Returns all available rooms
* 
* @return all available rooms, or null if there are aren't
*/
public Collection<Room> getAvailableRooms();

/**
* Returns guests that are staying in room with the passed number
* 
* @param roomNumber the room to search the guest in 
* @return the guests that are staying in room with the passed number, or null if the room is available 
*/
public Collection<Guest> getGuestInRoom(int roomNumber);

/**
* Returns the room, which the passed guest is staying in.
* 
* @param guest the guest to search the room for
* @return the room, which the passed guest is staying in, -1 if this guest
* is not in the hotel.
*/
public int getRoom(Guest guest);

/**
* Stores the rooms of the hotel, and their current state,
* e.g. if they are available or if not who is staying in.
* 
* @param f the file to write the information in.
* @return true if the store has passed successfully and false otherwise
*/
public boolean store(File f);

/**
* Loads the rooms of the hotel, and their stored state,
* e.g. if they are available or if not, who is staying in.
* 
* @param f the file to load from
* @return true if the load has passed successfully and false otherwise
*/
public boolean load(File f);
}
```

NoAvailableRoomException съответно трябва да си го напишете.
Спазвайте иментата на класовете както съм ги написал, тъй като ще ви пускам решенията на автоматични тестове.
Също така, моля поставете всички класове в пакет наречен: cjt2009.homework1; т.е. пакета, в който съм дефинирал интерфейса Hotel.
