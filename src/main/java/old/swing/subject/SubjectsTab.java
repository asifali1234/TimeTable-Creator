package old.swing.subject;

import com.genesis.timetablegenerator.swing.Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author Asif Ali
 */

public class SubjectsTab extends JPanel {

    public static final String[] COLUMN_NAMES = new String[]{
            "Code",
            "Name",
            "Class",
            "Min. classes per day",
            "Max. classes per day",
            "Total classes per week"
    };
    private DefaultTableModel tableModel;
    private ArrayList<String> classList = new ArrayList<>();

    public SubjectsTab(final Frame frame) {
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

                new SubjectEditor(frame, SubjectsTab.this, table.convertRowIndexToModel(table.rowAtPoint(e.getPoint())));
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
                new SubjectEditor(frame, SubjectsTab.this);
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

                new SubjectEditor(frame, SubjectsTab.this, table.convertRowIndexToModel(selectedRows[0]));
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

    public void rowAdded() {
        rowEdited(tableModel.getRowCount() - 1);
    }

    public void rowEdited(int rowIndex) {
        String classString = (String) tableModel.getValueAt(rowIndex, 2);
        if (!classList.contains(classString)) {
            classList.add(classString);
        }
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public ArrayList<String> getClassList() {
        return classList;
    }

}
