import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private int nextId = 1;
    private final String FILE_NAME = "../books.txt"; // file is in the root project folder

    public Library() {
        loadFromFile(); // automatically load books when program starts
    }

    public void addBook(String title, String author) {
        books.add(new Book(nextId++, title, author));
        System.out.println("Book added.");
    }

    public void removeBook(int id) {
        books.removeIf(book -> book.getId() == id);
        System.out.println("Book removed.");
    }

    public void issueBook(int id) {
        for (Book b : books) {
            if (b.getId() == id && !b.isIssued()) {
                b.setIssued(true);
                System.out.println("Book issued.");
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(int id) {
        for (Book b : books) {
            if (b.getId() == id && b.isIssued()) {
                b.setIssued(false);
                System.out.println("Book returned.");
                return;
            }
        }
        System.out.println("Invalid return.");
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // ✅ Save books to books.txt
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book b : books) {
                writer.write(b.getId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.isIssued());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    // ✅ Load books from books.txt
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    boolean issued = Boolean.parseBoolean(parts[3]);
                    Book book = new Book(id, title, author);
                    book.setIssued(issued);
                    books.add(book);
                    if (id >= nextId) nextId = id + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }
}
