package Server;

import java.io.*;
import java.net.Socket;

/*
为什么不能用BufferedReader和BufferedWriter来写？？？？？害我调了那么久？
 * */
public class TCPClient02 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        DataOutputStream ds = new DataOutputStream(socket.getOutputStream());
        String message = "2,1,1,2019-10-23 17:01,1.jpg";
        ds.writeUTF(message);
        System.out.println("传的数据为"+message);
        DataInputStream di = new DataInputStream(socket.getInputStream());
        String answer = di.readUTF();
        System.out.println("服务器发来的消息"+answer);
    }
}
