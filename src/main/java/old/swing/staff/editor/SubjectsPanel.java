package old.swing.staff.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Asif Ali
 */

public class SubjectsPanel extends JPanel {

    public SubjectsPanel() {
        super(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        JButton addButton = new JButton("+");
        int size = addButton.getPreferredSize().height;
        addButton.setPreferredSize(new Dimension(size, size));
        addButton.setMargin(new Insets(0, 0, 0, 0));
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addSubject();
            }

        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(addButton, gridBagConstraints);

        String[] columnNames = {"Name", "Section", "Preferences"};
        for (int i = 0; i < columnNames.length; i++) {
            gridBagConstraints.gridx = i + 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 10, 0, 0);
            add(new JLabel(columnNames[i]), gridBagConstraints);
        }

        addSubject();
    }

    private void addSubject() {

    }

}
