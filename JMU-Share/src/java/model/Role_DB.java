package model;

import java.util.*;
import java.sql.*;

/**
 * This class handles all of the database interactions for Roles
 *
 * @author recinocs
 * @version 2016-12-1
 */
public class Role_DB {

    private final DBHandler handler = new DBHandler();
    private Connection con;

    /**
     * This methods adds a role to the database if the role isn't already
     * present
     *
     * @param role, the Role being added
     * @return true if the role is successfully added, false otherwise
     * @throws SQLException
     */
    public boolean addRole(Role role) throws SQLException {
        boolean roleAdded = false;

        if (!(checkIfRoleExists(role))) {
            String roleName = role.getRoleName();

            String insertSql = "INSERT INTO role VALUES(null, ?);";

            PreparedStatement ps = con.prepareStatement(insertSql);

            ps.setString(1, roleName);

            ps.executeUpdate();

            roleAdded = true;
        }
        return roleAdded;
    }

    /**
     * This method checks to see if a role exists in the database or not. A role
     * is defined as existing if the roleName is already present on a role.
     *
     * @param role, the role being checked
     * @return true if the role already exists, false if it doesn't.
     * @throws SQLException
     */
    public boolean checkIfRoleExists(Role role) throws SQLException {
        boolean roleFound = false;
        String roleName;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        roleName = role.getRoleName();

        String command = "SELECT * FROM role WHERE role_name = ?;";
        PreparedStatement ps = con.prepareStatement(command);
        ps.setString(1, roleName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            roleFound = true;
        }
        return roleFound;
    }

    /**
     * This method returns a role with the given roleName, or null if no role
     * exists with that roleName
     * 
     * What is the point of this method? If we have the role name we don't need to 
     * talk with the database.
     *
     * @param roleName, the name being searched for
     * @return the Role with the given roleName, or null if it isn't found
     * @throws SQLException
     */
    public Role getRole(String roleName) throws SQLException {
        Role retRole = null;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        String getRole = "SELECT * FROM role WHERE role_name = ?;";
        PreparedStatement ps = con.prepareStatement(getRole);
        ps.setString(1, roleName);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            retRole = new Role(roleName);
        }

        return retRole;
    }

    /**
     * This method tries to delete a user entered role if it exists
     *
     * @param role, the Role trying to be deleted
     * @return true if the Role is successfully deleted, false otherwise
     * @throws SQLException
     */
    public boolean deleteRole(Role role) throws SQLException {
        boolean roleDeleted = false;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        Role temp_role = getRole(role.getRoleName());

        if (temp_role != null) {
            String delUserCommand = "DELETE FROM role WHERE role_name = ?;";

            String roleName = role.getRoleName();

            PreparedStatement ps = con.prepareStatement(delUserCommand);
            ps.setString(1, roleName);

            ps.executeUpdate();

            roleDeleted = true;
        }

        return roleDeleted;
    }

    /**
     * This method returns all roles in the database, or null if none exist
     *
     * @return an ArrayList of all Roles in the database or null if there are
     * none
     * @throws SQLException
     */
    public ArrayList<Role> getAllRoles() throws SQLException {
        ArrayList<Role> retRoles = new ArrayList<>();

        String roleName;

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }
        String getAllRoles = "SELECT * FROM role;";
        PreparedStatement ps = con.prepareStatement(getAllRoles);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            roleName = rs.getString(2);

            retRoles.add(new Role(roleName));
        }

        if (retRoles.isEmpty()) {
            retRoles = null;
        }
        return retRoles;
    }

    public int getIdForRole(Role role) throws SQLException {
        int id = -1;
        String roleName = role.getRoleName();

        if (!(handler.isOpen)) {
            handler.open();
            con = handler.con;
        }

        String sql = "SELECT id FROM role WHERE role_name = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, roleName);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            id = rs.getInt(1);
        }

        return id;
    }

}
