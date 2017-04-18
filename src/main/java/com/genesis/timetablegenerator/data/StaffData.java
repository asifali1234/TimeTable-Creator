package com.genesis.timetablegenerator.data;

import java.util.ArrayList;

/**
 * @author Asif Ali
 */

public class StaffData {

    public static final ArrayList<StaffData> STAFF_DATA_LIST = new ArrayList<>();
    private String id, name;
    private SubjectPreference[] subjectPreferences;

    public StaffData(String id, String name, SubjectPreference[] subjectPreferences) {
        this.id = id;
        this.name = name;
        this.subjectPreferences = subjectPreferences;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SubjectPreference[] getSubjectPreferences() {
        return subjectPreferences;
    }

    public class SubjectPreference {

        private SubjectData subjectData;
        private char section;
        private TimePreference[] timePreferences;

        public SubjectPreference(SubjectData subjectData, char section, TimePreference[] timePreferences) {
            this.subjectData = subjectData;
            this.section = section;
            this.timePreferences = timePreferences;
        }

        public SubjectData getSubjectData() {
            return subjectData;
        }

        public char getSection() {
            return section;
        }

        public TimePreference[] getTimePreferences() {
            return timePreferences;
        }

        public class TimePreference {

            private int day;
            private int[] hours;

            public TimePreference(int day, int[] hours) {
                this.day = day;
                this.hours = hours;
            }

            public int getDay() {
                return day;
            }

            public int[] getHours() {
                return hours;
            }

        }

    }

}
