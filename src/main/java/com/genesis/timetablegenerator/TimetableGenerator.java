package com.genesis.timetablegenerator;

import com.genesis.timetablegenerator.swing.Frame;

import javax.swing.*;

/**
 * @author Asif Ali
 */

public class TimetableGenerator {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Frame();
            }

        });


    }

}
