package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {
    private HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    // Fetch all hotels from the database
    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    // Fetch hotels by city name
    public ArrayList<Hotel> getHotelCity(String hotelName) {
        return this.hotelDao.getHotelCity(hotelName);
    }

    // Fetch a hotel by its ID
    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    // Save a new hotel and get its generated ID
    public int saveAndGetHotelId(Hotel hotel) {
        return this.hotelDao.saveAndGetHotelId(hotel);
    }

    // Update an existing hotel
    public boolean update(Hotel hotel) {
        if (this.getById(hotel.getHotel_id()) == null) {
            Helper.showMsg(hotel.getHotel_id() + " ID not found"); // Show a message if the hotel ID is not found
            return false;
        }
        return this.hotelDao.update(hotel);
    }

    // Delete a hotel by its ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID not found"); // Show a message if the hotel ID is not found
            return false;
        }
        return this.hotelDao.delete(id);
    }

    // Get hotel data formatted for a table view
    // Columns: Hotel ID, Hotel Name, City, District, Address, Email, Phone, Star Rating
    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotelList) {
        ArrayList<Object[]> hotelArrayList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = hotel.getHotel_id();
            rowObject[i++] = hotel.getHotel_name();
            rowObject[i++] = hotel.getHotel_city();
            rowObject[i++] = hotel.getHotel_district();
            rowObject[i++] = hotel.getHotel_fullAddress();
            rowObject[i++] = hotel.getHotel_email();
            rowObject[i++] = hotel.getHotel_phone();
            rowObject[i++] = hotel.getHotel_star();
            hotelArrayList.add(rowObject);
        }
        return hotelArrayList;
    }

    public ArrayList<Hotel> getList() {
        return null;
    }
}