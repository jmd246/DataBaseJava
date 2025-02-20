package library.Model;
public abstract class Media {
    private String name;
    private long id;
    public Media(String name){
        this.name = name;
    }
    public Media(String name,long id){
        this.name = name;
        this.id = id;
    }
    public void setID(long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public long getID(){
        return id;
    } 
    @Override 
    public abstract String toString();  
}
