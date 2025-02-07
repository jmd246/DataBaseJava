package library;
public abstract class Person {
    String name;
    Long id;
    public Person(String name,Long id){
        this.name =  name;
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public Long getID(){
        return this.id;
    }
    @Override public abstract String toString();
}
