package quantumBookstore.services;

import quantumBookstore.interfaces.Shippable;
import quantumBookstore.models.Book;

public class ShippingService {
    public ShippingService() {}

    public void ship(Shippable bookToBeShipped, int quantity, String Address){
        Book book = (Book) bookToBeShipped;
        System.out.println("Successfully Shipped Book: '" + book.getTitle() + "' with Quantity: " + quantity + (quantity == 1?" Book":" Books") + " to Address: " + Address);
    }
}
