package business;

import core.Helper;
import dao.ReserDao;
import entity.Reser;
import entity.Room;
import java.util.ArrayList;

public class ReserManager {

    private ReserDao reserDao;
    private Room room;
    private HotelManager hotelManager;
    private RoomManager roomManager;

    // Constructor to initialize DAO and manager objects
    public ReserManager() {
        this.reserDao = new ReserDao();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        this.room = new Room();
    }

    // Fetch a reservation by its ID
    public Reser getById(int id) {
        return this.reserDao.getById(id);
    }

    // Update an existing reservation
    public boolean update(Reser reser) {
        if (this.getById(reser.getId()) == null) {
            Helper.showMsg(reser.getId() + " ID not found"); // Show message if the reservation ID is not found
            return false;
        }
        return this.reserDao.update(reser);
    }

    // Delete a reservation by its ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID not found"); // Show message if the reservation ID is not found
            return false;
        }
        return this.reserDao.delete(id);
    }

    // Save a new reservation and return its ID
    public int saveAndGetReserlId(Reser reser) {
        return this.reserDao.saveAndGetReserlId(reser);
    }

    // Save guest information list for a reservation
    public boolean saveGuestInfoList(int reservationId) {
        return this.reserDao.saveGuestInfoList(reservationId);
    }

    // Fetch all reservations
    public ArrayList<Reser> findAll() {
        return this.reserDao.findAll();
    }

    // Fetch reservations by guest information ID
    public ArrayList<Reser> getListByGuestInfo(int id) {
        return this.reserDao.getListByGuestInfo(id);
    }

    // Get guest information formatted for a table view
    public ArrayList<Object[]> getForTableGuestInfo(int size, int id) {
        ArrayList<Object[]> guestInfoRowList = new ArrayList<>();
        for (Reser obj : this.getListByGuestInfo(id)) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getGuestFllName();
            rowObject[i++] = obj.getGuestnationalNumber();
            rowObject[i++] = obj.getGuestCountry();
            rowObject[i++] = obj.getGuestClass();
            guestInfoRowList.add(rowObject);
        }
        return guestInfoRowList;
    }

    // Get reservation details formatted for a table view
    public ArrayList<Object[]> getForTable(int size, ArrayList<Reser> resersList) {
        ArrayList<Object[]> reserArrayList = new ArrayList<>();
        for (Reser reser : resersList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = reser.getId();
            rowObject[i++] = reser.getRoom_id();
            rowObject[i++] = hotelManager.getById(roomManager.getById(reser.getRoom_id()).getHotelId()).getHotel_name();
            rowObject[i++] = roomManager.getById(reser.getRoom_id()).getRoomType();
            rowObject[i++] = reser.getReserFllName();
            rowObject[i++] = reser.getReserPhone();
            rowObject[i++] = reser.getReserEmail();
            rowObject[i++] = reser.getReserNote();
            rowObject[i++] = reser.getReserCheckInDdate();
            rowObject[i++] = reser.getReserCheckOutDate();
            rowObject[i++] = reser.getAdultNumb();
            rowObject[i++] = reser.getChildNumb();
            rowObject[i++] = reser.getTotalPrice();
            reserArrayList.add(rowObject);
        }
        return reserArrayList;
    }
}