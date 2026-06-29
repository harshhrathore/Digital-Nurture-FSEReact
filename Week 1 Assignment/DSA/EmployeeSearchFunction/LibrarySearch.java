import java.util.Arrays;
import java.util.Comparator;

public class LibrarySearch {
    static class Book {
        int bookId;
        String title;
        String author;

        Book(int bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
        }
    }

    static int linearSearch(Book[] shelf, String titleToFind) {
        for (int i = 0; i < shelf.length; i++) {
            if (shelf[i].title.equals(titleToFind)) {
                return i;
            }
        }
        return -1;
    }

    static int binarySearch(Book[] sortedShelf, String titleToFind) {
        int low = 0;
        int high = sortedShelf.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = sortedShelf[mid].title.compareTo(titleToFind);
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Book[] shelf = {
            new Book(1, "The Silent Patient", "Alex Michaelides"),
            new Book(2, "Educated", "Tara Westover"),
            new Book(3, "Atomic Habits", "James Clear"),
            new Book(4, "Sapiens", "Yuval Noah Harari"),
            new Book(5, "The Alchemist", "Paulo Coelho")
        };

        int foundAt = linearSearch(shelf, "Sapiens");
        System.out.println("Linear search found at index: " + foundAt);

        Book[] sortedShelf = Arrays.copyOf(shelf, shelf.length);
        Arrays.sort(sortedShelf, Comparator.comparing(b -> b.title));

        int foundAtSorted = binarySearch(sortedShelf, "The Alchemist");
        System.out.println("Binary search found at index: " + foundAtSorted);
    }
}
