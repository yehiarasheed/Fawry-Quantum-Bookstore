package quantumBookstore.models;

import quantumBookstore.interfaces.Shippable;

public class PaperBook extends Book implements Shippable {
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
