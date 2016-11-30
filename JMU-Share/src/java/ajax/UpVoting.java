/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import model.DBHandler;

/**
 * This class handles an upvote on a comment. It returns the new score in the
 * result stream. It doesn't need to though.
 *
 * @author greatwmc
 */
public class UpVoting extends ActionSupport {

    private Integer commentIdParam;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Integer getCommentIdParam() {
        return commentIdParam;
    }

    public void setCommentIdParam(Integer commentIdParam) {
        this.commentIdParam = commentIdParam;
    }

    public String execute() throws Exception {
        DBHandler db = new DBHandler();
        System.out.println("Upvote" + commentIdParam);
        db.increaseScore(commentIdParam);
        inputStream = new ByteArrayInputStream(commentIdParam.toString().getBytes("UTF-8"));
        return SUCCESS;
    }
}
