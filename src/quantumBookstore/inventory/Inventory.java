package quantumBookstore.inventory;

import quantumBookstore.interfaces.Mailable;
import quantumBookstore.interfaces.Shippable;
import quantumBookstore.models.Book;
import quantumBookstore.services.MailService;
import quantumBookstore.services.ShippingService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Inventory {
    private HashMap<String,Book> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    public boolean addBook(Book newBook) {
        inventory.put(newBook.getISBN(), newBook);
        if(inventory.containsKey(newBook.getISBN())){
            return true;
        }
        return false;
    }

    public LinkedList<Book> removeOutdatedBooks(int years){
        Iterator<Book> iterator = inventory.values().iterator();
        LinkedList<Book> outdatedBooks = new LinkedList<>();
        while(iterator.hasNext()){
            Book book = iterator.next();
            if((LocalDate.now().getYear()-book.getPublicationYear()) > years){
                outdatedBooks.add(book);
                inventory.remove(book.getISBN());
            }
        }
        return outdatedBooks;
    }

    public double buyBook(String ISBN, int quantity, String email, String Address) throws Exception{
        Book bookToBeBought = inventory.get(ISBN);
        if(bookToBeBought == null){
            throw new Exception("Error purchasing book with ISBN: " + ISBN + ". It does not exist in the quantumBookstore.inventory. Please check the ISBN and try again.");
        }
        else{
            double paidAmount = 0.0;
            if(bookToBeBought instanceof Shippable){
                Shippable shippableBookToBeBought = (Shippable) bookToBeBought;
                if(quantity<=shippableBookToBeBought.getQuantity()){
                    shippableBookToBeBought.setQuantity(shippableBookToBeBought.getQuantity()-quantity);
                    inventory.put(ISBN, (Book) shippableBookToBeBought);
                    // Send to ShippingService
                    ShippingService wasalha = new ShippingService();
                    wasalha.ship(shippableBookToBeBought,quantity);
                    paidAmount = bookToBeBought.getPrice()*quantity;
                }
                else{
                    throw new Exception("Error purchasing book with ISBN: " + ISBN + " due to insufficient quantity, please try again later.");
                }
            }
            else if(bookToBeBought instanceof Mailable){
                Mailable emailableBookToBeBought = (Mailable) bookToBeBought;
                //Send to MailService
                MailService bareed = new MailService();
                bareed.mail(emailableBookToBeBought,quantity);
                paidAmount = bookToBeBought.getPrice()*quantity;
            }
            return paidAmount;
        }

    }


}
