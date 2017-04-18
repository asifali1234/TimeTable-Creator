package com.genesis.timetablegenerator.algorithm;

/**
 * @author Asif Ali
 */

public class Hour {

    private Timetable timetable;
    private int day, startHour, endHour;
    private String subjectCode, staffId;

    public Hour(Timetable timetable, int day, int startHour, int endHour, String subjectCode, String staffId) {
        this.timetable = timetable;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.subjectCode = subjectCode;
        this.staffId = staffId;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public int getDay() {
        return day;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getStaffId() {
        return staffId;
    }

}
