package quantumBookstore.models;

public class PaperBook extends Book {
    private int quantity;

    public PaperBook(String ISBN, String title, int publicationYear, double price, int quantity) {
        super(ISBN, title, publicationYear, price);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
