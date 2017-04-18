package old.swing.staff;

import com.genesis.timetablegenerator.swing.Frame;
import old.swing.staff.editor.StaffEditor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Asif Ali
 */

public class StaffsTab extends JPanel {

    public static final String[] COLUMN_NAMES = new String[]{
            "Id",
            "Name",
            "Subject 1"
    };
    private DefaultTableModel tableModel;

    public StaffsTab(final Frame frame) {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        final JTable table = new JTable(tableModel);
        table.setRowSorter(new TableRowSorter<>(tableModel));
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() < 2) {
                    return;
                }

                new StaffEditor(frame, StaffsTab.this, table.convertRowIndexToModel(table.rowAtPoint(e.getPoint())));
            }

        });
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        add(buttonsPanel, BorderLayout.SOUTH);

        buttonsPanel.add(Box.createGlue());

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new StaffEditor(frame, StaffsTab.this);
            }

        });
        buttonsPanel.add(addButton);

        buttonsPanel.add(Box.createGlue());

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                if (selectedRows.length != 1) {
                    String message = null;
                    if (selectedRows.length == 0) {
                        message = "Please select a row.";
                    } else if (selectedRows.length > 1) {
                        message = "Please select one row.";
                    }

                    JOptionPane.showMessageDialog(frame, message, Frame.TITLE, JOptionPane.ERROR_MESSAGE);
                    return;
                }

                new StaffEditor(frame, StaffsTab.this, table.convertRowIndexToModel(selectedRows[0]));
            }

        });
        buttonsPanel.add(editButton);

        buttonsPanel.add(Box.createGlue());

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                if (selectedRows.length == 0) {
                    return;
                }

                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    tableModel.removeRow(table.convertRowIndexToModel(selectedRows[i]));
                }
            }

        });
        buttonsPanel.add(deleteButton);

        buttonsPanel.add(Box.createGlue());
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

}
