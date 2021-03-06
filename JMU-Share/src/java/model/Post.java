/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author greatwmc
 */
public class Post {

    Integer id;
    Integer authorId;
    Integer classId;
    String contentBody;
    Float rating;
    Integer endorse;
    String notesDesc;
    String title = "DEFAULT TITLE";
    
    //Here are the derived values
    String authorName;
    String className;


    
    /**
     * This constructor is for putting things INTO the database. That's why it lacks an "id" field.
     * @param authorId
     * @param classId
     * @param contentBody
     * @param rating
     * @param endorse
     * @param notes_desc
     * @param title 
     */
    public Post(Integer authorId, Integer classId, String contentBody, Float rating, Integer endorse, String notes_desc, String title) {
        this.authorId = authorId;
        this.classId = classId;
        this.contentBody = contentBody;
        this.rating = rating;
        this.endorse = endorse;
        this.notesDesc = notes_desc;
        this.title = title;
    }

    /**
     * this constructor is for getting Post objects OUT of the database including the ID.
     * @param id
     * @param authorId
     * @param classId
     * @param contentBody
     * @param rating
     * @param endorse
     * @param notes_desc
     * @param title
     * @throws SQLException 
     */
    public Post(Integer id, Integer authorId, Integer classId, String contentBody, Float rating, Integer endorse, String notes_desc, String title) throws SQLException {
        this.id = id;
        this.authorId = authorId;
        this.classId = classId;
        this.contentBody = contentBody;
        this.rating = rating;
        this.endorse = endorse;
        this.notesDesc = notes_desc;
        this.title = title;
        getDerivedValues(); 
    }
    
    public void getDerivedValues() throws SQLException
    {
        DBHandler db = new DBHandler();
        this.authorName = db.getAuthorName(authorId);
        this.className = db.getClassName(classId);
    }
    
    /**
     * This constructor takes a row of the SELECT * result set in the posts table and creates a Post object from it
     * This could be optimized to be faster and avoid the two extra database accesses. Make it one access and traverse the whole result set.
     * @param rs
     * @throws SQLException 
     */
    public Post(ResultSet rs) throws SQLException
    {
        this.id = rs.getInt(1);
        this.authorId = rs.getInt(2);
        this.classId = rs.getInt(3);
        this.contentBody = rs.getString(4);
        this.rating = rs.getFloat(5);
        this.endorse = rs.getInt(6);
        this.notesDesc = rs.getString(7);
        this.title = rs.getString(8);
        getDerivedValues();
    }
    
    public String getNotesDesc() {
        return notesDesc;
    }

    public void setNotesDesc(String notesDesc) {
        this.notesDesc = notesDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getContentBody() {
        return contentBody;
    }

    public void setContentBody(String contentBody) {
        this.contentBody = contentBody;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getEndorse() {
        return endorse;
    }

    public void setEndorse(Integer endorse) {
        this.endorse = endorse;
    }
    
    public String toString() {
        return "Post: " + getTitle() + "Class Id: " + getClassId();
    }
}
