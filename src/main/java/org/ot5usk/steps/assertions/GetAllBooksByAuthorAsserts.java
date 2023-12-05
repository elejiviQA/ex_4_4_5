package org.ot5usk.steps.assertions;

import lombok.Getter;
import org.ot5usk.models.add_new_author.AddNewAuthorRequest;
import org.ot5usk.models.add_new_author.AddNewAuthorResponse;
import org.ot5usk.models.add_new_book.AddNewBookRequest;
import org.ot5usk.models.get_all_books_by_author.GetAllBooksByAuthorResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.ot5usk.utils.DateBuilder.convertToLocalDateViaMillisecond;
import static org.ot5usk.utils.StringBuilder.expBookTitlesFromTestCases;

@Getter
public class GetAllBooksByAuthorAsserts {

    private final AddNewBookRequest expectedBook;
    private final List<GetAllBooksByAuthorResponse> currentBooks;
    private final AddNewAuthorRequest expectedAuthor;
    private final AddNewAuthorResponse currentAuthor;
    private static List<String> expectedTitles = new ArrayList<>();
    private static List<String> currentTitles = new ArrayList<>();
    private final long approximateUpdatedTime;

    public GetAllBooksByAuthorAsserts(AddNewBookRequest expectedBook, AddNewAuthorRequest expectedAuthor,
                                      AddNewAuthorResponse currentAuthor, List<GetAllBooksByAuthorResponse> currentBooks, long approximateUpdatedTime) {
        cleanIfNecessary();
        this.approximateUpdatedTime = approximateUpdatedTime;
        this.expectedBook = expectedBook;
        this.expectedAuthor = expectedAuthor;
        this.currentAuthor = currentAuthor;
        this.currentBooks = currentBooks;
        setExpectedTitle();
        setCurrentTitle();
    }

    private void cleanIfNecessary() {
        if (expectedTitles.size() == expBookTitlesFromTestCases.size()) {
            currentTitles = new ArrayList<>();
            expectedTitles = new ArrayList<>();
        }
    }

    private void setExpectedTitle() {
        expectedTitles.add(expectedBook.getBookTitle());
    }
    private void setCurrentTitle() {
        currentTitles.add(currentBooks.get(currentBooks.size() - 1).getBookTitle());
    }

    public void assertExpectedAuthorId() {
        assertTrue(currentBooks.stream().allMatch(currentBook -> currentBook.getAuthor().getId().equals(currentAuthor.getAuthorId())));
    }

    public void assertExpectedAuthorName() {
        currentBooks.forEach(currentBook -> {
            assertEquals(expectedAuthor.getFirstName(), currentBook.getAuthor().getFirstName());
            assertEquals(expectedAuthor.getFamilyName(), currentBook.getAuthor().getFamilyName());
            assertEquals(expectedAuthor.getSecondName(), currentBook.getAuthor().getSecondName());
        });
    }

    public void assertExpectedNumOfBooks() {
        assertEquals(expectedTitles.size(), currentTitles.size());
    }

    public void assertExpectedBookTitles() {
        expectedTitles.forEach(expectedTitle -> assertTrue(currentTitles.contains(expectedTitle)));
    }

    public void assertExpectedUpdated() {
        Date currentDate =  currentBooks.get(currentBooks.size() - 1).getUpdated();
        if (currentDate != null) {
            assertTrue(approximateUpdatedTime - currentDate.getTime() < 30000);
        }
    }

    public void assertExpectedAuthorBirthDate() {
        currentBooks.forEach(currentBook -> {
            LocalDate currentDate = convertToLocalDateViaMillisecond(currentBook.getAuthor().getBirthDate());
            assertEquals(expectedAuthor.getBirthDate(), currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        });
    }
}
