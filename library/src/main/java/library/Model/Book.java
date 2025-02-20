package library.Model;

public class Book extends Media{
    private String authorName;
    private String isbn;
    private final long authorID;

    public Book(String name,String authorName,String isbn,long authorID){
        super(name);
        this.authorName = authorName;
        this.isbn = isbn;
        this.authorID = authorID;
    }
    public Book(String name,String authorName,String isbn,long authorID ,long bookID){
        super(name,bookID);
        this.isbn = isbn;
        this.authorID = authorID;
        this.authorName = authorName;
    }

    public long getAuthorID() {
        return authorID;
    }
    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }
   
    public String getISBN(){
        return  isbn;
    }
    public String getAuthorName(){
        return authorName;
    } 
    public void setISBN(String isbn){
        this.isbn = isbn;
    }
    public void setBookID(long id){
        super.setID(id);
    }
    @Override
    public String toString() {
        return "Book ID: " + this.getID() + " Title of Book: " + this.getName();
    }
    
}
