package action;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;

import model.User_DB;
import model.User;

public class LoginAction extends FetchSessionAware {
    private String username, password, hashPassword;
    private User_DB db = new User_DB();
    private User user = null;
    private String role;
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public void validate() {
        try{
            if(username == null || username.equals(""))
                addFieldError("username", "The username field cannot be blank.");
            else if(password == null || password.equals(""))
                addFieldError("password", "The password field cannot be blank.");
            else {
                hashPassword = hashPassword(password);
                user = db.getUser(username, hashPassword);
                if(user == null)
                    addFieldError("username", "Invalid username/password combination.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public String execute() throws SQLException {
        role = db.getRole(user);
        session.put("logged_in", true);
        session.put("user", user);
        session.put("role", role);
        session.put("userId", user.getId());
        return SUCCESS;
    }
    
    private String hashPassword(String password) {
        String hashedPassword = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.reset();
            byte[] bytes = md.digest(password.getBytes());
            hashedPassword = new BigInteger(1, bytes).toString(16);
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return hashedPassword;
    }
}
