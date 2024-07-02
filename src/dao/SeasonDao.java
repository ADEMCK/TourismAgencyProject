package dao;

import core.Db;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for handling operations related to hotel seasons in the database.
 */
public class SeasonDao {
    private Connection con;

    /**
     * Constructs a new SeasonDao instance and initializes the database connection.
     */
    public SeasonDao() {
        this.con = Db.getInstance();
    }
    //Retrieves a season by its hotel ID from the database.

    public Season getById(int hotelId) {
        Season season = null;
        String query = "SELECT season_id, hotel_id, start_date, end_date, season_type FROM hotel_seasons WHERE hotel_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                season = match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return season;
    }

    //Retrieves a season by its season ID from the database.
    public Season getSeasonDate(int seasonId) {
        Season season = null;
        String query = "SELECT season_id, hotel_id, start_date, end_date, season_type FROM hotel_seasons WHERE season_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, seasonId);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                season = match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return season;
    }

    //Retrieves all seasons associated with a specific hotel from the database.
    public ArrayList<Season> findAll(int hotelId) {
        ArrayList<Season> seasonArrayList = new ArrayList<>();
        String query = "SELECT season_id, hotel_id, start_date, end_date, season_type FROM hotel_seasons WHERE hotel_id = ? ORDER BY season_id ASC";

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Season season = match(resultSet);
                seasonArrayList.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonArrayList;
    }

    //Retrieves a list of seasons associated with a specific hotel from the database.
    public List<Season> getBySeasonList(int hotelId) {
        List<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM hotel_seasons WHERE hotel_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Season season = match(rs);
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasons;
    }

    //Maps a ResultSet record to a Season object.
    private Season match(ResultSet rs) throws SQLException {
        Season season = new Season();
        season.setSeasonId(rs.getInt("season_id"));
        season.setHotel_id(rs.getInt("hotel_id"));
        season.setSeason_start(LocalDate.parse(rs.getString("start_date")));
        season.setSeason_end(LocalDate.parse(rs.getString("end_date")));
        season.setSeasonName(rs.getString("season_type"));
        return season;
    }

    //Saves a list of seasons associated with a specific hotel in the database.

    public boolean save(List<Season> seasons, int hotelId) {
        String insertQuery = "INSERT INTO hotel_seasons (hotel_id, start_date, end_date, season_type) VALUES (?, ?, ?, ?)";
        boolean success = true;

        try (PreparedStatement pr = con.prepareStatement(insertQuery)) {
            for (Season season : seasons) {
                pr.setInt(1, hotelId);
                pr.setDate(2, Date.valueOf(season.getSeason_start()));
                pr.setDate(3, Date.valueOf(season.getSeason_end()));
                pr.setString(4, season.getSeasonName());

                int rowsAffected = pr.executeUpdate();
                if (rowsAffected != 1) {
                    success = false;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    //Updates a season in the database.
    public boolean update(Season season) {
        String updateQuery = "UPDATE hotel_seasons "
                + "SET start_date = ?, end_date = ? "
                + "WHERE hotel_id = ? AND season_type = ?";

        try (PreparedStatement pr = con.prepareStatement(updateQuery)) {
            pr.setDate(1, Date.valueOf(season.getSeason_start()));
            pr.setDate(2, Date.valueOf(season.getSeason_end()));
            pr.setInt(3, season.getHotel_id());
            pr.setString(4, season.getSeasonName());

            int rowsAffected = pr.executeUpdate();
            return rowsAffected > 0; // Return true if update is successful
        } catch (SQLException e) {
            e.printStackTrace(); // Print error if exception occurs
            return false; // Return false if update fails
        }
    }

    //Executes a SQL query to retrieve seasons based on a custom query string.

    public ArrayList<Season> selectByQuery(String query) {
        ArrayList<Season> seasonArrayList = new ArrayList<>();

        try (ResultSet rs = con.createStatement().executeQuery(query)) {
            while (rs.next()) {
                Season season = match(rs);
                seasonArrayList.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonArrayList;
    }

    //Retrieves a season by its ID from the database.
    public Season getBySeason(int id) {
        Season obj = new Season();
        String query = "SELECT * FROM hotel_seasons WHERE season_id = ?";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj.setSeason_start(LocalDate.parse(rs.getString("start_date")));
                obj.setSeason_end(LocalDate.parse(rs.getString("end_date")));
                obj.setSeasonName(rs.getString("season_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
