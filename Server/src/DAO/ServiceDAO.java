package DAO;

import Entity.Service;
import util.DBCon;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
* 1.志愿者开始服务上传第一次服务图片和时间
* 2.志愿者结束服务上传第二次服务图片和时间
* 3.判断用户是否完成了服务
* */
public class ServiceDAO {

    //第一次上传数据保存到数据库
    public static String beginService(Service s){
        PreparedStatement ps;
        String flag = "false";
        String sql = "insert into service(mission_id,service_status,service_stime,service_etime,service_spic, service_epic) value(?, ?, ?, default, ?, default)";
        try {
            ps = DBCon.getPreparedStatement(sql);
            ps.setString(1,s.getMission_id());
            ps.setString(2,s.getService_status());
            ps.setString(3,s.getService_stime());
            ps.setString(4,s.getService_spic());
            int ok = ps.executeUpdate();
            DBCon.close();//关闭语句和连接
            System.out.println("插入的结果为"+ok);
            if (ok>0){
                flag = "true";
                return flag;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static String endService(Service service){
        PreparedStatement ps;
        String flag = "false";
        String sql = "update service set service_status = ?,service_etime = ?,service_epic = ? where mission_id = ?";
        try {
            ps = DBCon.getPreparedStatement(sql);
            ps.setString(1,service.getService_status());
            ps.setString(2,service.getService_etime());
            ps.setString(3,service.getService_epis());
            ps.setString(4,service.getMission_id());
            int ok = ps.executeUpdate();
            DBCon.close();//关闭语句和连接
            System.out.println("插入的结果为"+ok);
            if (ok>0){
                flag = "true";
                return flag;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
