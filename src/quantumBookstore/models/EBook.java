package quantumBookstore.models;

import quantumBookstore.interfaces.Mailable;

public class EBook extends Book implements Mailable {
    private String fileType;

    public EBook(String ISBN, String title, int publicationYear,double price, String fileType) {
        super(ISBN, title, publicationYear, price);
        this.fileType = fileType;
    }

    @Override
    public String getFileType() {
        return fileType;
    }
}
