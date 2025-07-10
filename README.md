## Quantum Bookstore
A lightweight Java console application that models an online bookstore, demonstrating clean OOP design and easy extensibility.

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Project Structure](#project-structure)
4. [Getting Started](#getting-started)

   * [Prerequisites](#prerequisites)
   * [Clone & Import](#clone--import)
5. [Usage Example](#usage-example)
6. [Extending the System](#extending-the-system)

## Overview

Quantum Bookstore lets you manage three types of books—paper books, eBooks, and demo/showcase books—through a straightforward console interface. It handles inventory operations (adding, purchasing, removing outdated stock) and delegates deliveries to shipping or email services, all while throwing clear, descriptive errors when something goes wrong .

## Features

* **PaperBook**:

  * Tracks stock levels
  * Ships to a physical address via `ShippingService`
* **EBook**:

  * Records file type (e.g., PDF, EPUB)
  * Sends via `MailService` to the buyer’s email
* **DemoBook**:

  * “Showcase” items that cannot be purchased
* **Inventory Management**:

  * **Add** new books (ISBN, title, author, year, price)
  * **Remove** and return books older than a given number of years
  * **Buy** books by ISBN & quantity—adjusts stock or throws descriptive exceptions if out of stock or invalid ISBN&#x20;
* **Solid OOP**:

  * Abstract base classes, interfaces (`Mailable`, `Shippable`), and service classes
  * Designed for open/closed extensibility

## Project Structure

```
src/
└── quantumBookstore/
    ├── interfaces/
    │   ├── Mailable.java
    │   └── Shippable.java
    ├── inventory/
    │   └── Inventory.java
    ├── models/
    │   ├── Book.java
    │   ├── PaperBook.java
    │   ├── EBook.java
    │   └── DemoBook.java
    ├── services/
    │   ├── ShippingService.java
    │   └── MailService.java
    └── QuantumBookstoreFullTest.java  ← Main test driver
```

## Getting Started

### Prerequisites

* Java JDK 8 or higher
* Maven or IDE with built‑in build support (IntelliJ IDEA / Eclipse)

### Clone & Import

```bash
git clone https://github.com/yehiarasheed/Fawry-Quantum-Bookstore.git
```

1. **IntelliJ IDEA**

   * Open IDEA → **File ➔ Open** → select project folder
   * Let it index, then right‑click `QuantumBookstore.java` → **Run**

2. **Eclipse**

   * **File ➔ Import ➔ Existing Maven Projects** (or Existing Projects)
   * Point to the cloned folder → **Finish**
   * In **Package Explorer**, right‑click `QuantumBookstore.java` → **Run As ➔ Java Application**

## Usage Example

```java
// Necessary imports
public class Demo {
    public static void main(String[] args) {
        // Create services
        ShippingService wasalha = new ShippingService();
        MailService bareed = new MailService();

        // Inventory
        Inventory inventory = new Inventory(wasalha,bareed);

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

        // Try buying a DemoBook
        try {
            inventory.buyBook("ISBN003", 1, "buyer@example.com", "123 Main St");
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
            inventory.buyBook("ISBN001", 20, "buyer@example.com", "123 Main St");
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


```

## Extending the System

To add a new product type:

1. **Create** a subclass of `Book` (e.g., `AudioBook`).
2. **Implement** relevant interfaces (`Shippable`, `Mailable`, or new ones).
3. **Register** it with `Inventory`—no existing code needs modification.

This adherence to the Open/Closed Principle makes Quantum Bookstore easy to grow and maintain.

---
