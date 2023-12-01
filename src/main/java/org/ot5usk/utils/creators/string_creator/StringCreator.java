package org.ot5usk.utils.creators.string_creator;

import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.params.provider.Arguments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Log
public class StringCreator {

    public static List<String> expBookTitles = getExpectedBookTitlesFromTestCases();
    public static int epxBookTitlesLength = expBookTitles.size();
    public static String randCorrectStringBySelectedLength(int length) {
        return RandomStringUtils.random(length, true, true);
    }

    public static String randCorrectStringBySelectedLengthRandom(int limitLength) {
        return RandomStringUtils.random(new Random().nextInt(limitLength), true, true);
    }

    public static List<Arguments> randCorrectStringsBySelectedLengths(int from, int to) {
        List<Arguments> arguments = new ArrayList<>();
        for (int length = from; length <= to; length++) {
            arguments.add(Arguments.of(RandomStringUtils.random(length, true, true), length));
        }
        return arguments;
    }

    public static List<String> randCorrectAuthorNamesByRequirements() {
        return List.of(randCorrectStringBySelectedLength(AuthorNameLengthLimits.MIN.getLength()),
                randCorrectStringBySelectedLength(AuthorNameLengthLimits.AVERAGE.getLength()),
                randCorrectStringBySelectedLength(AuthorNameLengthLimits.MAX.getLength()));
    }

    private static List<String> getExpectedBookTitlesFromTestCases() {
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
