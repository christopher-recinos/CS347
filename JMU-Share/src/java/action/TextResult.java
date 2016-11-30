/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.InputStream;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
 
/**
 *
 * @author greatwmc
 */
public class TextResult extends ActionSupport  {
    private InputStream inputStream;
    
    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
 
    public String execute() throws Exception {
        inputStream = new ByteArrayInputStream("Hello from the TextResult.java Class".getBytes("UTF-8"));
        return SUCCESS;
    }
} 
