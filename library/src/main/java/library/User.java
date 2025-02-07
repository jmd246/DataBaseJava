package library;

public class User extends Person{
    public User(String name,Long id){
        super(name,id);
    }
    @Override
    public String toString() {
        return "User name is " + this.name + " user id is " + this.id ;
    }    
}
