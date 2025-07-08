# Fawry Quantum Bookstore

**Quantum Bookstore** is a simple, extensible Java console application that simulates an online bookstore.  
It demonstrates solid OOP design using abstract classes, interfaces, and services to handle different book types, shipping, and emailing.

---

## Features

- Paper Books — have stock and can be shipped.
- EBooks — have a file type and can be emailed.
- Demo Books — showcase/demo books that are not for sale.
- Inventory Management — add books, remove outdated books by age, and buy books by ISBN.
- Shipping & Mail Services — handle delivery based on book type.
- Extensible Design — easily add new product types without modifying existing code.

---

## Project Structure

```
src/
└── quantumBookstore/
├── interfaces/
│ ├── Mailable.java
│ └── Shippable.java
├── inventory/
│ └── Inventory.java
├── models/
│ ├── Book.java
│ ├── DemoBook.java
│ ├── EBook.java
│ └── PaperBook.java
├── services/
│ ├── MailService.java
│ └── ShippingService.java
└── QuantumBookstore.java // The main driver class
```


---

## Example

Below is an example of how the program runs:  
The bookstore:
- Adds books to the inventory
- Buys paper and ebooks
- Handles invalid ISBNs and stock limits
- Removes outdated books and shows the removed collection

  ```java
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
        LinkedList<Book> outdatedBooks = inventory.removeOutdatedBooks(10);

        // Print Outdated Books
        System.out.println("Quantum bookstore: Outdated Books:");
        for (Book b : outdatedBooks) {
            System.out.println("ISBN: " + b.getISBN() + ", Title: " + b.getTitle() + ", Year: " + b.getPublicationYear());
        }
  ```

**Example Output:**
![image](https://github.com/user-attachments/assets/74b04551-0e9a-4d32-8927-b3faa7475765)

## How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/yehiarasheed/Fawry-Quantum-Bookstore.git
#### 2. Open in IntelliJ IDEA

1. Open **IntelliJ IDEA**.
2. Click **Open** and select the cloned project folder.
3. Let IntelliJ index the project.
4. Right-click `QuantumBookstore.java` in the `src/quantumBookstore` package.
5. Click **Run 'QuantumBookstore.main()'**.
6. Check the console output for all test case results.

---

#### 3. Open in Eclipse

1. Open **Eclipse IDE**.
2. Click **File > Import > Existing Projects into Workspace**.
3. Browse to the cloned project folder and click **Finish**.
4. In the **Package Explorer**, expand the `src` folder.
5. Right-click `QuantumBookstore.java` in `src/quantumBookstore`.
6. Select **Run As > Java Application**.
7. Check the console output for all test cases.

This executes the main method and prints the output in the console.

---
