package model;

import java.util.*;
import java.sql.*;

/**
 * This class handles all of the database interaction for the school class
 *
 * @author recinocs
 * @version 2016-12-1
 */
public class School_DB {

    private final DBHandler handler = new DBHandler();
    private Connection con;

    /**
     * This method adds a school if it isn't already present in the database. A
     * school is present if the schoolName is already present in the database
     *
     * @param school, the school being added
     * @return true if the add is successful, false otherwise
     * @throws SQLException
     */
    public boolean addSchool(School school) throws SQLException {
        boolean schoolAdded = false;

        if (!(checkIfSchoolExists(school))) {
            String schoolName;
            String insertSql = "INSERT INTO school VALUES(null, ?);";
            PreparedStatement ps = con.prepareStatement(insertSql);
            schoolName = school.getSchoolName();
            ps.setString(1, schoolName);
            ps.executeUpdate();
            schoolAdded = true;
        }
        return schoolAdded;
    }

    /**
     * This method checks to see if the school already exists in the database
     *
     * @param school, the school being checked in the database
     * @return true if the school is found, false if it isn't
     * @throws SQLException
     */
    public boolean checkIfSchoolExists(School school) throws SQLException {
        boolean schoolFound = false;
        String schoolName;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        schoolName = school.getSchoolName();

        String command = "SELECT * FROM school WHERE school = ?;";
        PreparedStatement ps = con.prepareStatement(command);
        ps.setString(1, schoolName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            schoolFound = true;
        }
        return schoolFound;
    }

    /**
     * This method deletes a school if the school is present in the database
     *
     * @param school, the school being deleted
     * @return true if the school is deleted, false otherwise
     * @throws SQLException
     */
    public boolean deleteSchool(School school) throws SQLException {
        boolean schoolDeleted = false;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        School temp_school = getSchool(school.getSchoolName());

        if (temp_school != null) {
            String delUserCommand = "DELETE FROM school WHERE school = ?;";

            String schoolName = school.getSchoolName();

            PreparedStatement ps = con.prepareStatement(delUserCommand);
            ps.setString(1, schoolName);

            ps.executeUpdate();

            schoolDeleted = true;
        }

        return schoolDeleted;
    }

    /**
     * This method returns a school with the entered schoolname, or null if no
     * school is found
     *
     * @param schoolName, the schoolName for the school being searched
     * @return the School found with the given schoolName, or null if no school
     * exists
     * @throws SQLException
     */
    public School getSchool(String schoolName) throws SQLException {
        School retSchool = null;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        String getSchool = "SELECT * FROM school WHERE school = ?;";
        PreparedStatement ps = con.prepareStatement(getSchool);
        ps.setString(1, schoolName);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            retSchool = new School(schoolName);
        }
        return retSchool;
    }

    /**
     * This method returns all schools in the database
     *
     * @return an arraylist of all schools in the database, or null if no
     * schools exist in the database
     * @throws SQLException
     */
    public ArrayList<School> getAllSchools() {
        ArrayList<School> retSchools = new ArrayList<>();

        String schoolName;

        try {
            if (!(handler.isOpen)) {
                handler.open();
                con = handler.con;
            }
            String getAllSchools = "SELECT * FROM school;";
            PreparedStatement ps = con.prepareStatement(getAllSchools);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                schoolName = rs.getString(2);

                retSchools.add(new School(schoolName));
            }

            if (retSchools.isEmpty()) {
                retSchools = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retSchools;
    }

    /**
     * This method gets the id for a school, or -1 if no school exists
     *
     * @param school, the school being searched for
     * @return the id of school, or -1 if no school is found
     * @throws SQLException
     */
    public int getIdForSchool(School school) throws SQLException {
        int id = -1;
        String schoolName = school.getSchoolName();

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        String sql = "SELECT id FROM school WHERE school = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, schoolName);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            id = rs.getInt(1);
        }

        return id;
    }
}
