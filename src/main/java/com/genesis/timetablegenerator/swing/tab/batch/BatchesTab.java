package com.genesis.timetablegenerator.swing.tab.batch;

import com.genesis.timetablegenerator.data.BatchData;
import com.genesis.timetablegenerator.swing.Frame;
import com.genesis.timetablegenerator.swing.TableTab;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Asif Ali
 */

public class BatchesTab extends TableTab {

    private static final String[] COLUMN_NAMES = new String[]{
            "Year",
            "Sections"
    };
    private ArrayList<BatchData> batchDataList = new ArrayList<>();

    public BatchesTab(Frame frame) {
        super(frame, COLUMN_NAMES);
    }

    @Override
    protected void addClicked() {
//        new BatchEditor(frame, this, );
    }

    @Override
    protected void editClicked(int rowIndex) {
        new BatchEditor();
    }

    @Override
    protected void removeClicked(int[] rowIndexes) {
        for (int i = rowIndexes.length - 1; i >= 0; i--) {
            String year = (String) tableModel.getValueAt(i, 0);
            tableModel.removeRow(i);

            Iterator<BatchData> iterator = batchDataList.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getYear().equals(year)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    public void add(String[] data) {
        tableModel.addRow(data);

        batchDataList.add(new BatchData(data[0], data[1].replace(",", "").toCharArray()));
    }

    public void clear() {
        tableModel.setRowCount(0);

        batchDataList = new ArrayList<>();
    }

}
