package view;

import business.*;
import core.Helper;
import entity.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReservationAdd extends Layout {
    private JPanel wrapper;
    private JPanel pnl_reservation;
    private JTextField fld_hotel_name;
    private JTextArea txtArea_hotel_address;
    private JTextField fld_hotel_phone;
    private JTextArea txtArea_hotel_property;
    private JTextField fld_room_type;
    private JTextArea txtArea_room_property;
    private JTextField fld_adult_numb;
    private JTextField fld_child_numb;
    private JTextField fld_check_start_date;
    private JTextField fld_check_end_date;
    private JTextField fld_Night_Price;
    private JTextField fld_reser_price;
    private JPanel pnl_top;
    private JTextField fld_client_name;
    private JTextField fld_client_phone;
    private JTextField fld_client_mail;
    private JTextArea txtArea_client_note;
    private JButton btn_add_reservation;
    private JPanel container;
    private JButton btn_reser_price;
    private JLabel lbl_season_name;
    private JLabel lbl_season_date;
    private JButton misafirBilgileriButton;
    private JComboBox cmb_AdultBedNums;
    private JComboBox cmb_ChildBedNum;
    private Room room;
    private HotelManager hotelManager;
    private Hotel hotel;
    private PropertyManager propertyManager;
    private Property property;
    private TypeManager typeManager;
    private Types types;
    private SeasonManager seasonManager;
    private Season season;
    private ReserManager reserManager;
    private Reser reser;
    private RoomManager roomManager;
    LocalDate reservationStartDate;
    LocalDate reservationEndDate;
    LocalDate seasonStartDate;
    LocalDate seasonEndDate;
    int daysBetween;
    int adultBedNum = 0;
    int childBedNum = 0;

    public ReservationAdd(Room selectroom, Property selectProperty) {
        this.hotelManager = new HotelManager();
        this.propertyManager = new PropertyManager();
        this.typeManager = new TypeManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.reserManager = new ReserManager();
        this.reser = new Reser();
        this.property = selectProperty;
        this.room = selectroom;
        add(container);
        this.guiInitialize(850, 650);

        String hotelName = this.hotelManager.getById(this.room.getHotelId()).getHotel_name();
        fld_hotel_name.setText(hotelName);
        String hotelAdres = this.hotelManager.getById(this.room.getHotelId()).getHotel_fullAddress();
        txtArea_hotel_address.setText(hotelAdres);
        String hotelPhone = this.hotelManager.getById(this.room.getHotelId()).getHotel_phone();
        fld_hotel_phone.setText(hotelPhone);
        propertyManager.getByBedNum(this.room.getId()).getRoomAdultBedNum();
        // Get the number of adults and write it to JTextField
        adultBedNum = propertyManager.getByBedNum(this.room.getId()).getRoomAdultBedNum();
        fld_adult_numb.setText(String.valueOf(adultBedNum));
        // Get the number of children and write to JTextField
        childBedNum = propertyManager.getByBedNum(this.room.getId()).getRoomChildBedNum();
        fld_child_numb.setText(String.valueOf(childBedNum));
        List<String> hotelPropertyList = property.getPropertyNames();
        //clear JTextArea content
        txtArea_hotel_property.setText("");
        // Add all properties in the list to JTextArea
        for (String property : hotelPropertyList) {
            txtArea_hotel_property.append(property + "\n"); // Add each property to a new line
        }
        //determining the capacity data for the number of adult child beds
        loadCmbBedNumsList();
        String roomProperty = this.room.getRoomType();
        fld_room_type.setText(roomProperty);
        String hotelType = typeManager.getByTypeId(this.room.getHotelTypeId());
        txtArea_room_property.setText(hotelType);
        String roomNightPrince = String.valueOf(this.room.getRoomPrice());
        fld_Night_Price.setText(roomNightPrince);
        seasonStartDate = seasonManager.getSeasonDate(this.room.getSeasonId()).getSeason_start();
        seasonEndDate = seasonManager.getSeasonDate(this.room.getSeasonId()).getSeason_end();
        String formattedStartDate = seasonStartDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String formattedEndDate = seasonEndDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        lbl_season_date.setText(formattedStartDate + " - " + formattedEndDate);
        String seasonName = seasonManager.getSeasonDate(this.room.getSeasonId()).getSeasonName();
        lbl_season_name.setText(seasonName);

        btn_reser_price.addActionListener(e -> {
            if (Helper.isValidDate(fld_check_start_date.getText(), ("dd-MM-yyyy")) && Helper.isValidDate(fld_check_end_date.getText(), ("dd-MM-yyyy"))) {
                reservationStartDate = (LocalDate.parse(fld_check_start_date.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                reservationEndDate = (LocalDate.parse(fld_check_end_date.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                daysBetween = (int) ChronoUnit.DAYS.between(reservationStartDate, reservationEndDate);
            } else {
                Helper.showMsg("Invalid date entered"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
            }
            if (!fld_check_start_date.getText().isEmpty() && !fld_check_end_date.getText().isEmpty()) {
                int comparison = reservationStartDate.compareTo(reservationEndDate);
                int comparisonSeasonStart = seasonStartDate.compareTo(reservationStartDate);
                int comparisonSeasonEnd = reservationEndDate.compareTo(seasonEndDate);
                if (comparisonSeasonStart < 0 && comparisonSeasonEnd < 0) {
                    if ((comparison < 0)) {
                        int roomPrince = this.room.getRoomPrice();
                        int adultPrice = this.room.getAdultPrice();
                        int childPrice = this.room.getChildPrice();
                        int reservationPrice = ((daysBetween * roomPrince) + (adultPrice * (int) cmb_AdultBedNums.getSelectedItem()) + (childPrice * (int) cmb_ChildBedNum.getSelectedItem()));
                        fld_reser_price.setText(String.valueOf(reservationPrice));
                        // Create formatter (Use two decimal places after comma)
                        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                        // Format the number in a specific format
                        String formattedText = decimalFormat.format(reservationPrice);
                        fld_reser_price.setText(formattedText + " TL ");
                    } else {
                        Helper.showMsg(reservationStartDate + " date cannot be later than " + reservationEndDate + " date.");
                    }
                } else {
                    Helper.showMsg(formattedStartDate + " - " + formattedEndDate + " must be between season dates.");
                }
            } else {
                Helper.showMsg("Reservation Dates cannot be Empty"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
            }
        });
        fld_check_start_date.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                fld_reser_price.setText(null);
            }
        });
        fld_check_end_date.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                fld_reser_price.setText(null);
            }
        });
        cmb_AdultBedNums.addActionListener(e -> {
            fld_reser_price.setText(null);
            GuestInfoAddView.reservations.clear();
        });
        cmb_ChildBedNum.addActionListener(e -> {
            fld_reser_price.setText(null);
            GuestInfoAddView.reservations.clear();
        });

        btn_add_reservation.addActionListener(e -> {

            List<Reser> guestInfoList = GuestInfoAddView.reservations;

            if ((guestInfoList == null) || !guestInfoList.isEmpty()) {

                if (Helper.isFieldEmpty(fld_client_name) || Helper.isFieldEmpty(fld_client_phone) ||
                        Helper.isFieldEmpty(fld_client_mail) || Helper.isAreaEmpty(txtArea_client_note) || Helper.isFieldEmpty(fld_reser_price) ||
                        cmb_AdultBedNums.getSelectedItem() == null || (Integer) cmb_AdultBedNums.getSelectedItem() == 0 ||
                        cmb_ChildBedNum.getSelectedItem() == null) {
                    Helper.showMsg("fill"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
                } else if (!Helper.isValidDate(fld_check_start_date.getText(), ("dd-MM-yyyy")) || !Helper.isValidDate(fld_check_end_date.getText(), ("dd-MM-yyyy"))) {
                    Helper.showMsg("Invalid date entered"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
                } else {
                    String fldAdultBedNum = cmb_AdultBedNums.getSelectedItem().toString();
                    String fldChildBedNum = cmb_ChildBedNum.getSelectedItem().toString();
                    this.reser.setRoom_id(this.room.getId());
                    this.reser.setReserFllName(fld_client_name.getText());
                    this.reser.setReserPhone(fld_client_phone.getText());
                    this.reser.setReserEmail(fld_client_mail.getText());
                    this.reser.setReserNote(txtArea_client_note.getText());
                    this.reser.setReserCheckInDdate(fld_check_start_date.getText());
                    this.reser.setReserCheckOutDate(fld_check_end_date.getText());
                    this.reser.setAdultNumb(fldAdultBedNum);
                    this.reser.setChildNumb(fldChildBedNum);
                    this.reser.setTotalPrice(fld_reser_price.getText());
                    int reservationId = this.reserManager.saveAndGetReserlId(this.reser);
                    if (reservationId > 0) {
                        this.reser.setId(reservationId);
                        if (this.reserManager.saveGuestInfoList(reservationId)) {
                            Helper.showMsg("done"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
                            dispose();
                            //Evaluation Form 19 The stock of the booked room is decreasing
                            if (this.roomManager.stockUpdate(this.room, -1)) {
                                Helper.showMsg("Stock Reduced"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
                            }
                        } else {
                            Helper.showMsg("error"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
                        }
                    } else {
                        Helper.showMsg("error"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
                    }
                }
            } else {
                Helper.showMsg("Guest Information Cannot Be Empty"); //Evaluation Form 24-25 Appropriate pop up messages are given to the user for successful transactions and appropriate error messages are given to the user for incorrect transactions.
            }
        });
        misafirBilgileriButton.addActionListener(e -> {
            int fldAdultBedNum = 0;
            if (cmb_AdultBedNums.getSelectedItem() != null) {
                fldAdultBedNum = (int) cmb_AdultBedNums.getSelectedItem();
            }
            // int fldAdultBedNum= (int) cmb_AdultBedNums.getSelectedItem();
            int fldChildBedNum = (int) cmb_ChildBedNum.getSelectedItem();
            GuestInfoAddView guestInfoAddView = new GuestInfoAddView(fldAdultBedNum, fldChildBedNum);
        });
    }

    public void loadCmbBedNumsList() {
        // Let's create an integer array
        Integer[] itemAdult = new Integer[adultBedNum + 1];
        for (int i = 0; i <= adultBedNum; i++) {
            itemAdult[i] = i;
        }
        DefaultComboBoxModel<Integer> cmbAdultModel = new DefaultComboBoxModel<>(itemAdult);
        cmb_AdultBedNums.setModel(cmbAdultModel);
        cmb_AdultBedNums.setSelectedIndex(0);
        // Let's create an integer array
        Integer[] itemChild = new Integer[childBedNum + 1];
        for (int i = 0; i <= childBedNum; i++) {
            itemChild[i] = i;
        }
        DefaultComboBoxModel<Integer> cmbChildModel = new DefaultComboBoxModel<>(itemChild);
        cmb_ChildBedNum.setModel(cmbChildModel);
        cmb_ChildBedNum.setSelectedIndex(0);
    }
}