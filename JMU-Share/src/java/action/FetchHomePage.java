/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import java.util.ArrayList;
import java.util.Map;
import model.DBHandler;
import model.Post;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author greatwmc
 */
public class FetchHomePage implements SessionAware, ParameterNameAware {

    ArrayList<Post> userPosts;
    ArrayList<Class> classes;
    ArrayList<Post> favoriteNotes;

    private Map<String, Object> userSession;

    public ArrayList<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(ArrayList<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }

    public ArrayList<Post> getFavoriteNotes() {
        return favoriteNotes;
    }

    public void setFavoriteNotes(ArrayList<Post> favoriteNotes) {
        this.favoriteNotes = favoriteNotes;
    }

    public String execute() {
        DBHandler db = new DBHandler();
        Object ses = userSession.get("userId");
        Integer is = (Integer) ses;
        userPosts = db.getPostsWritenByUser(is);
        //classes = db.getClassesForUser(userId);
        //favoriteNotes = db.getUserFavorites(userId);
        return "success";
    }

    /**
     * This allows access to the session so that the userId can be accessed
     * @param session 
     */
    public void setSession(Map<String, Object> session) {

        userSession = session;

    }

    /**
     * This stops the user from hacking the URL request parameters
     * @param parameterName
     * @return 
     */
    public boolean acceptableParameterName(String parameterName) {

        boolean allowedParameterName = true;

        if (parameterName.contains("session") || parameterName.contains("request")) {

            allowedParameterName = false;

        }
        return allowedParameterName;
    }
}
