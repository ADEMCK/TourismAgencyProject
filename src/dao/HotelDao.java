package dao;

import core.Db;
import core.Helper;
import entity.Hotel;

import java.sql.*;
import java.util.ArrayList;

public class HotelDao {

    private final Connection con;

    // Constructor to initialize the database connection
    public HotelDao() {
        this.con = Db.getInstance();
    }

    // Method to retrieve a hotel by its ID from the database
    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotels WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs); // Map ResultSet to Hotel object
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Method to retrieve all hotels from the database
    public ArrayList<Hotel> findAll() {
        String sql = "SELECT * FROM public.hotels ORDER BY hotel_id ASC";
        return this.selectByQuery(sql);
    }

    // Method to update hotel information in the database
    public boolean update(Hotel hotel) {
        String updateQuery = "UPDATE public.hotels " +
                "SET hotel_name = ?, " +
                "city = ?, " +
                "district = ?, " +
                "full_address = ?, " +
                "email = ?, " +
                "phone_number = ?, " +
                "star = ? " +
                "WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(updateQuery);
            pr.setString(1, hotel.getHotel_name());
            pr.setString(2, hotel.getHotel_city());
            pr.setString(3, hotel.getHotel_district());
            pr.setString(4, hotel.getHotel_fullAddress());
            pr.setString(5, hotel.getHotel_email());
            pr.setString(6, hotel.getHotel_phone());
            pr.setInt(7, hotel.getHotel_star());
            pr.setInt(8, hotel.getHotel_id());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Method to save a new hotel and return its ID from the database
    public int saveAndGetHotelId(Hotel hotel) {
        String insertQuery = "INSERT INTO public.hotels (" +
                "hotel_name, city, district, full_address, email, phone_number, star) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Check if the email address already exists in the database
            if (!isEmailExists(hotel.getHotel_email())) {
                PreparedStatement pr = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                pr.setString(1, hotel.getHotel_name());
                pr.setString(2, hotel.getHotel_city());
                pr.setString(3, hotel.getHotel_district());
                pr.setString(4, hotel.getHotel_fullAddress());
                pr.setString(5, hotel.getHotel_email());
                pr.setString(6, hotel.getHotel_phone());
                pr.setInt(7, hotel.getHotel_star());

                int rowsAffected = pr.executeUpdate();
                if (rowsAffected == 1) {
                    ResultSet generatedKeys = pr.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Return the newly generated hotel ID
                    }
                }
            } else {
                Helper.showMsg("A record with this email already exists"); // Provide appropriate popup messages for successful operations and error messages for incorrect operations to the user
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1; // Return -1 in case of errors or unsuccessful insertion
    }

    // Method to check if a hotel with the given email address already exists in the database
    private boolean isEmailExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM public.hotels WHERE email = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Return true if there is a hotel registered with the email address
            }
        }
        return false;
    }

    // Method to execute a query and return a list of hotels based on the query
    public ArrayList<Hotel> selectByQuery(String query) {
        ArrayList<Hotel> modelList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                modelList.add(this.match(rs)); // Map ResultSet to Hotel objects
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return modelList;
    }

    // Method to map ResultSet data to a Hotel object
    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setHotel_name(rs.getString("hotel_name"));
        obj.setHotel_city(rs.getString("city"));
        obj.setHotel_district(rs.getString("district"));
        obj.setHotel_fullAddress(rs.getString("full_address"));
        obj.setHotel_email(rs.getString("email"));
        obj.setHotel_phone(rs.getString("phone_number"));
        obj.setHotel_star(rs.getInt("star"));
        return obj;
    }

    // Method to retrieve hotel city information based on the hotel name
    public ArrayList<Hotel> getHotelCity(String hotelName) {
        ArrayList<Hotel> roomPropertiesList = new ArrayList<>();
        Hotel obj;
        String query = "SELECT hotel_id, city FROM public.hotels WHERE hotel_name = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, hotelName);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new Hotel();
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setHotel_city(rs.getString("city"));
                roomPropertiesList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomPropertiesList;
    }

    // Method to delete a hotel from the database based on its ID
    public boolean delete(int id) {
        String query = "DELETE FROM public.hotels WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}