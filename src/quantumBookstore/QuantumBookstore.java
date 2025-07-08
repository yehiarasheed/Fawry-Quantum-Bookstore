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
            double paidAmount = inventory.buyBook("ISBN001", 2, "buyer@example.com", "123 Main St");
            System.out.println("Successfully purchased book with Paid Amount: " + paidAmount);
        } catch (Exception e) {
            System.out.println("Quantum bookstore: " + e.getMessage());
        }

        // Try buying an EBook
        try {
            double paidAmount = inventory.buyBook("ISBN002", 1, "buyer@example.com", "123 Main St");
            System.out.println("Successfully purchased book with Paid Amount: " + paidAmount);
        } catch (Exception e) {
            System.out.println("Quantum bookstore: " + e.getMessage());
        }

        // Try buying a non-existing book
        try {
            double paidAmount = inventory.buyBook("ISBN999", 1, "buyer@example.com", "123 Main St");
            System.out.println("Successfully purchased book with Paid Amount: " + paidAmount);
        } catch (Exception e) {
            System.out.println("Quantum bookstore: " + e.getMessage());
        }

        // Try buying more than in stock
        try {
            double paidAmount = inventory.buyBook("ISBN001", 20, "buyer@example.com", "123 Main St");
            System.out.println("Successfully purchased book with Paid Amount: " + paidAmount);
        } catch (Exception e) {
            System.out.println("Quantum bookstore: " + e.getMessage());
        }

        // Remove books older than 10 years
        System.out.println("Quantum bookstore: Removing outdated books (older than 10 years)...");
        LinkedList<Book> outdatedBooks = inventory.removeOutdatedBooks(10);  // You need to have this method in your Inventory class

        // Print remaining inventory
        System.out.println("Quantum bookstore: Outdated Books:");
        for (Book b : outdatedBooks) { // Assuming getBooks() returns LinkedList<Book>
            System.out.println("ISBN: " + b.getISBN() + ", Title: " + b.getTitle() + ", Year: " + b.getPublicationYear());
        }

    }


}

