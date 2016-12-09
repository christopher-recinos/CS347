package model;

/**
 * This class is a data representation of the role table
 * 
 * @author recinocs
 * @version 2016-11-30
 */
public class Role {
    private String roleName;
    private int roleId;

    public Role(String roleName, int roleId) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    public Role(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String toString() {
        return "Role: " + roleName;
    }
}
