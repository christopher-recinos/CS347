/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author greatwmc
 */
public class NotesImageFile_DB extends DBHandler {

    /**
     * Adds an image file location to the database
     *
     * @param img
     * @return
     */
    public boolean insertImage(NotesImageFile img) {
        try {
            if (!isOpen) {
                open();
            }
            String sql = "INSERT INTO image (file_location, post_id, content_type) VALUES (?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, img.getUniqueFileLocation());
            ps.setInt(2, img.getPostId());
            ps.setString(3, img.getContentType());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public NotesImageFile retreiveImage(int id) {

        String contentType = "UNKNOWN";
        String uniqueFileLocation = "UNKNOWN";
        int postId = -1;
        NotesImageFile img = null;

        try {
            if (!isOpen) {
                open();
            }
            String sql = "SELECT * FROM  image WHERE id = ?; ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                postId = rs.getInt("post_id");
                uniqueFileLocation = rs.getString("file_location");
                contentType = rs.getString("content_type");
            }
            img = new NotesImageFile(contentType, uniqueFileLocation, id, postId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return img;
    }
    
    public ArrayList<NotesImageFile> getAllImagesForPostId(Integer postId) throws SQLException {
        ArrayList<NotesImageFile> imageFileLocations = new ArrayList();
        if (!isOpen) {
            open();
        }
        String sql = "SELECT * FROM image WHERE post_id = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, postId);
        String imgFullFilePath;
        String contentType;
        NotesImageFile newImg;
        Integer imgId;
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            imgFullFilePath = rs.getString("file_location");
            contentType = rs.getString("content_type");
            imgId = rs.getInt("id");
            newImg = new NotesImageFile(imgId, imgFullFilePath, contentType);
            imageFileLocations.add(newImg);
        }
        return imageFileLocations;
    }
}
