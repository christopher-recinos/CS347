/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import model.Comment;
import model.DBHandler;

public class FetchComments extends ActionSupport{
    
    Integer post_id;
    Integer ses_post_id;
    ArrayList<Comment> list = new ArrayList<>();

    public Integer getSes_post_id() {
        return ses_post_id;
    }

    public void setSes_post_id(Integer ses_post_id) {
        this.ses_post_id = ses_post_id;
    }
   
    
    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public ArrayList<Comment> getList() {
        return list;
    }

    public void setList(ArrayList<Comment> list) {
        this.list = list;
    }

    /**
     * In order to get access to the post_id in the parameters, the request parameters must be pushed onto the stack.
     * Then get the stack context
     * Then get the parameters HashMap
     * Then get the post_id as a String[]
     * Then get the post id from the 0 index of the String array
     * Then parse that String as an integer and get the comments for that ID.
     * @return 
     */
    public String execute() {
        DBHandler db = new DBHandler();
        list = post_id != null? db.getPostComments(post_id) : db.getPostComments(ses_post_id);
        return "success";
    }
}
