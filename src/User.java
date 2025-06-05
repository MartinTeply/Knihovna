import java.util.ArrayList;

public class User extends Person {
    private int maxBooks = 3;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public User(String name) {
        super(name);
    }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= maxBooks) {
            System.out.println(name + " nemůže půjčit více než " + maxBooks + " knih.");
            return false;
        }
        borrowedBooks.add(book);
        return true;
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return name + " (" + borrowedBooks.size() + " výpůjček)";
    }
}
