package business;

import core.Helper;
import dao.HotelDao;
import dao.PropertyDao;
import entity.*;

import java.util.ArrayList;
import java.util.List;

public class PropertyManager {

    private HotelDao hotelDao;
    private PropertyDao propertyDao;
    private RoomManager roomManager;
    private Room room;

    // Constructor to initialize DAO and manager objects
    public PropertyManager() {
        this.propertyDao = new PropertyDao();
        this.room = new Room();
        this.roomManager = new RoomManager();
    }

    // Fetch a property by its ID
    public Property getById(int id) {
        return this.propertyDao.getById(id);
    }

    // Fetch a list of properties by room ID
    public ArrayList<Property> getListByRoomID(int id) {
        return this.propertyDao.getListByRoomID(id);
    }

    // Fetch a property by bed number
    public Property getByBedNum(int id) {
        return this.propertyDao.getByBedNum(id);
    }

    // Get room property details formatted for a table view
    public ArrayList<Object[]> getForTableRoomProperty(int size, int id) {
        ArrayList<Object[]> brandRowList = new ArrayList<>();
        for (Property obj : this.getListByRoomID(id)) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = obj.getRoomProperty();
            rowObject[i++] = obj.getRoomAdultBedNum();
            rowObject[i++] = obj.getRoomChildBedNum();
            rowObject[i++] = roomManager.getById(id).getAdultPrice();
            rowObject[i++] = roomManager.getById(id).getChildPrice();
            rowObject[i++] = obj.getRoomArea();
            rowObject[i++] = roomManager.getById(id).getRoomPrice();
            brandRowList.add(rowObject);
        }
        return brandRowList;
    }

    // Update an existing property
    public boolean update(Property property) {
        if (this.getById(property.getPropertyID()) == null) {
            Helper.showMsg(property.getPropertyID() + " ID not found"); // Show message if the property ID is not found
            return false;
        }
        return this.propertyDao.update(property);
    }

    // Save a new property linked to a hotel
    public boolean save(Property property, int hotelId) {
        return this.propertyDao.save(property, hotelId);
    }

    // Save room property details
    public boolean saveRoomProperty(Property property) {
        return this.propertyDao.saveRoomProperty(property);
    }

    // Fetch a list of properties for a given hotel ID
    public List<String[]> getPropertyList(int hotelId) {
        return this.propertyDao.getPropertyList(hotelId);
    }
}