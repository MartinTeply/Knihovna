
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    // Ukládání knih do CSV
    public void saveBooksToCSV(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Title,Author,Year,Available,BorrowedBy"); // Hlavička CSV souboru
            for (Book book : books) {
                writer.println(book.toCSV());
            }
        }
    }

    // Ukládání uživatelů do CSV
    public void saveUsersToCSV(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Name"); // Hlavička CSV souboru pro uživatele
            for (User user : users) {
                writer.println(user.getName());
            }
        }
    }
}
