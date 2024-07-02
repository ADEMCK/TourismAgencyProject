package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the layout for the application, providing methods to create and manage tables and other GUI components.
 */
public class Layout extends JFrame {

    //Creates a table within a JTextArea, displaying properties in a formatted manner.
    public void createTableStringLines(JTextArea textArea, Object[] columns, int hotelId, List<String[]> properties) {
        // Clear the content of the JTextArea
        textArea.setText("");

        // Print the table headers
        textArea.append(columns[0].toString() + "\t" + columns[1].toString() + "\n");

        // Print properties line by line
        for (String[] property : properties) {
            for (int i = 0; i < property.length; i++) {
                textArea.append(hotelId + "\t"); // Print hotel ID
                textArea.append(property[i]);
                if (i < property.length - 1) {
                    textArea.append("\n");
                }
            }
            textArea.append("\n"); // Add a new line for the next property
        }
        textArea.setEditable(false);
    }

    //Creates a table using the given model, table, columns, and rows.
    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        if (rows == null) {
            rows = new ArrayList<>();
        }
        for (Object[] row : rows) {
            model.addRow(row);
        }
    }

    //Initializes the GUI with the given width and height.
    public void guiInitialize(int width, int height) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Tourism Agency");
        setSize(width, height);
        setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        setVisible(true);
    }

    //Gets the selected row's data from the JTable.
    public static int getTableSelectedRow(JTable table, int hotelIdColumnIndex) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) { // If a row is selected
            // Get the value of the specific column in the selected row
            Object hotelIdObj = table.getValueAt(selectedRow, hotelIdColumnIndex);

            if (hotelIdObj instanceof Integer) {
                return (Integer) hotelIdObj; // Return the hotel ID as an integer
            }
        }

        return -1; // Return -1 if no row is selected or if the hotel ID cannot be obtained
    }

    //Adds a MouseListener to the table to handle row selection.
    public void tableRowSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }
}