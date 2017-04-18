package com.genesis.timetablegenerator.data;

import java.util.ArrayList;

/**
 * @author Asif Ali
 */

public class SubjectData {

    public static final ArrayList<SubjectData> SUBJECT_DATA_LIST = new ArrayList<>();
    private String code, name;
    private BatchData batchData;
    private int hoursPerDay, hoursPerWeek;

    public SubjectData(String code, String name, BatchData batchData, int hoursPerDay, int hoursPerWeek) {
        this.code = code;
        this.name = name;
        this.batchData = batchData;
        this.hoursPerDay = hoursPerDay;
        this.hoursPerWeek = hoursPerWeek;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BatchData getBatchData() {
        return batchData;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

}
