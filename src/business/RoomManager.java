package business;

import core.Helper;
import dao.RoomDao;
import entity.Room;

import java.util.ArrayList;

public class RoomManager {

    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private final RoomDao roomDao;

    // Constructor to initialize DAO and manager objects
    public RoomManager() {
        this.roomDao = new RoomDao();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
    }

    // Fetch a room by its ID
    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    // Fetch all rooms
    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    // Save a new room
    public int save(Room room) {
        return this.roomDao.save(room);
    }

    // Update stock for a room
    public boolean stockUpdate(Room room, int num) {
        return this.roomDao.stockUpdate(room, num);
    }

    // Delete a room by its ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID not found"); // Show message if the room ID is not found
            return false;
        }
        return this.roomDao.delete(id);
    }

    // Get room details formatted for a table view
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> roomList) {
        ArrayList<Object[]> roomArrayList = new ArrayList<>();
        for (Room room : roomList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = room.getId();
            rowObject[i++] = hotelManager.getById(room.getHotelId()).getHotel_name();
            rowObject[i++] = hotelManager.getById(room.getHotelId()).getHotel_city();
            rowObject[i++] = room.getRoomType();
            rowObject[i++] = room.getStock();
            rowObject[i++] = seasonManager.getSeasonDate(room.getSeasonId()).getSeason_start() + " - " + seasonManager.getSeasonDate(room.getSeasonId()).getSeason_end();
            rowObject[i++] = room.getAdultPrice();
            rowObject[i++] = room.getChildPrice();
            rowObject[i++] = room.getHotelTypeId();
            roomArrayList.add(rowObject);
        }
        return roomArrayList;
    }

    // Search for rooms based on various criteria
    public ArrayList<Room> searchForRooms(String startDate, String endDate, String searchCity, String hotelName, int adultNum, int childNum) {
        return this.roomDao.searchForRooms(startDate, endDate, searchCity, hotelName, adultNum, childNum);
    }

    public int fetchRoomTypePrice(String string) {
        return 0;
    }

    public int fetchPansiyonTypePrice(int hotelTypeId) {
        return 0;
    }

    public int fetchSeasonPrice(int seasonId) {
        return seasonId;
    }
}