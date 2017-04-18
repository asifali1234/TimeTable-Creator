package old.swing.subject;

import com.genesis.timetablegenerator.swing.Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * @author Asif Ali
 */

public class SubjectEditor extends JDialog {

    public SubjectEditor(final Frame frame, final SubjectsTab subjectsTab, final int rowIndex) {
        super(frame, "Subject Editor", true);

        setLayout(new GridBagLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        final JTextField[] textFields = new JTextField[SubjectsTab.COLUMN_NAMES.length];
        for (int i = 0; i < textFields.length; i++) {
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.insets = new Insets(10, 10, 0, 0);
            add(new JLabel(SubjectsTab.COLUMN_NAMES[i] + ": "), gridBagConstraints);

            JTextField textField = new JTextField(20);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = i;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(10, 0, 0, 10);
            add(textField, gridBagConstraints);
            textFields[i] = textField;
        }

        if (rowIndex != -1) {
            DefaultTableModel tableModel = subjectsTab.getTableModel();
            for (int i = 0; i < textFields.length; i++) {
                textFields[i].setText((String) tableModel.getValueAt(rowIndex, i));
            }
        }

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = textFields.length;
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
                            JOptionPane.showMessageDialog(frame, SubjectsTab.COLUMN_NAMES[i] + " should be numeric.",
                                    Frame.TITLE, JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                DefaultTableModel tableModel = subjectsTab.getTableModel();
                for (int i = 0, n = tableModel.getRowCount(); i < n; i++) {
                    if (tableModel.getValueAt(i, 0).equals(data[0])) {
                        JOptionPane.showMessageDialog(frame, "Duplicate subject code.", Frame.TITLE, JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (rowIndex == -1) {
                    tableModel.addRow(data);

                    subjectsTab.rowAdded();
                } else {
                    for (int i = 0; i < textFields.length; i++) {
                        tableModel.setValueAt(data[i], rowIndex, i);
                    }

                    subjectsTab.rowEdited(rowIndex);
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

    public SubjectEditor(Frame frame, SubjectsTab subjectsTab) {
        this(frame, subjectsTab, -1);
    }

    private void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}
