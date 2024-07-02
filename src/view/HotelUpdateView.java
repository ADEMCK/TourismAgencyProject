package view;

import business.HotelManager;
import business.PropertyManager;
import business.SeasonManager;
import business.TypeManager;
import core.Helper;
import entity.Hotel;
import entity.Property;
import entity.Season;
import entity.Types;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HotelUpdateView extends Layout {
    private JPanel container;
    private JList<String> lst_left_pro;
    private JList<String> lst_right_pro;
    private JList<String> lst_left_type;
    private JList<String> lst_right_type;
    private JButton btn_single_right;
    private JButton btn_multi_right;
    private JButton btn_multi_left;
    private JButton btn_single_left;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_district;
    private JTextField fld_hotel_fllAdres;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_phone;
    private JComboBox<Integer> cmb_hotel_star;
    private JButton btn_hotel_save;
    private JButton btn_single_right_type;
    private JButton btn_multi_right_type;
    private JButton btn_multi_left_type;
    private JRadioButton rd_btn_sum;
    private JRadioButton rd_btn_win;
    private JFormattedTextField fld_season_start;
    private JFormattedTextField fld_season_end;
    private JPanel pnl_hotel;
    private JPanel pntl_top;
    private JPanel pnl_property;
    private JPanel pnl_type;
    private JPanel pnl_season;
    private DefaultListModel<String> leftListPropertyModel;
    private DefaultListModel<String> rightListPropertyModel;
    private DefaultListModel<String> leftListTypeModel;
    private DefaultListModel<String> rightListTypeModel;
    private HotelManager hotelManager;
    private Hotel hotel;
    private PropertyManager propertyManager;
    private Property property;
    private TypeManager typeManager;
    private Types types;
    private SeasonManager seasonManager;
    private Season season;

    List<String> listRightType;

    // Constructor for HotelUpdateView
    public HotelUpdateView(Hotel hotel, Property property, Types types, Season season) {
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.propertyManager = new PropertyManager();
        this.property = property;
        this.typeManager = new TypeManager();
        this.types = types;
        this.seasonManager = new SeasonManager();
        this.season = season != null ? season : new Season();
        add(container);
        this.guiInitialize(700, 650);

        Integer[] integers = {1, 2, 3, 4, 5};
        // Create a DefaultComboBoxModel and add integers to the model
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>(integers);
        cmb_hotel_star.setModel(model);

        //Initialize GUI components
        hotelPropertyListComponent();
        hotelTypeListComponent();
        hotelSetHotelInfo();

        // Add ActionListener to save button
        btn_hotel_save.addActionListener(e -> {
            boolean isHotel = false;
            boolean isProperty = false;
            boolean isType = false;
            boolean isSeason = false;

            // Get selected property names
            List<String> propertyNames = Helper.getListFromJList(lst_right_pro);

            JTextField[] checkFieldList = {
                    this.fld_hotel_name,
                    this.fld_hotel_city,
                    this.fld_hotel_district,
                    this.fld_hotel_fllAdres,
                    this.fld_hotel_mail,
                    this.fld_hotel_phone,
                    this.fld_season_start,
                    this.fld_season_end
            };
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("Hotel Information Incomplete"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
            } else {
                // Update hotel information
                this.hotel.setHotel_name(fld_hotel_name.getText());
                this.hotel.setHotel_city(fld_hotel_city.getText());
                this.hotel.setHotel_district(fld_hotel_district.getText());
                this.hotel.setHotel_fullAddress(fld_hotel_fllAdres.getText());
                this.hotel.setHotel_email(fld_hotel_mail.getText());
                this.hotel.setHotel_phone(fld_hotel_phone.getText());
                this.hotel.setHotel_star((Integer) cmb_hotel_star.getSelectedItem());

                // Set season name based on radio button selection
                if (rd_btn_sum.isSelected()) {
                    this.season.setSeasonName("Summer");
                } else if (rd_btn_win.isSelected()) {
                    this.season.setSeasonName("Winter");
                }
                // Update season dates if valid
                if (this.hotel.getHotel_id() != 0) {

                    if (Helper.isValidDate(fld_season_start.getText(), "dd-MM-yyyy") && Helper.isValidDate(fld_season_end.getText(),"dd-MM-yyyy")) {
                        this.season.setSeason_start(LocalDate.parse(fld_season_start.getText().trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        this.season.setSeason_end(LocalDate.parse(fld_season_end.getText().trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        isSeason = this.seasonManager.update(this.season);
                    } else {
                        Helper.showMsg("Invalid date entered"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
                    }
                    // Update hotel, properties, and types
                    isHotel = this.hotelManager.update(this.hotel);
                    this.property.setPropertyNames(propertyNames);
                    isProperty = (this.propertyManager.update(this.property));

                    List<String> listRightType = Helper.getListFromJList(lst_right_type);
                    isType = this.typeManager.update(this.hotel.getHotel_id(), listRightType);
                    // Show success or failure messages for each update
                    if (isHotel) {
                        Helper.showMsg("Hotel information updated successfully");
                    } else {
                        Helper.showMsg("Failed to update hotel information");
                    }
                    if (isProperty) {
                        Helper.showMsg("Property updated successfully");
                    } else {
                        Helper.showMsg("Failed to update property");
                    }
                    if (isType) {
                        Helper.showMsg("Type updated successfully");
                    } else {
                        Helper.showMsg("Failed to update type");
                    }
                    if (isSeason) {
                        Helper.showMsg("Season updated successfully");
                    } else {
                        Helper.showMsg("Failed to update season");
                    }
                }
            }
        });
// ActionListener for Summer radio button
        rd_btn_sum.addActionListener(e -> {
            showSeasonDates("Summer");
        });
        // ActionListener for Winter radio button
        rd_btn_win.addActionListener(e -> {
            showSeasonDates("Winter");
        });
    }
    // Method to display dates based on season type
    private void showSeasonDates(String seasonType) {
        if (season != null) {
            List<Season> seasons = seasonManager.getBySeasonList(season.getHotel_id());
            if (seasons != null && !seasons.isEmpty()) {
                for (Season seasonList : seasons) {
                    if (seasonType.equals(seasonList.getSeasonName())) {
                        // Display season dates based on the selected season type
                        this.fld_season_start.setText(seasonList.getSeason_start().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        this.fld_season_end.setText(seasonList.getSeason_end().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        break; // Exit loop when season is found
                    }
                }
            }
        }
    }
    // Method to set hotel information in GUI
    public void hotelSetHotelInfo() {
        rightListTypeModel = new DefaultListModel<>();
        rightListPropertyModel = new DefaultListModel<>();
        this.fld_hotel_name.setText(this.hotel.getHotel_name());
        this.fld_hotel_city.setText(this.hotel.getHotel_city());
        this.fld_hotel_district.setText(this.hotel.getHotel_district());
        this.fld_hotel_fllAdres.setText(this.hotel.getHotel_fullAddress());
        this.fld_hotel_mail.setText(this.hotel.getHotel_email());
        this.fld_hotel_phone.setText(this.hotel.getHotel_phone());
        this.cmb_hotel_star.setSelectedItem(this.hotel.getHotel_star());
        this.rd_btn_sum.setSelected(true);
        this.fld_season_start.setText(this.season != null && this.season.getSeason_start() != null ? this.season.getSeason_start().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : " ");
        this.fld_season_end.setText(this.season != null && this.season.getSeason_end() != null ? this.season.getSeason_end().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : " ");

        // Set property names in right property list
        List<String> hotelPropertyList = property.getPropertyNames();
        for (String property : hotelPropertyList) {
            rightListPropertyModel.addElement(property); // Add each feature to the model
        }
        lst_right_pro.setModel(rightListPropertyModel);
        // Set types in right type list

        if (types != null) {
            ArrayList<Object[]> hotelTypesList = typeManager.getForTable(2, types.getHotelId());
            // Add data from ArrayList to the model
            for (Object[] rowObject : hotelTypesList) {
                rightListTypeModel.addElement((String) rowObject[1]); // Only type_name is added
            }
            lst_right_type.setModel(rightListTypeModel);
        }
    }

    public void hotelPropertyListComponent() {
        leftListPropertyModel = new DefaultListModel<>();
        String[] propertyArray = {
                "Free Parking",
                "Free WiFi",
                "Swimming Pool",
                "Fitness Centre",
                "Hotel Concierge",
                "SPA",
                "24/7 Room Service"
        };

        // Add property options to left property list model
        for (int i = 0; i < propertyArray.length; i++) {
            leftListPropertyModel.addElement(propertyArray[i]);
        }
        lst_left_pro.setModel(leftListPropertyModel);
        // ActionListener for single right button (property)
        btn_single_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getLeftListValue = lst_left_pro.getSelectedValue();
                int value = lst_left_pro.getSelectedIndex();
                // getLeftListValue != null
                if (value >= 0 && !rightListPropertyModel.contains(getLeftListValue)) {
                    rightListPropertyModel.addElement(getLeftListValue);
                    lst_right_pro.setModel(rightListPropertyModel);
                } else if (getLeftListValue == null) {
                    Helper.showMsg("Please select a property"); // Evaluation Form 24-25: Appropriate pop-up messages are given to the user for successful operations and appropriate error messages for incorrect operations.
                } else {
                    Helper.showMsg("Already in the list"); // Evaluation Form 24-25: Appropriate pop-up messages are given to the user for successful operations and appropriate error messages for incorrect operations.
                }
            }
        });

        // ActionListener for multiple right button (property)
        btn_multi_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if both lists have the same size
                if (leftListPropertyModel.getSize() == rightListPropertyModel.getSize()) {
                    Helper.showMsg("All items are already in the list"); // Evaluation Form 24-25 Appropriate pop-up messages are given to the user for successful operations and appropriate error messages are given to the user for incorrect operations
                } else {
                    // Get all items from the left list
                    List<String> allItems = new ArrayList<>();
                    for (int i = 0; i < leftListPropertyModel.getSize(); i++) {
                        allItems.add(leftListPropertyModel.getElementAt(i));
                    }
                    // Get existing items from the right list into a List
                    List<String> existingItems = new ArrayList<>();
                    for (int i = 0; i < rightListPropertyModel.getSize(); i++) {
                        existingItems.add(rightListPropertyModel.getElementAt(i));
                    }
                    // Add all items from the left list to the right list (only those not already in the right list)
                    for (String item : allItems) {
                        if (!existingItems.contains(item)) {
                            rightListPropertyModel.addElement(item);
                            lst_right_pro.setModel(rightListPropertyModel);
                        }
                    }
                }
            }
        });
        btn_multi_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected items
                Object[] selectedValues = lst_right_pro.getSelectedValues();

                if (selectedValues != null && selectedValues.length > 0) {
                    // Check if the number of selected items is less than the size of the right list
                    if (selectedValues.length < rightListPropertyModel.getSize()) {
                        // Remove selected items from the list
                        for (Object selectedValue : selectedValues) {
                            rightListPropertyModel.removeElement(selectedValue);
                        }
                    } else {
                        Helper.showMsg("You cannot delete all properties. There must be at least one property."); // Evaluation Form 24-25 Appropriate pop-up messages are given to the user for successful operations and appropriate error messages are given to the user for incorrect operations
                    }
                } else {
                    Helper.showMsg("Select a property"); // Evaluation Form 24-25 Appropriate pop-up messages are given to the user for successful operations and appropriate error messages are given to the user for incorrect operations
                }
            }
        });
    }

    // Method to populate the hotel type list component
    public void hotelTypeListComponent() {

        leftListTypeModel = new DefaultListModel<>();

        String[] typeArray = {
                "Ultra All Inclusive",
                "All Inclusive",
                "Bed and Breakfast",
                "Full Pension",
                "Half Pension",
                "Room Only",
                "Alcohol Excluded"
        };

        for (int i = 0; i < typeArray.length; i++) {
            leftListTypeModel.addElement(typeArray[i]);
        }
        lst_left_type.setModel(leftListTypeModel);

        btn_single_right_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getLeftListValue = lst_left_type.getSelectedValue();
                int value = lst_left_type.getSelectedIndex();
                // getLeftListValue != null
                if (value >= 0 && !rightListTypeModel.contains(getLeftListValue)) {
                    rightListTypeModel.addElement(getLeftListValue);
                    lst_right_type.setModel(rightListTypeModel);
                } else if (getLeftListValue == null) {
                    Helper.showMsg("Select a Type"); // Form 24-25: Appropriate pop-up messages are given to the user for successful operations, and appropriate error messages are given to the user for erroneous operations.
                } else {
                    Helper.showMsg("Already in List"); // Form 24-25: Appropriate pop-up messages are given to the user for successful operations, and appropriate error messages are given to the user for erroneous operations.
                }
            }
        });

        btn_multi_right_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftListTypeModel.getSize() == rightListTypeModel.getSize()) {
                    Helper.showMsg("All items are already in the list"); // Form 24-25: Appropriate pop-up messages are given to the user for successful operations, and appropriate error messages are given to the user for erroneous operations.
                } else {
                    // Get all items from the left list
                    List<String> allItems = new ArrayList<>();
                    for (int i = 0; i < leftListTypeModel.getSize(); i++) {
                        allItems.add(leftListTypeModel.getElementAt(i));
                    }
                    // Get existing items in the right list into a List
                    List<String> existingItems = new ArrayList<>();
                    for (int i = 0; i < rightListTypeModel.getSize(); i++) {
                        existingItems.add(rightListTypeModel.getElementAt(i));
                    }
                    // Add all items from the left list to the right list (those not already in the right list)
                    for (String item : allItems) {
                        if (!existingItems.contains(item)) {
                            rightListTypeModel.addElement(item);
                            lst_right_type.setModel(rightListTypeModel);
                        }
                    }
                }
            }
        });
        btn_multi_left_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected items
                Object[] selectedValues = lst_right_type.getSelectedValues();

                if (selectedValues != null && selectedValues.length > 0) {
                    // Check if the number of selected items is less than the size of the list
                    if (selectedValues.length < rightListTypeModel.getSize()) {
                        // Remove the selected items from the list
                        for (Object selectedValue : selectedValues) {
                            rightListTypeModel.removeElement(selectedValue);
                        }
                        deletTypelistMetot(selectedValues);
                    } else {
                        Helper.showMsg("You cannot delete all properties. There must be at least one property."); // Evaluation Form 24-25 Appropriate pop-up messages are given to the user for successful operations and appropriate error messages are given to the user for incorrect operations
                    }
                } else {
                    Helper.showMsg("Select a Type"); // Evaluation Form 24-25 Appropriate pop-up messages are given to the user for successful operations and appropriate error messages are given to the user for incorrect operations
                }
            }
        });
    }

    public void deletTypelistMetot(Object[] selectedValues) {

        listRightType = Helper.convertObjectListToStringList(selectedValues);
        if (this.typeManager.deleteType(hotel.getHotel_id(), listRightType)) {
            Helper.showMsg("done"); // Evaluation Form 24-25 Appropriate pop-up messages are given to the user for successful operations and appropriate error messages are given to the user for incorrect operations
        } else {
            Helper.showMsg("error"); // Evaluation Form 24-25 Appropriate pop-up messages are given to the user for successful operations and appropriate error messages are given to the user for incorrect operations
        }
    }
}