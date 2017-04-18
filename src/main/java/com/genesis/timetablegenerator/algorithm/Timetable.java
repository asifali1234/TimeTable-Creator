package com.genesis.timetablegenerator.algorithm;

import com.genesis.timetablegenerator.data.StaffData;
import com.genesis.timetablegenerator.data.SubjectData;

/**
 * @author Asif Ali
 */

public class Timetable {

    private TimetableGroup timetableGroup;
    private String year;
    private char section;
    private Hour[][] hours;

    public Timetable(TimetableGroup timetableGroup, String year, char section) {
        this.timetableGroup = timetableGroup;
        this.year = year;
        this.section = section;

        hours = new Hour[timetableGroup.getNoOfDays()][timetableGroup.getNoOfHours()];
    }

    public void addHour(int day, int startHour, StaffData staffData, StaffData.SubjectPreference subjectPreference) {
        SubjectData subjectData = subjectPreference.getSubjectData();
        hours[day][startHour] = new Hour(this, day, startHour, startHour + subjectData.getHoursPerDay() - 1, subjectData.getCode(), staffData.getId());
    }

    public String getYear() {
        return year;
    }

    public char getSection() {
        return section;
    }

    public Hour[][] getHours() {
        return hours;
    }

}
