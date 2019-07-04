/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd.dao;

import bdd.entities.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author otmangx
 */
public interface UsersDAO {
    public void create(User user) throws SQLException;
    public boolean isUser(User user) throws SQLException;
    public List<User> getAllUsers() throws SQLException;
    public void update(User user) throws SQLException;
    public void delete(Integer id) throws SQLException;
    /*
    
    public void update(Users user);
    public void delete(Users user);
    public void deleteAll();
    
    public Users getUserbyName(String username);
    
*/
}
