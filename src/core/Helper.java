package core;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    // Method to set the UI theme to Nimbus
    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    // Method to show popup messages based on different cases
    public static void showMsg(String str) {
        String msg;
        String title;
        switch (str) {
            case "fill" -> {
                msg = "Please fill in all fields!";
                title = "Error!";
            }
            case "done" -> {
                msg = "Operation Successful!";
                title = "Result";
            }
            case "notFound" -> {
                msg = "Record not found!";
                title = "Not Found";
            }
            case "error" -> {
                msg = "Invalid operation!";
                title = "Error!";
            }
            default -> {
                msg = str;
                title = "Message";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to confirm actions with a message
    public static boolean confirm(String str) {
        String msg;
        if (str.equals("sure")) {
            msg = "Are you sure you want to perform this action?";
        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Are you sure?", JOptionPane.YES_NO_OPTION) == 0;
    }

    // Method to return room property text based on a given number
    public static String roomProperty(String number) {
        String property = "";
        switch (number) {
            case "1":
                property = "Television";
                break;
            case "2":
                property = "Minibar";
                break;
            case "3":
                property = "Game Console";
                break;
            case "4":
                property = "Safe";
                break;
            case "5":
                property = "Projector";
                break;
        }
        return property;
    }

    // Method to check if a date string is valid based on the format pattern
    public static boolean isValidDate(String inputDate, String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        try {
            LocalDate date = LocalDate.parse(inputDate.trim(), formatter);
            return true; // Valid date format
        } catch (DateTimeParseException e) {
            return false; // Invalid date format
        }
    }

    // Method to parse a date string into LocalDate based on the format pattern
    public static LocalDate parseDate(String inputDate, String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        try {
            LocalDate date = LocalDate.parse(inputDate, formatter);
            return date; // Successful conversion
        } catch (DateTimeParseException e) {
            return null; // Conversion failed
        }
    }

    // Method to convert JList items to a String list
    public static List<String> getListFromJList(JList<String> jList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jList.getModel().getSize(); i++) {
            list.add(jList.getModel().getElementAt(i));
        }
        return list;
    }

    // Method to convert an array of objects to a list of strings
    public static List<String> convertObjectListToStringList(Object[] objectList) {
        List<String> stringList = new ArrayList<>();
        for (Object object : objectList) {
            // Convert each object to string and add to list
            String str = object.toString();
            stringList.add(str);
        }
        return stringList;
    }

    // Method to check if a JList is empty
    public static boolean isList_J_Empty(JList<?> list) {
        ListModel<?> model = list.getModel();
        return model.getSize() == 0;
    }

    // Method to check if an array of JTextFields has empty fields
    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }

    // Method to check if a JTextField is empty
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    // Method to check if a JTextArea is empty
    public static boolean isAreaEmpty(JTextArea field) {
        return field.getText().trim().isEmpty();
    }

    // Method to get the location point based on type and screen size
    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }
}
