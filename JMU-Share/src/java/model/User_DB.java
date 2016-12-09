package model;

import java.util.*;
import java.sql.*;

/**
 * This class handles all database transactions for the User class.
 *
 * @author Christopher Recinos
 * @version 2016-11-28
 */
public class User_DB {

    private final DBHandler handler = new DBHandler();
    private Connection con;

    /**
     * This method takes in a User object and adds it to the database if the
     * email and username are unique
     *
     * @param user, the User being added to the database
     * @return whether or not the add was successful
     * @throws java.sql.SQLException
     */
    public boolean addUser(User user) throws SQLException {
        boolean userAdded = false;

        if (!checkIfFieldsArePresent(user)) {

            String name, username, password, email;
            boolean isProfessor;
            int roleId, schoolId;

            String insertSql = "INSERT INTO user VALUES(null, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement ps = con.prepareStatement(insertSql);

            name = user.getName();
            username = user.getUsername();
            password = user.getPassword();
            email = user.getEmail();
            isProfessor = user.getIsProfessor();
            roleId = user.getRoleId();
            schoolId = user.getSchoolId();

            ps.setString(1, password);
            ps.setString(2, email);
            ps.setString(3, name);
            ps.setString(4, username);
            ps.setInt(5, roleId);
            ps.setBoolean(6, isProfessor);
            ps.setInt(7, schoolId);

            ps.executeUpdate();

            userAdded = true;
        }

        return userAdded;
    }

    /**
     * This method will search the database for the user and delete them if
     * they're found
     *
     * @param user, the User being deleted
     * @return true if the user is successfully deleted, false otherwise.
     * @throws java.sql.SQLException
     */
    public boolean deleteUser(User user) throws SQLException {
        boolean delUser = false;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        User temp = getUser(user.getUsername(), user.getEmail());

        if (temp != null) {
            String delUserCommand = "DELETE FROM user WHERE username = ? AND email = ?;";

            String username, email;
            username = user.getUsername();
            email = user.getEmail();

            PreparedStatement ps = con.prepareStatement(delUserCommand);
            ps.setString(1, username);
            ps.setString(2, email);

            ps.executeUpdate();

            delUser = true;
        }

        return delUser;
    }

    /**
     * This method checks to see whether or not the User parameter is present in
     * the database. A User is defined as present if the username and/or email
     * already exists in the database
     *
     * @param user, the User being checked
     * @return true if the user is in the database, false otherwise
     * @throws java.sql.SQLException
     */
    public boolean checkIfFieldsArePresent(User user) throws SQLException {
        boolean userFound = false;
        String username, email;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        username = user.getUsername();
        email = user.getEmail();

        String command = "SELECT * FROM user WHERE username=? OR email=?;";
        PreparedStatement ps = con.prepareStatement(command);
        ps.setString(1, username);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            userFound = true;
        }
        return userFound;
    }

    /**
     * This method will return the User that corresponds to username and password, or null if no
     * user corresponds to the username and password
     *
     * @param username, the username of the User being searched for
     * @param email, the email of the User being searched for
     * @return found User or null
     * @throws java.sql.SQLException
     */
    public User getUser(String username, String password) throws SQLException {
        User retUser = null;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        String getUser = "SELECT * FROM user WHERE username = ? AND password = ?;";
        PreparedStatement ps = con.prepareStatement(getUser);
        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String email, name;
            boolean isProfessor;
            int roleId, schoolId, id;

            email = rs.getString(3);
            name = rs.getString(4);
            roleId = rs.getInt(6);
            isProfessor = rs.getBoolean(7);
            schoolId = rs.getInt(8);
            id = rs.getInt("id");

            retUser = new User(id, email, name, username, isProfessor, roleId, schoolId);
        }
        return retUser;
    }

    /**
     * This method returns all users in the database
     *
     * @return An arraylist of user objects corresponding to all users in the
     * database. Returns null if there are no users in the database
     * @throws java.sql.SQLException
     */
    public ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> retUsers = new ArrayList<>();

        String password, email, name, username;
        boolean isProfessor;
        int roleId, schoolId;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }
        String getAllUsers = "SELECT * FROM user;";
        PreparedStatement ps = con.prepareStatement(getAllUsers);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            password = rs.getString(2);
            email = rs.getString(3);
            name = rs.getString(4);
            username = rs.getString(5);
            roleId = rs.getInt(6);
            isProfessor = rs.getBoolean(7);
            schoolId = rs.getInt(8);

            retUsers.add(new User(password, email, name, username, roleId, isProfessor, schoolId));
        }

        if (retUsers.isEmpty()) {
            retUsers = null;
        }
        return retUsers;
    }

    /**
     * This method gets the id for a user and returns it, or -1 if no user is
     * found
     *
     * @param user, the user who's id is being searched for
     * @return the id, or -1 if no id is found
     * @throws java.sql.SQLException
     */
    public int getIdForUser(User user) throws SQLException {
        int id = -1;
        String username, email;
        
        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }
        
        username = user.getUsername();
        email = user.getEmail();
        
        String sql = "SELECT id FROM user WHERE username = ? AND email = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, email);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {
            id = rs.getInt(1);
        }
        
        return id;
    }
    
    public String getRole(User user) throws SQLException {
        String role = "";
        
        if(!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }
        
        String sql = "SELECT * FROM role WHERE id = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, user.getRoleId());
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next())
            role = rs.getString(2);
        
        return role;
    }
    
    public boolean checkUsername(String username) throws SQLException {
        boolean usernameFound = false;
        
        if(!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }
        
        String sql = "SELECT username FROM user WHERE username = ?;";
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next())
            usernameFound = true;
        
        return usernameFound;
    }
    
    public boolean checkEmail(String email) throws SQLException {
        boolean emailFound = false;
        
        if(!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }
        
        String sql = "SELECT email FROM user WHERE email = ?;";
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next())
            emailFound = true;
        
        return emailFound;
    }

}
