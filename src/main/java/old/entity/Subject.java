package old.entity;

import java.util.HashMap;

/**
 * @author Asif Ali
 */

public class Subject {

    public static final HashMap<String, Subject> SUBJECT_MAP = new HashMap<>();
    private String name;
    private String[] classes;
    private int minPeriods, maxPeriods, totalPeriods;

    public Subject(String[] data) {
        name = data[0];
        classes = data[1].split(",");
        minPeriods = Integer.parseInt(data[2]);
        maxPeriods = Integer.parseInt(data[3]);
        totalPeriods = Integer.parseInt(data[4]);
    }

    public String getName() {
        return name;
    }

    public String[] getClasses() {
        return classes;
    }

    public int getMinPeriods() {
        return minPeriods;
    }

    public int getMaxPeriods() {
        return maxPeriods;
    }

    public int getTotalPeriods() {
        return totalPeriods;
    }

}
