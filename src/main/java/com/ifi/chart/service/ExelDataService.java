package com.ifi.chart.service;

import com.ifi.chart.model.ChartSeries;
import com.ifi.chart.model.ExelData;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExelDataService {
    private static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellTypeEnum() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
                return false;
            }
        }
        return true;
    }

    private static HSSFWorkbook getWorkbookFromFile(String filePath) {
        try {
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            return new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, List<ExelData>> readExel(String filePath) {
        HSSFWorkbook workbook = getWorkbookFromFile(filePath);
        Map<String, List<ExelData>> listSheet = new HashMap<String, List<ExelData>>();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            HSSFSheet sheet = workbook.getSheetAt(i);
            List<ExelData> listRow = new ArrayList<ExelData>();

            if (sheet.getSheetName().equals("Example")) break;
            Iterator<Row> rowIter = sheet.rowIterator();
            while (rowIter.hasNext()) {
                Row row = rowIter.next();
                if (isRowEmpty(row)) break;
                if (row.getRowNum() == 0) continue;
                listRow.add(new ExelData(row.getCell(0).toString(), row.getCell(1).toString(), row.getCell(2).toString()));
            }
            listSheet.put(sheet.getSheetName(), listRow);
        }
        return listSheet;
    }

    public static List<ChartSeries> getchartSeries(String filePath) {
        HSSFWorkbook workbook = getWorkbookFromFile(filePath);


        List<ChartSeries> chartSeries = new ArrayList<ChartSeries>();

        String name;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            List<Double> data = new ArrayList<Double>();
            HSSFSheet sheet = workbook.getSheetAt(i);
            Iterator<Row> rowIterator = sheet.rowIterator();
            name = sheet.getSheetName();

            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                if (isRowEmpty(row)) break;
                if (row.getRowNum() == 0) continue;
                data.add(Double.parseDouble(row.getCell(1).toString()));
            }
            chartSeries.add(new ChartSeries(name,data));
        }

        return chartSeries;
    }
}
