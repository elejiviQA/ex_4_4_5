package org.ot5usk.utils;

import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Log
public class StringBuilder {

    public static List<String> expBookTitlesFromTestCases = getExpBookTitlesFromTestCases();

    public static String randCorrectStringBySelectedLength(int length) {
        return RandomStringUtils.random(length, true, true);
    }

    public static String randCorrectStringBySelectedLengthRandom(int Length) {
        String result = null;
        while (result == null || result.isEmpty()) {
            result = RandomStringUtils.random(new Random().nextInt(Length), true, true);
        }
        return result;
    }

    private static List<String> getExpBookTitlesFromTestCases() {
        List<String> bookTitles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("/test_cases/positive/correct_book_titles_values.csv"))) {
            String bookTitle = reader.readLine();
            while(bookTitle != null) {
                bookTitles.add(bookTitle);
                bookTitle = reader.readLine();
            }
        } catch (IOException e) {
            log.warning(Arrays.toString(e.getStackTrace()));
        }
        return bookTitles;
    }
}
