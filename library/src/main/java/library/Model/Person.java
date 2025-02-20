package library.Model;

import java.util.List;

public abstract class Person {
    String name;
    long id;
    public Person(String name){
        this.name = name;
    }
    public Person(String name,long id){
        this.name =  name;
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public long getID(){
        return this.id;
    }
    public static void printUsers(List<Person> users){
       for(Person user : users){
          System.out.println("Type of user " + user.getClass() +" User name "+user.getName() + " user id " + user.getID());
       }
    }


    @Override public abstract String toString();
}
