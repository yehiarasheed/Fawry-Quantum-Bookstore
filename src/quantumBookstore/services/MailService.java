package quantumBookstore.services;

import quantumBookstore.interfaces.Mailable;
import quantumBookstore.models.Book;

public class MailService {
    public MailService(){}

    public void mail(Mailable bookToBeMailed, int quantity, String email){
        Book book = (Book) bookToBeMailed;
        System.out.println("Successfully Mailed Book: '" + book.getTitle() + "' with Quantity: " + quantity + (quantity == 1?" Book":" Books") + " to " + email);
    }
}
