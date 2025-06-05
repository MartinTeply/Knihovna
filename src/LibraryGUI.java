import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LibraryGUI extends JFrame {
    private Library library;
    private DefaultListModel<Book> bookListModel;
    private JList<Book> bookList;
    private JComboBox<User> userCombo;

    public LibraryGUI() {
        library = new Library();

        // data – knihy
        Book b1 = new Book("1984", "George Orwell", 1949);
        Book b2 = new Book("Hobit", "J.R.R. Tolkien", 1937);
        Book b3 = new Book("Kytice", "K. J. Erben", 1853);
        Book b4 = new Book("Zaklínač I Poslední přání", "Andrzej Sapkowski", 2011);
        Book b5 = new Book("Malý princ", "Antoine de Saint-Exupéry", 1943);
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);
        library.addBook(b5);

        // data – uživatelé
        User u1 = new User("Anna");
        User u2 = new User("Tomáš");
        library.addUser(u1);
        library.addUser(u2);

        setTitle("Knihovna - GUI");
        setSize(550, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        bookListModel = new DefaultListModel<>();
        for (Book b : library.getBooks()) {
            bookListModel.addElement(b);
        }
        bookList = new JList<>(bookListModel);
        add(new JScrollPane(bookList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        userCombo = new JComboBox<>();
        for (User u : library.getUsers()) {
            userCombo.addItem(u);
        }

        JButton borrowBtn = new JButton("Půjčit");
        JButton returnBtn = new JButton("Vrátit");
        JButton saveBooksBtn = new JButton("Uložit knihy");

        bottomPanel.add(new JLabel("Uživatel:"));
        bottomPanel.add(userCombo);
        bottomPanel.add(borrowBtn);
        bottomPanel.add(returnBtn);
        bottomPanel.add(saveBooksBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        borrowBtn.addActionListener(e -> {
            Book selectedBook = bookList.getSelectedValue();
            User selectedUser = (User) userCombo.getSelectedItem();
            if (selectedBook != null && selectedUser != null) {
                selectedBook.borrow(selectedUser);
                bookList.repaint();
            }
        });

        returnBtn.addActionListener(e -> {
            Book selectedBook = bookList.getSelectedValue();
            if (selectedBook != null) {
                selectedBook.returnBook();
                bookList.repaint();
            }
        });

        saveBooksBtn.addActionListener(e -> {
            try {
                library.saveBooksToCSV("books.csv");
                library.saveUsersToCSV("users.csv");
                JOptionPane.showMessageDialog(this, "Data byla uložena.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Chyba při ukládání dat.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI());
    }
}
