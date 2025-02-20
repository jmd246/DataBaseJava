package library.Model;

public class Author extends Person{
    public Author(String name){
        super(name);
    }
    public Author(String name,Long id){
        super(name,id);
    }
 
    @Override
    public String toString(){
        return "Author name " + name + " Author ID is " + this.id; 
    }
}
