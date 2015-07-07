package eu.grigoriev.utils.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {
    public static ArrayList<String> stringToListOfLines(String stackTrace) {
        return new ArrayList<>(Arrays.asList(stackTrace.split(System.lineSeparator())));
    }

    public static String join(List<String> strings, String lineSeparator) {
        return org.apache.commons.lang3.StringUtils.join(strings, lineSeparator);
    }
}
