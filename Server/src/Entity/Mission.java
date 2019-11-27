package Entity;

public class Mission {
    private String mission_id;
    private String mission_applicant_id;
    private String volunteer_id;
    private String mission_event_id;

    public Mission(String mission_id, String mission_applicant_id, String volunteer_id, String mission_event_id) {
        this.mission_id = mission_id;
        this.mission_applicant_id = mission_applicant_id;
        this.volunteer_id = volunteer_id;
        this.mission_event_id = mission_event_id;
    }

    public String getMission_id() {
        return mission_id;
    }

    public void setMission_id(String mission_id) {
        this.mission_id = mission_id;
    }

    public String getMission_applicant_id() {
        return mission_applicant_id;
    }

    public void setMission_applicant_id(String mission_applicant_id) {
        this.mission_applicant_id = mission_applicant_id;
    }

    public String getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(String volunteer_id) {
        this.volunteer_id = volunteer_id;
    }

    public String getMission_event_id() {
        return mission_event_id;
    }

    public void setMission_event_id(String mission_event_id) {
        this.mission_event_id = mission_event_id;
    }
}
