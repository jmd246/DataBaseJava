package library.DAOs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.Model.User;


public class UserDAO implements MemberDAO<User, Long>{
    private final Connection connection;
    public UserDAO(Connection connection){
        this.connection = connection;
    }
    @Override
    public User insert(User entity) throws SQLException {
        String query = "INSERT INTO user (name) VALUES (?)";

        try(PreparedStatement prepStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
            prepStatement.setString(1, entity.getName());
            int rowsInsert = prepStatement.executeUpdate();
            if(rowsInsert > 0){
                try(ResultSet key = prepStatement.getGeneratedKeys()){
                    if(key.next()){
                        return new User(entity.getName(),key.getLong(1));
                    }
                }
            }
        }
        return null;
    }

    @Override
    public User findByID(Long id) throws SQLException {
        if(id == null) {
            throw new IllegalArgumentException("ID cant be null");
        }
        String query = "SELECT * FROM user WHERE member_id = ?";
        try(PreparedStatement prepStatement = connection.prepareStatement(query)){
            prepStatement.setLong(1, id);
            ResultSet resultSet = prepStatement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                Long memberID = resultSet.getLong("member_id");
                User user = new User(name,memberID);
                resultSet.close();
                return user;
            }
            else{
                return null;
            }
        }
    }

    @Override
    public User findByName(String name) throws SQLException {
        String query = "SELECT * FROM user WHERE name = ? ";
        try(PreparedStatement prepStatement = connection.prepareStatement(query)){
            prepStatement.setString(1, name);
            ResultSet resultSet = prepStatement.executeQuery();
            if(resultSet.next()){
                String userName = resultSet.getString("name");
                Long memberID = resultSet.getLong("member_id");
                User user = new User(userName,memberID);
                resultSet.close();
                return user;
            }
            else{
                return null;
            }
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        String query = "SELECT * FROM user";
        List <User> users = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            while(resultSet.next()){
                String name = resultSet.getString("name");
                Long id = resultSet.getLong("member_id");
                User user = new User(name, id);
                users.add(user);
            }            
               return users;
           }
    }
    

    @Override
    public void update(User entity) throws SQLException {
        String query = "UPDATE user SET name = ? WHERE member_id = ?" ;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getID());
            int rowUpdated = preparedStatement.executeUpdate();
            if(rowUpdated > 0){
               System.err.println("User with id " + entity.getID() + " now has new name " + entity.getName());
            }
            else{
                throw new SQLException("Failed to update user with id " + entity.getID());
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM user WHERE member_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if(rowsDeleted > 0){
                System.out.println("Row with id "+ id + " has been deleted Successfully");
            }
            else{
                throw new SQLException("Failed to delete row with id " + id);
            }
        }
    }
   
  
}
