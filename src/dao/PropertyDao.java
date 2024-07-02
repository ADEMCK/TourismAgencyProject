package dao;

import core.Db;
import entity.Property;
import org.postgresql.jdbc.PgArray;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDao {
    private Connection con;

    private final HotelDao hotelDao = new HotelDao();

    public PropertyDao() {
        this.con = Db.getInstance();
    }

    // Method to retrieve property information by its ID from the database
    public Property getById(int id) {
        Property obj = null;
        String query = "SELECT * FROM public.hotel_property WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs); // Map ResultSet to Property object
            } else {
                // If ResultSet is empty or rs.next() returns false, create a new Property object and set the id
                obj = new Property();
                obj.setHotel_id(id); // Set the incoming id
                obj.setPropertyNames(List.of(new String[]{"Free Parking",
                        "Free WiFi",
                        "Swimming Pool",
                        "Fitness Center",
                        "Hotel Concierge",
                        "SPA",
                        "24/7 Room Service"}));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Method to retrieve a list of property names associated with a hotel ID from the database
    public List<String[]> getPropertyList(int hotelId) {
        List<String[]> propertyList = new ArrayList<>();

        String query = "SELECT * FROM public.hotel_property WHERE hotel_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, hotelId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Array propertyNamesArray = rs.getArray("property_names");
                if (propertyNamesArray != null) {
                    // Obtain PostgreSQL array as PgArray
                    PgArray pgArray = (PgArray) propertyNamesArray;

                    // Convert PgArray to Java array
                    Object[] pgElements = (Object[]) pgArray.getArray();

                    // Convert Java array to String array
                    String[] propertyNames = new String[pgElements.length];
                    for (int i = 0; i < pgElements.length; i++) {
                        propertyNames[i] = (String) pgElements[i];
                    }
                    // Add to the list
                    propertyList.add(propertyNames);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return propertyList;
    }

    // Method to map ResultSet data to a Property object
    public Property match(ResultSet rs) throws SQLException {
        Property property = new Property();
        property.setPropertyID(rs.getInt("property_id"));
        property.setHotel_id(rs.getInt("hotel_id"));

        // Get the java.sql.Array from the ResultSet
        Array propertyNamesArray = rs.getArray("property_names");

        if (propertyNamesArray != null) {
            // Convert java.sql.Array to Object array
            Object[] propertyNamesData = (Object[]) propertyNamesArray.getArray();

            // Convert Object array to List<String>
            List<String> propertyNamesList = new ArrayList<>();
            for (Object element : propertyNamesData) {
                if (element != null) {
                    propertyNamesList.add(element.toString());
                }
            } // Set the propertyNames list in the Property object
            property.setPropertyNames(propertyNamesList);
        }
        return property;
    }

    // Method to retrieve all properties from the database
    public ArrayList<Property> findAll() {
        String sql = "SELECT * FROM public.hotel_property ORDER BY property_id ASC";
        return this.selectByQuery(sql);
    }

    // Method to execute a query and return a list of properties based on the query
    public ArrayList<Property> selectByQuery(String query) {
        ArrayList<Property> modelList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                modelList.add(this.match(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return modelList;
    }

    // Method to save property information into the database associated with a hotel
    public boolean save(Property property, int hotelId) {
        String insertQuery = "INSERT INTO public.hotel_property (property_names, hotel_id) VALUES (?, ?)";

        try {
            PreparedStatement pr = con.prepareStatement(insertQuery);
            Array propertyNamesArray = con.createArrayOf("text", property.getPropertyNames().toArray());
            pr.setArray(1, propertyNamesArray);
            pr.setInt(2, hotelId);

            int rowsAffected = pr.executeUpdate();
            return rowsAffected > 0; // Return true if insertion is successful
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false; // Return false in case of errors or unsuccessful insertion
    }

    // Method to update property information in the database
    public boolean update(Property property) {
        String query = "UPDATE public.hotel_property SET " +
                "property_id = ?," +
                "property_names = ? " +  // Correction: Comma was unnecessary
                "WHERE hotel_id = ?";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, property.getPropertyID());
            if (!property.getPropertyNames().contains("Hotel Properties")) {
                Array propertyNamesArray = con.createArrayOf("text", property.getPropertyNames().toArray());
                pr.setArray(2, propertyNamesArray);
            }
            pr.setInt(3, property.getHotel_id());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Method to save room property information into the database
    public boolean saveRoomProperty(Property property) {
        String query = "INSERT INTO room_properties (property, room_id, adult_bed_num, child_bed_num, area ) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, property.getRoomProperty());
            pr.setInt(2, property.getRoomId());
            pr.setInt(3, property.getRoomAdultBedNum());
            pr.setInt(4, property.getRoomChildBedNum());
            pr.setInt(5, property.getRoomArea());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Method to delete property information from the database based on hotel ID
    public boolean delete(int id) {
        String query = "DELETE FROM public.hotel_property WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Method to retrieve a list of properties based on room ID from the database
    public ArrayList<Property> getListByRoomID(int id) {
        ArrayList<Property> roomPropertiesList = new ArrayList<>();
        Property obj;
        Property property = new Property();
        String query = "SELECT * FROM room_properties WHERE room_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new Property();
                obj.setRoomProperty(rs.getString("property"));
                obj.setRoomAdultBedNum(rs.getInt("adult_bed_num"));
                obj.setRoomChildBedNum(rs.getInt("child_bed_num"));
                obj.setRoomArea(rs.getInt("area"));
                roomPropertiesList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomPropertiesList;
    }

    // Method to retrieve property information based on room ID from the database
    public Property getByBedNum(int id) {
        Property obj = new Property();
        String query = "SELECT adult_bed_num, child_bed_num FROM public.room_properties WHERE room_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj.setRoomAdultBedNum(rs.getInt("adult_bed_num"));
                obj.setRoomChildBedNum(rs.getInt("child_bed_num"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
}