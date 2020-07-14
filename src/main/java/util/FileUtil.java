package util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {
    private final static String FILE_SEPARATOR = System.getProperty("file.separator");

    public static List<String[]> readFromCsv(File sourceCsvFile) {
        List<String[]> csvLines = null;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(sourceCsvFile));
            csvLines = csvReader.readAll();
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvLines;
    }

    public static void createBadDataCsvFile(List<String[]> badDataList, String csvFileDir) {
        String badDataFileName = csvFileDir + FILE_SEPARATOR + "bad-data-" + System.currentTimeMillis() + ".csv";
        try {
            File badDataFile = new File(badDataFileName);
            badDataFile.createNewFile();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(badDataFile));
            csvWriter.writeAll(badDataList, false);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logResult(int received, int success, int failed, String csvFileDir) {
        String logFileAbsoluteName = csvFileDir + FILE_SEPARATOR + "logResult.csv";
        File logFile = new File(logFileAbsoluteName);
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            CSVWriter csvWriter = new CSVWriter(new FileWriter(logFile));
            String[] logText = {
                    received + " of records received",
                    success + " of records successful",
                    failed + " of records failed"};
            csvWriter.writeNext(logText);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}