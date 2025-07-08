package quantumBookstore.services;

import quantumBookstore.interfaces.Mailable;
import quantumBookstore.models.Book;

public class MailService {
    public MailService(){}

    public void mail(Mailable bookToBeMailed, int quantity){
        Book book = (Book) bookToBeMailed;
        System.out.println("Have Successfully Mailed Book: " + book.getTitle() + " with quantity: " + quantity);
    }
}
