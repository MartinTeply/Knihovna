
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

    
    public void saveBooksToCSV(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Title,Author,Year,Available,BorrowedBy"); 
            for (Book book : books) {
                writer.println(book.toCSV());
            }
        }
    }

    
    public void saveUsersToCSV(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Name"); 
            for (User user : users) {
                writer.println(user.getName());
            }
        }
    }
}
