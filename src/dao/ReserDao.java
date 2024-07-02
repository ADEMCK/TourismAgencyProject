package dao;

import core.Db;
import core.Helper;
import entity.Reser;
import view.GuestInfoAddView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReserDao {
    private Connection con;

    public ReserDao() {
        this.con = Db.getInstance();
    }

    // Method to retrieve reservation details by ID from the database
    public Reser getById(int id) {
        Reser obj = null;
        String query = "SELECT * FROM public.reservations WHERE id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Method to retrieve all reservations from the database
    public ArrayList<Reser> findAll() {
        String sql = "SELECT * FROM public.reservations ORDER BY id ASC";
        return this.selectByQuery(sql);
    }

    // Method to execute a query and return a list of reservations based on the query
    public ArrayList<Reser> selectByQuery(String query) {
        ArrayList<Reser> modelList = new ArrayList<>();
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

    // Method to map ResultSet data to a Reser object
    public Reser match(ResultSet rs) throws SQLException {
        Reser reser = new Reser();
        reser.setId(rs.getInt("id"));
        reser.setRoom_id(rs.getInt("room_id"));
        reser.setReserFllName(rs.getString("reser_fll_name"));
        reser.setReserPhone(rs.getString("reser_phone"));
        reser.setReserEmail(rs.getString("reser_email"));
        reser.setReserNote(rs.getString("reser_note"));
        reser.setReserCheckInDdate(rs.getString("reser_check_in_date"));
        reser.setReserCheckOutDate(rs.getString("reser_check_out_date"));
        reser.setAdultNumb(rs.getString("adult_numb"));
        reser.setChildNumb(rs.getString("child_numb"));
        reser.setTotalPrice(rs.getString("total_price"));
        return reser;
    }

    // Method to save a reservation and return its ID
    public int saveAndGetReserlId(Reser reser) {
        String insertQuery = "INSERT INTO public.reservations (room_id, reser_fll_name, reser_phone, reser_email, reser_note, reser_check_in_date, reser_check_out_date, adult_numb, child_numb, total_price) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pr.setInt(1, reser.getRoom_id());
            pr.setString(2, reser.getReserFllName());
            pr.setString(3, reser.getReserPhone());
            pr.setString(4, reser.getReserEmail());
            pr.setString(5, reser.getReserNote());
            pr.setString(6, reser.getReserCheckInDdate());
            pr.setString(7, reser.getReserCheckOutDate());
            pr.setString(8, reser.getAdultNumb());
            pr.setString(9, reser.getChildNumb());
            pr.setString(10, reser.getTotalPrice());

            int rowsAffected = pr.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet generatedKeys = pr.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the new reservation ID
                }
            } else {
                Helper.showMsg("Reservation could not be added."); // Show error message if insertion fails
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace(); // Handle exception in case of error
        }
        return -1; // Return -1 in case of error or unsuccessful insertion
    }

    // Method to update a reservation in the database
    public boolean update(Reser reser) {
        String query = "UPDATE public.reservations SET " +
                "reser_fll_name = ?, " +
                "reser_phone = ?, " +
                "reser_email = ?, " +
                "reser_note = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, reser.getReserFllName());
            pr.setString(2, reser.getReserPhone());
            pr.setString(3, reser.getReserEmail());
            pr.setString(4, reser.getReserNote());
            pr.setInt(5, reser.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    // Method to delete a reservation from the database by ID
    public boolean delete(int id) {
        String query = "DELETE FROM public.reservations WHERE id =?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Example: Save guest information list into the database associated with a reservation
    public boolean saveGuestInfoList(int reservationId) {
        try {
            // Retrieve guest information list from GuestInfoAddView class
            List<Reser> guestInfoList = GuestInfoAddView.reservations;

            // Insert each Reser object into guest_info table for the given reservationId
            for (Reser reser : guestInfoList) {
                String insertQuery = "INSERT INTO public.guest_info (reservations_id, full_name, national_number, country, guest_class) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                insertStatement.setInt(1, reservationId);
                insertStatement.setString(2, reser.getGuestFllName());
                insertStatement.setString(3, reser.getGuestnationalNumber());
                insertStatement.setString(4, reser.getGuestCountry());
                insertStatement.setString(5, reser.getGuestClass());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if insertion fails
        }
        return true; // Return true if insertion succeeds
    }

    // Method to retrieve guest information list associated with a reservation from the database
    public ArrayList<Reser> getListByGuestInfo(int id) {
        ArrayList<Reser> reserGuestInfoList = new ArrayList<>();
        String query = "SELECT * FROM guest_info WHERE reservations_id = ?";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Reser resers = new Reser();
                resers.setGuestId(rs.getInt("guest_id"));
                resers.setId(rs.getInt("reservations_id"));
                resers.setGuestFllName(rs.getString("full_name"));
                resers.setGuestnationalNumber(rs.getString("national_number"));
                resers.setGuestCountry(rs.getString("country"));
                resers.setGuestClass(rs.getString("guest_class"));
                reserGuestInfoList.add(resers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserGuestInfoList;
    }
}