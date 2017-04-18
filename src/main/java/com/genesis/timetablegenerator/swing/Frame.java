package com.genesis.timetablegenerator.swing;

import com.genesis.timetablegenerator.swing.tab.batch.BatchesTab;
import old.swing.staff.StaffsTab;
import old.swing.subject.SubjectsTab;
import old.swing.timetable.TimetablesTab;

import javax.swing.*;
import java.awt.*;

/**
 * @author Asif Ali
 */

public class Frame extends JFrame {

    public static final String TITLE = "Timetable Generator";
    private BatchesTab batchesTab;
    private SubjectsTab subjectsTab;
    private StaffsTab staffsTab;

    public Frame() throws HeadlessException {
        super(TITLE);

        setLayout(new BorderLayout());
        setJMenuBar(new MenuBar(this));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane);

        batchesTab = new BatchesTab(this);
        tabbedPane.addTab("Batches", batchesTab);

        subjectsTab = new SubjectsTab(this);
        tabbedPane.addTab("Subjects", subjectsTab);

        staffsTab = new StaffsTab(this);
        tabbedPane.addTab("Staffs", staffsTab);

        tabbedPane.addTab("Timetables", new TimetablesTab());

        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public BatchesTab getBatchesTab() {
        return batchesTab;
    }

    public SubjectsTab getSubjectsTab() {
        return subjectsTab;
    }

    public StaffsTab getStaffsTab() {
        return staffsTab;
    }

}
