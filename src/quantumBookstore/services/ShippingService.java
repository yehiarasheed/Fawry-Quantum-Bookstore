package quantumBookstore.services;

import quantumBookstore.interfaces.Shippable;
import quantumBookstore.models.Book;

public class ShippingService {
    public ShippingService() {}

    public void ship(Shippable bookToBeShipped, int quantity){
        Book book = (Book) bookToBeShipped;
        System.out.println("Have Successfully Shipped Book: " + book.getTitle() + " with quantity: " + quantity);
    }
}
