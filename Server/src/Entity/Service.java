package Entity;

public class Service {
    private String mission_id;//主键任务id号
    private String service_status;//服务状态
    private String service_stime;//服务开始时间
    private String service_etime;//服务结束时间
    private String service_spic;//服务开始图片
    private String service_epis;//服务结束图片

    public String getMission_id() {
        return mission_id;
    }

    public void setMission_id(String mission_id) {
        this.mission_id = mission_id;
    }

    public String getService_status() {
        return service_status;
    }

    public void setService_status(String service_status) {
        this.service_status = service_status;
    }

    public String getService_stime() {
        return service_stime;
    }

    public void setService_stime(String service_stime) {
        this.service_stime = service_stime;
    }

    public String getService_etime() {
        return service_etime;
    }

    public void setService_etime(String service_etime) {
        this.service_etime = service_etime;
    }

    public String getService_spic() {
        return service_spic;
    }

    public void setService_spic(String service_spic) {
        this.service_spic = service_spic;
    }

    public String getService_epis() {
        return service_epis;
    }

    public void setService_epis(String service_epis) {
        this.service_epis = service_epis;
    }

    public Service(String mission_id, String service_status, String service_stime, String service_etime, String service_spic, String service_epis) {
        this.mission_id = mission_id;
        this.service_status = service_status;
        this.service_stime = service_stime;
        this.service_etime = service_etime;
        this.service_spic = service_spic;
        this.service_epis = service_epis;
    }
    public Service(){}
}
