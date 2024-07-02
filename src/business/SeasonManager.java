package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;
import java.util.List;

public class SeasonManager {

    private final SeasonDao seasonDao;

    // Constructor to initialize SeasonDao
    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    // Fetch a season by its ID
    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }

    // Fetch season dates by season ID
    public Season getSeasonDate(int seasonId) {
        return this.seasonDao.getSeasonDate(seasonId);
    }

    // Get a list of seasons by a specific ID
    public List<Season> getBySeasonList(int id) {
        return this.seasonDao.getBySeasonList(id);
    }

    // Find all seasons by a specific ID
    public ArrayList<Season> findAll(int id) {
        return this.seasonDao.findAll(id);
    }

    // Get season details formatted for a table view
    public ArrayList<Object[]> getForTable(int size, int id) {
        ArrayList<Object[]> seasonRowList = new ArrayList<>();
        for (Season season : this.findAll(id)) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = season.getHotel_id();
            rowObject[i++] = season.getSeason_start();
            rowObject[i++] = season.getSeason_end();
            rowObject[i++] = season.getSeasonName();
            seasonRowList.add(rowObject);
        }
        return seasonRowList;
    }

    // Save a list of seasons for a specific hotel
    public boolean save(List<Season> seasons, int hotelId) {
        return this.seasonDao.save(seasons, hotelId);
    }

    // Update a season
    public boolean update(Season season) {
        if (season.getHotel_id() == 0) {
            Helper.showMsg("ID not found"); // Show message if the hotel ID is not found
            return false;
        }
        return this.seasonDao.update(season);
    }

    // Fetch a season by its ID
    public Season getBySeason(int id) {
        return this.seasonDao.getBySeason(id);
    }
}