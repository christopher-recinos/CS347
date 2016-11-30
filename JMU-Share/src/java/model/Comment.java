/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author greatwmc
 */
public class Comment {
    
    private int commentId, authorId, postId;
    private String comment;
    private String commentAuthorName;
    private int score;

    public String getCommentAuthorName() {
        return commentAuthorName;
    }

    public void setCommentAuthorName(String commentAuthorName) {
        this.commentAuthorName = commentAuthorName;
    }

    public int getScore() {
        DBHandler db = new DBHandler();
        score = db.getCommentScore(commentId);    
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public Comment(int authorId, int postId, String comment) {
        this.authorId = authorId;
        this.postId = postId;
        this.comment = comment;
        
        DBHandler db = new DBHandler();        
        commentAuthorName = db.getAuthorName(authorId);
    }
    
    public Comment(int commentId, int authorId, int postId, String comment) {
        this.commentId = commentId;
        this.authorId = authorId;
        this.postId = postId;
        this.comment = comment;
        
        DBHandler db = new DBHandler();
        commentAuthorName = db.getAuthorName(authorId);
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String createCommentDiv()
    {
        return "<p>Hello<p>";
    }
    
}
