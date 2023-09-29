
 
import java.util.ArrayList;

class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private final ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void borrowBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Book borrowed successfully: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book not found or already borrowed.");
    }

    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("Book returned successfully: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book not found or already returned.");
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        books.stream().filter((book) -> (book.isAvailable())).forEachOrdered((book) -> {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")");
        });
    }
}

class LibraryReport {
    public void generateAllBooksReport(ArrayList<Book> books) {
        System.out.println("All Books:");
        books.forEach((book) -> {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")");
        });
    }

    public void generateBorrowedBooksReport(ArrayList<Book> books) {
        System.out.println("Borrowed Books:");
        books.stream().filter((book) -> (!book.isAvailable())).forEachOrdered((book) -> {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")");
        });
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        LibraryReport report = new LibraryReport();

        // Adding books to the library
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", "978-0-316-76948-0"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4"));
        library.addBook(new Book("1984", "George Orwell", "978-0-452-28423-4"));

        // Display available books
        library.displayAvailableBooks();

        // Borrow and return books
        library.borrowBook("978-0-316-76948-0");
        library.borrowBook("978-0-452-28423-4");
        library.returnBook("978-0-316-76948-0");

        // Display available books after some operations
        library.displayAvailableBooks();

        // Generate reports
        ArrayList<Book> allBooks = library.getAllBooks();
        ArrayList<Book> borrowedBooks = library.getBorrowedBooks();

        report.generateAllBooksReport(allBooks);
        report.generateBorrowedBooksReport(borrowedBooks);
    }
}
