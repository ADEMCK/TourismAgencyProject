package view;

import business.*;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HotelView extends Layout {
    private JPanel pnl_user_top;
    private JLabel lbl_emp_info;
    private JPanel pnl_hotel_list;
    private JPanel pnl_pansiyon_type;
    private JPanel pnl_season_list;
    private JTable tbl_hotel_list;
    private JTable tbl_hotel_type;
    private JTable tbl_hotel_season;
    private JScrollPane scrl_hotel_list;
    private JScrollPane scl_left_list;
    private JScrollPane scrl_right_list;
    private JPanel container;
    private JScrollPane scrl_mid_list;
    private JTextArea textArea1;
    private JPanel tab_hotel;
    private JPanel tab_room;
    private JButton btn_room_add;
    private JPanel room_sh_form;
    private JComboBox cmb_region_hotelName;
    private JTextField fld_chec_in;
    private JTextField fld_check_out;
    private JTextField fld_adult_numb;
    private JButton btn_room_sh;
    private JTable tbl_room_list;
    private JTable tbl_room_property;
    private JPanel pnl_r_top;
    private JButton cmb_room_list_clear;
    private JScrollPane scrl_room_property;
    private JPanel pnl_mid;
    private JScrollPane scrl_room_list;
    private JPanel pnl_room;
    private JPanel pnl_room_pro;
    private JComboBox cmb_city;
    private JComboBox cmb_adult_num_search;
    private JComboBox cmb_child_num_search;
    private JPanel pnl_price;
    private JTable tbl_price;
    private JTabbedPane MENU;
    private JPanel tab_reser;
    private JPanel pnl_reser_list;
    private JPanel pnl_guest_info;
    private JTable tbl_reser_list;
    private JTable tbl_guest_info;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton btn_employee_logout;
    private JPopupMenu hotel_Menu;
    private JPopupMenu room_Menu;
    private JPopupMenu reser_Menu;
    private Object[] col_hotel;
    private Object[] col_room_list;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_hotelType = new DefaultTableModel();
    private DefaultTableModel tmdl_hotelProperty = new DefaultTableModel();
    private DefaultTableModel tmdl_hotelSeason = new DefaultTableModel();
    private DefaultTableModel textArea = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reser = new DefaultTableModel();
    private DefaultTableModel tmdl_guest = new DefaultTableModel();
    private DefaultTableModel tmdl_room_properties = new DefaultTableModel();
    private Object[] row_room_properties;
    private User user;
    private Room room;
    private Property property;
    private HotelManager hotelManager;
    private PropertyManager propertyManager;
    private TypeManager typeManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private ReserManager reserManager;
    private int select_hotel_id;
    String date_check_in;
    String date_check_out;
    String hotelName = null;
    String selectedCity = null;

    int adultNum = 0;
    int childNum = 0;

    public HotelView(User user) {
        this.hotelManager = new HotelManager();
        this.propertyManager = new PropertyManager();
        this.typeManager = new TypeManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.reserManager = new ReserManager();
        this.property = new Property();
        this.fld_chec_in = new JTextField(20);
        this.fld_check_out = new JTextField(20);
        btn_employee_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window

                //Open a new LoginView window
                LoginView loginView = new LoginView();
                loginView.setVisible(true); //Make the new window visible
            }
        });

        add(container);
        this.guiInitialize(800, 650);
        this.user = user;
        if (this.user == null) {
            dispose();
        }
        this.lbl_emp_info.setText("Welcome: " + this.user.getUsername());
        loadHotelTable(null);
        loadHotelTypesTable();
        loadHotelPopertyTable();
        loadHotelSeasonTable();
        hotelMenuList();
        loadRoomTable(null);
        loadHotelNameCombo();
        loadRoomPropertiesTable();
        roomMenuList();
        loadReserTable(null);
        loadGuestTable();
        reserMenuList();

        tbl_hotel_list.addMouseListener(new MouseAdapter() {
        });
        btn_room_add.addActionListener(e -> {
            RoomAdd roomAdd = new RoomAdd(room);
        });

        btn_room_sh.addActionListener(e -> {
            date_check_in = fld_chec_in.getText().trim();
            date_check_out = fld_check_out.getText().trim();
            ArrayList<Room> roomArrayList = this.roomManager.searchForRooms(date_check_in, date_check_out, selectedCity, hotelName, adultNum, childNum);
            ArrayList<Object[]> roomList = this.roomManager.getForTable(this.col_room_list.length, roomArrayList);
            loadRoomTable(roomList);
        });
        //hotel search code end
        cmb_region_hotelName.addActionListener(e -> {
            ComboItem hotelNameComboItem = (ComboItem) cmb_region_hotelName.getSelectedItem();
            if (hotelNameComboItem != null) {
                hotelName = hotelNameComboItem.getValue();
            } else {
                hotelName = null;
            }
        });
        // Get the number of adults
        cmb_adult_num_search.addActionListener(e -> {
            String cmbAdultNumbSelectedItem = (String) cmb_adult_num_search.getSelectedItem();
            if (cmbAdultNumbSelectedItem != null) {
                adultNum = Integer.parseInt(cmbAdultNumbSelectedItem);
            }
        });
        // Get the number of children
        cmb_child_num_search.addActionListener(e -> {
            String cmbChildNumSelectedItem = (String) cmb_child_num_search.getSelectedItem();
            if (cmbChildNumSelectedItem != null) {
                childNum = Integer.parseInt(cmbChildNumSelectedItem);
            }
        });
        //Get city name
        cmb_city.addActionListener(e -> {
            Object selectedCityObject = cmb_city.getSelectedItem();
            if (selectedCityObject != null) {
                selectedCity = selectedCityObject.toString();
            }
        });
        //SEARCH CLEARING RESET TABLE
        cmb_room_list_clear.addActionListener(e -> {
            if (cmb_region_hotelName != null) {
                cmb_region_hotelName.setSelectedItem(null);
            }
            if (cmb_city != null) {
                cmb_city.setSelectedItem(null);
            }
            if (cmb_adult_num_search != null) {
                cmb_adult_num_search.setSelectedItem(null);
            }
            if (cmb_child_num_search != null) {
                cmb_child_num_search.setSelectedItem(null);
            }
            loadRoomTable(null);
            tmdl_room_properties.setRowCount(0);
            selectedCity = null;
            hotelName = null;
            date_check_in = null;
            date_check_out = null;
            adultNum = 0;
            childNum = 0;
        });
    }

    //--------------------------room tab menu
    public void loadHotelNameCombo() {
        // Remove all items from the combo box
        cmb_region_hotelName.removeAllItems();
        // Add a default item to the combo box
        cmb_region_hotelName.addItem(new ComboItem(0, null));
        cmb_city.setSelectedItem(null);

        // Fetch all hotels
        ArrayList<Hotel> hotelList = hotelManager.findAll();

        // HashSet to store unique hotel names
        HashSet<String> hotelNames = new HashSet<>();

        // Iterate through the hotel list to add names to the HashSet
        for (Hotel hotel : hotelList) {
            String hotelName = hotel.getHotel_name();

            // Add the hotel name to the HashSet if it hasn't been added before
            if (!hotelNames.contains(hotelName)) {
                hotelNames.add(hotelName);
                // Add the hotel name to the combo box
                cmb_region_hotelName.addItem(new ComboItem(hotel.getHotel_id(), hotelName));
            }
        }
    }

    public void loadRoomPropertiesTable() {
        // Highlight the selected row in the table
        this.tableRowSelect(tbl_room_property);

        // Add a MouseListener to listen for mouse clicks on tbl_room_list
        tbl_room_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the value at index 0 (room ID) of the selected row
                int selectedRoomId = getTableSelectedRow(tbl_room_list, 0);

                // Call getById method on hotelManager to fetch details of the selected room
                Object[] col_room_properties = {"Room Properties", "Adult Bed Count", "Child Bed Count", "Adult Fee", "Child Fee", "Area (m2)", "Nightly Rate"};
                ArrayList<Object[]> roomPropertiesList = propertyManager.getForTableRoomProperty(col_room_properties.length, selectedRoomId);

                // Create a table with fetched room properties data
                createTable(tmdl_room_properties, tbl_room_property, col_room_properties, roomPropertiesList);
            }
        });
    }

    public void loadGuestTable() {
        // Highlight the selected row in the table
        this.tableRowSelect(this.tbl_guest_info);

        // Add a MouseListener to listen for mouse clicks on tbl_reser_list
        tbl_reser_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the value at index 0 (reservation ID) of the selected row
                int selectedReserId = getTableSelectedRow(tbl_reser_list, 0);

                // Define column names for guest information table
                Object[] col_guest_list = {"Reservation Id", "Full Name", "Country ID Number", "Country", "Class"};

                // Fetch guest information for the selected reservation ID
                ArrayList<Object[]> guestInfoList = reserManager.getForTableGuestInfo(col_guest_list.length, selectedReserId);

                // Create a table with fetched guest information data
                createTable(tmdl_guest, tbl_guest_info, col_guest_list, guestInfoList);
            }
        });
    }

    public void loadReserTable(ArrayList<Object[]> reserlList) {
        this.tableRowSelect(this.tbl_reser_list);
        Object[] col_reservation_list = {"Reser Id", "Room Id", "Hotel Name", "Room Type", "Full Name", "Phone", "E-mail", "Note", "Check-in Date", "Check-out Date", "Adult Count", "Child Count", "Total Fee"};
        if (reserlList == null) {
            reserlList = this.reserManager.getForTable(col_reservation_list.length, reserManager.findAll());
        }
        createTable(this.tmdl_reser, this.tbl_reser_list, col_reservation_list, reserlList);
    }

    public void loadRoomTable(ArrayList<Object[]> roomlList) {
        this.tableRowSelect(this.tbl_room_list);
        col_room_list = new Object[]{"id", "Hotel Name", "City Name", "Room Type", "Stock", "Season Dates", "Adult Price", "Child Price", "Pension Type"};
        if (roomlList == null) {
            roomlList = this.roomManager.getForTable(col_room_list.length, roomManager.findAll());
        }
        createTable(this.tmdl_room, this.tbl_room_list, col_room_list, roomlList);
    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        this.tableRowSelect(tbl_hotel_list);
        this.col_hotel = new Object[]{"Hotel ID", "Hotel Name", "City", "Area", "Address", "Email", "Phone", "Star Rating"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(col_hotel.length, hotelManager.findAll());
        }
        this.createTable(this.tmdl_hotel, this.tbl_hotel_list, col_hotel, hotelList);
    }

    public void loadHotelPopertyTable() {
        //Let's mark the selected row in the table
        // Let's add MouseListener to listen for mouse clicks on tbl_hotel_list
        tbl_hotel_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the value (hotel ID) at index 0 of the selected row
                int selectedHotelId = getTableSelectedRow(tbl_hotel_list, 0);
                // Let's call the getById method on the hotelManager to get the details of the selected hotel
                Object[] columnNames = new Object[]{"Hotel ID", "Hotel Property"};
                List<String[]> hotelPropertyList = propertyManager.getPropertyList(selectedHotelId);
                createTableStringLines(textArea1, columnNames, selectedHotelId, hotelPropertyList);
            }
        });
    }

    public void loadHotelTypesTable() {
        //Let's mark the selected row in the table
        // Let's add MouseListener to listen for mouse clicks on tbl_hotel_list
        tbl_hotel_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the value (hotel ID) at index 0 of the selected row
                int selectedHotelId = getTableSelectedRow(tbl_hotel_list, 0);
                // Let's call the getById method on the hotelManager to get the details of the selected hotel
                Object[] col_hotelType = new Object[]{"Hotel ID", "Pension Type"};
                ArrayList<Object[]> hotelTypeList = typeManager.getForTable(col_hotelType.length, selectedHotelId);
                createTable(tmdl_hotelType, tbl_hotel_type, col_hotelType, hotelTypeList);
            }
        });
    }

    public void loadHotelSeasonTable() {
        //Let's mark the selected row in the table

        // Let's add MouseListener to listen for mouse clicks on tbl_hotel_list
        tbl_hotel_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the value (hotel ID) at index 0 of the selected row
                int selectedHotelId = getTableSelectedRow(tbl_hotel_list, 0);
                // Let's call the getById method on the hotelManager to get the details of the selected hotel
                Object[] col_hotelSeason = new Object[]{"Hotel ID\t", "Season Start\t", "Season End", "Season Type"};
                ArrayList<Object[]> hotelSeasonList = seasonManager.getForTable(col_hotelSeason.length, selectedHotelId);
                //ArrayList<Object[]> brandList = this.hotelManager.getForType(col_hotelType.length);
                createTable(tmdl_hotelSeason, tbl_hotel_season, col_hotelSeason, hotelSeasonList);
            }
        });
    }

    public void hotelMenuList() {
        this.hotel_Menu = new JPopupMenu();
        this.hotel_Menu.add("New").addActionListener(e -> {
            HotelAddView hotelAddView = new HotelAddView(new Hotel(), new Property(), new Types(), new Season());
            hotelAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    loadHotelTypesTable();
                    loadHotelPopertyTable();
                    loadHotelSeasonTable();
                    hotelMenuList();
                }
            });
        });

        this.hotel_Menu.add("Update").addActionListener(e -> {
            int selectHotelId = getTableSelectedRow(tbl_hotel_list, 0);
            HotelUpdateView hotelUpdateView = new HotelUpdateView
                    (this.hotelManager.getById(selectHotelId),
                            this.propertyManager.getById(selectHotelId),
                            this.typeManager.getById(selectHotelId),
                            this.seasonManager.getById(selectHotelId));
            hotelUpdateView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    loadHotelTypesTable();
                    loadHotelPopertyTable();
                    loadHotelSeasonTable();
                    hotelMenuList();
                }
            });
        });
        this.hotel_Menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = getTableSelectedRow(tbl_hotel_list, 0);
                if (this.hotelManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadHotelTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_hotel_list.setComponentPopupMenu(this.hotel_Menu);
    }

    public void roomMenuList() {
        this.room_Menu = new JPopupMenu();
        this.room_Menu.add("Reservation").addActionListener(e -> {
            int selectRoomId = getTableSelectedRow(tbl_room_list, 0);
            this.room = roomManager.getById(selectRoomId);
            if (this.room.getStock() > 0) {
                ReservationAdd reservationAdd = new ReservationAdd(this.roomManager.getById(selectRoomId), this.propertyManager.getById(this.roomManager.getById(selectRoomId).getHotelId()));
                reservationAdd.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadReserTable(null);
                    }
                });
            } else {
                Helper.showMsg("Room stock is 0, reservation cannot be made");
            }
        });

        this.room_Menu.add("New").addActionListener(e -> {
            RoomAdd roomAdd = new RoomAdd(new Room());
            roomAdd.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                    loadRoomPropertiesTable();
                }
            });
        });
        this.room_Menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = getTableSelectedRow(tbl_room_list, 0);
                if (this.roomManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadRoomTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.room_Menu.add("Update").addActionListener(e -> {
            loadRoomTable(null);
        });
        this.tbl_room_list.setComponentPopupMenu(this.room_Menu);
    }
    public void reserMenuList() {
        this.reser_Menu = new JPopupMenu();
        this.reser_Menu.add("Update").addActionListener(e -> {
            int selectReserId = getTableSelectedRow(tbl_reser_list, 0);
            RevervationUpdateView revervationUpdateView = new RevervationUpdateView(this.reserManager.getById(selectReserId));
            revervationUpdateView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReserTable(null);
                }
            });
        });
        this.reser_Menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = getTableSelectedRow(tbl_reser_list, 0);
                int selectRoomdId = getTableSelectedRow(tbl_reser_list, 1);
                if (this.reserManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadReserTable(null);

                    this.room=this.roomManager.getById(selectRoomdId);
                    if(this.roomManager.stockUpdate(this.room,+1)){
                        Helper.showMsg("Stock Increased");
                    }
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_reser_list.setComponentPopupMenu(this.reser_Menu);
    }
}