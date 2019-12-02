package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/*
* ServerSocket的构造方法有以下几种重载形式：

◆ServerSocket()throws IOException
◆ServerSocket(int port) throws IOException
◆ServerSocket(int port, int backlog) throws IOException
◆ServerSocket(int port, int backlog, InetAddress bindAddr) throws IOException

在以上构造方法中，参数port指定服务器要绑定的端口（服务器要监听的端口），
参数backlog指定客户连接请求队列的长度，
参数bindAddr指定服务器要绑定的IP地址。
* */
public class TCPServer {
    //创建端口号，绑定到65000端口
    private ServerSocket serverSocket;
    private int port = 65000;

    public TCPServer() throws IOException {
        serverSocket = new ServerSocket(port,3);
        System.out.println("服务器启动成功，等待客户端接入");
    }

    public void service() throws IOException {

        //循环监听
        while (true) {//多线程，循环创建多个线程
            Socket socket = null;
            try {
                socket = serverSocket.accept();//如果有客户端连接服务器就要开辟一条新的线程
                /*阻塞进程，只有serverSocket调用了accept方法才会从请求队列中取出连接请求，
                 队列中释放空位，以容纳新的连接请求，否则当连接数达到ServeSocket最大连接数时将报错*/
                System.out.println("New connection accepted"+
                socket.getInetAddress()+":"+socket.getPort());
                new CaluteLength(socket).start();//开辟一条新的线程执行run方法操作
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (socket!=null){
                    socket.close();
                }
            }
        }
    }

    public static void main(String[] args) {
        TCPServer tcpServer;
        try {
            tcpServer = new TCPServer();//新建服务器
            Thread.sleep(60000*10);//使服务器一直存在，不会马上执行结束
//            tcpServer.service();//启动服务器开始接受socket的连接
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
