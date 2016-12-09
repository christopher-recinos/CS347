/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author greatwmc
 */
public class NotesImageFile {

    private String fileName, contentType, uniqueFileLocation , base64Encoding;
    private Integer id, postId;

    /**
     * Takes a regular filename and creates a unique filename with date values included
     * @param fileName - the plain filename
     * @return 
     */
    public static String createUniqueFileName(String fileName) {
        int hashcode = fileName.hashCode();
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd-HH-mm-ss");
        Calendar calobj = Calendar.getInstance();
        System.out.println(df.format(calobj.getTime()));
        return (df.format(calobj.getTime()) + hashcode + fileName);
    }

    /**
     * Use this for the images that aren't yet hashed by name and date. They
     * have yet to be added to the database.
     */
    public NotesImageFile(String preHashFileName, String contentType) {
        this.fileName = createUniqueFileName(preHashFileName);
        ServletContext context = ServletActionContext.getServletContext();
        String filePath = context.getInitParameter("file-upload");
        this.uniqueFileLocation = filePath + this.fileName;
        this.contentType = contentType;
    }
    
    /**
     * This constructor is used to turn the image into an base64 image buffer so that it can be shown in the jsp
     * @param imgId
     * @param imgFullFilePath
     * @param contentType 
     */
    public NotesImageFile(int imgId, String imgFullFilePath, String contentType) 
    {
        this.id = imgId;
        this.uniqueFileLocation = imgFullFilePath;
        this.contentType = contentType;
        this.fileName = splitTheFilePathOnLastSlashToGetTheName(imgFullFilePath);
        try {
            //this.base64Encoding = Base64.encodeBase64URLSafeString(FileUtils.readFileToByteArray(getImageFile(imgFullFilePath)));
            this.base64Encoding = Base64.encodeBase64String(FileUtils.readFileToByteArray(getImageFile(imgFullFilePath)));
        } catch (IOException ex) {
            Logger.getLogger(NotesImageFile.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
    }
    
    
    private File getImageFile(String imgFullFilePath) {
        return new File(uniqueFileLocation);
    }
    
        /**
     * Use this for the images already in the database as the fileLocation is
     * hashed and dated
     *
     * @param contentType
     * @param uniqueFileLocation
     * @param id
     * @param postId
     */
    public NotesImageFile(String contentType, String uniqueFileLocation, Integer id, Integer postId) {
        this.contentType = contentType;
        this.uniqueFileLocation = uniqueFileLocation;
        this.id = id;
        this.postId = postId;
    }

    
    public static String splitTheFilePathOnLastSlashToGetTheName(String fullPath)
    {
        String[] s = fullPath.split("/");
        String fileName = s[s.length - 1];
        return fileName;
    }

    public String getBase64Encoding() {
        return base64Encoding;
    }

    public void setBase64Encoding(String base64Encoding) {
        this.base64Encoding = base64Encoding;
    }

    public String getUniqueFileLocation() {
        return uniqueFileLocation;
    }

    public void setUniqueFileLocation(String uniqueFileLocation) {
        this.uniqueFileLocation = uniqueFileLocation;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    
}
