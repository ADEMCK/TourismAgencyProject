package business;

import core.Helper;
import dao.TypeDao;
import entity.Types;

import java.util.ArrayList;
import java.util.List;

public class TypeManager {
    private final TypeDao typeDao;

    // Constructor to initialize TypeDao
    public TypeManager() {
        this.typeDao = new TypeDao();
    }

    // Find all types by a specific hotel ID
    public ArrayList<Types> findAll(int id) {
        return this.typeDao.findAll(id);
    }

    // Get types formatted for a table view
    public ArrayList<Object[]> getForTable(int size, int id) {
        ArrayList<Object[]> typeRowList = new ArrayList<>();
        for (Types type : this.findAll(id)) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = type.getHotelId();
            rowObject[i++] = type.getTypeName();
            typeRowList.add(rowObject);
        }
        return typeRowList;
    }

    // Get a type by its ID
    public Types getById(int id) {
        return this.typeDao.getById(id);
    }

    // Get a type name by its ID
    public String getByTypeId(int id) {
        return this.typeDao.getByTypeId(id);
    }

    // Update types for a specific hotel ID
    public boolean update(int hotelId, List<String> typeList) {
        if (hotelId == 0) {
            Helper.showMsg("ID not found"); // Show message if the hotel ID is not found
            return false;
        }
        return this.typeDao.update(hotelId, typeList);
    }

    // Save types for a specific hotel ID
    public boolean save(int hotelId, List<String> typeList) {
        return this.typeDao.save(hotelId, typeList);
    }

    // Delete types for a specific hotel ID
    public boolean deleteType(int hotelId, List<String> typeNames) {
        if (hotelId == 0) {
            Helper.showMsg("ID not found"); // Show message if the hotel ID is not found
            return false;
        }
        return this.typeDao.deleteType(hotelId, typeNames);
    }
}