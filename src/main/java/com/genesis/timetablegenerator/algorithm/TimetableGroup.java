package com.genesis.timetablegenerator.algorithm;

import com.genesis.timetablegenerator.data.BatchData;
import com.genesis.timetablegenerator.data.StaffData;

import java.util.HashMap;
import java.util.Random;

/**
 * @author Asif Ali
 */

public class TimetableGroup {

    private int noOfDays, noOfHours, lunchAfter;
    private HashMap<String, Timetable> timetableMap = new HashMap<>();
    private Random random = new Random();

    public TimetableGroup(int noOfDays, int noOfHours, int lunchAfter) {
        this.noOfDays = noOfDays;
        this.noOfHours = noOfHours;
        this.lunchAfter = lunchAfter;

        for (BatchData batchData : BatchData.BATCH_DATA_LIST) {
            for (char section : batchData.getSections()) {
                String year = batchData.getYear();
                timetableMap.put(year + " " + section, new Timetable(this, year, section));
            }
        }
    }

    public void generate() {
        for (StaffData staffData : StaffData.STAFF_DATA_LIST) {
            for (StaffData.SubjectPreference subjectPreference : staffData.getSubjectPreferences()) {
                StaffData.SubjectPreference.TimePreference[] timePreferences = subjectPreference.getTimePreferences();
                if (timePreferences.length == 0) {
                    continue;
                }

//                timetableMap.get(subjectPreference.getSubjectData().getBatchData().getYear() + " " + subjectPreference.getSection())
//                        .addHour(staffData, subjectPreference);
            }
        }
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public int getNoOfHours() {
        return noOfHours;
    }

}
