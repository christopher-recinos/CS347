package model;

/**
 * This class is a data representation of the school table
 * 
 * @author recinocs
 * @version 2016-11-29
 */
public class School {
    private String schoolName;
    private int id;
    
    public School(String schoolName) {
        this.schoolName = schoolName;
    }

    School(int schoolId, String name) {
        this.schoolName = name;
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getSchoolName() {
        return schoolName;
    }
    
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    
    public String toString() {
        return "School: " + schoolName;
    }
}
