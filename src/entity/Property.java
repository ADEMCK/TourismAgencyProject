package entity;

import java.util.List;

/**
 * This class represents a Property entity which can be associated with hotels and rooms, including various attributes
 * like property ID, property names, hotel ID, room properties, and room details.
 */
public class Property {

    private int propertyID;
    private List<String> propertyNames; // List to store property names
    private int hotel_id; // Using a more general name hotelId to store hotel ID
    private int roomPropertyId;
    private String roomProperty;
    private int roomId;
    private int roomAdultBedNum;
    private int roomChildBedNum;
    private int roomArea;
    private Room room;

    /**
     * Default constructor.
     */
    public Property() {
    }

    //Constructor to initialize a Property object with room-related attributes.
    public Property(int roomPropertyId, String roomProperty, int roomId, int roomAdultBedNum, int roomChildBedNum, int roomArea) {
        this.roomPropertyId = roomPropertyId;
        this.roomProperty = roomProperty;
        this.roomId = roomId;
        this.roomAdultBedNum = roomAdultBedNum;
        this.roomChildBedNum = roomChildBedNum;
        this.roomArea = roomArea;
    }

    //Constructor to initialize a Property object with a list of property names.
    public Property(List<String> propertyNames) {
        this.propertyNames = propertyNames;
    }

    //Constructor to initialize a Property object with property ID, list of property names, and hotel ID.
    public Property(int propertyID, List<String> propertyNames, int hotel_id) {
        this.propertyID = propertyID;
        this.propertyNames = propertyNames;
        this.hotel_id = hotel_id;
    }

    // Getters and Setters for room-related attributes
    public int getRoomPropertyId() {
        return roomPropertyId;
    }

    public void setRoomPropertyId(int roomPropertyId) {
        this.roomPropertyId = roomPropertyId;
    }

    public int getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(int roomArea) {
        this.roomArea = roomArea;
    }

    public int getRoomAdultBedNum() {
        return roomAdultBedNum;
    }

    public void setRoomAdultBedNum(int roomAdultBedNum) {
        this.roomAdultBedNum = roomAdultBedNum;
    }

    public int getRoomChildBedNum() {
        return roomChildBedNum;
    }

    public void setRoomChildBedNum(int roomChildBedNum) {
        this.roomChildBedNum = roomChildBedNum;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomProperty() {
        return roomProperty;
    }

    public void setRoomProperty(String roomProperty) {
        this.roomProperty = roomProperty;
    }

    // Getters and Setters for general property attributes

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public List<String> getPropertyNames() {
        return propertyNames;
    }

    public void setPropertyNames(List<String> propertyNames) {
        this.propertyNames = propertyNames;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyID=" + propertyID +
                ", propertyNames=" + propertyNames +
                ", hotel_id=" + hotel_id +
                ", roomPropertyId=" + roomPropertyId +
                ", roomProperty='" + roomProperty + '\'' +
                ", roomId=" + roomId +
                ", roomAdultBedNum='" + roomAdultBedNum + '\'' +
                ", roomChildBedNum='" + roomChildBedNum + '\'' +
                ", roomArea=" + roomArea +
                ", room=" + room +
                '}';
    }
}
