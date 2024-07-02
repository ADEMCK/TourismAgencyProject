package entity;

public class Types {
    private int typeId;
    private int hotelId;
    private String typeName;

    // Default constructor
    public Types() {
    }

    // Constructor with parameters
    public Types(int typeId, String typeName, int hotelId) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.hotelId = hotelId;
    }

    // Getter and Setter methods

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    // toString method for easy representation of the object as a string
    @Override
    public String toString() {
        return "Types{" +
                "typeId=" + typeId +
                ", hotelId=" + hotelId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}