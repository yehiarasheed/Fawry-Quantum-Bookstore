package quantumBookstore;

import quantumBookstore.inventory.Inventory;
import quantumBookstore.models.Book;
import quantumBookstore.models.DemoBook;
import quantumBookstore.models.EBook;
import quantumBookstore.models.PaperBook;
import quantumBookstore.services.MailService;
import quantumBookstore.services.ShippingService;

import java.util.LinkedList;

public class QuantumBookstore {
    public static void main(String[] args) {
        // Create services
        ShippingService shippingService = new ShippingService();
        MailService mailService = new MailService();

        // Inventory
        Inventory inventory = new Inventory();

        // Add books
        PaperBook paperBook = new PaperBook("ISBN001", "Java Fundamentals", 2005, 50.0, 10);
        EBook eBook = new EBook("ISBN002", "Learn Java Online", 2021, 30.0, "PDF");
        DemoBook demoBook = new DemoBook("ISBN003", "Java Demo", 2022, 0.0);

        inventory.addBook(paperBook);
        inventory.addBook(eBook);
        inventory.addBook(demoBook);

        // Try buying a PaperBook
        try {
            inventory.buyBook("ISBN001", 2, "buyer@example.com", "123 Main St");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----------------------");

        // Try buying an EBook
        try {
            inventory.buyBook("ISBN002", 1, "buyer@example.com", "123 Main St");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----------------------");

        // Try buying a non-existing book
        try {
            inventory.buyBook("ISBN999", 1, "buyer@example.com", "123 Main St");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----------------------");

        // Try buying more than in stock
        try {
            double paidAmount = inventory.buyBook("ISBN001", 20, "buyer@example.com", "123 Main St");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----------------------");

        // Remove books older than 10 years
        LinkedList<Book> outdatedBooks = inventory.removeOutdatedBooks(10);

        // Print Outdated Books
        System.out.println("Outdated Books:");
        for (Book b : outdatedBooks) {
            System.out.println("ISBN: " + b.getISBN() + ", Title: " + b.getTitle() + ", Year: " + b.getPublicationYear());
        }

    }


}

