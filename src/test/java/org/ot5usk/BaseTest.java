package org.ot5usk;

import org.apache.commons.lang3.RandomStringUtils;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.ot5usk.steps.specifications.Specifications.requestSpecAddNewAuthor;

public class BaseTest {

    public static class AuthorCreator {
        static List<Integer> nameLengths = List.of(1, 25, 50);
        public static String randomAnyAuthorName() {
            return RandomStringUtils.random(new Random().nextInt(50), true, true);
        }

        public static String randomAnyAuthorName(int length) {
            return RandomStringUtils.random(length, true, true);
        }

        public static List<String> randomAnyAuthorNamesList() {
            List<String> names = new ArrayList<>();
            for (Integer nameLength: nameLengths) {
                names.add(randomAnyAuthorName(nameLength));
            }
            return names;
        }

        public static AddNewAuthorResponse createNewAuthor() {
            return requestSpecAddNewAuthor( randomAnyAuthorName(), randomAnyAuthorName(), randomAnyAuthorName(), 201);
        }

        public static Long getNewAuthorId() {
            return createNewAuthor().getAuthorId();
        }
    }
}
