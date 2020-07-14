import db.SQLCommand;
import util.FilterUtil;

import java.io.File;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static db.JDBCConfig.close;
import static db.JDBCConfig.config;
import static util.FileUtil.*;

public class ParserMain {
    public static void main(String[] args) {
        File csvFile = readFilePath();
        String csvFileDir = csvFile.getParent();
        List<String[]> allDataList = readFromCsv(csvFile);
        List<String[]> badDataList = new ArrayList<>();
        Statement statement = config();
        SQLCommand sqlCommand = new SQLCommand(statement);
        sqlCommand.createDataTable();
        int receivedRowsCount = 0;
        int successfulRowsCount = 0;
        int failedRowsCount = 0;
        for (String[] csvLine : allDataList) {
            if (csvLine.length == 10) {
                csvLine = FilterUtil.filterCsv(csvLine);
                if (Arrays.stream(csvLine).anyMatch(String::isEmpty)) {
                    badDataList.add(csvLine);
                    failedRowsCount++;
                } else {
                    sqlCommand.insertIntoDataTable(csvLine);
                    successfulRowsCount++;
                }
                receivedRowsCount++;
            }
        }
        close();
        createBadDataCsvFile(badDataList, csvFileDir);
        logResult(receivedRowsCount, successfulRowsCount, failedRowsCount, csvFileDir);
    }

    private static File readFilePath() {
        Scanner scanner = new Scanner(System.in);
        File csvFilePath = new File(scanner.nextLine());
        while (!csvFilePath.exists()) {
            csvFilePath = new File(scanner.nextLine());
        }
        return csvFilePath;
    }
}