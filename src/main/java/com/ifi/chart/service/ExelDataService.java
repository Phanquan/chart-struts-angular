package com.ifi.chart.service;

import com.ifi.chart.model.ExelData;
import com.ifi.chart.model.SeriesData;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
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

    public static List<SeriesData> getSeriesDataByDay(String filePath) {
        HSSFWorkbook workbook = getWorkbookFromFile(filePath);
        List<SeriesData> listDataByDay = new ArrayList<SeriesData>();
        String name;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            List<Double> data = new ArrayList<Double>();
            HSSFSheet sheet = workbook.getSheetAt(i);
            Iterator<Row> rowIterator = sheet.rowIterator();
            name = sheet.getSheetName();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (isRowEmpty(row)) break;
                if (row.getRowNum() == 0) continue;
                data.add(Double.parseDouble(row.getCell(1).toString()));
            }
            listDataByDay.add(new SeriesData(i, name, data));
        }
        return listDataByDay;
    }

    public static List<List<List<SeriesData>>> getSeriesDataByHour(String filePath) {
        HSSFWorkbook workbook = getWorkbookFromFile(filePath);
        List<List<List<SeriesData>>> listDataByHour = new ArrayList<List<List<SeriesData>>>();
        String namePA = "PA";
        String namePS = "PS";
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            List<List<SeriesData>> listDataPerSheet = new ArrayList<List<SeriesData>>();
            HSSFSheet sheet = workbook.getSheetAt(i);
            for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j += 24) {
                List<Double> dataPA = new ArrayList<Double>();
                List<Double> dataPS = new ArrayList<Double>();
                for (int k = j; k < j + 24; k++) {
                    HSSFRow row = sheet.getRow(k);
                    dataPA.add(Double.parseDouble(row.getCell(1).toString().replaceAll("[^.0-9]+", "")));
                    dataPS.add(Double.parseDouble(row.getCell(2).toString().replaceAll("[^.0-9]+", "")));
                }
                listDataPerSheet.add(Arrays.asList(new SeriesData(j,namePA,dataPA),new SeriesData(j,namePS,dataPS)));
            }
            listDataByHour.add(listDataPerSheet);
        }

        return listDataByHour;
    }
}
