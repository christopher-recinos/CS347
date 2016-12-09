/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.sql.SQLException;
import java.util.ArrayList;
import model.DBHandler;
import model.Post;
import model.Role;
import model.School;
import model.User;

/**
 *
 * @author greatwmc
 */
public class FetchProfile extends FetchSessionAware{

    private ArrayList<Post> userPosts;
    private User user;
    private School school;
    private Role role;
    private Integer userId;
    
    private ArrayList<Class> classes;

    /**
     * Gets all of the objects for the specified userIdParam.
     * If there is no userIdParam then use the session userId 
     * to get their profile instead.
     * @return 
     */
    public String execute() throws SQLException {
        //Use the userId Parameter if it is available first.
        if (null == userId)
        {
            //If no id is specified, then use the session userId
            Object ses = session.get("userId");
            userId = (Integer) ses;
        }
        
        DBHandler db = new DBHandler();
        userPosts = db.getPostsWritenByUser(userId);
        user = db.getUserFromId(userId);
        school = db.getSchoolFromId(user.getSchoolId());
        role = db.getRoleFromId(user.getRoleId());
     
        //classes = db.getClassesForUser(userIdParam);  Use this to link to the students' classes
        //favoriteNotes = db.getUserFavorites(userId);
        return "success";
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ArrayList<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(ArrayList<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }
}
