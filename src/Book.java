

public class Book implements Borrowable {
    private String title;
    private String author;
    private int year;
    private boolean isAvailable = true;
    private User borrowedBy = null;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public void borrow(User user) {
        if (isAvailable && user.borrowBook(this)) {
            isAvailable = false;
            borrowedBy = user;
            System.out.println(user.getName() + " si půjčil/a knihu: " + title);
        } else {
            System.out.println("Kniha '" + title + "' není dostupná.");
        }
    }

    public void returnBook() {
        if (borrowedBy != null) {
            borrowedBy.returnBook(this);
            isAvailable = true;
            System.out.println("Kniha '" + title + "' byla vrácena.");
            borrowedBy = null;
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBorrowedByName() {
        return borrowedBy != null ? borrowedBy.getName() : "Nikdo";
    }

    public String toCSV() {
        return title + "," + author + "," + year + "," + isAvailable + "," +
                (borrowedBy != null ? borrowedBy.getName() : "");
    }

    @Override
    public String toString() {
        return title + " od " + author + " (" + year + ") - " +
                (isAvailable ? "Dostupná" : "Půjčená uživatelem " + borrowedBy.getName());
    }
}
