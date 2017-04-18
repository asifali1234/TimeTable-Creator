package old.swing.staff.editor;

import com.genesis.timetablegenerator.swing.Frame;
import old.swing.staff.StaffsTab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * @author Asif Ali
 */

public class StaffEditor extends JDialog {

    public StaffEditor(final Frame frame, final StaffsTab staffsTab, final int rowIndex) {
        super(frame, "Staff Editor", true);

        setLayout(new GridBagLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        final JTextField[] textFields = new JTextField[StaffsTab.COLUMN_NAMES.length - 1];
        for (int i = 0; i < textFields.length; i++) {
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.insets = new Insets(10, 10, 0, 0);
            add(new JLabel(StaffsTab.COLUMN_NAMES[i] + ": "), gridBagConstraints);

            JTextField textField = new JTextField(20);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = i;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(10, 0, 0, 10);
            add(textField, gridBagConstraints);
            textFields[i] = textField;
        }

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = textFields.length;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        add(new JLabel("Subjects: "), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = textFields.length;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(10, 0, 0, 10);
        add(new SubjectsPanel(), gridBagConstraints);

        if (rowIndex != -1) {
            DefaultTableModel tableModel = staffsTab.getTableModel();
            for (int i = 0; i < textFields.length; i++) {
                textFields[i].setText((String) tableModel.getValueAt(rowIndex, i));
            }
        }

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = textFields.length + 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(buttonsPanel, gridBagConstraints);

        buttonsPanel.add(Box.createGlue());

        JButton editButton = new JButton(rowIndex == -1 ? "Add" : "Edit");
        editButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String[] data = new String[textFields.length];
                for (int i = 0; i < textFields.length; i++) {
                    data[i] = textFields[i].getText();

                    if (data[i].isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "One or more field is empty.", Frame.TITLE, JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (i > 2) {
                        try {
                            //noinspection ResultOfMethodCallIgnored
                            Integer.parseInt(data[i]);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(frame, StaffsTab.COLUMN_NAMES[i] + " should be numeric.",
                                    Frame.TITLE, JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                DefaultTableModel tableModel = staffsTab.getTableModel();
                if (rowIndex == -1) {
                    tableModel.addRow(data);
                } else {
                    for (int i = 0; i < textFields.length; i++) {
                        tableModel.setValueAt(data[i], rowIndex, i);
                    }
                }

                close();
            }

        });
        buttonsPanel.add(editButton);

        buttonsPanel.add(Box.createGlue());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }

        });
        buttonsPanel.add(cancelButton);

        buttonsPanel.add(Box.createGlue());

        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    public StaffEditor(Frame frame, StaffsTab staffsTab) {
        this(frame, staffsTab, -1);
    }

    private void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}
