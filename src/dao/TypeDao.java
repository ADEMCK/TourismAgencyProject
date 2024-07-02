package dao;

import core.Db;
import entity.Types;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Data access object for handling operations related to hotel types in the database.
 */
public class TypeDao {

    private Connection con;

    /**
     * Constructs a new TypeDao instance and initializes the database connection.
     */
    public TypeDao() {
        this.con = Db.getInstance();
    }

    //Retrieves a type by its hotel ID from the database.

    public Types getById(int id) {
        Types obj = null;
        String query = "SELECT * FROM public.type_hotel WHERE hotel_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs != null && !rs.isClosed() && rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    //Retrieves a type name by its type ID from the database.
    public String getByTypeId(int id) {
        String typeName = null;

        String query = "SELECT type_name FROM public.type_hotel WHERE type_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, id);
            try (ResultSet rs = pr.executeQuery()) {
                if (rs.next()) {
                    typeName = rs.getString("type_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeName;
    }

    //Retrieves all types associated with a specific hotel from the database.
    public ArrayList<Types> findAll(int hotelId) {
        ArrayList<Types> typesList = new ArrayList<>();

        String query = "SELECT type_id, hotel_id, type_name FROM public.type_hotel WHERE hotel_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Types types = match(resultSet);
                typesList.add(types);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typesList;
    }

    //Maps a ResultSet record to a Types object.
    private Types match(ResultSet rs) throws SQLException {
        Types obj = new Types();
        obj.setTypeId(rs.getInt("type_id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setTypeName(rs.getString("type_name"));
        return obj;
    }

    //Saves a list of types associated with a specific hotel in the database.
    public boolean save(int hotelId, List<String> rightList) {
        try {
            // Iterate through the list of types and add them to the database
            for (String feature : rightList) {
                String insertQuery = "INSERT INTO type_hotel (hotel_id, type_name) VALUES (?, ?)";
                PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                insertStatement.setInt(1, hotelId);
                insertStatement.setString(2, feature);
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if the insertion fails
        }
        return true; // Return true if the insertion is successful
    }

    //Updates the types associated with a specific hotel in the database.
    public boolean update(int hotelId, List<String> rightList) {
        try {
            // Retrieve existing types from the database
            String selectQuery = "SELECT type_name FROM type_hotel WHERE hotel_id = ?";
            PreparedStatement selectStatement = con.prepareStatement(selectQuery);
            selectStatement.setInt(1, hotelId);
            ResultSet resultSet = selectStatement.executeQuery();

            HashSet<String> existingFeatures = new HashSet<>();
            while (resultSet.next()) {
                existingFeatures.add(resultSet.getString("type_name"));
            }

            // Add new types to the database if they do not already exist
            for (String feature : rightList) {
                if (!existingFeatures.contains(feature)) {
                    String insertQuery = "INSERT INTO type_hotel (hotel_id, type_name) VALUES (?, ?)";
                    PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                    insertStatement.setInt(1, hotelId);
                    insertStatement.setString(2, feature);
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //Deletes types associated with a specific hotel from the database.
    public boolean deleteType(int hotelId, List<String> rightList) {
        try {
            // Retrieve existing types from the database
            String selectQuery = "SELECT type_name FROM type_hotel WHERE hotel_id = ?";
            PreparedStatement selectStatement = con.prepareStatement(selectQuery);
            selectStatement.setInt(1, hotelId);
            ResultSet resultSet = selectStatement.executeQuery();

            HashSet<String> existingFeatures = new HashSet<>();
            while (resultSet.next()) {
                existingFeatures.add(resultSet.getString("type_name"));
            }

            // Delete types from the database if they exist
            for (String feature : rightList) {
                if (existingFeatures.contains(feature)) {
                    String deleteQuery = "DELETE FROM type_hotel WHERE hotel_id = ? AND type_name = ?";
                    PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
                    deleteStatement.setInt(1, hotelId);
                    deleteStatement.setString(2, feature);
                    deleteStatement.executeUpdate();
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}