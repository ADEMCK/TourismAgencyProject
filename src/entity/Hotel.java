package entity;

/**
 * This class represents a Hotel entity with various attributes such as ID, name, city, district, address, phone, email, and star rating.
 */
public class Hotel {
    private int hotel_id;
    private String hotel_name;
    private String hotel_city;
    private String hotel_district;
    private String hotel_fullAddress;
    private String hotel_phone;
    private String hotel_email;
    private int hotel_star;

    /**
     * Default constructor.
     */
    public Hotel() {
    }

    //Parameterized constructor to initialize a Hotel object.
    public Hotel(int hotel_id, String hotel_name, String hotel_city, String hotel_district, int hotel_star, String hotel_fullAddress, String hotel_phone,
                 String hotel_email) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_star = hotel_star;
        this.hotel_city = hotel_city;
        this.hotel_district = hotel_district;
        this.hotel_fullAddress = hotel_fullAddress;
        this.hotel_phone = hotel_phone;
        this.hotel_email = hotel_email;
    }

    //Gets the hotel ID.
    public int getHotel_id() {
        return hotel_id;
    }

    //Sets the hotel ID.
    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    //Gets the hotel name.
    public String getHotel_name() {
        return hotel_name;
    }

    //Sets the hotel name.
    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    //Gets the city where the hotel is located.
    public String getHotel_city() {
        return hotel_city;
    }

    //Sets the city where the hotel is located.
    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    //Gets the district where the hotel is located.
    public String getHotel_district() {
        return hotel_district;
    }

    //Sets the district where the hotel is located.
    public void setHotel_district(String hotel_district) {
        this.hotel_district = hotel_district;
    }

    //Gets the star rating of the hotel.
    public int getHotel_star() {
        return hotel_star;
    }

    //Sets the star rating of the hotel.
    public void setHotel_star(int hotel_star) {
        this.hotel_star = hotel_star;
    }

    //Gets the full address of the hotel.
    public String getHotel_fullAddress() {
        return hotel_fullAddress;
    }

    //Sets the full address of the hotel.
    public void setHotel_fullAddress(String hotel_fullAddress) {
        this.hotel_fullAddress = hotel_fullAddress;
    }

    //Gets the contact phone number of the hotel.
    public String getHotel_phone() {
        return hotel_phone;
    }

    //Sets the contact phone number of the hotel.
    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    //Gets the contact email address of the hotel.
    public String getHotel_email() {
        return hotel_email;
    }

    //Sets the contact email address of the hotel.
    public void setHotel_email(String hotel_email) {
        this.hotel_email = hotel_email;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotel_id +
                ", hotel_name='" + hotel_name + '\'' +
                ", hotel_city='" + hotel_city + '\'' +
                ", hotel_district='" + hotel_district + '\'' +
                ", hotel_address='" + hotel_fullAddress + '\'' +
                ", hotel_phone='" + hotel_phone + '\'' +
                ", hotel_email='" + hotel_email + '\'' +
                ", hotel_star='" + hotel_star + '\'' +
                '}';
    }
}
