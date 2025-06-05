import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryGUI extends JFrame {
    private Library library;
    private DefaultListModel<Book> bookListModel;
    private JList<Book> bookList;
    private JComboBox<User> userCombo;

    public LibraryGUI() {
        library = new Library();

        // Dummy data – knihy
        Book b1 = new Book("1984", "George Orwell", 1949);
        Book b2 = new Book("Hobit", "J.R.R. Tolkien", 1937);
        Book b3 = new Book("Kytice", "K. J. Erben", 1853);
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        // Dummy data – uživatelé
        User u1 = new User("Anna");
        User u2 = new User("Tomáš");
        library.addUser(u1);
        library.addUser(u2);

        // Nastavení okna
        setTitle("Knihovna - GUI");
        setSize(550, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Seznam knih
        bookListModel = new DefaultListModel<>();
        for (Book b : library.getBooks()) {
            bookListModel.addElement(b);
        }
        bookList = new JList<>(bookListModel);
        add(new JScrollPane(bookList), BorderLayout.CENTER);

        // Výběr uživatele a tlačítka
        JPanel bottomPanel = new JPanel();
        userCombo = new JComboBox<>();
        for (User u : library.getUsers()) {
            userCombo.addItem(u);
        }

        JButton borrowBtn = new JButton("Půjčit");
        JButton returnBtn = new JButton("Vrátit");

        bottomPanel.add(new JLabel("Uživatel:"));
        bottomPanel.add(userCombo);
        bottomPanel.add(borrowBtn);
        bottomPanel.add(returnBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // Akce pro půjčení
        borrowBtn.addActionListener(e -> {
            Book selectedBook = bookList.getSelectedValue();
            User selectedUser = (User) userCombo.getSelectedItem();
            if (selectedBook != null && selectedUser != null) {
                selectedBook.borrow(selectedUser);
                bookList.repaint();
            }
        });

        // Akce pro vrácení
        returnBtn.addActionListener(e -> {
            Book selectedBook = bookList.getSelectedValue();
            if (selectedBook != null) {
                selectedBook.returnBook();
                bookList.repaint();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI());
    }
}