package view;

import business.*;
import core.ComboItem;
import entity.*;
import core.Helper;
import entity.Hotel;

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomAdd extends Layout {
    private JPanel container;
    private JPanel wrapper;
    private JPanel pnl_room_add_top;
    private JPanel pnl_room_add_left;
    private JComboBox cmb_room_hotelname;
    private JComboBox cmb_room_type;
    private JComboBox cmb_room_pansiyon_type;
    private JComboBox cmb_season;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JPanel pnl_room_add_right;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JButton btn_room_add;
    private JComboBox<Integer> cmb_adult_num;
    private JComboBox<Integer> cmb_child_num;
    private JLabel lbl_total_room_pricer;
    private JComboBox<Integer> cmb_room_stock;
    private JComboBox<Integer> cmb_room_area;
    private HotelManager hotelManager;
    private PropertyManager propertyManager;
    private TypeManager typeManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private Room room;
    private Property property;
    private int cmbSeason_id = 0;
    private int hotel_type_id = 0;
    int roomStockNum = 0;
    int adultNum = 0;
    int childNum = 0;
    List<String> listRightType;

    // HotelManegementGUI constructor method
    public RoomAdd(Room room) {
        this.hotelManager = new HotelManager();
        this.propertyManager = new PropertyManager();
        this.typeManager = new TypeManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.property = new Property();
        this.room = new Room();

        add(container);
        this.guiInitialize(760, 750);
        Integer[] cmbAdult = {0, 1, 2, 3};
        // Create DefaultComboBoxModel and add integers to the model
        DefaultComboBoxModel<Integer> cmbAdultModel = new DefaultComboBoxModel<>(cmbAdult);
        cmb_adult_num.setModel(cmbAdultModel);
        cmb_adult_num.setSelectedIndex(0);

        Integer[] cmbChild = {0, 1, 2, 3,};
        // Create DefaultComboBoxModel and add integers to the model
        DefaultComboBoxModel<Integer> cmbChildModel = new DefaultComboBoxModel<>(cmbChild);
        cmb_child_num.setModel(cmbChildModel);
        cmb_child_num.setSelectedIndex(0);

        Integer[] cmbRoomArea = {0, 50, 100, 150, 200};
        // Create DefaultComboBoxModel and add integers to the model
        DefaultComboBoxModel<Integer> cmbAreaModel = new DefaultComboBoxModel<>(cmbRoomArea);
        cmb_room_area.setModel(cmbAreaModel);
        cmb_room_area.setSelectedIndex(0);

        Integer[] integers = {0, 1, 2, 3, 4, 5};
        // Create DefaultComboBoxModel and add integers to the model
        DefaultComboBoxModel<Integer> cmbStockModel = new DefaultComboBoxModel<>(integers);
        cmb_room_stock.setModel(cmbStockModel);
        cmb_room_stock.setSelectedIndex(0);
        radioButton1.setText(Helper.roomProperty("1"));
        radioButton2.setText(Helper.roomProperty("2"));
        radioButton3.setText(Helper.roomProperty("3"));
        radioButton4.setText(Helper.roomProperty("4"));
        radioButton5.setText(Helper.roomProperty("5"));
        loadHotelNameCombo();

        cmb_room_hotelname.addActionListener(event -> {
            loadHotelTypesCombo();
            loadSeasonCombo();
            cmb_room_type.setSelectedIndex(0);
            cmb_season.setSelectedIndex(0);
        });

        btn_room_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_adult_price) || Helper.isFieldEmpty(fld_child_price) ||
                    cmb_room_type.getSelectedItem().toString().equals("") || cmb_room_pansiyon_type.getSelectedItem() == null ||
                    cmb_season.getSelectedItem() == null || cmb_room_hotelname.getSelectedItem() == null ||
                    cmb_adult_num.getSelectedItem() == null || (Integer) cmb_adult_num.getSelectedItem() == 0 ||
                    cmb_room_stock.getSelectedItem() == null || (Integer) cmb_room_stock.getSelectedItem() == 0 ||
                    cmb_room_area.getSelectedItem() == null || (Integer) cmb_room_area.getSelectedItem() == 0 ||
                    (!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() &&
                            !radioButton4.isSelected() && !radioButton5.isSelected())) {
                Helper.showMsg("fill");
            } else {
                String room_type = cmb_room_type.getSelectedItem().toString();

                int adult_price = Integer.parseInt(fld_adult_price.getText().toString());
                int child_price = Integer.parseInt(fld_child_price.getText().toString());

                ComboItem hotelComboItem = (ComboItem) cmb_room_hotelname.getSelectedItem();
                int hotel_id = hotelComboItem.getKey();

                ComboItem hotelTypeComboItem = (ComboItem) cmb_room_pansiyon_type.getSelectedItem();
                hotel_type_id = hotelTypeComboItem.getKey();

                ComboItem seasonComboItem = (ComboItem) cmb_season.getSelectedItem();
                cmbSeason_id = seasonComboItem.getKey();

                this.room.setRoomType(room_type);
                this.room.setStock(cmb_room_stock.getSelectedIndex());
                this.room.setSeasonId(cmbSeason_id);
                this.room.setAdultPrice(adult_price);
                this.room.setChildPrice(child_price);
                this.room.setHotelTypeId(hotel_type_id);
                this.room.setHotelId(hotel_id);
                this.room.setRoomPrice(roomPriceCalcul());

                int roomId = this.roomManager.save(this.room);

                String room_properties = "";
                for (int i = 1; i <= 7; i++) { //adding room property
                    switch (i) {
                        case 1:
                            if (radioButton1.isSelected()) {
                                room_properties += "\n" + radioButton1.getText();
                            }
                            break;
                        case 2:
                            if (radioButton2.isSelected()) {
                                room_properties += "\n" + radioButton2.getText();
                            }
                            break;
                        case 3:
                            if (radioButton3.isSelected()) {
                                room_properties += "\n" + radioButton3.getText();
                            }
                            break;
                        case 4:
                            if (radioButton4.isSelected()) {
                                room_properties += "\n" + radioButton4.getText();
                            }
                            break;
                        case 5:
                            if (radioButton5.isSelected()) {
                                room_properties += "\n" + radioButton5.getText();
                            }
                            break;
                    }
                }
                //ComboItem selectedBrand = (ComboItem) cmb_season.getSelectedItem();
                this.property.setRoomProperty(room_properties);
                this.property.setRoomId(roomId);
                this.property.setRoomAdultBedNum((Integer) cmb_adult_num.getSelectedItem());
                this.property.setRoomChildBedNum((Integer) cmb_child_num.getSelectedItem());
                this.property.setRoomArea((Integer) (cmb_room_area.getSelectedItem()));
                if (this.propertyManager.saveRoomProperty(this.property)) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
// nightly fee price update events
        cmb_room_type.addActionListener(e -> roomPriceCalcul());
        cmb_room_pansiyon_type.addActionListener(e -> roomPriceCalcul());
        cmb_adult_num.addActionListener(e -> roomPriceCalcul());
        cmb_child_num.addActionListener(e -> roomPriceCalcul());
        cmb_room_area.addActionListener(e -> roomPriceCalcul());

        cmb_child_num.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Get the selected item as a String
                    Integer selectedInt = (Integer) e.getItem();
                    // Now you can use the parsed integer value
                    if (selectedInt == 0) {
                        fld_child_price.setText(null);
                        fld_child_price.setEditable(false);
                    } else {
                        fld_child_price.setText(null);
                        fld_child_price.setEditable(true);
                    }
                }
            }
        });

        cmb_adult_num.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Get the selected item as a String
                    Integer selectedInt = (Integer) e.getItem();
                    // Now you can use the parsed integer value
                    if (selectedInt == 0) {
                        fld_adult_price.setText(null);
                        fld_adult_price.setEditable(false);
                    } else {
                        fld_adult_price.setText(null);
                        fld_adult_price.setEditable(true);
                    }
                }
            }
        });
    }

    //Evaluation Form 17 The price of the accommodation is successfully calculated according to the guest information, the number of nights to stay and the room price.
    public int roomPriceCalcul() {
        //Creating a Map that will keep service types and prices
        int pansiyonTypePrice = 0;
        int roomTypePrice = 0;
        int adultPrice = 0;
        int childPrice = 0;
        int roomAreaPrice = 0;  // Set default value to 0 (for error condition)
        int totalPrice = 0;

        Map<String, Integer> servicePrices = new HashMap<>();
        servicePrices.put("Ultra All Inclusive", 500);
        servicePrices.put("All Inclusive", 400);
        servicePrices.put("Bed and Breakfast", 300);
        servicePrices.put("Full Pension", 600);
        servicePrices.put("Half Pension", 450);
        servicePrices.put("Room Only", 200);
        servicePrices.put("Alcohol Excluded", 350);

        Object cmbRoomPansiyonTypeSelectedItem = cmb_room_pansiyon_type.getSelectedItem();
        if (cmbRoomPansiyonTypeSelectedItem != null) {
            String selectedHotelPansiyonName = cmbRoomPansiyonTypeSelectedItem.toString();
            // Check the price of the selected hotel and hostel type on the map
            if (servicePrices.containsKey(selectedHotelPansiyonName)) {
                pansiyonTypePrice = servicePrices.get(selectedHotelPansiyonName);
                System.out.println(selectedHotelPansiyonName + " price for: " + pansiyonTypePrice);
            } else {
                System.out.println(selectedHotelPansiyonName + " no price information found for .");
            }
        }

        //Creating a map that will keep room types and prices
        Map<String, Integer> roomPrices = new HashMap<>();
        //Create the map containing room types and prices
        roomPrices = new HashMap<>();
        roomPrices.put("Single Room", 200); // Price for Single Room: 200 TL
        roomPrices.put("Double Room", 300); // Price for Double Room: 300 TL
        roomPrices.put("Junior Suite Room", 400); // Price for Junior Suite Room: 400 TL
        roomPrices.put("Suite Room", 500); // Price for Suite Room: 500 TL

        Object cmbRoomTypeSelectedItem = cmb_room_type.getSelectedItem();
        if (cmbRoomTypeSelectedItem != null) {
            String selectedType = cmbRoomTypeSelectedItem.toString();
            // Check the price of the selected room type on the map
            if (roomPrices.containsKey(selectedType)) {
                //Find the price based on the selected room type
                roomTypePrice = roomPrices.get(selectedType);
                System.out.println(selectedType + " price for: " + roomTypePrice);
            }
        }
        //Creating a map that will keep prices and numbers of rooms and beds
        Map<Integer, Integer> adultBedPricer = new HashMap<>();
        //Create the map containing room types and prices
        adultBedPricer = new HashMap<>();
        adultBedPricer.put(1, 200);
        adultBedPricer.put(2, 400);
        adultBedPricer.put(3, 600);

        Object cmbAdultBedSelectedItem = cmb_adult_num.getSelectedItem();
        if (cmbAdultBedSelectedItem != null) {
            int selectedType = (int) cmbAdultBedSelectedItem;
            // Check selected bed price on map
            if (adultBedPricer.containsKey(selectedType)) {
                //Find the price based on the selected room type
                adultPrice = adultBedPricer.get(selectedType);
                System.out.println(selectedType + " price for: " + adultPrice);
            }
        }
        //Creating a map that will keep prices and numbers of rooms and beds
        Map<Integer, Integer> childBedPricer = new HashMap<>();
        //Create the map containing room types and prices
        childBedPricer = new HashMap<>();
        childBedPricer.put(1, 100); // price for: 100 TL
        childBedPricer.put(2, 200); // price for: 200 TL
        childBedPricer.put(3, 300); // price for: 300 TL

        Object cmbChildBedSelectedItem = cmb_child_num.getSelectedItem();
        if (cmbChildBedSelectedItem != null) {
            int selectedType = (int) cmbChildBedSelectedItem;
            // Check selected bed price on map
            if (childBedPricer.containsKey(selectedType)) {
                //Find the price based on the selected room type
                childPrice = childBedPricer.get(selectedType);
                System.out.println(selectedType + " price for: " + childPrice);
            }
        }
        Map<Integer, Integer> roomArea = new HashMap<>();
        //Create the map containing room types and prices
        roomArea = new HashMap<>();
        roomArea.put(50, 50); // price for: 100 TL
        roomArea.put(100, 100); // price for: 200 TL
        roomArea.put(150, 150); // price for: 300 TL
        roomArea.put(200, 200);

        Object cmbRoomAreaSelectedItem = cmb_room_area.getSelectedItem();
        if (cmbRoomAreaSelectedItem != null) {
            int selectedType = (int) cmbRoomAreaSelectedItem;
            // Check selected bed price on map
            if (roomArea.containsKey(selectedType)) {
                //Find the price based on the selected room type
                roomAreaPrice = roomArea.get(selectedType);
                System.out.println(selectedType + " price for: " + roomAreaPrice);
            }
        }

        totalPrice = roomTypePrice + pansiyonTypePrice + adultPrice + childPrice + roomAreaPrice;

        // Create formatter (Use two decimal places after comma)
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        // Format the number in a specific format
        String formattedText = decimalFormat.format(totalPrice);
        lbl_total_room_pricer.setText(formattedText + " TL ");
        return totalPrice;
    }

    //Method that transfers hotel names to the combo box
    public void loadHotelNameCombo() {
        cmb_room_hotelname.removeAllItems();
        cmb_room_hotelname.addItem(new ComboItem(0, null));

        ArrayList<Hotel> hotelList = hotelManager.findAll(); // Get all hotels
        for (Hotel hotel : hotelList) {
            cmb_room_hotelname.addItem(new ComboItem(hotel.getHotel_id(), hotel.getHotel_name() + " - " + hotel.getHotel_city()));
        }
    }

    private void loadHotelTypesCombo() {
        ComboItem hotelComboItem = (ComboItem) cmb_room_hotelname.getSelectedItem();
        cmb_room_pansiyon_type.removeAllItems();
        cmb_room_pansiyon_type.addItem(new ComboItem(0, null));

        if (hotelComboItem != null && hotelComboItem.getKey() != 0) {
            for (Types types : typeManager.findAll(hotelComboItem.getKey())) {
                cmb_room_pansiyon_type.addItem(new ComboItem(types.getTypeId(), types.getTypeName()));
            }
        } else {
            cmb_room_pansiyon_type.addItem(new ComboItem(0, null));
        }
    }

    private void loadSeasonCombo() {
        ComboItem hotelComboItem = (ComboItem) cmb_room_hotelname.getSelectedItem();
        cmb_season.removeAllItems();
        cmb_season.addItem(new ComboItem(0, null));

        if (hotelComboItem != null && hotelComboItem.getKey() != 0) {
            for (Season obj : seasonManager.findAll(hotelComboItem.getKey())) {
                cmb_season.addItem(new ComboItem(obj.getSeasonId(), (obj.getSeason_start() + "  -  " + obj.getSeason_end()) + "  -  " + obj.getSeasonName()));
            }
        } else {
            cmb_season.addItem(new ComboItem(0, null));
        }
    }
}