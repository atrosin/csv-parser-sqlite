package util;

import java.util.Arrays;

public class FilterUtil {
    public static String[] filterCsv(String[] csvLine) {
        return Arrays.stream(csvLine)
                .map(str -> str.contains("'") ? str.replace("'", "''") : str)
                .toArray(String[]::new);
    }
}