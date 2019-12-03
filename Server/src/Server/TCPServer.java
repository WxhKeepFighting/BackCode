package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private ServerSocket ss;
    private int port = 8888;

    public TCPServer() throws IOException {
        ss = new ServerSocket(port);
        System.out.println("服务器启动成功，等待客户端接入");
    }

    private void mainService(){//主服务即循环监听各种连接请求
        while (true) {//多线程，循环创建多个线程
            Socket s = null;
            try {
                s = ss.accept();//获得客户端的套接字
                /*阻塞进程，只有serverSocket调用了accept方法才会从请求队列中取出连接请求，
                 队列中释放空位，以容纳新的连接请求，否则当连接数达到ServeSocket最大连接数时将报错*/
                System.out.println("客户端接入"+
                s.getInetAddress().getLocalHost());
                System.out.println("开始执行具体服务");
                new Thread(new SpecialService(s)).start();//开辟一条新的线程执行run方法操作
                System.out.println("方法执行结束");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TCPServer tcpServer;
        try {
            tcpServer = new TCPServer();//新建服务器
//            Thread.sleep(60000*10);//当前线程睡眠，不会马上执行结束
            tcpServer.mainService();//启动服务器开始接受socket的连接
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
