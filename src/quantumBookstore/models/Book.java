package quantumBookstore.models;

public abstract class Book {
    private String ISBN;
    private String title;
    private int publicationYear;
    private double price;

    public Book(String ISBN, String title, int publicationYear, double price) {
        this.ISBN = ISBN;
        this.title = title;
        this.publicationYear = publicationYear;
        this.price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public double getPrice() {
        return price;
    }
}
