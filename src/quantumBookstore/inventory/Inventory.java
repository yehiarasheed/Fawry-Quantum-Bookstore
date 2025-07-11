package quantumBookstore.inventory;

import quantumBookstore.interfaces.Mailable;
import quantumBookstore.interfaces.Shippable;
import quantumBookstore.models.Book;
import quantumBookstore.models.DemoBook;
import quantumBookstore.services.MailService;
import quantumBookstore.services.ShippingService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Inventory {
    private HashMap<String,Book> inventory;
    private ShippingService shippingService;
    private MailService mailService;

    public Inventory(ShippingService shippingService, MailService mailService) {
        inventory = new HashMap<>();
        this.shippingService = shippingService;
        this.mailService = mailService;
    }

    public boolean addBook(Book newBook) {
        inventory.put(newBook.getISBN(), newBook);
        return inventory.containsKey(newBook.getISBN());
    }

    public LinkedList<Book> removeOutdatedBooks(int years){
        System.out.println("Removing outdated books (older than " + years + " years)...");
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
            // Log: Unsuccessful Purchase DNE
            throw new Exception("Error purchasing book with ISBN: " + ISBN + ". It does not exist in the Inventory. Please check the ISBN and try again.");
        }
        else{
            double paidAmount = 0.0;
            if(bookToBeBought instanceof Shippable){
                Shippable shippableBookToBeBought = (Shippable) bookToBeBought;
                if(quantity<=shippableBookToBeBought.getQuantity()){
                    shippableBookToBeBought.setQuantity(shippableBookToBeBought.getQuantity()-quantity);
                    inventory.put(ISBN, (Book) shippableBookToBeBought);
                    paidAmount = bookToBeBought.getPrice()*quantity;
                    // Log: Successful Purchase
                    System.out.println("Successfully Purchased Book: '" + bookToBeBought.getTitle() + "' with Paid Amount: " + paidAmount);
                    // Send to ShippingService for Shipping
                    shippingService.ship(shippableBookToBeBought,quantity, Address);
                }
                else{
                    // Log: Unsuccessful Purchase (Insufficient Quantity)
                    throw new Exception("Error purchasing book with ISBN: " + ISBN + " due to insufficient quantity, please try again later.");
                }
            }
            else if(bookToBeBought instanceof Mailable){
                Mailable mailableBookToBeBought = (Mailable) bookToBeBought;
                paidAmount = bookToBeBought.getPrice()*quantity;
                // Log: Successful Purchase
                System.out.println("Successfully Purchased Book: '" + bookToBeBought.getTitle() + "' with Paid Amount: " + paidAmount);
                //Send to MailService for Mailing
                mailService.mail(mailableBookToBeBought,quantity, email);
            }
            else if(bookToBeBought instanceof DemoBook){
                // Log: Unsuccessful Purchase (Demo books are not for sale)
                throw new Exception("Error purchasing book with ISBN: " + ISBN + ". Demo books are for showcase only and are not for sale.");
            }
            return paidAmount;
        }

    }


}
