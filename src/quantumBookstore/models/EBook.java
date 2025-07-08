package quantumBookstore.models;

public class EBook extends Book {
    private String fileType;

    public EBook(String ISBN, String title, int publicationYear,double price, String fileType) {
        super(ISBN, title, publicationYear, price);
        this.fileType = fileType;
    }

}
