package com.genesis.timetablegenerator.swing;

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

public abstract class TableTab extends JPanel {

    protected DefaultTableModel tableModel;

    protected TableTab(final com.genesis.timetablegenerator.swing.Frame frame, String[] columnNames) {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(columnNames, 0) {

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

                editClicked(table.convertRowIndexToModel(table.rowAtPoint(e.getPoint())));
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
                addClicked();
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

                    JOptionPane.showMessageDialog(frame, message, com.genesis.timetablegenerator.swing.Frame.TITLE, JOptionPane.ERROR_MESSAGE);
                    return;
                }

                editClicked(table.convertRowIndexToModel(selectedRows[0]));
            }

        });
        buttonsPanel.add(editButton);

        buttonsPanel.add(Box.createGlue());

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                if (selectedRows.length == 0) {
                    JOptionPane.showMessageDialog(frame, "Please select a row.", com.genesis.timetablegenerator.swing.Frame.TITLE, JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (int i = 0; i < selectedRows.length; i++) {
                    selectedRows[i] = table.convertRowIndexToModel(selectedRows[i]);
                }

                removeClicked(selectedRows);
            }

        });
        buttonsPanel.add(removeButton);

        buttonsPanel.add(Box.createGlue());
    }

    protected abstract void addClicked();

    protected abstract void editClicked(int rowIndex);

    protected abstract void removeClicked(int[] rowIndexes);

}
