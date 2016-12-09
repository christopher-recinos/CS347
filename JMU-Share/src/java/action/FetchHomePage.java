/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import model.DBHandler;
import model.Post;

/**
 *
 * @author greatwmc
 */
public class FetchHomePage extends FetchSessionAware{

    ArrayList<Post> userPosts;
    ArrayList<Class> classes;
    ArrayList<Post> favoriteNotes;

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

    public String execute() throws SQLException {
        DBHandler db = new DBHandler();
        Object ses = session.get("userId");
        Integer is = (Integer) ses;
        userPosts = db.getPostsWritenByUser(is);
        //classes = db.getClassesForUser(userId);
        //favoriteNotes = db.getUserFavorites(userId);
        return "success";
    }
}
