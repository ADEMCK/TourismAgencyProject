package entity;

import java.time.LocalDate;

/**
 * This class represents a season entity with attributes for the season details and the associated hotel.
 */
public class Season {

    private int seasonId;
    private LocalDate season_start;
    private LocalDate season_end;
    private int hotel_id;
    private String seasonName;

    /**
     * Default constructor.
     */
    public Season() {
    }

    //Constructor to initialize a Season object with specific details.
    public Season(int seasonId, LocalDate season_start, LocalDate season_end, String seasonName, int hotel_id) {
        this.seasonId = seasonId;
        this.season_start = season_start;
        this.season_end = season_end;
        this.seasonName = seasonName;
        this.hotel_id = hotel_id;
    }

    // Getter and setter methods

    //Gets the name of the season.
    public String getSeasonName() {
        return seasonName;
    }

    //Sets the name of the season.
    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    //Gets the unique identifier of the season.
    public int getSeasonId() {
        return seasonId;
    }

    //Sets the unique identifier of the season.
    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    //Gets the start date of the season.
    public LocalDate getSeason_start() {
        return season_start;
    }

    //Sets the start date of the season.
    public void setSeason_start(LocalDate season_start) {
        this.season_start = season_start;
    }

    //Gets the unique identifier of the associated hotel.
    public int getHotel_id() {
        return hotel_id;
    }

    //Sets the unique identifier of the associated hotel.
    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    //Gets the end date of the season.
    public LocalDate getSeason_end() {
        return season_end;
    }

    //Sets the end date of the season.
    public void setSeason_end(LocalDate season_end) {
        this.season_end = season_end;
    }

    //Returns a string representation of the Season object.
    @Override
    public String toString() {
        return "Season{" +
                "seasonId=" + seasonId +
                ", season_start=" + season_start +
                ", season_end=" + season_end +
                ", hotel_id=" + hotel_id +
                ", seasonName='" + seasonName + '\'' +
                '}';
    }
}