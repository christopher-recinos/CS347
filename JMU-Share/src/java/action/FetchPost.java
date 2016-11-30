/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.ArrayList;
import model.Comment;
import model.DBHandler;
import model.Post;

/**
 *
 * @author greatwmc
 */
public class FetchPost {
    Integer postId;
    Post post;
    ArrayList<Comment> commentsList = new ArrayList<>();
    
    public String execute() {
        DBHandler db = new DBHandler();
        post = db.getPost(postId);
        commentsList = db.getPostComments(postId);
        return "success";
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
