/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Comment;
import model.DBHandler;
import model.NotesImageFile;
import model.NotesImageFile_DB;
import model.Post;

/**
 * @author greatwmc
 */
public class FetchPost {

    Integer postId;
    Post post;
    ArrayList<Comment> commentsList = new ArrayList();
    ArrayList<NotesImageFile> imageList = new ArrayList();
    ArrayList<String> base64URLSafeStrings = new ArrayList();
    String testImageString;

    public String execute() throws SQLException {
        DBHandler db = new DBHandler();
        post = db.getPostFromId(postId);
        commentsList = db.getPostComments(postId);
        NotesImageFile_DB nifdb = new NotesImageFile_DB();
        imageList = nifdb.getAllImagesForPostId(postId);
        for (NotesImageFile img : imageList) {
            testImageString = img.getBase64Encoding();
            String strImg = img.getBase64Encoding();
            base64URLSafeStrings.add(strImg);
            try {
                PrintWriter writer = new PrintWriter("base1.txt", "UTF-8");
                writer.println(strImg);
                writer.close();
            } catch (IOException e) {
   // do something
            }
            System.out.println(strImg);
        }
        
        
        return "success";
    }

    public String getTestImageString() {
        return testImageString;
    }

    public void setTestImageString(String testImageString) {
        this.testImageString = testImageString;
    }

    public ArrayList<NotesImageFile> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<NotesImageFile> imageList) {
        this.imageList = imageList;
    }

    public ArrayList<String> getBase64URLSafeStrings() {
        return base64URLSafeStrings;
    }

    public void setBase64URLSafeStrings(ArrayList<String> base64URLSafeStrings) {
        this.base64URLSafeStrings = base64URLSafeStrings;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public ArrayList<Comment> getCommentsList() {
        return commentsList;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setCommentsList(ArrayList<Comment> commentsList) {
        this.commentsList = commentsList;
    }
}
