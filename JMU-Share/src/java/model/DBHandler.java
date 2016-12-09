/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author miles greatwood
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.*;

public class DBHandler {

    /**
     * Database access credentials
     */
    protected String driverName = null, url = null, userId = null, password = null;
    /**
     * Database connection
     */
    protected Connection con;
    /**
     * SQL Statement
     */
    protected Statement stmt;
    /**
     * Connection open status
     */
    protected boolean isOpen = false;

    /**
     * Get parameters required to open DBMS connection.
     */
    public DBHandler() {
        // Get DB access credentials from web.xml -- HOW DO WE GET THE WEB.XML TO WORK 
        //NO INITIAL CONTEXT EXCEPTION WITH THIS CODE
        //Apparently this only works when deployed
        try {
            Context envCtx = (Context) (new InitialContext()).lookup("java:comp/env");
            driverName = (String) envCtx.lookup("DriverClassName");
            url = (String) envCtx.lookup("Url");
            userId = (String) envCtx.lookup("UserId");
            password = (String) envCtx.lookup("Password");
        } catch (NoInitialContextException contextError) {
            driverName = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://grove.cs.jmu.edu:3306/team27_db";
            userId = "team27";
            password = "nov#mber5";
        } catch (NamingException e) {
            e.printStackTrace();
        }

        /*
         driverName = "./sqlStuff/connector/mysql-connector-java-5.1.40-bin.jar";
         url = "jdbc:mysql://localhost:3306/team27_db";
         userId = "team27";
         password = "nov#mber5";
         */
    }

    /**
     * Open the DB connection
     *
     * @throws SQLException if the DB connection cannot be established
     */
    public void open() throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        con = DriverManager.getConnection(url, userId, password);
        stmt = con.createStatement();
        isOpen = true;
    }

    /**
     * Close the DB connection.
     *
     * @throws SQLException if the DB connection close fails
     */
    public void close() throws SQLException {
        stmt.close();
        con.close();
        isOpen = false;
    }

    /**
     * Execute a command and return a result count.
     *
     * @param command An SQL command to be executed.
     * @return The count of rows affected by the command
     * @throws SQLException if there is a database error during command
     * execution
     */
    public int doCommand(String command) throws SQLException {
        if (!isOpen) {
            open();
        }
        int resultCount = stmt.executeUpdate(command);
        return resultCount;
    }

    /**
     * Execute a query and return a ResultSet.
     *
     * @param query The SQL query to be executed
     * @return A ResultSet of the query results
     * @throws SQLException if the database query action fails
     */
    public ResultSet doQuery(String query) throws SQLException {
        if (!isOpen) {
            open();
        }
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    public int increaseScore(int id) {

        int score = 0;
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT score FROM comments WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                score = rs.getInt(1);
            }

            score++;
            sql = "UPDATE comments SET score = ? WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, score);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    public void decreaseScore(int id) {
        int score = 0;
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT score FROM comments WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                score = rs.getInt(1);
            }

            score--;
            sql = "UPDATE comments SET score = ? WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, score);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a comment to the database. Uses prepared statement.
     *
     * @param c - The comment with ID's and text
     */
    public void insertComment(Comment c) {
        try {
            if (!isOpen) {
                open();
            }
            String sql = "INSERT INTO comments (post_id, comment, author_id) VALUES (?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(3, c.getAuthorId());
            ps.setInt(1, c.getPostId());
            ps.setString(2, c.getComment());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Post getPostFromId(int post_id) {
        Post post = null;
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT * FROM posts WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, post_id);
            ResultSet rs = ps.executeQuery();

            Integer author_id;
            Integer class_id;
            String contentBody;
            String notes_desc;
            Float rating;
            Integer endorse;

            while (rs.next()) {
                author_id = rs.getInt(2);
                class_id = rs.getInt(3);
                contentBody = rs.getString(4);
                rating = rs.getFloat(5);
                endorse = rs.getInt(6);
                notes_desc = rs.getString(7);
                post = new Post(post_id, author_id, class_id, contentBody, rating, endorse, notes_desc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    /**
     * Retrieves all of the comments from the database for post_id
     *
     * @param post_id
     * @return
     */
    public ArrayList<Comment> getPostComments(int post_id) {
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT * FROM comments WHERE post_id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, post_id);
            ResultSet rs = ps.executeQuery();

            int id, post, author;
            String text;
            Comment comment;

            while (rs.next()) {
                id = rs.getInt(1);
                author = rs.getInt(4);
                post = rs.getInt(2);
                text = rs.getString(3);
                comment = new Comment(id, author, post, text);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public String getAuthorName(int id) {
        String name = "deleted";
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT name FROM user WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    int getCommentScore(int commentId) {
        int score = 0;
        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT score FROM comments WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, commentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                score = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    public ArrayList<Post> getPostsWritenByUser(Integer userId) throws SQLException {
        ArrayList<Post> userPosts = new ArrayList();
            if (!isOpen) {
                open();
            }
            String sql = "SELECT * FROM posts WHERE author_id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            //Create each of the post objects
            while (rs.next()) {
                userPosts.add(new Post(rs));
            }
        
        return userPosts;
    }

    String getClassName(Integer classId) throws SQLException {
        String sql;
        PreparedStatement ps;
        String className = "NO CLASS NAME";
            if (!isOpen) {
                open();
            }
            sql = "SELECT class_name FROM class WHERE class_num = ?;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, classId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                className = rs.getString(1);
            }
        return className;
    }

    public Role getRoleFromId(int roleId) throws SQLException {
        String sql = "SELECT * FROM role WHERE id = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, roleId);
        ResultSet rs = ps.executeQuery();
        Role role = null;
        String role_name;

        if (rs.next()) {
            role_name = rs.getString("role_name");
            role = new Role(role_name, roleId);
        }
        return role;
    }

    public School getSchoolFromId(int schoolId) throws SQLException {
        if (!isOpen) {
            open();
        }
        String name = "No School For This ID" + schoolId;
        School school = null;
        String sql = "SELECT school FROM school WHERE id = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, schoolId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            name = rs.getString("school");
        }
        school = new School(schoolId, name);
        return school;
    }

    public User getUserFromId(Integer userId) throws SQLException {
        String email, name, username;
        boolean isProfessor;
        int roleId, schoolId;
        User user = null;
        if (!isOpen) {
            open();
        }
        String sql = "SELECT * FROM user WHERE id = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            username = rs.getString("username");
            email = rs.getString("email");
            name = rs.getString("name");
            isProfessor = rs.getBoolean("is_professor");
            roleId = rs.getByte("role_id");
            schoolId = rs.getByte("school_id");
            user = new User(userId, email, name, username, isProfessor, roleId, schoolId);
        }
        return user;
    }
}
