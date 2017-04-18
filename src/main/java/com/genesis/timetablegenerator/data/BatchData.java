package com.genesis.timetablegenerator.data;

import java.util.ArrayList;

/**
 * @author Asif Ali
 */

public class BatchData {

    public static final ArrayList<BatchData> BATCH_DATA_LIST = new ArrayList<>();
    private String year;
    private char[] sections;

    public BatchData(String year, char[] sections) {
        this.year = year;
        this.sections = sections;
    }

    public String getYear() {
        return year;
    }

    public char[] getSections() {
        return sections;
    }

}
