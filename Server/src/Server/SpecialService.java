package Server;

import DAO.ServiceDAO;
import DAO.User_SystemDAO;
import Entity.Service;
import Entity.User_system;

import java.io.*;
import java.net.Socket;

//特殊服务，根据不同的socket完成不同的服务
/*
    根据前端返回的id的值来执行不同的操作，我们规定
* id=1，执行登录验证
* id=2，执行第一次保存服务信息
* */
public class SpecialService implements Runnable {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public SpecialService(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            createStream();//后续操作都不用在创建流
            String content = dataInputStream.readUTF();
            System.out.println("客户端发送消息为" + content);
            String message[] = content.split(",");
            String id = message[0];
            System.out.println("执行的操作id为" + id);
            if (id.equals("登录验证")) {
                String s_id = message[1];
                String s_password = message[2];
                loginVerification(s_id, s_password);
            }
            if (id.equals("开始服务")) {
                String mission_id = message[1];//获取任务id(id号自动生成的)
                String service_status = message[2];//获取状态码
                String service_stime = message[3];//获取起始时间
                String service_spic = message[4];//获取起始照片
                bService(mission_id, service_status, service_stime, service_spic);
            }
            if (id.equals("结束服务")) {
                String mission_id = message[1];//获取任务id
                String service_status = message[2];//获取状态码
                String service_etime = message[3];//获取结束时间
                String service_epic = message[4];//获取结束照片
                eService(mission_id, service_status, service_etime, service_epic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //关闭流和socket
    private void close() throws IOException {
        if (dataInputStream != null) {
            dataOutputStream.close();
        }
        if (dataOutputStream != null) {
            dataInputStream.close();
        }
        if (socket != null) {
            socket.close();
        }
    }

    //创建流
    private void createStream() throws IOException {
        //获取客户端的输入输出流
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    private void loginVerification(String s_id, String s_password) throws IOException {
        User_system user = new User_system(s_id, s_password);
        String flag = User_SystemDAO.user_login(user);
        System.out.println("查询的状态是" + flag);
        dataOutputStream.writeUTF("查询结果为" + flag);
        dataOutputStream.flush();
        close();//不管是什么操作，执行完之后都要关闭流
    }

    private void bService(String mission_id, String service_status, String service_stime, String service_spic) throws IOException {
        Service service = new Service();
        service.setMission_id(mission_id);
        service.setService_status(service_status);
        service.setService_stime(service_stime);
        service.setService_spic(service_spic);
        String flag = ServiceDAO.beginService(service);
        dataOutputStream.writeUTF(flag);
        dataOutputStream.flush();
        close();
    }

    private void eService(String mission_id, String service_status, String service_etime, String service_epic) throws IOException {
        Service service = new Service();
        service.setMission_id(mission_id);
        service.setService_status(service_status);
        service.setService_etime(service_etime);
        service.setService_epis(service_epic);
        String flag = ServiceDAO.endService(service);
        dataOutputStream.writeUTF(flag);
        dataOutputStream.flush();
        close();
    }

}
