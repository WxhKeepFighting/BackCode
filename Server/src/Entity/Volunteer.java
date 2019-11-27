package Entity;

public class Volunteer {

    private String v_id;
    private String v_name;
    private String v_phone;
    private String v_address;
    private String v_password;

    public Volunteer(String v_id, String v_password, String v_name, String v_phone, String v_address) {
        this.v_id = v_id;
        this.v_name = v_name;
        this.v_phone = v_phone;
        this.v_address = v_address;
        this.v_password = v_password;
    }

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public String getV_phone() {
        return v_phone;
    }

    public void setV_phone(String v_phone) {
        this.v_phone = v_phone;
    }

    public String getV_address() {
        return v_address;
    }

    public void setV_address(String v_address) {
        this.v_address = v_address;
    }

    public void setV_password(String v_password) {
        this.v_password = v_password;
    }

    public String getV_password() {
        return v_password;
    }
}
