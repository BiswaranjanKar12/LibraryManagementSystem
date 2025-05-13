import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter Book ID to remove: ");
                    int removeId = sc.nextInt();
                    library.removeBook(removeId);
                    break;
                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    library.issueBook(issueId);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                    break;
                case 5:
                    library.listBooks();
                    break;
                case 6:
                    library.saveToFile();  // ✅ This line is required to save before exit
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}

