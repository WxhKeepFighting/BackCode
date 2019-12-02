package Entity;

public class User_system {
    private String s_id;
    private String s_password;
    private String role_id;
    private String active;

    public User_system(String s_id, String s_password, String role_id, String active) {
        this.s_id = s_id;
        this.s_password = s_password;
        this.role_id = role_id;
        this.active = active;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
